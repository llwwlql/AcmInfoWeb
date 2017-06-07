package com.llwwlql.crawler.user;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TestSpider {
	private String urlName;
	private String url;

	public String getUrlName() {
		return urlName;
	}

	public void setUrlName(String urlName) {
		this.urlName = urlName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public static void main(String[] args) {
		String url = "https://www.cnblogs.com/";
		String fileName = "E:/URLQUEUE.txt";
		Set<String> isQueue = new HashSet<String>();
		OutputStream fout = null;
		Queue<String> q = new LinkedList<String>();
		q.offer(url);
		isQueue.add(url);
		try {
			fout = new FileOutputStream(fileName);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while (!q.isEmpty()) {
			url = q.poll();
			Document doc = null;
			try {
				doc = Jsoup.connect(url).get();
				System.out.println("连接可用！" + url);
				try {
					url = url + "\n\r";
					fout.write(url.getBytes());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("连接不可用！" + url);
				continue;
			}
			Element body = doc.body();
			if (body != null) {
				Elements es = body.getElementsByTag("a");
				for (Element element : es) {
					String href = element.absUrl("href");
					if (!href.equals("#") && !isQueue.contains(href)) {
						q.offer(href);
						isQueue.add(href);
						// String b = element.text() + "\t\t" + href + "\r\n";
					}
				}
			}
		}
	}
}
