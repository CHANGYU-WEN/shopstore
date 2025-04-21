<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="zh-tw">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>小小書局</title>
  <link rel="stylesheet" href="./style.css">
</head>

<body>
	<%@include file="header.jsp"%>

	<jsp:include page="nav.jsp" flush="true">
		<jsp:param name="name" value="${customer.name}"/>
	</jsp:include>


<div class ="main">

	<div class ="content">
	


	
	</div>
	<!---content-->

</div>
<!--main-->

	<%@include file="footer.jsp"%>

  
</body>
</html>