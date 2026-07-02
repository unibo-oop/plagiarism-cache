package Controller;

import Model.Days;
import Model.ErrorException;
import Model.WarningException;

public class ValidateWarning implements ValidateWarningInterface {

	private ControllerWorkersInterface contWorkers = new ControllerWorkers();
	private Reservation cont;

	public void validateWARNING(Reservation cont) throws WarningException, ErrorException {
		this.cont = cont;

		checkHoursForDayProf();// 6 ore al giorno
		check4TimeWeekProfessor(); // più di 4 giorni a settimana,
		check4TimeWeekStudent(); // più di 4 giorni a settimana
	}

	/**
	 * this method check if professor work more of six hours in a day. if more
	 * six hours throw a warning error
	 * 
	 * @throws WarningException
	 */
	private void checkHoursForDayProf() throws WarningException {
		int i = 0;
		for (Reservation reservation : this.contWorkers.getByDay(cont.getDay())) {
			if (reservation.getPerson().toString().equals(cont.getPerson().toString())) {
				i++;
			}
		}
		if (i > 5) {
			throw new WarningException("Il prof. svolge " + i + " ore di lezione" + "\n" + "Aggiungerne un'altra?");
		}
	}

	/**
	 * this method check if professor work more of 4 day in a week. if more 4
	 * day throw a warning error
	 * 
	 * @throws WarningException
	 */
	private void check4TimeWeekProfessor() throws WarningException {
		int i = 0;
		boolean contDay = false;
		for (Days day : Days.values()) {
			for (Reservation res : this.contWorkers.getByProfessor(cont.getPerson())) {
				if (res.getDay().equals(day)) {
					contDay = true;
				}
			}
			if (contDay) {
				contDay = false;
				i++;
			}
		}
		if (i == 4) {
			throw new WarningException("Il prof. svolge " + i + " giorni di lezione" + "\n"
					+ "Aggiungerlo anche nel quinto?");
		}
	}

	/**
	 * this method check if students work more of 4 day in a week. if more 4 day
	 * throw a warning error
	 * 
	 * @throws WarningException
	 */
	private void check4TimeWeekStudent() throws WarningException {

		int i = 0;
		boolean isType = false;
		for (Days day : Days.values()) {
			for (Reservation res : this.contWorkers.getByDay(day)) {
				if (Model.Type.getSecondYears().contains(this.cont.getCourse().getType())
						&& Model.Type.getSecondYears().contains(res.getCourse().getType())) {
					isType = true;
				} else if (Model.Type.getThirdYears().contains(this.cont.getCourse().getType())
						&& Model.Type.getThirdYears().contains(res.getCourse().getType())) {
					isType = true;
				} else if (Model.Type.getFifthYears().contains(this.cont.getCourse().getType())
						&& Model.Type.getFifthYears().contains(res.getCourse().getType())) {
					isType = true;
				} else if (this.cont.getCourse().getType().toString().equals(res.getCourse().getType().toString())) {
					isType = true;
				}
			}
			if (isType) {
				isType = false;
				i++;
			}
		}
		if (i == 4) {
			throw new WarningException("Gli studenti di questo anno svolgono 4 giorni di lezione" + "\n"
					+ "Aggiungerlo anche nel quinto?");
		}

	}

}
