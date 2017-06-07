package com.llwwlql.crawler.user;

import org.jsoup.nodes.Document;

/**
 * 
 * @ClassName: BaseCrawler
 * @Description: 所有Crawler类的接口类
 * @author: 逯其鲁
 * @date: 2017-6-7 下午4:14:32
 */
interface BaseCrawlerUser {

	Document getUserInfo() throws Exception;

	void saveUserInfo(Document doc) throws Exception;

}
