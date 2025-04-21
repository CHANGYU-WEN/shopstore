<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-tw">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>小小書局-商品列表</title>
  <link rel="stylesheet" href="./style.css">
</head>

<body>
	<%@include file="header.jsp"%>

	<jsp:include page="nav.jsp" flush="true">
		<jsp:param name="name" value="${customer.name}"/>
	</jsp:include>


<div class ="main">


	<div class ="content">
	
	<div class = "carditem">
		<ul>
		<c:forEach var="goods" items="${goodsList}" varStatus="status" >
			<li>
			<div class="card" >

			<div class="img">
 			<img src="./img/${goods.image}" alt="書本圖片" onclick="location.href='Controller?action=detail&id=${goods.id}'"/>
			</div>
			<%--img--%>

			<div class = "card-desc">
 				<div class ="cardBookName"><h5>書名:${goods.bookName}</h5></div>
  				<div class ="cardtext"><p>作者:${goods.author}</p></div>
  				<div class ="cardtext"><p>價格:NT${goods.price}</p></div>
			</div>

				<div class="card-button">
					<button onclick="location.href='Controller?action=add&pagename=Frompage&id=${goods.id}&name=${goods.bookName}&price=${goods.price}'">放入購物車</button>
				</div>
			
		
			</div>
			<%--card--%>

			</li>
		
		</c:forEach>
		</ul>
	</div>
	<%--carditem--%>

	<div class ="pagebutton">
	<ul>

		<li>
			<a href ="Controller?action=paging&page=prev">
			<button>上一頁</button>
			</a>
		</li>

		<c:forEach var="page" begin="1" end="${totalPage}">
		<li><a href ="Controller?action=paging&page=${page}">
			<button>${page}</button>
			</a>
		</li>
		</c:forEach>

		<li>
			<a href ="Controller?action=paging&page=next">
			<button>下一頁</button>
			</a>
		</li>
	</ul>

	</div>
	<!--pagebutton-->
	
	</div>
	<!---content-->

</div>
<!--main-->

	<%@include file="footer.jsp"%>

  
</body>
</html>