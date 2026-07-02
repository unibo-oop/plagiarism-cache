package model;

import java.time.LocalDate;
import java.time.LocalTime;

public interface Prestazione {

	/**
	 * patient getter.
	 * 
	 * @return performance's patient
	 */
	String getPaziente();

	/**
	 * doctor getter.
	 * 
	 * @return performance's doctor
	 */
	int getDottore();

	/**
	 * type getter.
	 * 
	 * @return performance's type
	 */
	String getTipo();

	/**
	 * date getter.
	 * 
	 * @return performance's date
	 */
	LocalDate getData();

	/**
	 * hour getter.
	 * 
	 * @return performance's hour
	 */
	LocalTime getOra();

	/**
	 * status getter.
	 * 
	 * @return performance's status
	 */
	String getStato();

	/**
	 * machine getter.
	 * 
	 * @return performance's machine
	 */
	String getMacchinario();

	/**
	 * treatment room getter.
	 * 
	 * @return performance's treatment room
	 */
	String getAmbulatorio();
}
