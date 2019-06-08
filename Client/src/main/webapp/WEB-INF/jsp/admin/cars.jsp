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
                    <th>Modèle</th>
                    <th>Lattitude</th>
                    <th>Longitude</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${cars}" var="item">
                    <tr>
                        <td>${item.getIdCar()}</td>
                        <td>
                            <c:choose>
                                <c:when test="${item.getModel()==1}">
                                   Voiture 1
                                </c:when>
                                <c:when test="${item.getModel()==2}">
                                    Voiture 2
                                </c:when>
                                <c:otherwise>
                                    Voiture 3
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>${item.getLat()}</td>
                        <td>${item.getLon()}</td>
                        <td>
                            <c:choose>
                                <c:when test="${item.getStatus()==1}">
                                    <span class="badge badge-success">Disponible</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="badge badge-danger">Reservée</span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </jsp:body>
</t:admin>