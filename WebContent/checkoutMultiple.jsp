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
		<div class="col-sm-6 col-md-4 col-md-offset-4">
  	 <form action="checkoutMultiple" id="options" method="POST">
	</form>
	<H3>Opcje płatności</H3>
	<select name="paymentType" form="options">
	<c:if test="${billingInfo != null }">
 	 <option value="card">Karta</option>
 	 </c:if>
 	 <option value="paypall">Paypall</option>
 	 <option value="blik">BLIK</option>
	</select>
	
	<H3>Wybierz dostawce</H3>
	<select name="carrier" form="options">
 	 <option value="dhl">DHL - dostawa 20 zł</option>
 	 <option value="inPost">Inpost - dostawa 15 zł</option>
 	 <option value="pigeon">Gołab pocztowy - dostawa 100 zł(brak gwarancji dostarczenia produktu)</option>
	</select>
   	
   	<H2>Całość: <c:out value="${total} zł + dostawa"/></H2>
   	Adres: <input type="text" form="options" name="address" required>
   	<input type="submit" form="options" value="Zapłać">
   	</div>
    </div>
   <jsp:include page="footer.jsp"/>
   	
    <script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
    <script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
    <script src="resources/js/bootstrap.js"></script>
  </body>
</html>