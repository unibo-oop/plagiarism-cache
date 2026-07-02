package magazzino.entratamerci.dto;

import magazzino.entratamerci.models.articolo;
import magazzino.entratamerci.models.locazione;

import java.util.Objects;

public class ItemsPosition {
    private magazzino.entratamerci.models.locazione locazione;
    private magazzino.entratamerci.models.articolo articolo;

    private  int quantita;

    public ItemsPosition(magazzino.entratamerci.models.locazione locazione, magazzino.entratamerci.models.articolo articolo, int quantita) {
        this.locazione = locazione;
        this.articolo = articolo;
        this.quantita = quantita;
    }

    public magazzino.entratamerci.models.locazione getLocazione() {
        return locazione;
    }

    public void setLocazione(magazzino.entratamerci.models.locazione locazione) {
        this.locazione = locazione;
    }

    public magazzino.entratamerci.models.articolo getArticolo() {
        return articolo;
    }

    public void setArticolo(magazzino.entratamerci.models.articolo articolo) {
        this.articolo = articolo;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemsPosition that = (ItemsPosition) o;
        return locazione.equals(that.locazione) && articolo.equals(that.articolo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(locazione, articolo);
    }
}
