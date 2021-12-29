<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <ul class="c-t-lg mb-5 pb-5" id="listaDepartamentos">	
	<c:forEach var="tempEntidad2" items="${listaEntidadesLvl2}">
 		<li class="mb-4"><a class="c-link" href="Organismo1Servlet?idEntidad=${tempEntidad2.id_entidad }" title="Ir a página del ${tempEntidad2.nombre }">${tempEntidad2.nombre }</a></li>
 	</c:forEach>
 </ul>
