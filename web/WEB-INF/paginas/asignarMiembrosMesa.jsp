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
            <h1 class="text-center text-white m-4">ASIGNAR</h1>
            <div class="card card-signin">                
                <div class="card-body">
                    <h5 class="card-title text-center">Asignar miembros de mesa</h5>
                    <form class="form-signin" action="asignarMiembroMesa" method="POST">                          
                        
                        <div class="input-group mb-3">                            
                            <div class="input-group-prepend">
                                <label class="input-group-text" for="inputMesa">Mesa</label>
                            </div>
                            <select name="mesa" class="custom-select" id="inputMesa" required>                                     
                                <option  disabled value="" selected hidden>Elegir...</option>
                                <c:forEach var="mesa" items="${mesas}">
                                    <option value="${mesa.getId()}">
                                        ${mesa.getId()}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                        
                        <div class="input-group mb-3">                            
                            <div class="input-group-prepend">
                                <label class="input-group-text" for="inputMiembrouno">Primer miembro</label>
                            </div>
                            <select name="primermiembro" class="custom-select" id="inputMiembrouno" required>                                     
                                <option  disabled value="" selected hidden>Elegir...</option>
                                <c:forEach var="ciudadano" items="${ciudadanos}">
                                    <option value="${ciudadano.getId()}">
                                        ${ciudadano.getNombres()} ${ciudadano.getApellidos()}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                        
                        <div class="input-group mb-3">                            
                            <div class="input-group-prepend">
                                <label class="input-group-text" for="inputMiembrouno">Segundo miembro</label>
                            </div>
                            <select name="primermiembro" class="custom-select" id="inputMiembrouno" required>                                     
                                <option  disabled value="" selected hidden>Elegir...</option>
                                <c:forEach var="ciudadano" items="${ciudadanos}">
                                    <option value="${ciudadano.getId()}">
                                        ${ciudadano.getNombres()} ${ciudadano.getApellidos()}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                        
                        <div class="input-group mb-3">                            
                            <div class="input-group-prepend">
                                <label class="input-group-text" for="inputMiembrouno">Tercer miembro</label>
                            </div>
                            <select name="primermiembro" class="custom-select" id="inputMiembrouno" required>                                     
                                <option  disabled value="" selected hidden>Elegir...</option>
                                <c:forEach var="ciudadano" items="${ciudadanos}">
                                    <option value="${ciudadano.getId()}">
                                        ${ciudadano.getNombres()} ${ciudadano.getApellidos()}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>

                        <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit">Asignar</button>

                        <%-- alerta mensaje registro inválido --%>    
                        <c:if test="${not empty mensaje}">
                            <c:choose>
                                <c:when test="${mensaje == 'creado'}">
                                    <div class="alert alert-success alert-dismissible mt-3 rounded-pill fade show" role="alert">
                                        <strong>Correcto!</strong> El ciudadano ha sido ${mensaje}
                                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                </c:when>
                                <c:when test="${mensaje == 'ya existe'}">
                                    <div class="alert alert-warning alert-dismissible mt-3 rounded-pill fade show" role="alert">
                                        <strong>Advertencia!</strong> El ciudadano ${mensaje}
                                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="alert alert-danger alert-dismissible mt-3 rounded-pill fade show" role="alert">
                                        <strong>Error!</strong> El ciudadano ${mensaje}
                                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                </c:otherwise>
                            </c:choose>                                
                        </c:if>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page='../componentes/comunes/piecera.jsp'/>
