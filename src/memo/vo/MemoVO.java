package memo.vo;

public class MemoVO {
	private int idx;
	private String writer;
	private String memo;
	private String post_date;
	
	public MemoVO() {
	}
	
	public MemoVO(String writer, String memo) {
		this.writer = writer;
		this.memo = memo;
	}

	// Getters and Setters
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getPost_date() {
		return post_date;
	}
	public void setPost_date(String post_date) {
		this.post_date = post_date;
	}
	
	
	// toString() method
	@Override
	public String toString() {
		return "MemoVO [idx=" + idx + ", writer=" + writer + ", memo=" + memo + ", post_date=" + post_date + "]";
	}
	
	
}
