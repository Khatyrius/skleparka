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
   <h2>Dziękujemy za zakup, mail z potwierdzeniem wysyłki zostanie wysłany na maila przy potwierdzeniu płatności.</h2>
   
   <form action="checkoutFinale" method=GET>
  	<input type="hidden" name="command" value="simulate" />
    <input type=submit value="SYMULUJ PLATNOSC">
    </form>
   </div>
   </div>
   <jsp:include page="footer.jsp"/>
   	
    <script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
    <script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
    <script src="resources/js/bootstrap.js"></script>
  </body>
</html>