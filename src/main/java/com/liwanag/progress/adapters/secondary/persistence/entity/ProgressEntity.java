package com.liwanag.progress.adapters.secondary.persistence.entity;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

import java.time.Instant;

@DynamoDbBean
@RequiredArgsConstructor
@Getter
@Setter
@Builder
public final class ProgressEntity {
    @NotNull
    private String pk;
    @NotNull
    private String sk;
    @NotNull
    private String userId;
    @NotNull
    private String status;
    private String unitId;
    private String episodeId;
    private String activityId;
    @NotNull
    private Integer totalCount;
    @NotNull
    private Integer completedCount;
    @NotNull
    private Long firstStartedAt;
    @NotNull
    private Long lastUpdatedAt;
    private Long completedAt;

    @DynamoDbPartitionKey
    @DynamoDbAttribute("PK")
    public String getPk() {
        return pk;
    }

    @DynamoDbSortKey
    @DynamoDbAttribute("SK")
    public String getSk() {
        return sk;
    }
}
