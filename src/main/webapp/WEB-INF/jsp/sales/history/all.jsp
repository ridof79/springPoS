<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Sale History</title>

    <%-- BootStrap --%>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
          crossorigin="anonymous">

    <%-- Icon --%>
    <link rel="icon" type="image/x-icon"
          href="<c:url value="../../assets/icon.ico" />"/>

    <%-- CSS --%>
    <link href="<c:url value='../../assets/css/style.css'/>" rel="stylesheet">
</head>
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
            <a class="nav-link" href="../../auth/logout">
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
                        <a class="nav-link" href="../../home">
                            Dashboard
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="../../sales">
                            Sale
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="../../items">
                            Items
                        </a>
                    </li>
                </ul>
                <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
                    <span>History sale</span>
                    <a class="d-flex align-items-center text-muted" href="#"></a>
                </h6>
                <ul class="nav flex-column mb-2">
                    <li class="nav-item">
                        <a class="nav-link active" href="#">
                            All
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link " href="<c:url value="/sales/history/${cashier.username}"/>">
                            <c:out value="${cashier.name}"></c:out>
                        </a>
                    </li>
                </ul>
                <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
                    <span>Cashier</span>
                    <a class="d-flex align-items-center text-muted" href="#"></a>
                </h6>
                <ul class="nav flex-column mb-2">
                    <li class="nav-item">
                        <a class="nav-link" href="../../cashiers">
                            All
                        </a>
                    </li>
                </ul>
            </div>
        </nav>

        <%-- Main --%>
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap pb-3 mb-3 border-bottom">
                <h1 class="h2">
                    History Sale
                </h1>
            </div>
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap border-bottom">
                <p>You can only view your own transaction!</p>
            </div>
            <div class="d-flex flex-wrap flex-md-nowrap align-items-center pb-3 mb-3 border-bottom">
                <div class="table-responsive mt-3">
                    <table class="table table-striped table-sm">
                        <thead>
                        <tr>
                            <th>Sale number</th>
                            <th>Date transaction</th>
                            <th>Cashier</th>
                            <th>Transaction Amount</th>
                            <th>Payment type</th>
                            <th>Action</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${sales}" var="sale">
                            <tr>
                                <td><c:out value="${sale.saleNumber}"/></td>
                                <td><c:out value="${sale.transactionDate}"/></td>
                                <td><c:out value="${sale.cashier.name}"/></td>
                                <td><c:out value="${sale.totalPrice()}"/></td>
                                <td>
                                    <c:choose>
                                        <c:when test="${sale.cashInHand eq 0}">
                                            <c:out value="QRIS"/>
                                        </c:when>
                                        <c:otherwise>
                                            <c:out value="CASH"/>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <a href="../detail?saleId=<c:out value="${sale.saleNumber}"/>">
                                        <button class="btn btn-xs btn-outline-secondary">
                                            View
                                        </button>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </main>
    </div>
</div>

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