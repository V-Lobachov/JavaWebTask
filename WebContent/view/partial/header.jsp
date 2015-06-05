<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="header">
	<div class="header_resize">
		<div class="logo">
			<h1>
				<a href="<c:url value="/" />">Lime Card<small>make it
						better do it faster makes us stronger</small></a>

			</h1>
		</div>
		<div class="clr"></div>
		<div class="menu_nav">


			<ul class="nav navbar-nav">

				<c:choose>
					<c:when test="${not empty user }">
						<li><a href="<c:url value="/" />">${ localeResource.headHome}</a></li>
						<li><a href="<c:url value="/user" />"> ${ localeResource.userPage}</a></li>
						<c:choose>
							<c:when test="${user.role == 'admin'}">
								<li><a href="<c:url value="/users" />">${ localeResource.headUsers}</a></li>
							</c:when>
							<c:otherwise></c:otherwise>
						</c:choose>
						<li><a href="<c:url value="/logout" />">${ localeResource.headLogout}</a></li>

					</c:when>
					<c:otherwise>
						<li><a href="<c:url value="/login" />">${ localeResource.headLogin}</a></li>
						<li><a href="registration">${ localeResource.headRegister}</a></li>
					</c:otherwise>

				</c:choose>
			</ul>



			<div class="searchform">
				<ul class="nav navbar-nav">
					<c:if test="${ user.role == 'admin' }">
						<li><input id='searchTrail' type="text"
							placeholder="${localeResource.searchField}" class="search-query"></li>
					</c:if>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-expanded="false">${ localeResource.language}
							<span class="caret"></span>
					</a>
						<ul class="dropdown-menu" role="menu">
							<li role="presentation"><a
								href="${requestScope['javax.servlet.forward.request_uri']}?locale=UA">UA</a></li>
							<li role="presentation"><a
								href="${requestScope['javax.servlet.forward.request_uri']}?locale=EN">EN</a></li>
						</ul></li>

				</ul>



			</div>

		</div>
		<div class="clr"></div>
	</div>
</div>

