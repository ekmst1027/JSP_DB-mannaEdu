package board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

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
	
	// 파일 이름 리턴
	public String getFileName(int num) {
		String result = "";
		SqlSession session = null;
		try {
			// mybatis 실행 객체 생성
			session = MybatisManager.getInstance().openSession();
			// 게시물 번호에 해당하는 첨부파일 이름이 리턴됨
			result = session.selectOne("board.getFileName", num);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();	// 리소스 정리
		}
		
		return result;
	}
	
	// 다운로드 횟수 증가 처리
	public void plusDown(int num) {
		SqlSession session = null;
		try {
			session = MybatisManager.getInstance().openSession();
			session.update("board.plusDown", num);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
	}
	
	// 조회수 증가 처리
	public void plusReadCount(int num, HttpSession count_session ) {
		SqlSession session = null;
		try {
			long read_time = 0;	// 게시물을 읽은 시간
			// 세션이 있으면 읽은 시간을 가져옴
			if(count_session.getAttribute("read_time_" + num) != null) {
				read_time = (long)count_session.getAttribute("read_time_" + num);
			}
			long current_time = System.currentTimeMillis();	// 현재 시각
			session = MybatisManager.getInstance().openSession();
			// 일정시간이 경과하면 조회수를 올림
			if(current_time - read_time > 5*1000) {
				session.update("board.plusReadCount", num);
				session.commit();
				count_session.setAttribute("read_time_" + num, current_time);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) session.close();
		}
	}
	
	// 상세화면용 vo 리턴 코드
	public BoardVO view(int num, boolean newline) {
		SqlSession session = null;
		BoardVO vo = null;
		try {
			session = MybatisManager.getInstance().openSession();
			vo = session.selectOne("board.view", num);
			
			// 줄바꿈 처리
			if(newline == true) {
				String content = vo.getContent();
				content = content.replace("\n", "<br>");
				vo.setContent(content);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		return vo;
	}
	
	public String passwdCheck(int num, String passwd) {
		String result = null;
		SqlSession session = null;
		try {
			session = MybatisManager.getInstance().openSession();
			Map<String, Object> map = new HashMap<>();
			map.put("num", num);	// 맵.put("변수명", 값)
			map.put("passwd", passwd);
			result = session.selectOne("board.pass_check", map);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
		
		return result;
	}
	
	// 게시물 수정
	public void update(BoardVO vo) {
		SqlSession session = null;
		try {
			session = MybatisManager.getInstance().openSession();
			session.update("board.update", vo);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
	}
	
	// 게시물 삭제
	public void delete(int num) {
		SqlSession session = null;
		try {
			session = MybatisManager.getInstance().openSession();
			session.update("board.delete", num);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(session != null) session.close();
		}
	}
	
	
}
