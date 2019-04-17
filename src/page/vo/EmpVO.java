package page.vo;

public class EmpVO {
	private int empno;
	private String ename;
	
	// Getters and Setters
	public int getEmpno() {
		return empno;
	}
	public void setEmpno(int empno) {
		this.empno = empno;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	
	
	// toString() method
	@Override
	public String toString() {
		return "EmpVO [empno=" + empno + ", ename=" + ename + "]";
	}

}
