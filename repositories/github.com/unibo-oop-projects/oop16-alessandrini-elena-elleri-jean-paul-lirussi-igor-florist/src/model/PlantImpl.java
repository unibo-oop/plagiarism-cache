package model;

import java.awt.Image;
import java.awt.image.BufferedImage;
//import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
//import java.io.InputStream;
import java.time.LocalDate;
import java.time.Period;

import javax.imageio.ImageIO;

/**
 * implements the plant.
 */
public abstract class PlantImpl implements Plant, Serializable {

    private static final long serialVersionUID = -675058626311300658L;

    private final String name;

    private final Color colore;

    /**
     * time for the blooming
     */
    private final Period bTime;

    private final String description;

    /**
     * the condition : seed bud flower withered
     */
    private boolean watered;

    private Condition condizione;

    /**
     * the date the flower is planted
     */
    private LocalDate sowing; //differenza tra Date e LocalDate?

    private final Image image; //immagine pianta


    /**
     * 
     * @param name name of the flower
     * @param colore color of the flower
     * @param despath path of the description of the flower
     * @param imgpath path of the image of the flower
     * @param bTime blooming time of the flower
     */
    public PlantImpl(final String name, final Color colore, final String despath, final String imgpath, final Period bTime) {
        this.name = name;
        this.colore = colore;
        this.description = PlantImpl.leggiDes(despath);
        this.bTime = bTime;
        this.image = this.getFlowerImage(imgpath); 
    }

    /**
     * method for the controller.
     * @return the plant. 
     */
    public PlantImpl getPlant() {
        return this;
    } 

    //default methods
    @Override
    public String getName() {
        return name;
    }

    @Override
    public Color getColor() {
        // returns the color
        return colore;
    }

    @Override
    public Period getBloomigTime() {
        return bTime;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public boolean isWatered() {
        return watered;
    }

    @Override
    public void setWatered(final boolean watered) {
        this.watered = watered;

    }

    @Override
    public Condition getCondition() {
        return condizione;
    }

    @Override
    public void setCondition(final Condition condizione) {
        this.condizione = condizione;

    }

    @Override
    public LocalDate getDate() {
        return sowing;
    }

    @Override
    public void setDate(final LocalDate data) {
        this.sowing = data;
    }

    @Override
    public Period getAge() {
        return Period.between(sowing, LocalDate.now());
    }

    @Override
    public Image getImage() {
        return image;
    }


    /**
     *  metodo privato per leggere stringhe da file 
     * @param path il percorso del file con la descrizione
     * @return la stringa con la descrizione
     */
    private static String leggiDes(final String path) {
        final File name = new File(path); //crea il file
        if (name.isFile()) { //se c'è
            try {
                final BufferedReader bufread = new BufferedReader(new FileReader(name));
                final StringBuffer buffer = new StringBuffer();
                String text = bufread.readLine(); //alla stringa aggiunge la prima linea letta
                while (text != null) { //fino che non finisce il testo
                    buffer.append(text);
                    text = bufread.readLine(); //aggiunge linea
                }
                bufread.close(); //chiude il buffer reader
                return buffer.toString(); //ritorna il buffer con tutto
            } catch (IOException ioException) {
                System.out.println("IOExeption");
            }
        }
        return null;
    }


    /**
     *  metodo privato per leggere immagini da file 
     * @param path il percorso del file con immagine
     * @return l'immagine
     */
    private BufferedImage getFlowerImage(final String path) {
        try {
            return ImageIO.read(new File(path));
            } catch (IOException e) {
            return null;
            }

    }

/*
    private static Object readFromFile(final String path) {
        try {
        return new Image(PlantImp.class.getResourceAsStream("/" + path));
        } catch (Exception exception) {
            System.out.println("no image");
        }
        return null;
    }
*/
}
