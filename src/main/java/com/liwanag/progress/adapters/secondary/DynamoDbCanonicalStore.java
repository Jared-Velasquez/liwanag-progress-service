package com.liwanag.progress.adapters.secondary;

import com.liwanag.progress.adapters.secondary.persistence.entity.ActivityEntity;
import com.liwanag.progress.adapters.secondary.persistence.entity.EpisodeEntity;
import com.liwanag.progress.adapters.secondary.persistence.entity.UnitEntity;
import com.liwanag.progress.adapters.secondary.persistence.mapper.CanonicalMapper;
import com.liwanag.progress.domain.content.Activity;
import com.liwanag.progress.domain.content.Episode;
import com.liwanag.progress.domain.content.FqId;
import com.liwanag.progress.domain.content.Unit;
import com.liwanag.progress.ports.secondary.CanonicalStore;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;

import java.util.Optional;

import static com.liwanag.progress.adapters.secondary.persistence.entity.ContentKeys.*;

@Slf4j
public final class DynamoDbCanonicalStore implements CanonicalStore {
    private final DynamoDbTable<UnitEntity> unitTable;
    private final DynamoDbTable<EpisodeEntity> episodeTable;
    private final DynamoDbTable<ActivityEntity> activityTable;
    private final CanonicalMapper mapper;

    public DynamoDbCanonicalStore(
            @Qualifier("unitTable") DynamoDbTable<UnitEntity> unitTable,
            @Qualifier("episodeTable") DynamoDbTable<EpisodeEntity> episodeTable,
            @Qualifier("activityTable") DynamoDbTable<ActivityEntity> activityTable,
            CanonicalMapper mapper
    ) {
        this.unitTable = unitTable;
        this.episodeTable = episodeTable;
        this.activityTable = activityTable;
        this.mapper = mapper;
    }

    @Override
    public Optional<Activity> loadActivity(FqId fqid) {
        if (!fqid.isActivityFqId())
            throw new IllegalArgumentException("FqId is not an activity FqId: " + fqid);

        ActivityEntity entity = activityTable.getItem(
                Key.builder().partitionValue(activityPk(fqid)).sortValue(liveSk()).build()
        );

        if (entity == null) {
            log.error("Activity not found for FqId: {}", fqid.toString());
            return Optional.empty();
        }

        return Optional.of(mapper.toModel(entity));
    }

    @Override
    public Optional<Episode> loadEpisode(FqId fqid) {
        if (!fqid.isEpisodeFqId())
            throw new IllegalArgumentException("FqId is not an episode FqId: " + fqid);

        EpisodeEntity entity = episodeTable.getItem(
                Key.builder().partitionValue(episodePk(fqid)).sortValue(liveSk()).build()
        );

        if (entity == null) {
            log.error("Episode not found for FqId: {}", fqid.toString());
            return Optional.empty();
        }

        return Optional.of(mapper.toModel(entity));
    }

    @Override
    public Optional<Unit> loadUnit(FqId fqid) {
        if (!fqid.isUnitFqId())
            throw new IllegalArgumentException("FqId is not an episode FqId: " + fqid);

        UnitEntity entity = unitTable.getItem(
                Key.builder().partitionValue(unitPk(fqid)).sortValue(liveSk()).build()
        );

        if (entity == null) {
            log.error("Unit not found for FqId: {}", fqid.toString());
            return Optional.empty();
        }

        return Optional.of(mapper.toModel(entity));
    }
}
