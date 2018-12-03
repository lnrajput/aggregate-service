package com.acc.cloud.ms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acc.cloud.ms.domain.User;
import com.acc.cloud.ms.repository.AggregateRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class AggregateService {
	
	private final AggregateRepository aggregateRepository;
	
    @Autowired
    public AggregateService(AggregateRepository aggregateRepository) {
        this.aggregateRepository = aggregateRepository;
    }    

    public void removeFriend(Long fromId, Long toId) {
    	log.info("In removeFriend method of Aggregate Service..");
    	aggregateRepository.removeFriend(fromId, toId);
    }

    public void addFriend(Long fromId, Long toId) {
    	log.info("In addFriend method of Aggregate Service..");
    	aggregateRepository.addFriend(fromId, toId);
    }
    
    public void saveUser(User user) {
    	log.info("In saveUser method of Aggregate Service..");
    	aggregateRepository.save(user);
    }

    public List<User> getFriendOfFriend(Long fromId, Long toId){
    	log.info("In friendOfFriend method of Aggregate Service..");
    	return aggregateRepository.friendOfFriend(fromId, toId);
    }

}
