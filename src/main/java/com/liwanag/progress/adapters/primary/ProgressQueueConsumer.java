package com.liwanag.progress.adapters.primary;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.liwanag.progress.adapters.primary.event.envelope.AnswerEvaluatedEventPayload;
import com.liwanag.progress.adapters.primary.event.envelope.SessionFinishedEventPayload;
import com.liwanag.progress.adapters.primary.event.eventbridge.EventBridgeEvent;
import com.liwanag.progress.adapters.primary.event.mapper.InboundEventMapper;
import com.liwanag.progress.ports.primary.ManageProgress;
import io.awspring.cloud.sqs.annotation.SqsListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public final class ProgressQueueConsumer {
    private final ObjectMapper objectMapper;
    private final InboundEventMapper mapper;
    private final ManageProgress manageProgress;

    @SqsListener(value = "UserProgressQueue.fifo")
    public void listen(String message) throws JsonProcessingException {
        EventBridgeEvent event = objectMapper.readValue(message, EventBridgeEvent.class);

        switch(event.detail()) {
            case AnswerEvaluatedEventPayload answerEvaluated -> {}
            case SessionFinishedEventPayload sessionFinished -> manageProgress.onSessionFinished(mapper.toModel(sessionFinished));
            default -> log.warn("Received unknown event type: {}", event.detailType());
        }
    }
}