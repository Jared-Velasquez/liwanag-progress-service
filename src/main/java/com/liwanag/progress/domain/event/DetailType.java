package com.liwanag.progress.domain.event;

//public enum DetailType {
//    ANSWER_EVALUATED("AnswerEvaluated"),
//    SESSION_FINISHED("SessionFinished"),
//    ACTIVITY_COMPLETED("ActivityCompleted"),
//    EPISODE_COMPLETED("EpisodeCompleted"),
//    UNIT_COMPLETED("UnitCompleted");
//
//    private final String source;
//
//    DetailType(String s) {
//        this.source = s;
//    }
//
//    @Override
//    public String toString() {
//        return this.source;
//    }
//}

public enum DetailType {
    UnitCompleted,
    EpisodeCompleted,
    ActivityCompleted,
    SessionFinished,
    AnswerEvaluated;
}
