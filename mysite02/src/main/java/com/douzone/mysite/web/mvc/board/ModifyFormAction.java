package com.douzone.mysite.web.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class ModifyFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sno = request.getParameter("no");
		Long no = Long.parseLong(sno);
		BoardVo bd = new BoardDao().viewBoard(no);
		request.setAttribute("Ititle", bd.getTitle());
		request.setAttribute("Icontent", bd.getContents());
		request.setAttribute("no", no);
		MvcUtil.forward("board/modify", request, response);
	}

}
