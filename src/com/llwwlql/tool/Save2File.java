package com.llwwlql.tool;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

public class Save2File {
	/**
	 * 测试方法，保存指定文件中
	 * 
	 * @param fileName
	 * @param data
	 * @throws IOException
	 */
	public static void save2File(String fileName, String data) throws IOException {

		OutputStream fout = new FileOutputStream(fileName);
		byte[] fileDate = data.getBytes("GBK");
		fout.write(fileDate);
		if (fout != null)
			fout.close();
	}

}
