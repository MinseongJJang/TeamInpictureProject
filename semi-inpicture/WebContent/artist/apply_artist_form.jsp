<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js">
</script>
	<script type="text/javascript">
		var count=1;
		$(document).ready(function() {
			$("#addBtn").click(function(){
				var str = "<input type='file' name='artist_attachment_file"+(count++)+"'/>&nbsp;&nbsp;<button type='button' id='delete'>파일 삭제하기</button><hr>";
	            $("#fileDiv").append(str);
			});
		 	$("#deleteBtn").click(function(){
            	$(this).$("#fileDiv").remove();      
            });	
		});
	</script>
<div class="main_slider" style="background-image:url(images/slider_1.jpg)">
		<div class="container fill_height">
			<div class="row align-items-center fill_height">
				<div class="col">
					<div class="main_slider_content">
						<form method="post" action="${pageContext.request.contextPath}/front" enctype="multipart/form-data">
							<input type="hidden" name="command" value="ApplyArtist">
							<table class="login_table">
								<tr>
									<td>제목 &nbsp;&nbsp; <input type="text" name="title" placeholder="게시글 제목을 입력하세요" required="required">
									</td>
								</tr>
								<tr>
									<td><textarea cols="90" rows="15" name="content" required="required" placeholder="작가님이 누구인지 이해하고 앞으로 인픽처에서 어떤 활동을 보여주실지 기대할 수 있도록 알려주세요."></textarea></td>
								</tr>
								<tr>
									<td><button type="button" id="addBtn" value="파일 추가하기" class="file_submit_btn">파일 추가하기</button></td>
								</tr>
								<tr>
									<td>
										<div id="fileDiv">
											<button class="replace">파일 업로드</button><input type="file" name="artist_attachment_file" id="upload"/>&nbsp;&nbsp;<button type="button" name="delete" id="deleteBtn" class="file_submit_btn">파일 삭제하기</button><hr>
										</div>
									</td>
								</tr>
								<tr>	
									<td colspan="2">
										<button type="submit" class="newsletter_submit_btn">작가 신청하기</button>
										<button type="reset" class="newsletter_submit_btn">취소</button>
									</td>
								</tr>
							</table>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
