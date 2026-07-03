package model;

import model.Astronauta.Astronauta;

public interface Model {
    public Astronauta getAstronauta();
    public TempoImpl getTempo();
    public void setAstronauta(final Astronauta astronauta);
    public void setTempo(final TempoImpl tempo);
}
