<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
	function adminBtn() {
		if(confirm("작가 신청을 승인하시겠습니까?")) {
			location.href="${pageContext.request.contextPath}/front?command=ChangeMemberType&id=${requestScope.dto.inpictureMemberDTO.id}&content=${requestScope.dto.artistApplyContent}";
		}
	}
</script>

<div class="main1">
	<h2>작가 신청</h2>
		<div class="content">
			<input type="hidden" name="command" value="ChangeMemberType">
				<table class="table" style="border-bottom:1px solid #dbdbdb" style="border-top:1px solid #dbdbdb" >
					<tr>
						<td>글번호</td>
						<td colspan="2"> ${requestScope.dto.artistPostNo }</td>								
					</tr>
					<tr>
						<td colspan="3"></td>
					</tr>
					<tr>
						<td>제목</td>
						<td colspan="2">
							${requestScope.dto.artistApplyTitle}
						</td>								
					</tr>
					<tr>
						<td colspan="3"></td>
					</tr>
					<tr>
						<td>작성자</td>
						<td colspan="2">
							 ${requestScope.dto.inpictureMemberDTO.name }
						</td>	
					</tr>
					<tr>
						<td colspan="3"></td>
					</tr>
					<tr>
						<td>작성 날짜</td>
						<td colspan="2">
							${requestScope.dto.regdate }
						</td>	
					</tr>
					<tr>
						<td colspan="3"></td>
					</tr>
					<tr>
						<td>작성 내용</td>
						<td colspan="2">
							<pre>${requestScope.dto.artistApplyContent}</pre>
						</td>	
					</tr>
					<tr>
						<td colspan="3"></td>
					</tr>
					<c:forEach var="list" items="${requestScope.list}" varStatus="info">
						<tr>
							<td>
								<img src="${pageContext.request.contextPath}/artist_images/${list.artAttachmentPath}" style="width: 100%"><br>
							</td>
						</tr>
					</c:forEach>
				</table>
				<div align="center">
						<button type="submit" class="newsletter_submit_btn" onclick="adminBtn()">승인</button>&nbsp;<button type="button" class="newsletter_submit_btn">거절</button>
				</div>		
		</div>
</div>