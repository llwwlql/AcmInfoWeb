package com.llwwlql.crawler.contest;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class CrawlerVCInfo implements BaseCrawlerContest {

	private String loginUrl = "https://vjudge.net/user/login";
	private String contestUrl = "https://vjudge.net/contest/rank/single/";
	private String loginConUrl = "https://vjudge.net/contest/login/";
	private String contestId;
	private Map<String, String> cookies;
	private Document doc;

	private boolean attpLogin = false;

	private final static String cookiesFile = "vjCookies.properties";

	public CrawlerVCInfo(String contestId) {
		super();
		this.contestId = contestId;
		contestUrl = contestUrl + contestId;
		loginConUrl = loginConUrl + contestId;
		System.out.println(contestUrl);
		System.out.println(loginConUrl);
	}

	public String getContestId() {
		return contestId;
	}

	public void setContestId(String contestId) {
		this.contestId = contestId;
	}

	/**
	 * 获取Contest的排名信息
	 */
	@Override
	public Document getContestInfo() throws Exception {
		readCookies();
		Connection conn = Jsoup.connect(contestUrl);
		conn.cookies(cookies).validateTLSCertificates(false)
				.ignoreContentType(true);
		doc = conn.post();
		// 判断Cookie是否有效,第一次判断无效先进行Contest登录，第二次判断无效则先登录用户，再登录Contest
		if (doc.body().text().equals("")) {
			if (attpLogin)
				this.loginUser();
			this.login();
			attpLogin = true;
			System.out.println("重新获取：");
			this.getContestInfo();
		}
		return doc;
	}

	/**
	 * 解析Contest的排名数据
	 */
	@Override
	public void saveContestInfo(Document doc) throws Exception {

	}

	/**
	 * 保存Cookies信息到配置文件中 实现Cookies持久化
	 */
	@Override
	public void saveCookies(Map<String, String> cookies) throws Exception {
		OutputStream fout = new FileOutputStream(cookiesFile);

		for (Map.Entry<String, String> entry : cookies.entrySet()) {
			String keyValue = entry.getKey() + "=" + entry.getValue() + "\n";
			fout.write(keyValue.getBytes());
		}
		if (fout != null)
			fout.close();
	}

	/**
	 * 读取配置文件中的Cookies信息
	 */
	@Override
	public void readCookies() throws Exception {
		InputStream fin = new FileInputStream(cookiesFile);
		Properties pro = new Properties();
		pro.load(fin);
		if (fin != null)
			fin.close();
		cookies = new HashMap<String, String>((Map) pro);
		for (Map.Entry<String, String> entry : cookies.entrySet()) {
			System.out.println("Key = " + entry.getKey() + ", Value = "
					+ entry.getValue());
		}
	}

	/**
	 * 进行
	 */
	public void login() throws Exception {
		readCookies();
		Map<String, String> tempCookies = new HashMap<String, String>();
		tempCookies.put("password", "lduacm");
		Connection conn = Jsoup.connect(loginConUrl);
		conn.cookies(cookies).validateTLSCertificates(false).data(tempCookies)
				.ignoreContentType(true).method(Method.POST);
		conn.execute();
	}

	/**
	 * 进行用户登录
	 * 
	 * @throws Exception
	 */
	public void loginUser() throws Exception {

		Map<String, String> paramete = new HashMap<String, String>();
		paramete.put("username", "HTTPCrawl");
		paramete.put("password", "12345678");

		Connection conn = Jsoup.connect(loginUrl);
		conn.validateTLSCertificates(false).data(paramete).method(Method.POST);

		cookies = conn.execute().cookies();
		saveCookies(cookies);
	}

	/**
	 * 测试方法，保存指定文件中
	 * 
	 * @param fileName
	 * @param data
	 * @throws IOException
	 */
	public void save2File(String fileName, String data) throws IOException {

		OutputStream fout = new FileOutputStream(fileName);
		fout.write(data.getBytes());
		if (fout != null)
			fout.close();
	}

	public static void main(String[] args) throws Exception {
		CrawlerVCInfo vjudgeContest = new CrawlerVCInfo("142083");
		// 142083
		// vjudgeContest.loginUser();
		// vjudgeContest.login();
		vjudgeContest.getContestInfo();
		System.out.println("运行完成！");
	}
}
