package com.zhsz.utils.test;

public class GloablParam {
	private static String post = "1";
	private static String days = "";
	private static String searchkey = "";
	public static String getPost() {
		return post;
	}
	public static void setPost(String post) {
		GloablParam.post = post;
	}
	public static String getDays() {
		return days;
	}
	public static void setDays(String days) {
		GloablParam.days = days;
	}
	public static String getSearchkey() {
		return searchkey;
	}
	public static void setSearchkey(String searchkey) {
		GloablParam.searchkey = searchkey;
	}
	
	
	
	
}
