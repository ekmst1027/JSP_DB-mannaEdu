package member;

public class MemberVO {
	private String userid;
	private String passwd;
	private String name;
	private String reg_date;
	private String address;
	private String tel;
	
	// Constructor
	public MemberVO() {
	}
	
	public MemberVO(String userid, String passwd, String name, String address, String tel) {
		super();
		this.userid = userid;
		this.passwd = passwd;
		this.name = name;
		this.address = address;
		this.tel = tel;
	}
	
	public MemberVO(String userid, String passwd, String name, String reg_date, String address, String tel) {
		super();
		this.userid = userid;
		this.passwd = passwd;
		this.name = name;
		this.reg_date = reg_date;
		this.address = address;
		this.tel = tel;
	}

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
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	// toString() method
	@Override
	public String toString() {
		return "MemberVO [userid=" + userid + ", passwd=" + passwd + ", name=" + name + ", reg_date=" + reg_date
				+ ", address=" + address + ", tel=" + tel + "]";
	}

}
