<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <script type="text/javascript">
 	$(document).ready(function() {
 		$("#artist td").click(function(){
 			//alert($(this).parent().find(":hidden").val());
 			location.href="${pageContext.request.contextPath}/front?command=DetailArtist&id="+$(this).parent().find(":hidden").val();
 		});
 	});
 </script>

<div class="main_slider">
<div class="container fill_height">
	<div class="row align-items-center fill_height">
		<div class="col">
			<div class="main_slider_content">
				<h3>작가 목록 리스트</h3>
<table class="table table-bordered  table-hover boardlist">
	<thead>
		<tr>
			<th>작가 이름</th><th></th>
		</tr>
	</thead>
	<tbody id="artist">
		<c:forEach var="ldto" items="${requestScope.ldto.artList}">
			<tr>
				<td>${ldto.inpictureMemberDTO.name  }<input type="hidden" value="${ldto.inpictureMemberDTO.id }"></td>
				<td><img src="${pageContext.request.contextPath}/artist_upload/${ldto.artMainPic }" width="100px" height="100px"> </td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<c:set var="pb" value="${requestScope.ldto.pb}"></c:set>
<div class="pagingArea">
	<ul class="pagination">
		<c:if test="${pb.previousPageGroup}">
			<li><a
				href="front?command=List&pageNo=${pb.startPageOfPageGroup-1}">&laquo;</a>
			</li>
		</c:if>
		<c:forEach var="i" begin="${pb.startPageOfPageGroup}"
			end="${pb.endPageOfPageGroup}">
			<c:choose>
				<c:when test="${pb.nowPage!=i}">
					<li><a href="front?command=List&pageNo=${i}">${i}</a></li>
				</c:when>
				<c:otherwise>
					<li class="active"><a href="#">${i}</a></li>
				</c:otherwise>
			</c:choose>
	&nbsp;
	</c:forEach>
		<c:if test="${pb.nextPageGroup}">
			<li><a
				href="front?command=List&pageNo=${pb.endPageOfPageGroup+1}">&raquo;</a></li>
		</c:if>
	</ul>
</div>
				
			</div>
		</div>
	</div>
</div>
</div>
