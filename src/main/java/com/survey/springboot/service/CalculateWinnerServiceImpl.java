package com.survey.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.survey.springboot.dao.OptionDAO;
import com.survey.springboot.model.Option;


@Service("calculateWinnerService")
public class CalculateWinnerServiceImpl implements CalculateWinnerService{

	@Autowired
	OptionDAO optionDAO;
	
	@Override
	public Option calculateWinner(long postId) {
		
		return optionDAO.optionWithMaxCount(postId);
	}
	
	

}
