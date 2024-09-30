package service;

import dao.MemberDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class MemberDelete implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");

		// 세션에 id가 없을 경우 처리
		if (id == null || id.isEmpty()) {
			// 잘못된 접근 처리
			ActionForward forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath(request.getContextPath() + "/main.do");
			return forward;
		}

		// DAO 호출하여 회원 탈퇴 처리
		MemberDAO dao = MemberDAO.getInstance();
		int result = dao.delete(id);

		// 결과 처리
		ActionForward forward = new ActionForward();
		if (result == 1) {
			// 탈퇴 성공 시 세션 무효화 및 메인 페이지로 이동
			session.invalidate();
			response.setContentType("text/html; charset=utf-8");
			response.getWriter().print("<script>alert('회원 탈퇴가 완료되었습니다.'); location.href='" + request.getContextPath()
					+ "/main.do';</script>");
		} else {
			// 탈퇴 실패 시 오류 메시지 출력
			response.setContentType("text/html; charset=utf-8");
			response.getWriter().print("<script>alert('회원 탈퇴에 실패하였습니다.'); history.go(-1);</script>");
		}

		return null;
	}
}
