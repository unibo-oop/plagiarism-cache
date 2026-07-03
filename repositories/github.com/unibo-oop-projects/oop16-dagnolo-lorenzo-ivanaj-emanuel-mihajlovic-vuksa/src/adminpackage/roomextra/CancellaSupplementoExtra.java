package adminpackage.roomextra;

import adminpackage.template.DeleteOperation;

/*
 * 
 * */

public class CancellaSupplementoExtra extends DeleteOperation {
    /**
     * 
     * @param testo
     * @param titolo
     */
    public CancellaSupplementoExtra(final String testo, final String titolo) {
        super(testo, titolo);
    }

    @Override
    public String[] getElements() {
        String[] ciao = { "VUUUksa" };
        ;
        return ciao;
    }

    @Override
    public void sendData() {
        System.out.println(this.getElementoScelto());

    }
}
