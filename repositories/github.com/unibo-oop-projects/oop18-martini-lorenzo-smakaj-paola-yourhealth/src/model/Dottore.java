package model;

import java.time.LocalTime;

public interface Dottore extends Persona {

	/**
	 * id getter.
	 * 
	 * @return doctor's id
	 */
	int getTesserino();

	/**
	 * role getter.
	 * 
	 * @return doctor's role
	 */
	String getRuolo();

	/**
	 * schedule begin getter.
	 * 
	 * @return doctor's schedule's begin
	 */
	LocalTime getOrarioInizio();

	/**
	 * schedule end getter.
	 * 
	 * @return doctor's schedule's end
	 */
	LocalTime getOrarioFine();

}
