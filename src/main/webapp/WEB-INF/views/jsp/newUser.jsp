<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
<h1>Hello, dear user!
Please put info in form below!</h1> 

<form action="./newUser" method="POST">
	 <p>Your first name: <input type="text" max="16"  name="firstName" size="20px">
	 <p>Your last name: <input type="text" max="16"  name="lastName" size="20px">
	 <p>Number of your card: <input type="text" max="16"  name="cardId" size="20px">
	 <p>Expire date for your card: <input type="date"  name="expiredDate" size="20px">
	 <p>Secure code: <input type="password"  name="secureCode" size="20px">
	 <p><input type="submit" value="submit">
</form>
 
</body>
</html>
