package util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Helper class which provides some utility functions for parsing and formatting
 * tasks.
 *
 */
public class Utilities {

	private static final Dimension SCREEN = Toolkit.getDefaultToolkit().getScreenSize();
	private static final int WIDTH = (int) SCREEN.getWidth() * 4 / 5;
	private static final int HEIGHT = (int) SCREEN.getHeight() * 3 / 4;

	private static Logger logger = Logger.getLogger("model");

	public int getDefaultWidth() {
		return Utilities.WIDTH;
	}

	public int getDefaultHeight() {
		return Utilities.HEIGHT;
	}

	public static ImageIcon getImageIcon(String s) {
		if (s.equalsIgnoreCase("/images/page1-img3.jpg")) {
			ImageIcon icon = new ImageIcon(Utilities.class.getResource(s));
			Image img = icon.getImage();
			Image newimg = img.getScaledInstance(178, 136, java.awt.Image.SCALE_SMOOTH);
			return new ImageIcon(newimg);
		}
		ImageIcon icon = new ImageIcon(Utilities.class.getResource(s));
		Image img = icon.getImage();
		Image newimg = img.getScaledInstance(178, 28, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(newimg);
	}

	public static Color getSystemColor() {
		return new Color((SystemColor.window).getRGB());
	}

	public static Date parseDate(String s) {
		Date d = null;
		DateFormat format = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
		try {
			d = format.parse(s);
		} catch (java.text.ParseException e) {
			logger.log(Level.SEVERE, e.getStackTrace().toString());
		}
		return d;
	}

	public static Date parseDateTime(String date, String time) {
		Date d = null;
		String concatenatedDateAndTime = date + " " + time;
		DateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.ITALIAN);
		try {
			d = format.parse(concatenatedDateAndTime);
		} catch (java.text.ParseException e) {
			logger.log(Level.SEVERE, e.getStackTrace().toString());
		}
		return d;
	}

	public static boolean validateTime(String time) {
		Pattern pattern;
		Matcher matcher;
		final String TIME24HOURS_PATTERN = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
		pattern = Pattern.compile(TIME24HOURS_PATTERN);
		matcher = pattern.matcher(time);
		return matcher.matches();
	}

	public static XMLGregorianCalendar getXmlDate(Date date) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		XMLGregorianCalendar dateXml = null;
		try {
			TimeZone tz = TimeZone.getTimeZone("Europe/Rome");
			/**
			 * final int year, final int month, final int day, final int hour,
			 * final int minute, final int second, final int millisecond, final
			 * int timezone)
			 */
			dateXml = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal.get(Calendar.YEAR),
					cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.HOUR_OF_DAY),
					cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND), cal.get(Calendar.MILLISECOND), 0);
		} catch (DatatypeConfigurationException e) {
			logger.log(Level.SEVERE, e.getStackTrace().toString());
		}
		return dateXml;
	}

	public static Date fromXmlDate(XMLGregorianCalendar calendar) {
		if (calendar == null) {
			return null;
		}
		calendar.setHour(calendar.getHour() - 2);
		Date d = calendar.toGregorianCalendar().getTime();
		return d;
	}

	public static String getFormattedDate(Date date) {
		String FORMATER = "dd.MM.yyyy";
		DateFormat format = new SimpleDateFormat(FORMATER);
		String formattedDate = format.format(date);
		return formattedDate;
	}

	public static String getFormattedTime(Date date) {
		String FORMATER = "dd.MM.yyyy HH:mm";
		DateFormat format = new SimpleDateFormat(FORMATER, Locale.ITALIAN);
		String formattedTime = format.format(date);
		formattedTime = formattedTime.substring(11);
		return formattedTime;
	}

	public static BigInteger bigInteger(Integer i) {
		Long l = Long.valueOf(i.longValue());
		return BigInteger.valueOf(l.longValue());
	}

}
