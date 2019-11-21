<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page='../componentes/comunes/cabecera.jsp'/>

<div class="btn-salir-container">
    <a href="${pageContext.request.contextPath}" class="mx-2">
        <span class="badge badge-secondary rounded-pill">
            <h5 class="px-4 text-wrap m-0">salir</h5>
        </span>
    </a>
</div>

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
        <a href="revisarVotacion" class="mx-2">
            <span class="badge badge-secondary rounded-pill">
                <h5 class="px-4 text-wrap m-0">Revisar votación</h5>
            </span>
        </a>
    </div>
        </div>
    </div>
    <h1 class="text-center text-white m-4">PARTIDOS</h1>
    <c:forEach var="partido" items="${partidos}">
        <div class="d-flex justify-content-center">
            <div class="card my-2" style="width: 680px; min-width: 680px; min-height: 220px;">
                <div class="row no-gutters">
                  <div class="col-md-4" style="height: 220px;">
                    <img src="${pageContext.request.contextPath}/recursos/imagenes/${partido.getImagen()}" class="card-img" alt="..." style='height: 100%; width: 100%; object-fit: cover'>
                  </div>
                  <div class="col-md-8">
                    <div class="card-body">
                      <h3 class="card-title text-dark"><span class="badge badge-secondary mr-2">partido </span> ${partido.getNombre()}</h3>
                      <p class="card-text mt-5 text-muted"><span class="badge badge-secondary mr-2">candidato </span> ${partido.getCiudadano().getNombres()} ${partido.getCiudadano().getApellidos()}</p>
                      <p class="card-text mt-5"><strong class="text-muted"><span class="badge badge-secondary mr-2">dni </span>${partido.getCiudadano().getDni()}</strong></p>
                    </div>
                  </div>
                </div>
            </div>
        </div>
    </c:forEach>
    
</div>

<jsp:include page='../componentes/comunes/piecera.jsp'/>
