package com.example.user.kafka;

import com.example.user.domain.request.CommunityMemberRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberDeleteProducer {
    private final KafkaTemplate<String, Long> kafkaTemplate;
    public void send(Long memberId) {
        kafkaTemplate.send(TopicConfig.memberDelete, memberId);
    }

}
