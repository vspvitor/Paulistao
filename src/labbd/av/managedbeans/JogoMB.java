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
public class JogoMB {
	private Jogo jogo;
	private JogoDAO jogoDAO;
	private List<Jogo> jogos;
	
	
	
	public JogoMB(){
		jogo = new Jogo();
		jogoDAO = new JogoDAOImpl();
		
		try {
			setJogos(jogoDAO.pesquisar());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void carregaJogos(){
		
		try {
			jogoDAO.carregaJogos();
			setJogos(jogoDAO.pesquisar());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void deletaJogos(){
		try {
			jogoDAO.deletaJogos();
			setJogos(jogoDAO.pesquisar());
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
