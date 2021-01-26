<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c1" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>SBB: Trips</title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/res/img/sbbBadge.png" type="image/x-icon">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="<c:url value="/res/css/navbar.css"/>" />
    <link rel="stylesheet" href="<c:url value="/res/css/sbb.classes.css"/>" />
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>
</head>

<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">

    <a class="navbar-brand" href="<c:url value="/"/>">
        <img src="${pageContext.request.contextPath}/res/img/sbbBadge.png" width="30" height="30"
             class="d-inline-block align-top" alt="">
        Swiss Federal Railways
    </a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/admin/management"/>" >
                    <i class="fas fa-cog"></i>
                    Management &#8592;
                </a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/admin/crud"/>"> Add train | station </a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/admin/trainselect"/>"> Set trip for train </a>
            </li>

            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">
                    Show
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="<c:url value="/admin/trainsandstations"/>">
                        Show all trains | stations</a>
                </div>
            </li>
        </ul>

        <a class="nav-link" href="<c:url value="/logout"/>">
            <i class="fa fa-user" aria-hidden="true"></i>
            Log out
        </a>

    </div>
</nav>

<c:choose>
    <c:when test="${empty allTrips}">
        <div class="container-fluid">
            <div class="row" style="height: 100px">
                <div class="col-2"></div>
                <div class="col-8"  style="text-align:center;" >
                    <h1 style="font-size: 50px; margin-top: 100px">No trains available!</h1>
                </div>
                <div class="col-2"></div>
            </div>
        </div>
    </c:when>
    <c:otherwise>

        <div class="container mt-4 p-md-3 mb-5 col-12 rounded-container">
            <div>
                <a class="link-no-dec" href="${pageContext.request.contextPath}/admin/allTrips?lastadded">
                   Last added at first <h1 style="text-align: center; margin-bottom: 20px;">Trips</h1>
                </a>
                <c:if test="${param.error != null}">
                    <div class="alert alert-danger alert-dismissible col-md-8 offset-md-2 fade show
                text-center" role="alert">
                        <span>${param.error} It has already been completed.</span>
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                            <span aria-hidden="true" onclick="">&times;</span>
                        </button>
                    </div>
                </c:if>
            </div>

            <table id="myTable" class="table" style="text-align: center">
                <thead>
                <tr>
                    <th scope="col">
                        <a class="link-no-dec" href="${pageContext.request.contextPath}/admin/allTrips">
                            Train</a>

                    </th>
                    <th scope="col">From</th>
                    <th scope="col">To</th>
                    <th scope="col">Departure</th>
                    <th scope="col">Arrival</th>
                    <th scope="col">Trip Info</th>
                    <th scope="col">Passengers</th>
                    <th scope="col">Action</th>
                </tr>
                </thead>

                <tbody>

                <c:forEach var="trip" items="${allTrips}" varStatus="vs">

                    <tr>
                        <th scope="row">
                                ${trip.trainDTO.trainNumber}
                        </th>
                        <td>${trip.departureStationDTO.title}</td>
                        <td>${trip.arrivalStationDTO.title}</td>
                        <fmt:setLocale value="en_US" scope="session"/>
                        <td>
                            <strong><fmt:formatDate value="${trip.departureTime}" pattern="HH:mm"/></strong>
                            <br>
                            <fmt:formatDate value="${trip.departureTime}" pattern="E, dd.MM.yyyy"/>
                        </td>
                        <td>
                            <strong><fmt:formatDate value="${trip.arrivalTime}" pattern="HH:mm"/></strong>
                            <br>
                            <fmt:formatDate value="${trip.arrivalTime}" pattern="E, dd.MM.yyyy"/>
                        </td>
                        <td>
                            <!-- Button trigger modal -->
                            <button type="button" class="btn btn-info" data-toggle="modal"
                                    data-target="#exampleModal${vs.index}"
                                    id="viewDetailButton1${vs.index}">
                                Show Info
                            </button>
                            <!-- Modal -->
                            <div class="modal fade" id="exampleModal${vs.index}" tabindex="-1" role="dialog"
                                 aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header" style="border-bottom: 0 none;">
                                            <h5 class="modal-title" id="exampleModalLabel">Trip info</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            <table class="table">
                                                <thead class="thead-success">
                                                <tr>
                                                    <th scope="col">From</th>
                                                    <th scope="col">To</th>
                                                    <th scope="col">Departure</th>
                                                    <th scope="col">Arrival</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <c1:forEach var="segment" items="${trip.scheduleList}">
                                                    <tr>
                                                        <td>${segment.stationFromDTO.title}</td>
                                                        <td>${segment.stationToDTO.title}</td>

                                                        <td>
                                                            <fmt:formatDate value="${segment.departureTime}"
                                                                            pattern="HH:mm dd.MM.yy"/>
                                                        </td>
                                                        <td><fmt:formatDate value="${segment.arrivalTime}"
                                                                            pattern="HH:mm dd.MM.yy"/>
                                                        </td>


                                                    </tr>
                                                </c1:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                        <div class="modal-footer" style="border-top: 0 none; display: flex;
                                            justify-content: space-between">
                                            <span style="font-size: 15px">*All times are indicated with delay</span>
                                            <button type="button" class="btn btn-secondary" data-dismiss="modal">
                                                Close
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </td>
                        <td>
                            <a class="btn btn-success" href="/admin/passengers/${trip.trainDTO.id}/${trip.id}/"
                               role="button" target="_blank">Show Info</a>
                        </td>


<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>

</body>
</html>