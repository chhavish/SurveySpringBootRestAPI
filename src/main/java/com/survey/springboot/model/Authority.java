package com.survey.springboot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "authorities")
public class Authority {

	
	
	public Authority() {
		super();
	}

	public Authority(long authorityId) {
		super();
		this.authorityId = authorityId;
	}

	@Id
	@GeneratedValue
	@Column(name = "authorityId")
	long authorityId;

	@Column(name = "authority")
	String authority;

	public long getAuthorityId() {
		return authorityId;
	}

	public void setAuthorityId(long authorityId) {
		this.authorityId = authorityId;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

}
