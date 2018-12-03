package com.acc.cloud.ms.event.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import com.acc.cloud.ms.domain.Friend;
import com.acc.cloud.ms.event.FriendEvent;
import com.acc.cloud.ms.service.AggregateService;

import lombok.extern.slf4j.Slf4j;

/**
 * Message stream listener for {@link Friend} events. Maps types of events
 * to a graph operation that replicates a connected view of domain data
 * across microservices.
 *
 * @author Laxminarayan Rajput
 */
@Slf4j
@Configuration
@EnableBinding(FriendSink.class)
public class FriendListener {

    private final AggregateService aggregateService;
    
    @Autowired
    public FriendListener(AggregateService aggregateService) {
        this.aggregateService = aggregateService;
    }

    @StreamListener(value = FriendSink.FRIEND_INPUT)
    public void apply(Message<FriendEvent> friendEvent) {

        log.info("Event received: " + friendEvent.toString());

        switch (friendEvent.getPayload().getEventType()) {
            case FRIEND_ADDED:
            	aggregateService.addFriend(
                        friendEvent.getPayload().getSubject().getUserId(),
                        friendEvent.getPayload().getSubject().getFriendId());
                break;
            case FRIEND_REMOVED:
            	aggregateService.removeFriend(
                        friendEvent.getPayload().getSubject().getUserId(),
                        friendEvent.getPayload().getSubject().getFriendId());
                break;
        }
    }
}
