package model;

public interface Athlete {
	 /**
	  * 
	  * @return the name of the athlete
	  */
	 public String getName();
	 /**
	  * 
	  * @return the surname of the athlete
	  */
	 public String getSurname();	 
	 /**
	  * 
	  * @return the belt of the athlete with a value and a description
	  */
	 public Belt getBelt();
	 /**
	  * Set the vote in the array "voti"[]{kick_vote,form_vote, fight_vote}
	  * @param indice
	  * @param voto
	  */
	 public void setVoto(int indice, int voto);
	 /**
	  * Get the vote in the array "voti" at the given index
	  * @param indice
	  * @return
	  */
	 public int getVoto(int indice);
	 /**
	  * Counts the numbers of given vote in the array
	  * @return the numbers of given vote in the array
	  */
	 public int getAvanzamento();
	 /**
	  * Check if the athlete has completed the exam and his grades and 
	  * set the field 'promosso' true or false
	  */
	 public void isPromosso();	
	 /**
	  * Set the vote of the Taegeuk
	  * @param puntiTotali
	  */
	 public void setVotoForma(Double puntiTotali);
	 /**
	  * Get the vote of the taegeuk
	  * @return
	  */
	 public double getVotoForma();
	 /**
	  * 
	  * @return true if the athlete is promoted, false if not
	  */
	 public boolean getPromosso();	 
}
