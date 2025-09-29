package com.liwanag.progress.adapters.secondary.persistence.entity;

import lombok.*;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

@DynamoDbBean
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Builder
public class ActivityProgressEntity {
    private String pk;
    private String sk;
    private String userId;
    private String unitId;
    private String episodeId;
    private String activityId;
    private String status;
    private Long firstStartedAt;
    private Long lastUpdatedAt;
    private Long firstCompletedAt;
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
