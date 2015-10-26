package labbd.av.managedbeans;

import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import labbd.av.dao.implementation.CampeonatoDAOImpl;
import labbd.av.dao.interfaces.CampeonatoDAO;
import labbd.av.models.Campeonato;

@ManagedBean
@SessionScoped
public class CampeonatoMB {
	private Campeonato campeonato;
	private CampeonatoDAO campeonatoDAO;
	private List<Campeonato> campeonatos;
	
	
	public CampeonatoMB(){
		campeonato = new Campeonato();
		campeonatoDAO = new CampeonatoDAOImpl();
		
		try {
			setCampeonatos(campeonatoDAO.chamarTabelaCampeonato());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

	public Campeonato getCampeonato() {
		return campeonato;
	}

	public void setCampeonato(Campeonato campeonato) {
		this.campeonato = campeonato;
	}

	public CampeonatoDAO getCampeonatoDAO() {
		return campeonatoDAO;
	}

	public void setCampeonatoDAO(CampeonatoDAO campeonatoDAO) {
		this.campeonatoDAO = campeonatoDAO;
	}

	public List<Campeonato> getCampeonatos() {
		return campeonatos;
	}

	public void setCampeonatos(List<Campeonato> campeonatos) {
		this.campeonatos = campeonatos;
	}

	
	
}
