package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.GuestbookRepository;
import com.douzone.mysite.vo.GuestbookVo;



@Service
public class GuestbookService {
	@Autowired
	private GuestbookRepository guestbookRepository;
	
	public void write(GuestbookVo vo) {
		guestbookRepository.insert(vo);
	}
	
	public List<GuestbookVo> list() {
		return guestbookRepository.findAll();
	}
	
	public List<GuestbookVo> list(long no) {
		return guestbookRepository.findAll(no);
	}
	
	public Boolean delete(Long no, String password) {
		return 1 == guestbookRepository.deleteByNoAndPassword(no, password);
	}
}
