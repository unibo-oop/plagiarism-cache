package model;

import java.time.Period;

/**
 * implements plant Narciso.
 */
public class Narcissus extends PlantImpl {


    private static final int FIORITURA = 12; //giorni di fioritura

    private static final String NAME = "Narcissus";
    private static final Color COLORE = Color.YELLOW;
    private static final String PATHDESCRIPTION = "res/model.descriptions/des-narcissus.txt"; // descrizione da file
    private static final String IMAGEDESCRIPTION = "res/model.images/img-narcissus.jpg"; // immagine da file
    private static final Period BTIME = Period.ofDays(FIORITURA);


    /**
     * serial number.
     */
    private static final long serialVersionUID = -7621827224672334219L;



    /**
     * costruttore.
     */
    public Narcissus() {
        super(NAME, COLORE, PATHDESCRIPTION, IMAGEDESCRIPTION, BTIME);
    }
}
