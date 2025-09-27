package com.liwanag.progress.adapters.primary.event.eventbridge;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.liwanag.progress.adapters.primary.event.envelope.AnswerEvaluatedEventPayload;
import com.liwanag.progress.adapters.primary.event.envelope.InboundEventEnvelope;
import com.liwanag.progress.adapters.primary.event.envelope.SessionFinishedEventPayload;
import com.liwanag.progress.domain.event.DetailType;

import java.time.Instant;

public record EventBridgeEvent(
        String version,
        String id,
        @JsonProperty("detail-type")
        DetailType detailType,
        String source,
        Instant time,
        @JsonTypeInfo(
                use = JsonTypeInfo.Id.NAME,
                include = JsonTypeInfo.As.EXTERNAL_PROPERTY,
                property = "detail-type"
        )
        @JsonSubTypes({
                @JsonSubTypes.Type(value = AnswerEvaluatedEventPayload.class, name = "AnswerEvaluated"),
                @JsonSubTypes.Type(value = SessionFinishedEventPayload.class, name = "SessionFinished")
        })
        InboundEventEnvelope detail
) {
}
