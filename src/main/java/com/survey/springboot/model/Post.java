package com.survey.springboot.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Post")
public class Post implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="postId")
	private long postId;
	
	@Column(name="username")
	private String username;
	
	@Column(name="postedOn")
	private Date postedOn;
	
	@Column(name="endDate")
	private Date endDate;
	
	@OneToMany(cascade = CascadeType.ALL)
//	(mappedBy = "`Option`", cascade = CascadeType.ALL, fetch = FetchType.EAGER )
	private List<Option> options;
	
	protected Post(){
		
	}
//	String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());

	public Post(long postId, String username, Date postedOn, Date endDate){
		super();
		this.postId = postId;
		this.username = username;
		
		this.postedOn = Calendar.getInstance().getTime();
		this.endDate = endDate;
	}
	
	
	public long getPostId() {
		return postId;
	}
	public void setPostId(long postId) {
		this.postId = postId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getPostedOn() {
		return postedOn;
	}
	public void setPostedOn() {
		this.postedOn = Calendar.getInstance().getTime();
	}
	public Date getEndDate() {
//		Date date = new Date(this.endDate);
//	    SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
////	    return formatDate.
//	    String strDate = new SimpleDateFormat("yyyy-MM-dd").format(this.endDate);
//		try {
//			this.endDate =  formatDate.parse(strDate);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
		return this.endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public List<Option> getOptions() {
		return options;
	}
	public void setOptions(List<Option> options) {
		this.options = options;
	}

}
