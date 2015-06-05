<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="ex" uri="/WEB-INF/users.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>



<h2 id="title">${ localeResource.adminTitle}</h2>

<h2>${ localeResource.usersTableTitle}</h2>
<%-- <table class="table table-striped">
	<thead>
		<tr>
			<th>${ localeResource.id}</th>
			<th>${ localeResource.fieldFirstName}</th>
			<th>${ localeResource.fieldLastName}</th>
			<th>${ localeResource.fieldEmail}</th>
			<th>${ localeResource.fieldRole}</th>
			<th>${ lacaleResource.fieldEdit }</th>
		</tr>
	</thead>
	<tbody>
		<ex:users data="${users}"></ex:users>
	</tbody>
</table>
 --%>

<table class="table table-striped">
	<thead>
		<tr>
			<th>${ localeResource.id}</th>
			<th><a href="<c:url value="/users?f=first_name" />">${ localeResource.fieldFirstName}</a></th>
			<th><a href="<c:url value="/users?l=last_name" />">${ localeResource.fieldLastName}</a></th>
			<th><a href="<c:url value="/users?email=email" />">${ localeResource.fieldEmail}</a></th>
			<th>${ localeResource.fieldRole}</th>
			<th>${ lacaleResource.fieldEdit }</th>




		</tr>
	</thead>
	<tbody>
		<c:forEach items="${users }" var="user1">
			<tr>
				<td>${user1.id}</td>
				<td>${user1.firstName}</td>
				<td>${user1.lastName}</td>
				<td>${user1.email}</td>
				<td id="role">${user1.role}</td>
				<td><a class='btn btn-primary btn-xs btn-lg btn-block'
					href='<c:url value="/user/account?action=index&id=${user1.id }" />'>${localeResource.userAccountButton }</a>
					<a class='btn btn-success btn-xs btn-lg btn-block'
					href='<c:url value="/user/history?action=index&userId=${user1.id }" />'>
						${localeResource.accountHistoryButton } </a> <c:choose>
						<c:when test="${user1.role =='admin' }">


							<a onclick="return confirm()" id='roleTriger'
								class='btn btn-danger btn-xs btn-lg btn-block'
								href='<c:url value="users?action=new&role=user&userId=${user1.id }" />'>
								${localeResource.changeRoleButton }</a>
						</c:when>
						<c:otherwise>
							<a onclick="return confirm()" id='roleTriger'
								class='btn btn-danger btn-xs btn-lg btn-block'
								href='<c:url value="users?action=new&role=admin&userId=${user1.id }" />'>
								${localeResource.changeRoleButton }</a>
						</c:otherwise>
					</c:choose></td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<p class="pages">


	<c:forEach var="i" begin="1" end="${amountOfPages}" step="1"
		varStatus="loop">

		<a href="<c:url value="/users?page=${ i + loop.begin-1}" />">${ i + loop.begin-1}</a>
	</c:forEach>

</p>

