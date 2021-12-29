package es.aragon.orgavi.bl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import es.aragon.orgavi.dal.Legislatura;
import es.aragon.orgavi.util.Conexion;
import es.aragon.orgavi.util.Propiedades;

/**
 * Esta clase contiene los métodos de obtención de información de la Base de Datos relativa a las Legislaturas (tabla ORG_LEGISLATURA)
 * @author Daniel Marcos
 *
 */
public class LegislaturaModel {
	private Connection conexion;
    private static final Logger LOGGER = LogManager.getLogger(LegislaturaModel.class.getName());
    private static final String VISIBLE = "VISIBLE";
    private static final String OBSERVACIONES = "OBSERVACIONES";
    private static final String NOMBRE = "NOMBRE";
    private static final String ID_LEGISLATURA = "ID_LEGISLATURA";
    private static final String FECHA_INI = "FECHA_INI";
    private static final String FECHA_FIN = "FECHA_FIN";
	
	/**
	 * Devuelve List con legislaturas ordenadas de más reciente a más antigua
	 * @return List con todas las legislaturas ordenadas de más reciente a más antigua
	 */
	public List<Legislatura> getLegislaturasDesc(){
		Propiedades.setDefaultUrlPropertiesLog();
		Statement statement = null;
		ResultSet result = null;
		List<Legislatura> listaLegislaturas = new ArrayList<>();
		String query = "SELECT * FROM ORG_LEGISLATURA WHERE FECHA_INI IS NOT NULL AND FECHA_FIN IS NOT NULL ORDER BY FECHA_INI DESC";

		try {
			conexion = obtenerConexion();
			LOGGER.debug("Conexión establecida con éxito desde es.aragon.legislaturas/getLegislaturasDesc");
			statement = conexion.createStatement();
			result = statement.executeQuery(query);
			while (result.next()) {
				Legislatura legislatura = new Legislatura(result.getInt(ID_LEGISLATURA), result.getString(NOMBRE), result.getDate(FECHA_INI), result.getDate(FECHA_FIN), result.getInt(VISIBLE), result.getString(OBSERVACIONES));
				listaLegislaturas.add(legislatura);
			}
		}catch (Exception e) {
			LOGGER.error(e);
		}finally {
			Conexion.cierraConsultas(result, statement, conexion);
		}
		
		return listaLegislaturas;
	}
	
	/**
	 * Retorna la legislatura actual
	 * Consideramos legislatura actual la última legislatura creada.
	 * La recuperamos entonces obteniendo la fecha de inicio más reciente (por si no tuviera la de final asignada)
	 * @return Objeto Legislatura con la legislatura actual
	 */
	public Legislatura getLegislaturaActual() {
		Propiedades.setDefaultUrlPropertiesLog();
		Statement statement = null;
		Legislatura legislaturaActual = null;
		ResultSet result = null;

		String query = "SELECT * FROM ORG_LEGISLATURA WHERE FECHA_INI = (SELECT MAX(FECHA_INI) FROM ORG_LEGISLATURA)"; //Obtenelos la legislatura actual a partir de la máxima fecha de inicio de las existentes. Devuelve la futura ID 26, no la actual ID 25.
		//Consulta para calcular la id de la legislatura actual modificada tras que añadieran fecha de inicio a la legislatura futura con id 26. //Ahora obtenemos la legislatura actual a partir de la mínima fecha de inicio entre las que tienen fecha_fin nula.
		//String query = "SELECT * FROM ORG_LEGISLATURA WHERE FECHA_INI = (SELECT MIN(FECHA_INI) FROM ORG_LEGISLATURA WHERE FECHA_FIN IS NULL)";
		try {
			conexion = obtenerConexion();
			LOGGER.debug("Conexión establecida con éxito desde es.aragon.legislaturas/LegislaturaModel/getLegislaturaActual");
			statement = conexion.createStatement();
			result = statement.executeQuery(query);
			while (result.next()) {
				Legislatura legislatura = new Legislatura(result.getInt(ID_LEGISLATURA), result.getString(NOMBRE), result.getDate(FECHA_INI), result.getDate(FECHA_FIN), result.getInt(VISIBLE), result.getString(OBSERVACIONES));
				legislaturaActual = legislatura;
			}
		}catch (Exception e) {
			LOGGER.error(e);
		}finally {
			Conexion.cierraConsultas(result, statement, conexion);
		}
		return legislaturaActual;
	}
	
	/**
	 * Retorna la ID de la legislatura actual
	 * Por legislatura actaul entendemos la última generada. Por lo tanto la de fecha de inicio más alta, por si no tuviera asignada fecha de fin.
	 * @return Entero con la id de la legislatura actual
	 */
	public int getIdLegislaturaActual() {
		Propiedades.setDefaultUrlPropertiesLog();
		Statement statement = null;
		ResultSet result = null;
		int idLegislaturaActual = 0;
		String query = "SELECT ID_LEGISLATURA FROM ORG_LEGISLATURA WHERE FECHA_INI = (SELECT MAX(FECHA_INI) FROM ORG_LEGISLATURA)";//Legislatura futura ID26, no presente ID 25
		//String query = "SELECT ID_LEGISLATURA FROM ORG_LEGISLATURA WHERE FECHA_INI = (SELECT MIN(FECHA_INI) FROM ORG_LEGISLATURA WHERE FECHA_FIN IS NULL)";
		try {
			conexion = obtenerConexion();
			LOGGER.debug("Conexión establecida con éxito desde es.aragon.legislaturas/LegislaturaModel/getIdLegislaturaActual");
			statement = conexion.createStatement();
			result = statement.executeQuery(query);
			while (result.next()) {
				idLegislaturaActual = result.getInt(ID_LEGISLATURA);
			}
		}catch (Exception e) {
			LOGGER.error(e);
		}finally {
			Conexion.cierraConsultas(result, statement, conexion);
		}
		return idLegislaturaActual;
	}
	
	/**
	 * Retorna la ID de la legislatura histórica más reciente
	 * La legislatura histórica más reciente es la segunda más reciente (dado que la más reciente es la actual).
	 * @return Entero con la id de la legislatura histórica más reciente
	 */
	public int getIdLegislaturaHistoricaReciente() {
		Propiedades.setDefaultUrlPropertiesLog();
		Statement statement = null;
		ResultSet result = null;
		int idLegislaturaHistoricaReciente = 0;
		//String query = "SELECT ID_LEGISLATURA FROM ORG_LEGISLATURA WHERE FECHA_FIN = (SELECT MAX(FECHA_FIN) FROM ORG_LEGISLATURA WHERE FECHA_FIN IS NOT NULL)"; //Replanteamos consulta porque ahora la legislatura actual tiene fecha fin también
		String query = "SELECT ID_LEGISLATURA FROM ORG_LEGISLATURA WHERE FECHA_INI = (SELECT MAX(FECHA_INI) FROM ORG_LEGISLATURA WHERE FECHA_INI != (SELECT MAX(FECHA_INI) FROM ORG_LEGISLATURA))";
		try {
			conexion = obtenerConexion();
			LOGGER.debug("Conexión establecida con éxito desde es.aragon.legislaturas/LegislaturaModel/getIdLegislaturaActual");
			statement = conexion.createStatement();
			result = statement.executeQuery(query);
			while (result.next()) {
				idLegislaturaHistoricaReciente = result.getInt(ID_LEGISLATURA);
			}
		}catch (Exception e) {
			LOGGER.error(e);
		}finally {
			Conexion.cierraConsultas(result, statement, conexion);
		}
		return idLegislaturaHistoricaReciente;
	}
	
	/**
	 * Retorna una legislatura a partir de su ID
	 * @param id Id de la legislatura de la que se desea obtener información
	 * @return Objeto Legislatura con la información de la legislatura cuya id se pasa por parámetro
	 */
	public Legislatura getById(int id) {
		Propiedades.setDefaultUrlPropertiesLog();
		PreparedStatement statement = null;
		Legislatura legislaturaSeleccionada = null;
		ResultSet result = null;
		String query = "SELECT * FROM ORG_LEGISLATURA WHERE ID_LEGISLATURA = ?";
		try {
			conexion = obtenerConexion();
			LOGGER.debug("Conexi�n establecida con éxito desde es.aragon.legislaturas/LegislaturaModel/getById");
			statement = conexion.prepareStatement(query);
			statement.setInt(1, id);
			result = statement.executeQuery();
			while (result.next()) {
				Legislatura legislatura = new Legislatura(result.getInt(ID_LEGISLATURA), result.getString(NOMBRE), result.getDate(FECHA_INI), result.getDate(FECHA_FIN), result.getInt(VISIBLE), result.getString(OBSERVACIONES));
				legislaturaSeleccionada = legislatura;
			}
		}catch (Exception e) {
			LOGGER.error(e);
		}finally {
			Conexion.cierraConsultasPreparadas(result, statement, conexion);
		}
		return legislaturaSeleccionada;
	}
	
	/**
	 * Obtiene la fecha de inicio de la legislatura actual
	 * @return Objeto Date con la fecha de la legislatura actual
	 */
	public Date getFechaIniLegislaturaActual() {
		Propiedades.setDefaultUrlPropertiesLog();
		Statement statement = null;
		String query = "SELECT MAX(FECHA_INI) AS FECHA_INI FROM ORG_LEGISLATURA"; //Entendemos por legislatura actual la de fecha de inicio más reciente.De esta forma cogemos la futura (ID 26), con la que desean trabajar y no la presente (ID 25)
		//String query = "SELECT FECHA_INI FROM ORG_LEGISLATURA WHERE FECHA_INI = (SELECT MIN(FECHA_INI) FROM ORG_LEGISLATURA WHERE FECHA_FIN IS NULL)";
		Date fecha_ini = null;
		ResultSet result = null;
		try {
			conexion = obtenerConexion();
			LOGGER.debug("Conexión establecida con éxito desde es.aragon.legislaturas/LegislaturaModel/getFechaIniLegislaturaActual");
			statement = conexion.createStatement();
			result = statement.executeQuery(query);
			while (result.next()) {
				fecha_ini = result.getDate(FECHA_INI);		
			}
		}catch (Exception e) {
			LOGGER.error(e);
		}finally {
			Conexion.cierraConsultas(result, statement, conexion);
		}
		return fecha_ini;
	}
	
	/**
	 * Obtiene una conexi�n del pool de conexiones
	 * @return Conexión
	 * @throws SQLException Excepción de SQL
	 */
	private Connection obtenerConexion() throws SQLException {
		return Conexion.getConexion();
	}
}
