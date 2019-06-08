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
                    <th>Login</th>
                    <th>Prénom</th>
                    <th>Nom</th>
                    <th>Note</th>
                    <th>Admin</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${users}" var="item">
                    <tr>
                        <td>${item.getIdUser()}</td>
                        <td>${item.getLogin()}</td>
                        <td>${item.getFirstname()}</td>
                        <td>${item.getLastname()}</td>
                        <td>${item.getNote()}</td>
                        <td>
                            <c:choose>
                                <c:when test="${item.getAdmin()==1}">
                                    <span class="badge badge-success"><i class="fas fa-check-circle"></i></span>
                                </c:when>
                                <c:otherwise>
                                    <span class="badge badge-danger"><i class="fas fa-times-circle"></i></span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </jsp:body>
</t:admin>