<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.request.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="board">
				<form id="search_form" action="${pageContext.request.contextPath }/board/list/1" method="post">
					<input type="text" id="kwd" name="kwd" value="${keyword }">
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex" style = 'table-layout: fixed'>
				<tr>
						<th>번호</th>
						<th width = "25%">제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th width = "25%">작성일</th>
						<th>&nbsp;</th>
					</tr>
				<c:set var="totalcount" value="${fn:length(totallist) }" />
				<c:set var="pn" value="${pageNo}" />			
				<c:set var="count" value="${fn:length(list) }" />
				<c:forEach items="${list }" var="vo" varStatus="status" >
					<tr>
						<!-- 전체 -한페이지글수*(페이지넘버-1)-status.index -->
						<!--<td>[${count - status.index}]</td>-->
						<td>[${totalcount- 10*(pn-1)-status.index}]</td>
						<td style="text-align:left; padding-left:${vo.depth*20}px">
							<c:if test="${vo.depth>0 }">
								<img src="${pageContext.request.contextPath }/assets/images/reply.png">
							</c:if>
							<a href="${pageContext.request.contextPath }/board/view/${vo.no }">${vo.title }</a>
						</td>
						<td>${vo.user_name }</td>
						<td>${vo.hit }</td>
						<td>${vo.reg_date }</td>
						<c:choose>
							<c:when test='${authUser.no == vo.user_no}'>
								<td><a href="${pageContext.request.contextPath }/board/deleteform/${vo.no }" class="del">삭제</a></td>
							</c:when>
						</c:choose>
					</tr>				
				</c:forEach>	
				</table>
				
				<!-- pager 추가 -->
				<div class="pager">
					<ul>
						<li><a href="">◀</a></li>
						<c:choose>
							<c:when test='${totalcount%10==0}'>
								<c:set var="endNo" value="${totalcount/10}" />
							</c:when>
							<c:otherwise>
								<c:set var="endNo" value="${totalcount/10+1}" />
							</c:otherwise>
						</c:choose>
						<c:forEach begin="1" end="${endNo}" step="1" var="i">
							<li><a href="${pageContext.request.contextPath }/board/list/${i }?kwd=${keyword }">${i }</a></li>	
						</c:forEach>	
						<li><a href="">▶</a></li>						
					</ul>
				</div>					
				<!-- pager 추가 -->
				<div class="bottom">
					<a href="${pageContext.request.contextPath }/board/writeform" id="new-book">글쓰기</a>
				</div>				
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp" />
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>