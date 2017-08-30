package com.survey.springboot.dao;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.survey.springboot.model.Option;
import com.survey.springboot.model.Post;

@Transactional
@Repository
public class PostDAO implements IPostDAO{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void createPost(Post post) {
		entityManager.persist(post);
	}

	@Override
	public Set<Option> getOptionsByPostId(int postId) {
		return (Set<Option>) entityManager.find(Option.class, postId);
//		return null;
	}

	@Override
	public Set<Post> getAllPosts() {
		String hql = "FROM Post as post ORDER BY post.id";
		return (Set<Post>) entityManager.createQuery(hql).getResultList();
//		return null;
	}

	@Override
	public Post getPostByPostId(long postId) {
		return entityManager.find(Post.class, postId);
	}

}
