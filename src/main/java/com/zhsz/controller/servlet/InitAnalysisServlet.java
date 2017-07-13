package com.zhsz.controller.servlet;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import com.zhsz.controller.test.AnalysisThread;


public class InitAnalysisServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public void init() throws ServletException {
		// 启动定时获取access_token的线程
		System.out.println("回复情感分析线程启动。。。。。。。。");
		new Thread(new AnalysisThread()).start();
	}
}
