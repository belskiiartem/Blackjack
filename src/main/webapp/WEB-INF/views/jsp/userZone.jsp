<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
<h1>Hello, <c:out value="${gamerInfo.firstName}" /> <c:out value="${gamerInfo.lastName}" />!</h1>
You have attached account <c:out value="${account.cardNumber}" /> with balance $<c:out value="${account.balance}" />
You payment history is:

<table>
 	<tr>
		<th>Date</th><th>Action</th><th>amount</th>
	</tr>
	<c:forEach var="journal" items="${journal}" varStatus="loop">
	<tr>
		<th><c:out value="${journal.date}" /></th>
		<th><c:out value="${journal.actionType}" /></th>
		<th><c:out value="${journal.amount}" /></th>
	</tr>
	</c:forEach>
</table>

<form action="./gameZone" method="POST">
	 <h2>And now you can start new game with bet:</h2>
	 <input type="text" max="16"  name="bet" size="20px" value="1"> <br>
	<input type="submit" value="submit">
</form>
 
</body>
</html>
