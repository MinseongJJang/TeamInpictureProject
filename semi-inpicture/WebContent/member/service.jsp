<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style type="text/css">
.asd td{
font-size:35px;
}
</style>
<div class="main">
	<table class="asd">
		<tr>
			<td><img
				src="${pageContext.request.contextPath}/temp_images/logo.jpg"><br></td>
			<td>
				<h2>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;팀 인픽쳐</h2>
			</td>
		</tr>
		<tr>
			<td>
				<h6>Technology</h6>
				<p>인픽쳐만의 그림 경매 시스템을 구축하여 더욱 만족도 높은 서비스를 제공</p>

			</td>
			<td rowspan="4">
			<img src="${pageContext.request.contextPath}/temp_images/groupPhoto.jpg"
			width="300" height="400">
			</td>
		</tr>
		<tr>
			<td>
				<h6>Invaluable</h6>
				<p>
					지불한 금액의 가치를 넘어서는 경매품을 제공하여 높은 만족도를 제공
				</p>
			</td>
		</tr>
		<tr>
			<td>
				<h6>Service</h6>
				<p>회원들과의 지속적인 피드백과 정보 수집을 통해 원하는 정보와 서비스 제공</p>
			</td>
		</tr>
		<tr>
			<td>
				<h6>Community</h6>
				<p>많은 인디 작가와의 제휴를 통한 작가 활동을 권유하고, 회원들과의 활발한 교류의 장 제공</p>
			</td>
		</tr>
	</table>
</div>