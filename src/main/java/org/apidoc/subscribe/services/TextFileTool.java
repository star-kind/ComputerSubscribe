package org.apidoc.subscribe.services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 * 文本文件的处理操作
 * 
 * @author user
 *
 */
public class TextFileTool {
	static String ts = "org.apidoc.subscribe.services" + "...\n";

	static String file_url = "/home/user/";
	static String previous_file_name = "PostMan-";
	static String subffix_file_name = ".html";

	/**
	 * 创建html文件,格式:PostMan-时间毫秒数.html,返回文件路径全名
	 * 
	 * @return
	 */
	public String createFileByUrl() {
		Long time = new Date().getTime();
		String timeStr = String.valueOf(time);
		// 完整的文件路径
		String completeUrl = file_url + previous_file_name + timeStr
				+ subffix_file_name;

		File file = new File(completeUrl);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println(ts + "createFileByUrl--创建成功\n--return=" + completeUrl);
		return completeUrl;
	}

	/**
	 * 接收一个字符串,追加写入指定文本文件中
	 * 
	 * @param content
	 * @param fileUrl
	 */
	public void writeContentToFile(String content, String fileUrl) {
		File file = new File(fileUrl);
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
			// 文件的续写
			FileWriter fw = new FileWriter(file, true);
			// 写入换行,Windows平台下用\r\n，Linux/Unix平台下用\n
			fw.write("\r\n");

			fw.write(content);

			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

//	public static void main(String[] args) {
//		TextFileTool tool = new TextFileTool();
//		String fileUrl = tool.createFileByUrl();
//		tool.writeContentToFile(
//				"<html><head><title>999</title></head><body>666666</body></html><style>body{font-size:40px;}</style>",
//				fileUrl);
//	}
}
