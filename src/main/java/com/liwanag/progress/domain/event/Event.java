package com.liwanag.progress.domain.event;

public sealed interface Event permits AnswerEvaluatedEvent, SessionFinishedEvent, ActivityCompletedEvent, EpisodeCompletedEvent, UnitCompletedEvent {
}
