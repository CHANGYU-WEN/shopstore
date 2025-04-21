
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-tw">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>小小書局-會員登入</title>
  <link rel="stylesheet" href="./style.css">
</head>

<body>
 	<%@include file="header.jsp"%>

	<jsp:include page="nav.jsp" flush="true">
		<jsp:param name="name" value="${customer.name}"/>
	</jsp:include>


<div class ="main">

	<div class ="login">


		<form action="Controller" name="login" method="post">
			<table>
			<tr>
				<td colspan="2"><strong>會員登入</strong></td>
			</tr>
			<tr>
				<td>帳號:</td>
				<td><input type="text" name="userid"></td>
			</tr>
			<tr>
				<td>密碼:</td>
				<td><input type="password" name="password"></td>
			</tr>
			<tr>
				<td><input type=submit value ="登入"></td>
				<td><input type=reset value ="重置"></td>
			</tr>
			</table>			
		<input type="hidden" name="action" value="login">
		</form>

		<ul>
			<c:forEach var="error" items="${errors}">
				<li style=" list-style: none; color:red;">${errors}</li>
			</c:forEach>
		</ul>

	
	</div>
	<!---login-->

</div>
<!--main-->

<%@include file="footer.jsp"%>

  
</body>
</html>