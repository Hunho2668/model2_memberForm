package service;

import java.io.PrintWriter;

import dao.MemberDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class IdCheck implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("IdCheck");
		// 출력 스트림 객체 생성
		PrintWriter out = response.getWriter();
		
		String id = request.getParameter("id");
		System.out.println("id:" + id);

		MemberDAO dao = MemberDAO.getInstance();
		int result = dao.idcheck(id); // id중복검사
		System.out.println("result :" + result);
		
		//웹브라우저에 출력되는 값이 callback 함수로 처리된다.
		out.println(result);
		
		
		return null;
	}

}
