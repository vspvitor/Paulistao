package labbd.av.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import labbd.av.models.Grupo;

public interface GrupoDAO {
	
	public void carregaGrupos();
	
	public void deletaGrupos() throws SQLException;
	
	public List<Grupo> pesquisar() throws SQLException;
	
	public List<Grupo> pesquisarA() throws SQLException;
	public List<Grupo> pesquisarB() throws SQLException;
	public List<Grupo> pesquisarC() throws SQLException;
	public List<Grupo> pesquisarD() throws SQLException;
	
}
