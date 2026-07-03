package model;

import java.time.Period;

/**
 * implements plant Orchidea.
 */
public class Orchid extends PlantImpl {


    private static final int FIORITURA = 27; //giorni di fioritura

    private static final String NAME = "Orchid";
    private static final Color COLORE = Color.PURPLE;
    private static final String PATHDESCRIPTION = "res/model.descriptions/des-orchid.txt"; // descrizione da file
    private static final String IMAGEDESCRIPTION = "res/model.images/img-orchid.jpg"; // immagine da file
    private static final Period BTIME = Period.ofDays(FIORITURA);


    /**
     * serial number.
     */
    private static final long serialVersionUID = -7621827224672334219L;



    /**
     * costruttore.
     */
    public Orchid() {
        super(NAME, COLORE, PATHDESCRIPTION, IMAGEDESCRIPTION, BTIME);
    }
}
