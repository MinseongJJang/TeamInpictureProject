<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	$(document).ready(function() {
		$("#deleteBtn").click(function() {
			if (confirm("경매신청을 취소하시겠습니까?")) {
				$("#command").val("DeleteApplyAuction");
				$("#auctionForm").submit();
			}
		});//delete click

		$("#applyBtn").click(function() {
			if (confirm("경매신청을 승인하시곘습니까?")) {
				$("#command").val("RegisterAuction");
				$("#auctionForm").submit();
			}
		});//apply click
	});//ready
</script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<%-- <c:choose>
		<c:when test="${requestScope.auctionDTO.inpictureMemberDTO.memberType != '2' || sessionScope.mvo != null}">
			<div class="main1">
				<h2>경매 신청</h2>
				<div class="content">
					<table class="table" style="border-bottom: 1px solid #dbdbdb"
						style="border-top:1px solid #dbdbdb">
						<tr>
							<td>경매 그림</td>
							<td colspan="2"><img
								src="${pageContext.request.contextPath }/auction_apply_images/${requestScope.auctionDTO.auctionMainPic}">
							</td>
						</tr>
						<tr>
							<td>경매 번호</td>
							<td colspan="2">${requestScope.auctionDTO.auctionNo }</td>
						</tr>
						<tr>
							<td colspan="3"></td>
						</tr>
						<tr>
							<td>경매 그림 제목</td>
							<td colspan="2">${requestScope.auctionDTO.auctionTitle }</td>
						</tr>
						<tr>
							<td colspan="3"></td>
						</tr>
						<tr>
							<td>그림 작가</td>
							<td colspan="2">
								${requestScope.auctionDTO.inpictureMemberDTO.name }</td>
						</tr>
						<tr>
							<td colspan="3"></td>
						</tr>
						
						<tr>
							<td colspan="3"></td>
						</tr>
						<tr>
							<td>경매 그림 소개</td>
							<td colspan="2"><textarea readonly="readonly" cols="100"
									rows="1">${requestScope.auctionDTO.auctionContent }</textarea>
							</td>
						</tr>
						<tr>
							<td>경매 시작가</td>
							<td colspan="2">${requestScope.auctionDTO.auctionBeginPrice }</td>
						</tr>
						<tr>
							<td colspan="3"></td>
						</tr>
						<tr>
							<td>즉시 구매가</td>
							<td colspan="2">${requestScope.auctionDTO.auctionPromptlyPrice }</td>
						</tr>
						<tr>
							<td colspan="3"></td>
						</tr>
						<tr>
							<td>경매 시작시간</td>
							<td colspan="2">${requestScope.auctionDTO.auctionBeginTime}</td>
						</tr>
						<tr>
							<td colspan="3"></td>
						</tr>
						<tr>
							<td>경매 종료시간</td>
							<td colspan="2">${requestScope.auctionDTO.auctionEndTime }</td>
						</tr>
						<tr>
							<td colspan="3"></td>
						</tr>
					</table>
					<form method="post"
						action="${pageContext.request.contextPath }/front"
						id="auctionForm">
						<input type="hidden" name="id"
							value="${requestScope.auctionDTO.inpictureMemberDTO.id }">
						<input type="hidden" name="auctionNo"
							value="${requestScope.auctionDTO.auctionNo }"> <input
							type="hidden" name="command" value="" id="command">
						<div align="center">
							<button type="button" id="applyBtn" class="newsletter_submit_btn"
								onclick="adminBtn()">승인</button>
							&nbsp;
							<button type="button" class="newsletter_submit_btn">삭제</button>
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
 --%>
<style type="text/css">
.table2 td,.table2 th{
	padding: .75rem;
    vertical-align: middle;
    border-top: 1px solid #e9ecef;
    border-collapse: collapse;
}
.table2{
	    border-collapse: collapse;
}
.th,.td{

	display: table-cell;
}
.auc2{
	font-weight:bold;
	font-size:16px;
}
</style>
 <c:choose>
		<c:when test="${requestScope.auctionDTO.inpictureMemberDTO.memberType != '2' || sessionScope.mvo != null}">
			<div class="main1">
				<h2>경매 신청</h2>
				<div class="content">
				
					<table class="table2" style="border-bottom: 1px solid #dbdbdb"
						style="border-top:1px solid #dbdbdb">
						<tr>
							<td rowspan="3"><img
								src="${pageContext.request.contextPath }/auction_apply_images/${requestScope.auctionDTO.auctionMainPic}">
							</td>
							<td class="auc2 ">경매 그림 제목</td>
							<td colspan="2">${requestScope.auctionDTO.auctionTitle }</td>
							<td class="auc2">그림 작가</td>
							<td colspan="2">
								${requestScope.auctionDTO.inpictureMemberDTO.name }</td>
						</tr>
						<tr>
							<td class="auc2">경매 시작가</td>
							<td colspan="2">${requestScope.auctionDTO.auctionBeginPrice }</td>
							<td class="auc2">즉시 구매가</td>
							<td colspan="2">${requestScope.auctionDTO.auctionPromptlyPrice }</td>
						</tr>						
						<tr>
							<td class="auc2">경매 시작시간</td>
							<td colspan="2">${requestScope.auctionDTO.auctionBeginTime}</td>
							<td class="auc2">경매 종료시간</td>
							<td colspan="2">${requestScope.auctionDTO.auctionEndTime }</td>
						</tr>
						
					</table>
					
					<div align="center">
						<table>
							<tr>
								<td style="font-weight:bold;font-size:16px;">경매 그림 소개</td>
							</tr>
							<tr>
								<td><pre><textarea readonly="readonly" cols="110"
									rows="12">${requestScope.auctionDTO.auctionContent }
						     			</textarea>
									</pre>
								</td>
							</tr>
						</table>
					</div>
					<form method="post"
						action="${pageContext.request.contextPath }/front"
						id="auctionForm">
						<input type="hidden" name="id"
							value="${requestScope.auctionDTO.inpictureMemberDTO.id }">
						<input type="hidden" name="auctionNo"
							value="${requestScope.auctionDTO.auctionNo }"> <input
							type="hidden" name="command" value="" id="command">
						<div align="center">
							<button type="button" id="applyBtn" class="newsletter_submit_btn"
								onclick="adminBtn()">승인</button>
							&nbsp;
							<button type="button" class="newsletter_submit_btn">삭제</button>
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
 



