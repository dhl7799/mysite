<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/guestbook.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="guestbook" class="delete-form">
			<h1>정말 삭제하시겠습니까?</h1>
				<form method="post" action="${pageContext.request.contextPath }/board/delete/${no }">
					<input type='hidden' name="no" value='${no }'>
					<input type="submit" value="확인" style="width: 100px; margin-left: 100px;margin-right: auto;">
				</form>
				<a href="${pageContext.request.contextPath }/board/list/1">돌아가기</a>
			</div>
			<c:if test="${fail }">
				<h4>비밀번호가 일치하지 않습니다</h4>
			</c:if>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>