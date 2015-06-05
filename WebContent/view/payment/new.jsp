<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2>${localeResource.paymentPage }</h2>

<jsp:include page="/view/partial/errors.jsp"></jsp:include>


<form accept-charset="UTF-8" role="form" action="payment?action=${act }"
	method="post">
	<ol>
		<li><label for="amount">${localeResource.paymentAmount }</label> <input
			class="form-control" placeholder="${localeResource.paymentAmountField }" name="amount"
			type="text" id="amount" value=""></li>
		<li><label for="card">${localeResource.paymentCC }</label> <select name="card"
			class="form-control">
				<c:forEach items="${activeCC}" var="cc">
					<option>${cc.card }</option>
				</c:forEach>
		</select></li>
		<li><label for="bill">${localeResource.paymentBill }</label> <input
			class="form-control" placeholder="${localeResource.paymentBill }" name="bill" type="text"
			value=""></li>
		<li><br> <input class="btn btn-lg btn-success btn-block"
			type="submit" value="${localeResource.paymentButton }"></li>
	</ol>
</form>
