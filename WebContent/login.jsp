<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <title>Skleparka - logowanie</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/styles.css" type="text/css" rel="stylesheet">
  </head>

  <body>
    
   <jsp:include page="headerChanger.jsp"/>
   	
	<c:if test="${errorMessage != null}">
    <div class="alert alert-danger" role="alert">
  	<c:out value="${errorMessage}"/>
	</div>
	</c:if>
	
    <div class="container">
		<div class="col-sm-6 col-md-4 col-md-offset-4">
			<form class="form-signin" action="login" method="post">
				<h2 class="form-signin-heading">Zaloguj się</h2>
				<input name="inputUsername" type="text" name="inputUsername" class="form-control" placeholder="Nazwa użytkownika" required autofocus />
				<input name="inputPassword" type="password" class="form-control" placeholder="Hasło" required />
				<button class="btn btn-lg btn-primary btn-block" type="submit" value="Sign In">Zaloguj</button>
				<a href="register">Zarejestruj</a>
			</form>
		</div>
    </div>
        
    <jsp:include page="footer.jsp"/>
    
    <script src="resources/js/bootstrap.bundle.min.js"></script>
  </body>
</html>