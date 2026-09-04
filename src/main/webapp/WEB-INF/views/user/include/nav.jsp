<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<link href="<c:url value='/resources/css/user/include/nav.css'/>" rel="stylesheet" type="text/css">

<nav>
	
	<div id="nav_wrap">
		
		<%
			Object object = session.getAttribute("loginedUserMemberId");
			if (object == null) {
		%>
			<div class="menu">
			
				<ul>
					<li><a href="<c:url value='/user/member/createAccountForm'/>">회원가입</a></li>			
					<li><a href="<c:url value='/user/member/loginForm'/>">로그인</a></li>					
				</ul>
				
			</div>
		<%
			} else {
		%>
			<div class="menu">
			
				<ul>	
					<li><a href="<c:url value='/user/member/logoutConfirm'/>">로그아웃</a></li>			
					<li><a href="<c:url value='/user/member/modifyAccountForm'/>">계정수정</a></li>			
					<li><a href="#none">나의책장</a></li>			
				</ul>
				
			</div>
		<%
			}
		%>
		
		
		
		<div class="search">
			
			<form 
				action=""
				name=""
				method="get">
				
				<input type="text" name="b_name" placeholder="Enter the name of the book you are looking for.">
				<input type="button" value="search">
			</form>
			
		</div>
		
	</div>
	
</nav>