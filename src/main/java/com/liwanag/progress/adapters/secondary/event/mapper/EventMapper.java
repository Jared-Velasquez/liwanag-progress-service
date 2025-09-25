package com.liwanag.progress.adapters.secondary.event.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liwanag.progress.domain.event.Event;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class EventMapper {
    private final ObjectMapper objectMapper;

    public Event fromJson(String json) {
        return null;
    }
}
