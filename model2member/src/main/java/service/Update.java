package service;

import dao.MemberDAO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model2.MemberDTO;

public class Update implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		System.out.println("UpdateMember");

		// 세션에서 현재 사용자 ID 가져오기
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");

		// 2. DAO 객체를 생성하고, 업데이트 메서드를 호출
		MemberDAO dao = MemberDAO.getInstance();
		MemberDTO member = dao.getMember(id); // 1명의 상세정보 구하기
		System.out.println("수정폼 :" + member);

		String hobby = member.getHobby();
		String [] h = hobby.split("-"); 
		
		//공유 설정
		request.setAttribute("member", member);
		request.setAttribute("h", h);
		
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/updateform.jsp");
		
		return forward;

	}
}
