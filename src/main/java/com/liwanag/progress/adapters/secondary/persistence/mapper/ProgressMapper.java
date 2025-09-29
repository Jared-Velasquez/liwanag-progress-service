package com.liwanag.progress.adapters.secondary.persistence.mapper;

import com.liwanag.progress.adapters.secondary.persistence.entity.*;
import com.liwanag.progress.domain.content.FqId;
import com.liwanag.progress.domain.progress.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.HashSet;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.liwanag.progress.adapters.secondary.persistence.entity.ProgressKeys.progressPk;
import static com.liwanag.progress.adapters.secondary.persistence.entity.ProgressKeys.progressSk;

@Component
@Slf4j
public final class ProgressMapper {
    public ActivityProgressEntity toEntity(ActivityProgress model) {
        return ActivityProgressEntity.builder()
                .pk(progressPk(model.getUserId()))
                .sk(progressSk(model.getFqid()))
                .userId(model.getUserId().toString())
                .unitId(model.getFqid().getUnitId())
                .episodeId(model.getFqid().getEpisodeId())
                .activityId(model.getFqid().getActivityId())
                .status(model.getStatus().name())
                .firstStartedAt(model.getFirstStartedAt() != null ? model.getFirstStartedAt().toEpochMilli() : null)
                .lastUpdatedAt(model.getLastUpdatedAt() != null ? model.getLastUpdatedAt().toEpochMilli() : null)
                .completedAt(model.getCompletedAt() != null ? model.getCompletedAt().toEpochMilli() : null)
                .firstCompletedAt(model.getFirstCompletedAt() != null ? model.getFirstCompletedAt().toEpochMilli() : null)
                .build();
    }

    public ActivityProgress toModel(ActivityProgressEntity entity) {
        return ActivityProgress.builder()
                .userId(UUID.fromString(entity.getUserId()))
                .fqid(ProgressKeys.extractFqId(entity.getSk()))
                .status(ProgressStatus.valueOf(entity.getStatus())) // TODO: perform exception handling
                .firstStartedAt(entity.getFirstStartedAt() != null ? Instant.ofEpochMilli(entity.getFirstStartedAt()) : null)
                .lastUpdatedAt(entity.getLastUpdatedAt() != null ? Instant.ofEpochMilli(entity.getLastUpdatedAt()) : null)
                .firstCompletedAt(entity.getFirstCompletedAt() != null ? Instant.ofEpochMilli(entity.getFirstCompletedAt()) : null)
                .completedAt(entity.getCompletedAt() != null ? Instant.ofEpochMilli(entity.getCompletedAt()) : null)
                .build();
    }

    public EpisodeProgressEntity toEntity(EpisodeProgress model) {
        return EpisodeProgressEntity.builder()
                .pk(progressPk(model.getUserId()))
                .sk(progressSk(model.getFqid()))
                .userId(model.getUserId().toString())
                .unitId(model.getFqid().getUnitId())
                .episodeId(model.getFqid().getEpisodeId())
                .status(model.getStatus().name())
                .totalCount(model.getTotalCount())
                .completedCount(model.getCompletedCount())
                .completedActivityFqIds(model.getCompletedActivityFqIds().stream().map(Object::toString).toList())
                .firstStartedAt(model.getFirstStartedAt() != null ? model.getFirstStartedAt().toEpochMilli() : null)
                .lastUpdatedAt(model.getLastUpdatedAt() != null ? model.getLastUpdatedAt().toEpochMilli() : null)
                .firstCompletedAt(model.getFirstCompletedAt() != null ? model.getFirstCompletedAt().toEpochMilli() : null)
                .completedAt(model.getCompletedAt() != null ? model.getCompletedAt().toEpochMilli() : null)
                .build();
    }

    public EpisodeProgress toModel(EpisodeProgressEntity entity) {
        return EpisodeProgress.builder()
                .userId(UUID.fromString(entity.getUserId()))
                .fqid(ProgressKeys.extractFqId(entity.getSk()))
                .status(ProgressStatus.valueOf(entity.getStatus())) // TODO: perform exception handling
                .totalCount(entity.getTotalCount())
                .completedCount(entity.getCompletedCount())
                .completedActivityFqIds(entity.getCompletedActivityFqIds().stream().map(FqId::new).collect(Collectors.toCollection(HashSet::new)))
                .firstStartedAt(entity.getFirstStartedAt() != null ? Instant.ofEpochMilli(entity.getFirstStartedAt()) : null)
                .lastUpdatedAt(entity.getLastUpdatedAt() != null ? Instant.ofEpochMilli(entity.getLastUpdatedAt()) : null)
                .firstCompletedAt(entity.getFirstCompletedAt() != null ? Instant.ofEpochMilli(entity.getFirstCompletedAt()) : null)
                .completedAt(entity.getCompletedAt() != null ? Instant.ofEpochMilli(entity.getCompletedAt()) : null)
                .build();
    }

    public UnitProgressEntity toEntity(UnitProgress model) {
        return UnitProgressEntity.builder()
                .pk(progressPk(model.getUserId()))
                .sk(progressSk(model.getFqid()))
                .userId(model.getUserId().toString())
                .unitId(model.getFqid().getUnitId())
                .status(model.getStatus().name())
                .totalCount(model.getTotalCount())
                .completedCount(model.getCompletedCount())
                .completedEpisodeFqIds(model.getCompletedEpisodeFqIds().stream().map(Object::toString).toList())
                .firstStartedAt(model.getFirstStartedAt() != null ? model.getFirstStartedAt().toEpochMilli() : null)
                .lastUpdatedAt(model.getLastUpdatedAt() != null ? model.getLastUpdatedAt().toEpochMilli() : null)
                .firstCompletedAt(model.getFirstCompletedAt() != null ? model.getFirstCompletedAt().toEpochMilli() : null)
                .completedAt(model.getCompletedAt() != null ? model.getCompletedAt().toEpochMilli() : null)
                .build();
    }

    public UnitProgress toModel(UnitProgressEntity entity) {
        log.info("Mapping FqId from entity: {}", entity.getSk());
        return UnitProgress.builder()
                .userId(UUID.fromString(entity.getUserId()))
                .fqid(ProgressKeys.extractFqId(entity.getSk()))
                .status(ProgressStatus.valueOf(entity.getStatus())) // TODO: perform exception handling
                .totalCount(entity.getTotalCount())
                .completedCount(entity.getCompletedCount())
                .completedEpisodeFqIds(entity.getCompletedEpisodeFqIds().stream().map(FqId::new).collect(Collectors.toCollection(HashSet::new)))
                .firstStartedAt(entity.getFirstStartedAt() != null ? Instant.ofEpochMilli(entity.getFirstStartedAt()) : null)
                .lastUpdatedAt(entity.getLastUpdatedAt() != null ? Instant.ofEpochMilli(entity.getLastUpdatedAt()) : null)
                .firstCompletedAt(entity.getFirstCompletedAt() != null ? Instant.ofEpochMilli(entity.getFirstCompletedAt()) : null)
                .completedAt(entity.getCompletedAt() != null ? Instant.ofEpochMilli(entity.getCompletedAt()) : null)
                .build();
    }
}
