<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="pl.skleparka.beans.User"%>
<!DOCTYPE html>
<html>
  <head>
    <title>Skleparka</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/styles.css" type="text/css" rel="stylesheet">
  </head>

  <body>  
    <jsp:include page="headerChanger.jsp"/>
	
   <div class="container">
  	<div class="row">
  	<!--Table-->
  	<table id="tablePreview" class="table table-hover table-bordered">
<!--Table head-->
  <thead>
    <tr>
      <th>#</th>
      <th>Zdjęcie</th>
      <th>Nazwa</th>
      <th>Ilość</th>
      <th>Typ</th>
      <th>Cena</th>
      <th>Opis</th>
      <th>Średnia ocen</th>
    </tr>
  </thead>
  <!--Table head-->
  <!--Table body-->
  <tbody>
  <c:forEach var="temp" items="${product}" > 
    <tr>
      <th scope="row"></th>
      <td><img src="<c:out value="${temp.getImageUrl()}"/>" alt="Nie można załadować zdjęcia" height="250" width="250"></td>
      <td><c:out value="${temp.getProductName()}"/></td>
      <td><c:out value="${temp.getQuantity()}"/></td>
      <td><c:out value="${temp.getType()}"/></td>
      <td><c:out value="${temp.getPrice()}zł"/></td>
      <td><c:out value="${temp.getDescription()}"/></td>    
      <td><c:out value="${avg}"/></td>  
    </tr>
   </c:forEach>
  </tbody>
  <!--Table body-->
</table>
 
 <br>
  	
<table id="tablePreview" class="table table-hover table-bordered">
<!--Table head-->
  <thead>
    <tr>
      <th>#</th>
      <th>Użytkownik</th>
      <th>Komentarz</th>
      <th>Ocena</th>
    </tr>
  </thead>
  <!--Table head-->
  <!--Table body-->
  <tbody>
  <c:forEach var="temp" items="${review}" > 
    <tr>
      <th scope="row"></th>
      <td><c:out value="${temp.getUsername()}"/></td>
      <td><c:out value="${temp.getDescription()}"/></td>
      <td><c:out value="${temp.getRating()}"/></td>
      <c:if test="${temp.getUserId() == users.getId()}">
      <td><form action="review" method=POST>
      <input type="hidden" name="command" value="removeReview"/>
      <input type="hidden" name="reviewId" value="${temp.getReviewId()}"/>
      <input type="submit" class="btn btn-sm btn-danger btn-block" value="Usuń ">   
      </form></td>  
      
      <td><form action="updateReview" method=GET>
      <input type="hidden" name="reviewId" value="${temp.getReviewId()}"/>
      <input type="submit" class="btn btn-sm btn-warning btn-block" value="Edytuj">   
      </form></td>  
      </c:if>
   </c:forEach>
   <c:if test="${users!=null }">
   <c:if test ="${!userList.contains(users) }">
   	<tr>
   		<td></td>
   		<td></td>
   		<td></td>
   		<td></td>
   		<td></td>
   		<td><form action="addReview" method="GET">
   		<input type="hidden" name="productId" value="${product.get(0).getProductId()}"/>
   	 	<input type=submit class="btn btn-sm btn-success btn-block" value="Dodaj nowy komentarz"/>
    	</form></td>
    </tr>
    </c:if>
   </c:if>
  </tbody>
  <!--Table body-->
</table>
<!--Table-->
  	
    </div>
</div>
   
   <jsp:include page="footer.jsp"/>
   	
    <script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
    <script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
    <script src="resources/js/bootstrap.js"></script>
  </body>
</html>