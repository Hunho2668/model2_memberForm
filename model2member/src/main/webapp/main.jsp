<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- 세션이 있는 경우 (로그인 된 경우) -->
<c:if test="${sessionScope.id != null}">
	<p>안녕하세요, ${sessionScope.id}님!</p>
	<br>
	<br>
	<p>반갑고~</p>
	<br>
	<br>
	<a href="<%=request.getContextPath()%>/UpdateMember.do">정보수정</a>
	<a href="<%=request.getContextPath()%>/logout.do">로그아웃</a>
	<a href="<%=request.getContextPath()%>/DeleteMember.do"
		onclick="return confirm('정말로 회원 탈퇴를 하시겠습니까?');">회원 탈퇴</a>
</c:if>

<!-- 세션이 없는 경우 (로그인 되지 않은 경우) -->
<c:if test="${sessionScope.id == null}">
	<p>로그인이 필요합니다.</p>
	<a href="<%=request.getContextPath()%>/MemberForm.do">회원가입</a>
	<a href="<%=request.getContextPath()%>/LoginForm.do">로그인</a>
</c:if>
