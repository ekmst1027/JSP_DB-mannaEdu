package guestbook.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import guestbook.vo.GuestBookVO;
import sqlmap.MybatisManager;

public class GuestBookDAO {
	
	// 방명록 리스트 리턴
	public List<GuestBookVO> getList(String searchkey, String search) {
		// mybatis 실행 객체 생성
		SqlSession session = MybatisManager.getInstance().openSession();
		// guestbook 네임스페이스의 id가 gbList인 sql 태그 실행
		// 리턴 타입은 ArrayList를 쓸 수 없고 List 사용
//		List<GuestBookVO> list = session.selectList("guestbook.gbList");
		List<GuestBookVO> list = null;
		
		// 이름+내용으로 조회
		if(searchkey.equals("name_content")) {
			list = session.selectList("gbListAll", search);
			
		// 이름 혹은 내용으로 조회
		} else {
			Map<String, Object> map = new HashMap<>();
			map.put("searchkey", searchkey);	// 검색 옵션
			map.put("search", search);	// 검색 키워드
			list = session.selectList("gbList", map);
		}
		
		// 리스트를 풀어서 변경한 후 다시 저장
		for(GuestBookVO vo : list) {
			String content = vo.getContent();
			
			// 공백 처리
			content = content.replace("  ", "&npsp;&nbsp;");
			// 태그 처리
			content = content.replace("<", "&lt;");
			content = content.replace(">", "&gt;");
			
			// 줄바꿈 처리 
			content = content.replace("\n", "<br>");
			
			//변경된 내용을 vo에 다시 저장
			vo.setContent(content);
		}
		
		session.close();	// 세션 종료(리소스 정리)
		return list;
	}
	
	// 비밀번호가 맞으면 true, 틀리면 false를 리턴하는 코드
	public boolean passwdCheck(int idx, String passwd) {
		// mybatis 실행 객체 생성
		SqlSession session = MybatisManager.getInstance().openSession();
		boolean result = false;
		GuestBookVO vo = new GuestBookVO();
		vo.setIdx(idx);
		vo.setPasswd(passwd);
		// 레코드가 1개 selectOne, 2개 이상 selectList
		int count = session.selectOne("guestbook.passwdCheck", vo);
		// 조건식?true일때의 값 : false일때의 값
		result = count == 1 ? true : false;
		
		session.close();	// 세션 닫기
		
		return result;
	}
	
	// 게시물 번호에 해당하는 레코드를 vo에 담아서 리턴하는 코드
	public GuestBookVO gbDetail(int idx) {
		// mybatis 실행 객체 생성
		SqlSession session = MybatisManager.getInstance().openSession();
		GuestBookVO vo = new GuestBookVO();
		vo = session.selectOne("guestbook.gbDetail", idx);
		session.close();
		
		return vo;
	}
	
	// 레코드 수정
	public void gbUpdate(GuestBookVO vo) {
		// mybatis 실행 객체 생성
		SqlSession session = MybatisManager.getInstance().openSession();
		// 네임스페이스.sql의ID
		session.update("guestbook.gbUpdate", vo);	// 매개변수는 1개만 가능
		session.commit();	// insert, update, delete 쿼리는 커밋해야 함
		session.close();	// 리소스 정리
	}
	
	// 레코드 삭제
	public void gbDelete(int idx) {
		SqlSession session = MybatisManager.getInstance().openSession();
		session.delete("guestbook.gbDelete", idx);
		session.commit();
		session.close();
	}
	
	// 레코드 삽입
	public void gbInsert(GuestBookVO vo) {
		SqlSession session = MybatisManager.getInstance().openSession();
		session.insert("guestbook.gbInsert", vo);
		session.commit();
		session.close();
	}

}
