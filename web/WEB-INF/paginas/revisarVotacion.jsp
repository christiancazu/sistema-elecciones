<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page='../componentes/comunes/cabecera.jsp'/>

<div class="btn-salir-container">
    <a href="${pageContext.request.contextPath}" class="mx-2">
        <span class="badge badge-secondary rounded-pill">
            <h5 class="px-4 text-wrap m-0">salir</h5>
        </span>
    </a>
</div>

<div class="container my-5">

    <!--menu-->
    <jsp:include page='../componentes/comunes/menu.jsp'/>

    <h1 class="text-center text-white m-4 text-uppercase" id="resultados-ubigeo"></h1>

    <div class="row justify-content-center my-2">
        <div class="col-xs-12 col-md-3">
            <div class="list-group">
                <li class="list-group-item active bg-dark">Mesas de votación</li>
                <button 
                    type="button" 
                    class="list-group-item list-group-item-action"
                    onclick="obtenerVotosPorUbigeo('general')">
                    General
                </button>
                <c:forEach var="ubigeo" items="${ubigeos}">  
                    <button 
                        type="button" 
                        class="list-group-item list-group-item-action"
                        onclick="obtenerVotosPorUbigeo(${ubigeo.getId()})">
                        ${ubigeo.getNombre()}
                    </button>
                </c:forEach>
            </div>
        </div>
        <div class="col-xs-12 col-md-9">
            <table class="table table-striped table-hover bg-white">
                <thead class="thead-dark">
                    <tr>
                        <th scope="col" class="text-center">Partido</th>
                        <th scope="col">Votos</th>
                        <th scope="col">Porcentaje</th>
                    </tr>
                </thead>
                <tbody style="line-height: 4">
                    <tr id="tr-nulos">
                        <td style="height: 5rem;">NULOS</td>
                        <td class="text-center"></td>
                        <td class="text-center"></td>
                    </tr>
                    <tr id="tr-blancos">
                        <td style="height: 5rem;">BLANCOS</td>
                        <td class="text-center"></td>
                        <td class="text-center"></td>
                    </tr>

                    <tr class="bg-dark text-light" id="tr-total">
                        <td style="height: 5rem;">TOTAL</td>
                        <td class="text-center"></td>
                        <td class="text-center">100 %</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>

<jsp:include page='../componentes/comunes/piecera.jsp'/>

<script>
    const baseURL = location.origin + "/" + location.pathname.split('/')[1]
    
    const $trNulos = $("#tr-nulos")
    const $trBlancos = $("#tr-blancos")
    const $trTotal = $("#tr-total")
    
    const $resultadosUbigeo = $("#resultados-ubigeo")
    
    $(document).ready(() => {
        obtenerVotosPorUbigeo("general")
    })

    function obtenerVotosPorUbigeo(id) {
        $.ajax({
            type: 'GET',
            url: 'revisarVotacion?ubigeo=' + id,
            success: function (respuesta) {
                rellenarTituloResultados(respuesta)
                rellenarNulos(respuesta)
                rellenarBlancos(respuesta)
                rellenarPartidoVotos(respuesta)
                rellenarTotal(respuesta)
            },
            error: function (respuesta) {
                console.error(respuesta)
            }
        })
    }
    function rellenarTituloResultados({nombreUbigeo}) {
        $resultadosUbigeo.html('RESULTADOS ' + nombreUbigeo)
    }
    
    function rellenarNulos({total, nulos}) {
        $trNulos.children().eq(1).empty()
        $trNulos.children().eq(2).empty()
        $trNulos.children()[1].append(nulos | 0)
        $trNulos.children()[2].append((isNaN((nulos/total*100).toFixed(2)) ? '0.00' : ((nulos/total*100).toFixed(2)))  + ' %')
    }
    
    function rellenarBlancos({total, blancos}) {
        $trBlancos.children().eq(1).empty()
        $trBlancos.children().eq(2).empty()
        $trBlancos.children()[1].append(blancos | 0)
        $trBlancos.children()[2].append((isNaN((blancos/total*100).toFixed(2)) ? '0.00' : ((blancos/total*100).toFixed(2)))  + ' %')
    }
    
    function rellenarPartidoVotos({total, partidoVotos}) {
        let plantilla = ''
        
        $('.partidos-insertados').remove()
       
        partidoVotos.forEach(partidoVoto => {
            const porcentaje = (isNaN((partidoVoto.votos/total*100).toFixed(2)) ? '0.00' : ((partidoVoto.votos/total*100).toFixed(2)))

            plantilla += '<tr class="partidos-insertados"><td class="d-flex justify-content-between">'
            plantilla += partidoVoto.partido.nombre
            plantilla += '<div class="img-votacion-container">'
            plantilla += '<img src="' + baseURL +'/recursos/imagenes/' + partidoVoto.partido.imagen + '" class="img-votacion" alt="...">'
            plantilla += '</div>'
            plantilla += '</td>'
            plantilla += '<td class="text-center" style="width: 2rem">'
            plantilla += partidoVoto.votos | 0
            plantilla += '</td>'
            plantilla += '<td class="text-center" style="width: 2rem">'
            plantilla += porcentaje
            plantilla += ' %</td></tr>'
        })
        
        $(plantilla).insertBefore($trTotal)  
    }
    
    function rellenarTotal({total}) {
        $trTotal.children().eq(1).empty()
        $trTotal.children()[1].append(total)
    }
</script>
