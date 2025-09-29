package com.liwanag.progress.adapters.secondary.persistence.mapper;

import com.liwanag.progress.adapters.secondary.persistence.entity.ActivityEntity;
import com.liwanag.progress.adapters.secondary.persistence.entity.EpisodeEntity;
import com.liwanag.progress.adapters.secondary.persistence.entity.UnitEntity;
import com.liwanag.progress.domain.content.Activity;
import com.liwanag.progress.domain.content.Episode;
import com.liwanag.progress.domain.content.FqId;
import com.liwanag.progress.domain.content.Unit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public final class CanonicalMapper {
    // Note: no need to map toEntity since canonical content is read-only
    public Unit toModel(UnitEntity entity) {
        log.info("Mapping UnitEntity FqId to unit: {}", entity.getPk());
        return Unit.builder()
                .fqid(new FqId(entity.getPk()))
                .episodeFqIds(entity.getEpisodeFqIds().stream().map(FqId::new).toList())
                .build();
    }

    public Episode toModel(EpisodeEntity entity) {
        log.info("Mapping EpisodeEntity FqId to episode: {}", entity.getPk());
        return Episode.builder()
                .fqid(new FqId(entity.getPk()))
                .activityFqIds(entity.getActivityFqIds().stream().map(FqId::new).toList())
                .build();
    }

    public Activity toModel(ActivityEntity entity) {
        log.info("Mapping ActivityEntity FqId to activity: {}", entity.getPk());
        return Activity.builder()
                .fqid(new FqId(entity.getPk()))
                .build();
    }
}
