package com.douzone.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.dto.JsonResult;
import com.douzone.mysite.service.GuestbookService;
import com.douzone.mysite.vo.GuestbookVo;

@Controller
@RequestMapping("/guestbook")
public class GuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(Model model) {
		List<GuestbookVo> list = guestbookService.list();
		model.addAttribute("list",list);
		return "/guestbook/list";
	}
	
	@RequestMapping(value="/list", method=RequestMethod.POST)
	public String write(GuestbookVo vo) {
		System.out.println(vo);
		guestbookService.write(vo);
		System.out.println(vo);
		return "redirect:/guestbook/list";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public String deleteform(Model model, Long no) {
		model.addAttribute("no",no);
		return "/guestbook/delete";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String delete(Long no, String password) {
		guestbookService.delete(no,password);
		return "redirect:/guestbook/list";
	}
	
	@RequestMapping("/spa")
	public String indexSPA() {
		return "guestbook/index-spa";
	}
	
	/*
	@RequestMapping(value="/spa", method=RequestMethod.GET)
	public String indexSPA(Model model) {
		List<GuestbookVo> list = guestbookService.list();
		model.addAttribute("list",list);
		return "guestbook/index-spa";
	}*/
		
}
