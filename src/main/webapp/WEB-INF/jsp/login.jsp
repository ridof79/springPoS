<%@ page contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>WideMart Point of Sales</title>

<%-- BootStrap --%>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">

<%-- Icon --%>
<link rel="icon" type="image/x-icon"
	href="<c:url value="/assets/icon.ico" />" />

<%-- CSS --%>
<link href="<c:url value="/assets/css/front.css"/>" rel="stylesheet">

<%-- Alertify --%>
<link rel="stylesheet" href='<c:url value="/assets/alertifyjs/css/alertify.min.css"/>' />
<link rel="stylesheet"	href='<c:url value="/assets/alertifyjs/css/themes/bootstrap.min.css"/>' />
<script src="<c:url value="/assets/alertifyjs/alertify.min.js"/>"></script>


</head>

<body>
<div class="overlay"></div>
	<div class="background-image"></div>
	<div
		class="bd-masthead mb-3 position-absolute top-50 start-50 translate-middle">
		<div class="container-xxl bd-gutter">

			<div class="row">
				<div class="col-md-8 text-left">
					<div class="card text-white transparent-card mb-3">
						<div class="card-body">

							<img src="<c:url value='../assets/image/pos_logo.png'/>" width="200"
								height="200" alt="Pos" class="d-none d-sm-block mx-auto mb-5">
							<p class="lead mb-2	text-white">Selamat datang di platform
								Point of Sale kami, tempat di mana kemudahan dan efisiensi
								bertemu dalam satu pengalaman berbelanja yang tak terlupakan.</p>
						</div>
					</div>
				</div>

				<div class="col-6 col-md-4 text-left">
					<div class="card rounded-3 text-white bg-dark mb-3">
						<div class="card-body">
							<form name='login' action="<c:url value='/auth/login' />" method='POST'>
								<h2 class="text-center mb-3">Login!</h2>

								<div class="mb-3">
									<label for="username" class="form-label">Username:</label> <input
										type="text" id="username" name="username"
										class="form-control text-white bg-dark"
										placeholder="Enter Username" required>
								</div>

								<div class="mb-3">
									<label for="password" class="form-label">Password:</label> <input
										type="password" id="password" name="password"
										class="form-control text-white bg-dark"
										placeholder="Enter Password" required>
								</div>

								<button type="submit" class="btn btn-primary">Login</button>

							</form>
						</div>
					</div>
					<div class="card text-white bg-dark ">
						<div class="card-body">
							<h3 class="text-center mb-3">Don't have an account?</h3>
							<a href='<c:url value="/auth/register"/>' class="d-grid">
								<button type="button" class="btn btn-primary">Register</button>
							</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<input type="hidden" id="status" value="${status}" />
	<script src="<c:url value="/assets/js/login.js"/>"></script>
	<script	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
		crossorigin="anonymous"></script>
</body>
</html>