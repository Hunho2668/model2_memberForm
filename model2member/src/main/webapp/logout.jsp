<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
    // 세션 무효화 (로그아웃 처리)
    session.invalidate();

    // 로그아웃 메시지를 출력하고 메인 페이지 또는 로그인 페이지로 리다이렉트
    out.println("<script>");
    out.println("alert('로그아웃 되었습니다.');");
    out.println("location.href='" + request.getContextPath() + "/main.do';");  // 메인 페이지로 리다이렉트
    out.println("</script>");
%>
