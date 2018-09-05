<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style type="text/css">
 .a td{
 	width:100px;
 	height:100px;
 	align:center;
 	margin:auto;
 }

</style>
<div class="main_slider" align="center">
			<div class="main_slider_content">
				<h2>작품 detail</h2>
				<table class="table a">
					<tr>
						<td rowspan="3"></td>
						<td rowspan="3"><img  src="${pageContext.request.contextPath}/art_images/${requestScope.dto.artMainPic}" width="400px" height="400px"></td>
					    <td>제목</td>
					    <td colspan="2" align="center">${requestScope.dto.artTitle}</td>
					    <td rowspan="3"></td>
                    </tr>
                    <tr>
					    <td>아이디</td>
						<td colspan="2" align="center">${requestScope.dto.inpictureMemberDTO.id}</td>
					</tr>
					<tr>
						<td>그림 설명</td>
						<td colspan="2" align="center">${requestScope.dto.artContent}</td>
					</tr>
				</table>
				<c:if test="${requestScope.dto.inpictureMemberDTO.id==sessionScope.mvo.id}">
				<form action="${pageContext.request.contextPath}/front">
					<input type="hidden" name="artNo" value="${requestScope.dto.artNo}">
					<input type="hidden" name="command" value="DeleteMyArt">
					<input type="submit" value="삭제">
				</form>
				<form action="${pageContext.request.contextPath}/front" method="post">
					<input type="hidden" name="artNo" value="${requestScope.dto.artNo}">
					<input type="hidden" name="command" value="UpdateMyArtForm">
					<input type="submit" value="수정">
				</form>
				</c:if>
			</div>
</div>
