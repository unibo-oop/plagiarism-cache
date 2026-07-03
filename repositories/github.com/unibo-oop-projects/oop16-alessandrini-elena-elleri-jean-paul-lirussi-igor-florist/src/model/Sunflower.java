package model;

import java.time.Period;

/**
 * implements plant sunflower.
 */
public class Sunflower extends PlantImpl {


    private static final int FIORITURA = 23; //giorni di fioritura

    private static final String NAME = "Sunflower";
    private static final Color COLORE = Color.YELLOW;
    private static final String PATHDESCRIPTION = "res/model.descriptions/des-sunflower.txt"; // descrizione da file
    private static final String IMAGEDESCRIPTION = "res/model.images/img-sunflower.jpg"; // immagine da file
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
    public Sunflower(final int pos) {
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
