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

	<div class ="goodsDetail">

		<div class="goodsDetailPicture">
		<img src="./img/${goods.image}" alt="書本圖片"/>
		</div>

	<table>
		<tr>
			<td>
			<div class="goodsDetailTitle"><h2><strong>${goods.bookName}</strong></h2></div>
			</td>
		</tr>
		
		<tr>
			<td>書名:${goods.bookName}</td>
		</tr>
					
		<tr>
			<td>作者:${goods.author}</td>
		</tr>
		<tr>
			<td>譯者:${goods.translator}</td>
		</tr>
		<tr>
			<td>出版社:${goods.publisher}</td>
		</tr>
		<tr>
			<td>國際標準書號:${goods.isbn}</td>
		</tr>
		<tr>
			<td>語言:${goods.language}</td>
		</tr>
		<tr>
			<td>價格:NT${goods.price}</td>
		</tr>

		<tr>
			<td>
				<button onclick="location.href='Controller?action=add&pagename=Fromdetail&id=${goods.id}&name=${goods.bookName}&price=${goods.price}'">放入購物車</button>
			</td>
		</tr>



	</table>
	
		
	</div>
	<!---content-->

</div>
<!--main-->

<%@include file="footer.jsp"%>

  
</body>
</html>