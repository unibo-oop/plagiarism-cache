package adminpackage.roomtype;

import adminpackage.template.DeleteOperation;

/**
 * 
 * emanu.
 *
 */
public class CancellaTipoCamera extends DeleteOperation {
    /**
     * Use a template metode to delete a type room.
     * 
     * @param testo
     *            label text
     * @param titolo
     *            frame text
     */
    public CancellaTipoCamera(final String testo, final String titolo) {
        super(testo, titolo);
    }

    public static void main(final String[] args) {
        new CancellaTipoCamera("Scegli tipo camera da cancellare", "Cancella tipo camera");
    }

    @Override
    public String[] getElements() {
        String[] ciao = { "VUKSAAAAAAAA" };
        return ciao;
    }

    @Override
    public void sendData() {
        System.out.println(this.getElementoScelto());
    }
}
