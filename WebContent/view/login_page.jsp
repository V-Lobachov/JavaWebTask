<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<jsp:include page="/view/partial/references.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="/view/partial/header.jsp"></jsp:include>


	<jsp:include page="/view/partial/errors.jsp"></jsp:include>


	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-4">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							<c:out value="${localeResource.loginTitle}"></c:out>
						</h3>
					</div>
					<div class="panel-body">
						<form accept-charset="UTF-8" role="form" action="login"
							method="post">
							<fieldset>
								<div class="form-group">
									<label>${localeResource.fieldEmail }</label> <input
										class="form-control" placeholder="E-mail" name="email"
										type="text">
								</div>
								<div class="form-group">
									<label>${localeResource.fieldPass }</label> <input
										class="form-control" placeholder="Password" name="password"
										type="password" value="">
								</div>

								<input class="btn btn-lg btn-success btn-block" type="submit"
									value="${localeResource.buttonLogin }">
							</fieldset>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>


</body>
</html>