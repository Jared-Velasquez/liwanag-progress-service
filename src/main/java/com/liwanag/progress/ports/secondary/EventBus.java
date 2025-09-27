package com.liwanag.progress.ports.secondary;

import com.liwanag.progress.domain.event.Event;

public interface EventBus {
    /**
     * Emit an event to the event bus.
     * @param event
     */
    void emit(Event event);
}
