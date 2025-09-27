package com.liwanag.progress.domain.event;

import com.liwanag.progress.domain.content.FqId;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.Instant;
import java.util.UUID;

/**
 * @param fqid Must be activity FqId
 */
@Builder
public record ActivityCompletedEvent(@NotNull UUID userId, @NotNull FqId fqid,
                                     @NotNull Boolean isFirstCompletion, @NotNull Instant timestamp) implements Event {

}