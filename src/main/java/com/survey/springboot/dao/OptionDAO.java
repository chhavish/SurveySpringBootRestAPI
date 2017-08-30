package com.survey.springboot.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.survey.springboot.model.Option;
import com.survey.springboot.model.Winner;

@Transactional
@Repository
public class OptionDAO implements IOptionDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void voteForOption(long postId, long optionId) {

		Option option = getOptionById(optionId);
		option.setCount(option.getCount() + 1);
		entityManager.flush();

	}

	@Override
	public Option getOptionById(long optionId) {
		return entityManager.find(Option.class, optionId);
	}

	@Override
	public Option optionWithMaxCount(long postId) {

		String hql = "Select * from (Select po.options_option_id,  max(count) from UserDB.`option` o join UserDB.post_options po on po.options_option_id = o.option_id  where po.post_post_id = :postId group by po.options_option_id)  g LIMIT 1;";
		List<Object[]> results = entityManager.createNativeQuery(hql).setParameter("postId", postId).getResultList();
		List<Winner> finalResult = new ArrayList<>(results.size());
		for (Object[] result : results) {
			finalResult.add(new Winner((BigInteger) result[0], (int) result[1]));
		}
		return (Option) entityManager.find(Option.class, finalResult.get(0).getOptionId().longValue());
	}

}
