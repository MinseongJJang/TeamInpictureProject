<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.2.1.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>
$(function(){
	$('#out').click(function(){
		location.href="${pageContext.request.contextPath}/index.jsp";
	});
	if(${spaceuservo.id==null}){
		$('#loginModal').modal();
	}
});
</script>
<style type="text/css">
body {
    background-color: #222;
}
#spinner.active {
    display: block;
}
#spinner {
    display: none;
    position: absolute;
    height: 60px;
    width: 60px;
    top: 40%;
    left: 48%;
    z-index: 1;
}
.ball {
    position: absolute;
    display: block;
    background-color: #567f9a;
    left: 24px;
    width: 12px;
    height: 12px;
    border-radius: 6px;
}
#first {
    -webkit-animation-timing-function: cubic-bezier(0.5, 0.3, 0.9, 0.9);
    -webkit-animation-name: rotate; 
    -webkit-animation-duration: 2s; 
    -webkit-animation-iteration-count: infinite;
    -webkit-transform-origin: 6px 30px;
    -moz-animation-timing-function: cubic-bezier(0.5, 0.3, 0.9, 0.9);
    -moz-animation-name: rotate; 
    -moz-animation-duration: 2s; 
    -moz-animation-iteration-count: infinite;
    -moz-transform-origin: 6px 30px;

}
#second {
    -webkit-animation-timing-function: cubic-bezier(0.5, 0.5, 0.9, 0.9);
    -webkit-animation-name: rotate; 
    -webkit-animation-duration: 2s; 
    -webkit-animation-iteration-count: infinite;
    -webkit-transform-origin: 6px 30px;
	  -moz-animation-timing-function: cubic-bezier(0.5, 0.5, 0.9, 0.9);
    -moz-animation-name: rotate; 
    -moz-animation-duration: 2s; 
    -moz-animation-iteration-count: infinite;
    -moz-transform-origin: 6px 30px;
}
#third {
    -webkit-animation-timing-function: cubic-bezier(0.5, 0.7, 0.9, 0.9);
    -webkit-animation-name: rotate; 
    -webkit-animation-duration: 2s; 
    -webkit-animation-iteration-count: infinite;
    -webkit-transform-origin: 6px 30px;
	  -moz-animation-timing-function: cubic-bezier(0.5, 0.7, 0.9, 0.9);
    -moz-animation-name: rotate; 
    -moz-animation-duration: 2s; 
    -moz-animation-iteration-count: infinite;
    -moz-transform-origin: 6px 30px;
}
@-webkit-keyframes rotate {
  0% {
    -webkit-transform: rotate(0deg) scale(1);
  }
  100% { 
    -webkit-transform: rotate(1440deg) scale(1); 
  }
}​

@-moz-keyframes rotate {
  0% {
    -moz-transform: rotate(0deg) scale(1);
  }
  100% { 
    -moz-transform: rotate(1440deg) scale(1); 
  }
}
</style>
</head>
<body>
    <div id="spinner" class="active">
        <span id="first" class="ball"></span>
        <span id="second" class="ball"></span>
        <span id="third" class="ball"></span>
    </div>
      <div class="modal fade" id="loginModal" role="dialog" id="loginModal">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title"></h4>
				</div>
				<div class="modal-body">
					회원권한이 없습니다. 메인화면으로 이동합니다.
				</div>
				<div class="modal-footer">
					<input type="button" class="btn btn-default" data-dismiss="modal" id="out" value="닫기">
				</div>
			</div>

		</div>
	</div>
</body>
</html>