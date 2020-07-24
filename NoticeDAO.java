package com.the.cho.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;


import com.the.cho.controller.MemberController;
import com.the.cho.dto.Notice;
import com.the.cho.mybatis.MybatisManager;
import com.the.cho.vo.SearchInfo;

public class NoticeDAO{

	private SqlSessionFactory sqlSessionFactory=MybatisManager.getInstance();
	NoticeDAO() {}
	
	public List<Notice> noticeList(SearchInfo searchInfo, RowBounds rowBounds) {
		SqlSession sqlSession=sqlSessionFactory.openSession();
		//쿼리를 실행해서 나온 결과를 List에 저장후 리턴
		List<Notice> result=sqlSession.selectList("notice.list",searchInfo, rowBounds);
		sqlSession.close();
		
		return result;
	}

	public int noticeTotal(SearchInfo searchInfo) {
		SqlSession sqlSession=sqlSessionFactory.openSession();
		int result=sqlSession.selectOne("notice.total", searchInfo);
		sqlSession.close();
		return result;
	}

	public Notice detail(int no) {
		SqlSession sqlSession=sqlSessionFactory.openSession();
//		Mapper에게 일시키는 것.
		Notice result=sqlSession.selectOne("notice.detail",no);
		sqlSession.close();
		return result;
	}

	public int delete(int delete_no) {
		//DB연결..
		SqlSession sqlSession=sqlSessionFactory.openSession();
		//쿼리실행을 위해..Mapper를 이용하자.
		//("mapper namespace.id", 넘겨줄데이터)
		int result=sqlSession.delete("notice.delete", delete_no);
		//dB데이터가 변경되었으므로 확정하자.
		sqlSession.commit();
		//commit() 대신 DB연결시
		//SqlSession sqlSession=sqlSessionFactory.openSession(true); 
		//쓰면 auto commit 된다.
		
		//연결해제
		sqlSession.close();
		return result;
	}

	
	
}
