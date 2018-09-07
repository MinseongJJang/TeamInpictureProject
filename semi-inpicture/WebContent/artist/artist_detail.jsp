<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style type="text/css">
	.afd{
		margin-bottom:500px;
	}
 .a td2{
 	width:300px;
 	height:50px;
 	align:center;
 	margin:auto;
 }
 #div3{
 	margin-bottom:50px;
 }
 .a td{
 	width:100px;
 	height:100px;
 	align:center;
 	margin:auto;
 	font-size:17px;
 	font-weight:bold;
 }
 .amam{
 	margin-top:30px;
 	margin-bottom:70px;
 }
 .table4 td,.table4 th{
	padding: .75rem;
    vertical-align: middle;
    border-top: 1px solid #e9ecef;
    border-collapse: collapse;
}
.table4{
	    border-collapse: collapse;
}
.th,.td{

	display: table-cell;
}
</style>
<%-- <div class="main2" id="div3" align="center">
	<div class="main_slider_content">
		<h2>작가 detail</h2>
		<table class="table">
			<tr>
				<td rowspan="3">
				<img src="${pageContext.request.contextPath }/artist_images/${requestScope.adto.artist_main_pic}" width="400px" height="400px"></td>
				<td width="40%">이름</td>
				<td width="60%">${requestScope.adto.inpictureMemberDTO.name}</td>
			</tr>
			<tr>
				<td>메일</td>
				<td>${requestScope.adto.inpictureMemberDTO.email}</td>
			</tr>
			<tr>
				<td id="c_555">작가 소개</td>
				<td>
					${requestScope.adto.artistIntro}
				</td>
			</tr>
		</table>
		<hr>
	  		<c:import url="${requestScope.url2 }"/>
	</div>
</div> --%>
<c:choose>
<c:when test="${sessionScope.mvo!=null}">
<div class="amam">
<div class="main_slider" align="center">
			<div class="main_slider_content">
				<h2>작품 detail</h2>
				<table class="table4 a">
					<tr>
						<td rowspan="3"></td>
						<td rowspan="3"><img src="${pageContext.request.contextPath }/artist_images/${requestScope.adto.artist_main_pic}" width="400px" height="400px"></td>
					    <td>이름</td>
					    <td colspan="2" align="center" style=font-weight:normal>${requestScope.adto.inpictureMemberDTO.name}</td>
					    <td rowspan="3"></td>
                    </tr>
                    <tr>
					    <td>메일</td>
						<td colspan="2" align="center" style=font-weight:normal>${requestScope.adto.inpictureMemberDTO.email}</td>
					</tr>
					<tr>
						<td>작가소개</td>
						<td colspan="2" align="center" style=font-weight:normal>${requestScope.adto.artistIntro}</td>
					</tr>
				</table>
				<div></div>
				<c:import url="${requestScope.url2 }"/>	
				
			</div>
</div>
</div>
</c:when>
<c:otherwise>
		<script>
			location.href="${pageContext.request.contextPath}/member/need_login.jsp";
		</script>
	</c:otherwise>
</c:choose>
