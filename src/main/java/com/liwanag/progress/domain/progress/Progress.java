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
     * Total number of questions for activities,
     * total number of activities for episodes,
     * or total number of episodes for units.
     */
    @NotNull
    private Integer totalCount;
    /*
     * Number of completed questions for activities,
     * number of completed activities for episodes,
     * or number of completed episodes for units.
     */
    @NotNull
    private Integer completedCount;
    @NotNull
    private Instant firstStartedAt;
    @NotNull
    private Instant lastUpdatedAt;
    private Instant completedAt;
}
