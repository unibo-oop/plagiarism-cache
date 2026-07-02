package utility;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class PopUp extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = 3647943346532131731L;

    /**
     * 
     * @param msg
     */
    public void popUpInfo(final String msg) {
        JOptionPane.showMessageDialog(rootPane, msg, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * 
     * @param msg
     */
    public void popUpWarning(final String msg) {
        JOptionPane.showMessageDialog(rootPane, msg, "Attenzione", JOptionPane.WARNING_MESSAGE);
    }

    /**
     * 
     * @param msg
     */
    public void popUpError(final String msg) {
        JOptionPane.showMessageDialog(rootPane, msg, "Errore", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * 
     */
    public void popUpErrorOrMissing() {
        this.popUpWarning("Dati mancanti o errati. Riprovare.");
    }

    /**
     * 
     * @param msg
     * @return input user
     */
    public String popUpInput(final String msg) {
        return JOptionPane.showInputDialog(rootPane, msg, "Richiesta dati", JOptionPane.QUESTION_MESSAGE);
    }

    /**
     * 
     * @param msg
     * @return true for confirm input
     */
    public Boolean popUpConfirm(final String msg) {
        return JOptionPane.showConfirmDialog(rootPane, msg, "Conferma scelta", JOptionPane.YES_NO_OPTION)==0;
    }
}
