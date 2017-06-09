package com.llwwlql.crawler.user;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.llwwlql.dao.HduUser;
import com.llwwlql.tool.Save2File;

public class CrawlerHduUser implements BaseCrawlerUser {

	private String userUrl = "http://acm.hdu.edu.cn/userstatus.php?user=";
	private String userName;

	public CrawlerHduUser(String userName) {
		super();
		this.userUrl = userUrl + userName;
	}

	@Override
	public Document getUserInfo() throws Exception {
		Document doc = Jsoup.connect(userUrl).get();
		Save2File.save2File("E:/TestDoc/hduUser.html", doc.toString());
		return doc;
	}

	@Override
	public void saveUserInfo(Document doc) throws Exception {
		Elements body = doc.getElementsByTag("tbody");
		String nickName = doc.getElementsByTag("h1").text();
		
		String proSubmiss = body.get(3).getElementsByTag("tr").get(3).child(1)
				.text();
		String proSoloved = body.get(3).getElementsByTag("tr").get(4).child(1)
				.text();
		String submiss = body.get(3).getElementsByTag("tr").get(5).child(1)
				.text();
		String accept = body.get(3).getElementsByTag("tr").get(6).child(1)
				.text();
		System.out.println("nickname: " + nickName + "\tproSubmisson : " + proSubmiss +"\tproSolved : " + proSoloved + "\tsubmission : " + submiss + "\t accepted : " + accept);
		// 获取或者传入HduUser
		HduUser hduUser = null;
		// 更新hduUser信息
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public static void main(String[] args) throws Exception {
		CrawlerHduUser crawlerHduUser = new CrawlerHduUser("llwwlql");
		Document doc = crawlerHduUser.getUserInfo();
		crawlerHduUser.saveUserInfo(doc);
	}

}
