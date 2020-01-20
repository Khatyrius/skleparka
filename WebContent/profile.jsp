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
  <!--Table-->
  <h3>Dane podstawowe:</h3>
	<table id="tablePreview" class="table table-hover table-bordered">
	<!--Table head-->
  <thead>
    <tr>
      <th>#</th>
      <th>Email</th>
      <th>Imie</th>
      <th>Drugie imie</th>
      <th>Nazwisko</th>
    </tr>
  </thead>
  <!--Table head-->
  <!--Table body-->
  <tbody>
    <tr>
      <th scope="row">1</th>
      <td><c:out value="${users.getEmail()}"/></td>
      <td><c:out value="${users.getFirstName()}"/></td>
      <td><c:out value="${users.getMiddleName()}"/></td>
      <td><c:out value="${users.getLastName()}"/></td>
      <td><input type="submit" class="btn btn-sm btn-warning btn-block" value="Zaktalizuj dane"></td>
      <td><input type="submit" class="btn btn-sm btn-success btn-block" value="Zmień hasło"></td>
    </tr>
  </tbody>
  <!--Table body-->
</table>

  <h3>Dane do płatności:</h3>
<table id="tablePreview" class="table table-hover table-bordered">
<!--Table head-->
  <thead>
    <tr>
      <th>#</th>
      <th>Numer karty</th>
      <th>Data wygaśnięcia</th>
      <th>Kod CVV</th>
      <th>Adres do wysyłki</th>
    </tr>
  </thead>
  <!--Table head-->
  <!--Table body-->
  <tbody>
    <tr>
      <th scope="row">1</th>
      <td><c:out value="${billingInfo.getCardNumber()}"/></td>
      <td><c:out value="${billingInfo.getExpirationDate()}"/></td>
      <td><c:out value="${billingInfo.getSecurityCode()}"/></td>
      <td><c:out value="${billingInfo.getBillingAddress()}"/></td>
      <td><input type="submit" class="btn btn-sm btn-warning btn-block" value="Zaktalizuj dane"></td>
      <td><input type="submit" class="btn btn-sm btn-success btn-block" value="Dodaj dane do płatności"></td>
    </tr>
  </tbody>
  <!--Table body-->
</table>
  </div>
</div>
   
   <jsp:include page="footer.jsp"/>
   	
    <script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
    <script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
    <script src="resources/js/bootstrap.js"></script>
  </body>
</html>