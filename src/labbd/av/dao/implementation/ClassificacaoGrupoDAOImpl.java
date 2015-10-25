package labbd.av.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import labbd.av.dao.generic.GenericDAO;
import labbd.av.dao.interfaces.ClassificacaoGrupoDAO;
import labbd.av.models.ClassificacaoGrupo;

public class ClassificacaoGrupoDAOImpl implements ClassificacaoGrupoDAO {

	Connection connection;

	public ClassificacaoGrupoDAOImpl() {
		GenericDAO gDao = new GenericDAO();
		connection = gDao.getConnection();
	}

	@Override
	public List<ClassificacaoGrupo> pesquisarA() throws SQLException {
		List<ClassificacaoGrupo> lista = new ArrayList<ClassificacaoGrupo>();
		String sql = "SELECT * FROM fn_grupos(?)" + " ORDER BY pontos DESC," + " golsMarcados DESC,"
				+ " saldoGols DESC";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, 1);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			ClassificacaoGrupo classificacaoGrupo = new ClassificacaoGrupo();
			classificacaoGrupo.setNomeTime(rs.getString("nomeTime"));
			classificacaoGrupo.setNumeroJogosDisputados(rs.getInt("numeroJogosDisputados"));
			classificacaoGrupo.setVitorias(rs.getInt("vitorias"));
			classificacaoGrupo.setEmpates(rs.getInt("empates"));
			classificacaoGrupo.setDerrotas(rs.getInt("derrotas"));
			classificacaoGrupo.setGolsMarcados(rs.getInt("golsMarcados"));
			classificacaoGrupo.setGolsSofridos(rs.getInt("golsSofridos"));
			classificacaoGrupo.setSaldoGols(rs.getInt("saldoGols"));
			classificacaoGrupo.setPontos(rs.getInt("pontos"));

			lista.add(classificacaoGrupo);
		}
		return lista;
	}

	@Override
	public List<ClassificacaoGrupo> pesquisarB() throws SQLException {
		List<ClassificacaoGrupo> lista = new ArrayList<ClassificacaoGrupo>();
		String sql = "SELECT * FROM fn_grupos(?)" + " ORDER BY pontos DESC," + " golsMarcados DESC,"
				+ " saldoGols DESC";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, 2);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			ClassificacaoGrupo classificacaoGrupo = new ClassificacaoGrupo();
			classificacaoGrupo.setNomeTime(rs.getString("nomeTime"));
			classificacaoGrupo.setNumeroJogosDisputados(rs.getInt("numeroJogosDisputados"));
			classificacaoGrupo.setVitorias(rs.getInt("vitorias"));
			classificacaoGrupo.setEmpates(rs.getInt("empates"));
			classificacaoGrupo.setDerrotas(rs.getInt("derrotas"));
			classificacaoGrupo.setGolsMarcados(rs.getInt("golsMarcados"));
			classificacaoGrupo.setGolsSofridos(rs.getInt("golsSofridos"));
			classificacaoGrupo.setSaldoGols(rs.getInt("saldoGols"));
			classificacaoGrupo.setPontos(rs.getInt("pontos"));

			lista.add(classificacaoGrupo);
		}
		return lista;
	}

	@Override
	public List<ClassificacaoGrupo> pesquisarC() throws SQLException {
		List<ClassificacaoGrupo> lista = new ArrayList<ClassificacaoGrupo>();
		String sql = "SELECT * FROM fn_grupos(?)" + " ORDER BY pontos DESC," + " golsMarcados DESC,"
				+ " saldoGols DESC";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, 3);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			ClassificacaoGrupo classificacaoGrupo = new ClassificacaoGrupo();
			classificacaoGrupo.setNomeTime(rs.getString("nomeTime"));
			classificacaoGrupo.setNumeroJogosDisputados(rs.getInt("numeroJogosDisputados"));
			classificacaoGrupo.setVitorias(rs.getInt("vitorias"));
			classificacaoGrupo.setEmpates(rs.getInt("empates"));
			classificacaoGrupo.setDerrotas(rs.getInt("derrotas"));
			classificacaoGrupo.setGolsMarcados(rs.getInt("golsMarcados"));
			classificacaoGrupo.setGolsSofridos(rs.getInt("golsSofridos"));
			classificacaoGrupo.setSaldoGols(rs.getInt("saldoGols"));
			classificacaoGrupo.setPontos(rs.getInt("pontos"));

			lista.add(classificacaoGrupo);
		}
		return lista;
	}

	@Override
	public List<ClassificacaoGrupo> pesquisarD() throws SQLException {
		List<ClassificacaoGrupo> lista = new ArrayList<ClassificacaoGrupo>();
		String sql = "SELECT * FROM fn_grupos(?)"
				+ " ORDER BY pontos DESC,"
				+ " golsMarcados DESC,"
				+ " saldoGols DESC";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, 4);
		ResultSet rs = ps.executeQuery();
		while(rs.next()){
			ClassificacaoGrupo classificacaoGrupo = new ClassificacaoGrupo();
			classificacaoGrupo.setNomeTime(rs.getString("nomeTime"));
			classificacaoGrupo.setNumeroJogosDisputados(rs.getInt("numeroJogosDisputados"));
			classificacaoGrupo.setVitorias(rs.getInt("vitorias"));
			classificacaoGrupo.setEmpates(rs.getInt("empates"));
			classificacaoGrupo.setDerrotas(rs.getInt("derrotas"));
			classificacaoGrupo.setGolsMarcados(rs.getInt("golsMarcados"));
			classificacaoGrupo.setGolsSofridos(rs.getInt("golsSofridos"));
			classificacaoGrupo.setSaldoGols(rs.getInt("saldoGols"));
			classificacaoGrupo.setPontos(rs.getInt("pontos"));
			
			lista.add(classificacaoGrupo);
		}
		return lista;
		}
}
