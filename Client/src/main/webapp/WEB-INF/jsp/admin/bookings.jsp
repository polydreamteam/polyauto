<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:admin>
     <jsp:attribute name="scripts">
    <script>
        $(document).ready( function () {
            $('#cars-list').DataTable();
        } );
    </script>
    </jsp:attribute>
    <jsp:body>
        <h1 class="mb-5">Liste des voitures</h1>
        <table id="cars-list" class="table table-hover">
            <thead class="thead-dark">
                <tr>
                    <th>Identifiant</th>
                    <th>Status</th>
                    <th>Date de départ</th>
                    <th>Date de retour</th>
                    <th>Emprunteur</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${bookings}" var="item">
                    <tr>
                        <td>${item.getIdBooking()}</td>
                        <td>
                            <c:choose>
                                <c:when test="${item.getStatus()==1}">
                                    <span class="badge badge-success">En cours</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="badge badge-warning">Terminée</span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>${item.getDateUp().toString()}</td>
                        <td>
                            <c:choose>
                                <c:when test="${item.getStatus()==1}">

                                </c:when>
                                <c:otherwise>
                                    ${item.getDateDown().toString()}
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>${item.getUsersByIdUser().getFirstname()} ${item.getUsersByIdUser().getLastname()}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </jsp:body>
</t:admin>