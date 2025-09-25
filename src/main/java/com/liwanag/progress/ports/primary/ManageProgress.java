package com.liwanag.progress.ports.primary;

import com.liwanag.progress.domain.event.ActivityCompletedEvent;
import com.liwanag.progress.domain.event.AnswerEvaluatedEvent;

public interface ManageProgress {
    void onAnswerEvaluated(AnswerEvaluatedEvent event);
    void onActivityCompleted(ActivityCompletedEvent event);
}
