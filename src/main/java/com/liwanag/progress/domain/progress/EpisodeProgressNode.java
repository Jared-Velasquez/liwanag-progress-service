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
public final class EpisodeProgressNode {
    private FqId episodeFqId;
    private int completedActivities;
    private int totalActivities;

    private ProgressStatus status;
    private List<ActivityProgressNode> activities;
}
