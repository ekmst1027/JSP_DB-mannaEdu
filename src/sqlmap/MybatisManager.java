package sqlmap;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

// mybatis를 이용하여 sql을 실행시키는 객체(SqlSession)를 생성하는 코드
public class MybatisManager {
	// 싱글톤 패턴(singleton pattern)
	// SqlSessionFactoryBuilder => SqlSessionFactory => SqlSession
	// SqlSession 객체 생성기
	private static SqlSessionFactory instance;
	// private 생성자
	private MybatisManager() { }
	
	public static SqlSessionFactory getInstance() {
		Reader reader = null;
		try {
			// Java Resources의 src
			reader = Resources.getResourceAsReader("sqlmap/sqlMapConfig.xml");
			// SqlSessionFactory 생성기
			instance = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(reader != null)
					reader.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return instance;
	}

}
