
package com.llwwlql.crawler.contest;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.google.gson.Gson;
import com.llwwlql.dao.Contest;
import com.llwwlql.dao.Json2VjudgeContest;
import com.llwwlql.tool.DateUtil;
import com.llwwlql.tool.Save2File;

public class CrawlerVjudgeContest {

	private final static String vjParam = "vjParam.properties";
	private final static String contestUrl = "https://vjudge.net/contest/data";
	private Document doc = null;

	/**
	 * 获取Vjudge所有的Contest基本信息
	 * @return
	 * @throws Exception
	 */
	public Document crawlAllContest() throws Exception {
		Connection conn = Jsoup.connect(contestUrl);
		InputStream fin = new FileInputStream(vjParam);
		Properties pro = new Properties();
		pro.load(fin);
		if (fin != null)
			fin.close();
		conn.validateTLSCertificates(false).ignoreContentType(true)
				.data((Map) pro);
		doc = conn.post();
		Save2File.save2File("E:/TestDoc/AllVjudgeContest.html", doc.toString());
		return doc;
	}

	/**
	 * 保存所有Contest信息
	 * 
	 * @throws Exception
	 */
	public void saveAllContest() throws Exception {
		String json = doc.body().text();
		Gson gson = new Gson();
		Json2VjudgeContest contestInfo = gson.fromJson(json,
				Json2VjudgeContest.class);
		String[][] data = contestInfo.getData();
		String nowTime = DateUtil.getNowDate();
		
		// 将信息从数组中获取出来
		for (String[] strings : data) {
			String startTime = DateUtil
					.getBeginDate(Long.parseLong(strings[2]));
			String endTime = DateUtil.getBeginDate(Long.parseLong(strings[3]));
			Integer properNum = Integer.parseInt(data[0][13]);
			Integer originId = Integer.parseInt(data[0][0]);
			// 如何在数据库中存在，不操作
			if (!isExist(originId) && endTime.compareTo(nowTime) <= 0) {
				Contest vjudgeContest = new Contest(strings[1], originId, startTime, endTime, (short)2, (short)1, properNum);
				// save vjudgeContest对象
			}
		}
	}

	public boolean isExist(Integer origin) {
		//select isnull((select top(1) 1 from tableName where conditions), 0)
		//判断该数据是否在数据库中存在
		return true;
	}

	public static void main(String[] args) throws Exception {
		CrawlerVjudgeContest crawlerVCInfo = new CrawlerVjudgeContest();
		crawlerVCInfo.crawlAllContest();
		crawlerVCInfo.saveAllContest();
	}
}
