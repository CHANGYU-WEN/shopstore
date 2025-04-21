<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class = "nav">
    <ul>
        <li>歡迎

        <c:choose>
            <c:when test="${not empty sessionScope.customer.name}">
                ${sessionScope.customer.name}
            </c:when>
            <c:otherwise>
                訪客
            </c:otherwise>

        </c:choose>
        </li>

        <li><a href="login.jsp">登入</a></li>
        <li><a href="Controller?action=registerInit">註冊</a></li>
        <li><a href="Controller?action=list">商品列表</a></li>
        <li><a href="Controller?action=cart">購物車</a></li>
        <li><a href="Controller?action=logout">登出</a></li>
    </ul>
</div>
