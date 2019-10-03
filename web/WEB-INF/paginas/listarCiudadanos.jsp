<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page='../componentes/comunes/cabecera.jsp'/>

<div class="container my-5">
    <div class="d-flex justify-content-center">
        <a href="listarCiudadanos" class="mx-2">
            <h1><span class="badge badge-secondary rounded-pill">Listar</span></h1>
        </a>
        <a href="registrarCiudadano" class="mx-2">
            <h1><span class="badge badge-secondary rounded-pill">Registrar</span></h1>
        </a>
    </div>
    <h1 class="text-center text-white">CIUDADANOS</h1>
    <table class="table table-striped table-hover bg-white my-5">
        <thead class="thead-dark">
          <tr>
            <th scope="col">Dni</th>
            <th scope="col">Apellidos</th>
            <th scope="col">Nombres</th>
            <th scope="col">Candidato</th>
            <th scope="col">Miembro de mesa</th>
            <th scope="col">Acción</th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="ciudadano" items="${ciudadanos}">  
          <tr>
            <td>${ciudadano.getDni()}</td>
            <td>${ciudadano.getApellidos()}</td>
            <td>${ciudadano.getNombres()}</td>
            <td>
                <c:choose>
                    <c:when test="${ciudadano.getCandidato() == true}">
                        <span class="badge badge-primary">si</span>
                    </c:when>
                    <c:otherwise>
                        <span class="badge badge-danger">no</span>
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <c:choose>
                    <c:when test="${ciudadano.getMiembromesa() == true}">
                        <span class="badge badge-primary">si</span>
                    </c:when>
                    <c:otherwise>
                         <span class="badge badge-danger">no</span>
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <button class="btn btn-success">Asignar Miembro de mesa</button>
            </td>
          </tr>
          </c:forEach>
        </tbody>
    </table>
</div>

<jsp:include page='../componentes/comunes/piecera.jsp'/>

