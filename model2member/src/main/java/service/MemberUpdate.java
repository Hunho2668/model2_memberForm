package service;

import java.io.PrintWriter;

import dao.MemberDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model2.MemberDTO;

public class MemberUpdate implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 인코딩 설정
		request.setCharacterEncoding("utf-8");

		// MemberDTO 객체 생성 및 폼 데이터 설정
		MemberDTO member = new MemberDTO();
		member.setId(request.getParameter("id"));
		member.setPasswd(request.getParameter("passwd"));
		member.setName(request.getParameter("name"));
		member.setJumin1(request.getParameter("jumin1"));
		member.setJumin2(request.getParameter("jumin2"));
		member.setMailid(request.getParameter("mailid"));
		member.setDomain(request.getParameter("domain"));
		member.setTel1(request.getParameter("tel1"));
		member.setTel2(request.getParameter("tel2"));
		member.setTel3(request.getParameter("tel3"));
		member.setPhone1(request.getParameter("phone1"));
		member.setPhone2(request.getParameter("phone2"));
		member.setPhone3(request.getParameter("phone3"));
		member.setPost(request.getParameter("post"));
		member.setAddress(request.getParameter("address"));
		member.setGender(request.getParameter("gender"));

		// 취미 배열을 하나의 문자열로 합침
		String[] hobby = request.getParameterValues("hobby");
		String h = String.join("-", hobby);
		member.setHobby(h);
		member.setIntro(request.getParameter("intro"));

		// DAO 객체 생성 및 업데이트 메서드 호출
		MemberDAO dao = MemberDAO.getInstance();
		int result = dao.memberupdate(member);

		// 결과에 따른 처리
		ActionForward forward = new ActionForward();
		if (result == 1) {
			System.out.println("회원 정보 수정 성공");

			// 회원정보 수정 성공 시 알림 메시지와 함께 메인 페이지로 리다이렉트
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('회원가입이 성공적으로 완료되었습니다.');");
			out.println("location.href='" + request.getContextPath() + "/main.do';"); // main.do로 이동
			out.println("</script>");
			out.close();
		}

		else {
			System.out.println("회원 정보 수정 실패");
			forward.setRedirect(false); // 실패 시 같은 페이지로 포워딩하여 오류 메시지 표시
			forward.setPath("/updateform.jsp");
		}

		return forward;
	}
}
