package es.aragon.orgavi.servlet;

import java.io.IOException;
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
 * Servlet implementation class GabineteServlet
 */
@WebServlet("/GabineteServlet")
public class GabineteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LogManager.getLogger(GabineteServlet.class.getName());

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GabineteServlet() {
        super();
		Propiedades.setDefaultUrlPropertiesLog();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			EntidadModel modeloEntidad = new EntidadModel();
//			int entidadId = request.getParameter("idEntidad")!=null?Integer.parseInt(request.getParameter("idEntidad")):modeloEntidad.getIdPresidenciaActual();
			int entidadId = Integer.parseInt(request.getParameter("idEntidad"));
			// Recuperamos el nombre de la entidad padre
			Entidad entidadPadre = modeloEntidad.getEntidadById(entidadId);			
			request.setAttribute("entidadPadre", entidadPadre);
			// Recuperamos el nombre del gabinete
			Entidad gabinete = modeloEntidad.getEntidadById(entidadId);//Parámetro, id de la entidad padre del gabinete
			request.setAttribute("gabinete", gabinete);
			//Recuperamos los cargos del gabinete
			CargoModel modeloCargo = new CargoModel();
			List<Cargo> listaCargosGabinete;
			listaCargosGabinete = modeloCargo.getCargosGabinete(entidadId); //parámetros: id de la entidad cuyo gabinete queremos obtener
			if(listaCargosGabinete.isEmpty()) {
				listaCargosGabinete = modeloCargo.getCargosGabineteAlt(entidadId);
			}
			request.setAttribute("listaCargosGabinete", listaCargosGabinete);
		
			//Listado de links
			String link_aragon_raiz = Propiedades.getRaizLink(1);
			String link_aragon_servicios = Propiedades.getRaizLink(2);
			
			request.setAttribute("link_aragon_raiz", link_aragon_raiz);
			request.setAttribute("link_aragon_servicios", link_aragon_servicios);
			
			//Devolución
			RequestDispatcher dis = request.getRequestDispatcher("/gabinete.jsp");
			dis.forward(request, response);
		}catch(Exception e) {
			LOGGER.error(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
