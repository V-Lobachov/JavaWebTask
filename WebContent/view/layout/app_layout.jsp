<%@page import="com.epam.rank.task.model.UserModel"%>
<%@page import="org.apache.tomcat.util.security.MD5Encoder"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<jsp:include page="/view/partial/references.jsp"></jsp:include>

</head>
<body>
	<div class="main">
		<jsp:include page="/view/partial/header.jsp"></jsp:include>



		<div class="content">
			<div class="content_resize">
				<div class="mainbar">
					<div class="article">
						<jsp:include page="${page}"></jsp:include>
					</div>



				</div>
				<div class="sidebar">
					<div class="gadget">
						<big class="star">${ localeResource.sideBarMenu}</big>
						<div class="clr"></div>
						<ul class="sb_menu">

							<li><a href="<c:url value="/user/edit?id=${user.id }" />">${ localeResource.menuSettings}</a></li>
							<li><a
								href="<c:url value="/user/account?action=index&id=${user.id }" />">
									${ localeResource.menuAccount}</a></li>

							<li><a
								href="<c:url value="/user/creditCards?action=index" />"> ${ localeResource.menuCredit}</a></li>
							<li><a href="<c:url value="/user/payment?action=new" />">
									${localeResource.newPayment}</a></li>
							<li><a href="<c:url value="/user/history?action=new&userId=${user.id }" />">
									${localeResource.bankHistory}</a></li>

						</ul>
					</div>
				</div>
				<div class="clr"></div>
			</div>
		</div>






		<div class="fbg">
			<div class="fbg_resize">
				<div class="col c1">
					<h2>
						<span>${ localeResource.contacts}</span>
					</h2>
					<p>
						<a href="#">hiken111@gmail.com</a>
					</p>
					<p>
						+1 (123) 444-5677<br /> +1 (123) 444-5678
					</p>

				</div>
				<div class="clr"></div>
			</div>
		</div>
		<jsp:include page="/view/partial/footer.jsp"></jsp:include>
	</div>
</body>
</html>