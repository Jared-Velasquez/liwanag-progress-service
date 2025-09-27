package com.liwanag.progress.domain.content;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class Episode {
    private final FqId fqid;
    private final List<FqId> activityFqIds;
}
