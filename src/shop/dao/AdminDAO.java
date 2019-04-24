package shop.dao;

import org.apache.ibatis.session.SqlSession;

import shop.vo.MemberVO;
import sqlmap.MybatisManager;

public class AdminDAO {
	
	public String loginCheck(MemberVO vo) {
		SqlSession session = MybatisManager.getInstance().openSession();
		String name = session.selectOne("admin.login_check", vo);
		session.close();
		return name;
	}

}
