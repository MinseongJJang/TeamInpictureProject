<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div style="padding-top:500px;">
<form method="post" action="${pageContext.request.contextPath}/front">
<input type="hidden" name="command" value="Login">
    <input type="text" name="id"   placeholder="아이디" size="12"> 
   <input type="password" name="password"  placeholder="비밀번호" size="12">
    <input type="submit" value="로그인">
</form>
</div>



