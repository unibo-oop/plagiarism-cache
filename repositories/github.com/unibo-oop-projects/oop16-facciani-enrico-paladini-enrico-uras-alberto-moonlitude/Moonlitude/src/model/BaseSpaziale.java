package model;

import model.Oggetti.Cibo;
import model.Oggetti.CommonEquipaggiamento;
import model.Oggetti.Equipaggiamento;
import model.Oggetti.Materiale;
import model.Oggetti.Oggetto;
import model.Oggetti.TutaSpaziale;
import model.Stanza.Magazzino;
import model.Stanza.Refrigeratore;

public class BaseSpaziale {
    private static class LazyHolder {
        private static final BaseSpaziale SINGLETON = new BaseSpaziale();
    }
    
    private BaseSpaziale() {
    }
    
    // Creo il SINGLETON alla prima chiamata
    public static BaseSpaziale getLog() {
        return LazyHolder.SINGLETON;
    }

    /**
     * Count how many object of a kind is own
     * @param oggetto object to be searched
     * @return the amount of the object
     */
    public Integer getQuantitativoOggetto(final Oggetto oggetto) {
        Integer quantitativo = 0;
        if (oggetto instanceof Cibo) {
            quantitativo += Refrigeratore.getLog().getQuantitaOggetto((Cibo) oggetto);
        } else if (oggetto instanceof Materiale) {
            quantitativo += Magazzino.getLog().getQuantitaOggetto((Materiale) oggetto);
        }
        if (Equipaggiamento.getLog().presenzaOggetto(oggetto)) {
            quantitativo ++;
        }
        return quantitativo;
    }
    
    /**
     * Remove an amount of an object
     * @param oggetto object to be removed
     * @param quantitativo amount to be removed
     */
    public void rimuoviQuantitativoOggetto(final Oggetto oggetto, final Integer quantitativo) { //SI POSSONO RIMUOVERE PERCH� SI ACCEDE DIRETTAMENTE ALLA STANZA
        Integer daRimuovere = quantitativo;
        if (oggetto instanceof Cibo) {
            Refrigeratore.getLog().rimuoviOggetto((Cibo) oggetto, daRimuovere);
        } else if (oggetto instanceof Materiale) {
            Magazzino.getLog().rimuoviOggetto((Materiale) oggetto, daRimuovere);
        }
    }
    
    /**
    /**
     * Add an amount of an object
     * @param oggetto object to be added
     * @param quantitativo amount to be added
     * @return whether or not it add the element
     */
    public Integer aggiungiQuantitativoOggetto(final Oggetto oggetto, final Integer quantitativo) {
        if (oggetto instanceof Cibo) {
            return Refrigeratore.getLog().aggiungiOggetto((Cibo) oggetto, quantitativo);
        } else if (oggetto instanceof Materiale) {
            return Magazzino.getLog().aggiungiOggetto((Materiale) oggetto, quantitativo);
            //return ((Magazzino) accediAdUnaStanza(CommonStanza.MAGAZZINO)).aggiungiOggetto((Materiale) oggetto, quantitativo, true);
        } else if (oggetto instanceof CommonEquipaggiamento) {
            Equipaggiamento.getLog().inserisciInListaOggettiEquipaggiabili((CommonEquipaggiamento) oggetto); 
        } else if (oggetto instanceof TutaSpaziale) {
            Equipaggiamento.getLog().cambioTuta((TutaSpaziale) oggetto);
        }
        return 0;
    }
    
    /**
     * Check whether or not an object is enough
     * @param oggettoDaControllare object to be controlled
     * @param quanto amount of the given object
     * @return whether or not the given amount of the given object is enough
     */
    public Boolean controllaOggetto(final Oggetto oggettoDaControllare, final Integer quanto) {
        if (this.getQuantitativoOggetto(oggettoDaControllare) >= quanto) {
            return true;
        }
        return false;
    }
}
