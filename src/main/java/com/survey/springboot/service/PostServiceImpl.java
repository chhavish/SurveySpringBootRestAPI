package com.survey.springboot.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.survey.springboot.dao.IPostDAO;
import com.survey.springboot.model.Option;
import com.survey.springboot.model.Post;

@Service("postService")
public class PostServiceImpl implements PostService {

	@Autowired
	IPostDAO postDAO;

	@Override
	public void createPost(Post post) {
		postDAO.createPost(post);
	}

	@Override
	public Set<Post> getAllPosts() {
		return postDAO.getAllPosts();
	}

	@Override
	public Set<Option> getOptionsByPostId(int postId) {
		return postDAO.getOptionsByPostId(postId);
	}

	@Override
	public Post getPostByPostId(long postId) {
		return postDAO.getPostByPostId(postId);
	}

}
