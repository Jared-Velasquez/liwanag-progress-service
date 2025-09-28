package com.liwanag.progress.adapters.secondary.persistence.mapper;

import com.liwanag.progress.adapters.secondary.persistence.entity.*;
import com.liwanag.progress.domain.content.FqId;
import com.liwanag.progress.domain.progress.*;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static com.liwanag.progress.adapters.secondary.persistence.entity.ProgressKeys.progressPk;
import static com.liwanag.progress.adapters.secondary.persistence.entity.ProgressKeys.progressSk;

@Component
public final class ProgressMapper {
//    public ProgressEntity toEntity(Progress model) {
//        return ProgressEntity.builder()
//                .pk(ProgressKeys.progressPk(model.getUserId()))
//                .sk(ProgressKeys.progressSk(model.getFqid()))
//                .userId(model.getUserId().toString())
//                .status(model.getStatus().name())
//                .unitId(model.getFqid().getUnitId())
//                .episodeId(model.getFqid().getEpisodeId())
//                .activityId(model.getFqid().getActivityId())
//                .totalCount(model.getTotalCount())
//                .completedCount(model.getCompletedCount())
//                .firstStartedAt(model.getFirstStartedAt().toEpochMilli())
//                .lastUpdatedAt(model.getLastUpdatedAt().toEpochMilli())
//                .completedAt(model.getCompletedAt() != null ? model.getCompletedAt().toEpochMilli() : null)
//                .build();
//    }
//
//    public Progress toModel(ProgressEntity entity) {
//        return Progress.builder()
//                .userId(UUID.fromString(entity.getUserId()))
//                .fqid(ProgressKeys.extractFqId(entity.getSk()))
//                .status(ProgressStatus.valueOf(entity.getStatus())) // TODO: perform exception handling
//                .totalCount(entity.getTotalCount())
//                .completedCount(entity.getCompletedCount())
//                .firstStartedAt(java.time.Instant.ofEpochMilli(entity.getFirstStartedAt()))
//                .lastUpdatedAt(java.time.Instant.ofEpochMilli(entity.getLastUpdatedAt()))
//                .completedAt(entity.getCompletedAt() != null ? java.time.Instant.ofEpochMilli(entity.getCompletedAt()) : null)
//                .build();
//    }

    public ActivityProgressEntity toEntity(ActivityProgress model) {
        return ActivityProgressEntity.builder()
                .pk(progressPk(model.getUserId()))
                .sk(progressSk(model.getFqid()))
                .userId(model.getUserId().toString())
                .unitId(model.getFqid().getUnitId())
                .episodeId(model.getFqid().getEpisodeId())
                .activityId(model.getFqid().getActivityId())
                .status(model.getStatus().name())
                .firstStartedAt(model.getFirstStartedAt().toEpochMilli())
                .lastUpdatedAt(model.getLastUpdatedAt().toEpochMilli())
                .completedAt(model.getCompletedAt() != null ? model.getCompletedAt().toEpochMilli() : null)
                .firstCompletedAt(model.getFirstCompletedAt() != null ? model.getFirstCompletedAt().toEpochMilli() : null)
                .build();
    }

    public ActivityProgress toModel(ActivityProgressEntity entity) {
        return ActivityProgress.builder()
                .userId(UUID.fromString(entity.getUserId()))
                .fqid(ProgressKeys.extractFqId(entity.getSk()))
                .status(ProgressStatus.valueOf(entity.getStatus())) // TODO: perform exception handling
                .firstStartedAt(java.time.Instant.ofEpochMilli(entity.getFirstStartedAt()))
                .lastUpdatedAt(java.time.Instant.ofEpochMilli(entity.getLastUpdatedAt()))
                .firstCompletedAt(entity.getFirstCompletedAt() != null ? java.time.Instant.ofEpochMilli(entity.getFirstCompletedAt()) : null)
                .completedAt(entity.getCompletedAt() != null ? java.time.Instant.ofEpochMilli(entity.getCompletedAt()) : null)
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
                .firstStartedAt(model.getFirstStartedAt().toEpochMilli())
                .lastUpdatedAt(model.getLastUpdatedAt().toEpochMilli())
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
                .completedActivityFqIds(entity.getCompletedActivityFqIds().stream().map(FqId::new).toList())
                .firstStartedAt(java.time.Instant.ofEpochMilli(entity.getFirstStartedAt()))
                .lastUpdatedAt(java.time.Instant.ofEpochMilli(entity.getLastUpdatedAt()))
                .firstCompletedAt(entity.getFirstCompletedAt() != null ? java.time.Instant.ofEpochMilli(entity.getFirstCompletedAt()) : null)
                .completedAt(entity.getCompletedAt() != null ? java.time.Instant.ofEpochMilli(entity.getCompletedAt()) : null)
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
                .firstStartedAt(model.getFirstStartedAt().toEpochMilli())
                .lastUpdatedAt(model.getLastUpdatedAt().toEpochMilli())
                .firstCompletedAt(model.getFirstCompletedAt() != null ? model.getFirstCompletedAt().toEpochMilli() : null)
                .completedAt(model.getCompletedAt() != null ? model.getCompletedAt().toEpochMilli() : null)
                .build();
    }

    public UnitProgress toModel(UnitProgressEntity entity) {
        return UnitProgress.builder()
                .userId(UUID.fromString(entity.getUserId()))
                .fqid(ProgressKeys.extractFqId(entity.getSk()))
                .status(ProgressStatus.valueOf(entity.getStatus())) // TODO: perform exception handling
                .totalCount(entity.getTotalCount())
                .completedCount(entity.getCompletedCount())
                .completedEpisodeFqIds(entity.getCompletedEpisodeFqIds().stream().map(FqId::new).toList())
                .firstStartedAt(java.time.Instant.ofEpochMilli(entity.getFirstStartedAt()))
                .lastUpdatedAt(java.time.Instant.ofEpochMilli(entity.getLastUpdatedAt()))
                .firstCompletedAt(entity.getFirstCompletedAt() != null ? java.time.Instant.ofEpochMilli(entity.getFirstCompletedAt()) : null)
                .completedAt(entity.getCompletedAt() != null ? java.time.Instant.ofEpochMilli(entity.getCompletedAt()) : null)
                .build();
    }
}
