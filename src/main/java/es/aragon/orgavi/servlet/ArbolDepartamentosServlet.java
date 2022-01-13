package es.aragon.orgavi.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
import es.aragon.orgavi.bl.LegislaturaModel;
import es.aragon.orgavi.dal.Cargo;
import es.aragon.orgavi.dal.Entidad;
import es.aragon.orgavi.dal.Legislatura;
import es.aragon.orgavi.util.Propiedades;

/**
 * Servlet implementation class ArbolDepartamentosPage
 */
@WebServlet("/ArbolDepartamentosServlet")
public class ArbolDepartamentosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LogManager.getLogger(ArbolDepartamentosServlet.class.getName());

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ArbolDepartamentosServlet() {
        super();
		Propiedades.setDefaultUrlPropertiesLog();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LegislaturaModel modeloLegislatura = new LegislaturaModel();
		try {
			int id = request.getParameter("idLegislatura")!=null?Integer.parseInt(request.getParameter("idLegislatura")):modeloLegislatura.getIdLegislaturaActual();
			//Legislatura Seleccionada | Si viaja en la url el parámetro idLegislatura, se toma. Si no, se calcula la actual
			Legislatura legislaturaSeleccionada;
			legislaturaSeleccionada = modeloLegislatura.getById(id);
			request.setAttribute("legislaturaSeleccionada", legislaturaSeleccionada);
	
			//Cargos de las Entidades de nivel 1
			CargoModel modeloCargo = new CargoModel();
			List<Cargo> listaCargosLvl1;
			listaCargosLvl1 = modeloCargo.getCargosEntidadesByLvl(id, 1); //parámetros: id de la legislatura, nivel 1
			request.setAttribute("listaCargosLvl1", listaCargosLvl1);
		
			//Cargos de las Entidades de nivel 2
			modeloCargo = new CargoModel();
			List<Cargo> listaCargosLvl2;
			listaCargosLvl2 = modeloCargo.getCargosEntidadesByLvl(id, 2); //parámetros: id de la legislatura, nivel 2
			request.setAttribute("listaCargosLvl2", listaCargosLvl2);
		
			//Listado entidades lvl 3
			EntidadModel modeloEntidad = new EntidadModel();
			List<Entidad> listaEntidadesLvl3;
			listaEntidadesLvl3 = modeloEntidad.getIdNameEntityByLvl(id, 3); //parámetros: id de la legislatura, nivel 3
			request.setAttribute("listaEntidadesLvl3", listaEntidadesLvl3);
			
			//Listado entidades lvl 4
			List<Entidad> listaEntidadesLvl4;
			listaEntidadesLvl4 = modeloEntidad.getIdNameEntityByLvl(id, 4); //parámetros: id de la legislatura, nivel 4
			request.setAttribute("listaEntidadesLvl4", listaEntidadesLvl4);
			
			//Listado de links
			String link_aragon_raiz = Propiedades.getRaizLink(1);
			String link_aragon_servicios = Propiedades.getRaizLink(2);
			
			request.setAttribute("link_aragon_raiz", link_aragon_raiz);
			request.setAttribute("link_aragon_servicios", link_aragon_servicios);
			
			//Devolución
			RequestDispatcher dis = request.getRequestDispatcher("/arbol-departamentos.jsp");
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
		try {
			doGet(request, response);
		}catch(Exception uhex) {
			LOGGER.error(uhex);
		}
	}

}
