package com.cdogs.lightBlog.util;

import org.jsoup.Jsoup;

/**
 * HTML parse utils.
 * @author CDogs
 *
 */
public class HTMLUtils {
	
	public static String html2Text(String html) {
		return Jsoup.parse(html).text();
	}

}
