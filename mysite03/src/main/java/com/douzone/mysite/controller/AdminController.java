package com.douzone.mysite.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.mysite.security.Auth;
import com.douzone.mysite.service.FileUploadService;
import com.douzone.mysite.service.SiteService;
import com.douzone.mysite.vo.SiteVo;

@Auth(role="ADMIN")
@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private SiteService siteService;
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	ServletContext servletContext;
	
	@Autowired
	private FileUploadService fileuploadService;
	
	@RequestMapping("")
	public String main(Model model) {
		SiteVo vo = siteService.getSiteInfo();
		model.addAttribute("siteVo",vo);
		return "admin/main";
	}

	@RequestMapping("/guestbook")
	public String guestbook() {
		return "admin/guestbook";
	}
	
	@RequestMapping("/board")
	public String board() {
		return "admin/board";
	}
	
	@RequestMapping("/user")
	public String user() {
		return "admin/user";
	}
	/*
	 * @RequestParam("title") String title,
			@RequestParam("welcomeMessage") String welcomeMessage,
			@RequestParam("profileURL") MultipartFile profileURL,
			@RequestParam("description") String description
	 * */
	@RequestMapping(value="/main/update", method=RequestMethod.POST)
	public String getSiteInfo(SiteVo vo, MultipartFile file) {
		String url = fileuploadService.restore(file);
		if(url != null) {
			vo.setProfileURL(url);
		}
		siteService.updateSiteInfo(vo);
		servletContext.setAttribute("sitevo",vo);
		
		SiteVo site = applicationContext.getBean(SiteVo.class);
		BeanUtils.copyProperties(vo, site);
			
		return "redirect:/admin";
	}
}
