package com.liwanag.progress.config;

import com.liwanag.progress.application.ManageProgressService;
import com.liwanag.progress.ports.primary.ManageProgress;
import com.liwanag.progress.ports.secondary.CanonicalStore;
import com.liwanag.progress.ports.secondary.EventBus;
import com.liwanag.progress.ports.secondary.ProgressStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProgressConfig {
    @Bean
    public ManageProgress manageProgress(
            ProgressStore progressStore,
            CanonicalStore canonicalStore,
            EventBus eventBus
    ) {
        return new ManageProgressService(
               progressStore,
                canonicalStore,
                eventBus
        );
    }
}
