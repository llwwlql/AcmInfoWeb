package com.llwwlql.dao;

public class Json2VjudgeContest {
	private String[][] data;
	private String recordsTotal;
	private String recordsFiltered;
	private Integer draw;	
	public Json2VjudgeContest(String[][] data, String recordsTotal,
			String recordsFiltered, Integer draw) {
		super();
		this.data = data;
		this.recordsTotal = recordsTotal;
		this.recordsFiltered = recordsFiltered;
		this.draw = draw;
	}
	public String[][] getData() {
		return data;
	}
	public void setData(String[][] data) {
		this.data = data;
	}
	public String getRecordsTotal() {
		return recordsTotal;
	}
	public void setRecordsTotal(String recordsTotal) {
		this.recordsTotal = recordsTotal;
	}
	public String getRecordsFiltered() {
		return recordsFiltered;
	}
	public void setRecordsFiltered(String recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}
	public Integer getDraw() {
		return draw;
	}
	public void setDraw(Integer draw) {
		this.draw = draw;
	}
}
