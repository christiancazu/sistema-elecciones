<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page='../componentes/comunes/cabecera.jsp'/>

<!--boton Salir-->
<jsp:include page='../componentes/comunes/botonSalir.jsp'/>    

<div class="container my-5">

    <!--menu-->
    <jsp:include page='../componentes/comunes/menu.jsp'/>

    <h1 class="text-center text-white m-4">CIUDADANOS</h1>
    <table class="table table-striped table-hover bg-white">
        <thead class="thead-dark">
            <tr>
                <th scope="col">Dni</th>
                <th scope="col">Apellidos</th>
                <th scope="col">Nombres</th>
                <th scope="col" class="text-center">Candidato</th>
                <th scope="col" class="text-center">Miembro de mesa</th>
                <th scope="col" class="text-center">Voto emitido</th>
                <!--            <th scope="col" class="text-center">Acción</th>-->
            </tr>
        </thead>
        <tbody>
            <c:forEach var="ciudadano" items="${ciudadanos}">  
                <tr>
                    <td>${ciudadano.getDni()}</td>
                    <td>${ciudadano.getApellidos()}</td>
                    <td>${ciudadano.getNombres()}</td>
                    <td class="text-center">
                        <c:choose>
                            <c:when test="${ciudadano.getCandidato() == true}">
                                <span class="badge badge-primary badge-outlined">
                                    <div class="h6 mb-0">si</div>
                                </span>
                            </c:when>
                            <c:otherwise>
                                <span class="badge badge-danger badge-outlined">
                                    <div class="h6 mb-0">no</div>
                                </span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td class="text-center">
                        <c:choose>
                            <c:when test="${ciudadano.getMiembromesa() == true}">
                                <span class="badge badge-primary badge-outlined">
                                    <div class="h6 mb-0">si</div>
                                </span>
                            </c:when>
                            <c:otherwise>
                                <span class="badge badge-danger badge-outlined">
                                    <div class="h6 mb-0">no</div>
                                </span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td class="text-center">
                        <c:choose>
                            <c:when test="${ciudadano.getEmitido()}">
                                <span class="badge badge-primary badge-outlined">
                                    <div class="h6 mb-0">si</div>
                                </span>
                            </c:when>
                            <c:otherwise>
                                <span class="badge badge-danger badge-outlined">
                                    <div class="h6 mb-0">no</div>
                                </span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <!-- <td>
                        <button class="btn btn-success btn-block">Editar</button>
                    </td> -->
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<jsp:include page='../componentes/comunes/piecera.jsp'/>

