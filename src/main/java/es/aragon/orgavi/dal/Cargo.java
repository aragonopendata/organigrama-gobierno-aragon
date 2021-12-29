package es.aragon.orgavi.dal;

import java.util.Date;
/**
 * Esta clase permite la generación de objetos Cargo.
 * Sus atributos se han extraído de los campos de la tabla ORG_CARGO de la Base de Datos
 * @author Daniel Marcos
 *
 */
public class Cargo {
	private int cargoIdCargo;
	private int cargoIdEntidad;
	private int cargoOrden;
	private String cargoCargo;
	private Date cargoFechaIni;
	private Date cargoFechaFin;
	private String cargoNombre;
	private String cargoEdificio;
	private String cargoDireccion;
	private String cargoCp;
	private String cargoLocalidad;
	private String cargoProvincia;
	private String cargoTelefono;
	private String biografia;
	private String cargoFotoPath;
	private String cargoFotoNombre;
	private String cargoEmail;
	private int cargoPublicado;
	private double cargoCoorX;
	private double cargoCoorY;
	private String cargoPaginaWeb;
	private String cargoPaginaWebTitulo;
	private String cargoAgenda;
	
	//Propiedades extendidas de Entidad
	private String entidad_nombre = null;
	private int entidad_id = 0;
	
	/**
	 * Método constructor sin parámetros
	 */
	public Cargo() {
		super();
	}
	
	/**
	 * Método constructor con parámetros
	 * @param id_cargo id del cargo
	 * @param id_entidad id de la entidad a la que pertenece el cargo
	 * @param orden orden que ocupa el cargo dentro de la entidad
	 * @param cargo nombre del cargo que ocupa
	 * @param fecha_ini fecha de inicio en el cargo
	 * @param fecha_fin fecha de finalización en el cargo
	 * @param nombre nombre y apellidos del cargo
	 * @param edificio edificio del cargo
	 * @param direccion dirección del cargo
	 * @param cp código postal del cargo
	 * @param localidad localidad del cargo
	 * @param provincia provincia del cargo
	 * @param telefono teléfono de contacto del cargo
	 * @param biografia biografía del cargo
	 * @param foto_path ruta a la fotografía del cargo
	 * @param foto_nombre nombre de la fotografía del cargo
	 * @param email dirección de correo electrónico del cargo
	 * @param publicado verdadero/falso si está publicado el cargo
	 * @param coor_x coordenada x del cargo
	 * @param coor_y coordenada y del cargo
	 * @param pagina_web dirección a la página web del cargo
	 * @param pagina_web_titulo título de la página web del cargo
	 * @param agenda agenda del cargo
	 */
	public Cargo(int id_cargo, int id_entidad, int orden, String cargo, Date fecha_ini, Date fecha_fin, String nombre,
			String edificio, String direccion, String cp, String localidad, String provincia, String telefono,
			String biografia, String foto_path, String foto_nombre, String email, int publicado, double coor_x,
			double coor_y, String pagina_web, String pagina_web_titulo, String agenda) {
		super();
		this.cargoIdCargo = id_cargo;
		this.cargoIdEntidad = id_entidad;
		this.cargoOrden = orden;
		this.cargoCargo = cargo;
		this.cargoFechaIni = (fecha_ini != null)?(Date)fecha_ini.clone():null;
		this.cargoFechaFin = (fecha_fin != null)?(Date)fecha_fin.clone():null;
		this.cargoNombre = nombre;
		this.cargoEdificio = edificio;
		this.cargoDireccion = direccion;
		this.cargoCp = cp;
		this.cargoLocalidad = localidad;
		this.cargoProvincia = provincia;
		this.cargoTelefono = (formatearTlf(telefono, 3)!=null)?formatearTlf(telefono, 3):telefono;
		this.biografia = biografia;
		this.cargoFotoPath = foto_path;
		this.cargoFotoNombre = foto_nombre;
		this.cargoEmail = email;
		this.cargoPublicado = publicado;
		this.cargoCoorX = coor_x;
		this.cargoCoorY = coor_y;
		this.cargoPaginaWeb = pagina_web;
		this.cargoPaginaWebTitulo = pagina_web_titulo;
		this.cargoAgenda = agenda;
	}
	
	/**
	 * Retorna la id del cargo
	 * @return Entero con id del cargo
	 */
	public int getId_cargo() {
		return cargoIdCargo;
	}
	/**Establece la id del cargo
	 * @param id_cargo id del cargo
	 */
	public void setId_cargo(int id_cargo) {
		this.cargoIdCargo = id_cargo;
	}
	/**
	 * Retorna la id de la entidad a la que pertenece el cargo
	 * @return Entero con la id de la entidad a la que pertenece el cargo
	 */
	public int getId_entidad() {
		return cargoIdEntidad;
	}
	/**
	 * Establece la id de la entidad a la que pertenece el cargo
	 * @param id_entidad id de la entidad a la que pertenece un cargo
	 */
	public void setId_entidad(int id_entidad) {
		this.cargoIdEntidad = id_entidad;
	}
	/**
	 * Retorna el orden del cargo
	 * @return Entero con el orden del cargo
	 */
	public int getOrden() {
		return cargoOrden;
	}
	/**
	 * Establece el orden del cargo
	 * @param orden Orden del cargo
	 */
	public void setOrden(int orden) {
		this.cargoOrden = orden;
	}
	/**
	 * Retorna el nombre del cargo
	 * @return String con el nombre del cargo desempeñado
	 */
	public String getCargo() {
		return cargoCargo;
	}
	/**
	 * Establece el nombre del cargo
	 * @param cargo Nombre del cargo a desempeñar
	 */
	public void setCargo(String cargo) {
		this.cargoCargo = cargo;
	}
	/**
	 * Retorna la fecha de inicio en el cargo
	 * @return Date con la fecha de inicio en el cargo
	 */
	public Date getFecha_ini() {
		if(cargoFechaIni!=null) {
			return (Date)cargoFechaIni.clone();
		}else {
			return null;
		}
	}
	/**
	 * Establece la fecha de inicio en el cargo
	 * @param fecha_ini fecha de inicio en el cargo
	 */
	public void setFecha_ini(Date fecha_ini) {
		if(fecha_ini != null) {
			this.cargoFechaIni = (Date)fecha_ini.clone();
		}else {
			this.cargoFechaIni=null;
		}
	}
	/**
	 * Retorna la fecha de finalización en el cargo
	 * @return Date con la fecha de finalización en el cargo
	 */
	public Date getFecha_fin() {
		if(cargoFechaFin!=null) {
			return (Date)this.cargoFechaFin.clone();
		}else {
			return null;
		}
	}
	/**
	 * Establece fecha de finalización en el cargo
	 * @param fecha_fin fecha de finalización en el cargo
	 */
	public void setFecha_fin(Date fecha_fin) {
		if(fecha_fin != null) {
			this.cargoFechaFin = (Date)fecha_fin.clone();
		}else {
			this.cargoFechaFin=null;
		}
	}
	/**
	 * Retorna el nombre y apellidos del cargo
	 * @return String con el nombre y apellidos del cargo
	 */
	public String getNombre() {
		return cargoNombre;
	}
	/**
	 * Establece el nombre y apellidos del cargo
	 * @param nombre nombre y apellidos del cargo
	 */
	public void setNombre(String nombre) {
		this.cargoNombre = nombre;
	}
	/**
	 * Retorna el edificio del cargo
	 * @return String con el nombre del edificio del cargo
	 */
	public String getEdificio() {
		return cargoEdificio;
	}
	/**
	 * Establece el nombre del edificio del cargo
	 * @param edificio nombre del edificio del cargo
	 */
	public void setEdificio(String edificio) {
		this.cargoEdificio = edificio;
	}
	/**
	 * Retorna la dirección de contacto del cargo
	 * @return String con la dirección de contacto del cargo
	 */
	public String getDireccion() {
		return cargoDireccion;
	}
	/**
	 * Establece la dirección de contacto del cargo
	 * @param direccion dirección de contacto del cargo
	 */
	public void setDireccion(String direccion) {
		this.cargoDireccion = direccion;
	}
	/**
	 * Retorna el código postar del cargo
	 * @return código postal del cargo
	 */
	public String getCp() {
		return cargoCp;
	}
	/**
	 * Establece el código postar del cargo
	 * @param cp código postal del cargo
	 */
	public void setCp(String cp) {
		this.cargoCp = cp;
	}
	/**
	 * Retorna la localidad del cargo
	 * @return String con la localidad del cargo
	 */
	public String getLocalidad() {
		return cargoLocalidad;
	}
	/**
	 * Establece la localidad del cargo
	 * @param localidad localidad del cargo
	 */
	public void setLocalidad(String localidad) {
		this.cargoLocalidad = localidad;
	}
	/**
	 * Retorna la provincia del cargo
	 * @return String con la provincia del cargo
	 */
	public String getProvincia() {
		return cargoProvincia;
	}
	/**
	 * Establece la provincia del cargo
	 * @param provincia provincia del cargo
	 */
	public void setProvincia(String provincia) {
		this.cargoProvincia = provincia;
	}
	/**
	 * Retorna el teléfono de contacto del cargo
	 * @return String con el número de teléfono de contacto del cargo
	 */
	public String getTelefono() {
		return cargoTelefono;
	}
	/**
	 * Establece el teléfono de contacto del cargo
	 * @param telefono teléfono de contacto del cargo
	 */
	public void setTelefono(String telefono) {
		this.cargoTelefono = (formatearTlf(telefono, 3)!=null)?formatearTlf(telefono, 3):telefono;
	}
	/**
	 * Retorna la biograf�a del cargo
	 * @return String con la biografía del cargo
	 */
	public String getBiografia() {
		return biografia;
	}
	/**
	 * Establece la biografía del cargo
	 * @param biografia biografía del cargo
	 */
	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}
	/**
	 * Retorna la ruta a la fotografía del cargo
	 * @return String con la ruta a la fotografía del cargo
	 */
	public String getFoto_path() {
		return cargoFotoPath;
	}
	/**
	 * Establece la ruta a la fotografía del cargo
	 * @param foto_path ruta a la fotografía del cargo
	 */
	public void setFoto_path(String foto_path) {
		this.cargoFotoPath = foto_path;
	}
	/**
	 * Retorna el nombre de la fotografía del cargo
	 * @return String con el nombre de la fotografía del cargo
	 */
	public String getFoto_nombre() {
		return cargoFotoNombre;
	}
	/**
	 * Establece el nombre de la fotografía del cargo
	 * @param foto_nombre nombre de la fotografía del cargo
	 */
	public void setFoto_nombre(String foto_nombre) {
		this.cargoFotoNombre = foto_nombre;
	}
	/**
	 * Retorna el email de contacto del cargo
	 * @return String con el email de contacto del cargo
	 */
	public String getEmail() {
		return cargoEmail;
	}
	/**
	 * Establece el email de contacto del cargo
	 * @param email email de contacto del cargo
	 */
	public void setEmail(String email) {
		this.cargoEmail = email;
	}
	/**
	 * Retorna verdadero o falso en función de si la información del cargo se encuentra publicada o no
	 * @return Verdadero o Falso sobre el estado de publicación de la información del cargo
	 */
	public int getPublicado() {
		return cargoPublicado;
	}
	/**
	 * Establece si la info del cargo está publicada o no
	 * @param publicado Verdadero o falso a si la info del cargo está publicada o no
	 */
	public void setPublicado(int publicado) {
		this.cargoPublicado = publicado;
	}
	/**
	 * Retorna las coordenadas del eje x del cargo
	 * @return Double con las coordenadas del eje x del cargo
	 */
	public double getCoor_x() {
		return cargoCoorX;
	}
	/**
	 * Establece las coordenadas para el eje 'x' del cargo
	 * @param coor_x coordenadas del eje 'x' del cargo
	 */
	public void setCoor_x(double coor_x) {
		this.cargoCoorX = coor_x;
	}
	/**
	 * Retorna las coordenadas del eje y del cargo
	 * @return Double con las coordenadas del eje 'y' del cargo
	 */
	public double getCoor_y() {
		return cargoCoorY;
	}
	/**
	 * Establece las coordenadas del eje 'y' del cargo
	 * @param coor_y coordenadas del eje 'y' del cargo
	 */
	public void setCoor_y(double coor_y) {
		this.cargoCoorY = coor_y;
	}
	/**
	 * Retorna la ruta a la página web del cargo
	 * @return String con la ruta de la página web del cargo
	 */
	public String getPagina_web() {
		return cargoPaginaWeb;
	}
	/**
	 * Establece la ruta a la página web del cargo
	 * @param pagina_web página web del cargo
	 */
	public void setPagina_web(String pagina_web) {
		this.cargoPaginaWeb = pagina_web;
	}
	/**
	 * Retorna el t�tulo de la página web del cargo
	 * @return String con el título de la pággina web del cargo
	 */
	public String getPagina_web_titulo() {
		return cargoPaginaWebTitulo;
	}
	/**
	 * Establece el título de la página web del cargo
	 * @param pagina_web_titulo título de la página web del cargo
	 */
	public void setPagina_web_titulo(String pagina_web_titulo) {
		this.cargoPaginaWebTitulo = pagina_web_titulo;
	}
	/**
	 * Retorna la agenda del cargo
	 * @return String con la agenda del cargo
	 */
	public String getAgenda() {
		return cargoAgenda;
	}
	/**
	 * Establece la agenda del cargo
	 * @param agenda agenda del cargo
	 */
	public void setAgenda(String agenda) {
		this.cargoAgenda = agenda;
	}
	
	//M�todos extendidos de Entidad
	/**
	 * Retorna el nombre de la entidad a la que pertenece el cargo
	 * @return String con el nombre de la entidad a la que pertenece el cargo
	 */
	public String getEntidad_nombre() {
		return entidad_nombre;
	}

	/**
	 * Establece el nombre de la entidad a la que pertenece el cargo
	 * @param entidad_nombre nombre de la entidad a la que pertenece el cargo
	 */
	public void setEntidad_nombre(String entidad_nombre) {
		this.entidad_nombre = entidad_nombre;
	}
	/**
	 * Retorna la id de la entidad a la que pertenece el cargo
	 * @return Entero con la id de la entidad a la que pertenece el cargo
	 */
	public int getEntidad_id() {
		return entidad_id;
	}
	/**
	 * Establece la id de la entidad a la que pertenece el cargo
	 * @param entidad_id id de la entidad a la que pertenece el cargo
	 */
	public void setEntidad_id(int entidad_id) {
		this.entidad_id = entidad_id;
	}
	/**
	 * Formatea el tlf de los cargos en grupos de n dígitos. Si no puede conseguirlo, lo retorna bruto de la bbdd
	 * @param telefono
	 * @param n determina la cantidad de dígitos en cada agrupación
	 * @return Devuelte el tlf formateado o crudo si no ha podido formatearlo
	 */
	private String formatearTlf(String tlf, int n) {
		StringBuilder bld = new StringBuilder();
		try {
		String tlfTrimed = tlf.trim();
		char[] aChar = tlfTrimed.toCharArray();
		if(aChar.length%n == 0) {
			for(int i=1; i<=aChar.length; i++) {
				if(i%n!=0) {
					bld.append(aChar[i-1]);
				}else {
					bld.append(aChar[i-1] + " ");
				}
			}
		}else {
			return tlf;
		}}catch(Exception e) {
			return tlf;
		}
		String tlfFormated = bld.toString();
		return tlfFormated.trim();
	}
	/**
	 * Método toString
	 * @return Parámetros de la clase convertidos en cadena de texto
	 */
	@Override
	public String toString() {
		return "Cargo [id_cargo=" + cargoIdCargo + ", id_entidad=" + cargoIdEntidad + ", orden=" + cargoOrden + ", cargo=" + cargoCargo
				+ ", fecha_ini=" + cargoFechaIni + ", fecha_fin=" + cargoFechaFin + ", nombre=" + cargoNombre + ", edificio="
				+ cargoEdificio + ", direccion=" + cargoDireccion + ", cp=" + cargoCp + ", localidad=" + cargoLocalidad + ", provincia="
				+ cargoProvincia + ", telefono=" + cargoTelefono + ", biografia=" + biografia + ", foto_path=" + cargoFotoPath
				+ ", foto_nombre=" + cargoFotoNombre + ", email=" + cargoEmail + ", publicado=" + cargoPublicado + ", coor_x="
				+ cargoCoorX + ", coor_y=" + cargoCoorY + ", pagina_web=" + cargoPaginaWeb + ", pagina_web_titulo="
				+ cargoPaginaWebTitulo + ", agenda=" + cargoAgenda + "]";
	}
	
	
}
