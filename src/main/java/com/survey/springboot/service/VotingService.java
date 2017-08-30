package com.survey.springboot.service;

import com.survey.springboot.model.Option;

public interface VotingService {
	
	public Option vote(long postId, long optionId);

}
