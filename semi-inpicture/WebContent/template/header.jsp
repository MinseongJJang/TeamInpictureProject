<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<header class="header trans_300">
	<div class="main_nav_container">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-right">
					<div class="logo_container">
						<a href="#"><img
							src="${pageContext.request.contextPath}/temp_images/logo.jpg"
							width="80px"></a>
					</div>
					<nav class="navbar">
						<ul class="navbar_menu">
							<li><a href="#">SERVICE</a></li>
							<li><a href="#">ARTIST</a></li>
							<li><a href="${pageContext.request.contextPath }/front?command=ApplyArtistForm">APPLY</a></li>
							<li><a href="#">AUCTION</a></li>
						</ul>
						<ul class="navbar_user">
							<li><a href="#"><i class="fa fa-search"
									aria-hidden="true"></i></a></li>
							<c:if test="${sessionScope.mvo == null }">
								<li><a href="${pageContext.request.contextPath }/front?command=LoginForm"><i
										class="fa fa-user" aria-hidden="true"></i></a></li>
							</c:if>
						</ul>
						<div class="hamburger_container">
							<i class="fa fa-bars" aria-hidden="true"></i>
						</div>
					</nav>
				</div>
			</div>
		</div>
	</div>

</header>