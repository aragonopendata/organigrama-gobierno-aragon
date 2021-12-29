# Manual de explotación

## Procedimientos de operación específicos
Una vez subida la versión de la aplicación al repositorio no se requiere de ningún tipo de acción específica.
## Requisitos específicos de copias de seguridad y procedimientos de recuperación
Al no existir dependencias de caché o autenticaciones no se demanda ningún requisito específico a la hora de realizar copias de seguridad o llevar a cabo procedimientos de recuperación.
La periodicidad a la hora de realizar copias de seguridad tampoco se aplica al estar presente el código en el repositorio, ya que la aplicación no realiza cambio alguno en el código.
## Problemáticas conocidas y propuestas de solución
1. Caso de no existir conexión con la base de datos, las vistas mostrarán su estructura básica pero faltas de contenido. Propuesta de solución: comprobación de la conexión entre la aplicación y la base de datos acudiendo a la url de monitorización de la aplicación en función del entorno que nos encontremos:
- <https://desaplicaciones.aragon.es/orgavi/test.jsp> para desarrollo
- <https://preaplicaciones.aragon.es/orgavi/test.jsp> para preproducción
- <https://aplicaciones.aragon.es/orgavi/test.jsp> para producción

Si nos devuelve el valor *OK* la conexión entre la aplicación y la base de datos es correcta.
Si nos devuelve el valor *KO* existe un problema de conexión entre la aplicación y la base de datos y esta debería tratar de restablecerse para que la aplicación pudiera funcionar correctamente. El problema puede radicar en los parámetros de configuración de la conexión, para lo que recomendamos revisar el fichero `OrgaviResources.properties` del entorno correspondiente:
- `orgavi/src/conf/sata-sources/des/OrgaviResources.properties` para el entorno de desarrollo
- `orgavi/src/conf/sata-sources/pre/OrgaviResources.properties` para el entorno de preproducción
- `orgavi/src/conf/sata-sources/pro/OrgaviResources.properties` para el entorno de producción

Deberemos comprobar tamibén que es correcta la ruta al fichero de configuración del entorno deseado en la propiedad `Fichero-Configuracion` del fichero de configuración `orgavi/src/conf/sata-sources/des/app.properties`
2. Caso de almacenarse registros o valores no adecuados en la base de datos, las consultas podrían tenerlos en cuenta y presentar información equívoca en las vistas. Por ejemplo la introducción de fechas erróneas en los valores de inicio y final de una legislatura podría afectar al orden de aparición de las mismas. O la ausencia del campo 'tipo' en una entidad podría ocasionar que dicho departamento no se mostrara en la categoría correspondiente. Como solución se propone cotejar la información de la base de datos cuando se detecte un error de este tipo para verificar que la información introducida es correcta, y modificarla en caso de no serlo.

3. Caso de realizarse modificaciones en la estructura de la base de datos, las consultas podrían arrojar diversos errores, por ejemplo de *invalid column name* si se hubieran modificado los nombres de los campos de una tabla. Como medida de prevención proponemos respetar la nomenclatura original de las tablas y campos de la base de datos y como propuesta de solución realizar una modificación en las consultas pertinentes en caso de alterarse dicha estructura.
## Relación de comprobaciones en `test.jsp`
- Conexión con la base de datos
Retorna: **OK**. La conexión entre aplicación y base de datos es correcta
Retorna: **KO**. La conexión entre la aplicación y la base de datos NO es correcta. Debe restablecerse para su correcto funcionamiento.
Causa de error: Valores erróneos en el fichero de configuración.
Procedimiento para subsanarlo: Acudir al fichero de configuración de los parámetros de conexión, comprobar si son correctos y modificarlos en caso de no serlo:
  - `orgavi/src/conf/sata-sources/des/OrgaviResources.properties` para el entorno de desarrollo
  - `orgavi/src/conf/sata-sources/pre/OrgaviResources.properties` para el entorno de preproducción
  - `orgavi/src/conf/sata-sources/pro/OrgaviResources.properties` para el entorno de producción