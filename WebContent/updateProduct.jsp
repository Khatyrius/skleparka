<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <title>Skleparka - rejestracja</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/css/styles.css" type="text/css" rel="stylesheet">
  </head>

  <body>
    
    <jsp:include page="headerChanger.jsp"/>
      	
    <div class="row" id="center">
    <div class="col-xs-12 col-sm-8 col-md-6 col-sm-offset-2 col-md-offset-3">
		<form role="form" action="updateProduct" method="post">
			<fieldset>
				<h2>Zaktualizuj produkt:</h2>
				<hr class="colorgraph">
				<div class="form-group">
                    Nazwa produktu: <input name="productName" value='<c:out value="${updateProduct.getProductName() }"></c:out>' type="text" class="form-control"  placeholder="Nazwa produktu" required autofocus />
				</div>
				<div class="form-group">
                    Ilość: <input name="quantity" min="0" value='<c:out value="${updateProduct.getQuantity() }"></c:out>' type="text" name="quantity" class="form-control" placeholder="Ilość" required autofocus />
				</div>
				<div class="form-group">
                    Typ: <input name="type" value='<c:out value="${updateProduct.getType() }"></c:out>' type="text" class="form-control" placeholder="Typ" required autofocus/>
				</div>
				<div class="form-group">
                    Cena: <input name="price" value='<c:out value="${updateProduct.getPrice() }"></c:out>' type="number" step="0.01" min="0" class="form-control" placeholder="Cena" required autofocus/>
				</div>
				<div class="form-group">
                    Opis: <input name="description" value='<c:out value="${updateProduct.getDescription() }"></c:out>' type="text" name="description" class="form-control" placeholder="Opis" required autofocus />
				</div>
				<div class="form-group">
                    Ścieżka do zdjęcia:<input name="imageUrl" value='<c:out value="${updateProduct.getImageUrl() }"></c:out>' type="text" name="imageUrl" class="form-control" placeholder="Url do zdjecia" required autofocus />
				</div>
				<hr class="colorgraph">
				<div class="row">
					<div class="center">
                        <input type="submit" class="btn btn-xl btn-primary btn-block" value="Aktualizuj">
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