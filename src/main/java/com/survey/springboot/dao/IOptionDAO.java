package com.survey.springboot.dao;

import com.survey.springboot.model.Option;
import com.survey.springboot.model.Post;

public interface IOptionDAO {
	
	public Option getOptionById(long optionId);
	public void voteForOption(long postId, long optionId);
	public Option optionWithMaxCount(long postId);

}
