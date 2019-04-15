package survey.vo;

public class SurveyResultVO {
	private int answer_idx;	// 응답 일련번호
	private int survey_idx;	// 문제번호
	private int num;	// 응답번호 
	
	// Getters and Setters
	public int getAnswer_idx() {
		return answer_idx;
	}
	public void setAnswer_idx(int answer_idx) {
		this.answer_idx = answer_idx;
	}
	public int getSurvey_idx() {
		return survey_idx;
	}
	public void setSurvey_idx(int survey_idx) {
		this.survey_idx = survey_idx;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
	// toString() method 
	@Override
	public String toString() {
		return "SurveyResultVO [answer_idx=" + answer_idx + ", survey_idx=" + survey_idx + ", num=" + num + "]";
	}

}	// finish SurveyResultVO class
