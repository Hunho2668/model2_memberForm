package service;

import java.io.PrintWriter;

import dao.MemberDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model2.MemberDTO;

public class MemberInsert implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("MemberInsert");

		request.setCharacterEncoding("utf-8");

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

		String[] hobby = request.getParameterValues("hobby");
		String h = "";
		for (String h1 : hobby) {
			h += h1 + "-"; // 예: "공부-게임-" 형태로 연결됨
		}
		member.setHobby(h);
		member.setIntro(request.getParameter("intro"));

		MemberDAO dao = MemberDAO.getInstance();
		int result = dao.insert(member);

		if (result == 1) {
			System.out.println("회원 가입 성공");
			
			// 회원가입 성공 시 알림 메시지와 함께 메인 페이지로 리다이렉트
            response.setContentType("text/html; charset=utf-8");
            PrintWriter out = response.getWriter();
            out.println("<script>");
            out.println("alert('회원가입이 성공적으로 완료되었습니다.');");
            out.println("location.href='" + request.getContextPath() + "/main.do';"); // main.do로 이동
            out.println("</script>");
            out.close();
		}

		ActionForward forward = new ActionForward();
		forward.setRedirect(false);		//dispatcher 방식으로 포워딩
		forward.setPath("loginform.jsp");	//포워딩 할 파일명

		return forward;
	}
}	
		