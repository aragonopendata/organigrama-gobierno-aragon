<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es"><head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <c:set var="entidadSeleccionada" value="${entidadSeleccionada}"/>
  <title><c:out value="${entidadSeleccionada.nombre}"/> del Gobierno de Aragón</title>
  <link rel="icon" type="image/x-icon" href="https://aplicaciones.aragon.es/favicon.ico">
  <link href="css/main.css" rel="stylesheet" type="text/css">
  <link href="css/clay.css" rel="stylesheet" type="text/css">
  <link href="css/styles.css" rel="stylesheet" type="text/css">
</head>

<body class="controls-visible default-color-scheme yui3-skin-sam guest-site signed-out public-page site">

  <div id="senna_surface1-screen_2" style="display: block;" class="flipped">

   <nav class="quick-access-nav" id="dvqk_quickAccessNav" aria-label="Saltar zonas del contenido">
    <ul>
      <li><a href="#content" class="js-salto-contenido" accesskey="0">Salto a contenido</a></li>
      <li><a href="#footer" class="js-salto-nav" accesskey="2">Saltar a navegación en el pie de la página</a></li>
    </ul>
  </nav>

  <div class="container-fluid dga-view px-0" id="wrapper">
    <header class="header" id="banner" aria-label="Cabecera" role="banner" tabindex="-1">
      <c:set var="link_aragon_raiz" value="${link_aragon_raiz}"/>
      <c:set var="link_aragon_servicios" value="${link_aragon_servicios}"/>
      <div class="navbar container">
        <div id="heading">
          <p class="site-title">
            <a href="${link_aragon_raiz}" class="logo-dga" title="Ir a la página principal del portal.">
              <img src="${link_aragon_raiz}o/aragon-theme/images/dga/logo-dga-color.svg" height="50" alt='Gobierno de Aragón.'>
            </a>
          </p>
        </div>
        <div class="navigation">
          <a href="${link_aragon_raiz}-/fondos-europeos-aragon" class="european-flag-head" title="Más información sobre los Fondos Estructurales y de Inversión Europeos y Fondo Next Generation EU en Aragón">
            <img class="image" src="${link_aragon_raiz}o/aragon-theme/images/dga/flag_europe.svg"  alt="Unión Europea">
          </a>
          <ul class="zoom hidden-xs">
            <li class="zoom__pos"><a aria-disabled="false" tabindex="0">A<sup>+</sup><span class="oculto">Aumentar el tamaño de texto un 200%</span></a></li>
            <li class="zoom__neg"><a aria-disabled="false" tabindex="0">A<sup>-</sup><span class="oculto">Disminuir el tamaño de texto un 200%</span></a></li>
          </ul>
        </div>
      </div>
      <ul class="zoom responsive hidden-sm hidden-md hidden-lg">
        <li class="zoom__pos"><a href="#" aria-disabled="false" tabindex="0">A<sup>+</sup><span class="oculto">Aumentar el tamaño de texto un 200%</span></a></li>
        <li class="zoom__neg"><a href="#" aria-disabled="false" tabindex="0">A<sup>-</sup><span class="oculto">Disminuir el tamaño de texto un 200%</span></a></li>
      </ul>
    </header>

    <main id="content" class="container pb-5 detail-news-module__body-news__description">
      <c:set var="entidadAnterior" value="${entidadPadre}"/>
      <nav aria-label="breadcrumb">
        <ol class="breadcrumb c-t-sm">
          <li class="breadcrumb-item"><a href="index.html" class="text-secondary" title="Ir a la pagina de inicio">Inicio</a></li>
          <li class="breadcrumb-item"><a href="Organismo1Servlet?idEntidad=${entidadAnterior.id_entidad}" class="text-secondary" title="Ir a ${entidadAnterior.nombre }"><c:out value="${entidadAnterior.nombre}"/></a></li>
          <li class="breadcrumb-item active text-dark" aria-current="page"><c:out value="${entidadSeleccionada.nombre}"/></li>
        </ol>
      </nav>
      <div class="mt-5 mb-5 pb-5 ">
        <h1 class="c-h1"><c:out value="${entidadSeleccionada.nombre}"/></h1>
       	<c:if test="${entidadSeleccionada.pagina_web != null }">
       		<p><a href="<c:out value="${entidadSeleccionada.pagina_web}"/>" class="c-link">Visitar web<span class="sr-only">de <c:out value="${entidadSeleccionada.pagina_web_titulo}"/></span><svg class="align-middle d-inline-block ml-2" style="fill:currentColor;" xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 24 24" width="1em" fill="#000000" aria-hidden="true" focusable="false" role="img"><path d="M0 0h24v24H0V0z" fill="none"/><path d="M19 19H5V5h7V3H5c-1.11 0-2 .9-2 2v14c0 1.1.89 2 2 2h14c1.1 0 2-.9 2-2v-7h-2v7zM14 3v2h3.59l-9.83 9.83 1.41 1.41L19 6.41V10h2V3h-7z"/></svg></a></p>
     	</c:if>
      </div>
        <div class="mb-5 pb-5">
          <c:set var="departamento" value="${infoDepartamento}"/>
        
          <div class="row">
            <c:if test="${departamento.responsable_nombre != null }">
            <div class="col-12">
              <p class="c-h3 mb-2"><c:out value="${departamento.responsable_nombre }"/></p>
              <p class="c-paragraph-base mb-3 mt-0"><c:out value="${departamento.responsable_cargo }"/></p>
              <ul class="c-t-sm text-secondary mb-4" aria-label="datos de contacto">
              	<c:if test="${departamento.direccion != null }">
                	<li class="mb-2"><svg class="align-middle d-inline-block mr-2" xmlns="http://www.w3.org/2000/svg" height="1.2em" viewBox="0 0 24 24" width="1.2em" fill="#F05442" aria-label="Dirección postal" role="img"><path d="M0 0h24v24H0z" fill="none"/><path d="M12 2C8.13 2 5 5.13 5 9c0 5.25 7 13 7 13s7-7.75 7-13c0-3.87-3.13-7-7-7zm0 9.5c-1.38 0-2.5-1.12-2.5-2.5s1.12-2.5 2.5-2.5 2.5 1.12 2.5 2.5-1.12 2.5-2.5 2.5z"/></svg> <c:out value="${departamento.direccion }"/>, <c:out value="${departamento.cp }"/> <c:out value="${departamento.localidad }"/> <c:out value="(${departamento.provincia })"/></li>
                </c:if>
                <c:if test="${departamento.telefono != null }">
                	<li class="mb-2"><svg class="align-middle d-inline-block mr-2" xmlns="http://www.w3.org/2000/svg" height="1.2em" viewBox="0 0 24 24" width="1.2em" fill="#F05442"  aria-label="Teléfono" role="img"><path d="M0 0h24v24H0V0z" fill="none"/><path d="M19.23 15.26l-2.54-.29c-.61-.07-1.21.14-1.64.57l-1.84 1.84c-2.83-1.44-5.15-3.75-6.59-6.59l1.85-1.85c.43-.43.64-1.03.57-1.64l-.29-2.52c-.12-1.01-.97-1.77-1.99-1.77H5.03c-1.13 0-2.07.94-2 2.07.53 8.54 7.36 15.36 15.89 15.89 1.13.07 2.07-.87 2.07-2v-1.73c.01-1.01-.75-1.86-1.76-1.98z"/></svg> <c:out value="${departamento.telefono }"/></li>
                	</c:if>
                <c:if test="${departamento.email != null }">
                	<li class="mb-2"><svg class="align-middle d-inline-block mr-2" xmlns="http://www.w3.org/2000/svg" height="1.2em" viewBox="0 0 24 24" width="1.2em" fill="#F05442" aria-label="Correo electrónico" role="img"><path d="M0 0h24v24H0z" fill="none"/><path d="M20 4H4c-1.1 0-1.99.9-1.99 2L2 18c0 1.1.9 2 2 2h16c1.1 0 2-.9 2-2V6c0-1.1-.9-2-2-2zm0 4l-8 5-8-5V6l8 5 8-5v2z"/></svg> <a href="mailto:${departamento.email }" class="text-secondary" title="mandar correo a ${departamento.nombre }"><c:out value="${departamento.email }"/></a></li>
                </c:if>
              </ul>
              <c:if test="${departamento.responsable_agenda != null }">
              	<p class="mb-3"><a class="c-link" href="${departamento.responsable_agenda }" title="Ver Agenda del ${departamento.nombre }">Agenda <span class="sr-only">de <c:out value="${departamento.responsable_cargo }"/></span><svg class="align-middle d-inline-block ml-2" style="fill:currentColor;" xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 24 24" width="1em" fill="#000000" aria-hidden="true" focusable="false"><path d="M0 0h24v24H0V0z" fill="none"/><path d="M19 19H5V5h7V3H5c-1.11 0-2 .9-2 2v14c0 1.1.89 2 2 2h14c1.1 0 2-.9 2-2v-7h-2v7zM14 3v2h3.59l-9.83 9.83 1.41 1.41L19 6.41V10h2V3h-7z"/></svg></a></p>
              </c:if>
              <c:if test="${departamento.responsable_biografia != null }">
              <details>
                <summary class="c-link c-t-semibold" title="Desplegar información de Biografía">Biografía</summary>
                <div class="pt-4 pb-4">
                  <div>
                    <p>${departamento.responsable_biografia }</p>
                  </div>
                </div>
              </details>
              </c:if>
            </div>
            </c:if>
          </div>
        </div>
		<c:forEach var="tempEntidad" items="${entidadesHijas}">
        <div class="mb-4">
          <h2 class="c-h2 mb-0">${tempEntidad.nombre }</h2>
          <c:if test = "${tempEntidad.pagina_web != null}">
          	<p><a href="${tempEntidad.pagina_web }" class="c-link">Visitar web<span class="sr-only">de ${tempEntidad.nombre }</span><svg class="align-middle d-inline-block ml-2" style="fill:currentColor;" xmlns="http://www.w3.org/2000/svg" height="1em" viewBox="0 0 24 24" width="1em" fill="#000000" aria-hidden="true" focusable="false" role="img"><path d="M0 0h24v24H0V0z" fill="none"/><path d="M19 19H5V5h7V3H5c-1.11 0-2 .9-2 2v14c0 1.1.89 2 2 2h14c1.1 0 2-.9 2-2v-7h-2v7zM14 3v2h3.59l-9.83 9.83 1.41 1.41L19 6.41V10h2V3h-7z"/></svg></a></p>
          </c:if>
        </div>
        <ul class="c-t-lg mb-5 pb-5 border-top">
        <c:forEach var="tempCargo" items="${listaEmpleados}">
        <c:if test = "${tempCargo.id_entidad == tempEntidad.id_entidad}">
          <li class="pb-4 pt-4 border-bottom">
            <p><strong>${tempCargo.nombre}</strong> - ${tempCargo.cargo }</p>
            <ul class="c-t-sm text-secondary mb-0">
              <c:if test = "${tempEntidad.direccion != null}">
				<li class="mb-2"><svg class="align-middle d-inline-block mr-2" xmlns="http://www.w3.org/2000/svg" height="1.2em" viewBox="0 0 24 24" width="1.2em" fill="#F05442" aria-label="DirecciÃ³n postal" role="img"><path d="M0 0h24v24H0z" fill="none"/><path d="M12 2C8.13 2 5 5.13 5 9c0 5.25 7 13 7 13s7-7.75 7-13c0-3.87-3.13-7-7-7zm0 9.5c-1.38 0-2.5-1.12-2.5-2.5s1.12-2.5 2.5-2.5 2.5 1.12 2.5 2.5-1.12 2.5-2.5 2.5z"/></svg> <c:out value="${tempEntidad.direccion}"/> <c:out value="${tempEntidad.cp}"/> <c:out value="${tempEntidad.localidad}"/> <c:out value="(${tempEntidad.provincia})"/></li>
              </c:if>
              <c:if test = "${tempEntidad.telefono != null}">
              	<li class="mb-2"><svg class="align-middle d-inline-block mr-2" xmlns="http://www.w3.org/2000/svg" height="1.2em" viewBox="0 0 24 24" width="1.2em" fill="#F05442"  aria-label="TelÃ©fono" role="img"><path d="M0 0h24v24H0V0z" fill="none"/><path d="M19.23 15.26l-2.54-.29c-.61-.07-1.21.14-1.64.57l-1.84 1.84c-2.83-1.44-5.15-3.75-6.59-6.59l1.85-1.85c.43-.43.64-1.03.57-1.64l-.29-2.52c-.12-1.01-.97-1.77-1.99-1.77H5.03c-1.13 0-2.07.94-2 2.07.53 8.54 7.36 15.36 15.89 15.89 1.13.07 2.07-.87 2.07-2v-1.73c.01-1.01-.75-1.86-1.76-1.98z"/></svg> <c:out value="${tempEntidad.telefono}"/></li>
              </c:if>
              <c:if test = "${tempEntidad.email != null}">
              	<li class="mb-2"><svg class="align-middle d-inline-block mr-2" xmlns="http://www.w3.org/2000/svg" height="1.2em" viewBox="0 0 24 24" width="1.2em" fill="#F05442" aria-label="Correo electrÃ³nico" role="img"><path d="M0 0h24v24H0z" fill="none"/><path d="M20 4H4c-1.1 0-1.99.9-1.99 2L2 18c0 1.1.9 2 2 2h16c1.1 0 2-.9 2-2V6c0-1.1-.9-2-2-2zm0 4l-8 5-8-5V6l8 5 8-5v2z"/></svg> <a href="mailto:${tempEntidad.email}" class="text-secondary" title="mandar correo a ${tempEntidad.nombre}"><c:out value="${tempEntidad.email}"/></a></li>
              </c:if>
            </ul>
          </li>
        </c:if>
        </c:forEach>
        </ul>
        </c:forEach>
    </main>

    <footer id="footer" role="contentinfo" class="footer mt-5" tabindex="-1">
       <div class="portlet-boundary portlet-boundary_com_liferay_journal_content_web_portlet_JournalContentPortlet_ portlet-static portlet-static-end portlet-barebone portlet-journal-content " id="p_p_id_com_liferay_journal_content_web_portlet_JournalContentPortlet_INSTANCE_footerContentBottom_">
        <section class="portlet" id="portlet_com_liferay_journal_content_web_portlet_JournalContentPortlet_INSTANCE_footerContentBottom">
          <div class="portlet-content">
            <div class="portlet-content-container">
              <div class="portlet-body">
                <div class="clearfix journal-content-article">
                  <div class="footer__policies">
                    <ul class="container footer__policies__listado">
                      <li class="logo link">
                        <a href="${link_aragon_servicios}sus_publico/PublicoServlet?accion=1" class="hidden-md hidden-lg u-btn u-btn-red"><strong>Suscripciones</strong></a>
                      </li>
                      <li class="logo-eu link hidden-sm hidden-sm hidden-md hidden-lg">
                        <a href="${link_aragon_raiz}Fondos_Europeos" class="european-flag" title="Más información sobre los Fondos Estructurales y de Inversión Europeos (Fondos EIE)" target="_blank" rel="noopener">
                          <img class="image" src="${link_aragon_raiz}o/aragon-theme/images/dga/flag_europe.svg" alt="Unión Europea">
                          <p class="claim">
                          <span>Fondo Europeo de Desarrollo Regional </span>
                          </p>
                          <p class="sub-claim">"Construyendo Europa desde Aragón"</p>
                        </a>
                      </li>
                      <li class="link"><a href="${link_aragon_raiz}">Inicio</a></li>
                      <li class="link"><a href="${link_aragon_raiz}-/aviso-legal-1">Aviso Legal</a></li>
                      <li class="link"><a href="${link_aragon_raiz}-/detalle-sobre-las-cookies-en-el-portal-de-servicios-del-gobierno-de-aragon">Política de cookies</a></li>
                      <li class="link"><a href="${link_aragon_raiz}-/politica-de-privacidad">Política de privacidad</a></li>
                      <li class="link"><a href="${link_aragon_raiz}-/accesibilidad">Accesibilidad</a></li>
                      <li class="link"><a href="${link_aragon_raiz}mapa-web">Mapa Web</a></li>
                      <li class="link cta hidden-xs hidden-sm"><a href="${link_aragon_servicios}sus_publico/PublicoServlet?accion=1" class="u-btn u-btn-red"><strong>Suscripciones</strong></a></li>
                    </ul>
                  </div>
                  <div class="footer__rrss-contact container">
                    <div class="row">
                      <ul class="col-xs-12 col-sm-12 col-md-3 rrss">
                        <li>
                          <a href="http://www.youtube.com/user/GobiernoAragon" class="icon-rrss"><img src="./img/youtube.jpg" alt="YouTube. Ir al canal del Gobierno de Aragón."></a>
                        </li>
                        <li>
                         <a href="https://www.facebook.com/GobAragon" class="icon-rrss"><img src="./img/facebook.jpg" alt="Facebook. Ir al facebook del Gobierno de Aragón."></a>
                        </li>
                        <li>
                          <a href="https://twitter.com/GobAragon" class="icon-rrss"><img src="./img/twitter.jpg" alt="Twitter. Ir al twitter del Gobierno de Aragón."></a>
                        </li>
                        <li>
                          <a href="https://www.instagram.com/gobaragon/?hl=es" class="icon-rrss"><img src="./img/instagram.jpg" alt="Instagram. Ir al instagram del Gobierno de Aragón."></a>
                        </li>
                      </ul>
                      <div class="col-xs-12 col-sm-12 col-md-6">
                        <span class="name">Gobierno de Aragón</span>
                        <span>Edificio Pignatelli. Pº María Agustín, 36. 50004 - Zaragoza - <abbr title="Teléfono">Tfno.</abbr> <a href="tel:+34976714000">+34 976714000</a></span>
                      </div>
                      <a href="${link_aragon_raiz}-/fondos-europeos-aragon" class="hidden-xs col-sm-3 european-flag" title="Más información sobre los Fondos Estructurales y de Inversión Europeos (Fondos EIE)">
                        <img class="image mx-auto" src="${link_aragon_raiz}o/aragon-theme/images/dga/flag_europe.svg" alt="Unión Europea">
                        <p class="claim">
                          <span>Fondo Europeo de Desarrollo Regional </span>
                        </p>
                        <p class="sub-claim">"Construyendo Europa desde Aragón"</p>
                      </a>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </section>
      </div>
    </footer>
  </div>
</div>
<script
  src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script><script src="js/main.js"></script>
</body>
</html>
