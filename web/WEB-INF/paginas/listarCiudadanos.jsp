<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page='../componentes/comunes/cabecera.jsp'/>

<div class="container my-5">
    <div class="jumbotron jumbotron-fluid bg-light rounded-pill p-0">
        <div class="container">
            <h6 class="display-4 text-center text-primary">Sistema de Elecciones</h6>
        </div>
    </div>
    <div class="row">
        <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
    <div class="d-flex justify-content-center">
        <a href="listarCiudadanos" class="mx-2">
            <span class="badge badge-secondary rounded-pill">
                <h5 class="px-4 text-wrap m-0">Listar ciudadanos</h5>
            </span>
        </a>
        <a href="registrarCiudadano" class="mx-2">
            <span class="badge badge-secondary rounded-pill">
                <h5 class="px-4 text-wrap m-0">Registrar ciudadano</h5>
            </span>
        </a>
        <a href="listarPartidos" class="mx-2">
            <span class="badge badge-secondary rounded-pill">
                <h5 class="px-4 text-wrap m-0">Listar partidos</h5>
            </span>
        </a>
        <a href="registrarPartido" class="mx-2">
            <span class="badge badge-secondary rounded-pill">
                <h5 class="px-4 text-wrap m-0">Registrar partido</h5>
            </span>
        </a>
        <a href="AsignarMiembrosMesa" class="mx-2">
            <span class="badge badge-secondary rounded-pill">
                <h5 class="px-4 text-wrap m-0">Miembros de Mesa</h5>
            </span>
        </a>
    </div>
        </div>
    </div>
    <h1 class="text-center text-white m-4">CIUDADANOS</h1>
    <table class="table table-striped table-hover bg-white">
        <thead class="thead-dark">
          <tr>
            <th scope="col">Dni</th>
            <th scope="col">Apellidos</th>
            <th scope="col">Nombres</th>
            <th scope="col" class="text-center">Candidato</th>
            <th scope="col" class="text-center">Miembro de mesa</th>
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
<!--            <td>
                <button class="btn btn-success btn-block">Editar</button>
            </td>-->
          </tr>
          </c:forEach>
        </tbody>
    </table>
</div>

<jsp:include page='../componentes/comunes/piecera.jsp'/>

