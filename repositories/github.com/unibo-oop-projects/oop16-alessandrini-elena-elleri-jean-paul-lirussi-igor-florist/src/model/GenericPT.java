package model;

import java.time.Period;

/**
 * implements generic plant.
 */
public class GenericPT extends PlantImpl {


    private static final int FIORITURA = 7; //giorni di fioritura

    private static final String NAME = "Generic";
    private static final Color COLORE = Color.GREEN;
    private static final String PATHDESCRIPTION = "res/model.descriptions/des-generic.txt"; // description from file
    private static final String IMAGEDESCRIPTION = "res/model.images/img-generic.jpg"; // image form file
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
    public GenericPT(final int pos) {
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
