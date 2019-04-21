package shop.vo;

public class MemberVO {
	private String userid;
	private String passwd;
	private String name;
	
	// Getters and Setters
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	// toString() method
	@Override
	public String toString() {
		return "MemberVO [userid=" + userid + ", passwd=" + passwd + ", name=" + name + "]";
	}

}
