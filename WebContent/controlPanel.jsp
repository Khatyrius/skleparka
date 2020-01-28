<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="pl.skleparka.beans.User"%>
<%@ page import="pl.skleparka.beans.BillingInfo" %>
<%@ page import="pl.skleparka.beans.Order" %>
<%@ page import="pl.skleparka.beans.Shipment" %>
<%@ page import="pl.skleparka.beans.Payment" %>

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
  	<form class ="col-md-2 col-md-offset-1" action="controlPanel" method="GET">
  	<input type="hidden" name="command" value="controlUsers" />
    <input type=submit class="btn btn-primary" value="Kontrola użytkowników">
    </form>
      
    <form class ="col-md-2" action="controlPanel" method=GET>
  	<input type="hidden" name="command" value="controlProducts" />
    <input type=submit class="btn btn-success" value="Kontrola produktów">
    </form>
    
    <form class ="col-md-2" action="controlPanel" method="GET">
  	<input type="hidden" name="command" value="controlOrders" />
    <input type=submit class="btn btn-danger" value="Kontrola zamówień">
    </form>
    
    <form class ="col-md-2" action="controlPanel" method="GET">
  	<input type="hidden" name="command" value="controlShipments" />
    <input type=submit class="btn btn-warning" value="Kontrola dostaw">
    </form>
    
    <form class ="col-md-2" action="controlPanel" method="GET">
  	<input type="hidden" name="command" value="controlPayments" />
    <input type=submit class="btn btn-info" value="Kontrola płatności">
    </form>
    </div>
  	</div>
  	
	<div class="container">
	<br>
	<c:choose>
  	<c:when test="${command == 'controlUsers' || command == 'filterUsers'}"> 
  	     
    <form action="controlPanel" id="filterUsers" method="GET">
  	<input type="text" name="search" placeholder="Wpisz szukane">
  	<input type="hidden" name="command" value="filterUsers" />
  	<input type="submit">
	</form>

	<select name="parameter" form="filterUsers">
  	<option value="id">Id</option>
 	 <option value="username">Nazwa uzytkownika</option>
 	 <option value="email">Email</option>
 	 <option value="fristName">Imię</option>
 	 <option value="lastName">Nazwisko</option>
 	 <option value="active">Aktywny</option>
	</select>
     
	<table id="tablePreview" class="table table-hover table-bordered">
  	<thead>
    	<tr>
      		<th>ID</th>
      		<th>Nazwa użytkownika</th>
      		<th>Email</th>
      		<th>Imię</th>
      		<th>Drugie Imię</th>
      		<th>Nazwisko</th>
      		<th>Aktywny</th>
    	</tr>
  	</thead>
  	<tbody>
  	<c:forEach var="temp" items="${userList}" > 
    <tr>
      <th scope="row"><c:out value="${temp.getId()}"/></th>
      <td><c:out value="${temp.getUsername()}"/></td>
      <td><c:out value="${temp.getEmail()}"/></td>
      <td><c:out value="${temp.getFirstName()}"/></td>
      <td><c:out value="${temp.getMiddleName()}"/></td>
      <td><c:out value="${temp.getLastName()}"/></td>
      <td><c:out value="${temp.isActive()}"/></td>
      	<c:choose>
  		<c:when test="${temp.isActive() == true}"> 
  		<td><form action="controlPanel" method=POST>
      <input type="hidden" name="action" value="blockUser"/>
      <input type="hidden" name="userId" value="${temp.getId()}"/>
      <input type="submit" class="btn btn-sm btn-danger btn-block" value="Blokuj użytkownika"> 
      </form></td>  
  		</c:when>
		<c:when test="${temp.isActive() == false}"> 
  			<td><form action="controlPanel" method=POST>
      <input type="hidden" name="action" value="activateUser"/>
      <input type="hidden" name="userId" value="${temp.getId()}"/>
      <input type="submit" class="btn btn-sm btn-success btn-block" value="Aktywuj użytkownika"> 
      </form></td> 
  		</c:when>
		</c:choose>
    </tr>
   	</c:forEach>
  	</tbody>
	</table>
	</c:when>
	
  	<c:when test="${command == 'controlProducts' || command == 'filterProducts'}">
  	 <form action="controlPanel" id="filterProducts" method="GET">
  	<input type="text" name="search" placeholder="Wpisz szukane">
  	<input type="hidden" name="command" value="filterProducts" />
  	<input type="submit">
  	
  	<select name="parameter" form="filterProducts">
  	<option value="id">Id</option>
 	 <option value="name">Nazwa produktu</option>
 	 <option value="type">Typ produktu</option>
	</select>
  	
	</form>
	<table id="tablePreview" class="table table-hover table-bordered">
  	<thead>
    <tr>
      <th>ID</th>
      <th>Zdjęcie</th>
      <th>Nazwa</th>
      <th>Ilość</th>
      <th>Typ</th>
      <th>Cena</th>
      <th>Opis</th>
    </tr>
  </thead>
  	<tbody>
 	<c:forEach var="temp" items="${productsList}" > 
    <tr>
      <th scope="row"><c:out value="${temp.getProductId()}"/></th>
      <td><img src="<c:out value="${temp.getImageUrl()}"/>" alt="Nie można załadować zdjęcia" height="100" width="100"></td>
      <td><c:out value="${temp.getProductName()}"/></td>
      <td><c:out value="${temp.getQuantity()}"/></td>
      <td><c:out value="${temp.getType()}"/></td>
      <td><c:out value="${temp.getPrice()}zł"/></td>
      <td><c:out value="${temp.getDescription()}"/></td>
        <td><form action="updateProduct" method="GET">
  		<input type="hidden" name="productId" value="${temp.getProductId()}" />
   	 	<input type=submit class="btn btn-sm btn-warning btn-block" value="Uaktualnij dane">
    	</form></td>
      
    </tr>
   	</c:forEach>
   	<tr>
   		<td></td>
   		<td></td>
   		<td></td>
   		<td></td>
   		<td></td>
   		<td></td>
   		<td></td>
   		<td><form action="addProduct" method="GET">
   	 	<input type=submit class="btn btn-sm btn-success btn-block" value="Dodaj nowy produkt">
    	</form></td>
   	</tr>
  	</tbody>
	</table>
	</c:when>
	
  	<c:when test="${command == 'controlOrders'}"> 
  	<table id="tablePreview" class="table table-hover table-bordered">
  	<thead>
    <tr>
      <th>ID</th>
      <th>Id użytkownika</th>
      <th>Data zamówienia</th>
      <th>Status</th>
      <th>Wartość</th>
    </tr>
  </thead>
  	<tbody>
 	<c:forEach var="temp" items="${orderList}" > 
    <tr>
      <th scope="row"><c:out value="${temp.getOrderId()}"/></th>
      <td><c:out value="${temp.getUserId()}"/></td>
      <td><c:out value="${temp.getOrderDate()}"/></td>
      <td><c:out value="${temp.getStatus()}"/></td>
      <td><c:out value="${temp.getTotal()} zł"/></td>
    </tr>
   	</c:forEach>
  	</tbody>
	</table>
  	 </c:when>
  	 
  	<c:when test="${command == 'controlShipments'}"> 
	<table id="tablePreview" class="table table-hover table-bordered">
  	<thead>
    <tr>
      <th>ID</th>
      <th>ID zamówienia</th>
      <th>ID użytkownika</th>
      <th>Numer do śledzenia</th>
      <th>Adres zwrotny</th>
      <th>Dostawca</th>
      <th>Status</th>
      <th>Płatność za dostawe</th>
    </tr>
  </thead>
  	<tbody>
 	<c:forEach var="temp" items="${shipmentsList}" > 
    <tr>
      <th scope="row"><c:out value="${temp.getShipmentId()}"/></th>
      <td><c:out value="${temp.getOrderId()}"/></td>
      <td><c:out value="${temp.getUserId()}"/></td>
      <td><c:out value="${temp.getTrackingNumber()}"/></td>
      <td><c:out value="${temp.getReturnAddress()}"/></td>
      <td><c:out value="${temp.getCarrier()}"/></td>
      <td><c:out value="${temp.getStatus()}"/></td>
       <td><c:out value="${temp.getCharge()} zł"/></td>
    </tr>
   	</c:forEach>
  	</tbody>
	</table>
  	</c:when>
	
  	<c:when test="${command == 'controlPayments'}"> 
<table id="tablePreview" class="table table-hover table-bordered">
  	<thead>
    <tr>
      <th>ID</th>
      <th>Typ</th>
      <th>ID użytkownika</th>
      <th>ID zamówienia</th>
      <th>Status</th>
      <th>Całość</th>
    </tr>
  </thead>
  	<tbody>
 	<c:forEach var="temp" items="${paymentsList}" > 
    <tr>
      <th scope="row"><c:out value="${temp.getPaymentId()}"/></th>
      <td><c:out value="${temp.getPaymentType()}"/></td>
      <td><c:out value="${temp.getUserId()}"/></td>
      <td><c:out value="${temp.getOrderId()}"/></td>
      <td><c:out value="${temp.getStatus()}"/></td>
      <td><c:out value="${temp.getAmount()} zł"/></td>
    </tr>
   	</c:forEach>
  	</tbody>
	</table>
  	</c:when>
  	<c:otherwise>
  	 <div class ="text-center">
  	 <h2>NIEZLA  PRÓBA  ALE  NIC  TU  NIE  MA</h2>
  	 </div> 
  	</c:otherwise>
	</c:choose>
	</div>
  	
   
   <jsp:include page="footer.jsp"/>
   	
    <script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
    <script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
    <script src="resources/js/bootstrap.js"></script>
  </body>
</html>