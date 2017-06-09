package com.llwwlql.crawler.contest;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.llwwlql.tool.Save2File;

public class CrawlerHduContest {

	private String contestUrl = "http://acm.hdu.edu.cn/diy/contest_search.php?action=go&content=LDU&types=1&page=";
	private int page = 1;
	private Document doc;

	public CrawlerHduContest() {
		super();
		this.contestUrl += this.page;
	}

	/**
	 * 获取Hdu Contest的信息
	 * @return
	 * @throws Exception
	 */
	public Document crawlAllContest() throws Exception {
		Connection conn = Jsoup.connect(contestUrl);
		doc = conn.get();
		Save2File.save2File("E:/TestDoc/AllHduContest.html", doc.toString());
		return doc;
	}
	
	public static void main(String[] args) throws Exception {
		CrawlerHduContest contest = new CrawlerHduContest();
		contest.crawlAllContest();
	}

}