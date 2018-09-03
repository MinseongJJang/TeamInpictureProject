<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	$(document).ready(function() {
		$("#doCheckBtn").click(function(){
			$("#mailing").prop("checked",true);
		});
		$("#notDoCheckBtn").click(function(){
			$("#mailing").prop("checked",false); // 체크 속성을 할당
		});
	});
</script>
</head>
<body>
	<div class="main_slider" style="background-image:url(images/slider_1.jpg)">
		<div class="container fill_height">
			<div class="row align-items-center fill_height">
				<div class="col">
					<div class="main_slider_content">
						<input type="button" id="doCheckBtn" value="전체선택">
						<input type="button" id="notDoCheckBtn" value="전체선택해제">
						<input type="button" id="adminBtn" value="선택승인"><br>
						<table class="table table-bordered  table-hover boardlist">
							<thead>
								<tr>
									<th>선택</th><th>글 번호</th><th>제목</th><th>아이디</th><th>등록일</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="ldto" items="${requestScope.ldto.artistApplyList}">
									<tr>
										<td>
											<input type="checkbox" id="mailing" value="${ldto.artistPostNo }">
										</td>
										<td>${ldto.artistPostNo }</td>
										<td><a href="${pageContext.request.contextPath}/front?command=ApplyArtistDetail&artistApplyNo=${ldto.artistPostNo }">${ldto.artistApplyTitle }</a></td>
										<td>${ldto.inpictureMemberDTO.id }</td>
										<td>${ldto.regdate }</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<c:set var="pb" value="${requestScope.ldto.pb}"></c:set>
						<div class="pagingArea">
							<ul class="pagination">
								<c:if test="${pb.previousPageGroup}">
									<li><a
										href="front?command=ApplyArtistList&pageNo=${pb.startPageOfPageGroup-1}">&laquo;</a>
									</li>
								</c:if>
								<c:forEach var="i" begin="${pb.startPageOfPageGroup}"
									end="${pb.endPageOfPageGroup}">
									<c:choose>
										<c:when test="${pb.nowPage!=i}">
											<li><a href="front?command=ApplyArtistList&pageNo=${i}">${i}</a></li>
										</c:when>
										<c:otherwise>
											<li class="active"><a href="#">${i}</a></li>
										</c:otherwise>
									</c:choose>
							&nbsp;
							</c:forEach>
								<c:if test="${pb.nextPageGroup}">
									<li><a
										href="front?command=ApplyArtistList&pageNo=${pb.endPageOfPageGroup+1}">&raquo;</a></li>
								</c:if>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>