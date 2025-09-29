package com.liwanag.progress.application;

import com.liwanag.progress.domain.content.Episode;
import com.liwanag.progress.domain.content.Unit;
import com.liwanag.progress.domain.event.SessionFinishedEvent;
import com.liwanag.progress.domain.content.FqId;
import com.liwanag.progress.domain.progress.*;
import com.liwanag.progress.ports.primary.ManageProgress;
import com.liwanag.progress.ports.secondary.CanonicalStore;
import com.liwanag.progress.ports.secondary.EventBus;
import com.liwanag.progress.ports.secondary.ProgressStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.NoSuchElementException;
import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
public class ManageProgressService implements ManageProgress {
    private final ProgressStore progressStore;
    private final CanonicalStore canonicalStore;
    private final EventBus eventBus;

    @Override
    public void onSessionFinished(SessionFinishedEvent event) {
        // Try loading the activity progress corresponding to the session
        log.info("Handling SessionFinishedEvent for userId: {}, fqId: {}", event.userId(), event.fqid());
        ActivityProgress activityProgress = progressStore.loadActivity(event.userId(), event.fqid()).orElse(
                ActivityProgress.createInProgress(event.userId(), event.fqid())
        );

        // Update the activity progress to completed
        // TODO: how should sessions handle skips/retries/aborts?
        activityProgress.markCompleted();
        progressStore.save(activityProgress);
        log.info("Activity progress marked as completed for userId: {}, fqId: {}", event.userId(), event.fqid());

        // Try to complete the episode
        Boolean isEpisodeCompleted = tryCompleteEpisode(event.userId(), event.fqid());

        // Try to complete the unit
        if (!isEpisodeCompleted)
            return;

        tryCompleteUnit(event.userId(), event.fqid());
    }

    /**
     * Try to complete the episode if all activities are completed
     * @param userId
     * @param activityId
     * @return True if episode is completed, false otherwise
     */
    private Boolean tryCompleteEpisode(UUID userId, FqId activityId) {
        if (!activityId.isActivityFqId()) {
            log.warn("FqId is not of type Activity: {}", activityId);
            return false;
        }

        FqId episodeId = activityId.toEpisodeFqId();

        // load episode content to get list of activities
        Episode episode = canonicalStore.loadEpisode(episodeId).orElseThrow(() -> new NoSuchElementException("Episode not found: " + episodeId));

        EpisodeProgress episodeProgress = progressStore.loadEpisode(userId, episodeId).orElse(
                EpisodeProgress.createInProgress(userId, episodeId, episode.getActivityFqIds().size())
        );

        episodeProgress.recordActivityCompletion(activityId);
        progressStore.save(episodeProgress);

        // TODO: emit EpisodeCompletedEvent if episode is completed
        // eventBus.emit(null);

        return episodeProgress.isCompleted();
    }

    private Boolean tryCompleteUnit(UUID userId, FqId activityId) {
        if (!activityId.isActivityFqId()) {
            log.warn("FqId is not of type Activity: {}", activityId);
            return false;
        }

        FqId unitId = activityId.toUnitFqId();
        FqId episodeId = activityId.toEpisodeFqId();

        // load unit content to get list of episodes
        Unit unit = canonicalStore.loadUnit(activityId).orElseThrow(() -> new NoSuchElementException("Unit not found: " + unitId));

        UnitProgress unitProgress = progressStore.loadUnit(userId, unitId).orElse(
                UnitProgress.createInProgress(userId, unitId, unit.getEpisodeFqIds().size())
        );

        unitProgress.recordEpisodeCompletion(episodeId);
        progressStore.save(unitProgress);

        // TODO: emit UnitCompletedEvent if unit is completed
        // eventBus.emit(null);

        return unitProgress.isCompleted();
    }
}
