<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/detail.css">
		<script src="<%=request.getContextPath() %>/js/jquery-3.5.1.min.js"></script>
		
	</head>
	<body>
		<div>
			<jsp:include page="/view/header.jsp"/>
		</div>
		
		<main>
			<h1>메인영역</h1>
			<!-- ///1050px/// -->
			<div class="page-board view-width">
				<!-- ///200px// -->
				<!-- //사이드메뉴위치// -->
				<jsp:include page="/view/sideMenu.jsp"/>
				<!-- //820px/// -->
				<section id="detail">
					<h1>공지사항-상세페이지</h1>
					<div>
						<div>
							<p class="page-tit">공지사항
								<span class="sub">
									컬리의 새로운 소식들과 유용한 정보들을 한곳에서 확인하세요.
								</span>
							</p>
						</div>
						
						<div class="page-view">
							<table class="detail-tit">
								<tr>
									<td class="tit">제목</td>
									<td colspan="3">${requestScope.result.subject }</td>
								<tr>
								<tr>
									<td class="tit">작성자</td>
									<td colspan="3">${result.writer }</td>
								<tr>
								<tr>
									<td class="tit">작성일</td>
									<td>${result.reg_date }</td>
									<td class="tit">조회수</td>
									<td>${result.count }</td>
								<tr>
							</table>
							<div class="content">
								${result.content }
							</div>
							<script type="text/javascript">
								$(function () {
									$("btn-list").click(function(){
										history.go(-1)
										;
									});
								});
							</script>
							<div class="btn">
								<button id="btn-list">목록</button>
								<button id="btn-update">수정</button>
								<a href="delete.bo?noForDelete=${result.no }"><button id="btn-delete">삭제</button></a>
							</div>
						</div>
					</div>
				</section>
			</div>
		</main>
		<div>
			<jsp:include page="/view/footer.jsp"/>
		</div>
	</body>
</html>