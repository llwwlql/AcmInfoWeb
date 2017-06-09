package com.llwwlql.crawler.contest;

import java.util.Map;

import org.jsoup.nodes.Document;

/**
 * 
 * @ClassName: BaseCrawler
 * @Description: 所有Crawler类的接口类
 * @author: 逯其鲁
 * @date: 2017-6-7 下午4:14:32
 */
interface BaseCrawlerContest {

	Document getContestInfo() throws Exception;

	void saveContestInfo(Document doc) throws Exception;

	void saveCookies(Map<String ,String> cookies) throws Exception;

	void readCookies() throws Exception;

	void login() throws Exception;

}
