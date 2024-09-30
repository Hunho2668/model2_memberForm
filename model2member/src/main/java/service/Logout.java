package service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Logout implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 세션 무효화
		HttpSession session = request.getSession();
		session.invalidate();

		// 로그아웃 후 메인 페이지로 리다이렉트
		ActionForward forward = new ActionForward();
		forward.setRedirect(true); // 리다이렉트 방식으로 이동
		forward.setPath(request.getContextPath() + "/main.do"); // 로그아웃 후 메인 페이지로 이동

		return forward;
	}
}
