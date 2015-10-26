package labbd.av.dao.implementation;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import labbd.av.dao.generic.GenericDAO;
import labbd.av.dao.interfaces.JogoDAO;
import labbd.av.models.Jogo;

public class JogoDAOImpl implements JogoDAO {

	Connection connection;

	public JogoDAOImpl() {
		GenericDAO gDao = new GenericDAO();
		connection = gDao.getConnection();
	}

	@Override
	public void carregaJogos() {
		String sql1 = "{call sp_gerajogos}";

		try {
			CallableStatement cs1 = connection.prepareCall(sql1);
			cs1.execute();
			cs1.close();

			System.out.println("executou sp_gerajogos");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}

	}

	@Override
	public void deletaJogos() throws SQLException {
		String sql = "DELETE from Jogos";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.execute();
		ps.close();
	}

	@Override
	public List<Jogo> pesquisar() throws SQLException {
		List<Jogo> lista = new ArrayList<Jogo>();
		String sql = "SELECT codigoJogo,T1.nomeTime AS Time_1, golsTimeA AS Gols_time_1,"
				+ " golsTimeB AS Gols_time_2, T2.nomeTime AS Time_2,"
				+ " CONVERT(CHAR(10), data, 103) AS Data_da_partida" + " FROM Jogos" + " INNER JOIN Times AS T1"
				+ " ON T1.codigoTime = Jogos.codigoTimeA" + " INNER JOIN Times AS T2"
				+ " ON T2.codigoTime = Jogos.codigoTimeB" + " ORDER BY data";
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Jogo jogo = new Jogo();
			jogo.setCodigoJogo(rs.getInt("codigoJogo"));
			jogo.setDatat(rs.getString("Data_da_partida"));
			jogo.setNomeTimeA(rs.getString("Time_1"));
			jogo.setGolsTimeA(rs.getInt("Gols_time_1"));
			jogo.setGolsTimeB(rs.getInt("Gols_time_2"));
			jogo.setNomeTimeB(rs.getString("Time_2"));

			lista.add(jogo);
		}
		ps.close();
		rs.close();
		System.out.println("Encheu a lista");
		return lista;
	}

	@Override
	public List<Jogo> pesquisarPorData(Jogo jogoData) throws SQLException {
		List<Jogo> lista = new ArrayList<Jogo>();
		String sql = "SELECT codigoJogo,T1.nomeTime AS Time_1, golsTimeA AS Gols_time_1,"
				+ "golsTimeB AS Gols_time_2, T2.nomeTime AS Time_2,"
				+ " CONVERT(CHAR(10), data, 103) AS Data_da_partida" + " FROM Jogos" + " INNER JOIN Times AS T1"
				+ " ON T1.codigoTime = Jogos.codigoTimeA" + " INNER JOIN Times AS T2"
				+ " ON T2.codigoTime = Jogos.codigoTimeB" + " WHERE Jogos.data = ? " + " ORDER BY data";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, jogoData.getDatat());
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Jogo jogo = new Jogo();
			jogo.setCodigoJogo(rs.getInt("codigoJogo"));
			jogo.setDatat(rs.getString("Data_da_partida"));
			jogo.setNomeTimeA(rs.getString("Time_1"));
			jogo.setGolsTimeA(rs.getInt("Gols_time_1"));
			jogo.setGolsTimeB(rs.getInt("Gols_time_2"));
			jogo.setNomeTimeB(rs.getString("Time_2"));
			System.out.println("preenche a lista");
			lista.add(jogo);
		}
		ps.close();
		rs.close();
		System.out.println("Encheu a lista");
		return lista;
	}

	@Override
	public List<Jogo> carregaDatas() throws SQLException {
		List<Jogo> lista = new ArrayList<Jogo>();
		String sql = "SELECT" + " CONVERT(CHAR(10), data, 103) AS Data_da_partida,data" + " FROM Jogos"
				+ " INNER JOIN Times AS T1" + " ON T1.codigoTime = Jogos.codigoTimeA" + " INNER JOIN Times AS T2"
				+ " ON T2.codigoTime = Jogos.codigoTimeB" + " GROUP BY data" + " ORDER BY data";
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Jogo jogo = new Jogo();
			jogo.setDatat(rs.getString("Data_da_partida"));
			jogo.setData(rs.getDate("data"));
			System.out.println("preenche a lista");
			lista.add(jogo);
		}
		ps.close();
		rs.close();
		System.out.println("Encheu a lista");
		return lista;
	}

	// av2
	@Override
	public void atualizaGols(Jogo jogo) throws SQLException {
		String sql = "UPDATE Jogos SET " + "golsTimeA = ?," + "golsTimeB = ? " + " WHERE codigoJogo = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, jogo.getGolsTimeA());
		ps.setInt(2, jogo.getGolsTimeB());
		ps.setInt(3, jogo.getCodigoJogo());

		ps.execute();
		ps.close();

	}

	// av2
	@Override
	public List<Jogo> Quartas1() throws SQLException {
		List<Jogo> lista = new ArrayList<Jogo>();
		String sql = "{call sp_quartas 1, 2}";
		CallableStatement cs = connection.prepareCall(sql);

		ResultSet rs = cs.executeQuery();
		while (rs.next()) {
			Jogo jogo = new Jogo();
			jogo.setNomeTimeA(rs.getString("nomeTime1"));
			jogo.setNomeTimeB(rs.getString("nomeTime2"));

			lista.add(jogo);
		}
		System.out.println("encheu quartas 1");
		rs.close();
		cs.close();
		return lista;

	}

	@Override
	public List<Jogo> Quartas2() throws SQLException {
		List<Jogo> lista = new ArrayList<Jogo>();
		String sql = "{call sp_quartas 2, 1}";
		CallableStatement cs = connection.prepareCall(sql);

		ResultSet rs = cs.executeQuery();
		while (rs.next()) {
			Jogo jogo = new Jogo();
			jogo.setNomeTimeA(rs.getString("nomeTime1"));
			jogo.setNomeTimeB(rs.getString("nomeTime2"));

			lista.add(jogo);
		}
		System.out.println("encheu quartas 2");
		rs.close();
		cs.close();
		return lista;

	}

	@Override
	public List<Jogo> Quartas3() throws SQLException {
		List<Jogo> lista = new ArrayList<Jogo>();
		String sql = "{call sp_quartas 3, 4}";
		CallableStatement cs = connection.prepareCall(sql);

		ResultSet rs = cs.executeQuery();
		while (rs.next()) {
			Jogo jogo = new Jogo();
			jogo.setNomeTimeA(rs.getString("nomeTime1"));
			jogo.setNomeTimeB(rs.getString("nomeTime2"));

			lista.add(jogo);
		}
		System.out.println("encheu quartas 3");
		rs.close();
		cs.close();
		return lista;
	}

	@Override
	public List<Jogo> Quartas4() throws SQLException {
		List<Jogo> lista = new ArrayList<Jogo>();
		String sql = "{call sp_quartas 4, 3}";
		CallableStatement cs = connection.prepareCall(sql);

		ResultSet rs = cs.executeQuery();
		while (rs.next()) {
			Jogo jogo = new Jogo();
			jogo.setNomeTimeA(rs.getString("nomeTime1"));
			jogo.setNomeTimeB(rs.getString("nomeTime2"));

			lista.add(jogo);
		}
		System.out.println("encheu quartas 4");
		rs.close();
		cs.close();
		return lista;

	}

}
