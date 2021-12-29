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

import es.aragon.orgavi.dal.Cargo;
import es.aragon.orgavi.util.Conexion;
import es.aragon.orgavi.util.Propiedades;

/**
 * Esta clase contiene los métodos de obtenci�n de información de la Base de Datos relativa a los Cargos (tabla ORG_CARGO)
 * @author Daniel Marcos
 *
 */
public class CargoModel {
	private Connection conexion;
	private static final Logger LOGGER = LogManager.getLogger(CargoModel.class.getName());
    private static final String ID_DE_ENTIDAD = "ID_ENTIDAD";

	/**
	 * Recupera una lista de cargos a partir de la id de la legislatura y el nivel de la entidad
	 * También obtiene datos de la entidad a la que pertenece cada cargo
	 * @param legislatura_id id de la legislatura
	 * @param entidad_nivel nivel de la entidad
	 * @return
	 */
	public List<Cargo> getCargosEntidadesByLvl(int legislatura_id, int entidad_nivel) {
		Propiedades.setDefaultUrlPropertiesLog();
		PreparedStatement statement = null;
		List<Cargo> lista = new ArrayList<>();
		String query = "SELECT ORG_ENTIDAD.NOMBRE AS NOMBRE_ENTIDAD, ORG_ENTIDAD.ID_ENTIDAD AS ID_ENTIDAD, ORG_CARGO.NOMBRE AS NOMBRE_CARGO, ORG_CARGO.FECHA_INI AS FECHA_INI, ORG_CARGO.FECHA_FIN AS FECHA_FIN, ORG_CARGO.PUBLICADO AS PUBLICADO FROM ORG_CARGO INNER JOIN ORG_ENTIDAD ON ORG_CARGO.ID_ENTIDAD = ORG_ENTIDAD.ID_ENTIDAD WHERE ORG_ENTIDAD.ID_LEGISLATURA = ? AND ORG_ENTIDAD.NIVEL = ? AND ORG_CARGO.ORDEN = 1 ORDER BY ORG_ENTIDAD.ORDEN";
		ResultSet result = null;
		try {
			conexion = obtenerConexion();            
			LOGGER.debug("Conexión establecida con éxito desde es.aragon.cargos/CargoModel/getCargosEntidadesByLvl");
			statement = conexion.prepareStatement(query);
			statement.setInt(1, legislatura_id);
			statement.setInt(2, entidad_nivel);
			result = statement.executeQuery();

			while (result.next()) {
				Cargo temp = new Cargo();
				int entidad_id = result.getInt(ID_DE_ENTIDAD);
				String entidad_nombre = result.getString("NOMBRE_ENTIDAD");
				temp.setEntidad_id(entidad_id);
				temp.setEntidad_nombre(entidad_nombre);
				if(result.getInt("PUBLICADO")==1) { //Sólo si publicado es 1 registramos los datos del cargo
					String nombre = result.getString("NOMBRE_CARGO");
					Date fecha_ini = result.getDate("FECHA_INI");
					Date fecha_fin = result.getDate("FECHA_FIN");
					temp.setNombre(nombre);
					temp.setFecha_ini(fecha_ini);
					temp.setFecha_fin(fecha_fin);
				}
				lista.add(temp);					
			}
		} catch (SQLException e) {
			LOGGER.error(e);
		}finally {
			Conexion.cierraConsultasPreparadas(result, statement, conexion);
		}
		return lista;
	}
	
	/**
	 * Recupera todos los empleados que trabajan en una entidad que es hija de una entidad padre pasada por parámetro
	 * Una entidad padre podría tener, por ejemplo, tener 3 entidades hijas, este método recupera los empleados de las 3 hijas y la id de cada una de las 3 hijas
	 * @param id_entidad_padre id de la entidad padre
	 * @return List de entidades hija que tienen por entidad padre aquella con id igual al id pasado por parámetro
	 */
	public List<Cargo> getCargosByIDEntidadPadre(int id_entidad_padre) {
		Propiedades.setDefaultUrlPropertiesLog();
		PreparedStatement statement = null;
		List<Cargo> lista = new ArrayList<>();
		String query = "SELECT ORG_CARGO.ID_CARGO AS ID_CARGO, ORG_CARGO.NOMBRE AS NOMBRE, ORG_CARGO.CARGO AS CARGO, ORG_CARGO.ID_ENTIDAD AS ID_ENTIDAD FROM ORG_CARGO INNER JOIN ORG_ENTIDAD ON ORG_CARGO.ID_ENTIDAD = ORG_ENTIDAD.ID_ENTIDAD WHERE ORG_ENTIDAD.ID_ENTIDAD_PADRE = ? AND ORG_ENTIDAD.NIVEL < 5 AND ORG_CARGO.PUBLICADO = 1 ORDER BY ORG_ENTIDAD.NIVEL, ORG_ENTIDAD.NIVEL, ORG_CARGO.ORDEN";
		ResultSet result = null;
		try {
			conexion = obtenerConexion();
			LOGGER.debug("Conexión establecida con éxito desde es.aragon.cargos/CargoModel/getCargosByIDEntidadPadre");
			statement = conexion.prepareStatement(query);
			statement.setInt(1, id_entidad_padre);
			result = statement.executeQuery();
			while (result.next()) {
				Cargo temp = new Cargo();
				int id_cargo = result.getInt("ID_CARGO");
				String nombre = result.getString("NOMBRE");
				String cargo = result.getString("CARGO");
				int id_entidad = result.getInt(ID_DE_ENTIDAD);
				int entidad_id = result.getInt(ID_DE_ENTIDAD);
				temp.setId_cargo(id_cargo);
				temp.setNombre(nombre);
				temp.setCargo(cargo);
				temp.setId_entidad(id_entidad);
				temp.setEntidad_id(entidad_id);
				lista.add(temp);
			}
		} catch (SQLException e) {
			LOGGER.error(e);
		}finally {
			Conexion.cierraConsultasPreparadas(result, statement, conexion);
		}
		return lista;
	}
	/**
	 * Devuelve los cargos que conforman una entidad de gabinete (Tipo G) de una entidad padre cuyo ID proporcionamos por parámetro ordenados por orden ASC
	 * @param id_entidad_padre ID de la entidad padre cuya entidad gabinete queremos obtener
	 * @return List de objetos Cargo que constan en la entidad de tipo G que tiene como entidad padre la id proporcionada por parámetro
	 */
	public List<Cargo> getCargosGabinete(int id_entidad_padre) {
		Propiedades.setDefaultUrlPropertiesLog();
		PreparedStatement statement = null;
		List<Cargo> lista = new ArrayList<>();
		String query = "SELECT ORG_CARGO.CARGO AS CARGO, ORG_CARGO.NOMBRE AS NOMBRE, ORG_CARGO.DIRECCION AS DIRECCION, ORG_CARGO.CP AS CP, ORG_CARGO.LOCALIDAD AS LOCALIDAD, ORG_CARGO.PROVINCIA AS PROVINCIA, ORG_CARGO.EMAIL AS EMAIL, ORG_CARGO.TELEFONO AS TELEFONO FROM ORG_CARGO INNER JOIN ORG_ENTIDAD ON ORG_CARGO.ID_ENTIDAD = ORG_ENTIDAD.ID_ENTIDAD WHERE ORG_ENTIDAD.TIPO = 'G' AND ORG_ENTIDAD.ID_ENTIDAD_PADRE = ? AND ORG_ENTIDAD.NIVEL < 5 AND ORG_CARGO.PUBLICADO = 1 ORDER BY ORG_CARGO.ORDEN ASC";
		ResultSet result = null;
		try {
			conexion = obtenerConexion();
			LOGGER.debug("Conexión establecida con éxito desde es.aragon.cargos/CargoModel/getCargosByIDEntidadPadre");
			statement = conexion.prepareStatement(query);
			statement.setInt(1, id_entidad_padre);
			result = statement.executeQuery();
				while (result.next()) {
				Cargo temp = new Cargo();
				temp.setNombre(result.getString("NOMBRE"));
				temp.setCargo(result.getString("CARGO"));
				temp.setDireccion(result.getString("DIRECCION"));
				temp.setCp(result.getString("CP"));
				temp.setLocalidad(result.getString("LOCALIDAD"));
				temp.setProvincia(result.getString("PROVINCIA"));
				temp.setTelefono(result.getString("TELEFONO"));
				temp.setEmail(result.getString("EMAIL"));
				
				lista.add(temp);
				}			
		} catch (SQLException e) {
			LOGGER.error(e);
		}finally {
			Conexion.cierraConsultasPreparadas(result, statement, conexion);
		}
		return lista;
	}
	/**
	 * Devuelve los cargos no pertenecientes a una entidad gabinete que conforman una entidad de gabinete de una entidad padre cuyo ID proporcionamos por parámetro ordenados por orden ASC
	 * @param id_entidad_padre ID de la entidad padre cuyos cargos que conforman el gabinete queremos obtener
	 * @return List de objetos Cargo que conforman el gabinete de la entidad cuya id proporcionamos por parámetro //4044
	 */
	public List<Cargo> getCargosGabineteAlt(int id_entidad_padre) {
		Propiedades.setDefaultUrlPropertiesLog();
		PreparedStatement statement = null;
		List<Cargo> lista = new ArrayList<>();
		String query = "SELECT NOMBRE, CARGO, DIRECCION, CP, LOCALIDAD, PROVINCIA, TELEFONO, EMAIL FROM ORG_CARGO WHERE ID_ENTIDAD = ? AND ORDEN != 1 AND PUBLICADO = 1 ORDER BY ORDEN ASC";
		//SELECT NOMBRE, CARGO, DIRECCION, CP, LOCALIDAD, PROVINCIA, TELEFONO, EMAIL FROM ORG_CARGO WHERE ID_ENTIDAD = 4041 AND ORDEN != 1 ORDER BY ORDEN ASC; 4037
		ResultSet result = null;
		try {
			conexion = obtenerConexion();
			LOGGER.debug("Conexión establecida con éxito desde es.aragon.cargos/CargoModel/getCargosGabineteAlt");
			statement = conexion.prepareStatement(query);
			statement.setInt(1, id_entidad_padre);
			result = statement.executeQuery();
				while (result.next()) {
				Cargo temp = new Cargo();
				temp.setNombre(result.getString("NOMBRE"));
				temp.setCargo(result.getString("CARGO"));
				temp.setDireccion(result.getString("DIRECCION"));
				temp.setCp(result.getString("CP"));
				temp.setLocalidad(result.getString("LOCALIDAD"));
				temp.setProvincia(result.getString("PROVINCIA"));
				temp.setTelefono(result.getString("TELEFONO"));
				temp.setEmail(result.getString("EMAIL"));
				
				lista.add(temp);
				}			
		} catch (SQLException e) {
			LOGGER.error(e);
		}finally {
			Conexion.cierraConsultasPreparadas(result, statement, conexion);
		}
		return lista;
	}
	
	/**
	 * Esta función permite reconocer si una entidad posee gabinete en base a sus cargos y no mediante una entidad gabinete hija suya.
	 * La condición es que sea entidad de nivel 1 o 2 (no hace falta explicitarlo en la consulta) y que posea más de un cargo, dado que el de nivel 1 no cuenta que es el responsable y el no forma parte del gabinete a mostrar
	 * @param idPadre ID de la entidad de la que queremos conocer si posee un gabinete de cargos dependientes
	 * @return Retorna un booleano. True es si posee un gabinete de cargos dependientes. False si no
	 */
	public boolean hasGabineteAlt(int idPadre) {
		Propiedades.setDefaultUrlPropertiesLog();
		PreparedStatement statement = null;
		ResultSet result = null;
		boolean hasGabinete = false;
		String query = "SELECT COUNT(ID_CARGO) AS TOTAL FROM ORG_CARGO WHERE ID_ENTIDAD = ?";
		try {
			conexion = obtenerConexion();		
			statement = conexion.prepareStatement(query);
			LOGGER.debug("Conexión establecida desde es.aragon.entidades/CargoModel/hasGabineteAlt");
			statement.setInt(1, idPadre);
			result = statement.executeQuery();
			while(result.next()) {
				if(result.getInt("TOTAL")>1) {
					hasGabinete = true;
				}
			}
		}catch(Exception e) {
			LOGGER.error(e);
		}finally {
			Conexion.cierraConsultasPreparadas(result, statement, conexion);
		}
		return hasGabinete;
	}
	
	/**
	 * Esta función recupera todos los responsables históricos de entidades de nivel 1 y 2 para el histórico de legislturas
	 * Filtra, por lo tanto, por cargos dependientes de entidades de nivel 1 y 2
	 * de una legislatura concreta cuya id se pasa por parámetro
	 * que tengan el publicado en SI
	 * Podría entrar en conflicto con gabinete u otros miebros, pero al sólo mostrarse los históricos se ha pactado con el cliente que todos los
	 * dependientes de un departamento histórico (no legislatura actual) que no sean responsables pasarán a publicado "no".
	 * Para no interferir con consultas de legislaturas actuales tiene esta función propia.
	 * @param id_legislatura Id de la legislatura a la que pertenecen las entidades cuyos cargos queremos obtener
	 * @param nivel Usaremos nivel 1 para recuperar cargos dependientes de entidades de nivel 1 y nivel 2 para cargos dependientes de entidades de nivel 2
	 * @return List de Cargos con todos los responsables publicados depentiendes de una entidad del nivel pasado por parámetro
	 */
	public List<Cargo> getHistoricalResponsables(int id_legislatura, int nivel) {
		Propiedades.setDefaultUrlPropertiesLog();
		PreparedStatement statement = null;
		List<Cargo> lista = new ArrayList<>();
		String query = "SELECT ORG_CARGO.NOMBRE AS NOMBRE, ORG_CARGO.ID_ENTIDAD AS ID_ENTIDAD FROM ORG_CARGO INNER JOIN ORG_ENTIDAD ON ORG_CARGO.ID_ENTIDAD = ORG_ENTIDAD.ID_ENTIDAD WHERE ORG_ENTIDAD.ID_LEGISLATURA = ? AND ORG_ENTIDAD.NIVEL = ? AND ORG_CARGO.PUBLICADO = 1 ORDER BY ORG_CARGO.ORDEN ASC";
		ResultSet result = null;
		try {
			conexion = obtenerConexion();
			LOGGER.debug("Conexión establecida con éxito desde es.aragon.cargos/CargoModel/getHistoricalResponsables");
			statement = conexion.prepareStatement(query);
			statement.setInt(1, id_legislatura);
			statement.setInt(2, nivel);
			result = statement.executeQuery();
				while (result.next()) {
				Cargo temp = new Cargo();
				temp.setNombre(result.getString("NOMBRE"));
				temp.setId_entidad(result.getInt("ID_ENTIDAD"));				
				lista.add(temp);
				}			
		} catch (SQLException e) {
			LOGGER.error(e);
		}finally {
			Conexion.cierraConsultasPreparadas(result, statement, conexion);
		}
		return lista;
	}
	
	/**
	 * Obtener una conexión mediante el pool de conexiones
	 * @return Devuelve la conexi�n
	 * @throws SQLException Arroja excepci�n de SQL
	 */
	private Connection obtenerConexion() throws SQLException {
		return Conexion.getConexion();
	}
}
