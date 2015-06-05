<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<jsp:include page="/view/partial/errors.jsp"></jsp:include>

<h2>${localeResource.acBalanceTitle }</h2>



<form accept-charset="UTF-8" role="form"
	action="account?action=${act}&accountId=${accountId}" method="post">
	<ol>

		<li><label for="balance">${localeResource.acBalanceField }</label>
			<input class="form-control" placeholder="Balance. Format 0.00"
			name="balance" type="text" value=""></li>
		<li><br> <input class="btn btn-lg btn-success btn-block"
			type="submit" value="Update"></li>
	</ol>
</form>
