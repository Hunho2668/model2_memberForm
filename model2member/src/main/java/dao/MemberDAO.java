//DAO(Data Access Object)

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import model2.MemberDTO;

public class MemberDAO {

	// 싱글톤 : 객체 생성을 한번만 수행하는 것.
	private static MemberDAO instance = new MemberDAO();

	public static MemberDAO getInstance() {
		return instance;

	}

	// 컨넥션풀에서 컨넥션 을 구해오는 메소드
	private Connection getConnection() throws Exception {
		Context init = new InitialContext();
		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/orcl");
		return ds.getConnection();

	}

	// 회원가입
	public int insert(MemberDTO member) {
		int result = 0;

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			// 컨넥션풀에서 컨넥션을 구해온다.
			con = getConnection();
			String sql = "insert into model2member ";
			sql += " values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,sysdate)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPasswd());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getJumin1());
			pstmt.setString(5, member.getJumin2());
			pstmt.setString(6, member.getMailid());
			pstmt.setString(7, member.getDomain());
			pstmt.setString(8, member.getTel1());
			pstmt.setString(9, member.getTel2());
			pstmt.setString(10, member.getTel3());
			pstmt.setString(11, member.getPhone1());
			pstmt.setString(12, member.getPhone2());
			pstmt.setString(13, member.getPhone3());
			pstmt.setString(14, member.getPost());
			pstmt.setString(15, member.getAddress());
			pstmt.setString(16, member.getGender());
			pstmt.setString(17, member.getHobby());
			pstmt.setString(18, member.getIntro());

			result = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	// 로그인 (회원인증 처리 )
	public int memberCheck(MemberDTO member) {
		int result = 0;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();

			String sql = "select * from model2member where id=?";
			// 프리페어드 객체
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			rs = pstmt.executeQuery(); // selectSQL문 실행

			if (rs.next()) {
				if (rs.getString("passwd").equals(member.getPasswd())) {
					result = 1; // id,passwd 모두 일치시(회원인증 성공)
				} else {
					result = -1; // 비번 불일치
				}

			} else {
				result = -2; // id 불일치
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public MemberDTO getMember(String id) {
		MemberDTO member = new MemberDTO();
		// 로그 추가
		System.out.println("getMember 메서드 호출됨, ID=" + id);
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();

			// 수정: SQL 구문 오류 수정 ("id?=" 대신 "id=?")
			String sql = "select * from model2member where id=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery(); // select SQL문 실행

			if (rs.next()) { // 조건식을 만족하는 데이터 1개를 가져온다.
				member.setId(rs.getString("id"));
				member.setPasswd(rs.getString("passwd"));
				member.setName(rs.getString("name"));
				member.setJumin1(rs.getString("jumin1"));
				member.setJumin2(rs.getString("jumin2"));
				member.setMailid(rs.getString("mailid"));
				member.setDomain(rs.getString("domain"));
				member.setTel1(rs.getString("tel1"));
				member.setTel2(rs.getString("tel2"));
				member.setTel3(rs.getString("tel3"));
				member.setPhone1(rs.getString("phone1"));
				member.setPhone2(rs.getString("phone2"));
				member.setPhone3(rs.getString("phone3"));
				member.setPost(rs.getString("post"));
				member.setAddress(rs.getString("address"));
				member.setGender(rs.getString("gender"));
				member.setHobby(rs.getString("hobby"));
				member.setIntro(rs.getString("intro"));

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return member;
	}

	// 회원정보수정
	public int memberupdate(MemberDTO member) {

		Connection con = null;
		PreparedStatement pstmt = null;

		int result = 0;

		try {

			con = getConnection();

			// SQL 쿼리 수정: 'WHERE'절 구문 오류 수정
			String sql = "UPDATE model2member SET name=?, jumin1=?, jumin2=?, mailid=?, domain=?, tel1=?,";
			sql += "tel2=?, tel3=?, phone1=?, phone2=?, phone3=?, post=?, address=?,";
			sql += "gender=?, hobby=?, intro=? WHERE id=?"; // 쉼표 대신 공백 사용

			pstmt = con.prepareStatement(sql);

			// PreparedStatement에 값 설정
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getJumin1());
			pstmt.setString(3, member.getJumin2());
			pstmt.setString(4, member.getMailid());
			pstmt.setString(5, member.getDomain());
			pstmt.setString(6, member.getTel1());
			pstmt.setString(7, member.getTel2());
			pstmt.setString(8, member.getTel3());
			pstmt.setString(9, member.getPhone1());
			pstmt.setString(10, member.getPhone2());
			pstmt.setString(11, member.getPhone3());
			pstmt.setString(12, member.getPost());
			pstmt.setString(13, member.getAddress());
			pstmt.setString(14, member.getGender());
			pstmt.setString(15, member.getHobby());
			pstmt.setString(16, member.getIntro());
			pstmt.setString(17, member.getId());

			result = pstmt.executeUpdate(); // update SQL문 실행

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	// 회원 탈퇴
	public int delete(String id) {
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = getConnection();

			String sql = "DELETE FROM model2member WHERE id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			result = pstmt.executeUpdate(); // delete SQL문 실행

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	// ID중복검사(ajax)
	public int idcheck(String id) {
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getConnection();

			String sql = "select * from model2member where id=?";
			pstmt = con.prepareStatement(sql); // PreparedStatement 초기화
			pstmt.setString(1, id);
			rs = pstmt.executeQuery(); // select SQL문 실행
			if (rs.next()) {
				result = 1; // 중복 ID
			} else {
				result = -1; // 사용 가능한 ID
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	// 로그인 (회원인증)
	public int memberAuth(String id, String passwd) {
		int result = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();

			// 1. 먼저 ID만 확인하는 쿼리
			String sql = "select passwd from model2member where id=?";
			pstmt = con.prepareStatement(sql); // PreparedStatement 초기화
			pstmt.setString(1, id);
			rs = pstmt.executeQuery(); // select SQL문 실행

			if (rs.next()) {
				// ID가 존재할 때, 비밀번호 확인
				String dbPasswd = rs.getString("passwd");
				if (dbPasswd.equals(passwd)) {
					result = 1; // ID와 비밀번호가 일치함
				} else {
					result = 0; // ID는 맞지만 비밀번호가 틀림
				}
			} else {
				result = -1; // ID가 존재하지 않음
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

}