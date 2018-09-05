<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
	$(document).ready(function(){
		$("#deleteBtn").click(function(){
			if(confirm("경매신청을 취소하시겠습니까?")){
				$("#command").val("DeleteApplyAuction");
				$("#auctionForm").submit();
			}
		});//delete click
		
		$("#applyBtn").click(function(){
			if(confirm("경매신청을 승인하시곘습니까?")){
				$("#command").val("RegisterAuction");
				$("#auctionForm").submit();
			}
		});//apply click
	});//ready
</script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<div style="padding-top:200px;"></div>
<table class="table">
	<tr>
		<th>No &nbsp;&nbsp;${requestScope.auctionDTO.auctionNo }&nbsp;&nbsp;</th>
		<th>Title &nbsp;&nbsp;${requestScope.auctionDTO.auctionTitle }&nbsp;&nbsp;</th>
		<th>Writer &nbsp;&nbsp;${requestScope.auctionDTO.inpictureMemberDTO.name }&nbsp;&nbsp;</th>
	</tr>
	<tr>
		<td colspan="3"><img src="${pageContext.request.contextPath }/auction_apply_images/${requestScope.auctionDTO.auctionMainPic}"> </td>
	</tr>
	<tr>
		<td colspan="3"><textarea readonly="readonly" cols="100" rows="1">
		${requestScope.auctionDTO.auctionContent }
		</textarea></td>
	</tr>
	<tr>
		<td colspan="3">경매 시작가 &nbsp;&nbsp;${requestScope.auctionDTO.auctionBeginPrice }</td>
	</tr>
	<tr>
		<td colspan="3">즉시 구매가 &nbsp;&nbsp;${requestScope.auctionDTO.auctionPromptlyPrice }</td>
	</tr>
	<tr>
		<td colspan="3">경매 시작시간 &nbsp;&nbsp;${requestScope.auctionDTO.auctionBeginTime}</td>
	</tr>
	<tr>
		<td colspan="3">경매 종료시간 &nbsp;&nbsp;${requestScope.auctionDTO.auctionEndTime }</td>
	</tr>
	
</table>

<form method="post" action="${pageContext.request.contextPath }/front" id="auctionForm">
<input type="hidden" name="id" value="${requestScope.auctionDTO.inpictureMemberDTO.id }">
<input type="hidden" name="auctionNo" value="${requestScope.auctionDTO.auctionNo }">
<input type="hidden" name="command" value=""  id="command">
<input type="button" value="승인" id="applyBtn">
<input type="button" value="삭제" id="deleteBtn">
</form>
