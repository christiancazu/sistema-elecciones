<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page='../componentes/comunes/cabecera.jsp'/>

    <div class="container">
        <div class="row">
            <div class="col-xs-12 col-md-10 col-lg-8 mx-auto my-5">
                <h1 class="text-center text-white">BIENVENIDO A LA VOTACIÓN</h1>
                <hr class="my-4">
                <h3 class="text-center text-grey">
                    <span class="badge badge-light">ciudadano:</span>
                    ${ciudadano.getNombres()} ${ciudadano.getApellidos()}
                </h3>
                <hr class="my-5">
                <div class="alert alert-warning alert-dismissible mt-3 rounded-pill fade show" role="alert">
                    <strong>Atención!</strong> la elección aún no ha comenzado.
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
            </div>
        </div>
    </div>
    
<jsp:include page='../componentes/comunes/piecera.jsp'/>
