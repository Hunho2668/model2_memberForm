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
import service.IdCheck;
import service.Login;
import service.Main;
import service.MemberDelete;
import service.MemberInsert;
import service.MemberUpdate;
import service.Update;

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
			// ID중복검사(ajax)
		} else if (command.equals("/IdCheck.do")) {
			try {
				action = new IdCheck();
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 회원가입 폼
		} else if (command.equals("/MemberForm.do")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./memberform.jsp");
			// 로그인 폼
		} else if (command.equals("/LoginForm.do")) { // "/" 추가
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./loginform.jsp");
		}
		// 로그인 폼
		else if (command.equals("/Login.do")) {
			try {
				action = new Login();
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// 메인 페이지로 이동
		} else if (command.equals("/main.do")) {
			try {
				action = new Main(); // Main 서비스 클래스 호출
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 로그아웃 처리
		else if (command.equals("/logout.do")) {
			request.getSession().invalidate(); // 세션 무효화
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath(request.getContextPath() + "/main.do"); // 로그아웃 후 로그인 페이지로 리다이렉트

		}
		// 회원 정보 불러오는 컨트롤
		else if (command.equals("/UpdateMember.do")) {
			try {
				action = new Update(); // Update 서비스 클래스 호출
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// 실제 회원 정보 업데이트 처리
		else if (command.equals("/MemberUpdateAction.do")) {
			try {
				action = new MemberUpdate(); // MemberUpdate 서비스 클래스 호출 (회원 정보 업데이트)
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
		//회원 탈퇴 처리
		else if (command.equals("/DeleteMember.do")) {
			try {
				action = new MemberDelete(); // MemberDelete.java 호출
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// 포워딩 처리
		if (forward != null)

		{
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