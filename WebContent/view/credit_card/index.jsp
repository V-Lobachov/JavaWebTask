<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<h2>${localeResource.titleUserCreditCard }</h2>


<table class="table table-striped">
	<thead>
		<tr>

			<th>${localeResource.ccIdField}</th>
			<th>${localeResource.ccNumberField}</th>
			<th>${localeResource.ccAccount}</th>
			<th>${localeResource.delete}</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${creditCards}" var="card">
			<tr>
				<td>${card.id }</td>
				<td>${card.card }</td>
				<td>${card.account }</td>
				<td><a
					href="<c:url value="/user/creditCards?action=delete&ccId=${card.id }" />">${localeResource.delete}</a></td>


			</tr>
		</c:forEach>
	</tbody>
</table>


<a href="<c:url value="/user/creditCards?action=new&id=${user.id }" />">${ localeResource.linkNewCredit}</a>


