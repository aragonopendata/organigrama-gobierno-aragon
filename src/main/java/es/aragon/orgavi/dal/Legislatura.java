package es.aragon.orgavi.dal;

import java.util.Date;

/**
 * Esta clase permite la generación de objetos Legislatura.
 * Sus atributos se han extraído de los campos de la tabla ORG_LEGISLATURA de la Base de Datos.
 * @author Daniel Marcos
 *
 */
public class Legislatura {
	private int id_legislatura;
	private String nombre;
	private Date fecha_ini;
	private Date fecha_fin;
	private int visible;
	private String observaciones;
	
	/**
	 * Método constructor sin parámetros
	 */
	public Legislatura() {
		super();
	}
	
	/**
	 * Constructor con todos los campos de legislatura
	 * @param id_legislatura id de la legislatura
	 * @param nombre nombre de la legislatura
	 * @param fecha_ini fecha de inicio de la legislatura
	 * @param fecha_fin fecha de finalización de la legislatura
	 * @param visible visibilidad de la legislatura
	 * @param observaciones observaciones de la legislatura
	 */
	public Legislatura(int id_legislatura, String nombre, Date fecha_ini, Date fecha_fin, int visible,
			String observaciones) {
		super();
		this.id_legislatura = id_legislatura;
		this.nombre = nombre;
		this.fecha_ini = (fecha_ini != null)?(Date)fecha_ini.clone():null;
		this.fecha_fin = (fecha_fin != null)?(Date)fecha_fin.clone():null;
		this.visible = visible;
		this.observaciones = observaciones;
	}
	/**
	 * Retorna la id de la legislatura
	 * @return Entero con la id de la legislatura
	 */
	public int getId_legislatura() {
		return id_legislatura;
	}
	/**
	 * Establece la id de la legislatura
	 * @param id_legislatura id de la legislatura
	 */
	public void setId_legislatura(int id_legislatura) {
		this.id_legislatura = id_legislatura;
	}
	/**
	 * Retorna el nombre de la legislatura
	 * @return String con el nombre de la legislatura
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * Establece el nombre de la legislatura
	 * @param nombre nombre de la legislatura
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * Retorna la fecha de inicio de la legislatura
	 * @return Date con la fecha de inicio de la legislatura
	 */
	public Date getFecha_ini() {
		if(fecha_ini!=null) {
			return (Date)fecha_ini.clone();
		}else {
			return null;
		}
	}
	/**
	 * Establece la fecha de inicio de la legislatura
	 * @param fecha_ini fecha de inicio de la legislatura
	 */
	public void setFecha_ini(Date fecha_ini) {
		if(fecha_ini != null) {
			this.fecha_ini = (Date)fecha_ini.clone();
		}else {
			this.fecha_ini=null;
		}
	}
	/**
	 * Retorna la fecha de finalización de la legislatura
	 * @return Date con la fecha de finalización de la legislatura
	 */
	public Date getFecha_fin() {
		if(fecha_fin!=null) {
			return (Date)this.fecha_fin.clone();
		}else {
			return null;
		}
	}
	/**
	 * Establece la fecha de finalización de la legislatura
	 * @param fecha_fin
	 */
	public void setFecha_fin(Date fecha_fin) {
		if(fecha_fin != null) {
			this.fecha_fin = (Date)fecha_fin.clone();
		}else {
			this.fecha_fin=null;
		}
	}
	/**
	 * Retorna el estado de visibilidad de la legislatura (verdadero o falso)
	 * @return Booleano con el estado de visibilidad de la legislatura
	 */
	public int getVisible() {
		return visible;
	}
	/**
	 * Establece el estado de visibilidad de la legislatura
	 * @param visible estado de visibilidad de la legislatura
	 */
	public void setVisible(int visible) {
		this.visible = visible;
	}
	/**
	 * Retorna las observaciones sobre la legislatura
	 * @return String con las observaciones sobre la legislatura
	 */
	public String getObservaciones() {
		return observaciones;
	}
	/**
	 * Establece las observaciones sobre la legislatura
	 * @param observaciones observaciones sobre la legislatura
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	/**
	 * Método toString
	 * @return Parámetros de la clase convertidos en cadena de texto
	 */
	@Override
	public String toString() {
		return "Legislatura [id_legislatura=" + id_legislatura + ", nombre=" + nombre + ", fecha_ini=" + fecha_ini
				+ ", fecha_fin=" + fecha_fin + ", visible=" + visible + ", observaciones=" + observaciones + "]";
	}
	
	
}
