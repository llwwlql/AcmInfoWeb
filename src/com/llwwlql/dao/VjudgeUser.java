package com.llwwlql.dao;

public class VjudgeUser {

	private String userName;
	private String nickName;
	private String solved;
	private String attempted;

	public VjudgeUser() {
		super();
	}

	public VjudgeUser(String userName, String nickName, String solved,
			String attempted) {
		super();
		this.userName = userName;
		this.nickName = nickName;
		this.solved = solved;
		this.attempted = attempted;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getSolved() {
		return solved;
	}

	public void setSolved(String solved) {
		this.solved = solved;
	}

	public String getAttempted() {
		return attempted;
	}

	public void setAttempted(String attempted) {
		this.attempted = attempted;
	}

	@Override
	public String toString() {
		return "VjudgeUser [userName=" + userName + ", nickName=" + nickName
				+ ", solved=" + solved + ", attempted=" + attempted + "]";
	}

}
