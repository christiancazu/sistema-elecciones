<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page='../componentes/comunes/cabecera.jsp'/>

<!--boton Salir-->
<jsp:include page='../componentes/comunes/botonSalir.jsp'/>     

<div class="container my-5">

    <!--menu-->
    <jsp:include page='../componentes/comunes/menu.jsp'/>

    <h1 class="text-center text-white m-4">REGISTRO</h1>

    <div class="d-flex flex-row justify-content-center">
        <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
            <div class="card card-signin">                
                <div class="card-body">
                    <%-- alerta mensaje registro inválido --%>    
                    <c:if test="${not empty mensaje}">
                        <c:choose>
                            <c:when test="${mensaje == 'creado'}">
                                <div class="alert alert-success alert-dismissible mt-3 rounded-pill fade show" role="alert">
                                    <strong>Correcto!</strong> El partido ha sido ${mensaje}
                                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                            </c:when>
                            <c:when test="${mensaje == 'ya existe'}">
                                <div class="alert alert-warning alert-dismissible mt-3 rounded-pill fade show" role="alert">
                                    <strong>Advertencia!</strong> El partido ${mensaje}
                                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                            </c:when>
                            <c:otherwise>
                                <div class="alert alert-danger alert-dismissible mt-3 rounded-pill fade show" role="alert">
                                    <strong>Error!</strong> El partido ${mensaje}
                                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                            </c:otherwise>
                        </c:choose>                                
                    </c:if>
                    <h5 class="card-title text-center">Registrar partido</h5>
                    <form class="form-signin" action="registrarPartido" method="POST" enctype="multipart/form-data">                          

                        <div class="form-label-group">
                            <input type="text" name="nombre" id="inputNombre" class="form-control" placeholder="ingrese nombre del partido" required>
                            <label for="inputNombre">Nombre</label>
                        </div>

                        <div class="form-group">
                            <label for="">Subir imágen:</label>
                            <div class="input-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" id="input-group-text">Subir imágen:</span>
                                </div>
                                <div class="custom-file">
                                    <input type="file" class="custom-file-input" id="img-input" name="imagen"  accept=".jpg,.jpeg,.png" required
                                           aria-describedby="input-group-text">
                                    <label id="file-label" class="custom-file-label" for="img-input">elija una imágen</label>
                                </div>

                                <div class="container mt-2">
                                    <div class="row justify-content-center">
                                        <img id="img-preview" src="#" alt="" class="img-preview"/>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="input-group mb-3">                            
                            <div class="input-group-prepend">
                                <label class="input-group-text" for="inputCiudadano">Candidato</label>
                            </div>
                            <select name="ciudadano" class="custom-select" id="inputCiudadano" required>                                     
                                <option  disabled value="" selected hidden>Elegir...</option>
                                <c:forEach var="ciudadano" items="${ciudadanos}">
                                    <option value="${ciudadano.getId()}">
                                        ${ciudadano.getNombres()} ${ciudadano.getApellidos()}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>

                        <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit">
                            Registrar
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page='../componentes/comunes/piecera.jsp'/>

<script>
    $("#img-input").change(function () {
        readURL(this);
        $('#file-label').text(() => {
            return $(this).val() === null ? "Elija una imagen" : "Modificar imagen"
        })
    })

    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function (e) {
                $('#img-preview').attr('src', e.target.result);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }
</script>