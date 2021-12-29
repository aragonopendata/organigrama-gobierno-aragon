<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="es"><head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Histórico de legislaturas - Organigrama del Gobierno de Aragón</title>
  <link rel="icon" type="image/x-icon" href="https://aplicaciones.aragon.es/favicon.ico">
  <link href="https://www.aragon.es/o/aragon-theme/css/main.css" rel="stylesheet" type="text/css">
  <link href="https://www.aragon.es/o/aragon-theme/css/clay.css" rel="stylesheet" type="text/css">
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
      <div class="navbar container">
        <div id="heading">
          <p class="site-title">
            <a href="https://www.aragon.es" class="logo-dga" title="Ir a la página principal del portal.">
              <img src="https://www.aragon.es/o/aragon-theme/images/dga/logo-dga-color.svg" height="50" alt='Gobierno de AragÃ³n.'>
            </a>
          </p>
        </div>
        <div class="navigation">
          <a href="https://www.aragon.es/-/fondos-europeos-aragon" class="european-flag-head" title="Más información sobre los Fondos Estructurales y de Inversión Europeos y Fondo Next Generation EU en Aragón">
            <img class="image" src="https://www.aragon.es/o/aragon-theme/images/dga/flag_europe.svg"  alt="Unión Europea">
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
      <nav aria-label="breadcrumb">
        <ol class="breadcrumb c-t-sm">
          <li class="breadcrumb-item"><a href="index.html" class="text-secondary" title="Ir a la pagina de inicio">Inicio</a></li>
          <li class="breadcrumb-item active text-dark" aria-current="page">Histórico de Legislaturas</li>
        </ol>
      </nav>
      <div class="row">
        <h1 id="main-title" class="c-h1 col mt-5 mb-5 pb-5">Histórico de legislaturas</h1>
      </div>

      <div class="mb-5 pb-5">
        <div class="row">
          <div class="col-12 col-md-3 mb-5 pb-5">
            <nav class="c-sidebar" aria-label="Menu de Listado de legislaturas">
              <ul>
              	<c:forEach var="tempLegislatura" items="${legislaturas}">
              		<li class="mb-4"><a class="c-t-sm text-secondary" href="HistoricoLegislaturasServlet?idLegislatura=${tempLegislatura.id_legislatura}" ><td>${tempLegislatura.nombre}</td></a></li>
              	</c:forEach>
              </ul>
            </nav>
          </div>
          <div class="col-12 col-md-9 border-left border-secondary">
          	<c:set var="legislaturaSeleccionada" value="${legislaturaSeleccionada}"/>
            <h2 class="c-h2 mb-5"><c:out value="${legislaturaSeleccionada.nombre}"/></h2>
            <c:forEach var="tempEntidadLvl1" items="${listaEntidadesLvl1}">
            	<p class="mb-2 mt-5"><strong>${tempEntidadLvl1.nombre}</strong></p>
            	<c:forEach var="tempCargoLvl1" items="${listaCargosLvl1}">
            		<c:if test = "${tempEntidadLvl1.id_entidad == tempCargoLvl1.id_entidad}">
            			<p class="mb-2">${tempCargoLvl1.nombre}</p>
            		</c:if>
            	</c:forEach>
            </c:forEach>
            <c:forEach var="tempEntidadLvl2" items="${listaEntidadesLvl2}">
            	<p class="mb-2 mt-5"><strong>${tempEntidadLvl2.nombre}</strong></p>
            	<c:forEach var="tempCargoLvl2" items="${listaCargosLvl2}">
            		<c:if test = "${tempEntidadLvl2.id_entidad == tempCargoLvl2.id_entidad}">
            			<p class="mb-2">${tempCargoLvl2.nombre}</p>
            		</c:if>
            	</c:forEach>
            </c:forEach>
          </div>
        </div>
      </div>

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
                        <a href="http://servicios.aragon.es/sus_publico/PublicoServlet?accion=1" class="hidden-md hidden-lg u-btn u-btn-red"><strong>Suscripciones</strong></a>
                      </li>
                      <li class="logo-eu link hidden-sm hidden-sm hidden-md hidden-lg">
                        <a href="http://www.aragon.es/Fondos_Europeos" class="european-flag" title="Más información sobre los Fondos Estructurales y de Inversión Europeos (Fondos EIE)" target="_blank" rel="noopener">
                          <img class="image" src="https://www.aragon.es/o/aragon-theme/images/dga/flag_europe.svg" alt="Unión Europea">
                          <p class="claim">
                          <span>Fondo Europeo de Desarrollo Regional </span>
                          </p>
                          <p class="sub-claim">"Construyendo Europa desde Aragón"</p>
                        </a>
                      </li>
                      <li class="link"><a href="https://www.aragon.es/">Inicio</a></li>
                      <li class="link"><a href="https://www.aragon.es/-/aviso-legal-1">Aviso Legal</a></li>
                      <li class="link"><a href="https://www.aragon.es/-/detalle-sobre-las-cookies-en-el-portal-de-servicios-del-gobierno-de-aragon">Política de cookies</a></li>
                      <li class="link"><a href="https://www.aragon.es/-/politica-de-privacidad">Política de privacidad</a></li>
                      <li class="link"><a href="https://www.aragon.es/-/accesibilidad">Accesibilidad</a></li>
                      <li class="link"><a href="https://www.aragon.es/mapa-web">Mapa Web</a></li>
                      <li class="link cta hidden-xs hidden-sm"><a href="http://servicios.aragon.es/sus_publico/PublicoServlet?accion=1" class="u-btn u-btn-red"><strong>Suscripciones</strong></a></li>
                    </ul>
                  </div>
                  <div class="footer__rrss-contact container">
                    <div class="row">
                      <ul class="col-xs-12 col-sm-12 col-md-3 rrss">
                        <li>
                          <a href="http://www.youtube.com/user/GobiernoAragon" class="icon-rrss"><img src="https://www.aragon.es/documents/20127/185258/youtube.jpg/265feae0-4185-4eae-dc88-f989c6c30e43?t=1560274281563" alt="YouTube. Ir al canal del Gobierno de AragÃ³n."></a>
                        </li>
                        <li>
                         <a href="https://www.facebook.com/GobAragon" class="icon-rrss"><img src="https://www.aragon.es/documents/20127/185258/facebook.jpg/3d6a39c0-befe-e18d-7fe8-6ea04bf462ce?t=1560274299851" alt="Facebook. Ir al facebook del Gobierno de AragÃ³n."></a>
                        </li>
                        <li>
                          <a href="https://twitter.com/GobAragon" class="icon-rrss"><img src="https://www.aragon.es/documents/20127/185258/twitter.jpg/cce68d2f-088d-4103-df24-26895a8b339a?t=1560274293648" alt="Twitter. Ir al twitter del Gobierno de AragÃ³n."></a>
                        </li>
                        <li>
                          <a href="https://www.instagram.com/gobaragon/?hl=es" class="icon-rrss"><img src="https://www.aragon.es/documents/20127/185258/instagram+%283%29.png/d97c46d6-dd1c-5480-439d-f33f1b403d9b?t=1603706682623" alt="Instagram. Ir al instagram del Gobierno de AragÃ³n."></a>
                        </li>
                      </ul>
                      <div class="col-xs-12 col-sm-12 col-md-6">
                        <span class="name">Gobierno de Aragón</span>
                        <span>Edificio Pignatelli. Pº María Agustín, 36. 50004 - Zaragoza - <abbr title="Teléfono">Tfno.</abbr> <a href="tel:+34976714000">+34 976714000</a></span>
                      </div>
                      <a href="/-/fondos-europeos-gobierno-de-aragon" class="hidden-xs col-sm-3 european-flag" title="Más información sobre los Fondos Estructurales y de Inversión Europeos (Fondos EIE)">
                        <img class="image mx-auto" src="https://www.aragon.es/o/aragon-theme/images/dga/flag_europe.svg" alt="Unión Europea">
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
