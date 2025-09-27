package com.liwanag.progress.domain.progress;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Builder
public final class Progress {
    @NotNull
    private UUID userId;
    @NotNull
    private FqId fqid;
    @NotNull
    private ProgressStatus status;
    /*
     * Total number of activities for episodes
     * or total number of episodes for units.
     */
    @NotNull
    private Integer totalCount;
    /*
     * Number of completed activities for episodes,
     * or number of completed episodes for units.
     */
    @NotNull
    private Integer completedCount;
    @NotNull
    private Instant firstStartedAt;
    @NotNull
    private Instant lastUpdatedAt;
    private Instant firstCompletedAt;
    private Instant completedAt;

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
}
