package com.example.user.kafka;

import com.example.user.domain.request.AlbumUpdateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AlbumUpdateProducer {
    private final KafkaTemplate<String, AlbumUpdateRequest> kafkaTemplate;
    public void send(AlbumUpdateRequest albumUpdateRequest) {
        kafkaTemplate.send(TopicConfig.albumUpdate, albumUpdateRequest);
    }

}
