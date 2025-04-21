<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="zh-tw">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>小小書局-會員註冊</title>
  <link rel="stylesheet" href="./style.css">
	<script>
		function verify(myform)
		{
			var errorMessage ="";
			if(myform.userid.value =="")
			{
				errorMessage+="帳號不能為空\n";
			}
			if(myform.password.value =="")
			{
				errorMessage+="密碼不能為空\n";
			}
			if(myform.name.value =="")
			{
				errorMessage+="姓名不能為空\n";
			}
			if(myform.password.value != myform.password2.value)
			{
				errorMessage+="兩次輸入的密碼不同\n";
			}

			var date = /^(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)$/;
			if(!date.test(myform.birthday.value))
			{
				errorMessage+="日期格式錯誤(YYYY-MM-DD)\n";
			}
			if(errorMessage=="")
			{
				return true;
			}
			else
			{
				alert(errorMessage);
				return false;
			}
		}
	</script>
</head>

<body>

	<%@include file="header.jsp"%>

	<jsp:include page="nav.jsp" flush="true">
		<jsp:param name="name" value="${customer.name}"/>
	</jsp:include>


<div class ="main">


	<div class ="register">

		<%--測試--%>
		<%--
		<ul>
			<c:forEach var="error" item="${error}">
				<li>${error}</li>

			</c:forEach>>
		</ul>>
		--%>

		<form name="myform" action ="Controller" method="post" onsubmit="return verify(this);" >
			<table>
			<tr>
				<td colspan="2"><strong>會員註冊</strong></td>
			</tr>
			<tr>
				<td>帳號:</td>
				<td><input type="text" name="userid"/></td>
			</tr>
			<tr>
				<td>姓名:</td>
				<td><input type="text" name="name" /></td>
			</tr>
			<tr>
				<td>密碼:</td>
				<td><input type="password" name="password" /></td>
			</tr>
			<tr>
				<td>再次輸入密碼:</td>
				<td><input type="password" name="password2" /></td>
			</tr>
			<tr>
				<td>出生日期:</td>
				<td><input type="text" name="birthday" /></td>
			</tr>
			<tr>
				<td>地址:</td>
				<td><input type="text" name="address" /></td>
			</tr>
			<tr>
				<td>電話號碼:</td>
				<td><input type="text" name="phone" /></td>
			</tr>
			<tr>
				<td><input type="submit" value ="註冊"></td>
				<td><input type="reset" value ="重置"></td>
			</tr>
			</table>
		<input type ="hidden" name="action" value="register">
		</form>

	</div>
	<!---login-->

</div>
<!--main-->

	<%@include file="footer.jsp"%>

</body>
</html>