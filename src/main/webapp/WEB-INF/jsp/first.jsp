<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Hello! World!</title>
    </head>
    <body>
        <h1>${message} </h1>
		<ul>
			<c:forEach var="user" items="${userlist}">
				<li>${user.name}</li>
			</c:forEach>
		</ul>
		
		<form id='testform' action="/example/confirm" method="POST">
			ID:<input type='text' id='id' name='id'><br>
			NAME:<input type='text' name='name'><br>
			AGE:<input type='text' name='age'><br>
			<button type="submit" value="提交">送出</button>
		</form>
    </body>
</html>
