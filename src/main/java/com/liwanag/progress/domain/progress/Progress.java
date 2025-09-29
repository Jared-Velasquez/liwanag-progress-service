package com.liwanag.progress.domain.progress;

public sealed interface Progress permits UnitProgress, EpisodeProgress, ActivityProgress {
    Boolean isCompleted();
}
