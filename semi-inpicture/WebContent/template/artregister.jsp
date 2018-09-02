<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 

 <div class="main_slider">
		<div class="container fill_height">
			
			<div class="row align-items-center fill_height">
				<div class="col">
					<div class="main_slider_content">
					<h1>작가 작품 등록</h1>
						<form method="post" action="${pageContext.request.contextPath}/front" enctype="multipart/form-data">
						<table class="table">
    						<tr>
    							<td>
    								작품명     								
     								<input type="text" name="artName" placeholder="작품명" required="required">
    							</td>
    						</tr>   
    						<tr>
     							<td>     
     								<textarea cols="90" rows="15" name="content" required="required" placeholder="그림소개"></textarea>
     							</td>
     							<td>   								
									<input type="hidden" name="command" value="RegisterMyArt">
									사진  <input type="file" name="picture" > <br>
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