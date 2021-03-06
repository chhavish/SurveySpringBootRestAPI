package com.survey.springboot.service;

//import java.util.List;
import java.util.Set;

import com.survey.springboot.model.Option;
import com.survey.springboot.model.Post;

public interface PostService {

	public void createPost(Post post);

	public Set<Post> getAllPosts();

	public Post getPostByPostId(long postId);
	
	Set<Option> getOptionsByPostId(int postId);
}
