package com.survey.springboot.model;

import java.math.BigInteger;

public class Winner {

	private BigInteger optionId;
	private int count;
	
	public Winner(BigInteger optionId, int count){
		this.optionId = optionId;
		this.count = count;
	}

	public BigInteger getOptionId() {
		return optionId;
	}

	public void setOptionId(BigInteger optionId) {
		this.optionId = optionId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
	
}
