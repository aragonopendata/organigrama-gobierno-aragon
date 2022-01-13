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
import es.aragon.orgavi.bl.LegislaturaModel;
import es.aragon.orgavi.dal.Cargo;
import es.aragon.orgavi.dal.Entidad;
import es.aragon.orgavi.dal.Legislatura;
import es.aragon.orgavi.util.Propiedades;

/**
 * Servlet implementation class HistoricoLegislaturasServlet
 */
@WebServlet("/HistoricoLegislaturasServlet")
public class HistoricoLegislaturasServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LogManager.getLogger(HistoricoLegislaturasServlet.class.getName());

    /**
     * @see HttpServlet#HttpServlet()
     */
    public HistoricoLegislaturasServlet() {
        super();
		Propiedades.setDefaultUrlPropertiesLog();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		//List con todas las legislaturas ordenadas de reciente a antigua
		LegislaturaModel modeloLegislatura = new LegislaturaModel();
		List<Legislatura> listaLegislaturas;
		listaLegislaturas = modeloLegislatura.getLegislaturasDesc();
		request.setAttribute("legislaturas", listaLegislaturas);

		//Legislatura Seleccionada | Si viaja en la url el parámetro idLegislatura, se toma. Si no, se calcula la histórica más reciente
		Legislatura legislaturaSeleccionada;
		int id = request.getParameter("idLegislatura")!=null?Integer.parseInt(request.getParameter("idLegislatura")):modeloLegislatura.getIdLegislaturaHistoricaReciente();
		legislaturaSeleccionada = modeloLegislatura.getById(id);
		request.setAttribute("legislaturaSeleccionada", legislaturaSeleccionada);
		
		//Listado de las Entidades de nivel 1
		EntidadModel modeloEntidad = new EntidadModel();
		List<Entidad> listaEntidadesLvl1;
		listaEntidadesLvl1 = modeloEntidad.getEntidadesByLvl(id, 1); //parámetros: id de la legislatura, entidades de nivel 1
		request.setAttribute("listaEntidadesLvl1", listaEntidadesLvl1);
		
		//Listado de los cargos de entidades de nivel 1
		CargoModel modeloCargo = new CargoModel();
		List<Cargo> listaCargosLvl1;
		listaCargosLvl1 = modeloCargo.getHistoricalResponsables(id, 1); //parámetros: id de la legislatura, entidades de nivel 1
		request.setAttribute("listaCargosLvl1", listaCargosLvl1);
		
		//Listado de entidades de nivel 2
		List<Entidad> listaEntidadesLvl2;
		listaEntidadesLvl2 = modeloEntidad.getEntidadesByLvl(id, 2); //parámetros: id de la legislatura, entidades de nivel 2
		request.setAttribute("listaEntidadesLvl2", listaEntidadesLvl2);
		
		//Listado de cargos de entidades de nivel 2
		List<Cargo> listaCargosLvl2;
		listaCargosLvl2 = modeloCargo.getHistoricalResponsables(id, 2); //parámetros: id de la legislatura, entidades de nivel 2
		request.setAttribute("listaCargosLvl2", listaCargosLvl2);
		
		//Listado de links
		String link_aragon_raiz = Propiedades.getRaizLink(1);
		String link_aragon_servicios = Propiedades.getRaizLink(2);
		
		request.setAttribute("link_aragon_raiz", link_aragon_raiz);
		request.setAttribute("link_aragon_servicios", link_aragon_servicios);
		
		//Devolución	
		RequestDispatcher dis = request.getRequestDispatcher("/historico-legislaturas.jsp");
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
		}	}

}
