package labbd.av.managedbeans;

import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import labbd.av.dao.implementation.ClassificacaoGrupoDAOImpl;
import labbd.av.dao.interfaces.ClassificacaoGrupoDAO;
import labbd.av.models.ClassificacaoGrupo;

@ManagedBean
@SessionScoped
public class CampeonatoGruposMB {
	private ClassificacaoGrupo classificacaoGrupoA;
	private ClassificacaoGrupo classificacaoGrupoB;
	private ClassificacaoGrupo classificacaoGrupoC;
	private ClassificacaoGrupo classificacaoGrupoD;
	
	private ClassificacaoGrupoDAO classificacaoGrupoDAO;
	
	private List<ClassificacaoGrupo> classificacaoTimesA;
	private List<ClassificacaoGrupo> classificacaoTimesB;
	private List<ClassificacaoGrupo> classificacaoTimesC;
	private List<ClassificacaoGrupo> classificacaoTimesD;
	
	public CampeonatoGruposMB(){
		classificacaoGrupoA = new ClassificacaoGrupo();
		classificacaoGrupoB = new ClassificacaoGrupo();
		classificacaoGrupoC = new ClassificacaoGrupo();
		classificacaoGrupoD = new ClassificacaoGrupo();
		classificacaoGrupoDAO = new ClassificacaoGrupoDAOImpl();	
		
		try {
			setClassificacaoTimesA(classificacaoGrupoDAO.pesquisarA());
			setClassificacaoTimesB(classificacaoGrupoDAO.pesquisarB());
			setClassificacaoTimesC(classificacaoGrupoDAO.pesquisarC());
			setClassificacaoTimesD(classificacaoGrupoDAO.pesquisarD());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public ClassificacaoGrupo getClassificacaoGrupoA() {
		return classificacaoGrupoA;
	}

	public void setClassificacaoGrupoA(ClassificacaoGrupo classificacaoGrupoA) {
		this.classificacaoGrupoA = classificacaoGrupoA;
	}

	public ClassificacaoGrupo getClassificacaoGrupoB() {
		return classificacaoGrupoB;
	}

	public void setClassificacaoGrupoB(ClassificacaoGrupo classificacaoGrupoB) {
		this.classificacaoGrupoB = classificacaoGrupoB;
	}

	public ClassificacaoGrupo getClassificacaoGrupoC() {
		return classificacaoGrupoC;
	}

	public void setClassificacaoGrupoC(ClassificacaoGrupo classificacaoGrupoC) {
		this.classificacaoGrupoC = classificacaoGrupoC;
	}

	public ClassificacaoGrupo getClassificacaoGrupoD() {
		return classificacaoGrupoD;
	}

	public void setClassificacaoGrupoD(ClassificacaoGrupo classificacaoGrupoD) {
		this.classificacaoGrupoD = classificacaoGrupoD;
	}

	public ClassificacaoGrupoDAO getClassificacaoGrupoDAO() {
		return classificacaoGrupoDAO;
	}

	public void setClassificacaoGrupoDAO(ClassificacaoGrupoDAO classificacaoGrupoDAO) {
		this.classificacaoGrupoDAO = classificacaoGrupoDAO;
	}

	public List<ClassificacaoGrupo> getClassificacaoTimesA() {
		return classificacaoTimesA;
	}

	public void setClassificacaoTimesA(List<ClassificacaoGrupo> classificacaoTimesA) {
		this.classificacaoTimesA = classificacaoTimesA;
	}

	public List<ClassificacaoGrupo> getClassificacaoTimesB() {
		return classificacaoTimesB;
	}

	public void setClassificacaoTimesB(List<ClassificacaoGrupo> classificacaoTimesB) {
		this.classificacaoTimesB = classificacaoTimesB;
	}

	public List<ClassificacaoGrupo> getClassificacaoTimesC() {
		return classificacaoTimesC;
	}

	public void setClassificacaoTimesC(List<ClassificacaoGrupo> classificacaoTimesC) {
		this.classificacaoTimesC = classificacaoTimesC;
	}

	public List<ClassificacaoGrupo> getClassificacaoTimesD() {
		return classificacaoTimesD;
	}

	public void setClassificacaoTimesD(List<ClassificacaoGrupo> classificacaoTimesD) {
		this.classificacaoTimesD = classificacaoTimesD;
	}
	
	
}
