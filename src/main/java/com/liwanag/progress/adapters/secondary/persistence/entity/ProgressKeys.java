package com.liwanag.progress.adapters.secondary.persistence.entity;

import com.liwanag.progress.domain.progress.FqId;

import java.util.UUID;

public final class ProgressKeys {
    public static String progressPk(UUID userId) {
        return "USER#" + userId;
    }

    public static String progressSk(FqId fqid) {
        if (fqid.isActivityFqId()) {
            return String.format("PROGRESS#ACTIVITY#%s#%s#%s", fqid.getUnitId(), fqid.getEpisodeId(), fqid.getActivityId());
        } else if (fqid.isEpisodeFqId()) {
            return String.format("PROGRESS#EPISODE#%s#%s", fqid.getUnitId(), fqid.getEpisodeId());
        } else if (fqid.isUnitFqId()) {
            return String.format("PROGRESS#UNIT#%s", fqid.getUnitId());
        } else {
            throw new IllegalArgumentException("FqId is not a valid FqId");
        }
    }

    public static String extractUserId(String pk) {
        if (pk == null || !pk.startsWith("USER#")) {
            throw new IllegalArgumentException("Invalid PK format");
        }
        return pk.substring(5);
    }

    public static FqId extractFqId(String sk) {
        if (sk == null || !sk.startsWith("PROGRESS#")) {
            throw new IllegalArgumentException("Invalid SK format");
        }
        String[] parts = sk.split("#");
        if (parts.length < 3) {
            throw new IllegalArgumentException("Invalid SK format");
        }
        return switch (parts[1]) {
            case "UNIT" -> new FqId(parts[2]);
            case "EPISODE" -> {
                if (parts.length != 4) throw new IllegalArgumentException("Invalid EPISODE SK format");
                yield new FqId(parts[2], parts[3], null);
            }
            case "ACTIVITY" -> {
                if (parts.length != 5) throw new IllegalArgumentException("Invalid ACTIVITY SK format");
                yield new FqId(parts[2], parts[3], parts[4]);
            }
            default -> throw new IllegalArgumentException("Invalid SK prefix");
        };
    }
}
