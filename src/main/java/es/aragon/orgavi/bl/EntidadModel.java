package es.aragon.orgavi.bl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import es.aragon.orgavi.dal.Entidad;
import es.aragon.orgavi.util.Conexion;
import es.aragon.orgavi.util.Propiedades;

/**
 * Esta clase contiene los métodos de obtención de información de la Base de Datos relativa a las Entidades (tabla ORG_ENTIDAD)
 * @author Daniel Marcos
 *
 */
public class EntidadModel {//prueba
	private Connection conexion;
    private static final Logger LOGGER = LogManager.getLogger(EntidadModel.class.getName());
    private static final String TELEFONO = "TELEFONO";  
    private static final String RESPONSABLE_NOMBRE = "RESPONSABLE_NOMBRE";  
    private static final String RESPONSABLE_CARGO = "RESPONSABLE_CARGO";
    private static final String PROVINCIA = "PROVINCIA";
    private static final String PAGINA_WEB_TITULO = "PAGINA_WEB_TITULO";
    private static final String PAGINA_WEB = "PAGINA_WEB";
    private static final String ORDEN = "ORDEN";
    private static final String OBSERVACIONES = "OBSERVACIONES";
    private static final String NOMBRE = "NOMBRE";
    private static final String NIVEL = "NIVEL";
    private static final String LOCALIDAD = "LOCALIDAD";
    private static final String ID_LEGISLATURA = "ID_LEGISLATURA";
    private static final String ID_ENTIDAD_PADRE = "ID_ENTIDAD_PADRE";
    private static final String ID_ENTIDAD = "ID_ENTIDAD";
    private static final String EMAIL = "EMAIL";
    private static final String EDIFICIO = "EDIFICIO";
    private static final String DIRECCION = "DIRECCION";
    private static final String CP = "CP";
    private static final String DEPENDENCIA_DIRECTA = "DEPENDENCIA_DIRECTA";
    private static final String COOR_X = "COOR_X";
    private static final String COOR_Y = "COOR_Y";
    private static final String COD_SIU = "COD_SIU";
 
	/**
	 * Devuelve todas las entidades de un nivel concreto de una legislatura concreta
	 * @param legislatura_id Id de la legislatura a la que pertenecen las entidades que se quieren recuperar
	 * @param entidad_nivel Nivel de las entidades que se desean recuperar
	 * @return List con todas las entidades de un nivel y legislatura pasados por parámetros
	 */
	public List<Entidad> getEntidadesByLvl(int legislatura_id, int entidad_nivel){
		Propiedades.setDefaultUrlPropertiesLog();
		PreparedStatement statement = null;
		List<Entidad> listaEntidades = new ArrayList<>();
		String query = "SELECT * FROM ORG_ENTIDAD WHERE ID_LEGISLATURA = ? AND NIVEL = ? ORDER BY ORDEN";
		ResultSet result = null;
		try {
			conexion = obtenerConexion();		
			statement = conexion.prepareStatement(query);
			LOGGER.debug("Conexión establecida desde es.aragon.entidades/EntidadModel/getEntidadesByLvl");
			statement.setInt(1, legislatura_id);
			statement.setInt(2, entidad_nivel);
			result = statement.executeQuery();
			while(result.next()) {
				Entidad entidad = new Entidad(result.getInt(ID_ENTIDAD), result.getInt(ID_ENTIDAD_PADRE), result.getInt(ID_LEGISLATURA), result.getInt(NIVEL), result.getInt(ORDEN),
						result.getString(NOMBRE), result.getString(OBSERVACIONES), result.getInt(DEPENDENCIA_DIRECTA), result.getString(COD_SIU), result.getString(EDIFICIO), result.getString(DIRECCION), result.getString(CP),
						result.getString(LOCALIDAD), result.getString(PROVINCIA), result.getString(TELEFONO), result.getDouble(COOR_X), result.getDouble(COOR_Y), result.getString(EMAIL), result.getString(PAGINA_WEB), result.getString(PAGINA_WEB_TITULO));
				listaEntidades.add(entidad);
			}
		} catch (Exception e) {
			LOGGER.error(e);
		}finally {
			Conexion.cierraConsultasPreparadas(result, statement, conexion);
		}
		return listaEntidades;
	}
	
		/**
		 * Devuelve el nombre y el ID de las entidades de un nivel concreto de una legislatura concreta.
		 * Utilizado para mostrar los enlaces a los departamentos de nivel 2 en el index.html
		 * @param legislatura_id Id de la legislatura a la que pertenecen las entidades que se quieren recuperar
		 * @param entidad_nivel Nivel de las entidades que se desean recuperar
		 * @return List con las entidades de un nivel y legislatura pasados por parámetros, rellenas sólo con su nombre e ID
		 */
		public List<Entidad> getIdNameEntityByLvl(int legislatura_id, int entidad_nivel){
			Propiedades.setDefaultUrlPropertiesLog();
			PreparedStatement statement = null;
			List<Entidad> listaEntidades = new ArrayList<>();
			String query = "SELECT ID_ENTIDAD, ID_ENTIDAD_PADRE, NOMBRE FROM ORG_ENTIDAD WHERE ID_LEGISLATURA = ? AND NIVEL = ? ORDER BY ORDEN";
			ResultSet result = null;
			try {
				conexion = obtenerConexion();		
				statement = conexion.prepareStatement(query);
				LOGGER.debug("Conexión establecida desde es.aragon.entidades/EntidadModel/getIdNameEntityByLvl");
				statement.setInt(1, legislatura_id);
				statement.setInt(2, entidad_nivel);
				result = statement.executeQuery();
				//mas nuevo
				while(result.next()) {
					int id_entidad = result.getInt(ID_ENTIDAD);
					int id_entidad_padre = result.getInt(ID_ENTIDAD_PADRE);
					String nombre = result.getString(NOMBRE);
					Entidad entidad = new Entidad();
					entidad.setId_entidad(id_entidad);
					entidad.setNombre(nombre);
					entidad.setId_entidad_padre(id_entidad_padre);
					listaEntidades.add(entidad);
				}
			} catch (Exception e) {
				LOGGER.error(e);
			}finally {
				Conexion.cierraConsultasPreparadas(result, statement, conexion);
			}
			return listaEntidades;
		}
	/**
	 * Recupera una entidad a partir de su id
	 * @param entidad_id Id de la entidad que se desea recuperar
	 * @return Objeto Entidad recuperado a partir de su Id
	 */
	public Entidad getEntidadById(int entidad_id) {
		Propiedades.setDefaultUrlPropertiesLog();
		PreparedStatement statement = null;
		ResultSet result = null;
		Entidad entidad = null;
		String query = "SELECT * FROM ORG_ENTIDAD WHERE ID_ENTIDAD = ?";
		try {
			conexion = obtenerConexion();
			statement = conexion.prepareStatement(query);
			LOGGER.debug("Conexión establecida desde es.aragon.entidades/EntidadModel/getEntidadById");
			statement.setInt(1, entidad_id);
			result = statement.executeQuery();

			while(result.next()) {
				entidad = new Entidad(result.getInt(ID_ENTIDAD), result.getInt(ID_ENTIDAD_PADRE), result.getInt(ID_LEGISLATURA), result.getInt(NIVEL), result.getInt(ORDEN),
						result.getString(NOMBRE), result.getString(OBSERVACIONES), result.getInt(DEPENDENCIA_DIRECTA), result.getString(COD_SIU), result.getString(EDIFICIO), result.getString(DIRECCION), result.getString(CP),
						result.getString(LOCALIDAD), result.getString(PROVINCIA), result.getString(TELEFONO), result.getDouble(COOR_X), result.getDouble(COOR_Y), result.getString(EMAIL), result.getString(PAGINA_WEB), result.getString(PAGINA_WEB_TITULO));
			}
		}catch(Exception e) {
			LOGGER.error(e);
		}finally {
			Conexion.cierraConsultasPreparadas(result, statement, conexion);
		}
		return entidad;
	}
	
	/**
	 * Recupera una entidad de nivel 1 o 2 a partir de su id.
	 * También recupera información del cargo a partir de su orden
	 * @param id_departamento Id de la entidad de nivel 2 que se desea recuperar
	 * @param orden_cargo Orden del cargo que pertenece a la entidad que se desea recuperar
	 * @return Objeto Entidad con informaci�n de la entidad recuperada y del cargo responsable de la misma (el de primer orden dentro de ella)
	 */
	public Entidad getDepartamentoById(int id_departamento, int orden_cargo) {
		Propiedades.setDefaultUrlPropertiesLog();
		PreparedStatement statement = null;
		Entidad departamento = null;
		ResultSet result = null;
		String query = "SELECT ORG_ENTIDAD.ID_ENTIDAD AS ID_ENTIDAD, ORG_ENTIDAD.NOMBRE AS NOMBRE, ORG_ENTIDAD.EDIFICIO AS EDIFICIO, ORG_ENTIDAD.DIRECCION AS DIRECCION, ORG_ENTIDAD.CP AS CP, ORG_ENTIDAD.LOCALIDAD AS LOCALIDAD, ORG_ENTIDAD.PROVINCIA AS PROVINCIA, ORG_ENTIDAD.TELEFONO AS TELEFONO, ORG_ENTIDAD.EMAIL AS EMAIL, ORG_CARGO.ID_CARGO AS RESPONSABLE_ID, ORG_CARGO.NOMBRE AS RESPONSABLE_NOMBRE, ORG_CARGO.CARGO AS RESPONSABLE_CARGO, ORG_CARGO.AGENDA AS RESPONSABLE_AGENDA, ORG_CARGO.BIOGRAFIA AS RESPONSABLE_BIOGRAFIA, ORG_CARGO.FOTO_PATH AS RESPONSABLE_FOTO_PATH, ORG_CARGO.FOTO_NOMBRE AS RESPONSABLE_FOTO_NOMBRE, ORG_CARGO.FOTO_CONTENIDO AS RESPONSABLE_FOTO_CONTENIDO, ORG_CARGO.PUBLICADO AS RESPONSABLE_PUBLICADO FROM ORG_ENTIDAD INNER JOIN ORG_CARGO ON ORG_ENTIDAD.ID_ENTIDAD = ORG_CARGO.ID_ENTIDAD WHERE ORG_ENTIDAD.ID_ENTIDAD = ? AND ORG_CARGO.ORDEN = ?";
		try {
			conexion = obtenerConexion();
			statement = conexion.prepareStatement(query);
			LOGGER.debug("Conexión establecida desde es.aragon.entidades/EntidadModel/getDepartamentoById");
			statement.setInt(1, id_departamento);
			statement.setInt(2, orden_cargo);
			result = statement.executeQuery();
			while(result.next()) {
				departamento = new Entidad();
				departamento.setId_entidad(result.getInt(ID_ENTIDAD));
				departamento.setNombre(result.getString(NOMBRE));
				departamento.setEdificio(result.getString(EDIFICIO));
				departamento.setDireccion(result.getString(DIRECCION));
				departamento.setCp(result.getString("CP"));
				departamento.setLocalidad(result.getString(LOCALIDAD));
				departamento.setProvincia(result.getString(PROVINCIA));
				departamento.setTelefono(result.getString(TELEFONO));
				departamento.setEmail(result.getString(EMAIL));
				int publicado = (result.getInt("RESPONSABLE_PUBLICADO")==1)?1:0;
				if( publicado == 1){ // Si publicado == 1
					departamento.setResponsable_id(result.getInt("RESPONSABLE_ID"));
					departamento.setResponsable_nombre(result.getString(RESPONSABLE_NOMBRE));
					departamento.setResponsable_cargo(result.getString(RESPONSABLE_CARGO));
					departamento.setResponsable_foto_path(result.getString("RESPONSABLE_FOTO_PATH"));
					departamento.setResponsable_foto_nombre(result.getString("RESPONSABLE_FOTO_NOMBRE"));
					departamento.setResponsable_agenda(result.getString("RESPONSABLE_AGENDA"));
					departamento.setResponsable_biografia(result.getString("RESPONSABLE_BIOGRAFIA"));
					byte[] imgData = result.getBytes("RESPONSABLE_FOTO_CONTENIDO"); // blob field
					if(imgData != null) {
						String encode = Base64.getEncoder().encodeToString(imgData);
						departamento.setResponsable_foto_contenido(encode);
					}
				}
			}
		}catch(Exception e) {
			LOGGER.error(e);
		}finally {
			Conexion.cierraConsultasPreparadas(result, statement, conexion);
		}
		return departamento;
	}
	
	/**
	 * Retorna la id de la Presidencia del Gobierno de Aragón de la legislatura actual
	 * @return Entero con el id de la Presidencia Actual
	 */
	public int getIdPresidenciaActual() {
		Propiedades.setDefaultUrlPropertiesLog();
	    Statement statement = null;
	    ResultSet result = null;
		int id_presidenciaActual = 0;
		String query = "SELECT ORG_ENTIDAD.ID_ENTIDAD, ORG_ENTIDAD.NOMBRE FROM ORG_ENTIDAD INNER JOIN ORG_LEGISLATURA ON ORG_ENTIDAD.ID_LEGISLATURA = ORG_LEGISLATURA.ID_LEGISLATURA WHERE ORG_LEGISLATURA.FECHA_INI = (SELECT MAX(FECHA_INI) FROM ORG_LEGISLATURA) AND ORG_ENTIDAD.NIVEL = 1 AND ORG_ENTIDAD.ORDEN = 1";
		try {
			conexion = obtenerConexion();
			LOGGER.debug("Conexión establecida desde es.aragon.entidades/EntidadModel/getIdPresidenciaActual");
			statement = conexion.createStatement();
			result = statement.executeQuery(query);
			while (result.next()) {
				id_presidenciaActual = result.getInt(ID_ENTIDAD);
			}
		}catch (Exception e) {
			LOGGER.error(e);
		}finally {
			Conexion.cierraConsultas(result, statement, conexion);
		}
		return id_presidenciaActual;
	}
	
	/**
	 * Retorna la id de la entidad de nivel 3 de mayor orden
	 * @return Entero con el id de la entidad de nivel 3 de mayor orden
	 */
	public int getIdServicioPrincipalActual() {
		Propiedades.setDefaultUrlPropertiesLog();
		Statement statement = null;
		int servicioPrincipalActual = 0;
		ResultSet result = null;
		String query = "SELECT ORG_ENTIDAD.ID_ENTIDAD, ORG_ENTIDAD.NOMBRE FROM ORG_ENTIDAD INNER JOIN ORG_LEGISLATURA ON ORG_ENTIDAD.ID_LEGISLATURA = ORG_LEGISLATURA.ID_LEGISLATURA WHERE ORG_LEGISLATURA.FECHA_INI = (SELECT MAX(FECHA_INI) FROM ORG_LEGISLATURA) AND ORG_ENTIDAD.NIVEL = 3 AND ORG_ENTIDAD.ORDEN = 1";
		try {
			conexion = obtenerConexion();
			LOGGER.debug("Conexión establecida desde es.aragon.entidades/EntidadModel/getIdServicioPrincipalActual");
			statement = conexion.createStatement();
			result = statement.executeQuery(query);
			while (result.next()) {
				servicioPrincipalActual = result.getInt(ID_ENTIDAD);
			}
			conexion.close();
		}catch (Exception e) {
			LOGGER.error(e);
		}finally {
			Conexion.cierraConsultas(result, statement, conexion);
		}
		return servicioPrincipalActual;
	}
	
	/**
	 * Recupera una lista de entidades que son hijas de la entidad que tenga la id pasada por parámetro en la legislatura actual
	 * También aporta información del cargo responsable de la misma.
	 * La info del cargo devolverá sus datos de nombre y cargo si publicado == 1.
	 * Devolverá en su nombre 'Vacante' si no existe cargo para esa entidad
	 * Devolverá en su nombre null si publicado == 0, y en jsp debe realizar un if!=null para pintar su nombre o vacante y saltarlo si publicado == 0.
	 * @param id_entidad_padre Id de la entidad a la que pertenecen las entidades que queremos recuperar
	 * @param id_legislatura Id de la legislatura a la que pertenecen las entidades que queremos recuperar
	 * @return List de entidades que pertenecen a la entidad cuyo id se pasa por parámetro y pertenecen a la legislatura cuya id se pasa pro parámetro
	 */
	public List<Entidad> getEntidadesHijaYResponsable(int id_entidad_padre, int id_legislatura, String tipo){
		Propiedades.setDefaultUrlPropertiesLog();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<Entidad> listaEntidadesHija = new ArrayList<>();
		String query = "";
		if(tipo == "all") {
			query = "SELECT ORG_ENTIDAD.ID_ENTIDAD AS ID_ENTIDAD, ORG_ENTIDAD.NOMBRE AS NOMBRE, ORG_CARGO.NOMBRE AS RESPONSABLE_NOMBRE, ORG_CARGO.CARGO AS RESPONSABLE_CARGO, ORG_CARGO.PUBLICADO AS RESPONSABLE_PUBLICADO FROM ORG_ENTIDAD LEFT JOIN ORG_CARGO ON ORG_ENTIDAD.ID_ENTIDAD = ORG_CARGO.ID_ENTIDAD WHERE ORG_ENTIDAD.ID_ENTIDAD_PADRE = ? AND ORG_ENTIDAD.ID_LEGISLATURA = ? AND ORG_CARGO.ORDEN = 1 ORDER BY ORG_ENTIDAD.NIVEL, ORG_ENTIDAD.ORDEN"; //AND ORG_CARGO.ORDEN=1 puede llegar a convertir el left en inner pero al no haber 'vacantes' en la bbdd no hay problema
		}else{
			query = "SELECT ORG_ENTIDAD.ID_ENTIDAD AS ID_ENTIDAD, ORG_ENTIDAD.NOMBRE AS NOMBRE, ORG_CARGO.NOMBRE AS RESPONSABLE_NOMBRE, ORG_CARGO.CARGO AS RESPONSABLE_CARGO, ORG_CARGO.PUBLICADO AS RESPONSABLE_PUBLICADO FROM ORG_ENTIDAD LEFT JOIN ORG_CARGO ON ORG_ENTIDAD.ID_ENTIDAD = ORG_CARGO.ID_ENTIDAD WHERE ORG_ENTIDAD.ID_ENTIDAD_PADRE = ? AND ORG_ENTIDAD.ID_LEGISLATURA = ? AND ORG_ENTIDAD.TIPO = ? AND ORG_CARGO.ORDEN = 1 ORDER BY ORG_ENTIDAD.NIVEL, ORG_ENTIDAD.ORDEN"; //AND ORG_CARGO.ORDEN=1 puede llegar a convertir el left en inner pero al no haber 'vacantes' en la bbdd no hay problema
		}
		try {
			conexion = obtenerConexion();
			LOGGER.debug("Conexión establecida desde es.aragon.entidades/EntidadModel/getEntidadesHijaYResponsable");
			statement = conexion.prepareStatement(query);
			statement.setInt(1, id_entidad_padre);
			statement.setInt(2, id_legislatura);
			if(tipo!="all") {
				statement.setString(3, tipo);
			}
			result = statement.executeQuery();
			while(result.next()) {
				int id_entidad = result.getInt(ID_ENTIDAD);
				String nombre = result.getString(NOMBRE);
				String responsable_nombre = null;
				String responsable_cargo = null;
				
				//Generamos la entidad
				Entidad aux = new Entidad();
				aux.setId_entidad(id_entidad);
				aux.setNombre(nombre);
				//Y el cargo asociado a la misma:
				//Si existe el cargo, el orden==1
				if(!(result.getString(RESPONSABLE_NOMBRE) == "" || result.getString(RESPONSABLE_NOMBRE) == null) && result.getInt("RESPONSABLE_PUBLICADO") == 1) {
					responsable_nombre = result.getString(RESPONSABLE_NOMBRE);
					responsable_cargo = result.getString(RESPONSABLE_CARGO);
					//Si existe el cargo pero publicado es == 0
				}else if(!(result.getString(RESPONSABLE_NOMBRE) == "" || result.getString(RESPONSABLE_NOMBRE) == null) && result.getInt("RESPONSABLE_PUBLICADO") != 1){
					//Si no, si existe la entidad pero no hay info de ningún cargo..., hay vacante
				}else if(result.getString(NOMBRE) == "" || result.getString(NOMBRE) == null) {
					responsable_nombre = "Vacante";
				}
				aux.setResponsable_nombre(responsable_nombre);
				aux.setResponsable_cargo(responsable_cargo);
				listaEntidadesHija.add(aux);
			}
			conexion.close();

		}catch (Exception e){
			LOGGER.error(e);
		}finally {
			Conexion.cierraConsultasPreparadas(result, statement, conexion);
		}
		return listaEntidadesHija;
	}
	
	/**
	 * Obtiene listado de entidades hijas de la entidad cuya id se pasa por parámetro.
	 * @param id_entidad_superior Id de la entidad padre de las las entidades que queremos recuperar
	 * @return List de Entidades cuya entidad padre se corresponde con el id pasado por parámetro
	 */
	public List<Entidad> getEntidadesHijas(int id_entidad_superior){
		Propiedades.setDefaultUrlPropertiesLog();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<Entidad> listaEntidadesHija = new ArrayList<>();
		String query = "SELECT * FROM ORG_ENTIDAD WHERE ID_ENTIDAD_PADRE = ? AND NIVEL < 5 ORDER BY NIVEL, ORDEN";
		try {
			conexion = obtenerConexion();
			statement = conexion.prepareStatement(query);
			LOGGER.debug("Conexión establecida desde es.aragon.entidades/EntidadModel/getEntidadesHijas");
			statement.setInt(1, id_entidad_superior);
			result = statement.executeQuery();
			while(result.next()) {
				Entidad entidad = new Entidad(result.getInt(ID_ENTIDAD), result.getInt(ID_ENTIDAD_PADRE), result.getInt(ID_LEGISLATURA), result.getInt(NIVEL), result.getInt(ORDEN),
						result.getString(NOMBRE), result.getString(OBSERVACIONES), result.getInt(DEPENDENCIA_DIRECTA), result.getString(COD_SIU), result.getString(EDIFICIO), result.getString(DIRECCION), result.getString(CP),
						result.getString(LOCALIDAD), result.getString(PROVINCIA), result.getString(TELEFONO), result.getDouble(COOR_X), result.getDouble(COOR_Y), result.getString(EMAIL), result.getString(PAGINA_WEB), result.getString(PAGINA_WEB_TITULO));
				listaEntidadesHija.add(entidad);
			}
			conexion.close();

		}catch (Exception e){
			LOGGER.error(e);
		}finally {
			Conexion.cierraConsultasPreparadas(result, statement, conexion);
		}
		return listaEntidadesHija;
	}
	
	/**
	 * Retorna la entidad padre de la entidad cuya id se pasa por parámetro
	 * @param id Id de la entidad hija.
	 * @return Objeto Entidad padre de la entidad cuya id se pasa por parámetro
	 */
	public Entidad getInfoEntidadPadre(int id) {
		Propiedades.setDefaultUrlPropertiesLog();
		PreparedStatement statement = null;
		ResultSet result = null;
		Entidad entidadPadre = new Entidad();
		String query = "SELECT ENTIDAD_PADRE.ID_ENTIDAD AS ID_ENTIDAD, ENTIDAD_PADRE.NOMBRE AS NOMBRE FROM ORG_ENTIDAD ENTIDAD_PADRE INNER JOIN ORG_ENTIDAD ENTIDAD_HIJA ON ENTIDAD_PADRE.ID_ENTIDAD = ENTIDAD_HIJA.ID_ENTIDAD_PADRE WHERE ENTIDAD_HIJA.ID_ENTIDAD = ?";
		try {
			conexion = obtenerConexion();
			statement = conexion.prepareStatement(query);
			LOGGER.debug("Conexión establecida desde es.aragon.entidades/EntidadModel/getDepartamentoById");
			statement.setInt(1, id);
			result = statement.executeQuery();
			while(result.next()) {
				int id_entidad = result.getInt(ID_ENTIDAD);
				String nombre = result.getString(NOMBRE);
				entidadPadre.setId_entidad(id_entidad);
				entidadPadre.setNombre(nombre);
			}
			conexion.close();
		}catch(Exception e) {
			LOGGER.error(e);
		}finally {
			Conexion.cierraConsultasPreparadas(result, statement, conexion);
		}
		return entidadPadre;
	}
	
/**
 * Retorna una lista de entidades y sus responsables del nivel y legislatura que le pasemos por parámetro.
 * Usado para devolver info de la presidencia y vicepresidencia (entidades de lvl 1) y de sus responsables para la página Index.html
 * @param legislatura_id id de la legislatura a la que pertenecen las entidades que se desean consultar
 * @param entidad_nivel Nivel de las entidades que se desean consultar
 * @param orden_cargo Orden del cargo de cada entidad de las que se desea recuperar información. Permite extraer información del responsable de cada entidad
 * @return List de entidades y cargos responsables de cada entidad de un nivel pasado por par�metro
 */
	public List<Entidad> getEntidadesYResponsablesByLvl(int legislatura_id, int entidad_nivel, int orden_cargo){
		Propiedades.setDefaultUrlPropertiesLog();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<Entidad> listaEntidades = new ArrayList<>();
		String query = "SELECT ORG_ENTIDAD.ID_ENTIDAD AS ID_ENTIDAD, ORG_ENTIDAD.NOMBRE AS NOMBRE, ORG_ENTIDAD.EDIFICIO AS EDIFICIO, ORG_ENTIDAD.DIRECCION AS DIRECCION, ORG_ENTIDAD.CP AS CP, ORG_ENTIDAD.LOCALIDAD AS LOCALIDAD, ORG_ENTIDAD.PROVINCIA AS PROVINCIA, ORG_ENTIDAD.TELEFONO AS TELEFONO, ORG_ENTIDAD.EMAIL AS EMAIL, ORG_CARGO.ID_CARGO AS RESPONSABLE_ID, ORG_CARGO.NOMBRE AS RESPONSABLE_NOMBRE, ORG_CARGO.CARGO AS RESPONSABLE_CARGO, ORG_CARGO.AGENDA AS RESPONSABLE_AGENDA, ORG_CARGO.BIOGRAFIA AS RESPONSABLE_BIOGRAFIA, ORG_CARGO.FOTO_PATH AS RESPONSABLE_FOTO_PATH, ORG_CARGO.FOTO_NOMBRE AS RESPONSABLE_FOTO_NOMBRE, ORG_CARGO.FOTO_CONTENIDO AS RESPONSABLE_FOTO_CONTENIDO, ORG_CARGO.PUBLICADO AS RESPONSABLE_PUBLICADO FROM ORG_ENTIDAD INNER JOIN ORG_CARGO ON ORG_ENTIDAD.ID_ENTIDAD = ORG_CARGO.ID_ENTIDAD WHERE ORG_ENTIDAD.ID_LEGISLATURA = ? AND ORG_ENTIDAD.NIVEL = ? AND ORG_CARGO.ORDEN = ? ORDER BY ORG_ENTIDAD.NIVEL";
		try {
			conexion = obtenerConexion();		
			statement = conexion.prepareStatement(query);
			LOGGER.debug("Conexión establecida desde es.aragon.entidades/EntidadModel/getEntidadesByLvl");
			statement.setInt(1, legislatura_id);
			statement.setInt(2, entidad_nivel);
			statement.setInt(3, orden_cargo);
			result = statement.executeQuery();
			while(result.next()) {
				Entidad entidad = new Entidad();
				entidad.setId_entidad(result.getInt(ID_ENTIDAD));
				entidad.setNombre(result.getString(NOMBRE));
				entidad.setEdificio(result.getString(EDIFICIO));
				entidad.setDireccion(result.getString(DIRECCION));
				entidad.setCp(result.getString(CP));
				entidad.setLocalidad(result.getString(LOCALIDAD));
				entidad.setProvincia(result.getString(PROVINCIA));
				entidad.setTelefono(result.getString(TELEFONO));
				entidad.setEmail(result.getString(EMAIL));
				int responsable_publicado = result.getInt("RESPONSABLE_PUBLICADO");
				if(responsable_publicado == 1){
					entidad.setResponsable_id(result.getInt("RESPONSABLE_ID"));
					entidad.setResponsable_nombre(result.getString(RESPONSABLE_NOMBRE));
					entidad.setResponsable_cargo(result.getString(RESPONSABLE_CARGO));
					entidad.setResponsable_foto_path(result.getString("RESPONSABLE_FOTO_PATH"));
					entidad.setResponsable_foto_nombre(result.getString("RESPONSABLE_FOTO_NOMBRE"));
					entidad.setResponsable_agenda(result.getString("RESPONSABLE_AGENDA"));
					entidad.setResponsable_biografia(result.getString("RESPONSABLE_BIOGRAFIA"));
					byte[] imgData = result.getBytes("RESPONSABLE_FOTO_CONTENIDO"); // blob field
					if(imgData != null) {
						String encode = Base64.getEncoder().encodeToString(imgData);
		            	entidad.setResponsable_foto_contenido(encode);
					}
				}
				listaEntidades.add(entidad);
			}
			conexion.close();
		}catch(Exception e) {
			LOGGER.error(e);
		}finally {
			Conexion.cierraConsultasPreparadas(result, statement, conexion);
		}
		return listaEntidades;
	}

	/**
	 * Esta función permite conocer si una entidad cuyo ID se aporta como parámetro posee una entidad hija de tipo G, es decir, un gabinete
	 * @param idPadre ID de la entidad de la que queremos conocer si posee una entidad hija de tipo G o gabinete
	 * @return Retorna un booleano. True es que posee una entidad hija de tipo G, o gabinete, false que no.
	 */
	public boolean hasGabinete(int idPadre) {
		Propiedades.setDefaultUrlPropertiesLog();
		PreparedStatement statement = null;
		ResultSet result = null;
		boolean hasGabinete = false;
		String query = "SELECT ID_ENTIDAD FROM ORG_ENTIDAD WHERE ORG_ENTIDAD.TIPO = 'G' AND ORG_ENTIDAD.ID_ENTIDAD_PADRE = ?";
		try {
			conexion = obtenerConexion();		
			statement = conexion.prepareStatement(query);
			LOGGER.debug("Conexión establecida desde es.aragon.entidades/EntidadModel/hasGabinete");
			statement.setInt(1, idPadre);
			result = statement.executeQuery();
			while(result.next()) {
				if(result.getInt(ID_ENTIDAD)!=0) {
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
	 * Función que permite detectar si la entidad pasada por parámetro es de nivel 1
	 * @param idEntidad Id de la entidad a comprobar si es de nivel 1
	 * @return true si la ID de la entidad pasada por parámetro es de nivel 1, false si no
	 */
	public boolean isLvl1(int idEntidad) {
		Propiedades.setDefaultUrlPropertiesLog();
		PreparedStatement statement = null;
		ResultSet result = null;
		boolean isLvl1 = false;
		String query = "SELECT NIVEL FROM ORG_ENTIDAD WHERE ORG_ENTIDAD.ID_ENTIDAD = ?";
		try {
			conexion = obtenerConexion();		
			statement = conexion.prepareStatement(query);
			LOGGER.debug("Conexión establecida desde es.aragon.entidades/EntidadModel/isLvl1");
			statement.setInt(1, idEntidad);
			result = statement.executeQuery();
			while(result.next()) {
				if(result.getInt(NIVEL)==1) {
					isLvl1 = true;
				}
			}
		}catch(Exception e) {
			LOGGER.error(e);
		}finally {
			Conexion.cierraConsultasPreparadas(result, statement, conexion);
		}
		return isLvl1;
	}
	/**
	 * Obtiene los ID de todas las entidades que poseen una entidad hija de tipo G o gabinete.
	 * @return List de Entidad en que almacenamos el id de las entidades con entidades hija de tipo G o Gabinetes
	 */
	public List<Entidad> getIdEntitiesWithGabiente(){
		Propiedades.setDefaultUrlPropertiesLog();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<Entidad> listaEntidades = new ArrayList<>();
		String query = "SELECT PADRE.ID_ENTIDAD FROM ORG_ENTIDAD PADRE INNER JOIN ORG_ENTIDAD HIJA ON PADRE.ID_ENTIDAD = HIJA.ID_ENTIDAD_PADRE WHERE HIJA.TIPO = 'G'";
		try {
			conexion = obtenerConexion();		
			statement = conexion.prepareStatement(query);
			LOGGER.debug("Conexión establecida desde es.aragon.entidades/EntidadModel/getIdEntitiesWithGabienteByLvl");
			result = statement.executeQuery();
			while(result.next()) {
				Entidad entidad = new Entidad();
				entidad.setId_entidad(result.getInt(ID_ENTIDAD));
				listaEntidades.add(entidad);
			}
		}catch(Exception e) {
			LOGGER.error(e);
		}finally {
			Conexion.cierraConsultasPreparadas(result, statement, conexion);
		}
		return listaEntidades;
	}
	
	/**
	 * Obtiene las ID de las entidad que tienen un gabinete por el número de empleados que dependen de él y no por tener una entidad hija de tipo 'G'
	 * @return List de Entidad en que almacenamos el id de las entidades con gabinete debido a número de empleados y no a entidad hija de tipo 'G'
	 */
	public List<Entidad> getIdEntitiesWithGabienteAlt(int idLegislatura){
		Propiedades.setDefaultUrlPropertiesLog();
		PreparedStatement statement = null;
		ResultSet result = null;
		List<Entidad> listaEntidades = new ArrayList<>();
		String query = "SELECT ORG_ENTIDAD.ID_ENTIDAD AS ID_ENTIDAD, COUNT(*) FROM ORG_ENTIDAD, ORG_CARGO WHERE ORG_ENTIDAD.ID_ENTIDAD = ORG_CARGO.ID_ENTIDAD AND ORG_ENTIDAD.NIVEL <3 AND ORG_ENTIDAD.ID_LEGISLATURA = ? GROUP BY ORG_ENTIDAD.ID_ENTIDAD HAVING COUNT(*)>1";
		try {
			conexion = obtenerConexion();		
			statement = conexion.prepareStatement(query);
			LOGGER.debug("Conexión establecida desde es.aragon.entidades/EntidadModel/getIdEntitiesWithGabienteAlt");
			statement.setInt(1, idLegislatura);
			result = statement.executeQuery();
			while(result.next()) {
				Entidad entidad = new Entidad();
				entidad.setId_entidad(result.getInt(ID_ENTIDAD));
				listaEntidades.add(entidad);
			}
		}catch(Exception e) {
			LOGGER.error(e);
		}finally {
			Conexion.cierraConsultasPreparadas(result, statement, conexion);
		}
		return listaEntidades;
	}
	
	/**
	 * Obtener una conexión mediante el pool de conexiones
	 * @return Devuelve una conexión
	 * @throws SQLException Excepción de SQL
	 */
	private Connection obtenerConexion() throws SQLException {
		return Conexion.getConexion();
	}
}
