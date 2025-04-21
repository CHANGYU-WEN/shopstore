<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-tw">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>小小書局-購物車</title>
  <link rel="stylesheet" href="./style.css">

	<script>
		function calc(rowid)
		{
			var quantityInput = document.getElementById("quantityItem" + rowid);
			var quantity = parseInt(quantityInput.value);

			var priceItem ="priceItem"+rowid;
			var subItem ="subtotalItem"+rowid;

			var price =parseInt(document.getElementById(priceItem).innerText);
			var subTotal = parseInt(document.getElementById(subItem).innerText);
			var total = parseInt(document.getElementById("Total").innerText);

			if(isNaN(quantity)|| quantity<=0)
			{
				alert("請輸入正確的數字");
				quantity=1;
				quantityInput.value=quantity;
				quantityInput.focus();
			}

			updateSubtotal(rowid,quantity);
			updateTotal();
		}

		function changeQty(rowid, diff)
		{
			var quantityInput = document.getElementById("quantityItem" + rowid);
			var quantity = parseInt(quantityInput.value);

			if(isNaN(quantity))
			{
				quantity=1;
			}else
			{
				quantity += diff;
			}

			if(quantity<1)
			{
				quantity=1;
			}

			quantityInput.value=quantity;
			updateSubtotal(rowid,quantity);
			updateTotal();
		}

		function updateSubtotal(rowid,quantity)
		{
			var priceItem ="priceItem"+rowid;
			var subItem ="subtotalItem"+rowid;
			var price =parseInt(document.getElementById(priceItem).innerText);
			var subTotal = parseInt(document.getElementById(subItem).innerText);

			subtotaltemp = price *quantity;
			document.getElementById(subItem).innerText = subtotaltemp;
		}
		function updateTotal()
		{
			total =0.0;
			subtotalAllItems = document.querySelectorAll("[id^='subtotalItem']");
			subtotalAllItems.forEach(item => {total+=parseInt(item.innerText)});
			document.getElementById("Total").innerText = total;
		}

	</script>
</head>

<body>

<%@include file="header.jsp"%>

<jsp:include page="nav.jsp" flush="true">
	<jsp:param name="name" value="${customer.name}"/>
</jsp:include>

<div class = main>

	<div class = cart>

		<div class="cartLine"><h2>您的購物車列表</h2></div>
	
		<div class="cartTable">
			<form action="Controller?action=submitOrder" method="post">
		<table>
			<tr>
				<th>商品名稱</th>
     				<th>商品數量</th>
      				<th>商品單價</th>
      				<th>金額小計</th>

			</tr>
			<c:forEach var="row" items="${cart}">
			<tr>
				<td><div class="cartLineBook"><input type="hidden" name="goodsid" value="${row.goodsid}" />${row.goodsName}</div></td>
				<td>
					<div class="cartQty">
						<button type="button" onclick="changeQty(${row.goodsid}, -1)">-</button>
					<input type="text" name="quantityItem${row.goodsid}" id="quantityItem${row.goodsid}"  value="${row.quantity}" onblur="calc(${row.goodsid})">
						<button type="button" onclick="changeQty(${row.goodsid}, 1)">+</button>
					</div>
				</td>
				<td><span id="priceItem${row.goodsid}">${row.price}</span></td>
				<td><span id ="subtotalItem${row.goodsid}">${row.price}</span></td>
			</tr>
			</c:forEach>

			<tr>
				<td colspan="2"></td>
				<td>總計</td>
				<td><span id="Total">${total}</span></td>
			</tr>

			<c:if test="${not empty cart}">
			<tr>
				<td colspan="4">
					<button type="submit">送出訂單</button>
				</td>
			</tr>

			</c:if>
			<input type ="hidden" name="action" value="submitOrder">
		</table>
			</form>
		</div>
		<%--cartTable--%>


	</div>
	<%--cart--%>

</div>
<%--main--%>

</body>

<%@include file="footer.jsp"%>
</html>