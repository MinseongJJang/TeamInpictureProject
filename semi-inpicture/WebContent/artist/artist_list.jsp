<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <script type="text/javascript">
 	$(document).ready(function() {
 		$("#artist td").click(function(){
 			//alert($(this).parent().find(":hidden").val());
 			location.href="${pageContext.request.contextPath}/front?command=DetailArtist&id="+$(this).parent().find(":hidden").val();
 		});
 	});
 </script>
 <style>
/* Center website */
.main {
	font-family: Arial;
	padding: 40px;
	background-color: #f1f1f1;
	max-width: 1000px;
	margin: 200px auto;
}

h1 {
	font-size: 50px;
	word-break: break-all;
}

.myrow {
	margin: 10px -16px;
}

/* Add padding BETWEEN each column */
.myrow, .myrow>.column {
	padding: 8px;
}

/* Create three equal columns that floats next to each other */
.column {
	float: left;
	width: 33.33%;
	display: none; /* Hide all elements by default */
}

/* Clear floats after rows */
.myrow:after {
	content: "";
	display: table;
	clear: both;
}

/* Content */
.content {
	background-color: white;
	padding: 10px;
}

/* The "show" class is added to the filtered elements */
.show {
	display: block;
}
.pagination a {
    color: black;
    float: left;
    padding: 8px 16px;
    text-decoration: none;
    transition: background-color .3s;
}

/* Style the active/current link */
.pagination a.active {
    background-color: dodgerblue;
    color: white;
}

/* Add a grey background color on mouse-over */
.pagination a:hover:not(.active) {background-color: #ddd;}

</style>

	<!-- MAIN (Center website) -->
	<div class="main">
		<h2>작가 목록</h2>
		<!-- Portfolio Gallery Grid -->
		<div class="row myrow">
		
			<c:forEach var="ldto" items="${requestScope.ldto.artistList}">
				<div class="column nature">
					<div class="content" onclick="location.href='${pageContext.request.contextPath}/front?command=DetailArtist&id=${ldto.inpictureMemberDTO.id }'">
						<img src="${pageContext.request.contextPath}/artist_images/${ldto.artist_main_pic }" style="width: 100%; height:300px;">
						<h4>${ldto.inpictureMemberDTO.name  }</h4>
					</div>
				</div>
			</c:forEach>
		</div>
		<c:set var="pb" value="${requestScope.ldto.pb}"></c:set>
		<div class="pagingArea">
			<ul class="pagination">
				<c:if test="${pb.previousPageGroup}">
					<li><a
						href="front?command=ArtistList&pageNo=${pb.startPageOfPageGroup-1}">&laquo;</a>
					</li>
				</c:if>
				<c:forEach var="i" begin="${pb.startPageOfPageGroup}"
					end="${pb.endPageOfPageGroup}">
					<c:choose>
						<c:when test="${pb.nowPage!=i}">
							<li><a href="front?command=ArtistList&pageNo=${i}">${i}</a></li>
						</c:when>
						<c:otherwise>
							<li class="active"><a href="#">${i}</a></li>
						</c:otherwise>
					</c:choose>
			&nbsp;
			</c:forEach>
				<c:if test="${pb.nextPageGroup}">
					<li><a
						href="front?command=ArtistList&pageNo=${pb.endPageOfPageGroup+1}">&raquo;</a></li>
				</c:if>
			</ul>
		</div>
	</div>

<!-- END GRID -->
<!-- END MAIN -->
<script>
	filterSelection("all")
	function filterSelection(c) {
		var x, i;
		x = document.getElementsByClassName("column");
		if (c == "all")
			c = "";
		for (i = 0; i < x.length; i++) {
			w3RemoveClass(x[i], "show");
			if (x[i].className.indexOf(c) > -1)
				w3AddClass(x[i], "show");
		}
	}
	function w3AddClass(element, name) {
		var i, arr1, arr2;
		arr1 = element.className.split(" ");
		arr2 = name.split(" ");
		for (i = 0; i < arr2.length; i++) {
			if (arr1.indexOf(arr2[i]) == -1) {
				element.className += " " + arr2[i];
			}
		}
	}
	function w3RemoveClass(element, name) {
		var i, arr1, arr2;
		arr1 = element.className.split(" ");
		arr2 = name.split(" ");
		for (i = 0; i < arr2.length; i++) {
			while (arr1.indexOf(arr2[i]) > -1) {
				arr1.splice(arr1.indexOf(arr2[i]), 1);
			}
		}
		element.className = arr1.join(" ");
	}
</script>

<!-- ----------------------------------------------------------------------------- -->
