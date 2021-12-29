package es.aragon.orgavi.dal;

/**
 * Esta clase permite la creación de objetos Entidad.
 * Sus atributos se han extraído de los campos de la tabla ORG_ENTIDAD de la Base de Datos
 * @author Daniel Marcos
 *
 */
public class Entidad {
	private int id_entidad;
	private int id_entidad_padre;
	private int id_legislatura;
	private int nivel;
	private int orden;
	private String nombre;
	private String observaciones;
	private int dependencia_directa;
	private String cod_siu;
	private String edificio;
	private String direccion;
	private String cp;
	private String localidad;
	private String provincia;
	private String telefono;
	private double coor_x;
	private double coor_y;
	private String email;
	private String pagina_web;
	private String pagina_web_titulo;
	
	//Propiedades extendidas de cargo
	private int responsable_id = 0;
	private String responsable_nombre = null;
	private String responsable_cargo = null;
	private String responsable_biografia = null;
	private String responsable_foto_path = null;
	private String responsable_foto_nombre = null;
	private String responsable_agenda = null;
	private String responsable_foto_contenido = null;
	
	/**
	 * Método constructor sin parámetros
	 */
	public Entidad() {
		super();
	}
	
	/**
	 * Método constructor con todos los campos de Entidad
	 * @param id_entidad id de la entidad
	 * @param id_entidad_padre id de la entidad padre de la entidad
	 * @param id_legislatura id de la legislatura a la que pertenece la entidad
	 * @param nivel nivel de la entidad
	 * @param orden orden de la entidad
	 * @param nombre nombre de la entidad
	 * @param observaciones observaciones de la entidad
	 * @param dependencia_directa id de la entidad de la que depende directamente esta entidad
	 * @param cod_siu código siu
	 * @param edificio edificio donde se emplaza la entidad
	 * @param direccion dirección de la entidad
	 * @param cp c�digo postal de la entidad
	 * @param localidad localidad en donde se localiza la entidad
	 * @param provincia provincia en donde se localiza la entidad
	 * @param telefono teléfono de contacto de la entidad
	 * @param coor_x coordenada del eje 'x' de la entidad
	 * @param coor_y coordenada del eje 'y' de la entidad
	 * @param email email de contacto de la entidad
	 * @param pagina_web url de la página web de la entidad
	 * @param pagina_web_titulo título de la página web de la entidad
	 */
	public Entidad(int id_entidad, int id_entidad_padre, int id_legislatura, int nivel, int orden, String nombre,
			String observaciones, int dependencia_directa, String cod_siu, String edificio, String direccion, String cp,
			String localidad, String provincia, String telefono, double coor_x, double coor_y, String email,
			String pagina_web, String pagina_web_titulo) {
		super();
		this.id_entidad = id_entidad;
		this.id_entidad_padre = id_entidad_padre;
		this.id_legislatura = id_legislatura;
		this.nivel = nivel;
		this.orden = orden;
		this.nombre = nombre;
		this.observaciones = observaciones;
		this.dependencia_directa = dependencia_directa;
		this.cod_siu = cod_siu;
		this.edificio = edificio;
		this.direccion = direccion;
		this.cp = cp;
		this.localidad = localidad;
		this.provincia = provincia;
		this.telefono = (formatearTlf(telefono, 3)!=null)?formatearTlf(telefono, 3):telefono;
		this.coor_x = coor_x;
		this.coor_y = coor_y;
		this.email = email;
		this.pagina_web = pagina_web_titulo;
		this.pagina_web_titulo = pagina_web;
	}
	
	/**
	 * Retorna el id de la entidad
	 * @return Entero con el id de la entidad
	 */
	public int getId_entidad() {
		return id_entidad;
	}
	/**
	 * Establece el id de la entidad
	 * @param id_entidad id de la entidad
	 */
	public void setId_entidad(int id_entidad) {
		this.id_entidad = id_entidad;
	}
	/**
	 * Retorna el id de la entidad padre de la entidad
	 * @return id de la entidad padre de la entidad
	 */
	public int getId_entidad_padre() {
		return id_entidad_padre;
	}
	/**
	 * Establece el id de la entidad padre de la entidad
	 * @param id_entidad_padre id de la entidad padre de la entidad
	 */
	public void setId_entidad_padre(int id_entidad_padre) {
		this.id_entidad_padre = id_entidad_padre;
	}
	/**
	 * Retorna el id de la legislatura que corresponde a la entidad
	 * @return Entero con el id de la legislatura que corresponde a la entidad
	 */
	public int getId_legislatura() {
		return id_legislatura;
	}
	/**
	 * Establece el id de la legislatura que corresponde a la entidad
	 * @param id_legislatura id de la legislatura que corresponde a la entidad
	 */
	public void setId_legislatura(int id_legislatura) {
		this.id_legislatura = id_legislatura;
	}
	/**
	 * Retorna el nivel de la entidad
	 * @return Entero con el nivel de la entidad
	 */
	public int getNivel() {
		return nivel;
	}

	/**
	 * Establece el nivel de la entidad
	 * @param nivel nivel de la entidad
	 */
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	/**
	 * Retorna el orden de la entidad
	 * @return Entero con el orden de la entidad
	 */
	public int getOrden() {
		return orden;
	}
	/**
	 * Establece el orden de la entidad
	 * @param orden orden de la entidad
	 */
	public void setOrden(int orden) {
		this.orden = orden;
	}
	/**
	 * Retorna el nombre de la entidad
	 * @return String con el nombre de la entidad
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * Establece el nombre de la entidad
	 * @param nombre nombre de la entidad
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * Retorna las observaciones de la entidad
	 * @return String con las observaciones de la entidad
	 */
	public String getObservaciones() {
		return observaciones;
	}
	/**
	 * Establece las observaciones de la entidad
	 * @param observaciones observaciones de la entidad
	 */
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	/**
	 * Retorna la id de la entidad de la que la entidad actual depende directamente
	 * @return Entero con el id de la entidad de la que la entidad actual depende
	 */
	public int getDependencia_directa() {
		return dependencia_directa;
	}
	/**
	 * Establece la id de la entidad de la que depende la entidad actual
	 * @param dependencia_directa id de la entidad de la que que depende la entidad actual
	 */
	public void setDependencia_directa(int dependencia_directa) {
		this.dependencia_directa = dependencia_directa;
	}
	/**
	 * Retorna el código siu de la entidad
	 * @return String con el código siu de la entidad
	 */
	public String getCod_siu() {
		return cod_siu;
	}
	/**
	 * Establece el código siu de la entidad
	 * @param cod_siu código siu de la entidad
	 */
	public void setCod_siu(String cod_siu) {
		this.cod_siu = cod_siu;
	}
	/**
	 * Retorna el edificio de la entidad
	 * @return String con el edificio de la entidad
	 */
	public String getEdificio() {
		return edificio;
	}
	/**
	 * Establece el edificio de la entidad
	 * @param edificio edificio de la entidad
	 */
	public void setEdificio(String edificio) {
		this.edificio = edificio;
	}
	/**
	 * Retorna la direcci�n de contacto de la entidad
	 * @return String con la dirección de contacto de la entidad
	 */
	public String getDireccion() {
		return direccion;
	}
	/**
	 * Establece la dirección de contacto de la entidad
	 * @param direccion dirección de contacto de la entidad
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	/**
	 * Retorna el código postal de la entidad
	 * @return Entero con el código postal de la entidad
	 */
	public String getCp() {
		return cp;
	}
	/**
	 * Establece el código postal de la entidad
	 * @param cp código postal de la entidad
	 */
	public void setCp(String cp) {
		this.cp = cp;
	}
	/**
	 * Retorna la localidad de la entidad
	 * @return String con la localidad de la entidad
	 */
	public String getLocalidad() {
		return localidad;
	}
	/**
	 * Establece la localidad de la entidad
	 * @param localidad localidad de la entidad
	 */
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	/**
	 * Retorna la provincia de la entidad
	 * @return String con la provincia de la entidad
	 */
	public String getProvincia() {
		return provincia;
	}
	/**
	 * Establece la provincia de la entidad
	 * @param provincia provincia de la entidad
	 */
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	/**
	 * Retorna el teléfono de contacto de la entidad
	 * @return String con el teléfono de contacto de la entidad
	 */
	public String getTelefono() {
		return telefono;
	}
	/**
	 * Establece el teléfono de contacto de la entidad
	 * @param telefono teléfono de contacto de la entidad
	 */
	public void setTelefono(String telefono) {
		this.telefono = (formatearTlf(telefono, 3)!=null)?formatearTlf(telefono, 3):telefono;
	}
	/**
	 * Retorna la coordenada del eje 'x' de la entidad
	 * @return Double con la coordenada del eje 'x' de la entidad
	 */
	public double getCoor_x() {
		return coor_x;
	}
	/**
	 * Establece la coordenada del eje 'x' de la entidad
	 * @param coor_x coordenada del eje 'x' de la entidad
	 */
	public void setCoor_x(double coor_x) {
		this.coor_x = coor_x;
	}
	/**
	 * Retorna la coordenada del eje 'y' de la entidad
	 * @return Double con la coordenada del eje 'y' de la entidad
	 */
	public double getCoor_y() {
		return coor_y;
	}
	/**
	 * Establece la coordenada del eje 'y' de la entidad
	 * @param coor_y Coordenada del eje 'y' de la entidad
	 */
	public void setCoor_y(double coor_y) {
		this.coor_y = coor_y;
	}
	/**
	 * Retorna el email de contacto de la entidad
	 * @return String con el email de contacto de la entidad
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Estabece el email de contacto de la entidad 
	 * @param email email de contacto de la entidad
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * Retorna la dirección a la página web de la entidad
	 * @return String con la dirección a la página web de la entidad
	 */
	public String getPagina_web() {
		return pagina_web;
	}
	/**
	 * Establece la dirección a la página web de la entidad
	 * @param pagina_web_titulo página web de la entidad
	 */
	public void setPagina_web(String pagina_web) {
		this.pagina_web_titulo = pagina_web;
	}
	/**
	 * Retorna el título de la página web de la entidad
	 * @return String con el título de la página web de la entidad
	 */
	public String getPagina_web_titulo() {
		return pagina_web;
	}
	/**
	 * Establece el título de la página web de la entidad
	 * @param pagina_web_titulo título de la página web de la entidad
	 */
	public void setPagina_web_titulo(String pagina_web_titulo) {
		this.pagina_web = pagina_web_titulo;
	}

	//M�todos extendidos de cargo
	/**
	 * Retorna el id del cargo responsable de la entidad
	 * @return Entero con el id del cargo responsable de la entidad
	 */
	public int getResponsable_id() {
		return responsable_id;
	}
	/**
	 * Establece el id del cargo responsable de la entidad
	 * @param responsable_id id del cargo responsable de la entidad
	 */
	public void setResponsable_id(int responsable_id) {
		this.responsable_id = responsable_id;
	}
	/**
	 * Retorna el nombre y apellidos del cargo responsable de la entidad
	 * @return String con el nombre y apellidos del cargo responsable de la entidad
	 */
	public String getResponsable_nombre() {
		return responsable_nombre;
	}
	/**
	 * Establece el nombre y apellidos del cargo responsable de la entidad
	 * @param responsable_nombre nombre y apellidos del cargo responsable de la entidad
	 */
	public void setResponsable_nombre(String responsable_nombre) {
		this.responsable_nombre = responsable_nombre;
	}

	/**
	 * Retorna el título del cargo del responsable de la entidad
	 * @return String con el título del cargo del resposable de la entidad
	 */
	public String getResponsable_cargo() {
		return responsable_cargo;
	}
	/**
	 * Establece el título del cargo del responsable de la entidad
	 * @param responsable_cargo título del cargo del responsable de la entidad
	 */
	public void setResponsable_cargo(String responsable_cargo) {
		this.responsable_cargo = responsable_cargo;
	}
	/**
	 * Retorna la biografía del cargo responsable de la entidad
	 * @return String con la biografía del cargo responsable de la entidad
	 */
	public String getResponsable_biografia() {
		return responsable_biografia;
	}
	/**
	 * Establece la biografía del cargo responsable de la entidad
	 * @param responsable_biografia Biografía del cargo responsable de la entidad
	 */
	public void setResponsable_biografia(String responsable_biografia) {
		this.responsable_biografia = responsable_biografia;
	}
	/**
	 * Retorna la url de la fotografía del cargo responsable de la entidad
	 * @return url de la fotografía del cargo responsable de la entidad
	 */
	public String getResponsable_foto_path() {
		return responsable_foto_path;
	}
	/**
	 * Establece la url de la fotografía del cargo responsable de la entidad
	 * @param responsable_foto_path url de la fotografía del cargo responsable de la entidad
	 */
	public void setResponsable_foto_path(String responsable_foto_path) {
		this.responsable_foto_path = responsable_foto_path;
	}
	/**
	 * Retorna el nombre de la fotografía del cargo responsable de la entidad
	 * @return String con el nombre de la fotografía del cargo responsable de la entidad
	 */
	public String getResponsable_foto_nombre() {
		return responsable_foto_nombre;
	}
	/**
	 * Establece el nombre de la fotografía del cargo responsable de la entidad
	 * @param responsable_foto_nombre nombre de la fotografía del cargo responsable de la entidad
	 */
	public void setResponsable_foto_nombre(String responsable_foto_nombre) {
		this.responsable_foto_nombre = responsable_foto_nombre;
	}
	/**
	 * Retorna la agenda del cargo responsable de la entidad
	 * @return String con la agenda del cargo responsable de la entidad
	 */
	public String getResponsable_agenda() {
		return responsable_agenda;
	}
	/**
	 * Establece la agenda del cargo del responsable de la entidad
	 * @param responsable_agenda agenda del cargo responsable de la entidad
	 */
	public void setResponsable_agenda(String responsable_agenda) {
		this.responsable_agenda = responsable_agenda;
	}
	/**
	 * Retorna el contenido de la fotografía del cargo responsable de la entidad
	 * @return responsable_foto_contenido contenido de la fotografía del cargo responsable de la entidad
	 */
	public String getResponsable_foto_contenido() {
		return responsable_foto_contenido;
	}
	/**
	 * Establece el contenido de la fotografía del cargo responsable de la entidad
	 * @param responsable_foto_contenido contenido de la fotografía del cargo responsable de la entidad
	 */
	public void setResponsable_foto_contenido(String responsable_foto_contenido) {
		this.responsable_foto_contenido = responsable_foto_contenido;
	}

	
	
	/**
	 * Formatea el teléfono, si es factible, en grupos de n dígitos. Si no, lo deja como lo toma de la base de datos
	 * @param telefono
	 * @param n cantidad de dígitos en que se desean agrupar los números de teléfono.
	 * @return Retorna teléfono formateado en grupos de n dígitos si es viable, y si no lo retorna crudo de la base de datos
	 */
	private String formatearTlf(String telefono, int n) {
		StringBuilder bld = new StringBuilder();
		try {
		String tlfTrimeado = telefono.trim();
		char[] aCaracteres = tlfTrimeado.toCharArray();
		if(aCaracteres.length%n == 0) {
			for(int i=1; i<=aCaracteres.length; i++) {
				if(i%n!=0) {
					bld.append(aCaracteres[i-1]);
				}else {
					bld.append(aCaracteres[i-1] + " ");
				}
			}
		}else {
			return telefono;
		}}catch(Exception e) {
			return telefono;
		}
		
		String tlfFormateado = bld.toString();
		return tlfFormateado.trim();
	}
	
	/**
	 * Método toString
	 * @return Parámetros de la clase convertidos en cadena de texto
	 */
	@Override
	public String toString() {
		return "Entidad [id_entidad=" + id_entidad + ", id_entidad_padre=" + id_entidad_padre + ", id_legislatura="
				+ id_legislatura + ", nivel=" + nivel + ", orden=" + orden + ", nombre=" + nombre + ", observaciones="
				+ observaciones + ", dependencia_directa=" + dependencia_directa + ", cod_siu=" + cod_siu
				+ ", edificio=" + edificio + ", direccion=" + direccion + ", cp=" + cp + ", localidad=" + localidad
				+ ", provincia=" + provincia + ", telefono=" + telefono + ", coor_x=" + coor_x + ", coor_y=" + coor_y
				+ ", email=" + email + ", pagina_web=" + pagina_web + ", pagina_web_titulo=" + pagina_web_titulo + "]";
	}
	
}
