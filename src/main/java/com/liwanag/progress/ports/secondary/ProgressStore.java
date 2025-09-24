package com.liwanag.progress.ports.secondary;

import com.liwanag.progress.domain.progress.FqId;
import com.liwanag.progress.domain.progress.Progress;

import java.util.Optional;
import java.util.UUID;

public interface ProgressStore {
    void save(Progress progress);
    Optional<Progress> load(UUID userId, FqId fqid);
}
