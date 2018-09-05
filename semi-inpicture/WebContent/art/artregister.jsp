<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 

<div class="main1">
	<h2>작가 작품 등록</h2>
			<div class="content">
				<form method="post" action="${pageContext.request.contextPath}/front" enctype="multipart/form-data">
				<input type="hidden" name="command" value="RegisterMyArt">
					<table class="table" style="border-bottom:1px solid #dbdbdb" style="border-top:1px solid #dbdbdb" >
							<tr>
								<td>작품명</td>
								<td colspan="2"><input type="text" name="artName" placeholder="작품명" required="required"></td>								
							</tr>
							<tr>
								<td colspan="3"></td>
							</tr>
							<tr>
								<td>그림 소개 내용</td>
								<td colspan="2">
									<textarea cols="90" rows="15" name="content" required="required" placeholder="그림소개"></textarea>
								</td>								
							</tr>
							<tr>
								<td colspan="3"></td>
							</tr>
							<tr>
								<td>메인 사진 선택 :  &nbsp;&nbsp;</td>
								<td colspan="2">
									<input type="file" name="picture" > <br>&nbsp;&nbsp;
								</td>	
							</tr>							
						</table>
						<div align="center">
								<button type="submit" class="newsletter_submit_btn">그림 등록하기</button>&nbsp;<button type="reset" class="newsletter_submit_btn">취소</button>
						</div>		
				</form>
		</div>
</div>
