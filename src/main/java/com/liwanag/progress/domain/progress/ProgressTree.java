package com.liwanag.progress.domain.progress;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
public final class ProgressTree {
    private int completedUnits;
    private int totalUnits;
    private int completedEpisodes;
    private int totalEpisodes;
    private int completedActivities;
    private int totalActivities;

    private ProgressStatus status;

    private Set<UnitProgressNode> units;
}
