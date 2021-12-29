package es.aragon.orgavi.util;

import java.io.InputStream;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;

import es.aragon.orgavi.servlet.IndexServlet;

/**
 * Clase que permite gestionar ciertas propiedades de la aplicación, como recuperar la versión de la misma o indicar a librerías la ruta de su fichero de configuración
 * @author Daniel Marcos
 *
 */
public class Propiedades {
    private static final Logger LOGGER = LogManager.getLogger(Propiedades.class.getName());

    /**
     * Constructor privado para evitar ser instanciada
     */
    private Propiedades() {
        throw new IllegalStateException("Utility class");
      }
    /**
     * Obtenemos la versión de la aplicación a partir del fichero app.properties del entorno correspondiente.
     * Primeramente obtenemos el entorno actual, para saber en el directorio de qué entorno buscar el fichero app.properties
     * @return Duvuelve la versión extraída del fichero app.properties del entorno actual
     */
	public static String getAppVersion() {
		String url_entorno = "/orgavi/app.properties";
		Propiedades.setDefaultUrlPropertiesLog();
		String version = "";

		try{		
			Properties prop = new Properties();
			InputStream in = IndexServlet.class.getResourceAsStream(url_entorno);
			prop.load(in);
			version = prop.getProperty("Implementation-Version");
			in.close();
        } catch (Exception ex) {
			LOGGER.error(ex);
        }
		return version;
	}

	/**
	 * Esta función permite recuperar el JNDI Name del fichero de configuración OrgaviResources.properties del entorno en que se encuentr (des, pre, pro)
	 * @return String con el JNDI Name extraído del fichero de propiedades OrgaviResources.properties de uno de los tres entornos
	 */
	public static String getEntornoConexion() {
		String url_entorno = "/orgavi/app.properties";
		Propiedades.setDefaultUrlPropertiesLog();
		String entorno = "";

		try{		
			Properties prop = new Properties();
			InputStream in = IndexServlet.class.getResourceAsStream(url_entorno);
			prop.load(in);
			entorno = prop.getProperty("jdbc_DataSourceJNDIName");
			in.close();
        } catch (Exception ex) {
			LOGGER.error(ex);
        }
		return entorno;
	}
	
	/**
	 * Especificamos al log dónde debe buscar el fichero de configuración log4j2.properties
	 * dado que no se encuentra en su classpath por defecto: src/main/java
	 */
	public static void setDefaultUrlPropertiesLog() {
		Configurator.initialize("configurationFile", "/orgavi/log4j2.properties");

	}
}
