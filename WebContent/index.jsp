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
  	<c:if test="${param.success eq 1}">
    <div class="alert alert-success" role="alert">
  	Zarejsetrowano pomyślnie!
	</div>
	</c:if>
    <jsp:include page="headerChanger.jsp"/>
    <div class="container">
   	<div class="row">
   <h2>Spicy jalapeno bacon ipsum dolor amet alcatra spare ribs short ribs kielbasa shoulder pork tail biltong. Drumstick frankfurter shank pork loin tail kielbasa. Pork loin ham salami meatball pork belly. Ground round shoulder chicken kevin, jerky corned beef tongue drumstick brisket buffalo porchetta.<br><br>

Cow chicken kevin turducken chislic. Buffalo cupim turducken venison tenderloin beef chuck picanha alcatra meatball bacon tongue meatloaf rump short loin. Pancetta rump meatloaf shankle ham hock chislic turkey turducken. Venison capicola tail tenderloin.<br><br>

Strip steak venison beef leberkas, tenderloin capicola pancetta tri-tip. Shank venison ground round, tri-tip spare ribs pork loin t-bone landjaeger pastrami meatball bresaola filet mignon. Spare ribs pig short loin tail cow shoulder bresaola ball tip biltong shank corned beef tenderloin landjaeger ham hock leberkas. Venison chicken t-bone, chislic meatloaf fatback pancetta drumstick pork chop. Boudin tail ribeye bacon biltong short loin. Alcatra spare ribs leberkas rump doner fatback chuck tail meatloaf bresaola short ribs.<br><br>

Beef ribs jerky tail, burgdoggen pancetta ham buffalo. Doner turducken pork loin landjaeger ground round. Ham porchetta pork beef ribs shank. Landjaeger shank brisket, pig shoulder flank drumstick corned beef. Sausage pancetta biltong burgdoggen frankfurter shoulder. Strip steak rump kevin beef jerky ham landjaeger pork chicken ham hock tenderloin venison pork belly chislic turducken.<br><br>

Biltong drumstick brisket alcatra short loin shankle tail chicken picanha pork turducken cow tri-tip beef ground round. Capicola t-bone spare ribs, swine shoulder shank boudin chicken frankfurter ham ham hock alcatra filet mignon. Pork chop meatloaf kielbasa, pancetta swine porchetta buffalo shank ground round. Strip steak porchetta prosciutto, short ribs bacon sirloin ham hock spare ribs ribeye sausage picanha pork cupim meatloaf filet mignon. Shoulder drumstick leberkas prosciutto biltong ground round beef ribs kielbasa sirloin.</h2>
   </div>
   </div>
   <jsp:include page="footer.jsp"/>
   	
    <script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
    <script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
    <script src="resources/js/bootstrap.js"></script>
  </body>
</html>