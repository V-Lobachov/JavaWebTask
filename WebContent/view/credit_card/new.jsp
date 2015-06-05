<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/view/partial/errors.jsp"></jsp:include>


<form accept-charset="UTF-8" role="form"
	action="creditCards?action=${act }" method="post">
	<ol>
		<li><label for="card">${localeResource.ccNumberField}</label> <input
			class="form-control" placeholder="${localeResource.ccNumberField}" name="card"
			type="text" id="card" value=""></li>
		<li><label for="account">${localeResource.ccAccount}</label> <select
			class="form-control" name="account">

				<c:forEach items="${accounts }" var="account">
					<option>${account.account }</option>
				</c:forEach>

		</select></li>

		<li><br> <input class="btn btn-lg btn-success btn-block"
			type="submit" value="${localeResource.accountButton }"></li>
	</ol>
</form>
