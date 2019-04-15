package memo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import memo.vo.MemoVO;
import sqlmap.MybatisManager;

public class MemoDAO {
	//mybatis에서는 리턴타입을 ArrayList가 아닌 List로 해야 함
	public List<MemoVO> listMemo(String searchkey, String search) {
		// SqlSession : mybatis로 sql을 실행시키는 객체
		SqlSession session = MybatisManager.getInstance().openSession();
		// SqlSession을 이용하여 memo 네임스페이스의 listAll 쿼리를 실행시킴
		List<MemoVO> list = null;
		// 이름 + 메모로 찾기
		if(searchkey.equals("writer_memo")) {
			list = session.selectList("memo.listAll", search);
			
		// 이름으로 찾기, 메모로 찾기
		} else {
			Map<String, String> map = new HashMap<>();
			map.put("searchkey", searchkey);
			map.put("search", search);
			list = session.selectList("memo.list", map);
		}
		
		session.close();
		return list;
	}
	
	public void insertMemo(MemoVO vo) {
		// sql 실행 객체 생성
		SqlSession session = MybatisManager.getInstance().openSession();
		// insert("네임스페이스.sql의ID", 매개변수) : 매개변수는 1개만 가능
		String writer = vo.getWriter();
		String memo = vo.getMemo();
		
		// 태그 문자 처리 replace(A, B) : A를 B로 변경
		writer = writer.replace("<", "&lt;");
		writer = writer.replace(">", "&gt;");
		memo = memo.replace("<", "&lt;");
		memo = memo.replace(">", "&gt;");
		// 공백문자 처리(공백 2칸을 &nbsp;&nbsp;으로 변경)
		writer = writer.replace("  ", "&nbsp;&nbsp;");
		memo = memo.replace("  ", "&nbsp;&nbsp;");
		vo.setWriter(writer);
		vo.setMemo(memo);
		
		session.insert("memo.insert", vo);
		session.commit();	// 수동 커밋
		session.close();	// 세션을 닫아줌(리소스 정리)
	}
	
	public MemoVO viewMemo(int idx) {
		SqlSession session = MybatisManager.getInstance().openSession();
		// 결과 레코드가 한건이므로 selectOne("select id", 매개변수)
		MemoVO vo = session.selectOne("memo.view", idx);
		session.close();
		return vo;
	}
	
	public void updateMemo(MemoVO vo) {
		// sql 실행 객체 생성
		SqlSession session = MybatisManager.getInstance().openSession();
		// 레코드 수정 update("네임스페이스.sql의ID", 입력매개변수)
		session.update("memo.update", vo);	
		session.commit();	// 레코드 추가, 수정, 삭제 때 commit() 해야 함
		session.close();	// 세션을 닫음(리소스 정리)
	}
	
	public void deleteMemo(int idx) {
		SqlSession session = MybatisManager.getInstance().openSession();
		session.delete("memo.delete", idx);
		session.commit();
		session.close();
	}
}
