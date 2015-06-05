<%@page import="com.epam.rank.task.model.UserModel"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
$(function(){
	
	$('#accountActivity').click(function(e) {
		var obj = e.target;
		var url = obj.getAttribute("href");
		$.get(e.target.getAttribute("href"), function(responseText) {

		});

		var params = url.split('&');
		for (var i = 0; i < params.length; i++) {
			var hash = params[i].split('=');
			var key = hash[0];
			var value = hash[1];
			if (key == 'unfreeze') {
				value = "Freeze";
				obj.href = url.replace("&unfreeze=true", "");
			}else{
				value = "Unfreeze";
				obj.href = url + "&unfreeze=true";
				
			}
			obj.text = value;
			
			if('<%= ((UserModel)request.getSession().getAttribute("user")).getRole() %>' != 'admin' ){
				obj.remove();				
			}
		}

		return false;
	});

	
	
	
});
</script>


<h2>${localeResource.bankAccountPage }</h2>
<table class="table table-striped">
	<thead>
		<tr>
			<th>${localeResource.accountId}</th>
			<th>${localeResource.accountNumber}</th>
			<th>${localeResource.accountBalance}</th>
			<th>${localeResource.accountActive}</th>
			<th>${localeResource.accountEdit}</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${bankAccounts}" var="account">
			<tr>
				<td>${account.id }</td>
				<td>${account.account }</td>
				<td>${account.balance }</td>
				<c:choose>
					<c:when test="${ account.active}">
						<td><a onclick="return confirm()" id="accountActivity"
							href="<c:url value="/user/account?action=delete&accountId=${account.id }" />">Freeze</a></td>
					</c:when>
					<c:otherwise>
						<td><c:if test="${ user.role == 'admin' }">
								<a onclick="return confirm()" id="accountActivity"
									href="<c:url value="/user/account?action=delete&accountId=${account.id }&unfreeze=true" />">Unfreeze</a>
							</c:if></td>
					</c:otherwise>
				</c:choose>


				<td><a
					href="<c:url value="/user/account?action=edit&accountId=${account.id }" />">${localeResource.accountEdit}</a></td>

			</tr>
		</c:forEach>
	</tbody>
</table>



<a href="<c:url value="/user/account?action=new&id=${user.id }" />">${localeResource.newAccountButton}</a>