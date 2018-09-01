<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#auctionBtn").click(function(){
			$("#auctionForm").submit();
		})//click
	});//ready
</script>
<form method="post" action="${pageContext.request.contextPath}/front" enctype="multipart/form-data" id="auctionForm">
	<table>
	<input type="hidden" name="command" value="">
		<tr>
			<td>title</td>
			<td><input type="text" name="auctionTitle" placeholder="제목을 입력하세요"></td>
		</tr>
		<tr>
			<td colspan="2"><textarea placeholder="자신을 소개하세요" name="auctionContent" cols="130" rows="40"></textarea></td>
		</tr>
		<tr>
			<td colspan="2"><input type="file" name="auctionFile"></td>
		</tr>
		<tr>
			<td colspan="2"><input type="button" value="등록" id="auctionBtn"></td>
		</tr>
		<tr>
			<td colspan="2"><input type="button" value="취소" id="cancle"></td>
		</tr>
	</table>
</form>