package board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import board.vo.BoardVO;
import sqlmap.MybatisManager;

public class BoardDAO {
	
	// 게시물 목록 리턴
	public List<BoardVO> list() {
		List<BoardVO> list = null;
		SqlSession session = null;
		
		try {
			session = MybatisManager.getInstance().openSession();
			list = session.selectList("board.list");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();	// SqlSession 종료
		}
		
		return list;
	}
	
	// 게시물 저장
	public void insert(BoardVO vo) {
		SqlSession session = null;
		try {
			session = MybatisManager.getInstance().openSession();
			session.insert("board.insert", vo);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
	}

}
