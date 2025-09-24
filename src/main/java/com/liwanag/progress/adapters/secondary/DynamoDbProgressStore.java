package com.liwanag.progress.adapters.secondary;

import com.liwanag.progress.adapters.secondary.persistence.entity.ProgressEntity;
import com.liwanag.progress.adapters.secondary.persistence.entity.ProgressKeys;
import com.liwanag.progress.adapters.secondary.persistence.mapper.ProgressMapper;
import com.liwanag.progress.domain.progress.FqId;
import com.liwanag.progress.domain.progress.Progress;
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
    private final DynamoDbTable<ProgressEntity> progressTable;
    private final ProgressMapper progressMapper;

    public DynamoDbProgressStore(
            @Qualifier("progressTable") DynamoDbTable<ProgressEntity> progressTable,
            ProgressMapper progressMapper
    ) {
        this.progressTable = progressTable;
        this.progressMapper = progressMapper;
    }

    @Override
    public void save(Progress progress) {
        progressTable.putItem(progressMapper.toEntity(progress));
    }

    @Override
    public Optional<Progress> load(UUID userId, FqId fqid) {
        ProgressEntity entity = progressTable.getItem(r -> r.key(k -> k
                .partitionValue(ProgressKeys.progressPk(userId))
                .sortValue(ProgressKeys.progressSk(fqid))
        ));

        if (entity == null) {
            log.error("Progress not found for userId: {} and FqId: {}", userId, fqid);
            return Optional.empty();
        }

        return Optional.of(progressMapper.toModel(entity));
    }
}
