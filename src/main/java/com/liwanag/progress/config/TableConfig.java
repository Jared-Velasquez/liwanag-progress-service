package com.liwanag.progress.config;

import com.liwanag.progress.adapters.secondary.persistence.entity.ActivityEntity;
import com.liwanag.progress.adapters.secondary.persistence.entity.EpisodeEntity;
import com.liwanag.progress.adapters.secondary.persistence.entity.ProgressEntity;
import com.liwanag.progress.adapters.secondary.persistence.entity.UnitEntity;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

@Configuration
public class TableConfig {
    @Bean
    public DynamoDbClient dynamoDbClient() {
        return DynamoDbClient.create();
    }

    @Bean
    public DynamoDbEnhancedClient dynamoDbEnhancedClient(DynamoDbClient dynamoDbClient) {
        return DynamoDbEnhancedClient.builder()
                .dynamoDbClient(dynamoDbClient)
                .build();
    }

    @Bean
    @Qualifier("unitTable")
    public DynamoDbTable<UnitEntity> unitTable(DynamoDbEnhancedClient dynamoDbEnhancedClient) {
        return dynamoDbEnhancedClient.table("ContentTable", TableSchema.fromBean(UnitEntity.class));
    }

    @Bean
    @Qualifier("episodeTable")
    public DynamoDbTable<EpisodeEntity> episodeTable(DynamoDbEnhancedClient dynamoDbEnhancedClient) {
        return dynamoDbEnhancedClient.table("ContentTable", TableSchema.fromBean(EpisodeEntity.class));
    }

    @Bean
    @Qualifier("activityTable")
    public DynamoDbTable<ActivityEntity> activityTable(DynamoDbEnhancedClient dynamoDbEnhancedClient) {
        return dynamoDbEnhancedClient.table("ContentTable", TableSchema.fromBean(ActivityEntity.class));
    }

    @Bean
    @Qualifier("progressTable")
    public DynamoDbTable<ProgressEntity> progressTable(DynamoDbEnhancedClient dynamoDbEnhancedClient) {
        return dynamoDbEnhancedClient.table("ProgressTable", TableSchema.fromBean(ProgressEntity.class));
    }
}
