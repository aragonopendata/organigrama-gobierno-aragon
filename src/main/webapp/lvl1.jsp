<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	    <c:forEach var="tempEntidad1" items="${listaEntidadesLvl1}">
        <div class="mb-5 pb-5">
          <a href="Organismo1Servlet?idEntidad=${tempEntidad1.id_entidad}"><h2 class="c-h2 mt-3 mb-3">${tempEntidad1.nombre }</h2></a>
          <div class="row">
            <div class="col-8 col-sm-3 ">
              <img class="w-100 mb-4 border" loading="lazy" aria-hidden="true" alt="Fotografia de ${tempEntidad1.responsable_nombre }" src="data:image/jpeg;base64,${tempEntidad1.responsable_foto_contenido }">
            </div>
            <div class="col-12 col-sm-8">
              <p class="c-h3 mb-2">${tempEntidad1.responsable_nombre }</p>
              <p class="c-paragraph-base mb-3 mt-0">${tempEntidad1.responsable_cargo }</p>
              <h3 class="sr-only">Datos de contacto</h3>
              <ul class="c-t-sm mb-5 text-secondary">
              	<c:if test="${tempEntidad1.direccion != null }">
              		<li class="mb-2"><svg class="align-middle d-inline-block mr-2" xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 24 24" width="1em" fill="#F05442" aria-hidden="true" focusable="false" role="img"><title>marcador de mapa</title><path d="M0 0h24v24H0z" fill="none"/><path d="M12 2C8.13 2 5 5.13 5 9c0 5.25 7 13 7 13s7-7.75 7-13c0-3.87-3.13-7-7-7zm0 9.5c-1.38 0-2.5-1.12-2.5-2.5s1.12-2.5 2.5-2.5 2.5 1.12 2.5 2.5-1.12 2.5-2.5 2.5z"/></svg><span class="sr-only">Dirección física:</span> ${tempEntidad1.direccion } ${tempEntidad1.cp } ${tempEntidad1.localidad } (${tempEntidad1.provincia })</li>
              	</c:if>
              	<c:if test="${tempEntidad1.telefono != null }">
              	<li class="mb-2"><svg class="align-middle d-inline-block mr-2" xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 24 24" width="1em" fill="#F05442" aria-hidden="true" focusable="false" role="img"><title>teléfono</title><path d="M0 0h24v24H0V0z" fill="none"/><path d="M19.23 15.26l-2.54-.29c-.61-.07-1.21.14-1.64.57l-1.84 1.84c-2.83-1.44-5.15-3.75-6.59-6.59l1.85-1.85c.43-.43.64-1.03.57-1.64l-.29-2.52c-.12-1.01-.97-1.77-1.99-1.77H5.03c-1.13 0-2.07.94-2 2.07.53 8.54 7.36 15.36 15.89 15.89 1.13.07 2.07-.87 2.07-2v-1.73c.01-1.01-.75-1.86-1.76-1.98z"/></svg><span class="sr-only">Teléfono:</span> ${tempEntidad1.telefono }</li>
              	</c:if>
              	<c:if test="${tempEntidad1.email != null }">
              	<li class="mb-2"><svg class="align-middle d-inline-block mr-2" xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 24 24" width="1em" fill="#F05442" aria-hidden="true" focusable="false" role="img"><title>Sobre</title><path d="M0 0h24v24H0z" fill="none"/><path d="M20 4H4c-1.1 0-1.99.9-1.99 2L2 18c0 1.1.9 2 2 2h16c1.1 0 2-.9 2-2V6c0-1.1-.9-2-2-2zm0 4l-8 5-8-5V6l8 5 8-5v2z"/></svg> <span class="sr-only">correo electrónico:</span><a href="mailto:${tempEntidad1.email }" class="c-link-secondary" title="mandar correo a ${tempEntidad1.responsable_nombre }">${tempEntidad1.email }</a></li>
              	</c:if>    
              </ul>
              <h3 class="sr-only">Información adicional</h3>
              <c:if test="${tempEntidad1.responsable_agenda != null }">
	              <p class="mb-3"><a class="c-link" href="${tempEntidad1.responsable_agenda }" title="Ver Agenda del ${tempEntidad1.nombre }">Agenda <span class="sr-only">de ${tempEntidad1.responsable_cargo }</span><svg class="align-middle d-inline-block ml-2" style="fill:currentColor;" xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 24 24" width="1em" fill="#000000" aria-hidden="true" focusable="false" role="img"><path d="M0 0h24v24H0V0z" fill="none"/><path d="M19 19H5V5h7V3H5c-1.11 0-2 .9-2 2v14c0 1.1.89 2 2 2h14c1.1 0 2-.9 2-2v-7h-2v7zM14 3v2h3.59l-9.83 9.83 1.41 1.41L19 6.41V10h2V3h-7z"/></svg></a></p>
              </c:if>
              <c:if test="${tempEntidad1.responsable_biografia != null }">
	              <details class="mb-3">
	              	<summary class="c-link" title="Desplegar información de Biografía">Biografía <span class="sr-only">${tempEntidad1.responsable_cargo }</span></summary>
	                <div class="pt-4 pb-4">
	                  <div>
	                    <p>${tempEntidad1.responsable_biografia }</p>
	                  </div>
	                </div>
	              </details>
              </c:if>
              
            </div>
          </div>
        </div>
       </c:forEach>