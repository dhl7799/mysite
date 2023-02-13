package com.douzone.mysite.web.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class ModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String sno = String.valueOf(session.getAttribute("openedBoardNo"));
		Long no = Long.parseLong(sno);
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		BoardDao bd = new BoardDao();
		bd.modifyByNo(title, content, no);
		new ListAction().execute(request, response);
	}

}
