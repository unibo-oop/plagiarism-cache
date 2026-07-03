package model.Astronauta;

import java.io.Serializable;

import model.Posizione;

public class Astronauta implements Serializable {
    private static final long serialVersionUID = -8976023480567730322L;
    private Sopravvivenza parametri = new Sopravvivenza();
    
    private Posizione posizione = Posizione.BASE;

    public Astronauta() {
    }
    public Astronauta(final Sopravvivenza sopravvivenza) {
        this.parametri = sopravvivenza;
    }

    /**
     * Getter survival parameters of the astronaut
     * @return the Survival parameters
     */
    public Sopravvivenza getParametri() {
        return this.parametri;
    }
    /**
     * Getter method for the current position of the astronaut
     * @return the current position of the astronaut
     */
    public Posizione getPosizione() {
        return this.posizione;
    }
    /**
     * Setter method for the current position of the astronaut
     * @param pos next position of the astronaut
     */
    public void setPosizione(final Posizione pos) {
        this.posizione = pos;
    }
}