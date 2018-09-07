<%@page import="semi.inpicture.model.dto.InpictureMemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	$(document).ready(function() {
		$("#newsletter_submit").click(function() {		
			var type = ${sessionScope.mvo.memberType};
			var type2 = ${sessionScope.mvo};
			alert(type);
			if(type==2){
				alert("이미 작가입니다");
				return false;
			}else if(type==3){
				alert("관리자입니다.");
				return false;			
			}
		}) 
	});//ready
</script>
<!-- 새로운 carousel -->
<div class="deal_ofthe_week">
	<div
		class="carousel-padding align-items-center deal_ofthe_week_img text-right deal_ofthe_week_col">
		<div class="container ">
			<div class="background"></div>
			<section class="carousel_container">
				<div id="carousel">
					<c:choose>
						<c:when test="${requestScope.size > 1}">
							<c:forEach items="${requestScope.carousel1 }" var="carousel">
								<figure>
									<a
										href="${pageContext.request.contextPath }/front?command=AuctionArtDetail&auctionNo=${carousel.auctionApplyDTO.auctionNo}">
										<img
										src="${pageContext.request.contextPath}/auction_apply_images/${carousel.auctionApplyDTO.auctionMainPic}" height="300px;"/>
										<h3>${carousel.auctionApplyDTO.auctionTitle}</h3>
									</a>
								</figure>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<c:forEach items="${requestScope.carousel2 }" var="carousel">
								<figure>
									<a
										href="${pageContext.request.contextPath}/front?command=DetailArt&artNo=${carousel.artNo}">
										<img
										src="${pageContext.request.contextPath}/art_images/${carousel.artMainPic}"  height="300px;">
										<h3>${carousel.artTitle}</h3>
									</a>
								</figure>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</div>
			</section>
			<nav id="navigation">
				<span class="navigation-control left" data-increment="1"></span> <span
					class="navigation-control right" data-increment="-1"></span>
			</nav>
		</div>
	</div>
</div>

<!-- New Arrivals -->
<div class="container">
	<div class="new_arrivals">
		<div class="container">
			<div class="row">
				<div class="col text-center">
					<div class="section_title new_arrivals_title">
						<h2>New Arrivals</h2>
					</div>
				</div>
			</div>
			<div class="row align-items-center">
				<div class="col text-center">
					<div class="new_arrivals_sorting" id="abcdefg">
						<ul
							class="arrivals_grid_sorting clearfix button-group filters-button-group">
							<li
								class="grid_sorting_button button d-flex flex-column justify-content-center align-items-center active"
								data-filter=".women" id="art">ART</li>
							<li
								class="grid_sorting_button button d-flex flex-column justify-content-center align-items-center "
								data-filter=".accessories" id="artist">ARTIST</li>
							<li
								class="grid_sorting_button button d-flex flex-column justify-content-center align-items-center"
								data-filter=".men" id="auction">AUCTION</li>
						</ul>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<div class="product-grid"
						data-isotope='{ "itemSelector": ".product-item", "layoutMode": "fitRows" }'>

						<c:forEach items="${requestScope.lvo1.artList}" var="art"
							end="${requestScope.lvo1.pbMain.end}">

							<div class="product-item women">
								<div class="product discount product_filter">
									<div class="product_image">
										<img
											src="${pageContext.request.contextPath}/art_images/${art.artMainPic}"
											alt="" style="width: 200px; height: 200px;">
									</div>

									<div class="product_info">
										<div class="product_price">
											<a
												href="${pageContext.request.contextPath }/front?command=DetailArt&artNo=${art.artNo}">
												${art.artTitle} </a>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>


						<c:forEach items="${requestScope.lvo2.artistList }" var="artist"
							end="${requestScope.lvo2.pbMain.end }">
							<div class="product-item accessories">
								<div class="product discount product_filter">
									<div class="product_image">
										<img
											src="${pageContext.request.contextPath}/artist_images/${artist.artist_main_pic}"
											alt="">
									</div>
									<div class="product_info">
										<div class="product_price">
											<a
												href="${pageContext.request.contextPath }/front?command=DetailArtist&id=${artist.inpictureMemberDTO.id}">
												${artist.inpictureMemberDTO.name} </a>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>


						<c:forEach items="${requestScope.lvo3.auctionList}" var="auction"
							end="${requestScope.lvo3.pbMain.end }">
							<div class="product-item men">
								<div class="product product_filter">
									<div class="product_image">
										<img
											src="${pageContext.request.contextPath}/auction_apply_images/${auction.auctionApplyDTO.auctionMainPic}"
											alt="">
									</div>
									<div class="product_info">
										<div class="product_price">
											<a
												href="${pageContext.request.contextPath }/front?command=AuctionArtDetail&auctionNo=${auction.auctionApplyDTO.auctionNo}">
												${auction.auctionApplyDTO.auctionTitle} </a>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>

					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- Deal of the week -->





<!-- Benefit -->

<div class="benefit">
	<div class="container">
		<div class="row benefit_row">
			<div class="col-lg-3 benefit_col">
				<div class="benefit_item d-flex flex-row align-items-center">
					<div class="benefit_icon">
						<img
							src="${pageContext.request.contextPath }/temp_images/technology.png" />
					</div>
					<div class="benefit_content">
						<h6>Technology</h6>
						<p>인픽쳐만의 그림 경매 시스템을 구축하여 더욱 만족도 높은 서비스를 제공</p>
					</div>
				</div>
			</div>
			<div class="col-lg-3 benefit_col">
				<div class="benefit_item d-flex flex-row align-items-center">
					<div class="benefit_icon">
						<img
							src="${pageContext.request.contextPath }/temp_images/value.png" />
					</div>
					<div class="benefit_content">
						<h6>Invaluable</h6>
						<p>
							지불한 금액의 가치를 넘어서는 경매품을<br> 제공하여 높은 만족도를 제공
						</p>
					</div>
				</div>
			</div>
			<div class="col-lg-3 benefit_col">
				<div class="benefit_item d-flex flex-row align-items-center">
					<div class="benefit_icon">
						<img
							src="${pageContext.request.contextPath }/temp_images/service.png" />
					</div>
					<div class="benefit_content">
						<h6>Service</h6>
						<p>회원들과의 지속적인 피드백과 정보 수집을 통해 원하는 정보와 서비스 제공</p>
					</div>
				</div>
			</div>
			<div class="col-lg-3 benefit_col">
				<div class="benefit_item d-flex flex-row align-items-center">
					<div class="benefit_icon">
						<img
							src="${pageContext.request.contextPath }/temp_images/community.png" />
					</div>
					<div class="benefit_content">
						<h6>Community</h6>
						<p>많은 인디 작가와의 제휴를 통한 작가 활동을 권유하고, 회원들과의 활발한 교류의 장 제공</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>



<div class="newsletter">
	<div class="container">
		<div class="row">
			<div class="col-lg-5">
				<div
					class="newsletter_foot d-flex flex-column justify-content-center align-items-lg-start align-items-md-center text-center">
					<h4>작가 신청</h4>
					<p>그림에 대한 꿈을 가지고 계신분이라면 인픽쳐와 함께 하세요. 인픽쳐가 많은 작가들에게 기회의 장을
						제공합니다.</p>
				</div>
			</div>
			<div class="col-lg-7">
				<form action="${pageContext.request.contextPath }/front" id="artist23" method="post">					
					<input type="hidden" name="command" value="ApplyArtistForm">
					<div
						class="newsletter_foot d-flex flex-md-row flex-column flex-xs-column align-items-center justify-content-center">
						
						<button id="newsletter_submit" type="submit"
							class="newsletter_submit_btn trans_300" value="Submit">
							작가신청 하기</button>
							
							
					</div>
				</form>
			</div>
		</div>
	</div>
</div>


<%-- <a href="${pageContext.request.contextPath }/front?command=ApplyArtistForm"> <img src="${pageContext.request.contextPath }/temp_images/applyArtist.jpg" class="newsletter_img"></img>
			<div class="newsletter_text">
				<p>
				<h3>Apply Artist</h3>
				</p>
			</div>
		</a> --%>