package com.liwanag.progress.adapters.secondary.event.envelope;

public sealed interface EventEnvelope permits ActivityCompletedEventPayload, EpisodeCompletedEventPayload, UnitCompletedEventPayload {
}
