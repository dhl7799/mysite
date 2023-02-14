package com.douzone.mysite.vo;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.repository.UserRepository;

public class BoardVo {

	private Long no;
	private String title;
	private String content;
	private Long hit;
	private String reg_date;
	private Long g_no;
	private Long o_no;
	private Long depth;
	private Long user_no;
	private String user_name;
	
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getHit() {
		return hit;
	}
	public void setHit(Long hit) {
		this.hit = hit;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public Long getG_no() {
		return g_no;
	}
	public void setG_no(Long g_no) {
		this.g_no = g_no;
	}
	public Long getO_no() {
		return o_no;
	}
	public void setO_no(Long o_no) {
		this.o_no = o_no;
	}
	public Long getDepth() {
		return depth;
	}
	public void setDepth(Long depth) {
		this.depth = depth;
	}
	public Long getUser_no() {
		return user_no;
	}
	public void setUser_no(Long user_no) {
		this.user_no = user_no;
	}
	@Override
	public String toString() {
		return "BoardVo [no=" + no + ", title=" + title + ", content=" + content + ", hit=" + hit + ", reg_date="
				+ reg_date + ", g_no=" + g_no + ", o_no=" + o_no + ", depth=" + depth + ", user_no=" + user_no
				+ ", user_name=" + user_name + "]";
	}
	
}
