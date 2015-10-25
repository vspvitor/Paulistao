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
import labbd.av.dao.interfaces.GrupoDAO;
import labbd.av.models.Grupo;

public class GrupoDAOImpl implements GrupoDAO {

	Connection connection;

	public GrupoDAOImpl() {
		GenericDAO gDao = new GenericDAO();
		connection = gDao.getConnection();
	}

	@Override

	// chama procedures responsáveis pelo carregamento dos grupos
	public void carregaGrupos() {
		String sql1 = "{call sp_cabecadegrupo}";

		try {
			CallableStatement cs1 = connection.prepareCall(sql1);
			cs1.execute();
			cs1.close();

			System.out.println("executou sp_cabecadegrupo");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
		}

		// sorteiaGrupo();

	}

	/*
	 * public void sorteiaGrupo(){ String sql2 = "{call sp_sorteio}"; try {
	 * CallableStatement cs2 = connection.prepareCall(sql2); cs2.execute();
	 * cs2.close();
	 * 
	 * System.out.println("executou sp_sorteio"); } catch (SQLException e) {
	 * JOptionPane.showMessageDialog(null, e.getMessage(),"Erro",
	 * JOptionPane.ERROR_MESSAGE); }
	 * 
	 * };
	 */
	//

	@Override
	public void deletaGrupos() {
		String sql = "DELETE from Grupos";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.execute();
		} catch (SQLException e) {
			System.out.println(e);
		}

	}

	@Override
	public List<Grupo> pesquisar() throws SQLException {
		List<Grupo> lista = new ArrayList<Grupo>();
		String sql = "SELECT  codigoGrupo,grupo,nomeTime,Times.codigoTime, Times.estadio FROM Times"
				+ " INNER JOIN Grupos" + " ON Times.codigoTime = Grupos.codigoTime"
				+ " ORDER BY codigoGrupo, grupo, NEWID()";
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Grupo grupo = new Grupo();
			grupo.setCodigoGrupo(rs.getInt("codigoGrupo"));
			grupo.setGrupo(rs.getString("grupo"));
			grupo.setNomeTime(rs.getString("nomeTime"));
			grupo.setCodigoTime(rs.getInt("codigoTime"));
			grupo.setEstadio(rs.getString("estadio"));
			lista.add(grupo);
		}
		ps.close();
		rs.close();
		System.out.println("Encheu a lista");
		return lista;
	}

	@Override
	public List<Grupo> pesquisarA() throws SQLException {
		List<Grupo> listaA = new ArrayList<Grupo>();
		String sql = "SELECT  codigoGrupo,grupo,nomeTime,Times.codigoTime, Times.estadio FROM Times"
				+ " INNER JOIN Grupos" + " ON Times.codigoTime = Grupos.codigoTime" + " WHERE codigoGrupo = ?"
				+ " ORDER BY codigoGrupo, grupo, NEWID()";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, 1);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Grupo grupo = new Grupo();
			grupo.setCodigoGrupo(rs.getInt("codigoGrupo"));
			grupo.setGrupo(rs.getString("grupo"));
			grupo.setNomeTime(rs.getString("nomeTime"));
			grupo.setCodigoTime(rs.getInt("codigoTime"));
			grupo.setEstadio(rs.getString("estadio"));

			listaA.add(grupo);
		}
		ps.close();
		rs.close();
		System.out.println("Encheu a lista");
		return listaA;
	}

	@Override
	public List<Grupo> pesquisarB() throws SQLException {
		List<Grupo> listaB = new ArrayList<Grupo>();
		String sql = "SELECT  codigoGrupo,grupo,nomeTime,Times.codigoTime, Times.estadio FROM Times"
				+ " INNER JOIN Grupos" + " ON Times.codigoTime = Grupos.codigoTime" + " WHERE codigoGrupo = ?"
				+ " ORDER BY codigoGrupo, grupo, NEWID()";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, 2);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Grupo grupo = new Grupo();
			grupo.setCodigoGrupo(rs.getInt("codigoGrupo"));
			grupo.setGrupo(rs.getString("grupo"));
			grupo.setNomeTime(rs.getString("nomeTime"));
			grupo.setCodigoTime(rs.getInt("codigoTime"));
			grupo.setEstadio(rs.getString("estadio"));

			listaB.add(grupo);
		}
		ps.close();
		rs.close();
		System.out.println("Encheu a lista");
		return listaB;
	}

	@Override
	public List<Grupo> pesquisarC() throws SQLException {
		List<Grupo> listaC = new ArrayList<Grupo>();
		String sql = "SELECT  codigoGrupo,grupo,nomeTime,Times.codigoTime, Times.estadio FROM Times"
				+ " INNER JOIN Grupos" + " ON Times.codigoTime = Grupos.codigoTime" + " WHERE codigoGrupo = ?"
				+ " ORDER BY codigoGrupo, grupo, NEWID()";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, 3);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Grupo grupo = new Grupo();
			grupo.setCodigoGrupo(rs.getInt("codigoGrupo"));
			grupo.setGrupo(rs.getString("grupo"));
			grupo.setNomeTime(rs.getString("nomeTime"));
			grupo.setCodigoTime(rs.getInt("codigoTime"));
			grupo.setEstadio(rs.getString("estadio"));

			listaC.add(grupo);
		}
		ps.close();
		rs.close();
		System.out.println("Encheu a lista");
		return listaC;
	}

	@Override
	public List<Grupo> pesquisarD() throws SQLException {
		List<Grupo> listaD = new ArrayList<Grupo>();
		String sql = "SELECT  codigoGrupo,grupo,nomeTime,Times.codigoTime, Times.estadio FROM Times"
				+ " INNER JOIN Grupos" + " ON Times.codigoTime = Grupos.codigoTime" + " WHERE codigoGrupo = ?"
				+ " ORDER BY codigoGrupo, grupo, NEWID()";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setInt(1, 4);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Grupo grupo = new Grupo();
			grupo.setCodigoGrupo(rs.getInt("codigoGrupo"));
			grupo.setGrupo(rs.getString("grupo"));
			grupo.setNomeTime(rs.getString("nomeTime"));
			grupo.setCodigoTime(rs.getInt("codigoTime"));
			grupo.setEstadio(rs.getString("estadio"));

			listaD.add(grupo);
		}
		ps.close();
		rs.close();
		System.out.println("Encheu a lista");
		return listaD;
	}

}
