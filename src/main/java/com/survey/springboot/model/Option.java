package com.survey.springboot.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "`Option`")
public class Option implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "optionId")
	private long optionId;
	
	@Column(name = "count")
	private int count;
	
//	@Column(name = "description")
//	private String description;

	public Option() {

	}

	public Option(long optionId) {
		super();
		this.optionId = optionId;
//		this.description = descrition;
	}
	
//	@ManyToOne
//	// (fetch = FetchType.EAGER)
//	// @JoinColumn(name="id")
//	private Users user;
//
	@ManyToOne
//	// (fetch = FetchType.EAGER)
//	 @JoinColumn(name="postId")
	private Post post;

//
//	public Users getUserId() {
//		return user;
//	}
//
//	public void setUserId(Users user) {
//		this.user = user;
//	}
//
//	public Post getPostId() {
//		return post;
//	}
//
//	public void setPostId(Post post) {
//		this.post = post;
//	}

	
	public int getCount() {
		return count;
	}

	public long getOptionId() {
		return optionId;
	}

	public void setOptionId(long optionId) {
		this.optionId = optionId;
	}

	public void setCount(int count) {
		this.count = count;
	}

//	public String getDescription() {
//		return description;
//	}
//
//	public void setDescription(String description) {
//		this.description = description;
//	}

	
}
