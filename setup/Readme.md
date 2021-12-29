# Manual de instalación

## Descripción de la aplicación
Orgavi es la aplicación que implementa el **Organigrama del Gobierno de Aragón**.
Su desarrollo se ha realizado en Java, HTML, CSS y Javascript, apoyados en etiquetas JSP.
Arquitectura basada en el patrón de Modelo-Vista-Controlador.
La aplicación se inicia en la vista `index.html` y desde ella podemos visualizar información o acceder, mediante enlaces, al resto de vistas que muestran información de las diferentes entidades y cargos del Gobierno de Aragón.
La vista que muestra el histórico de legislaturas no es accesible desde enlaces alojados en la aplicación, sino que se accede desde enlaces externos.
La aplicación requiere, para la obtención de los datos a mostrar, de una conexión a la base de datos, cuyos parámetros de conexión de establecen en los ficheros de configuración existentes para cada uno de los entornos.

## Instalación en cada entorno
La aplicación no requiere de proceso de instalación. 
Una vez desplegada en el entorno adecuado únicamente requiere de conexión con la base de datos para funcionar.
## Instrucciones de creación de directorios en el servidor
La presente aplicación únicamente crea en el servidor el directorio que almacenará los *logs* de la misma, y lo realiza automáticamente mediante la librería *log4j2*, no siendo necesaria ninguna gestión manual.

Según consta en el apartado *4.8 Log de Aplicación* de las *Especificaciones técnicas para el alojamiento de aplicaciones*, la "ruta donde se dejan los ficheros de log será configurable por parametrización en fichero de propiedades. Se utilizará la ruta '/logs/app/<cod_app>' siendo <cod_app> el código de aplicación asignado".

Es por ello que se ha definido la ruta `/logs/app/orgavi/orgavi.log` en las propiedades del *log*, la cual puede modificarse desde el fichero de configuración `log4j2.properties` emplazado en cada uno de los tres directorios de configuración existentes, uno para cada entorno:
- `orgavi/src/conf/data-sources/des/log4j2.properties` para el entorno de desarrollo
- `orgavi/src/conf/data-sources/pre/log4j2.properties` para el entorno de preproducción
- `orgavi/src/conf/data-sources/pro/log4j2.properties` para el entorno de producción

La modificación de la ruta se realizará cambiando el valor de la propiedad: 'property.filename' teniéndose en cuenta que parte de la raíz del sistema.

## Ficheros de configuración
En la ruta `orgavi/src/conf/data-sources` existen tres directorios de configuración, uno para cada entorno, y un fichero de configuración común a todos los entornos, resultando:
- `orgavi/src/conf/data-sources/entorno.properties` para la configuración común a todos los entornos
- `orgavi/src/conf/data-sources/des` para el entorno de desarrollo
- `orgavi/src/conf/data-sources/pre` para el entorno de preproducción
- `orgavi/src/conf/data-sources/pro` para el entorno de producción

El fichero entorno.properties es común a todos los entornos y contiene las siguientes propiedades:
- Implementation-Vendor: contiene el nombre de la empresa desarrolladora
- Entorno-Config: contiene la palabra clave del entorno que se desea implementar (pro para producción, pre para preproducción y en caso de no ser ninguna otra utiliza por defecto el entorno de desarrollo). El valor debe escribirse en minúsculas y sin las comillas "pro" ó "pre".

En cada uno de los directorios de entorno encontramos los siguientes ficheros de configuración:
1. Fichero `app.properties`
Este fichero contiene las siguientes propiedades:
  - Implementation-Vendor: contiene el nombre de la empresa desarrolladora
  - Implementation-Version: contiene la versión de la aplicación

2. Fichero `log4j2.properties`
Este fichero contiene la estructura básica de atributos de la librería *log4j2* para la realización de *logs*.
Entre ellos destacan las siguientes propiedades:
  - property.filename: contiene la ruta en donde se almacenarán los *logs*
  - filter.treshold.level: contiene el nivel de tolerancia del *log*
  - appender.rolling.layout.pattern: contiene la plantilla de formato con la que se realizará cada *log*

Para la descripción y configuración de otros atributos puede consultarse la documentación oficial de [log4j2](https://logging.apache.org/log4j/2.x/manual/configuration.html#Properties "Ir a log4j2")

3. Fichero `OrgaviResources.properties`
Este fichero contiene el *pool* de conexiones con la Base de Datos
Entre sus propiedades destacan las siguientes:
  - jdbc_DataSourceName: contiene el nombre de Origen de los datos
  - jdbc_DataSourceDriver: contiene el driver de la base de datos
  - jdbc_Tnsnames: contiene el driver:protocolo del driver:@ruta:puerto/nombre de la base de datos
  - jdbc_DataBaseUserName: contiene el nombre del usuario para acceder a la base de datos
  - jdbc_DataBasePassword: contiene la contraseña de acceso a la base de datos

## Relación de cambios implementados respecto a la versión anterior
Modelo explicativo de la plantilla de registro de cambios:
- Fecha: Contendrá la fecha de la modificación de la versión
- Versión anterior: Contendrá la versión sobre la cual se implementan los cambios
- Versión actual: Contendrá la versión resultante de los cambios implementados, la versión actual
- Técnico: Nombre del técnico responsable de los cambios implementados en la nueva versión
- Ticket asociado: En caso de existir, ticket de referencia, signatura de jira o cualquier otro identificativo de la tarea llevada a cabo
- Incidencias causadas: Indicar si ha existido alguna incidencia que reportar
- Nombre del commit: Identificación del commit con los cambios realizados
> Los cambios realizados en fase de desarrollo no se reflejan

## Comprobación del servicio
La aplicación web depende de la correcta conexión a la base de datos.
Para su comprobación se han generado las rutas solicitadas en el apartado *4.11 Monitorización de la aplicación* de las *Especificaciones técnicas para el alojamiento de aplicaciones*, una para cada entorno:
- <https://desaplicaciones.aragon.es/orgavi/test.jsp>
- <https://preaplicaciones.aragon.es/orgavi/test.jsp>
- <https://aplicaciones.aragon.es/orgavi/test.jsp>

En ellas, si la conexión con la base de datos existe, debe devolver el valor **'OK'**.
En caso de fallar la conexión con la base de datos, debe devolver el valor **'KO'**.
## Dependencia de terceros
El acceso al 'Histórico de legislaturas' no se realiza desde ningún enlace o ruta presente en nuestra aplicación, por lo que hacemos constar aquí su url para facilitar su acceso: <https://desaplicaciones.aragon.es/orgavi/HistoricoLegislaturasServlet>
A parte de esto, no existe ningún tipo de dependencia con otros módulos, servicios horizontales ni plataformas de autenticación.
