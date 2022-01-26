package es.aragon.orgavi.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import es.aragon.orgavi.bl.CargoModel;
import es.aragon.orgavi.bl.EntidadModel;
import es.aragon.orgavi.dal.Cargo;
import es.aragon.orgavi.dal.Entidad;
import es.aragon.orgavi.util.Propiedades;

/**
 * Servlet implementation class ServicioPage
 */
@WebServlet("/Organismo2Servlet")
public class Organismo2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LogManager.getLogger(Organismo2Servlet.class.getName());
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Organismo2Servlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		EntidadModel modeloEntidad = new EntidadModel();
		CargoModel modeloCargo = new CargoModel();
		int entidad_id = request.getParameter("idEntidad")!=null?Integer.parseInt(request.getParameter("idEntidad")):modeloEntidad.getIdServicioPrincipalActual();
		
		//Entidad padre para el breadcum
		Entidad entidadPadre;
		entidadPadre = modeloEntidad.getInfoEntidadPadre(entidad_id);
		request.setAttribute("entidadPadre", entidadPadre);
		
		//Nombre de la entidad y web
		Entidad entidad;
		entidad = modeloEntidad.getEntidadById(entidad_id);
		request.setAttribute("entidadSeleccionada", entidad);

		//Info de la entidad y del cargo máximo responsable de la misma
		Entidad departamento;
		departamento = modeloEntidad.getDepartamentoById(entidad_id, 1);
		request.setAttribute("infoDepartamento", departamento);
		
		//Recuperamos las entidades hijas de la entidad que estamos mostrando. Buscamos en las hijas que coincida el valor de id_entidad_padre con el id_entidad de nuestra entidad
		List<Entidad> entidadesHijas;
		entidadesHijas = modeloEntidad.getEntidadesHijas(entidad_id);
		request.setAttribute("entidadesHijas", entidadesHijas);
		
		//Recuperamos todos los empleados que trabajen en alguna entidad que trabaja para la entidad que mostramos
		List<Cargo> listaEmpleados;
		listaEmpleados = modeloCargo.getCargosByIDEntidadPadre(entidad_id);
		request.setAttribute("listaEmpleados", listaEmpleados);
		
		//Listado de links
		String link_aragon_raiz = Propiedades.getRaizLink(1);
		String link_aragon_servicios = Propiedades.getRaizLink(2);
		
		request.setAttribute("link_aragon_raiz", link_aragon_raiz);
		request.setAttribute("link_aragon_servicios", link_aragon_servicios);
		
		//Devolución
		RequestDispatcher dis = request.getRequestDispatcher("/organismo-2.jsp");
		dis.forward(request, response);
		}catch(Exception e) {
			LOGGER.error(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			doGet(request, response);
		}catch(Exception e) {
			LOGGER.error(e);
		}
	}

}
