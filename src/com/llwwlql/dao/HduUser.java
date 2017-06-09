package com.llwwlql.dao;

/**
 * Hduuser entity. @author MyEclipse Persistence Tools
 */

public class HduUser implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7295167906684377440L;
	private Integer id;
	private String hduUserName;
	private Integer hduSolve;
	private Integer hduSubmission;
	private String hduNickName;
	private short hduType;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getHduUserName() {
		return this.hduUserName;
	}

	public void setHduUserName(String hduUserName) {
		this.hduUserName = hduUserName;
	}

	public Integer getHduSolve() {
		return this.hduSolve;
	}

	public void setHduSolve(Integer hduSolve) {
		this.hduSolve = hduSolve;
	}

	public Integer getHduSubmission() {
		return this.hduSubmission;
	}

	public void setHduSubmission(Integer hduSubmission) {
		this.hduSubmission = hduSubmission;
	}

	public String getHduNickName() {
		return this.hduNickName;
	}

	public void setHduNickName(String hduNickName) {
		this.hduNickName = hduNickName;
	}

	/**
	 * @return the hduType
	 */
	public short getHduType() {
		return hduType;
	}

	/**
	 * @param hduType the hduType to set
	 */
	public void setHduType(short hduType) {
		this.hduType = hduType;
	}
	
}