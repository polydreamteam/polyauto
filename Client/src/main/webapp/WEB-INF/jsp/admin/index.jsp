<%--
  Created by IntelliJ IDEA.
  User: Grey Hope
  Date: 12/04/2019
  Time: 16:52
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:admin>
    <jsp:body>
        <h1 class="mb-5">Dashboard PolyAuto</h1>
        <div class="row">
        <div class="card col mr-3" id="cars-card">
            <div class="card-body">
                <i class="fas fa-car"> ${nbCars} voitures dans le parc</i>
            </div>
            <a href="/PolyAuto/admin/cars?token=${token}" class="card-link"><i class="fas fa-arrow-right"></i></a>
        </div>
        <div class="card col" id="users-card">
            <div class="card-body">
                <i class="fas fa-users"></i> ${nbUsers} utilisateurs enregistrés
            </div>
            <a href="/PolyAuto/admin/users?token=${token}" class="card-link"><i class="fas fa-arrow-right"></i></a>
        </div>
        <div class="card col ml-3" id="bookings-card">
            <div class="card-body">
                <i class="fas fa-book"></i> ${nbBookings} réservations enregistrées
            </div>
            <a href="/PolyAuto/admin/bookings?token=${token}" class="card-link"><i class="fas fa-arrow-right"></i></a>
        </div>
    </jsp:body>
</t:admin>