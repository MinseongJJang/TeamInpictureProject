<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- <h3> 아이디 찾기 </h3>
<table class=main>
	<tr>
</table> -->
<style>
.test{
	 border-top:1px solid #000;
	 border-bottom:1px solid #000;
	 border-left:1px solid #000;
	 border-right:1px solid #000;
	
}

table td{
	padding:30px;
}
</style>

<div style="margin-bottom: 100px">
<div class="container" >
	<div class="row">
		<div class="col-sm-3"></div>
		<div class="col-sm-6 text-center " 
			style="margin-top: 100px; padding-bottom: 100px;">
			<div style="margin-top: 200px; text-align:center;" align="center">
				<table class="test" >
					<tr>
						<td>
							<h3>${requestScope.dto.name}님의 아이디는  ${requestScope.dto.id } 입니다!</h3>
						
						</td>
					</tr>
				</table>
			</div>
		</div>
		<div class="col-sm-3"></div>
	</div>
</div>
</div>