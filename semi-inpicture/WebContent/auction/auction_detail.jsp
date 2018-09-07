<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						$("#bidOkBtn")
								.click(
										function() {

											if ($("#bidPirce").val() == ""
													|| $("#bidPrice").val() == 0
													|| $("#bidPrice").val() <= $(
															"#maxBid").val()
													|| $("#bidPrice").val() <= $(
															"#beginPrice")
															.val()) {
												alert("입찰가는 현재 최고입찰가보다 높아야 합니다.");
											} else {
												if (confirm($("#bidPrice")
														.val()
														+ "원 입찰하시겠습니까?")) {
													$
															.ajax({
																type : "post",
																url : "${pageContext.request.contextPath}/front",
																data : $(
																		"#modalForm")
																		.serialize(),
																success : function(
																		result) {
																	$(
																			"#maxBidPrice")
																			.text(
																					result);
																}//
															});//ajax
												}//if   
											}//else
										});//click

						var nowTime = new Date();
						var endTime = $("#endTime").text().substring(11).split(
								":");
						var endHour = endTime[0];
						var endMinute = endTime[1];
						var endTime = new Date();
						endTime.setHours(endHour);
						endTime.setMinutes(endMinute);
						var diff = endTime.getTime() - nowTime.getTime();
						setInterval(function() {
							diff = diff - 1000;
							var time = (diff - 1000) / 1000;
							var hour = Math.floor(time / 3600);
							var min = Math.floor(time / 60);
							var sec = Math.floor(time % 60);

							$("#timer").html(
									hour + " 시간 " + min + " 분 " + sec
											+ " 초 남았습니다.").css("color", "red");
							if (min == '0' && hour == '0' && sec == '0') {
								$("#endAuction").submit();
							}
						}, 1000);//interval

					});//ready
</script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<style type="text/css">
.table5 td, .table5 th {
	padding: .75rem;
	vertical-align: middle;
	border-top: 1px solid #e9ecef;
	border-collapse: collapse;
}

.table5 {
	border-collapse: collapse;
	align:center;
}

.th, .td {
	display: table-cell;
}

.auc2 {
	font-weight: bold;
	font-size: 16px;
}

	.table6{
		padding: .75rem;
	vertical-align: middle;
	border-top: 1px solid #e9ecef;
	border-collapse: collapse;
	}
	.table6 {
	border-collapse: collapse;
}

.th, .td {
	display: table-cell;
}

 #tttt{
 	position-relative;
 	top:100px;
 	right:100px;
 	left:10000px;
 	bottom:100px;
 }
</style>
<c:choose>
<c:when test="${sessionScope.mvo!=null}">
<c:choose>
	<c:when
		test="${requestScope.auctionDTO.inpictureMemberDTO.memberType != '2' || sessionScope.mvo != null}">
		<form action="${pageContext.request.contextPath }/front"
			id="endAuction">
			<input type="hidden" id="beginPrice"
				value="${requestScope.auctionDTO.auctionApplyDTO.auctionBeginPrice }">
			<input type="hidden" name="command" value="EndAuction"> <input
				type="hidden" name="auctionNo"
				value="${requestScope.auctionDTO.auctionApplyDTO.auctionNo }">
		</form>
		<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
		<div class="main1">
			<h2>경매 정보</h2>
			<div class="content">
				<form method="post"
					action="${pageContext.request.contextPath }/front" id="endAuction"
					enctype="multipart/form-data">
					<input type="hidden" name="command" value="EndAuction"> <input
						type="hidden" name="auctionNo"
						value="${requestScope.auctionDTO.auctionApplyDTO.auctionNo }">
					<input type="hidden" id="maxBid" value="${param.maxBid }">
					<table class="table5" style="border-bottom: 1px solid #dbdbdb"
						style="border-top:1px solid #dbdbdb">
						<tr>
							<td rowspan="4"><img
								src="${pageContext.request.contextPath }/auction_apply_images/${requestScope.auctionDTO.auctionApplyDTO.auctionMainPic}"
								style="width: 300px; height: 350px;"></td>
							<td class="auc2"> 그림 제목</td>
							<td colspan="1">${requestScope.auctionDTO.auctionApplyDTO.auctionTitle }</td>
							<td class="auc2"> 작가</td>
							<td colspan="1">
								${requestScope.auctionDTO.auctionApplyDTO.inpictureMemberDTO.name}</td>
						</tr>
						<tr>
							<td class="auc2"> 경매 시작가</td>
							<td colspan="1">${requestScope.auctionDTO.auctionApplyDTO.auctionBeginPrice }</td>
							<td class="auc2"> 즉시 구매가</td>
							<td colspan="1">${requestScope.auctionDTO.auctionApplyDTO.auctionPromptlyPrice }</td>
						</tr>
						<tr>
							<td class="auc2"> 시작시간</td>
							<td colspan="1">${requestScope.auctionDTO.auctionApplyDTO.auctionBeginTime}</td>
							<td class="auc2"> 종료시간</td>
							<td colspan="1" id="endTime">${requestScope.auctionDTO.auctionApplyDTO.auctionEndTime }</td>
						    
						</tr>


						<tr>
							<td class="auc2"> 남은시간</td>
							<td colspan="1"><span id="timer"></span></td>
							<td class="auc2">최고 입찰가</td>
							<c:choose>
								<c:when test="${param.maxBid == 0}">
									<td colspan="2" id="maxBidPrice">${requestScope.auctionDTO.auctionApplyDTO.auctionBeginPrice }</td>
								</c:when>
								<c:otherwise>
									<td colspan="2" id="maxBidPrice">${param.maxBid}</td>
								</c:otherwise>
							</c:choose>
						</tr>
						<!--  -->	
						<tr>
							<td colspan="5" style="font-weight: bold; font-size: 16px;">경매 그림 소개</td>
						</tr>
						<tr>
							<td colspan="5" >
									<pre><textarea readonly="readonly" cols="130" rows="10">${requestScope.auctionDTO.auctionApplyDTO.auctionContent }
						     			</textarea></pre>
									</td>
						</tr>					
					</table>
				<%-- 	<div>
					<table class="table6" id="tttt">
						<tr>
							<td style="font-weight: bold; font-size: 16px;">경매 그림 소개</td>
						</tr>
						<tr>
							<td colspan="2" style="rigth:1000px;"><pre>
									<textarea readonly="readonly" cols="10" rows="6">${requestScope.auctionDTO.auctionApplyDTO.auctionContent }
						     			</textarea>
									</pre></td>
						</tr>
					</table>
					</div> --%>
					<div align="center">
						<button type="button" class="newsletter_submit_btn"
							data-toggle="modal" data-target="#myModal" id="bidBtn">입찰</button>
					</div>
				</form>
			</div>
		</div>

		<!-- Modal -->
		<div id="myModal" class="modal fade" role="dialog">
			<div class="modal-dialog">
				<!-- Modal content-->
				<form action="${pageContext.request.contextPath }/front"
					method="post" id="modalForm">
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
							<button type="button" class="newsletter_submit_btn"
								data-dismiss="modal" id="bidOkBtn">입찰</button>
							<button type="button" class="newsletter_submit_btn"
								data-dismiss="modal" id="promptlyBtn">즉시구매</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</c:when>
	<c:otherwise>
		<script type="text/javascript">
			location.href = "${pageContext.request.contextPath}/member/session_invalid.jsp";
		</script>
	</c:otherwise>
</c:choose>
</c:when>
<c:otherwise>
	<script>
		location.href="${pageContext.request.contextPath}/member/need_login.jsp";
	</script>
</c:otherwise>
</c:choose>
<!-- ------------------------------------------ -->
<%-- <c:choose>
		<c:when test="${requestScope.auctionDTO.inpictureMemberDTO.memberType != '2' || sessionScope.mvo != null}">
			<div class="main1">
				<h2>경매 신청</h2>
				<div class="content">
				
					<table class="table2" style="border-bottom: 1px solid #dbdbdb"
						style="border-top:1px solid #dbdbdb">
						<tr>
							<td rowspan="3"><img
								src="${pageContext.request.contextPath }/auction_apply_images/${requestScope.auctionDTO.auctionApplyDTO.auctionMainPic}" style="width:300px;height:350px;">
							</td>
							<td class="auc2 ">경매 그림 제목</td>
							<td colspan="2" >${requestScope.auctionDTO.auctionApplyDTO.auctionTitle }</td>
							<td class="auc2">그림 작가</td>
							<td colspan="2">
								${requestScope.auctionDTO.auctionApplyDTO.inpictureMemberDTO.name}</td>
						</tr>
						<tr>
							<td class="auc2">경매 시작가</td>
							<td colspan="2">${requestScope.auctionDTO.auctionApplyDTO.auctionBeginPrice }</td>
							<td class="auc2">즉시 구매가</td>
							<td colspan="2">${requestScope.auctionDTO.auctionApplyDTO.auctionPromptlyPrice }</td>
						</tr>						
						<tr>
							<td class="auc2">경매 시작시간</td>
							<td colspan="2">${requestScope.auctionDTO.auctionApplyDTO.auctionBeginTime}</td>
							<td class="auc2">경매 종료시간</td>
							<td colspan="2">${requestScope.auctionDTO.auctionApplyDTO.auctionEndTime }</td>
						</tr>
						
					</table>
					
					<div align="center">
						<table>
							<tr>
								<td style="font-weight:bold;font-size:16px;">경매 그림 소개</td>
							</tr>
							<tr>
								<td><pre><textarea readonly="readonly" cols="110"
									rows="12">${requestScope.auctionDTO.auctionApplyDTO.auctionContent }
						     			</textarea>
									</pre>
								</td>
							</tr>
						</table>
					</div>
					
				</div>
			</div>
		</c:when>
		<c:otherwise>
			<script type="text/javascript">
				location.href = "${pageContext.request.contextPath}/member/session_invalid.jsp";
			</script>
		</c:otherwise>
</c:choose> --%>
