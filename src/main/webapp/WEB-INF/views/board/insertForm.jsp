<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board insertForm</title>
<link rel="stylesheet" type="text/css" href="/resources/css/style.css">
</head>
<body>

<!-- 헤더 --> 
<%@ include file="../header/header.jsp" %>

<!-- 내용 --> 
<div class="insertForm-container">

<form id="insertForm" action="" method="post">
	<input type="hidden" name="action" id="action" value="">
	<input type="hidden" id="member_id" name="member_id" value="${loginVO.member_id}">
	
<div class="insertForm-inner-container">
	<div class="insertForm-top-container"><h1>Writing</h1></div>
	<div class="insertForm-title-container">
		<div class="insertForm-title">Title</div>
		<input class="insertForm-title-input" type="text" id="btitle" name="btitle" placeholder="제목을 입력해주세요.">
	</div>
	<div class="insertForm-content-container">
		<div class="insertForm-content">
			<div class="insertForm-content-area">Content</div>
		</div>
		<div class="insertForm-content-input-container">
			<textarea class="insertForm-content-input" id="bcontent" name="bcontent" placeholder="내용을 입력해주세요."></textarea>
		</div>
	</div>
	<div class="insertForm-btn-container">
		<a href="javascript:jsInsert()" class="insertForm-btn">등록</a>
	</div>
</div>
</form>

</div>

<!-- javascript -->
<script type="text/javascript">
function jsInsert() {
	if (confirm("등록하시겠습니까?")) {
		myFetch("insert", "insertForm", json => {
			if (json.status == 0) {
				// 성공
				alert(json.statusMessage);
				location = "list";
			} else {  
				alert(json.statusMessage);
			}
		});
	}
}
</script>

</body>
</html>