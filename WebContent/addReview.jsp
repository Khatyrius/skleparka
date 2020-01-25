<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <title>Skleparka - dodaj recenzje</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/styles.css" type="text/css" rel="stylesheet">
  </head>

  <body>
    
    <jsp:include page="headerChanger.jsp"/>
      	
    <div class="row" id="center">
    <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
		<form role="form" action="addReview" method="post">
			<fieldset>
				<h2>Dodaj nową recenzje:</h2>
				<hr class="colorgraph">
				<div class="form-group">
                    <input name="description" type="text" class="form-control"  placeholder="Komentarz" required autofocus />
				</div>
				<div class="form-group">
                   <input name="rating" min="1" max="5" type="number" name="quantity" class="form-control" placeholder="Ocena" required autofocus />
				</div>
				<hr class="colorgraph">
				<div class="row">
					<div class="center">
                        <input type="submit" class="btn btn-xl btn-success btn-block" value="Dodaj recenzje">
					</div>
					
				</div>
			</fieldset>
		</form>
	</div>
</div>
    
   <jsp:include page="footer.jsp"/>
    
    <script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
    <script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
    <script src="resources/js/bootstrap.js"></script>
  </body>
</html>