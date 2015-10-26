package labbd.av.managedbeans;

import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import labbd.av.dao.implementation.JogoDAOImpl;
import labbd.av.dao.interfaces.JogoDAO;
import labbd.av.models.Jogo;

@ManagedBean
@ViewScoped
public class QuartasJogoMB {
	private Jogo jogo1;
	private Jogo jogo2;
	private Jogo jogo3;
	private Jogo jogo4;
	private JogoDAO jogoDAO;

	private List<Jogo> jogos1;
	private List<Jogo> jogos2;
	private List<Jogo> jogos3;
	private List<Jogo> jogos4;

	public QuartasJogoMB() {
		jogo1 = new Jogo();
		jogo2 = new Jogo();
		jogo3 = new Jogo();
		jogo4 = new Jogo();

		jogoDAO = new JogoDAOImpl();

		try {
			setJogos1(jogoDAO.Quartas1());
			setJogos2(jogoDAO.Quartas2());
			setJogos3(jogoDAO.Quartas3());
			setJogos4(jogoDAO.Quartas4());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Jogo getJogo1() {
		return jogo1;
	}

	public void setJogo1(Jogo jogo1) {
		this.jogo1 = jogo1;
	}

	public Jogo getJogo2() {
		return jogo2;
	}

	public void setJogo2(Jogo jogo2) {
		this.jogo2 = jogo2;
	}

	public Jogo getJogo3() {
		return jogo3;
	}

	public void setJogo3(Jogo jogo3) {
		this.jogo3 = jogo3;
	}

	public Jogo getJogo4() {
		return jogo4;
	}

	public void setJogo4(Jogo jogo4) {
		this.jogo4 = jogo4;
	}

	public JogoDAO getJogoDAO() {
		return jogoDAO;
	}

	public void setJogoDAO(JogoDAO jogoDAO) {
		this.jogoDAO = jogoDAO;
	}

	public List<Jogo> getJogos1() {
		return jogos1;
	}

	public void setJogos1(List<Jogo> jogos1) {
		this.jogos1 = jogos1;
	}

	public List<Jogo> getJogos2() {
		return jogos2;
	}

	public void setJogos2(List<Jogo> jogos2) {
		this.jogos2 = jogos2;
	}

	public List<Jogo> getJogos3() {
		return jogos3;
	}

	public void setJogos3(List<Jogo> jogos3) {
		this.jogos3 = jogos3;
	}

	public List<Jogo> getJogos4() {
		return jogos4;
	}

	public void setJogos4(List<Jogo> jogos4) {
		this.jogos4 = jogos4;
	}

}

