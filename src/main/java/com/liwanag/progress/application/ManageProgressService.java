package com.liwanag.progress.application;

import com.liwanag.progress.domain.content.Episode;
import com.liwanag.progress.domain.content.Unit;
import com.liwanag.progress.domain.event.SessionFinishedEvent;
import com.liwanag.progress.domain.content.FqId;
import com.liwanag.progress.domain.progress.Progress;
import com.liwanag.progress.domain.progress.ProgressStatus;
import com.liwanag.progress.ports.primary.ManageProgress;
import com.liwanag.progress.ports.secondary.CanonicalStore;
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

    @Override
    public void onSessionFinished(SessionFinishedEvent event) {
        // Try loading the activity progress corresponding to the session
        log.info("Handling SessionFinishedEvent for userId: {}, fqId: {}", event.userId(), event.fqid());
        Progress activityProgress = progressStore.load(event.userId(), event.fqid()).orElse(
                Progress.builder()
                        .userId(event.userId())
                        .fqid(event.fqid())
                        .status(ProgressStatus.IN_PROGRESS)
                        .build()
        );

        // Update the activity progress to completed
        // TODO: how should sessions handle skips/retries/aborts?
        activityProgress.markCompleted();
        progressStore.save(activityProgress);
        log.info("Activity progress marked as completed for userId: {}, fqId: {}", event.userId(), event.fqid());

        // Try to complete the episode if all activities are completed
        tryCompleteEpisode(event.userId(), event.fqid());

        // Try to complete the unit if all episodes are completed
        tryCompleteUnit(event.userId(), event.fqid());
    }

    private void tryCompleteEpisode(UUID userId, FqId episodeId) {
        // TODO: load episode content to get list of activities
        Episode episode = canonicalStore.loadEpisode(episodeId).orElseThrow(() -> new NoSuchElementException("Episode not found: " + episodeId);

        // TODO: check if all activities are completed

        // TODO: if all activities are completed, mark episode progress as completed and save

        // TODO: emit EpisodeCompletedEvent if episode is completed
    }

    private void tryCompleteUnit(UUID userId, FqId unitId) {
        // TODO: load unit content to get list of episodes
        Unit unit = canonicalStore.loadUnit(unitId).orElseThrow(() -> new NoSuchElementException("Unit not found: " + unitId));

        // TODO: check if all episodes are completed

        // TODO: if all episodes are completed, mark unit progress as completed and save

        // TODO: emit UnitCompletedEvent if unit is completed
    }
}
