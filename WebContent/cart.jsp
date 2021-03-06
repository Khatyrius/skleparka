<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="pl.skleparka.beans.User"%>
<!DOCTYPE html>
<html>
  <head>
    <title>Skleparka - koszyk</title>
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
      <th>Cena</th>
    </tr>
  </thead>
  <!--Table head-->
  <!--Table body-->
  <tbody>
  <% int count = 1; %>
  <c:forEach var="temp" items="${cart}" > 
    <tr>
      <th scope="row"><%= count %></th>
      <td><img src="<c:out value="${temp.getImageUrl()}"/>" alt="Nie można załadować zdjęcia" height="250" width="250"></td>
      <td><c:out value="${temp.getProductName()}"/></td>
      <td><c:out value="${temp.getQuantity()}"/></td>
      <td><c:out value="${temp.getPrice() * temp.getQuantity()} zł"/></td>
      <td><form action="cart" method=POST>
      <input type="hidden" name="action" value="removeFromCart"/>
      <input type="hidden" name="productId" value="${temp.getProductId()}"/>
      <input type="submit" class="btn btn-sm btn-warning btn-block" value="Usuń z koszyka">   
      </form></td>  
    <% count++; %>
   </c:forEach>
      	<tr>
      	<td></td>
   		<td></td>
   		<td></td>
   		<td></td>
   		<td></td>
   		<td><form action="cart" method=POST>
    	 <input type="hidden" name="action" value="proceedToCheckout"/>
    	 <input type="hidden" name="productId" value ="0"/>
      	<input type="submit" class="btn btn-sm btn-success btn-block" value="Kup">   
      	</form></td> 
   	</tr>
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