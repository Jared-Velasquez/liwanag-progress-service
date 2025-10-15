package com.liwanag.progress.domain.progress;

import com.liwanag.progress.domain.content.FqId;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@EqualsAndHashCode
public final class ActivityProgressNode {
    private FqId activityFqId;
    private ProgressStatus status;
}
