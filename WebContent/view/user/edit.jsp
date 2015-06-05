<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h2>${ localeResource.editUserPage}</h2>

<jsp:include page="/view/partial/errors.jsp"></jsp:include>



<form accept-charset="UTF-8" role="form" action="edit" method="post">
	<ol>
		<li><label for="email">${ localeResource.fieldEmail}</label> <input
			class="form-control" placeholder="E-mail" name="email" type="text"
			id="email" value="${userToEdit.email }"></li>
		<li><label for="first_name">${ localeResource.fieldFirstName}</label>
			<input class="form-control" placeholder="First Name"
			name="first_name" type="text" value="${userToEdit.firstName }">
		</li>
		<li><label>${ localeResource.fieldLastName}</label> <input
			class="form-control" placeholder="Last Name" name="last_name"
			type="text" value="${userToEdit.lastName }"></li>
		<li><input type="text" hidden="true" name="id"
			value="${ userToEdit.id}"> 
			<br>
			<input
			class="btn btn-lg btn-success btn-block" type="submit"
			value="${ localeResource.buttonEdit}"></li>
	</ol>
</form>

