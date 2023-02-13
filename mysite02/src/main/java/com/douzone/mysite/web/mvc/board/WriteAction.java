package com.douzone.mysite.web.mvc.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.mvc.Action;

public class WriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		Long hit = 0l;
		Long g_no = 0l;
		Long o_no = 0l;
		Long depth = 0l;
		Long user_no = authUser.getNo();
		
		BoardVo nb = new BoardVo();
		nb.setTitle(title);
		nb.setContents(content);
		nb.setHit(hit);
		nb.setG_no(g_no);
		nb.setO_no(o_no);
		nb.setDepth(depth);
		nb.setUser_no(user_no);
		
		BoardDao bd = new BoardDao();
		bd.insert(nb);
		Long gno = bd.findNo(nb);
		bd.updateGNO(gno);
		new ListAction().execute(request, response);
	}

}
