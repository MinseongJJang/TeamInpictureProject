<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

		<div class="trans_300">
            <div class="main_slider_content">
               <h1>작가 작품 목록</h1>
               <table class="table">
                  <tr>
                   <td rowspan="2">
                    <c:if test="${requestScope.paging.previous}">  
                      <a href="${pageContext.request.contextPath}/front?command=ArtList&rnum=${requestScope.paging.start-1}">&laquo;</a>
                    </c:if>
                   </td>
                     <c:forEach var="avo" items="${requestScope.list }">
                        <td><a href="${pageContext.request.contextPath }/front?command=DetailArt&artNo=${avo.artNo}">
                          <img src="${pageContext.request.contextPath }/art_images/${avo.artMainPic }" class="img-art"></a>
                        </td>
                     </c:forEach>
                   <td rowspan="2">
                    <c:if test="${requestScope.paging.next}">
                     <a href="${pageContext.request.contextPath}/front?command=ArtList&rnum=${requestScope.paging.start+1}">&raquo;</a>
					</c:if>
                   </td>
                  </tr>
                  <tr>
	                  <c:forEach var="avo" items="${requestScope.list }">
	                        <td>${avo.artTitle }</td>
	                  </c:forEach>
                  </tr>
               </table>
            </div>
			</div>

