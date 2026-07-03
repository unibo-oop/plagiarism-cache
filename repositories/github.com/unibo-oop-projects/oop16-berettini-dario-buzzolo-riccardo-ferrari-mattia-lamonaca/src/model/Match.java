package model;

import java.io.Serializable;

public interface Match extends Serializable {
	
	/**
	 * 
	 * @return the info of the first Athletes
	 */
	public String getAtleta1();
	/**
	 * 
	 * @return the info of the second Athletes
	 */
	public String getAtleta2();
	/**
	 * 
	 * @return the info of the result of the Match
	 */
	public String getRisultato();
}
