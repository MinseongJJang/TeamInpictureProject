<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(document).ready(function(){
		$("#bidOkBtn").click(function(){
	 		if($("#bidPirce").val() =="" || $("#bidPrice").val() == 0 || $("#bidPrice").val() <= ${param.maxBid} ){
	 			alert("올바른 값을 입력해주세요");
	 		}else{
	 			if(confirm($("#bidPrice").val()+"원 입찰하시겠습니까?")){
	 				$.ajax({
	 					type : "post",
	 					url : "${pageContext.request.contextPath}/front",
	 					data : $("#modalForm").serialize(),
	 					success:function(result){
	 						alert(result);
	 						$("#maxBid").text(result);
	 					}//
	 				});//ajax
	 			}//if	
	 		}//else
 		});//click
	});//ready
</script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<div style="padding-top: 200px;"></div>
<c:choose>
<c:when test="${requestScope.auctionDTO != null || ${sessionScope.mvo != null}">
<form action="front" id="registerForm"></form>
<table class="table">
	<tr>
		<th>No
			&nbsp;&nbsp;${requestScope.auctionDTO.auctionApplyDTO.auctionNo }&nbsp;&nbsp;</th>
		<th>Title
			&nbsp;&nbsp;${requestScope.auctionDTO.auctionApplyDTO.auctionTitle }&nbsp;&nbsp;</th>
		<th>Writer
			&nbsp;&nbsp;${requestScope.auctionDTO.auctionApplyDTO.inpictureMemberDTO.name }&nbsp;&nbsp;</th>
	</tr>
	<tr>
		<td colspan="3"><img
			src="${pageContext.request.contextPath }/auction_apply_images/${requestScope.auctionDTO.auctionApplyDTO.auctionMainPic}">
		</td>
	</tr>
	<tr>
		<td colspan="3"><textarea readonly="readonly" cols="100" rows="1">
		${requestScope.auctionDTO.auctionApplyDTO.auctionContent }
		</textarea></td>
	</tr>
	<tr>
		<td colspan="3">경매 시작가
			&nbsp;&nbsp;${requestScope.auctionDTO.auctionApplyDTO.auctionBeginPrice }</td>
	</tr>
	<tr>
		<td colspan="3">즉시 구매가
			&nbsp;&nbsp;${requestScope.auctionDTO.auctionApplyDTO.auctionPromptlyPrice }</td>
	</tr>
	<tr>
		<td colspan="3">경매 시작시간
			&nbsp;&nbsp;${requestScope.auctionDTO.auctionApplyDTO.auctionBeginTime}</td>
	</tr>
	<tr>
		<td colspan="3">경매 종료시간
			&nbsp;&nbsp;${requestScope.auctionDTO.auctionApplyDTO.auctionEndTime }</td>
	</tr>
	<tr>
		<td colspan="2">최고 입찰가 &nbsp;&nbsp;</td>
		<td id="maxBid">${param.maxBid }</td>
	</tr>
	<tr>
		<td><button type="button" class="btn btn-info btn-lg"
				data-toggle="modal" data-target="#myModal" id="bidBtn">입찰</button></td>
	</tr>
</table>


<!-- Modal -->
<div id="myModal" class="modal fade" role="dialog">
	<div class="modal-dialog">
		<!-- Modal content-->
		<form action="${pageContext.request.contextPath }/front" method="post"
			id="modalForm">
			<input type="hidden" name="auctionNo"
				value="${requestScope.auctionDTO.auctionApplyDTO.auctionNo}">
			<input type="hidden" name="command" value="Bid">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>
				<div class="modal-body">
					<p>
						입찰가 : <input type="number" id="bidPrice" name="bidPrice"
							required="required">
					</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"
						id="bidOkBtn">입찰</button>
				</div>
			</div>
		</form>
	</div>
</div>
</c:when>
<c:otherwise>
	<script type="text/javascript">
		location.href="/member/error.jsp";
	</script>
</c:otherwise>
</c:choose>