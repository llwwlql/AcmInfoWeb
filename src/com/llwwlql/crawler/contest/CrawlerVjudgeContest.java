package com.llwwlql.crawler.contest;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class CrawlerVjudgeContest implements BaseCrawlerContest {

	private String url = "https://vjudge.net/contest/";
	private String contestId;

	public CrawlerVjudgeContest(String contestId) {
		super();
		this.contestId = contestId;
	}

	public String getContestId() {
		return contestId;
	}

	public void setContestId(String contestId) {
		this.contestId = contestId;
	}

	@Override
	public Document getContesInfo() throws Exception {

		Document doc = Jsoup.connect(url).validateTLSCertificates(false).get();
		System.out.println(doc.toString());
		return doc;
	}

	@Override
	public void saveContestInfo(Document doc) throws Exception {

	}

	@Override
	public void saveCookies() throws Exception {

	}

	@Override
	public void readCookies() throws Exception {

	}

	@Override
	public void login() throws Exception {

	}
	
	public void save2File(String fileName, String data) throws IOException {

		OutputStream fout = new FileOutputStream(fileName);
		fout.write(data.getBytes());
		if (fout != null)
			fout.close();
	}
	
	public static void main(String[] args) throws Exception {
		CrawlerVjudgeContest vjudgeContest = new CrawlerVjudgeContest("166636");
		Document doc = vjudgeContest.getContesInfo();
	}

}
