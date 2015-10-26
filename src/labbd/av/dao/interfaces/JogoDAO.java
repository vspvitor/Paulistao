package labbd.av.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import labbd.av.models.Jogo;

public interface JogoDAO {

	public void carregaJogos();

	public void deletaJogos() throws SQLException;

	public List<Jogo> pesquisar() throws SQLException;

	public List<Jogo> pesquisarPorData(Jogo jogo) throws SQLException;

	List<Jogo> carregaDatas() throws SQLException;

	public void atualizaGols(Jogo jogo) throws SQLException;

	public List<Jogo> Quartas1() throws SQLException;

	public List<Jogo> Quartas2() throws SQLException;

	public List<Jogo> Quartas3() throws SQLException;

	public List<Jogo> Quartas4() throws SQLException;

}
