<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<div class="row" style="padding-top:200px;">
	<div class="col">
		<div class="product-grid"
			data-isotope='{ "itemSelector": ".product-item", "layoutMode": "fitRows" }'>
			<c:forEach items="${requestScope.auctionList}" var="list">


				<div class="product-item women">
					<div class="product discount product_filter">
						<div class="product_image">
							<img src="${pageContext.request.contextPath}/auction_apply_images/${list.key.auctionApplyDTO.auctionMainPic }"
								alt="">
						</div>

						<div class="product_info">
							<h6 class="product_name">
								<a href="#">${list.key.auctionApplyDTO.auctionTitle }</a>
							</h6>
							<div class="product_price">최고 입찰가 : ${list.value.auctionBidPrice }</div>
						</div>
					</div>
				</div>

			</c:forEach>
		</div>
	</div>
</div>
