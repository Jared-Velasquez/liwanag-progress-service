package com.liwanag.progress.domain.progress;

import com.liwanag.progress.domain.content.FqId;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
public final class ActivityProgress implements Progress {
    @NotNull
    private UUID userId;
    @NotNull
    private FqId fqid;
    @NotNull
    private ProgressStatus status;

    // Note: no totalCount or completedCount for ActivityProgress since we want to account
    // for user-generated questions

    private Instant firstStartedAt;
    private Instant lastUpdatedAt;
    private Instant firstCompletedAt;
    private Instant completedAt;

    public static ActivityProgress createNew(UUID userId, FqId fqid) {
        if (!fqid.isActivityFqId()) {
            throw new IllegalArgumentException("FqId must be of type Activity");
        }
        return ActivityProgress.builder()
                .userId(userId)
                .fqid(fqid)
                .status(ProgressStatus.NOT_STARTED)
                .firstStartedAt(null)
                .lastUpdatedAt(null)
                .firstCompletedAt(null)
                .completedAt(null)
                .build();
    }

    public static ActivityProgress createInProgress(UUID userId, FqId fqid) {
        if (!fqid.isActivityFqId()) {
            throw new IllegalArgumentException("FqId must be of type Activity");
        }
        return ActivityProgress.builder()
                .userId(userId)
                .fqid(fqid)
                .status(ProgressStatus.IN_PROGRESS)
                .firstStartedAt(Instant.now())
                .lastUpdatedAt(Instant.now())
                .build();
    }

    /**
     * Marks the progress as completed if not already completed.
     * @return Boolean indicating if this marks the first activity completion (true) or was already completed (false).
     */
    public Boolean markCompleted() {
        if (this.status == ProgressStatus.COMPLETED) {
            return false;
        }
        this.status = ProgressStatus.COMPLETED;
        this.completedAt = Instant.now();
        if (this.fqid.isActivityFqId() && this.firstCompletedAt == null) {
            this.firstCompletedAt = this.completedAt;
            return true;
        }
        return false;
    }

    public Boolean isCompleted() {
        return this.status == ProgressStatus.COMPLETED;
    }
}
