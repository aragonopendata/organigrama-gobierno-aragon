package es.aragon.orgavi.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

import es.aragon.orgavi.bl.EntidadModel;
import es.aragon.orgavi.bl.LegislaturaModel;
import es.aragon.orgavi.dal.Entidad;
import es.aragon.orgavi.util.Propiedades;

/**
 * Servlet implementation class IndexPage
 */
@WebServlet("/IndexServlet")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LogManager.getLogger(IndexServlet.class.getName());

    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		//Recuperamos la acción a realizar
		int accion = request.getParameter("accion")!=null?Integer.parseInt(request.getParameter("accion")):0;
		EntidadModel modeloEntidad = new EntidadModel();
		LegislaturaModel modeloLegislatura = new LegislaturaModel();
		int id_legislaturaActual = modeloLegislatura.getIdLegislaturaActual();
		Gson gson;
		PrintWriter salida = response.getWriter();
		String json = "";
		//La filtramos, ejecutamos y convertimos en json si precisa
		switch(accion) {
		case 1: //Fecha de inicio de la legislatura actual
			Date fecha_ini = modeloLegislatura.getFechaIniLegislaturaActual();
			SimpleDateFormat sdf = new SimpleDateFormat("d");
			String day = sdf.format(fecha_ini);
			sdf = new SimpleDateFormat("MM");
			String month = sdf.format(fecha_ini);
			HashMap<String, String> meses = new HashMap<String, String>();
		    meses.put("01", "enero");
		    meses.put("02", "febrero");
		    meses.put("03", "marzo");
		    meses.put("04", "abril");
		    meses.put("05", "mayo");
		    meses.put("06", "junio");
		    meses.put("07", "julio");
		    meses.put("08", "agosto");
		    meses.put("09", "septiembre");
		    meses.put("10", "octubre");
		    meses.put("11", "noviembre");
		    meses.put("12", "diciembre");
			month = meses.get(month);

			sdf = new SimpleDateFormat("yyyy");
			String year = sdf.format(fecha_ini);
			salida.print(day + " de "+ month+ " de "+ year);
			break;
		case 2: //Entidades de nivel 1 de la legislatura actual importadas desde jsp
			List<Entidad> listaEntidadesLvl1;
			listaEntidadesLvl1 = modeloEntidad.getEntidadesYResponsablesByLvl(id_legislaturaActual, 1, 1); //parámetros: id de la legislatura, nivel 1
			request.setAttribute("listaEntidadesLvl1", listaEntidadesLvl1);
			RequestDispatcher dis = request.getRequestDispatcher("lvl1.jsp");
			dis.forward(request, response);
			break;	
		case 3: //Enlaces a entidades de nivel 2
			List<Entidad> listaEntidadesLvl2;
			listaEntidadesLvl2 = modeloEntidad.getIdNameEntityByLvl(id_legislaturaActual, 2); //parámetros: id de la legislatura, nivel 2 de entidad
			request.setAttribute("listaEntidadesLvl2", listaEntidadesLvl2);
			RequestDispatcher dispatcher = request.getRequestDispatcher("lvl2.jsp");
			dispatcher.forward(request, response);
			break;
		case 4: //Obtención y pintado de la versión de la aplicación a partir del fichero de propiedades
			String versionApp = "";
			versionApp = Propiedades.getAppVersion();
			salida.print(versionApp);
			break;
		case 5: //Generación del header con los links
			String link_aragon_raizAlt = Propiedades.getRaizLink(1);
			request.setAttribute("link_aragon_raiz", link_aragon_raizAlt);
			RequestDispatcher disp = request.getRequestDispatcher("/header.jsp");
			disp.forward(request, response);
			break;
		case 6: //Generación del footer con los links
			String link_aragon_raiz = Propiedades.getRaizLink(1);
			String link_aragon_servicios = Propiedades.getRaizLink(2);
			request.setAttribute("link_aragon_raiz", link_aragon_raiz);
			request.setAttribute("link_aragon_servicios", link_aragon_servicios);
			RequestDispatcher dispatch = request.getRequestDispatcher("/footer.jsp");
			dispatch.forward(request, response);
			break;
		default:	
		}		
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
