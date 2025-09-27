package com.liwanag.progress.adapters.secondary.persistence.mapper;

import com.liwanag.progress.adapters.secondary.persistence.entity.ActivityEntity;
import com.liwanag.progress.adapters.secondary.persistence.entity.EpisodeEntity;
import com.liwanag.progress.adapters.secondary.persistence.entity.UnitEntity;
import com.liwanag.progress.domain.content.Activity;
import com.liwanag.progress.domain.content.Episode;
import com.liwanag.progress.domain.content.FqId;
import com.liwanag.progress.domain.content.Unit;
import org.springframework.stereotype.Component;

@Component
public final class CanonicalMapper {
    // Note: no need to map toEntity since canonical content is read-only
    public Unit toModel(UnitEntity entity) {
        return Unit.builder()
                .fqid(new FqId(entity.getPk()))
                .episodeFqIds(entity.getEpisodeFqIds().stream().map(FqId::new).toList())
                .build();
    }

    public Episode toModel(EpisodeEntity entity) {
        return Episode.builder()
                .fqid(new FqId(entity.getPk()))
                .activityFqIds(entity.getActivityFqIds().stream().map(FqId::new).toList())
                .build();
    }

    public Activity toModel(ActivityEntity entity) {
        return Activity.builder()
                .fqid(new FqId(entity.getPk()))
                .build();
    }
}
