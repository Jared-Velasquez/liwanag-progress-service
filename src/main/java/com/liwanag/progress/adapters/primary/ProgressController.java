package com.liwanag.progress.adapters.primary;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/progress")
@RequiredArgsConstructor
public final class ProgressController {
    @GetMapping("/")
    public ResponseEntity<?> getProgress() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/units")
    public ResponseEntity<?> getUnitsProgress() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/units/{unitId}/episodes")
    public ResponseEntity<?> getEpisodesProgress() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/units/{unitId}/episodes/{episodeId}/activities")
    public ResponseEntity<?> getActivitiesProgress() {
        return ResponseEntity.ok().build();
    }
}
