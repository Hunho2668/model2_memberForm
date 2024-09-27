package controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.Action;
import service.ActionForward;
import service.MemberInsert;

@WebServlet("*.do")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// doGet(), doPost() 메소드에서 공통적인 작업을 처리하는 메소드
	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());

		System.out.println("requestURI:" + requestURI); // /model2member/MemberInsert.do
		System.out.println("contextPath:" + contextPath); // /model2member
		System.out.println("command:" + command); // /MemberInsert.do

		Action action = null;
		ActionForward forward = null;

		// 회원 가입
		if (command.equals("/MemberInsert.do")) {
			try {
				action = new MemberInsert();
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} // 포워딩 처리
		if (forward != null) {
			if (forward.isRedirect()) { // redirect 방식으로 포워딩
				response.sendRedirect(forward.getPath());
			} else { // dispatcher 방식으로 포워딩
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}

		}
	} // doProcess() end

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("get");

		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("post");

		doProcess(request, response);
	}
}