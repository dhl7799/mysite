package com.douzone.mysite.web.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.vo.UserVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class WriteCommentFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if (authUser == null) {
			MvcUtil.redirect(request.getContextPath()+"/user?a=loginform", request, response);
			return;
		}
		String sg_no = request.getParameter("g_no");
		String so_no = request.getParameter("o_no");
		String sdepth = request.getParameter("depth");
		

		Long g_no = Long.parseLong(sg_no);
		Long o_no = Long.parseLong(so_no);
		Long depth = Long.parseLong(sdepth);
		
		request.setAttribute("g_no", g_no);
		request.setAttribute("o_no", o_no);
		request.setAttribute("depth", depth);
		
		
		MvcUtil.forward("board/writecomment", request, response);
	}

}
