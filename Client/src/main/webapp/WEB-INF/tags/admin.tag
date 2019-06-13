<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="scripts" fragment="true" %>
<!doctype html>
<html>
    <head>
        <script type="text/javascript" src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="  crossorigin="anonymous"></script>
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
        <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
        <script src="https://kit.fontawesome.com/721083ccb8.js"></script>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>
    <body>
        <nav style="background-color: #F2ECD6;" class="navbar navbar-expand-lg navbar-light ">
            <div class="container">
                <div class="navbar-brand" href="#">
                    <span><img style="max-width: 10%" src="/PolyAuto/images/polyauto.png"></span>
                </div>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#mainnav" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="mainnav">
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item ">
                            <a class="nav-link" href="/PolyAuto/admin?token=${token}">HOME</a>
                        </li>
                        <li class="nav-item ">
                            <a class="nav-link" href="/PolyAuto/admin/cars?token=${token}">VOITURES</a>
                        </li>
                        <li class="nav-item ">
                            <a class="nav-link" href="/PolyAuto/admin/users?token=${token}">UTILISATEURS</a>
                        </li>
                        <li class="nav-item ">
                            <a class="nav-link" href="/PolyAuto/admin/bookings?token=${token}">RESERVATIONS</a>
                        </li>
                        <li class="nav-item ">
                            <a class="nav-link" href="/PolyAuto/admin/logout"><i class="fas fa-sign-out-alt"></i></a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <div id="body" class="container">
            <jsp:doBody/>
        </div>
        <jsp:invoke fragment="scripts"/>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </body>
</html>