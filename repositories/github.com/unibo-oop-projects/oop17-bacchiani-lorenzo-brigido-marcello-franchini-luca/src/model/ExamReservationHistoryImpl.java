package model;

import java.io.Serializable;
import java.time.temporal.ChronoField;
import java.util.Optional;
import java.util.Set;


public class ExamReservationHistoryImpl implements ExamReservationHistory, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private static final String PATH = System.getProperty("user.home") + System.getProperty("file.separator");
	private static final String FILENAME =  PATH + "prenotazioni.dat";
	private final Set<ExamReservation> allBooking;
	private final FileManager<ExamReservation> fm;


	public ExamReservationHistoryImpl() {
		fm = new FileManagerImpl<>();
		this.allBooking = fm.load(FILENAME);
	}

	public void addBooking(final ExamReservation p) {
		this.foundException(p);
		this.allBooking.add(p);
		this.insertInFile();	
	}

	public Set<ExamReservation> getAllBooking() {
		return this.allBooking;
	}

	public void insertInFile() {
		fm.save(FILENAME, allBooking);
	}

	private void foundException(final ExamReservation p) {
		if (!this.allBooking.stream().filter(x -> x.getDate().get(ChronoField.DAY_OF_MONTH) == p.getDate().get(ChronoField.DAY_OF_MONTH))
				.filter(x -> x.getDate().get(ChronoField.MONTH_OF_YEAR) 
						== p.getDate().get(ChronoField.MONTH_OF_YEAR))
				.filter(x -> x.getDate().get(ChronoField.YEAR) 
						== p.getDate().get(ChronoField.YEAR)).findFirst().equals(Optional.empty())) {
			//stesso giorno stesso esame stesso paziente
			if (!this.allBooking.stream()
					.filter(x -> x.getPatient().getFiscalCode().equals(p.getPatient().getFiscalCode()))
					.filter(x -> x.getExam().getExamName().equals(p.getExam().getExamName()))
					.findFirst().equals(Optional.empty())) {
				throw new IllegalArgumentException("Patient has already booked this exam's type today!");
				// stesso giorno stessa ora esami diversi stesso paziente
			} else if (!this.allBooking.stream()
					.filter(x -> x.getPatient().getFiscalCode().equals(p.getPatient().getFiscalCode()))
					.filter(x -> x.getHour().getHour() 
							==  p.getHour().getHour())
					.filter(x -> x.getHour().getMinute() 
							== p.getHour().getMinute())
					.findFirst().equals(Optional.empty())) {
				throw new IllegalArgumentException("Please change date or hour!");
				// stesso giorno stessa ora
			} else if (!this.allBooking.stream()
					.filter(x -> x.getHour().getHour()
							== p.getHour().getHour())
					.filter(x -> x.getHour().getMinute()
							== p.getHour().getMinute())
					.filter(x -> x.getStaff().getFiscalCode().equals(p.getStaff().getFiscalCode()))
					.findFirst().equals(Optional.empty())) {
				throw new IllegalArgumentException("A doctor can't take two exams at the same time!");	
			}

		}
	}


}
