package com.liwanag.progress.adapters.secondary;

import com.liwanag.progress.domain.event.Event;
import com.liwanag.progress.ports.secondary.EventBus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EventBridgeEventBus implements EventBus {
    @Override
    public void emit(Event event) {

    }
}
