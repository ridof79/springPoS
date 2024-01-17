<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Item</title>

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

    <%-- Alertify --%>
    <link rel="stylesheet" href='<c:url value="/assets/alertifyjs/css/alertify.min.css"/>'/>
    <link rel="stylesheet" href='<c:url value="/assets/alertifyjs/css/themes/bootstrap.min.css"/>'/>
    <script src="<c:url value="/assets/alertifyjs/alertify.min.js"/>"></script>

</head>
<body>
<div class="overlay"></div>
<div class="background-image"></div>
<nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0">
    <a class="navbar-brand col-sm-3 col-md-2 mr-0 ml-2" href="#"> Wide
        Point of Sales</a>
    <ul class="navbar-nav px-3">
        <li class="nav-item text-nowrap"><a class="nav-link"
                                            href="CashierServlet?action=logout">Sign out</a></li>
    </ul>
</nav>

<div class="container-fluid">
    <div class="row">
        <nav class="col-md-2 d-none d-md-block overlay-sidebar sidebar">
            <div class="sidebar-sticky">
                <ul class="nav flex-column">
                    <li class="nav-item"><a class="nav-link active" href="#">
                        <span data-feather="file"></span> Item
                    </a></li>
                </ul>
            </div>
        </nav>
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
            <div
                    class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
                <h1 class="h2">Edit Item</h1>

            </div>
            <c:set var="item" value="${item}"/>

            <form action="<c:url value='/items/edit'/>" method="post" modelAttribute="item" class="mt-3">

                <div class="form-group" hidden>
                    <label for="id">Item ID:</label>
                    <input type="text" class="form-control" id="id" name="id" value="${item.id}" readonly>
                </div>

                <div class="form-group row col-md-6">
                    <label for="itemCode" class="col-sm-2 col-form-label">Item Code:</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="itemCode" name="itemCode" required
                               value="${item.itemCode}">
                    </div>
                </div>

                <div class="form-group row col-md-6">
                    <label for="price" class="col-sm-2 col-form-label">Price:</label>
                    <div class="col-sm-10">
                        <input type="number" step="0.01" class="form-control" id="price" name="price" required
                               value="${item.price}">
                    </div>
                </div>

                <div class="form-group row col-md-6">
                    <label for="description" class="col-sm-2 col-form-label">Description:</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="description" name="description" required
                               value="${item.description}">
                    </div>
                </div>

                <div class="form-group row col-md-6">
                    <label for="type" class="col-sm-2 col-form-label">Type:</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="type" name="type" required value="${item.type}">
                    </div>
                </div>

                <div class="form-group row col-md-6">
                    <div class="col-sm-2 col-form-label">
                    </div>
                    <div class="col-sm-10">
                        <input type="checkbox" id="taxable" name="taxable" ${item.taxable ? "checked" : ""}>
                        <label class="form-check-label pl-1" for="taxable">Taxable</label>
                    </div>
                </div>

                <div class="form-group row col-md-6">
                    <div class="col-sm-2 col-form-label">
                    </div>
                    <div class="col-sm-10">
                        <button type="submit" class="btn btn-sm btn-outline-secondary">Update</button>
                        <a href="<c:url value="/items"/>">
                            <button type="button" class="btn btn-sm btn-outline-secondary">Cancel</button>
                        </a>
                    </div>
                </div>
            </form>
        </main>
    </div>
</div>

<input type="hidden" id="status" value="${status}"/>
<script src="<c:url value="/assets/js/item.js"/>"></script>

<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script
        src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
</body>
</html>
