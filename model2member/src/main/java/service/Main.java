package service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class Main implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ActionForward forward = new ActionForward();

        System.out.println("mainpage");

        // 세션에서 현재 사용자 ID 가져오기
        HttpSession session = request.getSession();
        String id = (String) session.getAttribute("id");
        
        // id 값을 request에 설정해서 JSP에서 사용할 수 있도록 전달
        request.setAttribute("id", id);
        
        // 메인 페이지로 포워딩 설정
        forward.setRedirect(false);  // 디스패처 방식으로 포워딩 (데이터를 전달할 수 있음)
        forward.setPath("main.jsp");  // 메인 페이지 경로 지정

        return forward;
    }
}
