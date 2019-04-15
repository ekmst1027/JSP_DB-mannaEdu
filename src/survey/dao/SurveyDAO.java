package survey.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import sqlmap.MybatisManager;
import survey.vo.SurveyResultVO;
import survey.vo.SurveySummaryVO;
import survey.vo.SurveyVO;

public class SurveyDAO {
	// 설문문제를 vo에 저장하여 리턴하는 코드
	public SurveyVO viewQuestion(int survey_idx) {
		// sql 실행을 위한 mybatis 객체 생성
		SqlSession session = MybatisManager.getInstance().openSession();
		// survey 네임스페이스의 view_question 쿼리 실행
		// 실행결과가 vo에 리턴됨
		SurveyVO vo = session.selectOne("survey.view_question", survey_idx);
		session.close();	// 리소스 정리
		return vo;
	}
	
	public void insertSurvey(SurveyResultVO vo) {
		SqlSession session = MybatisManager.getInstance().openSession();
		session.insert("survey.insert_survey", vo);	// 레코드 추가
		session.commit();	// 수동 커밋
		session.close();	// mybatis 세션 닫기
	}
	
	public List<SurveySummaryVO> listSummary(int survey_idx) {
		SqlSession session = MybatisManager.getInstance().openSession();
		List<SurveySummaryVO> items = session.selectList("survey.list_summary", survey_idx);
		session.close();
		return items;
	}
}
