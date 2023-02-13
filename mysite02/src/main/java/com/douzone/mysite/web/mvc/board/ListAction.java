package com.douzone.mysite.web.mvc.board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.dao.BoardDao;
import com.douzone.mysite.dao.GuestbookDao;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.GuestbookVo;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.mvc.Action;
import com.douzone.web.util.MvcUtil;

public class ListAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String kwd = request.getParameter("kwd");
		if(kwd == null) {
			kwd = "";
		}
		List<BoardVo> totallist = new BoardDao().searchByTitle(kwd);
		List<BoardVo> list = new ArrayList<>();
		//세션에서 author 받아와서 유저번호 전달
		
		String spageNo = request.getParameter("pageNo");
		Long pageNo = 1l;
		if(spageNo == null) {
			pageNo = 1l;
		}
		else {
			pageNo = Long.parseLong(spageNo);
		}
		//1이면 0~9 2면 10~19
		//1이면 0~2 2면 3~5 3이면 6~8 
		for(long i=10*(pageNo-1); i<10*pageNo;i++) {
			if(i>=totallist.size())
				break;
			list.add(totallist.get((int) i));
		}
		
		request.setAttribute("list", list);
		request.setAttribute("totallist", totallist);
		request.setAttribute("pageNo", pageNo);
		request.setAttribute("kwd", kwd);
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		if(authUser == null) {
			request.setAttribute("user_no", -1);
			MvcUtil.forward("board/list", request, response);
			return;
		}
		
		request.setAttribute("user_no", authUser.getNo());
		MvcUtil.forward("board/list", request, response);
	}
}
