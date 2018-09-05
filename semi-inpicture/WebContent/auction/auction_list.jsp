<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<c:choose>
<c:when test="${sessionScope.mvo != null }">
<div class="row" style="padding-top:200px;">
	<div class="col">
		<div class="product-grid"
			data-isotope='{ "itemSelector": ".product-item", "layoutMode": "fitRows" }'>
			<c:forEach items="${requestScope.lvo.mapList}" var="list" end="${requestScope.lvo.pb.postCountPerPage}">
				
				<div class="product-item women">
					<div class="product discount product_filter">
						<div class="product_image">
							<a href="${pageContext.request.contextPath }/front?command=AuctionArtDetail&auctionNo=${list.key.auctionApplyDTO.auctionNo}">
							<img src="${pageContext.request.contextPath}/auction_apply_images/${list.key.auctionApplyDTO.auctionMainPic }"
								alt="">
							</a>
						</div>

						<div class="product_info">
							<h6 class="product_name">
								<a href="${pageContext.request.contextPath }/front?command=AuctionArtDetail&auctionNo=${list.key.auctionApplyDTO.auctionNo}&maxBid=${list.value.auctionBidPrice }">${list.key.auctionApplyDTO.auctionTitle }</a>
							</h6>
							<c:choose>
								<c:when test="${list.value.auctionBidPrice == 0 }">
									<div class="product_price">최고 입찰가 : ${list.key.auctionApplyDTO.auctionBeginPrice}</div>
								</c:when>
								<c:otherwise>
									<div class="product_price">최고 입찰가 : ${list.value.auctionBidPrice}</div>
								</c:otherwise>
							</c:choose>
							<div class="product_price">경매종료시간 :${list.key.auctionApplyDTO.auctionEndTime }
</div>
						</div>
					</div>
				</div>

			</c:forEach>
		</div>
	</div>
</div>
</c:when>
<c:otherwise>
	<script type="text/javascript">
		location.href="${pageContext.request.contextPath}/member/need_login.jsp";
	</script>
</c:otherwise>
</c:choose>
