package labbd.av.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import labbd.av.dao.generic.GenericDAO;
import labbd.av.dao.interfaces.CampeonatoDAO;
import labbd.av.models.Campeonato;

public class CampeonatoDAOImpl implements CampeonatoDAO {

	Connection connection;

	public CampeonatoDAOImpl() {
		GenericDAO gDao = new GenericDAO();
		connection = gDao.getConnection();
	}

	@Override
	public List<Campeonato> chamarTabelaCampeonato() throws SQLException {
		List<Campeonato> lista = new ArrayList<Campeonato>();
		
		String sql = "SELECT * FROM fn_campeonato() ORDER BY pontos DESC, vitorias DESC, golsMarcados , saldoGols DESC";
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Campeonato campeonato = new Campeonato();
			campeonato.setNomeTime(rs.getString("nomeTime"));
			campeonato.setNumeroJogosDisputados(rs.getInt("numeroJogosDisputados"));
			campeonato.setVitorias(rs.getInt("vitorias"));
			campeonato.setEmpates(rs.getInt("empates"));
			campeonato.setDerrotas(rs.getInt("derrotas"));
			campeonato.setGolsMarcados(rs.getInt("golsMarcados"));
			campeonato.setGolsSofridos(rs.getInt("golsSofridos"));
			campeonato.setSaldoGols(rs.getInt("saldoGols"));
			campeonato.setPontos(rs.getInt("pontos"));
			
			lista.add(campeonato);
		}
		ps.close();
		rs.close();
		System.out.println("Encheu a lista de campeonato");
		return lista;
	}

}
