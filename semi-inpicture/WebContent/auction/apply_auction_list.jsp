<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div style="padding-top:200px;">
	<table>
		<tr>
			<th>No</th><th>Title</th><th>Writer</th>
		</tr>
		<c:forEach items="${requestScope.request.auctionList }" var="auction">
			<tr>
				
			</tr>
		</c:forEach>
	</table>
</div>