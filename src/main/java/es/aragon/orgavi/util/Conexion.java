package es.aragon.orgavi.util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

/**
 * Clase que gestiona la apertura y cierre de conexiones con la Base de Datos.
 * @author Daniel Marcos
 *
 */
public class Conexion {
	private static DataSource dataSource = null;
    private static final Logger LOGGER = LogManager.getLogger(Conexion.class.getName());
    
    /**
     * Método constructor privado para evitar ser instanciado
     */
    private Conexion() {
        throw new IllegalStateException("Utility class");
      }

	/**
	 * Gestiona la obtención de los parámetros de conexión desde un pool de conexiones
	 * @return Devuelve un objeto DataSource con la información de la conexión
	 */
    private static DataSource getDataSource() {
		Propiedades.setDefaultUrlPropertiesLog();
		String JNDIName = Propiedades.getEntornoConexion();
		if(dataSource == null) {
			try{	
				InitialContext context = new InitialContext();
				dataSource = (DataSource) context.lookup(JNDIName);
	        } catch (Exception ex) {

				LOGGER.error(ex);
	        }
		}
		return dataSource;
	}
    /**
     * Realiza una conexión con la Base de Datos
     * @return Retorna objeto Connection con la conexión
     * @throws SQLException 
     */
	public static Connection getConexion() throws SQLException{
		return getDataSource().getConnection();
	}
	
	/**
	 * Método de cierre de consultas preparadas
	 * @param rs ResultSet
	 * @param pst PreparedStatement
	 * @param con Connection
	 */
	public static void cierraConsultasPreparadas(ResultSet rs, PreparedStatement pst, Connection con) {
	    try {
	        if (rs != null) {
	        	rs.close();
	        }
	        if (pst != null) {
	        	pst.close();
	        }
	        if (con != null) {
	        	con.close();
	        }
	    } catch (Exception e) {
	        LOGGER.error(e);
	    }
	}
	
	/**
	 * Método de cierre de consultas
	 * @param rs ResultSet
	 * @param pst PreparedStatement
	 * @param con Connection
	 */
	public static void cierraConsultas(ResultSet rs, Statement st, Connection con) {
	    try {
	        if (rs != null) {
	        	rs.close();
	        }
	        if (st != null) {
	        	st.close();
	        }
	        if (con != null) {
	        	con.close();
	        }
	    } catch (Exception e) {
	        LOGGER.error(e);
	    }
	}
	
}
