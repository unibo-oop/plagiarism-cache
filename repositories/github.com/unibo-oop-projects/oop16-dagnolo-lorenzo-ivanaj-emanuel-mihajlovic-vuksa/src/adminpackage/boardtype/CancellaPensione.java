package adminpackage.boardtype;

import adminpackage.template.DeleteOperation;

/**
 * 
 * @author emanu.
 *
 */
public class CancellaPensione extends DeleteOperation {
    /**
     * 
     * @param testo
     * @param titolo
     */
    public CancellaPensione(final String testo, final String titolo) {
        super(testo, titolo);
    }

    public static void main(final String[] args) {
        new CancellaPensione("Seleziona pensione da cancellare", "Cancella pensione");
    }

    @Override
    public String[] getElements() {
        String[] ciao = { "pensione completa", "vuksa" };
        return ciao;
    }

    @Override
    public void sendData() {
        System.out.println(this.getElementoScelto());
    }
}