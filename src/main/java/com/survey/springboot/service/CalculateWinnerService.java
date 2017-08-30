package com.survey.springboot.service;

import com.survey.springboot.model.Option;

public interface CalculateWinnerService {
	
	public Option calculateWinner(long postId);

}
