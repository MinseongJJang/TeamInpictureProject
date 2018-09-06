<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript">
   $(document).ready(function(){
      $("#bidOkBtn").click(function(){
         
          if($("#bidPirce").val() =="" || $("#bidPrice").val() == 0 || $("#bidPrice").val() <= ${param.maxBid} || $("#bidPrice").val() <= $("#beginPrice").val() ){
             alert("입찰가는 현재 최고입찰가보다 높아야 합니다.");
          }else{
             if(confirm($("#bidPrice").val()+"원 입찰하시겠습니까?")){
                $.ajax({
                   type : "post",
                   url : "${pageContext.request.contextPath}/front",
                   data : $("#modalForm").serialize(),
                   success:function(result){
                      $("#maxBid").text(result);
                   }//
                });//ajax
             }//if   
          }//else
       });//click
       
          var nowTime = new Date();
          var endTime = $("#endTime").text().substring(23).split(":");
          var endHour =endTime[0];
          var endMinute = endTime[1];
          var endTime = new Date();
          endTime.setHours(endHour);
          endTime.setMinutes(endMinute);
          var diff = endTime.getTime() - nowTime.getTime();
         setInterval(function ()
         {
            diff = diff-1000;
            var time = (diff-1000)/1000;
            var hour = Math.floor(time/3600);
            var min = Math.floor(time/60);
            var sec = Math.floor(time%60);
   
            $("#timer").html(hour+" 시간 " + min + " 분 " + sec + " 초 남았습니다.");
            if(min=='0' && hour=='0' && sec=='0'){
               $("#endAuction").submit();
            }
         }, 1000);//interval
       
   });//ready
  
</script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<c:choose>
   <c:when
      test="${requestScope.auctionDTO.inpictureMemberDTO.memberType != '2' || sessionScope.mvo != null}">
      <form action="${pageContext.request.contextPath }/front"
         id="endAuction">
         <input type="hidden" id="beginPrice"
            value="${requestScope.auctionDTO.auctionApplyDTO.auctionBeginPrice }">
         <input type="hidden" name="command" value="EndAuction"> <input
            type="hidden" name="auctionNo"
            value="${requestScope.auctionDTO.auctionApplyDTO.auctionNo }">
      </form>
      <script
         src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
      <div class="main1">
         <h2>경매 정보</h2>
         <div class="content">
            <form method="post"
               action="${pageContext.request.contextPath }/front" id="endAuction"
               enctype="multipart/form-data">
               <input type="hidden" name="command" value="EndAuction"> <input
                  type="hidden" name="auctionNo"
                  value="${requestScope.auctionDTO.auctionApplyDTO.auctionNo }">
               <table class="table" style="border-bottom: 1px solid #dbdbdb"
                  style="border-top:1px solid #dbdbdb">
                  <tr>
                     <td>경매 그림</td>
                     <td colspan="2"><img
                        src="${pageContext.request.contextPath }/auction_apply_images/${requestScope.auctionDTO.auctionApplyDTO.auctionMainPic}"></td>
                  </tr>
                  <tr>
                     <td>경매 번호</td>
                     <td colspan="2">${requestScope.auctionDTO.auctionApplyDTO.auctionNo }</td>
                  </tr>
                  <tr>
                     <td colspan="3"></td>
                  </tr>
                  <tr>
                     <td>경매 이름</td>
                     <td colspan="2">${requestScope.auctionDTO.auctionApplyDTO.auctionTitle }</td>
                  </tr>
                  <tr>
                     <td colspan="3"></td>
                  </tr>
                  <tr>
                     <td>작가 이름</td>
                     <td colspan="2">${requestScope.auctionDTO.auctionApplyDTO.inpictureMemberDTO.name}
                     </td>
                  </tr>
                  <tr>
                     <td colspan="3"></td>
                  </tr>
                  <tr>
                     <td colspan="3"></td>
                  </tr>
                  <tr>
                     <td>경매 그림 소개</td>
                     <td colspan="2">${requestScope.auctionDTO.auctionApplyDTO.auctionContent }</td>
                  </tr>
                  <tr>
                     <td colspan="3"></td>
                  </tr>
                  <tr>
                     <td>경매 시작가</td>
                     <td colspan="2">${requestScope.auctionDTO.auctionApplyDTO.auctionBeginPrice }</td>
                  </tr>
                  <tr>
                     <td colspan="3"></td>
                  </tr>
                  <tr>
                     <td>즉시 구매가</td>
                     <td colspan="2">${requestScope.auctionDTO.auctionApplyDTO.auctionPromptlyPrice }</td>
                  </tr>
                  <tr>
                     <td colspan="3"></td>
                  </tr>
                  <tr>
                     <td>경매 시작시간</td>
                     <td colspan="2">${requestScope.auctionDTO.auctionApplyDTO.auctionBeginTime}</td>
                  </tr>
                  <tr>
                     <td colspan="3"></td>
                  </tr>
                  <tr>
                     <td>경매 종료시간</td>
                     <td colspan="2">${requestScope.auctionDTO.auctionApplyDTO.auctionEndTime }</td>
                  </tr>
                  <tr>
                     <td colspan="3"></td>
                  </tr>
                  <tr>
                     <td>경매 남은시간</td>
                     <td colspan="2"><span id="timer"></span></td>
                  </tr>
                  <tr>
                     <td colspan="3"></td>
                  </tr>
                  <tr>
                     <td>최고 입찰가</td>
                     <c:choose>
                        <c:when test="${param.maxBid == 0}">
                           <td colspan="2" id="maxBid">${requestScope.auctionDTO.auctionApplyDTO.auctionBeginPrice }</td>
                        </c:when>
                        <c:otherwise>
                           <td colspan="2" id="maxBid">${param.maxBid}</td>
                        </c:otherwise>
                     </c:choose>
                  </tr>
               </table>
               <div align="center">
                  <button type="button" class="newsletter_submit_btn"
                     data-toggle="modal" data-target="#myModal" id="bidBtn">입찰</button>
               </div>
            </form>
         </div>
      </div>

      <!-- Modal -->
      <div id="myModal" class="modal fade" role="dialog">
         <div class="modal-dialog">
            <!-- Modal content-->
            <form action="${pageContext.request.contextPath }/front"
               method="post" id="modalForm">
               <input type="hidden" name="auctionNo"
                  value="${requestScope.auctionDTO.auctionApplyDTO.auctionNo}">
               <input type="hidden" name="command" value="Bid">
               <div class="modal-content">
                  <div class="modal-header">
                     <button type="button" class="close" data-dismiss="modal">&times;</button>
                  </div>
                  <div class="modal-body">
                     <p>
                        입찰가 : <input type="number" id="bidPrice" name="bidPrice"
                           required="required">
                     </p>
                  </div>
                  <div class="modal-footer">
                     <button type="button" class="newsletter_submit_btn"
                        data-dismiss="modal" id="bidOkBtn">입찰</button>
                     <button type="button" class="newsletter_submit_btn"
                        data-dismiss="modal" id="promptlyBtn">즉시구매</button>
                  </div>
               </div>
            </form>
         </div>
      </div>
   </c:when>
   <c:otherwise>
      <script type="text/javascript">
         location.href="${pageContext.request.contextPath}/member/session_invalid.jsp";
      </script>
   </c:otherwise>
</c:choose>
