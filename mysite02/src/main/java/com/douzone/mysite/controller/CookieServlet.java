package com.douzone.mysite.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDao;

public class CookieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response,long no) throws ServletException, IOException {
		int visitCount = 0;
		
		// 쿠키읽기
		Cookie[] cookies = request.getCookies();
		if(cookies != null && cookies.length > 0) {
			for(Cookie cookie : cookies) {
				if(("visitCount"+Long.toString(no)).equals(cookie.getName())){
					visitCount = Integer.parseInt(cookie.getValue());
				}
			}
		}		
		
		visitCount++;
		
		// 쿠키쓰기
		Cookie cookie = new Cookie("visitCount"+Long.toString(no), String.valueOf(visitCount));
		cookie.setPath(request.getContextPath());
		cookie.setMaxAge(24 * 60 * 60);  // 1day
		response.addCookie(cookie);
		
		
		new BoardDao().updateHit((long) visitCount, no);
		// 화면 출력
		//response.setContentType("text/html; charset=utf-8");
		//PrintWriter out = response.getWriter();
		//out.println("<h1>방문횟수: " + visitCount + "</h1>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}