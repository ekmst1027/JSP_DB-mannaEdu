package survey.vo;

public class SurveySummaryVO {
	private int survey_idx;	// 문제 번호
	private int num;	// 응답 번호
	private int sum_num;	// 번호별 응답횟수
	private double rate;	// 번호별 응답률
	
	// Getters and Setters
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
	public int getSum_num() {
		return sum_num;
	}
	public void setSum_num(int sum_num) {
		this.sum_num = sum_num;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	
	// toString() method
	@Override
	public String toString() {
		return "SurveySummaryVO [survey_idx=" + survey_idx + ", num=" + num + ", sum_num=" + sum_num + ", rate=" + rate
				+ "]";
	}

}	// finish SurveySummaryVO class
