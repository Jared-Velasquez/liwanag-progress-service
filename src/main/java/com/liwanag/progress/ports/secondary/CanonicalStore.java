package com.liwanag.progress.ports.secondary;

import com.liwanag.progress.domain.content.Activity;
import com.liwanag.progress.domain.content.Episode;
import com.liwanag.progress.domain.content.FqId;
import com.liwanag.progress.domain.content.Unit;

import java.util.Optional;

public interface CanonicalStore {
    /**
     * Load an activity by its fully qualified ID
     * @param fqid Fully qualified activity ID
     * @return Activity object
     */
    Optional<Activity> loadActivity(FqId fqid);

    /**
     * Load an episode by its fully qualified ID
     * @param fqid Fully qualified episode ID
     * @return Episode object
     */
    Optional<Episode> loadEpisode(FqId fqid);

    /**
     * Load a unit by its fully qualified ID
     * @param fqid Fully qualified unit ID
     * @return Unit object
     */
    Optional<Unit> loadUnit(FqId fqid);
}
