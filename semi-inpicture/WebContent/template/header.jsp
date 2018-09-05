<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<header class="header trans_300">
	<div class="main_nav_container">
		<div class="container">
			<div class="row">
				<div class="col-lg-12 text-right">
					<div class="logo_container">
						<a href="${pageContext.request.contextPath}/front?command=GetAllInfoList"><img src="${pageContext.request.contextPath}/temp_images/logo.jpg" width="80px"></a>
					</div>
					<nav class="navbar">
						<ul class="navbar_menu">
							
							<li><a href="#">SERVICE</a></li>
							<li><a href="${pageContext.request.contextPath}/front?command=ArtistList">ARTIST</a></li>
							<li><a href="${pageContext.request.contextPath}/front?command=AuctionArtList">AUCTION</a></li>
							<li><a href="${pageContext.request.contextPath }/front?command=ApplyArtistForm">APPLY ARTIST</a></li>
							<c:choose>
								<c:when test="${sessionScope.mvo.memberType eq '3'}">
									<li><a href="${pageContext.request.contextPath }/front?command=ApplyArtistList">APPLY ARTIST LIST</a></li>
									<li><a href="${pageContext.request.contextPath }/front?command=ApplyAuctionArtList">APPLY AUCTION LIST</a></li>
								</c:when>
								<c:otherwise>
									<li><a href="${pageContext.request.contextPath }/front?command=ApplyAuctionArtForm">APPLY AUCTION</a></li>
								</c:otherwise>
							</c:choose>
						</ul>
						<ul class="navbar_user">
							<li><a href="#"><i class="fa fa-search"
									aria-hidden="true"></i></a></li>
							<c:choose>			
							<c:when test="${sessionScope.mvo == null }">
								<li><a href="${pageContext.request.contextPath }/front?command=LoginForm"><i
										class="fa fa-user" aria-hidden="true"></i></a></li>
							</c:when>
							<c:otherwise>	
							<li><a href="${pageContext.request.contextPath }/front?command=Logout"><i class="fa fa-sign-out"></i>
									</a></li>
							</c:otherwise>					
							</c:choose>	
						</ul>
						<div class="hamburger_container">
							<i class="fa fa-bars" aria-hidden="true"></i>
						</div>
					</nav>
				</div>
			</div>
		</div>
	</div>


<!-- ------------------------------------------------- -->

<div class="fs_menu_overlay"></div>
<div class="hamburger_menu">
	<div class="hambuger_logo">
		<a href="#"> <img alt=""
			src="${pageContext.request.contextPath }/temp_images/hamburgerlogo.png"
			class="hamburgerlogo"><br>
		</a>
	</div>
	<div class="hamburger_close">
		<i class="fa fa-times" aria-hidden="true"></i>
	</div>
	<div class="hamburger_menu_content ">

		<ul class="menu_top_nav">
			<c:choose>
				<c:when test="${sessionScope.mvo != null }">
					<li class="menu_item has-children">${sessionScope.mvo.name } 님</li>
					<li class="menu_item has-children">${sessionScope.mvo.point} &nbsp;&nbsp;보유중</li>
					<li class="menu_item has-children"><a href="${pageContext.request.contextPath}/front?command=MemberUpdateForm&id=${sessionScope.mvo.id}">UPDATE MEMBER</a></li>
				  <c:if test="${sessionScope.mvo.memberType eq '2'}">
					<li class="menu_item has-children"><a href="${pageContext.request.contextPath}/front?command=RegisterMyArtForm">REGISTER ART</a></li>
				  </c:if>	
					<li class="menu_item has-children"><a
						href="${pageContext.request.contextPath }/front?command=Logout">
							LOGOUT</a></li>
				</c:when>
				<c:otherwise>
					<li class="menu_item has-children"><a
						href="${pageContext.request.contextPath }/front?command=LoginForm">
							LOGIN </a></li>
					<li class="menu_item has-children"><a href="${pageContext.request.contextPath}/front?command=RegisterMemberForm"> REGISTER </a></li>
				</c:otherwise>
			</c:choose>
			<li class="menu_item"><a href="${pageContext.request.contextPath}/front?command=DetailArtist">NOTICE</a></li>
			<li class="menu_item"><a href="#">FAQ</a></li>
			<li class="menu_item"><a href="#">Q&A</a></li>
		</ul>
	</div>
</div>
</header>