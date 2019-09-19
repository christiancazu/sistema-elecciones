<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page='../componentes/comunes/cabecera.jsp'/>

<div class="container">
    <div class="row">
        <div class="col-sm-9 col-md-7 col-lg-5 mx-auto my-5">
            <h1 class="text-center text-white">REGISTRO</h1>
            <div class="card card-signin my-5">                
                <div class="card-body">
                    <h5 class="card-title text-center">Registrar ciudadano</h5>
                    <form class="form-signin" action="registrarCiudadano" method="POST">                          
                        <div class="form-label-group">
                            <input type="number" name="dni" id="inputDni" class="form-control" placeholder="ingrese su dni" required autofocus>
                            <label for="inputDni">Dni</label>
                        </div>
                        <div class="form-label-group">
                            <input type="text" name="apellidos" id="inputApellidos" class="form-control" placeholder="ingrese sus apellidos" required>
                            <label for="inputApellidos">Apellidos</label>
                        </div>
                        <div class="form-label-group">
                            <input type="text" name="nombres" id="inputNombres" class="form-control" placeholder="ingrese sus nombres" required>
                            <label for="inputNombres">Nombres</label>
                        </div>
                        <div class="form-label-group">
                            <input type="text" name="direccion" id="inputDireccion" class="form-control" placeholder="ingrese su direccion" required>
                            <label for="inputDireccion">Direcci�n</label>
                        </div>
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <label class="input-group-text" for="inputUbigeo">Ubigeo</label>
                            </div>
                            <select name="ubigeo" class="custom-select" id="inputUbigeo" required>                                     
                                <option  disabled value="" selected hidden>Elegir...</option>
                                <c:forEach var="ubigeo" items="${ubigeos}">
                                    <option value="${ubigeo.getId()}">${ubigeo.getNombre()}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <label class="input-group-text" for="inputSexo">Sexo</label>
                            </div>
                            <select name="sexo" class="custom-select" id="inputSexo" required>                                     
                                <option  disabled value="" selected hidden>Elegir...</option>
                                <option value="M">masculino</option>
                                <option value="F">femenino</option>
                            </select>
                        </div>
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <label class="input-group-text" for="inputEstadocivil">Estado civil</label>
                            </div>
                            <select name="estadocivil" class="custom-select" id="inputEstadocivil" required>                                     
                                <option  disabled value="" selected hidden>Elegir...</option>
                                <option value="S">soltero</option>
                                <option value="C">casado</option>
                                <option value="V">viudo</option>
                                <option value="D">divorciado</option>
                            </select>
                        </div>    
                        <div class="input-group mb-3">
                            <div class="input-group-prepend">
                                <label class="input-group-text" for="inputCandidato">Ciudadano</label>
                            </div>
                            <select name="candidato" class="custom-select" id="inputCandidato" required>                                     
                                <option  disabled value="" selected hidden>Elegir...</option>
                                <option value="candidato">candidato</option>
                                <option value="elector">elector</option>
                            </select>
                        </div>

                        <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit">Registrar</button>

                        <%-- alerta mensaje registro inv�lido --%>    
                        <c:if test="${not empty mensaje}">
                            <c:choose>
                                <c:when test="${mensaje == 'creado'}">
                                    <div class="alert alert-success alert-dismissible mt-3 rounded-pill fade show" role="alert">
                                        <strong>Error!</strong> El ciudadano ha sido ${mensaje}
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