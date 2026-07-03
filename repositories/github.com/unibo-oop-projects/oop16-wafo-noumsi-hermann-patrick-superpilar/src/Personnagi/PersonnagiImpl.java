package Personnagi;

import java.awt.Image;

import Oggetti.Ogetti;

public interface PersonnagiImpl {
	public Image walk(String nome , int frequenza );
	public boolean contact_A_Destra(Ogetti ogetto);
}
