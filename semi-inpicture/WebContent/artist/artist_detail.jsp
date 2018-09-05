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

	<div class="main1">
	<h2>작가 정보</h2>
			<div class="content">
				<form method="post" action="${pageContext.request.contextPath}/front" enctype="multipart/form-data">
				<input type="hidden" name="command" value="ApplyArtist">
					<table class="table" style="border-bottom:1px solid #dbdbdb" style="border-top:1px solid #dbdbdb" >
							<tr>
								<td>이름</td>
								<td colspan="2">${requestScope.adto.inpictureMemberDTO.name}</td>								
							</tr>
							<tr>
								<td colspan="3"></td>
							</tr>
							<tr>
								<td>메일</td>
								<td colspan="2">${requestScope.adto.inpictureMemberDTO.email}</td>								
							</tr>
							<tr>
								<td colspan="3"></td>
							</tr>
							<tr>
								<td>작가 소개</td>
								<td colspan="2">${requestScope.adto.artistIntro}</td>	
							</tr>							
						</table>
						<hr>
						  <c:import url="${requestScope.url2 }"/>
				</form>
		</div>
</div>

