package com.llwwlql.crawler.user;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.llwwlql.dao.VjudgeUser;

public class CrawlerVjudgeUser implements BaseCrawlerUser {
	private String url = "https://vjudge.net/user/";
	private String userName;
	private String baseFileName = "E:/TestDoc/";

	public CrawlerVjudgeUser(String userName) {
		this.userName = userName;
		this.url = this.url + this.userName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public Document getUserInfo() throws Exception {
		Connection conn = Jsoup.connect(url);
		//conn.timeout(10000);
		conn.validateTLSCertificates(false);
		Document doc = conn.get();
		return doc;
	}

	/**
	 * 进行数据抽取并保存数据到数据库
	 */
	@Override
	public void saveUserInfo(Document doc) throws Exception {
		String nickName = this.userName;
		String solved;
		String attempted;
		Element element = doc.body();
		Elements nickNameEs = element.getElementsByTag("span");
		
		Element solvedEs = element.getElementsByTag("td").get(3);
		solved = solvedEs.text();
		Element attEs = element.getElementsByTag("td").get(4);
		attempted = attEs.text();
		//这里处理需要斟酌,不同用户可能不一样
		if(nickNameEs.get(2).text() != null)
			nickName = nickNameEs.get(2).text();
		VjudgeUser vjudgeUser = new VjudgeUser(this.userName, nickName, solved, attempted);
		System.out.println(vjudgeUser);
	}

	@Override
	public void getCookies() throws Exception {

	}

	@Override
	public void saveCookies() throws Exception {

	}

	@Override
	public void login() throws Exception {

	}

	/**
	 * 测试输出到文件中
	 * 
	 * @param data
	 * @throws IOException
	 */
	public void save2File(String fileName, String data) throws IOException {

		OutputStream fout = new FileOutputStream(fileName);
		fout.write(data.getBytes());
		if (fout != null)
			fout.close();
	}

	public static void main(String[] args) {
		CrawlerVjudgeUser crawlerVjudge = new CrawlerVjudgeUser("llwwlql");
		try {
			Document doc = crawlerVjudge.getUserInfo();
			crawlerVjudge.saveUserInfo(doc);
			System.out.println("运行完成！");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
