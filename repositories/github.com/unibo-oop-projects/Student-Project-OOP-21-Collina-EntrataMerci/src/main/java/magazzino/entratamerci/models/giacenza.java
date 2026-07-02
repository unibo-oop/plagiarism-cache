package magazzino.entratamerci.models;

import java.util.Objects;

public class giacenza {
    private String articoloCodice;
    private String articoloDescrizione;

    private String areaCodice;
    private String areaDescrizione;
    private String locazioneCodice;
    private String locazioneDescrizione;

    private int quantita;

    public giacenza(String articoloCodice, String articoloDescrizione, String areaCodice, String areaDescrizione, String locazioneCodice, String locazioneDescrizione, int quantita) {
        this.articoloCodice = articoloCodice;
        this.articoloDescrizione = articoloDescrizione;
        this.areaCodice = areaCodice;
        this.areaDescrizione = areaDescrizione;
        this.locazioneCodice = locazioneCodice;
        this.locazioneDescrizione = locazioneDescrizione;
        this.quantita = quantita;
    }

    public giacenza(articolo articolo, locazione locazione, int quantita){
        this.articoloCodice = articolo.getCodice();
        this.articoloDescrizione = articolo.getDescrizione();
        this.areaCodice = locazione.getArea().getCodice();
        this.areaDescrizione =  locazione.getArea().getDescrizione();
        this.locazioneCodice = locazione.getCodice();
        this.locazioneDescrizione = locazione.getDescrizione();
        this.quantita = quantita;
    }

    public String getArticoloCodice() {
        return articoloCodice;
    }

    public String getAreaCodice() {
        return areaCodice;
    }

    public String getLocazioneCodice() {
        return locazioneCodice;
    }

    public String getArticoloFormatted(){ return String.format("[%s] %s", articoloCodice,articoloDescrizione);}
    public String getPosizioneFormatted(){ return String.format("[%s] %s - [%s] %s", areaCodice,areaDescrizione, locazioneCodice, locazioneDescrizione);}

    public void addQuantita(int quantita){
        this.quantita += quantita;
    }

    public Integer getQuantita() {
        return  quantita;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        giacenza giacenza = (giacenza) o;
        return articoloCodice.equals(giacenza.articoloCodice) && areaCodice.equals(giacenza.areaCodice) && locazioneCodice.equals(giacenza.locazioneCodice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(articoloCodice, areaCodice, locazioneCodice);
    }
}
