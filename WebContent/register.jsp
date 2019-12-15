<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    
    <nav class = "navbar navbar-inverse navbar-fixed-top">
      <div class="container">
        <a href="index.jsp" class="navbar-brand">Skleparka</a>
        
        
        <button class="navbar-toggle" data-toggle="collapse" data-target=".navHeaderCollapse">
          <span class="glyphicon glyphicon-list"></span>
        </button>
        
        <div class="collapse navbar-collapse navHeaderCollapse">
          <ul class="nav navbar-nav navbar-right">
            <li class="active"><a href="#">Główna</a></li>	
            <li><a href="login">Zaloguj się</a></li>
          </ul>
        </div>
        
      </div>
    </nav>
    
    <div class="container">
		<div class="col-sm-6 col-md-4 col-md-offset-4">
			<form class="form-signin" method="post" action="register">
				<h2 class="form-signin-heading">Zarejestruj się</h2>
				<input name="inputName" type="text" class="form-control" placeholder="Imie" required autofocus />
				<input name="inputSurame" type="text" class="form-control" placeholder="Nazwisko" required autofocus />
				<input name="inputPhone" type="number" class="form-control" placeholder="Numer telefonu" required autofocus />
				<input name="inputAddress" type="text" class="form-control" placeholder="Adres" required autofocus />
				<input name="inputCity" type="text" class="form-	control" placeholder="Miasto" required autofocus />
				<input name="inputEmail" type="email" class="form-control" placeholder="Email" required autofocus />
				<input name="inputUsername" type="text" name="inputUsername" class="form-control" placeholder="Nazwa użytkownika" required autofocus />
				<input name="inputPassword" type="password" class="form-control" placeholder="Hasło" required />
				<button class="btn btn-lg btn-primary btn-block" type="submit" >Zarejestruj</button>
			</form>
		</div>
    </div>
    
    <footer class="footer">
      <div class="container">
                <p class="navbar-text">Skleparka - developed by noobs</p>
      </div>
    </footer>
    
    <script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
    <script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
    <script src="resources/js/bootstrap.js"></script>
  </body>
</html>