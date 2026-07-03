package adminpackage.person;

import adminpackage.template.DeleteOperation;

//TODO
/**
 * 
 * emanu.
 *
 */
public class CancellaPersona extends DeleteOperation {
    public CancellaPersona(final String testo, final String titolo) {
        super(testo, titolo);

    }

    /**
     * 
     * @param args
     */
    public static void main(final String[] args) {
        new CancellaPersona("Scegli persona da cancellare", "Tipo persona da cancellare");
    }

    @Override
    public String[] getElements() {
        String[] ciao = { "Adulto", "Bambino", "Schiavo" };
        return ciao;
    }

    @Override
    public void sendData() {
        System.out.println(this.getElementoScelto());
    }
}