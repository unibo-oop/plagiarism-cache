package model;

import java.time.Period;

/**
 * implements plant rose.
 */
public class Rose extends PlantImpl {


    private static final int FIORITURA = 13; //giorni di fioritura

    private static final String NAME = "Rose";
    private static final Color COLORE = Color.RED;
    private static final String PATHDESCRIPTION = "res/model.descriptions/des-rose.txt"; // descrizione da file
    private static final String IMAGEDESCRIPTION = "res/model.images/img-rose.jpg"; // immagine da file
    private static final Period BTIME = Period.ofDays(FIORITURA);
    private final int pos;

    /**
     * serial number.
     */
    private static final long serialVersionUID = -7621827224672334219L;



    /**
     * costruttore.
     * @param pos the position of the plant.
     */
    public Rose(final int pos) {
        super(NAME, COLORE, PATHDESCRIPTION, IMAGEDESCRIPTION, BTIME);
        this.pos = pos;
    }

    /**
     * the position of the plant.
     * @return the position of the plant.
     */
    public int getPosition() {
        return this.pos;
    }
}
