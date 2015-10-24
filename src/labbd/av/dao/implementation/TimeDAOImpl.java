package labbd.av.dao.implementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import labbd.av.dao.generic.GenericDAO;
import labbd.av.dao.interfaces.TimeDAO;
import labbd.av.models.Time;

public class TimeDAOImpl implements TimeDAO {
	
	Connection connection;

	public TimeDAOImpl() {
		GenericDAO gDao = new GenericDAO();
		connection = gDao.getConnection();
	}

	@Override
	public List<Time> pesquisar() throws SQLException {
		List<Time> lista = new ArrayList<Time>();
		String sql = "SELECT * FROM Times";
		PreparedStatement ps = connection.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			Time time = new Time();
			time.setCodigoTime(rs.getInt("codigoTime"));
			time.setNomeTime(rs.getString("nomeTime"));
			time.setCidade(rs.getString("cidade"));
			time.setEstadio(rs.getString("estadio"));
			lista.add(time);
		}
		ps.close();
		rs.close();
		System.out.println("Encheu a lista");
		return lista;
	}
	
	
}
