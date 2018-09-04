<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style type="text/css">
 .a td{
 	width:50px;
 	height:50px;
 	align:center;
 	margin:auto;
 }

</style>
<div class="main_slider" align="center">
	<div class="main_slider_content">
		<h2>작가 detail</h2>
		<table class="table a">
			<tr >
				<td rowspan="3">
				<img  src="" width="400px" height="400px"></td>
				<td>이름</td>
				<td>${requestScope.adto.inpictureMemberDTO.name}</td>
			</tr>
			<tr>
				<td>메일</td>
				<td>${requestScope.adto.inpictureMemberDTO.email}</td>
			</tr>
			<tr>
				<td>작가 소개</td>
				<td>
					${requestScope.adto.artistIntro}
				</td>
			</tr>
		</table>
		<hr>
		<c:import url="${pageContext.request.contextPath }/art/art_list"/>
	</div>
</div>
