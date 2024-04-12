<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>member list</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/style.css">
</head>
<body>

<!-- 헤더 -->
<%@ include file="../header/header.jsp" %>

<div class="list-container">
    <!-- <form id="listForm" action="member.do" method="post">
        <input type="hidden" id="action" name="action" value="view">
        <input type="hidden" id="userId" name="userId" >
    </form> -->

    <form id="searchForm" action="list" method="post" >
        <input type="hidden" name="action" value="list">
        <div class="list-inner-container">
            <div class="list-top-menu">
                <!-- 사용자 추가 대신 회원가입 -->
                <!-- <div class="insert-container">
                    <a href="member.do?action=insertForm" class="insert-btn">+ 사용자 추가</a>
                </div> -->
                <div class="select-searchbar-container">
                    <div class="select-container">
                        <select id="size" name="size" >
                            <c:forEach var="size" items="${sizes}">
                                <option value="${size.code_id}" ${pageRequestVO.size == size.code_id ? 'selected' : ''} >${size.code_name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="searchbar-container">
                        <div class="searchbar">
                            <input type="text" placeholder="제목을 입력해주세요." id="searchKey" name="searchKey" value="${param.searchKey}" class="searchbar-input">
                            <input type="submit" value="검색" class="searchbar-a">
                        </div>
                    </div>
                </div>
            </div>

            <div class="list">
                <div class="list-top-content">
                    <div class="writer-area">아이디</div>
                    <div class="writer-area">이름</div>
                    <div class="writer-area">나이</div>
                    <div class="writer-area">주소</div>
                    <div class="writer-area">핸드폰</div>
                    <div class="writer-area">성별</div>
                    <c:if test="${loginVO.user_id eq 'admin'}">
                    <div class="admin-delete-btn"></div>
                    </c:if>
                </div>
                <c:forEach var="member" items="${pageResponseVO.list}">
                    <div class="list-content">
                        <div class="writer-area">${member.user_id}</div>
                        <div class="writer-area">${member.user_name}</div>
                        <div class="writer-area">${member.user_age}</div>
                        <div class="writer-area">${member.user_address}</div>
                        <div class="writer-area">${member.user_phone}</div>
                        <div class="writer-area">${member.user_sex}</div>
                        <c:if test="${loginVO.user_id eq 'admin'}">
                            <div class="admin-delete-btn"><a href="javascript:jsDelete(${member.member_id})">삭제</a></div>
                        </c:if>
                    </div>
                </c:forEach>

            </div>
        </div>

    </form>

    <!--  페이지 네비게이션 바 출력  -->
    <div class="float-end">
        <ul class="pagination flex-wrap">
            <c:if test="${pageResponseVO.prev}">
                <li class="page-item">
                    <a class="page-link" data-num="${pageResponseVO.start -1}">이전</a>
                </li>
            </c:if>

            <c:forEach begin="${pageResponseVO.start}" end="${pageResponseVO.end}" var="num">
                <li class="page-item ${pageResponseVO.pageNo == num ? 'active':''} ">
                    <a class="page-link"  data-num="${num}">${num}</a>
                </li>
            </c:forEach>

            <c:if test="${pageResponseVO.next}">
                <li class="page-item">
                    <a class="page-link"  data-num="${pageResponseVO.end + 1}">다음</a>
                </li>
            </c:if>
        </ul>

    </div>
</div>

<form id="listForm" name="listForm" action="member.do" method="post">
    <input type="hidden" id="delete_action" name="action" value="">
    <input type="hidden" id="member_id" name="member_id" value="">
</form>

<script type="text/javascript">
    function jsDelete(member_id) {
        if(confirm("사용자를 삭제하시겠습니까?")) {
            document.getElementById("delete_action").value = "delete";
            document.getElementById("member_id").value = member_id;
            myFetch("member.do", "listForm", json => {
                if (json.status == 0) {
                    alert(json.statusMessage);
                    location="member.do?action=list";
                } else {
                    alert(json.statusMessage);
                }
            })
        }
    }

</script>
</body>
</html>