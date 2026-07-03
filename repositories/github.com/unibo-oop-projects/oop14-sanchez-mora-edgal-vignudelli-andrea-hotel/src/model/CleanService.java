package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class CleanService implements Serializable{
	private static final long serialVersionUID = 966323382345698672L;
	private static CleanService myInstance;
	public static final String fileName = "CleanService.txt";

	private CleanService() {
	}

	// DA CAMBIARE CON FILESTREAM/FILEWRITER
	public static String getCleanService(List<Room> rooms) {
		LocalDate today = LocalDate.now();
		StringBuilder sb = new StringBuilder();
		sb.append("[DATA]: " + today.toString() + System.lineSeparator());
		for (Room r : rooms) {
			if (r.isBusy()) {
				if (r.getActualCustomer().getBooking().getEndDate().equals(today)) {
					sb.append(r.toString() + "[PULIZIA A FONDO]");
					// PULIZIA A FONDO ( ROOM NUMBER AND TYPE OF CLEANING)
					// MARKER PRIORITY IN QUALCHE MODO PER IDENTIFICARE QUELLE
					// ROOM CHE HANNO UN CAMBIO DI CUSTOMERS IN GIORNATA
					// E NON UNA SEMPLICE LIBERAZIONE STANZA
					Customer next = r.getNextCustomer();
					if (next.getBooking().getStartDate().equals(today)) {
						sb.append("SI Priorità"+System.lineSeparator());
					} else {
						sb.append("NO Priorità"+System.lineSeparator());
					}
					if (next.getBooking().getGuestsList().size() < r.getActualCustomer().getBooking().getGuestsList()
							.size()) {
						sb.append("TOGLIERE " + (r.getActualCustomer().getBooking().getGuestsList().size()
								- next.getBooking().getGuestsList().size()) + " LETTO/I"+System.lineSeparator()+System.lineSeparator());
					} else if (next.getBooking().getGuestsList().size() < r.getActualCustomer().getBooking()
							.getGuestsList().size()) {
						sb.append(
								"AGGIUNGERE "
										+ (next.getBooking().getGuestsList().size()
												- r.getActualCustomer().getBooking().getGuestsList().size())
										+ " LETTO/I"+System.lineSeparator());
					}
				} else {
					sb.append(r.toString()+ "[PULIZIA GIORNALIERA]"+System.lineSeparator()+System.lineSeparator());
				}
			} else {
				sb.append(r.toString()+ "[PULIZIA NON NECESSARIA]"+System.lineSeparator()+System.lineSeparator());
			}
		}
		return new String(sb);
	}

	public static CleanService getInstance() {
		if (myInstance == null) {
			myInstance = new CleanService();
		}
		return myInstance;
	}
	
	public static String getFileName() {
		return fileName;
	}

}
