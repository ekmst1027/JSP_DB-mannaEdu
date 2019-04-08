package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.DB;
import crypt.BCrypt;
import crypt.SHA256;

public class MemberDAO {
	
	// 회원목록을 리턴
	public List<MemberVO> memberList() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DB.dbConn();
			String sql = "SELECT userid, passwd, name, DATE_FORMAT(reg_date, '%Y-%m-%d') reg_date, address, tel FROM java.member";
			pstmt =conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			// 다음 레코드가 있으면
			while(rs.next()) {
				MemberVO vo = new MemberVO();
				// 결과셋.get자료형("필드명")
				vo.setUserid(rs.getString("userid"));
				vo.setPasswd(rs.getString("passwd"));
				vo.setName(rs.getString("name"));
				vo.setReg_date(rs.getString("reg_date"));
				vo.setAddress(rs.getString("address"));
				vo.setTel(rs.getString("tel"));
				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	// 회원 추가
	public int insert(MemberVO vo) {
		int rows = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DB.dbConn();
			String sql = "INSERT INTO member "
					+ " (userid, passwd, name, address, tel) values "
					+ "(?, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getUserid());
			pstmt.setString(2, vo.getPasswd());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getAddress());
			pstmt.setString(5, vo.getTel());
			rows = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return rows;
	}
	
	// 회원 상세정보 
	public MemberVO memberDetail(String id) {
		MemberVO vo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = DB.dbConn();
			String sql = "SELECT * FROM member WHERE userid = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				vo = new MemberVO();
				vo.setUserid(rs.getString("userid"));
				vo.setPasswd(rs.getString("passwd"));
				vo.setName(rs.getString("name"));
				vo.setReg_date(rs.getString("reg_date"));
				vo.setAddress(rs.getString("address"));
				vo.setTel(rs.getString("tel"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return vo;
	}
	
	public void update(MemberVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DB.dbConn();
			String sql = "UPDATE member set passwd=?, name=?, address=?, tel=? WHERE userid=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getPasswd());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getAddress());
			pstmt.setString(4, vo.getTel());
			pstmt.setString(5, vo.getUserid());
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void delete(String userid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DB.dbConn();
			String sql = "DELETE FROM member WHERE userid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userid);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String loginCheck(MemberVO vo) {
		String result = "";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DB.dbConn();
			String sql = "SELECT * FROM member WHERE userid=? and passwd=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getUserid());
			pstmt.setString(2, vo.getPasswd());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getString("name") + "님 환영합니다.";
			} else {
				result = "로그인 실패";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	// bcrypt 방식의 비밀번호 암호화 코드
	public int insertBcrypt(MemberVO vo) {
		int rows = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DB.dbConn();
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO MEMBER ");
			sql.append("(userid, passwd, name) VALUES ");
			sql.append("(?,?,?)");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, vo.getUserid());
			// 실행할 때마다 암호화키가 변경됨
			// BCrypt.hashpw(암호화할 평문, 암호화키)
			String passwd = 
					BCrypt.hashpw(vo.getPasswd(), BCrypt.gensalt());
			System.out.println("평문 : " + vo.getPasswd());
			System.out.println("암호화된 텍스트 : " + passwd);
			pstmt.setString(2, passwd);
			pstmt.setString(3, vo.getName());
			rows = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return rows;
	}
	
	// Bcrypt 방식의 보안 로그인
	public String loginCheckBcrypt(MemberVO vo) {
		String result ="";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DB.dbConn();
			String sql = "SELECT * FROM member where userid=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getUserid());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				String passwd = rs.getString("passwd");
				// checkpw(평문, 암호문) => 맞으면 true, 틀리면 false
				if(BCrypt.checkpw(vo.getPasswd(), passwd)) {
					result = rs.getString("name") + "님 환영합니다";
				} else {
					result = "로그인 실패...";
				}
			} else {
				result = "로그인 실패...";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	// 비밀번호 암호화(SHA256 방식)
	public void insertSha256(MemberVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DB.dbConn();
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO member ");
			sql.append("(userid, passwd, name) values ");
			sql.append("(?,?,?)");
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, vo.getUserid());
			
			SHA256 sha = SHA256.getInstance();
			// 스트링을 바이트 배열로 변환한 후 암호문 생성
			// 스트링.getBytes() : 스트링을 바이트배열로
			String shaPass = sha.getSha256(vo.getPasswd().getBytes());
			System.out.println("256 암호문 : " + shaPass);
			// 암호화된 비밀번호 입력
			pstmt.setString(2, shaPass);
			pstmt.setString(3, vo.getName());
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null)pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	// 보안 로그인(SHA256 방식)
	public String loginCheckSha256(MemberVO vo) {
		String result ="";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DB.dbConn();
			String sql = "SELECT * FROM member where userid=? passwd=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getUserid());
			SHA256 sha = SHA256.getInstance();
			String shaPass = sha.getSha256(vo.getPasswd().getBytes());
			pstmt.setString(2, shaPass);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getString("name") + "님 환영합니다";
			} else {
				result = "로그인 실패...";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(pstmt != null) pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(conn != null) conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
		
	}

}
