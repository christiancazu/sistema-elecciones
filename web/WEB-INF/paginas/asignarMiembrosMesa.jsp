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
                    <form class="form-signin" action="AsignarMiembrosMesa" method="POST">
                        <div class="input-group mb-3">                            
                            <div class="input-group-prepend">
                                <label class="input-group-text" for="inputMesa">Mesa</label>
                            </div>
                            <select name="mesa" class="custom-select" id="inputMesa" required>                                     
                                <option disabled value="" selected hidden>Elegir...</option>
                                
                                <!-- no muestra mesas donde ya tenga el miembrouno registrado-->
                                <c:forEach var="mesa" items="${mesas}">
                                    <c:if test = "${mesa.getMiembrouno().getId() == 0}">
                                        <option value="${mesa.getId()}">
                                            ${mesa.getId()}
                                        </option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </div>
                        
                        <div class="input-group mb-3">                            
                            <div class="input-group-prepend">
                                <label class="input-group-text" for="inputMiembrouno">Primer miembro</label>
                            </div>
                            <select name="inputMiembrouno" class="custom-select" id="inputMiembrouno" required>                                     
                                <option disabled value="" selected hidden>Elegir...</option>
                                <!-- no muestra ciudadanos que ya sean miembros de mesa y candidatos -->
                                <c:forEach var="ciudadano" items="${ciudadanos}">
                                    <c:if test = "${!ciudadano.getMiembromesa() && !ciudadano.getCandidato()}">
                                        <option 
                                            value="${ciudadano.getId()}" 
                                            data-ubigeo="${ciudadano.getUbigeo().getId()}"
                                        >
                                            ${ciudadano.getNombres()} ${ciudadano.getApellidos()}
                                        </option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </div>
                        
                        <div class="input-group mb-3">                            
                            <div class="input-group-prepend">
                                <label class="input-group-text" for="inputMiembrodos">Segundo miembro</label>
                            </div>
                            <select name="inputMiembrodos" class="custom-select" id="inputMiembrodos" required>                                     
                                <option disabled value="" selected hidden>Elegir...</option>
                                <!-- no muestra ciudadanos que ya sean miembros de mesa y candidatos -->
                                <c:forEach var="ciudadano" items="${ciudadanos}">
                                    <c:if test = "${!ciudadano.getMiembromesa() && !ciudadano.getCandidato()}">
                                        <option 
                                            value="${ciudadano.getId()}" 
                                            data-ubigeo="${ciudadano.getUbigeo().getId()}"
                                        >
                                            ${ciudadano.getNombres()} ${ciudadano.getApellidos()}
                                        </option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </div>
                        
                        <div class="input-group mb-3">                            
                            <div class="input-group-prepend">
                                <label class="input-group-text" for="inputMiembrotres">Tercer miembro</label>
                            </div>
                            <select name="inputMiembrotres" class="custom-select" id="inputMiembrotres" required>                                     
                                <option disabled value="" selected hidden>Elegir...</option>
                                <!-- no muestra ciudadanos que ya sean miembros de mesa y candidatos -->
                                <c:forEach var="ciudadano" items="${ciudadanos}">
                                    <c:if test = "${!ciudadano.getMiembromesa() && !ciudadano.getCandidato()}">
                                        <option 
                                            value="${ciudadano.getId()}" 
                                            data-ubigeo="${ciudadano.getUbigeo().getId()}"
                                        >
                                            ${ciudadano.getNombres()} ${ciudadano.getApellidos()}
                                        </option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </div>

                        <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit">Asignar</button>

                        <%-- alerta mensaje registro inválido --%>    
                        <c:if test="${not empty mensaje}">
                            <c:choose>
                                <c:when test="${mensaje == 'actualizada'}">
                                    <div class="alert alert-success alert-dismissible mt-3 rounded-pill fade show" role="alert">
                                        <strong>Correcto!</strong> La mesa ha sido actualizada
                                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                </c:when>
                                <c:when test="${mensaje == 'no actualizada'}">
                                    <div class="alert alert-warning alert-dismissible mt-3 rounded-pill fade show" role="alert">
                                        <strong>Advertencia!</strong> La mesa no ha sido actualizada
                                        <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="alert alert-danger alert-dismissible mt-3 rounded-pill fade show" role="alert">
                                        <strong>Error!</strong> Error al procesar la mesa
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

<script>
    $inputMesa = $('#inputMesa')
    $inputMiembrouno = $('#inputMiembrouno')
    $inputMiembrodos = $('#inputMiembrodos')
    $inputMiembrotres = $('#inputMiembrotres')
    
    $inputMesa.on('change', function () {
        const mesaSeleccionada = $(this).val()
        mostrarTodasLasOpciones()
        ocultarPorUbigeo(mesaSeleccionada)
    })
    
    function mostrarTodasLasOpciones() {
        $inputMiembrouno.find('option').each(function (index, option) {
            $(option).css('display', 'block')
        })
        $inputMiembrodos.find('option').each(function (index, option) {
            $(option).css('display', 'block')
        })
        $inputMiembrotres.find('option').each(function (index, option) {
            $(option).css('display', 'block')
        })
    }
    function ocultarPorUbigeo(mesaSeleccionada) {
        $inputMiembrouno.find('option').not('[data-ubigeo="' + mesaSeleccionada + '"]').each(function (index, option) {
            $(option).css('display', 'none')
        })
        $inputMiembrodos.find('option').not('[data-ubigeo="' + mesaSeleccionada + '"]').each(function (index, option) {
            $(option).css('display', 'none')
        })
        $inputMiembrotres.find('option').not('[data-ubigeo="' + mesaSeleccionada + '"]').each(function (index, option) {
            $(option).css('display', 'none')
        })
    }
</script>