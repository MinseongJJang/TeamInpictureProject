<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="main_slider"
	style="background-image: url(images/slider_1.jpg)">
	<div class="container fill_height">
		<div class="row align-items-center fill_height">
			<div class="col">
				<div class="main_slider_content">
					<h1>작가 작품 목록</h1>
					<table class="table">
						<tr>
							<c:forEach var="avo" items="${requestScope.list }">
								<td><img src="${pageContext.request.contextPath }/art_images/${avo.artMainPic }" class="img-art"></td>
							</c:forEach>
							</tr>
							<tr>
							<c:forEach var="avo" items="${requestScope.list }">
								<td>${avo.artTitle }</td>
							</c:forEach>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>



