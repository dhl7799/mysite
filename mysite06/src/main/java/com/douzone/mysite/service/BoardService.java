package com.douzone.mysite.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;

@Service
public class BoardService {
	@Autowired
	private BoardRepository boardrepository;
	
	public void increaseHit(Long no) {
		boardrepository.increaseHit(no);
	}
	
	public void write(BoardVo vo) {
		boardrepository.insert(vo);
		long no = boardrepository.getMaxNo();
		boardrepository.updateGNO(no);
	}
	
	public void writecomment(BoardVo vo) {
		boardrepository.insert(vo);
	}

	public BoardVo view(Long no) {
		BoardVo vo = boardrepository.viewBoard(no);
		return vo;
	}
	
	public List<BoardVo> search(String kwd){
		List<BoardVo> list = boardrepository.searchByTitle(kwd);
		return list;
	}
	/*
	public void modify(String title, String content, Long no){
		boardrepository.modifyByNo(title, content, no);
	}*/
	
	public void modify(BoardVo vo){
		boardrepository.modifyByNo(vo);
	}
	
	public long countBYGNO_AND_DEPTH(long g_no, long depth) {
		Long o_no = boardrepository.countBYGNO_AND_DEPTH(g_no, depth);
		return o_no;
	}
	
	public void delete(Long no) {
		boardrepository.deleteByNo(no);
	}

	public void increaseONoByGNo(long o_no, long g_no) {
		boardrepository.increaseONoByGNo(o_no,g_no);
	}
	
	public long countByGNo(long g_no) {
		return boardrepository.countByGNo(g_no);
	}

}
