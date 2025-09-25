package com.liwanag.progress.domain.event;

import com.liwanag.progress.domain.answer.Result;
import com.liwanag.progress.domain.progress.FqId;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.Instant;
import java.util.UUID;

/**
 * @param fqid Must be activity FqId
 */
@Builder
public record AnswerEvaluatedEvent(@NotNull UUID userId, @NotNull String questionId, @NotNull FqId fqid,
                                   @NotNull Result result, @NotNull Instant timestamp) implements Event {
}