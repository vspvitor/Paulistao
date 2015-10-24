package labbd.av.managedbeans;

import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import labbd.av.dao.implementation.JogoDAOImpl;
import labbd.av.dao.interfaces.JogoDAO;
import labbd.av.models.Jogo;

@ManagedBean
@SessionScoped
public class JogoMB2 {
	private Jogo jogo;
	private JogoDAO jogoDAO;
	private List<Jogo> jogos;
	
	
	
	public JogoMB2(){
		jogo = new Jogo();
		jogoDAO = new JogoDAOImpl();
		
		try {
			setJogos(jogoDAO.pesquisarPorData(jogo));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void pesquisarPorData(){
		try {
			setJogos(jogoDAO.pesquisarPorData(jogo));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Jogo getJogo() {
		return jogo;
	}



	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}



	public JogoDAO getJogoDAO() {
		return jogoDAO;
	}



	public void setJogoDAO(JogoDAO jogoDAO) {
		this.jogoDAO = jogoDAO;
	}



	public List<Jogo> getJogos() {
		return jogos;
	}



	public void setJogos(List<Jogo> jogos) {
		this.jogos = jogos;
	}
	
	
}
