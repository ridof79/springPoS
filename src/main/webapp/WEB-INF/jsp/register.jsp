<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Account</title>
<%-- BootStrap --%>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

<%-- Icon --%>
<link rel="icon" type="image/x-icon" href="<c:url value="/assets/icon.ico" />"/>

<%-- CSS --%>
<link href="<c:url value="/assets/css/front.css"/>" rel="stylesheet">

<%-- Alertify --%>
<link rel="stylesheet"	href='<c:url value="../assets/alertifyjs/css/alertify.min.css"/>' />
<link rel="stylesheet"	href='<c:url value="../assets/alertifyjs/css/themes/bootstrap.min.css"/>' />
<script src="<c:url value="../assets/alertifyjs/alertify.min.js"/>"></script>

</head>
<body>

	<div class="overlay"></div>
	<div class="background-image"></div>
	<div class=" mb-3 position-absolute top-50 start-50 translate-middle">
		<div class="container-xxl">
			<div class="col-auto text-left">
				<div class="card text-white bg-transparent">
					<div class="card-body">
						<img src="<c:url value="../assets/image/pos_logo.png"/>" width="100"
							height="100" alt="Pos" class="mx-auto d-block mt-5 mb-5 ">
						<h3 class="text-center mb-3">Create New Account:</h3>

						<form action="<c:url value='/auth/register'/>" method="post"
                              		modelAttribute="cashierDto">
							<div class="mb-3">
								<label for="username">Username:</label> <input type="text"
									id="username" name="username"
									class="form-control text-white bg-transparent" required>
							</div>
							<div class="mb-3">
								<label for="password">Password:</label> <input type="password"
									id="password" name="password"
									class="form-control text-white bg-transparent" required>
							</div>
							<div class="mb-3">
								<label for="name">Cashier Name:</label> <input type="text"
									id="name" name="name"
									class="form-control text-white bg-transparent" required>
							</div>

							<div class="mb-3">
								<input type="submit" class="btn btn-secondary"
									value="Create User">
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

		<input type="hidden" id="createStatus" value="${createStatus}" />
		<script src="<c:url value="/assets/js/register.js"/>"></script>
    	<script
    		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
    		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
    		crossorigin="anonymous"></script>
	</body>
</html>
