<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page='WEB-INF/componentes/comunes/cabecera.jsp'/>

    <div class="container">
        <div class="row">
            <div class="col-sm-9 col-md-7 col-lg-5 mx-auto my-5">
                <h1 class="text-center text-white">Sistema de elecciones</h1>
                <div class="card card-signin my-5">                
                    <div class="card-body">
                        <h5 class="card-title text-center">Ingresar al sistema</h5>
                        <form class="form-signin" action="identificarCiudadano" method="POST">
                            <div class="form-label-group">
                                <input type="text" name="usuario" id="inputUsuario" class="form-control" placeholder="ingrese su usuario" required autofocus>
                                <label for="inputUsuario">Usuario</label>
                            </div>
                            <div class="form-label-group">
                                <input type="password" name="clave" id="inputClave" class="form-control" placeholder="ingrese su clave" required>
                                <label for="inputClave">Clave</label>
                            </div>
                            <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit">Identificar</button>
                            
                            <%-- alerta mensaje usuario inválido --%>    
                            <c:if test="${not empty mensaje}">
                                <div class="alert alert-danger alert-dismissible mt-3 rounded-pill fade show" role="alert">
                                    <strong>Error!</strong> El ciudadano ${mensaje}
                                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                                      <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                            </c:if>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
<jsp:include page='WEB-INF/componentes/comunes/piecera.jsp'/>
