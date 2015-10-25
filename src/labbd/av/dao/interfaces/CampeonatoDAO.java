package labbd.av.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import labbd.av.models.Campeonato;

public interface CampeonatoDAO {

	public List<Campeonato> chamarTabelaCampeonato() throws SQLException;
}
