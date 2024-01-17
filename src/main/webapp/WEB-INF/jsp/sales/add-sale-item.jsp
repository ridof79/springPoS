<%@ page contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Add Sale Item</title>

    <%-- BootStrap --%>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">

    <%-- Icon --%>
    <link rel="icon" type="image/x-icon"
          href="<c:url value="/assets/icon.ico" />"/>

    <%-- CSS --%>
    <link href="<c:url value='/assets/css/style.css'/>" rel="stylesheet">
<body>

<div class="overlay"></div>
<div class="background-image"></div>

<%-- Header --%>
<nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0">
    <a class="navbar-brand col-sm-3 col-md-2 mr-0 ml-2" href="#">
        Wide Point of Sales
    </a>
    <ul class="navbar-nav px-3">
        <li class="nav-item text-nowrap">
            <a class="nav-link" href="../auth/logout">
                Sign out
            </a>
        </li>
    </ul>
</nav>

<div class="container-fluid">
    <div class="row">
        <%-- Sidebar --%>
        <nav class="col-md-2 d-none d-md-block overlay-sidebar sidebar">
            <div class="sidebar-sticky">
                <ul class="nav flex-column">
                    <li class="nav-item">
                        <a class="nav-link active" href="#">
                            Sale
                        </a>
                    </li>
                </ul>
            </div>
        </nav>

        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
            <div
                    class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
                <h1 class="h2">Add Sale Item</h1>

            </div>

            <form action='add-sale-item' method="post" class="mt-3">
                <div class="form-group row col-md-6">
                    <label class="col-sm-2 col-form-label">
                        Select Item:
                    </label>
                    <div class="col-sm-10">
                        <select name="itemCode" class="form-control" required>
                            <c:forEach items="${items}" var="item">
                                <option value="${item.itemCode}">
                                    <c:out value="${item.description}"/>
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="form-group row col-md-6">
                    <label for="quantity" class="col-sm-2 col-form-label">
                        Quantity:
                    </label>
                    <div class="col-sm-10">
                        <input type="number" id="quantity" name="quantity" class="form-control" required>
                    </div>
                </div>
                <div class="form-group row col-md-6">
                    <div class="col-sm-2 col-form-label">
                        <button type="submit" class="btn btn-sm btn-outline-secondary">
                            Submit
                        </button>
                    </div>
                </div>
            </form>
        </main>
    </div>
</div>

</body>
</html>