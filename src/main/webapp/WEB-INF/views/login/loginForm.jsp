<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login form</title>
<link rel="stylesheet" type="text/css" href="/resources/css/style.css">
</head>

<c:if test="${not empty param.err}">
<script>
	alert("아이디 또는 비밀번호가 잘못되었습니다.");
</script>	
</c:if>

<body>

<!-- 헤더 --> 
<%@ include file="../header/header.jsp" %>

<div class="login-container">
<form id="loginForm" action="" method="post" >
	<div class="login-inner-container">
		<div class="login-top-container"><h1>Login</h1></div>
		<div class="login-input-container">
    	
    		<div class="login-id">
    			<input type="text" class="login-id-input" id="user_id" name=user_id required="required" placeholder="id">
    		</div>
    		<div class="login-password">
        		<input type="password" class="login-password-input" id="user_password" name="user_password" required="required" placeholder="password">
        	</div>
        	<div class="login-autologin-container">
        		<div class="login-autologin">
        			<label for="autologin">자동로그인</label> <input type="checkbox" id="autologin" name="autologin" value="Y">
        		</div>
        	</div>    
    	</div>
    	<div class="login-btn-container">
    		<div class="login-btn-div">
        		<input type="submit" value="Login" class="login-btn">
        	</div>
        	<div class="login-signup">
        		계정이 없으신가요? <a href="member.do?action=insertForm" class="login-signup-a">회원가입</a> 
        	</div>
        	<!-- <a href="member.do?action=list" class="btn">취소</a> -->
    	</div>
    </div>         
</form>

</div>

<script type="text/javascript">
	const loginForm = document.getElementById("loginForm");
	loginForm.addEventListener("submit", e => {
		// 서버에 폼 데이터를 전송하지 않는다.
		e.preventDefault();

		myFetch("login", "loginForm", json => {
			if (json.status == 0) {
				alert(json.loginVO.user_id + "님 로그인 되었습니다.");
				location = "<c:url value='/board/list'/>";
			} else {
				alert(json.statusMessage);
			}
		});
	});
</script>

</body>
</html>