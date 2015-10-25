package labbd.av.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import labbd.av.models.ClassificacaoGrupo;

public interface ClassificacaoGrupoDAO {
	public List<ClassificacaoGrupo> pesquisarA() throws SQLException;

	public List<ClassificacaoGrupo> pesquisarB() throws SQLException;

	public List<ClassificacaoGrupo> pesquisarC() throws SQLException;

	public List<ClassificacaoGrupo> pesquisarD() throws SQLException;
}
