package com.liwanag.progress.domain.progress;

import com.liwanag.progress.domain.content.FqId;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Builder
public final class UnitProgress implements Progress {
    @NotNull
    private UUID userId;
    @NotNull
    private FqId fqid;
    @NotNull
    private ProgressStatus status;
    @NotNull
    private Integer totalCount;
    @NotNull
    private Integer completedCount;
    @NotNull
    private Set<FqId> completedEpisodeFqIds;
    private Instant firstStartedAt;
    private Instant lastUpdatedAt;
    private Instant firstCompletedAt;
    private Instant completedAt;

    public static UnitProgress createNew(UUID userId, FqId fqid, Integer totalCount) {
        if (!fqid.isUnitFqId()) {
            throw new IllegalArgumentException("FqId must be of type Unit");
        }
        return UnitProgress.builder()
                .userId(userId)
                .fqid(fqid.toUnitFqId()) // Discard the episode and activity part if present
                .status(ProgressStatus.NOT_STARTED)
                .totalCount(totalCount)
                .completedCount(0)
                .completedEpisodeFqIds(new HashSet<>())
                .firstStartedAt(Instant.now())
                .lastUpdatedAt(Instant.now())
                .firstCompletedAt(null)
                .completedAt(null)
                .build();
    }

    public static UnitProgress createInProgress(UUID userId, FqId fqid, Integer totalCount) {
        if (!fqid.isUnitFqId()) {
            throw new IllegalArgumentException("FqId must be of type Unit");
        }
        return UnitProgress.builder()
                .userId(userId)
                .fqid(fqid.toUnitFqId()) // Discard the episode and activity part if present
                .status(ProgressStatus.IN_PROGRESS)
                .totalCount(totalCount)
                .completedCount(0)
                .completedEpisodeFqIds(new HashSet<>())
                .firstStartedAt(Instant.now())
                .lastUpdatedAt(Instant.now())
                .firstCompletedAt(null)
                .completedAt(null)
                .build();
    }

    public Boolean recordEpisodeCompletion(FqId episodeFqId) {
        if (!episodeFqId.isEpisodeFqId()) {
            throw new IllegalArgumentException("FqId must be of type Episode");
        }
        if (this.completedEpisodeFqIds.contains(episodeFqId)) {
            return false;
        }
        this.completedEpisodeFqIds.add(episodeFqId);
        this.completedCount = this.completedEpisodeFqIds.size();
        this.lastUpdatedAt = Instant.now();
        if (this.status != ProgressStatus.COMPLETED) {
            this.status = ProgressStatus.IN_PROGRESS;
        }
        if (this.firstStartedAt == null) {
            this.firstStartedAt = this.lastUpdatedAt;
        }
        if (this.completedCount.equals(this.totalCount)) {
            this.status = ProgressStatus.COMPLETED;
            this.completedAt = this.lastUpdatedAt;
            if (this.firstCompletedAt == null) {
                this.firstCompletedAt = this.lastUpdatedAt;
            }
        }
        return true;
    }

    @Override
    public Boolean isCompleted() {
        return null;
    }
}
