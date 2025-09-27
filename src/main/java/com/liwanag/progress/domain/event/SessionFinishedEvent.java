package com.liwanag.progress.domain.event;

import com.liwanag.progress.domain.progress.FqId;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.Instant;
import java.util.UUID;

@Builder
public record SessionFinishedEvent(@NotNull UUID userId, @NotNull UUID sessionId,
                                   @NotNull FqId fqid,
                                   @NotNull Instant timestamp) implements Event {
}
