package model;

import java.time.Period;

/**
 * implements plant garofano.
 */
public class Carnation extends PlantImpl  {

    /*
    private static FileReader filedes = new FileReader(new File("res/des-gar.txt"));
    BufferedReader reader = new BufferedReader(new FileReader("prova.txt"));
*/

    //private static FileReader filedes = new FileReader(new File("res/des-gar.txt"));
    //getClass().getResourceAsStream("/res"+File.separator+"Des-Gar.txt");

    private static final int FIORITURA = 22; //giorni di fioritura

    private static final String NAME = "Carnation";
    private static final Color COLORE = Color.PINK;
    private static final String PATHDESCRIPTION = "res/model.descriptions/des-carnation.txt"; // descrizione da file
    private static final String IMAGEDESCRIPTION = "res/model.images/img-carnation.jpg"; // immagine da file
    private static final Period BTIME = Period.ofDays(FIORITURA);


    /**
     * serial number.
     */
    private static final long serialVersionUID = -7621827224672334219L;




    /**
     * costruttore.
     */
    public Carnation() {
        super(NAME, COLORE, PATHDESCRIPTION, IMAGEDESCRIPTION, BTIME);
    }
}
