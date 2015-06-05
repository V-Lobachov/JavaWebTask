<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${not empty errors}">

		<c:if test="${not empty errors}">
			<div>
				<c:forEach var="error" items="${errors}">

					<div class="alert alert-danger fade in" role="alert">
						<span class="glyphicon glyphicon-exclamation-sign"
							aria-hidden="true"></span> <span class="sr-only">Error:</span>
						<c:out value="${error}" />
					</div>
				</c:forEach>
				<c:remove var="errors" />
			</div>
		</c:if>
	
</c:if>

