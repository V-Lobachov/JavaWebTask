<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<jsp:include page="/view/partial/errors.jsp"></jsp:include>

<h2>${localeResource.newAccount }</h2>

<form accept-charset="UTF-8" role="form" action="account?action=${act }" method="post">
	<ol>
		<li><label for="account">${localeResource.accountNumber }</label> <input
			class="form-control" placeholder="${localeResource.accountNumber }" name="account"
			type="text" id="account" value=""></li>
		<li><label for="balance">${localeResource.accountBalance }</label> <input
			class="form-control" placeholder="${localeResource.accountBalance }" name="balance" type="text"
			value=""></li>
		<li>
		<br>
		<input class="btn btn-lg btn-success btn-block" type="submit"
			value="${localeResource.accountButton  }"></li>
	</ol>
</form>
