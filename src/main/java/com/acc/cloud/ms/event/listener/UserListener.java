package com.acc.cloud.ms.event.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;

import com.acc.cloud.ms.domain.User;
import com.acc.cloud.ms.event.UserEvent;
import com.acc.cloud.ms.service.AggregateService;

import lombok.extern.slf4j.Slf4j;

/**
 * Message stream listener for {@link User} events. Maps types of events
 * to a graph operation that replicates a connected view of domain data
 * across microservices.
 *
 * @author Laxminarayan Rajput
 */
@Slf4j
@Configuration
@EnableBinding(UserSink.class)
public class UserListener {

    private final AggregateService aggregateService;
    
    @Autowired
    public UserListener(AggregateService aggregateService) {
        this.aggregateService = aggregateService;
    }

    @StreamListener(value = UserSink.USER_INPUT)
    public void apply(Message<UserEvent> userEvent) {

        log.info("Event received: " + userEvent.toString());

        switch (userEvent.getPayload().getEventType()) {
            case USER_CREATED:

                // Saves a new user node
            	aggregateService.saveUser(userEvent.getPayload().getSubject());
                break;
        }
    }
}
