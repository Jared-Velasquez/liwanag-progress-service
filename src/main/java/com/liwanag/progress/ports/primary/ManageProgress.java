package com.liwanag.progress.ports.primary;

import com.liwanag.progress.domain.event.SessionFinishedEvent;

public interface ManageProgress {
    void onSessionFinished(SessionFinishedEvent event);
}
