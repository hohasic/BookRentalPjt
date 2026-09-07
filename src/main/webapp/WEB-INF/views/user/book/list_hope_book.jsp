<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="../../include/title.jsp" />

<link href="<c:url value='/resources/css/user/list_hope_book.css'/>" rel="stylesheet" type="text/css">

</head>
<body>

	<jsp:include page="../../include/header.jsp" />
	
	<jsp:include page="../include/nav.jsp" />
	
	<section>
	
		<div id="section_wrap">
			
			<div class="word">
				<h3>HOPE BOOKS</h3>
			</div>
			
			<div class="list_hope_book">
				
				<table>
					<thead>
						<tr>
							<th>도서명</th>
							<th>저자</th>
							<th>발행처</th>
							<th>발행연도</th>
							<th>요청일</th>
							<th>요청 수정일</th>
							<th>처리상태</th>
							<th>처리상태 수정일</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${hopeBookDtos}">
							<tr>
								<td>도서명</td>
								<td>저자</td>
								<td>발행처</td>
								<td>발행연도</td>
								<td>요청일</td>
								<td>요청 수정일</td>
								<td>처리상태</td>
								<td>처리상태 수정일</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				
			</div>
			
		</div>
	
	</section>
	
	<jsp:include page="../../include/footer.jsp" />

</body>
</html>