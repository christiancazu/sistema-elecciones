<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page='../componentes/comunes/cabecera.jsp'/>

<!--boton Salir-->
<jsp:include page='../componentes/comunes/botonSalir.jsp'/>  

<div class="container my-5">
    
    <!--menu-->
    <jsp:include page='../componentes/comunes/menu.jsp'/>
    
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
