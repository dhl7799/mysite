package com.douzone.mysite.web.mvc.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.mvc.Action;

public class WriteCommentAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String sg_no = request.getParameter("g_no");
		String so_no = request.getParameter("o_no");
		String sdepth = request.getParameter("depth");
		
		
		
		
		Long g_no = Long.parseLong(sg_no);
		
		Long depth = Long.parseLong(sdepth)+1l;
		Long o_no = Long.parseLong(so_no)+1l;
		new BoardDao().updateONOByGNO(o_no, g_no);
		
		Long hit = 0l;
		System.out.println("o_no = " + o_no);
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
		bd.updateGNO(g_no);
		new ListAction().execute(request, response);
	}

}
