package com.acc.cloud.ms.event.listener;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.SubscribableChannel;

/**
 * Custom Spring Cloud Stream {@link Sink} binding for processing
 * events from the {@link Friend} channel.
 *
 * @author Laxminarayan Rajput
 */
public interface FriendSink {

    String FRIEND_INPUT = "friend-input";

    @Input(FriendSink.FRIEND_INPUT)
    SubscribableChannel friendInput();
}
