<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>			
			        작가 작품 목록
               <table class="table">
                  <tr>
                   <td rowspan="3">
                    <c:if test="${requestScope.paging.previous}">  

                      <a href="${pageContext.request.contextPath}/front?command=DetailArtist&rnum=${requestScope.paging.start-1}&id=${param.id}">&laquo;</a>


                      	<img width="40"  src="${pageContext.request.contextPath }/temp_images/left2.jpg">
                      </a>

                    </c:if>
                   </td>
                     <c:forEach var="avo" items="${requestScope.list }">
                        <td rowspan="2"><a href="${pageContext.request.contextPath }/front?command=DetailArt&artNo=${avo.artNo}">
                          <img src="${pageContext.request.contextPath }/art_images/${avo.artMainPic}" class="img-art"></a>
                        </td>
                     </c:forEach>
                    <td rowspan="3">
                    <c:if test="${requestScope.paging.next}">

                     <a href="${pageContext.request.contextPath}/front?command=DetailArtist&rnum=${requestScope.paging.start+1}&id=${param.id}">&raquo;</a>


                     	<img width="40" src="${pageContext.request.contextPath }/temp_images/right2.jpg">
                     </a>

					</c:if>
                   </td> 
                  
                   
                  </tr>
                  <tr>
                  </tr>
                  <tr>
	                  <c:forEach var="avo" items="${requestScope.list }" varStatus="info">
	                        <td>${avo.artTitle } </td>
	                  </c:forEach>
                  </tr>
                  
               </table>

