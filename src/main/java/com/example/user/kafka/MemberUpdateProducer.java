package com.example.user.kafka;

import com.example.user.domain.request.CommunityMemberRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberUpdateProducer {
    private final KafkaTemplate<String, CommunityMemberRequest> kafkaTemplate;
    public void send(CommunityMemberRequest communityMemberRequest) {
        kafkaTemplate.send(TopicConfig.memberUpdate, communityMemberRequest);
    }

}
