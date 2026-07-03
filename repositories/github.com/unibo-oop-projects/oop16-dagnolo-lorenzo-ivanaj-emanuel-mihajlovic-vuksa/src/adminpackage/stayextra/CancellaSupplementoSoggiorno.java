package adminpackage.stayextra;

import adminpackage.template.DeleteOperation;

/**
 * 
 *  emanu.
 *
 */
public class CancellaSupplementoSoggiorno extends DeleteOperation {
    public CancellaSupplementoSoggiorno(String testo, String titolo) {
        super(testo, titolo);
    }

    @Override
    public String[] getElements() {
        String[] ciao = { "Parcheggio", "Yacuzzi" };
        return ciao;
    }

    @Override
    public void sendData() {
        System.out.println(this.getElementoScelto());
    }
    public static void main(String[] args){
        new CancellaSupplementoSoggiorno("Scegli supplemento da cancellare", "Cancellazione supplemento");
    }
}
