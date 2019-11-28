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
    
    <!--menu-->
    <jsp:include page='../componentes/comunes/menu.jsp'/>
    
    <h1 class="text-center text-white m-4">RESULTADOS DE LA VOTACIÓN</h1>
    <div class="d-flex flex-row justify-content-center">
        <div class="mx-2">
            <h3 class="text-center text-grey">
                votos nulos: 
                <span class="badge badge-secondary rounded-pill">
                    <h5 class="px-4">${nulos}</h5>
                </span>
            </h3>
        </div>
        <div class="mx-2">
            <h3 class="text-center text-grey">
                votos en blanco: 
                <span class="badge badge-secondary rounded-pill">
                    <h5 class="px-4">${blancos}</h5>
                </span>
            </h3>    
        </div>
    </div>
    <table class="table table-striped table-hover bg-white">
        <thead class="thead-dark">
            <tr>
                <th scope="col">Partido</th>
                <th scope="col">votos</th>
                <th scope="col">Cantidad</th>
                <th scope="col">Porcentaje</th>
                <!--            <th scope="col" class="text-center">Candidato</th>
                            <th scope="col" class="text-center">Miembro de mesa</th>
                            <th scope="col" class="text-center">Voto emitido</th>-->
                <!--<th scope="col" class="text-center">Acción</th>-->
            </tr>
        </thead>
        <tbody>
            <c:forEach var="ciudadano" items="${ciudadanos}">  
                <tr>
                    <td>${ciudadano.getDni()}</td>
                    <td>${ciudadano.getApellidos()}</td>
                    <td>${ciudadano.getNombres()}</td>
                    <td class="text-center">
                        <c:choose>
                            <c:when test="${ciudadano.getCandidato() == true}">
                                <span class="badge badge-primary badge-outlined">
                                    <div class="h6 mb-0">si</div>
                                </span>
                            </c:when>
                            <c:otherwise>
                                <span class="badge badge-danger badge-outlined">
                                    <div class="h6 mb-0">no</div>
                                </span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td class="text-center">
                        <c:choose>
                            <c:when test="${ciudadano.getMiembromesa() == true}">
                                <span class="badge badge-primary badge-outlined">
                                    <div class="h6 mb-0">si</div>
                                </span>
                            </c:when>
                            <c:otherwise>
                                <span class="badge badge-danger badge-outlined">
                                    <div class="h6 mb-0">no</div>
                                </span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td class="text-center">
                        <c:choose>
                            <c:when test="${ciudadano.getEmitido()}">
                                <span class="badge badge-primary badge-outlined">
                                    <div class="h6 mb-0">si</div>
                                </span>
                            </c:when>
                            <c:otherwise>
                                <span class="badge badge-danger badge-outlined">
                                    <div class="h6 mb-0">no</div>
                                </span>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <!--            <td>
                                    <button class="btn btn-success btn-block">Editar</button>
                                </td>-->
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<jsp:include page='../componentes/comunes/piecera.jsp'/>


