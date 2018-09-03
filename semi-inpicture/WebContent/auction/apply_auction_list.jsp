<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div style="padding-top: 200px;">

	<table class="table table-hover">
		<tr>
			<th>No</th>
			<th>Title</th>
			<th>Writer</th>
		</tr>
		<c:forEach items="${requestScope.lvo.auctionApplyList}" var="item"
			end="${requestScope.lvo.pb.postCountPerPage}">
			<c:if test="${item.auctionState == 0 }">
				<tr>
					<td>${item.auctionNo }</td>
					<td><a
						href="front?command=ApplyAuctionDetail&auctionNo=${item.auctionNo }">${item.auctionTitle }</a></td>
					<td>${item.inpictureMemberDTO.name }</td>
				</tr>
			</c:if>
		</c:forEach>
	</table>
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