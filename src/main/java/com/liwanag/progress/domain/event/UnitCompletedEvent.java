package com.liwanag.progress.domain.event;

import com.liwanag.progress.domain.progress.FqId;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.util.UUID;

/**
 * @param fqid Must be unit FqId
 */
public record UnitCompletedEvent(@NotNull UUID userId, @NotNull FqId fqid,
                                 @NotNull Instant timestamp) implements Event {

}