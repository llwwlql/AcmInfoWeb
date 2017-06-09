package com.llwwlql.crawler.contest;

import java.util.Map;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class CrawlerHCInfo implements BaseCrawlerContest{

	private Document doc;
	private String contestUrl = "";
	
	@Override
	public Document getContestInfo() throws Exception {
		Connection conn = Jsoup.connect("");
		
		return null;
	}

	@Override
	public void saveContestInfo(Document doc) throws Exception {
		
	}

	@Override
	public void saveCookies(Map<String, String> cookies) throws Exception {
		
	}

	@Override
	public void readCookies() throws Exception {
		
	}

	@Override
	public void login() throws Exception {
		
	}

}
