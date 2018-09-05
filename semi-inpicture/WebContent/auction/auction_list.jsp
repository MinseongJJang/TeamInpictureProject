<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<style>
/* Center website */
.main {
	font-family: Arial;
	padding: 40px;
	background-color: #f1f1f1;
	max-width: 1000px;
	margin: 200px auto;
}

h1 {
	font-size: 50px;
	word-break: break-all;
}

.myrow {
	margin: 10px -16px;
}

/* Add padding BETWEEN each column */
.myrow, .myrow>.column {
	padding: 8px;
}

/* Create three equal columns that floats next to each other */
.column {
	float: left;
	width: 33.33%;
	display: none; /* Hide all elements by default */
}

/* Clear floats after rows */
.myrow:after {
	content: "";
	display: table;
	clear: both;
}

/* Content */
.content {
	background-color: white;
	padding: 10px;
}

/* The "show" class is added to the filtered elements */
.show {
	display: block;
}

.pagination a {
	color: black;
	float: left;
	padding: 8px 16px;
	text-decoration: none;
	transition: background-color .3s;
}

/* Style the active/current link */
.pagination a.active {
	background-color: dodgerblue;
	color: white;
}

/* Add a grey background color on mouse-over */
.pagination a:hover:not (.active ) {
	background-color: #ddd;
}
</style>
<!-- MAIN (Center website) -->
<c:choose>
	<c:when
		test="${requestScope.auctionDTO.inpictureMemberDTO.memberType != '2' || sessionScope.mvo != null}">
		<div class="main">
			<h2>경매 신청 리스트</h2>
			<div class="row myrow">
				<c:forEach items="${requestScope.lvo.mapList}" var="list"
					end="${requestScope.lvo.pb.postCountPerPage}">
					<div class="column nature">
						<div class="content"
							onclick="location.href='${pageContext.request.contextPath }/front?command=AuctionArtDetail&auctionNo=${list.key.auctionApplyDTO.auctionNo}&maxBid=${list.value.auctionBidPrice }'">
							<img
								src="${pageContext.request.contextPath}/auction_apply_images/${list.key.auctionApplyDTO.auctionMainPic }">
							<h6 class="product_name">
								${list.key.auctionApplyDTO.auctionTitle }</h6>
							<c:choose>
								<c:when test="${list.value.auctionBidPrice == 0 }">
									<div class="product_price">최고 입찰가 :
										${list.key.auctionApplyDTO.auctionBeginPrice}</div>
								</c:when>
								<c:otherwise>
									<div class="product_price">최고 입찰가 :
										${list.value.auctionBidPrice}</div>
								</c:otherwise>
							</c:choose>
							<div class="product_price">경매종료시간 :
								${list.key.auctionApplyDTO.auctionEndTime }</div>
						</div>
					</div>
				</c:forEach>
			</div>
			<c:set var="pb" value="${requestScope.ldto.pb}"></c:set>
			<div class="pagingArea" align="center">
				<ul class="pagination">
					<c:if test="${requestScope.lvo.pb.previousPageGroup}">
						<li><a
							href="${pageContext.request.contextPath }/front?command=ApplyAuctionArtList&nowPage=${requestScope.lvo.pb.startPageOfPageGroup-1}">&laquo;</a></li>
					</c:if>

					<c:forEach var="page"
						begin="${requestScope.lvo.pb.startPageOfPageGroup }"
						end="${requestScope.lvo.pb.endPageOfPageGroup }">

						<c:choose>
							<c:when test="${page==requestScope.lvo.pb.nowPage}">
								<li class="active"><a>${page}</a></li>
							</c:when>
							<c:otherwise>
								<li><a
									href="front?command=ApplyAuctionArtList&nowPage=${page}">${page}</a></li>
							</c:otherwise>
						</c:choose>

					</c:forEach>
					<c:if test="${requestScope.lvo.pb.nextPageGroup}">
						<li><a
							href="${pageContext.request.contextPath }/front?command=ApplyAuctionArtList&nowPage=${requestScope.lvo.pb.endPageOfPageGroup+1}">&raquo;</a></li>
					</c:if>
				</ul>
			</div>
		</div>
	</c:when>
	<c:otherwise>
		<script type="text/javascript">
			location.href = "${pageContext.request.contextPath}/member/need_login.jsp";
		</script>
	</c:otherwise>
</c:choose>

<!-- END GRID -->
<!-- END MAIN -->
<script>
	filterSelection("all")
	function filterSelection(c) {
		var x, i;
		x = document.getElementsByClassName("column");
		if (c == "all")
			c = "";
		for (i = 0; i < x.length; i++) {
			w3RemoveClass(x[i], "show");
			if (x[i].className.indexOf(c) > -1)
				w3AddClass(x[i], "show");
		}
	}
	function w3AddClass(element, name) {
		var i, arr1, arr2;
		arr1 = element.className.split(" ");
		arr2 = name.split(" ");
		for (i = 0; i < arr2.length; i++) {
			if (arr1.indexOf(arr2[i]) == -1) {
				element.className += " " + arr2[i];
			}
		}
	}
	function w3RemoveClass(element, name) {
		var i, arr1, arr2;
		arr1 = element.className.split(" ");
		arr2 = name.split(" ");
		for (i = 0; i < arr2.length; i++) {
			while (arr1.indexOf(arr2[i]) > -1) {
				arr1.splice(arr1.indexOf(arr2[i]), 1);
			}
		}
		element.className = arr1.join(" ");
	}
</script>

<!-- ------------------------------------------------------ -->
<%-- <c:choose>
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
</c:choose> --%>
