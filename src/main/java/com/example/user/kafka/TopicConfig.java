package com.example.user.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.converter.JsonMessageConverter;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.backoff.FixedBackOff;

@Component
public class TopicConfig {
    public final static String albumUpdate = "albumUpdate";
    public final static String boardUpdate = "boardUpdate";
    public final static String communityMemberUpdate = "communityMemberUpdate";
    public final static String scheduleUpdate = "scheduleUpdate";
    public final static String chattingUpdate = "chattingUpdate";

    public final static String commentUpdate = "commentUpdate";

    @Bean
    public NewTopic albumTopic() {
        return new NewTopic(albumUpdate, 1, (short)1);
    }

    @Bean
    public NewTopic boardTopic() {
        return new NewTopic(boardUpdate, 1, (short)1);
    }

    @Bean
    public NewTopic communityMemberTopic() {
        return new NewTopic(communityMemberUpdate, 1, (short)1);
    }

    @Bean
    public NewTopic scheduleTopic() {
        return new NewTopic(scheduleUpdate, 1, (short)1);
    }

    @Bean
    public NewTopic chattingTopic() {
        return new NewTopic(chattingUpdate, 1, (short)1);
    }

    @Bean
    public NewTopic commentTopic() {
        return new NewTopic(commentUpdate, 1, (short)1);
    }

    @Bean
    public RecordMessageConverter converter() {
        return new JsonMessageConverter();
    }

    @Bean
    public CommonErrorHandler errorHandler(KafkaOperations<Object, Object> kafkaOperations) {
        return new DefaultErrorHandler(
                new DeadLetterPublishingRecoverer(kafkaOperations),
                new FixedBackOff(1000L, 2)
        );
    }
}
