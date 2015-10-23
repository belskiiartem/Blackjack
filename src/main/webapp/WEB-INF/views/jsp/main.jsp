<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
<form action="./login" method="POST">
	 <h2>Hello! You can use your card ID as login:</h2>
	 <input type="text" max="16"  name="cardId" size="20px"> <br>
	<input type="submit" value="submit">
</form>

</body>
</html>
