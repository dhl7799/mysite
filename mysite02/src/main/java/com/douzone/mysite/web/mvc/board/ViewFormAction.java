package com.douzone.mysite.web.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.controller.CookieServlet;
import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class ViewFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sno = request.getParameter("no");
		HttpSession session = request.getSession(true);
		Long no = Long.parseLong(sno);
		BoardDao db = new BoardDao();
		//db.increaseHit(no);
		new CookieServlet().doGet(request,response,no);
		BoardVo vo = db.viewBoard(no);
		request.setAttribute("vo", vo);
		request.setAttribute("g_no", vo.getG_no());
		request.setAttribute("o_no", vo.getO_no());
		request.setAttribute("depth", vo.getDepth());
		session.setAttribute("openedBoardNo", no);
		MvcUtil.forward("board/view", request, response);
	}

}
