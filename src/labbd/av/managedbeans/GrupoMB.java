package labbd.av.managedbeans;

import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import labbd.av.dao.implementation.GrupoDAOImpl;
import labbd.av.dao.interfaces.GrupoDAO;
import labbd.av.models.Grupo;

@ManagedBean
@SessionScoped
public class GrupoMB {

	private Grupo GrupoA;
	private Grupo GrupoB;
	private Grupo GrupoC;
	private Grupo GrupoD;
	private GrupoDAO grupoDAO;
	private List<Grupo> timesA;
	private List<Grupo> timesB;
	private List<Grupo> timesC;
	private List<Grupo> timesD;
	
	
	public GrupoMB(){
		GrupoA = new Grupo();
		GrupoB = new Grupo();
		GrupoC = new Grupo();
		GrupoD = new Grupo();
		grupoDAO = new GrupoDAOImpl();
		
		try {
			setTimesA(grupoDAO.pesquisarA());
			setTimesB(grupoDAO.pesquisarB());
			setTimesC(grupoDAO.pesquisarC());
			setTimesD(grupoDAO.pesquisarD());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void carregaGrupos(){
		
		try {
			grupoDAO.carregaGrupos();
			setTimesA(grupoDAO.pesquisarA());
			setTimesB(grupoDAO.pesquisarB());
			setTimesC(grupoDAO.pesquisarC());
			setTimesD(grupoDAO.pesquisarD());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void deletaGrupos(){
		try {
			grupoDAO.deletaGrupos();
			setTimesA(grupoDAO.pesquisarA());
			setTimesB(grupoDAO.pesquisarB());
			setTimesC(grupoDAO.pesquisarC());
			setTimesD(grupoDAO.pesquisarD());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public Grupo getGrupoA() {
		return GrupoA;
	}


	public void setGrupoA(Grupo grupoA) {
		GrupoA = grupoA;
	}


	public Grupo getGrupoB() {
		return GrupoB;
	}


	public void setGrupoB(Grupo grupoB) {
		GrupoB = grupoB;
	}


	public Grupo getGrupoC() {
		return GrupoC;
	}


	public void setGrupoC(Grupo grupoC) {
		GrupoC = grupoC;
	}


	public Grupo getGrupoD() {
		return GrupoD;
	}


	public void setGrupoD(Grupo grupoD) {
		GrupoD = grupoD;
	}


	public GrupoDAO getGrupoDAO() {
		return grupoDAO;
	}


	public void setGrupoDAO(GrupoDAO grupoDAO) {
		this.grupoDAO = grupoDAO;
	}


	public List<Grupo> getTimesA() {
		return timesA;
	}


	public void setTimesA(List<Grupo> timesA) {
		this.timesA = timesA;
	}


	public List<Grupo> getTimesB() {
		return timesB;
	}


	public void setTimesB(List<Grupo> timesB) {
		this.timesB = timesB;
	}


	public List<Grupo> getTimesC() {
		return timesC;
	}


	public void setTimesC(List<Grupo> timesC) {
		this.timesC = timesC;
	}


	public List<Grupo> getTimesD() {
		return timesD;
	}


	public void setTimesD(List<Grupo> timesD) {
		this.timesD = timesD;
	}


	
	
	
	
}
