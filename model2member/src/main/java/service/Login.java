package service;

import java.io.PrintWriter;
import dao.MemberDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Login implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("Login");
		ActionForward forward = new ActionForward();
		response.setContentType("text/html; charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();

		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");

		MemberDAO dao = MemberDAO.getInstance();
		int result = dao.memberAuth(id, passwd); // 회원 인증 처리

		if (result == 1) { // 회원 인증 성공
			session.setAttribute("id", id); // 세션 공유 설정
			forward.setRedirect(true);
			forward.setPath(request.getContextPath() + "/main.do"); // 경로 설정

		} else if (result == 0) { // 비밀번호가 틀림
			out.println("<script>");
			out.println("alert('비밀번호가 틀렸습니다.');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();
		} else if (result == -1) { // ID가 없음
			out.println("<script>");
			out.println("alert('아이디가 존재하지 않습니다.');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();
		}

		return forward;
	}
}
