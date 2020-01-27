<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="pl.skleparka.beans.User"%>
<%@ page import="pl.skleparka.beans.BillingInfo" %>
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
  	<form action="shop" id="filterProducts" method="GET">
  	<input type="text" name="search" placeholder="Wpisz szukane">
  	<input type="hidden" name="command" value="filterProducts" />
  	<input type="submit">
  	</form>	
  	
  	<select name="parameter" form="filterProducts">
 	 <option value="name">Nazwa produktu</option>
 	 <option value="type">Typ produktu</option>
	</select>
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
    </tr>
  </thead>
  <!--Table head-->
  <!--Table body-->
  <tbody>
  <% int count = 1; %>
  <c:forEach var="temp" items="${productsList}" > 
    <tr>
      <th scope="row"><%= count %></th>
      <td><img src="<c:out value="${temp.getImageUrl()}"/>" alt="Nie można załadować zdjęcia" height="250" width="250"></td>
      <td><c:out value="${temp.getProductName()}"/></td>
      <td><c:out value="${temp.getQuantity()}"/></td>
      <td><c:out value="${temp.getType()}"/></td>
      <td><c:out value="${temp.getPrice()}zł"/></td>
      <td><c:out value="${temp.getDescription()}"/></td>
      <%
    	if (session.getAttribute("users") == null){
    %>
      	<td><form  action="review" method=GET>
  		<input type="hidden" name="productId" value="${temp.getProductId()}"/>
    	<input type=submit class="btn btn-warning" value="Zobacz oceny">
    	</form></td>
      <td><a href="login" class="btn btn-sm btn-success btn-block">Zaloguj się</a></td>
      <% } else  { %>
      <td><form  action="review" method=GET>
  		<input type="hidden" name="productId" value="${temp.getProductId()}"/>
    	<input type=submit class="btn btn-warning" value="Zobacz oceny">
    	</form></td>
		<c:if test="${temp.getQuantity() gt 0 }">
      <td><form action="cart" method=POST>
      <input type="hidden" name="action" value="addToCart"/>
      <input type="hidden" name="productId" value="${temp.getProductId()}"/>
      <input type="submit" class="btn btn-sm btn-warning btn-block" value="Dodaj do koszyka"> 
      </form></td>  
        </c:if> 
      <c:if test="${temp.getQuantity() eq 0 }">
      <td><input class="btn btn-sm btn-danger btn-block" value="Dodaj do koszyka"></td>
      </c:if> 
      <td><form action="buyProduct" method=GET>
      <input type="hidden" name="productId" value="${temp.getProductId()}"/>
      <input type="submit" class="btn btn-sm btn-success btn-block" value="Kup teraz"> 
      </form></td>  
      <% } %>
    </tr>
    <% count++; %>
   </c:forEach>
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