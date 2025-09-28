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
    private List<FqId> completedEpisodeFqIds;
    @NotNull
    private Instant firstStartedAt;
    @NotNull
    private Instant lastUpdatedAt;
    private Instant firstCompletedAt;
    private Instant completedAt;

    public static UnitProgress create(UUID userId, FqId fqid, Integer totalCount) {
        if (!fqid.isUnitFqId()) {
            throw new IllegalArgumentException("FqId must be of type Unit");
        }
        return UnitProgress.builder()
                .userId(userId)
                .fqid(fqid)
                .status(ProgressStatus.IN_PROGRESS)
                .totalCount(totalCount)
                .completedCount(0)
                .firstStartedAt(Instant.now())
                .lastUpdatedAt(Instant.now())
                .firstCompletedAt(null)
                .completedAt(null)
                .build();
    }

    @Override
    public Boolean markCompleted() {
        return null;
    }

    @Override
    public Boolean isCompleted() {
        return null;
    }
}
