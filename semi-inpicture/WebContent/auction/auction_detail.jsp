<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript"></script>
<div style="padding-top:200px;"></div>
<table class="table">
	<tr>
		<th>No &nbsp;&nbsp;${requestScope.auctionDTO.auctionApplyDTO.auctionNo }&nbsp;&nbsp;</th>
		<th>Title &nbsp;&nbsp;${requestScope.auctionDTO.auctionApplyDTO.auctionTitle }&nbsp;&nbsp;</th>
		<th>Writer &nbsp;&nbsp;${requestScope.auctionDTO.auctionApplyDTO.inpictureMemberDTO.name }&nbsp;&nbsp;</th>
	</tr>
	<tr>
		<td colspan="3"><img src="${pageContext.request.contextPath }/auction_apply_images/${requestScope.auctionDTO.auctionApplyDTO.auctionMainPic}"> </td>
	</tr>
	<tr>
		<td colspan="3"><textarea readonly="readonly" cols="100" rows="1">
		${requestScope.auctionDTO.auctionApplyDTO.auctionContent }
		</textarea></td>
	</tr>
	<tr>
		<td colspan="3">경매 시작가 &nbsp;&nbsp;${requestScope.auctionDTO.auctionApplyDTO.auctionBeginPrice }</td>
	</tr>
	<tr>
		<td colspan="3">즉시 구매가 &nbsp;&nbsp;${requestScope.auctionDTO.auctionApplyDTO.auctionPromptlyPrice }</td>
	</tr>
	<tr>
		<td colspan="3">경매 시작시간 &nbsp;&nbsp;${requestScope.auctionDTO.auctionApplyDTO.auctionBeginTime}</td>
	</tr>
	<tr>
		<td colspan="3">경매 종료시간 &nbsp;&nbsp;${requestScope.auctionDTO.auctionApplyDTO.auctionEndTime }</td>
	</tr>
	
</table>

