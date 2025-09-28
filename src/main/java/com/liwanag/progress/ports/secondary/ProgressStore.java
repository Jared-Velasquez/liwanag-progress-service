package com.liwanag.progress.ports.secondary;

import com.liwanag.progress.domain.content.FqId;
import com.liwanag.progress.domain.progress.ActivityProgress;
import com.liwanag.progress.domain.progress.EpisodeProgress;
import com.liwanag.progress.domain.progress.Progress;
import com.liwanag.progress.domain.progress.UnitProgress;

import java.util.Optional;
import java.util.UUID;

public interface ProgressStore {
    void save(Progress progress);
    Optional<ActivityProgress> loadActivity(UUID userId, FqId fqid);
    Optional<EpisodeProgress> loadEpisode(UUID userId, FqId fqid);
    Optional<UnitProgress> loadUnit(UUID userId, FqId fqid);
}
