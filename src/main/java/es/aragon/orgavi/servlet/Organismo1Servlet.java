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
import es.aragon.orgavi.dal.Entidad;

/**
 * Servlet implementation class DepartamentoPage
 */
@WebServlet("/Organismo1Servlet")
public class Organismo1Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = LogManager.getLogger(Organismo1Servlet.class.getName());

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Organismo1Servlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
		EntidadModel modeloEntidad = new EntidadModel();
		LegislaturaModel modeloLegislatura = new LegislaturaModel();
		CargoModel modeloCargo = new CargoModel();
		int entidad_id = request.getParameter("idEntidad")!=null?Integer.parseInt(request.getParameter("idEntidad")):modeloEntidad.getIdPresidenciaActual();
		int id_legislatura_actual = modeloLegislatura.getIdLegislaturaActual();
		Entidad entidad;
		//Comprobamos si es una entidad de lvl 1 (para mostrar o no gabinete)
		boolean isLvl1 = modeloEntidad.isLvl1(entidad_id);
		request.setAttribute("isLvl1", isLvl1);
		boolean hasGabinete = false;
		if(isLvl1 == false) {//Si no es una entidad de nivel 1, comprobamos si tiene gabinete o no
			//Devolver si tiene gabinete
			hasGabinete = modeloEntidad.hasGabinete(entidad_id); //has gabinete = true or false
			if(hasGabinete == false) {
				hasGabinete = modeloCargo.hasGabineteAlt(entidad_id);
			}
			//Devolver Entidades de las que es padre, con el nombre y cargo del responsable de las mismas, por tipos
			String tipo = "A"; //Administraciones
			List<Entidad> listaEntidadesHijaTipoA;
			listaEntidadesHijaTipoA = modeloEntidad.getEntidadesHijaYResponsable(entidad_id, id_legislatura_actual, tipo);
			request.setAttribute("listaEntidadesHijaTipoA", listaEntidadesHijaTipoA);
			
			tipo = "C"; //Órganos consultivos
			List<Entidad> listaEntidadesHijaTipoC;
			listaEntidadesHijaTipoC = modeloEntidad.getEntidadesHijaYResponsable(entidad_id, id_legislatura_actual, tipo);
			request.setAttribute("listaEntidadesHijaTipoC", listaEntidadesHijaTipoC);
			
			tipo = "E"; //Empresas públicas
			List<Entidad> listaEntidadesHijaTipoE;
			listaEntidadesHijaTipoE = modeloEntidad.getEntidadesHijaYResponsable(entidad_id, id_legislatura_actual, tipo);
			request.setAttribute("listaEntidadesHijaTipoE", listaEntidadesHijaTipoE);
			
			tipo = "I"; //Institutos
			List<Entidad> listaEntidadesHijaTipoI;
			listaEntidadesHijaTipoI = modeloEntidad.getEntidadesHijaYResponsable(entidad_id, id_legislatura_actual, tipo);
			request.setAttribute("listaEntidadesHijaTipoI", listaEntidadesHijaTipoI);
		}else {//Si es un organismo de nivel 1...
			//Devolver Entidades de las que es padre, con el nombre y cargo del responsable de las mismas
			List<Entidad> listaEntidadesHija;
			listaEntidadesHija = modeloEntidad.getEntidadesHijaYResponsable(entidad_id, id_legislatura_actual, "all"); //all incluye todos los tipos en un if/else del modelo
			request.setAttribute("listaEntidadesHija", listaEntidadesHija);
		}
		request.setAttribute("hasGabinete", hasGabinete);
		//Devolver nombre de la entidad de lvl 2
		entidad = modeloEntidad.getEntidadById(entidad_id);
		request.setAttribute("entidadSeleccionada", entidad);
	
		//Devolver datos del cargo y de la entidad de la que es responsable el cargo
		Entidad departamento;
		departamento = modeloEntidad.getDepartamentoById(entidad_id, 1);
		request.setAttribute("infoDepartamento", departamento);

		//Devolución
		RequestDispatcher dis = request.getRequestDispatcher("/organismo-1.jsp");
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
