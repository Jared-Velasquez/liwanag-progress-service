package com.liwanag.progress.application;

import com.liwanag.progress.ports.primary.ManageProgress;
import com.liwanag.progress.ports.secondary.ProgressStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class ManageProgressService implements ManageProgress {
    private final ProgressStore progressStore;
}
