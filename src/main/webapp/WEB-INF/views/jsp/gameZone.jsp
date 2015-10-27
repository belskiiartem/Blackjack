<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
<h1>game zone</h1>

<p>Bank card:
	<c:forEach var="bank" items="${bankCardsOnHend}" varStatus="loop">
		<li><c:out value="${bank.rank}" /> of <c:out value="${bank.color}" /></li>
	</c:forEach>
<p>Bank count:
<c:out value="${bankCount}"/>	
<p>Your card
	<c:forEach var="gamer" items="${gamerCardsOnHend}" varStatus="loop">
		<li><c:out value="${gamer.rank}" /> of <c:out value="${gamer.color}" /></li>
	</c:forEach>
<p>You count:
<c:out value="${gamerCount}"/>	

<form action="./game" method="POST" >
 	<p>hit: <input type="radio" name="action" value="hit" checked>
	<p>stand: <input type="radio" name="action" value="stand">
	<p><input type="submit" value="Confirm">
</form>

	
</body>
</html>
