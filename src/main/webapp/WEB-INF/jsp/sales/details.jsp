<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Sale Detail</title>

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

<%-- Header --%>
<nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0">
    <a class="navbar-brand col-sm-3 col-md-2 mr-0 ml-2" href="#">
        Wide Point of Sales
    </a>
    <ul class="navbar-nav px-3">
        <li class="nav-item text-nowrap">
            <a class="nav-link" href="auth/logout">
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
                        <a class="nav-link" href="home">
                            Dashboard
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="#">
                            Sale
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="items">
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
                        <a class="nav-link" href="sales/history/all">
                            All
                        </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link " href="<c:url value="sales/history/${cashier.username}"/>"/>
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
                        <a class="nav-link" href="cashiers">
                            All
                        </a>
                    </li>
                </ul>
            </div>
        </nav>

        <%-- Main --%>
        <main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
                <h1 class="h2">
                    Sale details
                </h1>

                <%-- Button --%>
                <div class="btn-toolbar mb-2 mb-md-0">
                    <div class="btn-group mr-2">
                        <a href="sales/add-sale-item">
                            <button class="btn btn-sm btn-outline-secondary">
                                Add Item
                            </button>
                        </a>
                        <a href="sales/clear-sale-item">
                            <button class="btn btn-sm btn-outline-secondary">
                                Clear
                            </button>
                        </a>
                    </div>
                </div>
            </div>

            <%-- Table --%>
            <div class="table-responsive">
                <p> Sale number : <c:out value="${saleDto.saleNumber}"/></p>
                <p> Cashier : <c:out value="${saleDto.cashier.username}"/></p>

                <table class="table table-striped table-sm">
                    <thead>
                    <tr>
                        <th>Item Code</th>
                        <th>Description</th>
                        <th>Type</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Action</th>
                        <th>Total Price</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${saleDto.saleItems}" var="saleItem">
                        <tr>
                            <td><c:out value="${saleItem.item.itemCode}"/></td>
                            <td><c:out value="${saleItem.item.description}"/></td>
                            <td><c:out value="${saleItem.item.type}"/></td>
                            <td><c:out value="${saleItem.price}"/></td>
                            <td><c:out value="${saleItem.quantity}"/></td>
                            <td>
                                <a href="sales/delete-sale-item/<c:out value="${saleItem.id}"/>">
                                    <button class="btn btn-xs btn-outline-secondary">
                                        Delete
                                    </button>
                                </a>
                            </td>
                            <td><c:out value="${saleItem.totalPrice()}"/></td>
                        </tr>
                    </c:forEach>
                    <tr>
                        <th>Total Price:</th>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <th><c:out value="${saleDto.totalPriceWithoutTax()}"/></th>
                    </tr>
                    <tr>
                        <th>Tax:</th>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <th><c:out value="${saleDto.taxAmount()}"/></th>
                    </tr>
                    <tr>
                        <th>Total Price (Incl Tax):</th>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <td></td>
                        <th><c:out value="${saleDto.totalPrice()}"/></th>
                    </tr>
                    </tbody>
                </table>
            </div>

            <%-- Payment --%>
            <div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pb-2 mb-3 border-bottom">
                <div class="btn-group ml-auto">
                    <c:choose>
                        <c:when test="${empty saleDto.saleItems}"/>
                        <c:otherwise>
                            <a href="javascript:void(0);" onclick="togglePaymentOptions();">
                                <button class="btn btn-sm btn-outline-secondary">
                                    Pay
                                </button>
                            </a>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>

            <form id="paymentForm" action="" method="post" style="display: none;">
                <div class="form-group">
                    <input type="radio" id="cashRadio" name="paymentMethod" value="cash"
                           onclick="togglePaymentOption();">
                    <label class="custom-control-label" for="cashRadio">
                        Cash
                    </label>
                    <div id="cashInput" class="input-group mb-3 is-invalid">
                        <div class="input-group-prepend">
                            <span class="input-group-text">Rp.</span>
                        </div>
                        <input type="number" step="0.01" id="cashAmount" name="cashAmount" class="form-control"
                               disabled>
                    </div>
                </div>

                <div class="form-group">
                    <input type="radio" id="qrisRadio" name="paymentMethod" value="qris"
                           onclick="togglePaymentOption();">
                    <label class="custom-control-label" for="qrisRadio">
                        QRIS
                    </label>
                </div>

                <div class="text-center mt-3">
                    <button type="button" class="btn btn-sm btn-secondary" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-sm btn-primary ml-2" onclick="confirmPayment()">Confirm
                    </button>
                </div>
            </form>
        </main>
    </div>
</div>

<%-- Script --%>
<input type="hidden" id="status" value="${status}" />
<script type="text/javascript" src="<c:url value='/assets/js/sale.js'/>"></script>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
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