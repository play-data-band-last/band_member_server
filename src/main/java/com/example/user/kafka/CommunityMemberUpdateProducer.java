package com.example.user.kafka;

import com.example.user.domain.request.CommunityMemberRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommunityMemberUpdateProducer {
    private final KafkaTemplate<String, CommunityMemberRequest> kafkaTemplate;
    public void send(CommunityMemberRequest communityMemberRequest) {
        kafkaTemplate.send(TopicConfig.communityMemberUpdate, communityMemberRequest);
    }

}
