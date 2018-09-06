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
.pagination a:hover:not(.active) {background-color: #ddd;}

</style>
	<!-- MAIN (Center website) -->
	<div >
	<div class="main">
		<h2>경매 신청 리스트</h2>
		<!-- Portfolio Gallery Grid -->
		<div class="row myrow">
			<c:forEach items="${requestScope.lvo.auctionApplyList}" var="item" end="${requestScope.lvo.pb.postCountPerPage}">
				<div class="column nature">
					<div class="content" onclick="location.href='${pageContext.request.contextPath}/front?command=ApplyAuctionDetail&auctionNo=${item.auctionNo }'">
							<img src="${pageContext.request.contextPath }/auction_apply_images/${item.auctionMainPic}" style="width: 100%; height:300px;	">
						<h4>${item.auctionTitle }</h4>
						<p>${item.inpictureMemberDTO.name }</p>
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
	</div>
	
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
