package com.acc.cloud.ms.event.listener;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.SubscribableChannel;

/**
 * Custom Spring Cloud Stream {@link Sink} binding for processing
 * events from the {@link User} channel.
 *
 * @author Laxminarayan Rajput
 */
public interface UserSink {
    String USER_INPUT = "user-input";

    @Input(UserSink.USER_INPUT)
    SubscribableChannel userInput();
}
