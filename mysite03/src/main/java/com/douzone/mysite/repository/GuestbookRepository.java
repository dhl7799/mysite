package com.douzone.mysite.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StopWatch;

import com.douzone.mysite.vo.GuestbookVo;

@Repository
public class GuestbookRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	public void deleteByNoAndPassword(Long no, String password) {
		/*Map<String, Object> map = new HashMap<>();
		map.put("no", no);
		map.put("password", password);*/
		Map<String, Object> map = Map.of("no", no, "password", password);
		sqlSession.delete("guestbook.deleteByNoAndPassword", map);
	}
	
	public void insert(GuestbookVo vo) {
		sqlSession.insert("guestbook.insert",vo);
	}
	
	public List<GuestbookVo> findAll() {
		
		List<GuestbookVo> list = sqlSession.selectList("guestbook.findAll");
		
		return list;
	}
	
}
