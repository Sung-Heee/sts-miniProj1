<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<header>
    <div class="header-container">

        <div class="logo-container">
            <a href="<c:url value='/'/>" class="logo">logo</a>
        </div>

        <div class="menu-container">
<%--            &lt;%&ndash; 만약 로그인한 아이디가 "admin"이라면 &ndash;%&gt;--%>
<%--            <c:if test="${not empty sessionScope.loginVO and sessionScope.loginVO.userId eq 'admin'}">--%>
<%--                <a href="/" class="menu-btn">Company</a>--%>
<%--                <a href="board/list" class="menu-btn">Board</a>--%>
<%--                <a href="member/list" class="menu-btn">Member</a>--%>
<%--            </c:if>--%>
<%--            &lt;%&ndash; 관리자가 아닌 경우에는 "Company"와 "Board"만 표시 &ndash;%&gt;--%>
<%--            <c:if test="${empty sessionScope.loginVO or sessionScope.loginVO.userId ne 'admin'}">--%>
<%--                <a href="/" class="menu-btn">Company</a>--%>
<%--                <a href="board/list" class="menu-btn">Board</a>--%>
<%--            </c:if>--%>

                <a href="/" class="menu-btn">Company</a>
                <a href="<c:url value='/board/list'/>" class="menu-btn">Board</a>
                <a href="<c:url value='/member/list'/>" class="menu-btn">Member</a>
        </div>

        <div class="sign-container">
            <%-- 세션 스코프에 있는 loginVO 변수가 비어있는지 확인하여 로그인 여부를 판단. --%>
            <c:choose>
                <c:when test="${empty sessionScope.loginVO}">
                    <!-- 로그인 되어 있지 않은 경우 -->
                    <a href="<c:url value='/member/loginForm'/>" class="underline-btn">Login</a>
                    <a href="<c:url value='/member/insertForm'/>" class="square-btn">Sign Up</a>
                </c:when>
                <c:otherwise>
                    <!-- 로그인 되어 있는 경우 -->
                    <span>${loginVO.user_id}님</span>
                    <%-- 만약 로그인한 아이디가 "admin"이라면 로그아웃 링크를 표시합니다. --%>
                    <c:if test="${loginVO.user_id eq 'admin'}">
                        <a href="javascript:jsLogout()" class="square-btn">Logout</a>
                    </c:if>
                    <c:if test="${loginVO.user_id ne 'admin'}">
                        <a href="javascript:jsLogout()" class="underline-btn">Logout</a>
                        <a href="member/mypage?memberId=${loginVO.member_id}" class="square-btn">MyPage</a>
                    </c:if>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</header>

<script type="text/javascript" src="/resources/js/common.js"></script>
<script type="text/javascript">

    // 로그아웃
    function jsLogout() {
        if (confirm("로그아웃 하시겠습니까?")) {
            myFetch("member.do", {action:"logout"}, json => {
                if (json.status == 0) {
                    alert(json.statusMessage);
                    location = "main.jsp";
                } else {
                    alert(json.statusMessage);
                }
            });
        }
    }

</script>
