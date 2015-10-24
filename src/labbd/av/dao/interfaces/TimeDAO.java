package labbd.av.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import labbd.av.models.Time;

public interface TimeDAO {
	public List<Time> pesquisar() throws SQLException;
}
