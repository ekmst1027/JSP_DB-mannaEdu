package page.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import page.vo.EmpVO;
import sqlmap.MybatisManager;

public class EmpDAO {
	
	public List<EmpVO> empList(int start) {
		//mybatis 실행 객체 생성
		SqlSession session = MybatisManager.getInstance().openSession();
		
		// page 네임스페이스의 id가 empList인 쿼리 실행
		List<EmpVO> items = session.selectList("page.empList", start-1);
		
		// mybatis 리소스 정리
		session.close();
		
		return items;
	}
	
	public int empCount() {
		SqlSession session = MybatisManager.getInstance().openSession();
		
		int count = session.selectOne("page.empCount");
		
		session.close();
		
		return count;
	}

}
