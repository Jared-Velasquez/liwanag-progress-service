package com.liwanag.progress.domain.event;

import com.liwanag.progress.domain.content.FqId;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.util.UUID;

/**
 * @param fqid Must be episode FqId
 */
public record EpisodeCompletedEvent(@NotNull UUID userId, @NotNull FqId fqid,
                                    @NotNull Instant timestamp) implements Event {

}