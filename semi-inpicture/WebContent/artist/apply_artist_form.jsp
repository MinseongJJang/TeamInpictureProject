<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js">
</script>
	<script type="text/javascript">
	var count=1;
	$(document).ready(function() {
		$("#addBtn").click(function(){
			var str = "<tr><td><input type='file' name='artist_attachment_file"+(count++)+"'/>&nbsp;&nbsp;<button  type='button' name='delete' id='deleteBtn' class='file_del_btn'>파일 삭제하기</button></tr></td>";
            $("#fileDiv").append(str);
		});
		$("#table").on("click", ".file_del_btn", function() { //list안의 btnDel을 선택
			$(this).parent().parent().remove(); //this(btnDel)의 부모(td)의 부모(tr)를 삭제
		});  
	});
	</script>
<c:choose>
<c:when test="${sessionScope.mvo != null }">
<div class="main1">
	<h2>작가 신청</h2>
			<div class="content">
				<form method="post" action="${pageContext.request.contextPath}/front" enctype="multipart/form-data">
				<input type="hidden" name="command" value="ApplyArtist">
					<table class="table" style="border-bottom:1px solid #dbdbdb" style="border-top:1px solid #dbdbdb" id="table">
							<tr>
								<td>제목</td>
								<td colspan="2"><input type="text" name="title" placeholder="게시글 제목을 입력하세요" required="required" ></td>								
							</tr>
							<tr>
								<td colspan="3"></td>
							</tr>
							<tr>
								<td>작가 신청 내용</td>
								<td colspan="2">
									<textarea cols="90" rows="15" name="content" required="required" placeholder="작가님이 누구인지 이해하고 앞으로 인픽처에서 어떤 활동을 보여주실지 기대할 수 있도록 알려주세요.(작가 소개, 작가 이력, 주로 그리는 그림의 주제 및 특징,, 등등))"></textarea>
								</td>								
							</tr>
							 <tr>
								<td colspan="3"><button type="button" id="addBtn" value="파일 추가하기" class="file_submit_btn">파일 추가하기</button></td>
							</tr>
							<tr>
								<td colspan="3"></td>
							</tr>
							<tr>
								<td>메인 사진 선택 :  &nbsp;&nbsp;</td>
								<td colspan="2">
									<div id="fileDiv">
										<input type="file" name="aritst_main_pic" id="upload"/>&nbsp;&nbsp;
										<button type="button" name="delete" id="deleteBtn" class="file_del_btn">파일 삭제하기</button><hr>
									</div>							
								</td>	
							</tr>					
						</table>
						<div align="center">
								<button type="submit" class="newsletter_submit_btn">작가 신청하기</button>&nbsp;<button type="reset" class="newsletter_submit_btn">취소</button>
						</div>		
				</form>
		</div>
</div>
</c:when>
<c:otherwise>
	<script>
		location.href="${pageContext.request.contextPath}/member/need_login.jsp";
	</script>
</c:otherwise>
</c:choose>