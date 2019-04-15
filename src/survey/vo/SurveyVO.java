package survey.vo;

public class SurveyVO {
	private int survey_idx;	// 문제 번호
	private String question;	// 문제 내용
	private String ans1;	// 답1
	private String ans2;	// 답2
	private String ans3;	// 답3
	private String ans4;	// 답4
	private String status;	// 진행상태
	
	// Getters and Setters 
	public int getSurvey_idx() {
		return survey_idx;
	}
	public void setSurvey_idx(int survey_idx) {
		this.survey_idx = survey_idx;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAns1() {
		return ans1;
	}
	public void setAns1(String ans1) {
		this.ans1 = ans1;
	}
	public String getAns2() {
		return ans2;
	}
	public void setAns2(String ans2) {
		this.ans2 = ans2;
	}
	public String getAns3() {
		return ans3;
	}
	public void setAns3(String ans3) {
		this.ans3 = ans3;
	}
	public String getAns4() {
		return ans4;
	}
	public void setAns4(String ans4) {
		this.ans4 = ans4;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	// toString() method
	@Override
	public String toString() {
		return "SurveyVO [survey_idx=" + survey_idx + ", question=" + question + ", ans1=" + ans1 + ", ans2=" + ans2
				+ ", ans3=" + ans3 + ", ans4=" + ans4 + ", status=" + status + "]";
	}

}	// finish SurveyVO class
