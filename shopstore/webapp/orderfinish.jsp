<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-tw">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>小小書局-訂單完成</title>
  <link rel="stylesheet" href="./style.css">
</head>

<body>
<%@include file="header.jsp"%>

<jsp:include page="nav.jsp" flush="true">
	<jsp:param name="name" value="${customer.name}"/>
</jsp:include>


<div class ="main">
	
	<div class ="login-success">
	
		<h2>謝謝您的購物</h2>
		<h2>您的訂單編號是:${orderid}</h2>
		<h3><a href="Controller?action=index">繼續購物</a></h3>
	</div>
	<!---login-->

</div>
<!--main-->

<%@include file="footer.jsp"%>
  
</body>
</html>