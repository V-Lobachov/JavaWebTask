<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h2>${localeResource.accountHistory}</h2>

<table class="table table-striped">
	<thead>
		<tr>

			<th>${localeResource.historyId}</th>
			<th>${localeResource.historyMessage}</th>
			<th>${localeResource.historyAccount}</th>
			<th>${localeResource.historyDate}</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${history}" var="story">
			<tr>
				<td>${story.id }</td>
				<td>${story.message }</td>
				<td>${story.account }</td>
				<td>${story.created_at }</td>


			</tr>
		</c:forEach>
	</tbody>
</table>

<p class="pages">


	<c:forEach var="i" begin="1" end="${amountOfPages}" step="1"
		varStatus="loop">

		<a
			href="<c:url value="/user/history?userId=${user_id }&page=${ i + loop.begin-1}" />">${ i + loop.begin-1}</a>
	</c:forEach>

</p>
