package com.douzone.mysite.repository;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.BoardVo;

@Repository
public class BoardRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	public Long countBYGNO_AND_DEPTH(long g_no, long depth) {
		Map<String, Object> map = new HashMap<>();
		map.put("g_no", g_no);
		map.put("depth", depth);
		return sqlSession.selectOne("board.countBYGNO_AND_DEPTH", map);
	}
	
	public void updateGNO(Long no) {
		Map<String, Object> map = new HashMap<>();
		map.put("no", no);
		map.put("g_no", no);
		sqlSession.update("board.updateGNO", map);
	}
	
	public void updateHit(Long visitCount, Long no) {
		Map<String, Object> map = new HashMap<>();
		map.put("visitCount", visitCount);
		map.put("no", no);
		sqlSession.update("board.updateHit",map);
	}
		
	
	public void increaseHit(Long no) {	
		sqlSession.update("board.increaseHit",no);
	}
	
	public BoardVo viewBoard(Long no) {
		return sqlSession.selectOne("board.viewBoard", no);
	}
	
	public List<BoardVo> searchByTitle(String stitle) {
		List<BoardVo> list = sqlSession.selectList("board.searchByTitle",stitle);
		return list;
	}
	
	
	public void deleteByNo(Long no) {
		sqlSession.delete("board.deleteByNo", no);	
	}
	
	public void insert(BoardVo vo) {
		sqlSession.insert("board.insert", vo);
	}
	
	public long getMaxNo() {
		return sqlSession.selectOne("board.getMaxNo");
	}
	
	public void modifyByNo(BoardVo vo) {
		sqlSession.update("modifyByNo", vo);
	}

	public void increaseONoByGNo(long o_no, Long g_no) {
		Map<String,Object> map = new HashMap<>();
		map.put("o_no", o_no);
		map.put("g_no", g_no);
		sqlSession.update("increaseONoByGNo",map);
	}
	
	public long countByGNo(long g_no) {
		long num = 0;
		if(sqlSession.selectOne("countByGNo",g_no) != null) {
			num = sqlSession.selectOne("countByGNo",g_no);
		}
		return num;
	}

}
