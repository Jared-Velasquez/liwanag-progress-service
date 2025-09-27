package com.liwanag.progress.adapters.primary.event.mapper;

import com.liwanag.progress.adapters.primary.event.envelope.SessionFinishedEventPayload;
import com.liwanag.progress.adapters.secondary.event.envelope.ActivityCompletedEventPayload;
import com.liwanag.progress.domain.event.ActivityCompletedEvent;
import com.liwanag.progress.domain.event.SessionFinishedEvent;
import com.liwanag.progress.domain.progress.FqId;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class InboundEventMapper {
    public ActivityCompletedEvent toModel(ActivityCompletedEventPayload payload) {
        return null;
    }

    public SessionFinishedEvent toModel(SessionFinishedEventPayload payload) {
        return SessionFinishedEvent.builder()
                .userId(UUID.fromString(payload.userId()))
                .sessionId(UUID.fromString(payload.sessionId()))
                .fqid(new FqId(payload.unitId(), payload.episodeId(), payload.activityId()))
                .timestamp(payload.timestamp())
                .build();
    }
}
