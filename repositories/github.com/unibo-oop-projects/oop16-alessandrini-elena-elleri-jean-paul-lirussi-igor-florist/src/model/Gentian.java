package model;

import java.time.Period;

/**
 * implements plant Genziana.
 */
public class Gentian extends PlantImpl {


    private static final int FIORITURA = 5; //giorni di fioritura

    private static final String NAME = "Gentian";
    private static final Color COLORE = Color.BLUE;
    private static final String PATHDESCRIPTION = "res/model.descriptions/des-gentian.txt"; // descrizione da file
    private static final String IMAGEDESCRIPTION = "res/model.images/img-gentian.jpg"; // immagine da file
    private static final Period BTIME = Period.ofDays(FIORITURA);


    /**
     * serial number.
     */
    private static final long serialVersionUID = -7621827224672334219L;



    /**
     * costruttore.
     */
    public Gentian() {
        super(NAME, COLORE, PATHDESCRIPTION, IMAGEDESCRIPTION, BTIME);
    }
}
