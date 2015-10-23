<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<body>
<h1>feefefHello, <c:out value="${firstName}" /> <c:out value="${lastName}" />!</h1>
You have attached account <c:out value="${account}" /> with balance $<c:out value="${balance}" />
You payment history is:

<table>
 	<tr>
		<th>Date</th><th>Action</th><th>amount</th>
	</tr>
	<c:if test="${fn:length(journal) eq 0}">
		<tr><th>Haven't history yet</th></tr>
	</c:if>
	<c:forEach var="journal" items="${journal}" varStatus="loop">
	<tr>
		<th><c:out value="${journal.date}" /></th>
		<th><c:out value="${journal.actionType}" /></th>
		<th><c:out value="${journal.amount}" /></th>
	</tr>
	</c:forEach>

</table>

<form action="./startGame" method="POST">
	 <h2>And now you can start new game with bet:</h2>
	 <input type="text" max="16"  name="bet" size="20px" value="1"> <br>
	<input type="submit" value="submit">
</form>
 
</body>
</html>
