package com.liwanag.progress.application;

import com.liwanag.progress.domain.event.AnswerEvaluatedEvent;
import com.liwanag.progress.domain.event.SessionFinishedEvent;
import com.liwanag.progress.domain.progress.Progress;
import com.liwanag.progress.domain.progress.ProgressStatus;
import com.liwanag.progress.ports.primary.ManageProgress;
import com.liwanag.progress.ports.secondary.ProgressStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
public class ManageProgressService implements ManageProgress {
    private final ProgressStore progressStore;

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
    }
}
