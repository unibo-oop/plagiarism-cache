package model;

import model.Astronauta.Astronauta;

public class ModelImpl implements Model{
    private Astronauta astronauta;
    private Tempo tempo;
    
    public ModelImpl() {
        astronauta = new Astronauta();
        tempo = new TempoImpl(astronauta);
    }
    
    /**
     * Getter method for the astronaut
     * @return the astronaut
     */
    public Astronauta getAstronauta() {
        return this.astronauta;
    }
    
    /**
     * Getter method for the time
     * @return the time
     */
    public TempoImpl getTempo() {
        return (TempoImpl) this.tempo;
    }
    
    /**
     *Setter method for the astronaut
     */
    public void setAstronauta(final Astronauta astronauta) {
        this.astronauta = astronauta;
    }
    
    /**
     * Setter method for the time
     */
    public void setTempo(final TempoImpl tempo) {
        this.tempo = tempo;
    }
}
