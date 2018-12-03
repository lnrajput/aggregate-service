package com.acc.cloud.ms.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.acc.cloud.ms.domain.User;
import com.acc.cloud.ms.service.AggregateService;

@RestController
@RequestMapping("/v1/graph")
public class AggregateResource {

    private final AggregateService aggregateService;
    
    @Autowired
    public AggregateResource(AggregateService aggregateService) {
        this.aggregateService = aggregateService;
    }    

    @GetMapping("mutualFriends")
    List<User> getFriendOfFriend(@RequestParam("userId") Long userId,
                                 @RequestParam("friendId") Long friendId) {
        return aggregateService.getFriendOfFriend(userId, friendId);
    }
}
