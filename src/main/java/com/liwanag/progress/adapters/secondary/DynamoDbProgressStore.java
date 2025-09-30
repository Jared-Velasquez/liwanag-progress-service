package com.liwanag.progress.adapters.secondary;

import com.liwanag.progress.adapters.secondary.persistence.entity.*;
import com.liwanag.progress.adapters.secondary.persistence.mapper.ProgressMapper;
import com.liwanag.progress.domain.content.FqId;
import com.liwanag.progress.domain.progress.ActivityProgress;
import com.liwanag.progress.domain.progress.EpisodeProgress;
import com.liwanag.progress.domain.progress.Progress;
import com.liwanag.progress.domain.progress.UnitProgress;
import com.liwanag.progress.ports.secondary.ProgressStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;

import java.util.Optional;
import java.util.UUID;

@Component
@Slf4j
public class DynamoDbProgressStore implements ProgressStore {
    private final DynamoDbTable<UnitProgressEntity> unitProgressTable;
    private final DynamoDbTable<EpisodeProgressEntity> episodeProgressTable;
    private final DynamoDbTable<ActivityProgressEntity> activityProgressTable;
    private final ProgressMapper progressMapper;

    public DynamoDbProgressStore(
            @Qualifier("unitProgressTable") DynamoDbTable<UnitProgressEntity> unitProgressTable,
            @Qualifier("episodeProgressTable") DynamoDbTable<EpisodeProgressEntity> episodeProgressTable,
            @Qualifier("activityProgressTable") DynamoDbTable<ActivityProgressEntity> activityProgressTable,
            ProgressMapper progressMapper
    ) {
        this.unitProgressTable = unitProgressTable;
        this.episodeProgressTable = episodeProgressTable;
        this.activityProgressTable = activityProgressTable;
        this.progressMapper = progressMapper;
    }

    @Override
    public void save(Progress progress) {
        switch (progress) {
            case ActivityProgress ap -> activityProgressTable.putItem(progressMapper.toEntity(ap));
            case EpisodeProgress ep -> episodeProgressTable.putItem(progressMapper.toEntity(ep));
            case UnitProgress up -> unitProgressTable.putItem(progressMapper.toEntity(up));
        }
    }

    @Override
    public Optional<ActivityProgress> loadActivity(UUID userId, FqId fqid) {
        if (!fqid.isActivityFqId())
            throw new IllegalArgumentException("FqId is not an activity FqId: " + fqid);

        ActivityProgressEntity entity = activityProgressTable.getItem(r -> r.key(k -> k
                .partitionValue(ProgressKeys.progressPk(userId))
                .sortValue(ProgressKeys.progressSk(fqid))
        ));

        if (entity == null) {
            log.error("ActivityProgress not found for userId: {} and FqId: {}", userId, fqid);
            return Optional.empty();
        }

        return Optional.of(progressMapper.toModel(entity));
    }

    @Override
    public Optional<EpisodeProgress> loadEpisode(UUID userId, FqId fqid) {
        if (!fqid.isEpisodeFqId())
            throw new IllegalArgumentException("FqId is not an episode FqId: " + fqid);

        FqId episodeId = fqid.toEpisodeFqId();
        EpisodeProgressEntity entity = episodeProgressTable.getItem(r -> r.key(k -> k
                .partitionValue(ProgressKeys.progressPk(userId))
                .sortValue(ProgressKeys.progressSk(episodeId))
        ));

        if (entity == null) {
            log.error("EpisodeProgress not found for userId: {} and FqId: {}", userId, episodeId);
            return Optional.empty();
        }

        return Optional.of(progressMapper.toModel(entity));
    }

    @Override
    public Optional<UnitProgress> loadUnit(UUID userId, FqId fqid) {
        if (!fqid.isUnitFqId())
            throw new IllegalArgumentException("FqId is not a unit FqId: " + fqid);

        FqId unitId = fqid.toUnitFqId();
        UnitProgressEntity entity = unitProgressTable.getItem(r -> r.key(k -> k
                .partitionValue(ProgressKeys.progressPk(userId))
                .sortValue(ProgressKeys.progressSk(unitId))
        ));

        if (entity == null) {
            log.error("UnitProgress not found for userId: {} and FqId: {}", userId, unitId);
            return Optional.empty();
        }

        return Optional.of(progressMapper.toModel(entity));
    }
}
