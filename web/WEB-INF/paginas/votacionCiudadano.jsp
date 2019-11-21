<%@page import="entidades.Ciudadano"%>
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
        <div class="col-sm-12 mx-auto">
            <div class="d-flex justify-content-center flex-row">
                <h3 class="text-center text-grey">
                    ciudadano: 
                    <span class="badge badge-secondary rounded-pill">
                        <h5 class="px-4">${ciudadano.getNombres()} ${ciudadano.getApellidos()}</h5>
                    </span>
                </h3>
            </div>
            <div class="d-flex justify-content-center flex-row">
                <h3 class="text-center text-grey">
                    mesa: 
                    <span class="badge badge-secondary rounded-pill">
                        <h5 class="px-4"># ${ciudadano.getUbigeo().getId()}</h5>
                    </span>
                </h3>    
            </div>
            <h1 class="text-center text-white m-4">EMITA SU VOTO</h1>
            <form class="form-signin" id="form">

                <input type="hidden" name="ciudadano" value="${ciudadano.getId()}" id="ciudadanoId">

                <div class="d-none" id="emitido">${ciudadano.getEmitido()}</div>
                
                <table class="table table-striped table-hover bg-white">
                    <thead class="thead-dark">
                        <tr>
                            <th scope="col">Partido</th>
                            <th scope="col">Candidato</th>
                            <th scope="col" class="ml-auto"></th>
                            <th scope="col" class="text-center">Voto</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="partido" items="${partidos}">  
                            <tr>
                                <td style="line-height: 4;">${partido.getNombre()}</td>
                                <td style="line-height: 4;">${partido.getCiudadano().getApellidos()} ${partido.getCiudadano().getNombres()}</td>
                                <td class="d-flex justify-content-end">
                                    <div class="img-votacion-container">
                                        <img src="${pageContext.request.contextPath}/recursos/imagenes/${partido.getImagen()}" class="img-votacion" alt="...">
                                    </div>
                                </td>
                                <td>
                                    <section title="${partido.getNombre()}">
                                        <div class="votacion">
                                            <input type="radio" value="${partido.getId()}" id="${partido.getNombre()}" name="eleccion" >
                                            <label for="${partido.getNombre()}"></label>
                                        </div>
                                    </section>
                                </td>
                            </tr>
                        </c:forEach>
                        <tr>
                            <td style="line-height: 4;"></td>
                            <td style="line-height: 4;"></td>
                            <td class="d-flex justify-content-end" style="line-height: 4;">
                                <div class="img-votacion-container text-center">NULO</div>
                            </td>
                            <td>
                                <section title="NULO">
                                    <div class="votacion">
                                        <input type="radio" value="nulo" id="votoNulo" name="eleccion" >
                                        <label for="votoNulo"></label>
                                    </div>
                                </section>
                            </td>
                        </tr>
                        <tr style="display: none">
                    <input type="radio" value="blanco" name="eleccion" checked style="display: none" >
                    </tr>
                    </tbody>
                </table>

                <div class="d-flex justify-content-end">
                <button class="btn btn-lg btn-success border-dark text-uppercase" type="submit" id="btn-votacion">
                    Registrar votación
                </button>
                 </div>   
                
            </form>
                <div id="voto-correcto" class="d-none alert alert-success alert-dismissible mt-3 rounded-pill fade show" role="alert">
                    <strong>Correcto!</strong> Su voto ha sido emitido <span></span>
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div id="voto-incorrecto" class="d-none alert alert-danger alert-dismissible mt-3 rounded-pill fade show" role="alert">
                    <strong>Error!</strong> Su voto no pudo ser emitido
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div id="voto-hecho" class="d-none alert alert-warning alert-dismissible mt-3 rounded-pill fade show" role="alert">
                    <strong>Atención!</strong> Su voto ya fue emitido
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
        </div>
    </div>
</div>

<jsp:include page='../componentes/comunes/piecera.jsp'/>

<script>
    $(document).ready(() => {
        
        const $form = $('#form')
        const ciudadanoId = $('#ciudadanoId').val()
        const $radio = $('input[name="eleccion"]')

        verificarSiYaVoto()    

        $form.on('submit', function (e) {

            e.preventDefault()

            const eleccion = $radio.filter(':checked').val()

            $.ajax({
                type: "POST",
                data: {
                    ciudadano: ciudadanoId,
                    eleccion: eleccion
                },
                url: 'votacionCiudadano',
                success: function (content) {
                    if (content === 'correcto') {
                        $('#btn-votacion').attr('disabled', true)
                        $('#voto-correcto').removeClass('d-none')
                    } else {
                        $('#voto-incorrecto').removeClass('d-none')
                    }
                },
                error: function (content) {
                     $('#voto-incorrecto').removeClass('d-none')
                }
            })
        })

        function verificarSiYaVoto() {
            if ($('#emitido').html() === 'true') {
                $('#btn-votacion').attr('disabled', true)
                $('#voto-hecho').removeClass('d-none')
            }
        }

    })
</script>