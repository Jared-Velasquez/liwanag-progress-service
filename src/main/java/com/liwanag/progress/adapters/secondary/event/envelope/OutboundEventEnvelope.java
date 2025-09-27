package com.liwanag.progress.adapters.secondary.event.envelope;

public sealed interface OutboundEventEnvelope permits ActivityCompletedEventPayload, EpisodeCompletedEventPayload, UnitCompletedEventPayload {
}
