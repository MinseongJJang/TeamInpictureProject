<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function adminBtn() {
		if(confirm("작가 신청을 승인하시겠습니까?")) {
			location.href="${pageContext.request.contextPath}/front?command=ChangeMemberType&id=${requestScope.dto.inpictureMemberDTO.id}";
		}
	}
</script>
</head>
<body>
	<div class="main_slider" style="background-image:url(images/slider_1.jpg)" >
		<div class="container fill_height">
			<div class="row align-items-center fill_height">
				<div class="col">
					<div class="main_slider_content">
						<table class="table">
							<tr>
								<td>글번호 ${requestScope.dto.artistPostNo }</td>
								<td>제목: ${requestScope.dto.artistApplyTitle} </td>
								<td>작성자 :  ${requestScope.dto.inpictureMemberDTO.name }</td>
								<td>${requestScope.dto.regdate }</td>
							</tr>		
							<tr>
								<td colspan="5" >
								<pre>${requestScope.dto.artistApplyContent}</pre>
								</td>
							</tr>
								<c:forEach var="list" items="${requestScope.list}" varStatus="info">
								<tr>
									<td colspan="4" align="center">
										<img src="${pageContext.request.contextPath}/artist_upload/${list.artAttachmentPath}"><br>
									</td>
								</tr>
								</c:forEach>
							<tr>
								<td colspan="5" class="btnArea">
								 <button type="button" class="btn" onclick="adminBtn()">승인</button>
								 </td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>