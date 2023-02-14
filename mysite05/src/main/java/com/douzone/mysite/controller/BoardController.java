package com.douzone.mysite.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.security.AuthUser;
import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;



@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	//@RequestParam(value='p', required=true, defaultValue="1) Integer page
	@RequestMapping(value="/list/{pageNo}", method= {RequestMethod.GET,RequestMethod.POST})
	public String boardlist(@PathVariable("pageNo") Long pageNo, String kwd, HttpSession session, Model model) {
		if(kwd == null)
			kwd = "";
		
		List<BoardVo> totallist = boardService.search(kwd);
		List<BoardVo> list = new ArrayList<>();
		//list = totallist.subList((int)(10*(pageNo-1)), (int)(10*pageNo));
		for(long i=10*(pageNo-1); i<10*pageNo;i++) {
			if(i>=totallist.size())
				break;
			list.add(totallist.get((int) i));
		}
		
		model.addAttribute("list",list);
		model.addAttribute("totallist",totallist);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("keyword", kwd);
		return "/board/list";
	}
	
	@RequestMapping(value="/list", method=RequestMethod.POST)
	public String boardlist_page(HttpSession session, Model model) {
				
		return "/board/list";
	}
	
	@Auth
	@RequestMapping(value="/writeform", method=RequestMethod.GET)
	public String writeform(HttpSession session) {
		return "/board/write";
	}
	
	@Auth
	@RequestMapping(value="/write", method=RequestMethod.POST)
	public String write(@AuthUser UserVo authUser, HttpSession session, Model model,BoardVo bd) {
		BoardVo vo = new BoardVo();
		//UserVo authUser = (UserVo) session.getAttribute("authUser");
		Long hit = 0l;
		Long g_no = 0l;
		Long o_no = 0l;
		Long depth = 0l;
		Long user_no = authUser.getNo();
		
		
		vo.setTitle(bd.getTitle());
		vo.setContent(bd.getContent());
		vo.setHit(hit);
		vo.setG_no(g_no);
		vo.setO_no(o_no);
		vo.setDepth(depth);
		vo.setUser_no(user_no);
		System.out.println(authUser);
		System.out.println(vo);
		vo.toString();
		boardService.write(vo);
		
		return "redirect:/board/list/1";
	}
	
	@RequestMapping(value="/view/{no}", method=RequestMethod.GET)
	public String view(@PathVariable("no") Long no, Model model) {
		BoardVo vo = boardService.view(no);
		model.addAttribute("vo",vo);
		boardService.increaseHit(no);
		return "/board/view";
	}
	
	@Auth
	@RequestMapping(value="/modifyform/{no}", method=RequestMethod.GET)
	public String modify(@AuthUser UserVo authUser, @PathVariable("no") Long no, Model model) {
		BoardVo vo = boardService.view(no);
		model.addAttribute("no", no);
		model.addAttribute("Ititle", vo.getTitle());
		model.addAttribute("Icontent", vo.getContent());
		return "/board/modify";
	}
	
	//@PathVariable("no")
	@Auth
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modify(@AuthUser UserVo authUser, Long no, String title, String content, Model model) {
		BoardVo vo = new BoardVo();
		vo.setTitle(title);
		vo.setContent(content);
		vo.setNo(no);
		boardService.modify(vo);
		return "redirect:/board/list/1";
	}
	
	@Auth
	@RequestMapping(value="/writecommentform", method=RequestMethod.GET)
	public String writeCommentForm(@AuthUser UserVo authUser,Long g_no, Long o_no, Long depth, Model model) {
		model.addAttribute("g_no",g_no);
		model.addAttribute("o_no",o_no);
		model.addAttribute("depth",depth);
		return "/board/writecomment";
	}
	
	//@PathVariable("no")
	@Auth
	@RequestMapping(value="/writecomment", method=RequestMethod.POST)
	public String WriteComment(@AuthUser UserVo authUser, HttpSession session, Long g_no, Long o_no, Long depth, Model model, String title, String content) {
		//UserVo authUser = (UserVo) session.getAttribute("authUser");
		BoardVo vo = new BoardVo();
		vo.setTitle(title);
		vo.setContent(content);
		vo.setHit(0L);
		vo.setG_no(g_no);
		long ndepth = depth+1l;
		vo.setDepth(ndepth);
		
		//기존방식
		vo.setO_no(o_no+1);
		boardService.increaseONoByGNo(vo.getO_no(),g_no);
		
		//boardService.countBYGNO_AND_DEPTH(g_no, ndepth);
		
		vo.setUser_no(authUser.getNo());
		boardService.writecomment(vo);
		return "redirect:/board/list/1";
	}
	@Auth
	@RequestMapping(value="/deleteform/{no}", method=RequestMethod.GET)
	public String deleteform(@AuthUser UserVo authUser, @PathVariable("no") Long no,Model model) {
		model.addAttribute("no", no);
		return "/board/deleteform";
	}
	
	@Auth
	@RequestMapping(value="/delete/{no}", method=RequestMethod.POST)
	public String delete(@AuthUser UserVo authUser, @PathVariable("no") Long no) {
		boardService.delete(no);
		return "redirect:/board/list/1";
	}
	
	
	
}
