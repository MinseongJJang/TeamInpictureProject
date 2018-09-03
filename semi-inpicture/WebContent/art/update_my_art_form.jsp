<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<div class="main_slider">
		<div class="container fill_height">
			
			<div class="row align-items-center fill_height">
				<div class="col">
					<div class="main_slider_content">
					<h1>작가 작품 수정</h1>
						<form method="post" action="${pageContext.request.contextPath}/front" enctype="multipart/form-data">
						<table class="table">
    						<tr>
    							<td>
    								작품명     								
     								<input type="text" name="artName" value="${requestScope.aDTO.artTitle}">
    							</td>
    						</tr>   
    						<tr>
     							<td>     
     								<textarea cols="90" rows="15" name="content">${requestScope.aDTO.artContent}</textarea>
     							</td>
     							<td>   								
									<input type="hidden" name="command" value="UpdateMyArt">
									<input type="hidden" name="artNo" value="${requestScope.aDTO.artNo}">									
									사진  <input type="file" name="picture"> <br>
									<input type="submit" value="등록" >
									
     								</td>
    							</tr> 
     						</table>    
     					</form>     					
					</div>
				</div>
			</div>
		</div>
	</div>  