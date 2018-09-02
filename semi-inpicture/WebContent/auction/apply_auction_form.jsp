<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#auctionBtn").click(function(){
			$("#auctionForm").submit();
		});//click
		$(function(){
			$( "#datepicker1" ).datepicker();
		 });//datepicker
		$(function(){
			$( "#datepicker2" ).datepicker();
		});//datepicker
		
		$("#beginTime").keyup(function(){
			var bTime = $(this).val();
			if(bTime.length==2){
				bTime += ":";
				$(this).val(bTime);
			}
		});//keyup
		$("#endTime").keyup(function(){
			var bTime = $(this).val();
			if(bTime.length==2){
				bTime += ":";
				$(this).val(bTime);
			}
		});//keyup
	});//ready
</script>
<div style="padding-top:300px;">
<form method="post" action="${pageContext.request.contextPath}/front" enctype="multipart/form-data" id="auctionForm">
	<table>
	<input type="hidden" name="command" value="ApplyAuctionArt">
		<tr>
			<td>title</td>
			<td><input type="text" name="auctionTitle" placeholder="제목을 입력하세요"></td>
		</tr>
		<tr>
			<td colspan="2"><textarea placeholder="경매물품을 소개하세요" name="auctionContent" cols="130" rows="40"></textarea></td>
		</tr>
		<tr>
			<td colspan="2">경매시작시간 : <input type="text" id="datepicker1" name="beginTime1">&nbsp;&nbsp;<input type="text" name="beginTime2" id="beginTime"></td>
		</tr>
		<tr>
			<td colspan="2">경매종료시간 : <input type="text" id="datepicker2" name="endTime1">&nbsp;&nbsp;<input type="text" name="endTime2" id="endTime"> </td>
		</tr>
		<tr>
			<td colspan="2">즉시구매가 : <input type="text" name="promptlyPrice"></td>
		</tr>
		<tr>
			<td>경매시작가 : <input type="text" name="beginPrice"></td>
		</tr>
		<tr>
			<td colspan="2"><input type="file" name="auctionMainPic"></td>
		</tr>
		<tr>
			<td colspan="2"><input type="button" value="등록" id="auctionBtn">&nbsp;&nbsp;<input type="button" value="취소" id="cancle"></td>
		</tr>
	</table>
</form>
</div>