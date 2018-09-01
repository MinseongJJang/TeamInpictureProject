<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js">
</script>
<title>작가 신청 폼</title>
</head>
<body>
<div style="padding-top:300px;">
	<form method="post" action="${pageContext.request.contextPath}/front" enctype="multipart/form-data">
		<input type="hidden" name="command" value="ApplyArtist">
		<table class="table">
			<tr>
				<td>제목 &nbsp;&nbsp; <input type="text" name="title" placeholder="게시글 제목을 입력하세요" required="required">
				</td>
			</tr>
			<tr>
				<td><textarea cols="90" rows="15" name="content" required="required" placeholder="작가님이 누구인지 이해하고 앞으로 인픽처에서 어떤 활동을 보여주실지 기대할 수 있도록 알려주세요."></textarea></td>
			</tr>
		</table>
		<div id="fileDiv">
			<p>
				<input type="file" name="artist_attachment_file_0" multiple="multiple">
			</p>
		</div>
		<div class="btnArea">
			<button type="submit" class="btn">작가 신청하기</button>
			<button type="reset" class="btn">취소</button>
		</div>
	</form>
	</div>
</body>
</html>