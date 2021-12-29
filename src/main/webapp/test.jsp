<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, es.aragon.orgavi.util.*" %>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Monitorización de la aplicación</title>
<link rel="icon" type="image/x-icon" href="https://aplicaciones.aragon.es/favicon.ico">
</head>
<body>
<%
//Test de conectividad con la BBDD
try{
	Connection conexion;
	conexion = Conexion.getConexion();
	if(conexion != null){
		out.print("Conexión a la base de datos: OK");
	}else{
		out.print("Conexión a la base de datos: KO");
		//out.print("Causa: " + e);
	}
}catch(Exception e){
	out.print("Conexión a la base de datos: KO");
	//out.print("Causa: " + e);
}

%>
</body>
</html>