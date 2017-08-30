package com.survey.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.survey.springboot.dao.IOptionDAO;
import com.survey.springboot.dao.IPostDAO;
import com.survey.springboot.dao.IUserDAO;
import com.survey.springboot.dao.OptionDAO;
import com.survey.springboot.model.Option;

@Service
public class VotingServiceImpl implements VotingService {

	@Autowired
	private IPostDAO postDAO;
	
	@Autowired
	private IOptionDAO optionDAO;
	
	@Override
	public Option vote(long postId, long optionId) {
//		Post post = postDAO.getPostByPostId(postId);
		optionDAO.voteForOption(postId, optionId);
		Option option = optionDAO.getOptionById(optionId);
//		option.setCount(option.getCount()+1);
		
		return option;
		
	}

}
