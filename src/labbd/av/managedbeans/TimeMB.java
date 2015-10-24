package labbd.av.managedbeans;

import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import labbd.av.dao.implementation.TimeDAOImpl;
import labbd.av.dao.interfaces.TimeDAO;
import labbd.av.models.Time;

@ManagedBean
@SessionScoped
public class TimeMB {
	private Time timeAtual;
	private TimeDAO timeDAO;
	private List<Time> times;

	public TimeMB() {
		timeAtual = new Time();
		timeDAO = new TimeDAOImpl();

		try {
			setTimes(timeDAO.pesquisar());
			System.out.println("try ok");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Time> getTimes() {
		return times;
	}

	public void setTimes(List<Time> times) {
		this.times = times;
	}

	public Time getTimeAtual() {
		return timeAtual;
	}
	
	public TimeDAO getTimeDAO() {
		return timeDAO;
	}

	public void setTimeDAO(TimeDAO timeDAO) {
		this.timeDAO = timeDAO;
	}

	public void setTimeAtual(Time timeAtual) {
		this.timeAtual = timeAtual;
	}

}
