package model;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import model.domain.ObjectFactory;
import model.domain.Reservation;
import model.domain.Reservations;
import util.ApplicationConfiguration;
import util.Utilities;

/**
 * Implements the reservation backend service by using a XML file based
 * solution.
 * 
 * Reservations are persisted using file system. The format is XML.
 *
 */
public class XMLReservationService implements ReservationService {

	private static Logger logger = Logger.getLogger("model");

	public XMLReservationService() {
		File file = null;
		try {
			file = new File(System.getProperty("user.dir") + ApplicationConfiguration.RESERVATION_FILE);
			if (file.length() == 0) {
				file.createNewFile();
				PrintWriter writer = new PrintWriter(file);
				writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
				writer.println("<Reservations xmlns=\"http://www.example.org/Reservations\">");
				writer.println("</Reservations>");
				writer.close();
			}
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getStackTrace().toString());
		}
	}

	@Override
	public void newReservation(String name, String email, String phone, int table, Date date, int numberOfPersons,
			String menu) {
		Reservation reservation = new Reservation();
		reservation.setName(name);
		reservation.setEmail(email);
		reservation.setPhone(phone);
		reservation.setTable(new BigInteger(String.valueOf(table)));
		reservation.setDate(Utilities.getXmlDate(date));
		reservation.setNumberOfPersons(new BigInteger(String.valueOf(numberOfPersons)));
		reservation.setMenu(menu);

		Reservations reservations = this.getReservations();
		// calculate highest key
		List<BigInteger> ids = new ArrayList<BigInteger>();
		for (Reservation res : reservations.getReservation()) {
			ids.add(res.getId());
		}
		if (ids.size() > 0) {
			Collections.sort(ids);
			reservation.setId(Utilities.bigInteger((Integer) new Integer(ids.get(ids.size() - 1).intValue() + 1)));
		} else {
			reservation.setId(Utilities.bigInteger((Integer) new Integer(1)));
		}

		reservations.getReservation().add(reservation);

		try {
			writeReservations(reservations);
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
	}

	@Override
	public int getNumberOfReservations() {
		Reservations reservations = this.getReservations();
		return reservations.getReservation().size();
	}

	@Override
	public Reservations getReservations() {
		Reservations reservations = null;
		try {
			reservations = readReservations();
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
		return reservations;
	}

	protected Reservations readReservations() throws Exception {
		final JAXBContext context = JAXBContext.newInstance(Reservations.class);
		final Unmarshaller unmarshaller = context.createUnmarshaller();
		File f = new File(System.getProperty("user.dir") + ApplicationConfiguration.RESERVATION_FILE);
		try (final InputStream is = new BufferedInputStream(new FileInputStream(f))) {
			final Reservations reservations = (Reservations) unmarshaller.unmarshal(is);
			return reservations;
		}
	}

	protected void writeReservations(Reservations reservations) throws Exception {
		final JAXBContext context = JAXBContext.newInstance(Reservations.class);
		final Marshaller marshaller = context.createMarshaller();
		File f = new File(System.getProperty("user.dir") + ApplicationConfiguration.RESERVATION_FILE);
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		try (final OutputStream os = new BufferedOutputStream(new FileOutputStream(f))) {
			marshaller.marshal(reservations, os);
		}
	}

	@Override
	public void cancelReservation(int id) {
		Reservations reservations = this.getReservations();
		Iterator it = reservations.getReservation().iterator();
		int j = 0;
		while (it.hasNext()) {
			Reservation res = (Reservation) it.next();
			int i = res.getId().intValue();
			if (i == id) {
				reservations.getReservation().remove(j);
				break;
			}
			j++;
		}
		try {
			writeReservations(reservations);
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
		}
	}

	@Override
	public void modifyReservation(int id, String name, String email, String phone, int table, Date date,
			int numberOfPersons, String menu) {
		Reservations modifiedReservations = new ObjectFactory().createReservations();
		Reservations reservations = this.getReservations();
		for (Reservation res : reservations.getReservation()) {
			int i = res.getId().intValue();
			if (i == id) {
				res.setName(name);
				res.setEmail(email);
				res.setPhone(phone);
				res.setTable(BigInteger.valueOf(table));
				res.setNumberOfPersons(BigInteger.valueOf(numberOfPersons));
				res.setDate(Utilities.getXmlDate(date));
				res.setMenu(menu);
			}
			modifiedReservations.getReservation().add(res);
		}
		try {
			writeReservations(modifiedReservations);
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage());
		}

	}

	@Override
	public Reservation getReservation(int id) {
		Reservations reservations = this.getReservations();
		for (Reservation res : reservations.getReservation()) {
			int i = res.getId().intValue();
			if (i == id) {
				return res;
			}
		}
		return null;
	}

}
