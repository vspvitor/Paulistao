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
	private JogoDAO jogoDAOdata;
	private List<Jogo> jogos;
	private List<Jogo> jogoDatas;
	
	
	
	public JogoMB2(){
		setJogo(new Jogo());
		jogoDAO = new JogoDAOImpl();
		jogoDAOdata = new JogoDAOImpl();
		
		try {
			setJogoDatas(jogoDAOdata.carregaDatas());
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
	
	public void editar(Jogo jogo){
		this.jogo = jogo;
		setJogo(jogo);
		}
	
	public void confirmaEditar(){
		try {
			jogoDAO.atualizaGols(getJogo());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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

	public JogoDAO getJogoDAOdata() {
		return jogoDAOdata;
	}

	public void setJogoDAOdata(JogoDAO jogoDAOdata) {
		this.jogoDAOdata = jogoDAOdata;
	}

	public List<Jogo> getJogoDatas() {
		return jogoDatas;
	}

	public void setJogoDatas(List<Jogo> jogodatas) {
		this.jogoDatas = jogodatas;
	}
	
	
}
