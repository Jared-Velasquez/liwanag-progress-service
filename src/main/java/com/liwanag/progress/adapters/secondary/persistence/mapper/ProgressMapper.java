package com.liwanag.progress.adapters.secondary.persistence.mapper;

import com.liwanag.progress.adapters.secondary.persistence.entity.ProgressEntity;
import com.liwanag.progress.adapters.secondary.persistence.entity.ProgressKeys;
import com.liwanag.progress.domain.progress.Progress;
import com.liwanag.progress.domain.progress.ProgressStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public final class ProgressMapper {
    public ProgressEntity toEntity(Progress model) {
        return ProgressEntity.builder()
                .pk(ProgressKeys.progressPk(model.getUserId()))
                .sk(ProgressKeys.progressSk(model.getFqid()))
                .userId(model.getUserId().toString())
                .status(model.getStatus().name())
                .unitId(model.getFqid().getUnitId())
                .episodeId(model.getFqid().getEpisodeId())
                .activityId(model.getFqid().getActivityId())
                .totalCount(model.getTotalCount())
                .completedCount(model.getCompletedCount())
                .firstStartedAt(model.getFirstStartedAt().toEpochMilli())
                .lastUpdatedAt(model.getLastUpdatedAt().toEpochMilli())
                .completedAt(model.getCompletedAt() != null ? model.getCompletedAt().toEpochMilli() : null)
                .build();
    }

    public Progress toModel(ProgressEntity entity) {
        return Progress.builder()
                .userId(UUID.fromString(entity.getUserId()))
                .fqid(ProgressKeys.extractFqId(entity.getSk()))
                .status(ProgressStatus.valueOf(entity.getStatus())) // TODO: perform exception handling
                .totalCount(entity.getTotalCount())
                .completedCount(entity.getCompletedCount())
                .firstStartedAt(java.time.Instant.ofEpochMilli(entity.getFirstStartedAt()))
                .lastUpdatedAt(java.time.Instant.ofEpochMilli(entity.getLastUpdatedAt()))
                .completedAt(entity.getCompletedAt() != null ? java.time.Instant.ofEpochMilli(entity.getCompletedAt()) : null)
                .build();
    }
}
