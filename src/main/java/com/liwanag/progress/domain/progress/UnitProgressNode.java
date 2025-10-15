package com.liwanag.progress.domain.progress;

import com.liwanag.progress.domain.content.FqId;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@EqualsAndHashCode
public final class UnitProgressNode {
    private FqId unitFqId;
    private int completedEpisodes;
    private int totalEpisodes;
    private int completedActivities;
    private int totalActivities;

    private ProgressStatus status;

    private List<EpisodeProgressNode> episodes;
}
