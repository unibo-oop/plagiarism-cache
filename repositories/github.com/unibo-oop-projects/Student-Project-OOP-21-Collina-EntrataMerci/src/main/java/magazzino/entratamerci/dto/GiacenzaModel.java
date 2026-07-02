package magazzino.entratamerci.dto;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class GiacenzaModel {
    private SimpleStringProperty articolo;
    private SimpleStringProperty posizione;
    private SimpleIntegerProperty quantita;

    public GiacenzaModel(String articolo, String posizione, int quantita) {
        this.articolo = new SimpleStringProperty(articolo);
        this.posizione =  new SimpleStringProperty(posizione);
        this.quantita = new SimpleIntegerProperty(quantita);
    }

    public String getArticolo() {
        return articolo.get();
    }

    public SimpleStringProperty articoloProperty() {
        return articolo;
    }

    public String getPosizione() {
        return posizione.get();
    }

    public SimpleStringProperty posizioneProperty() {
        return posizione;
    }

    public int getQuantita() {
        return quantita.get();
    }

    public SimpleIntegerProperty quantitaProperty() {
        return quantita;
    }
}
