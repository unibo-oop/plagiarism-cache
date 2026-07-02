package model;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
/**
 * DateException, extends IllegalArgumentException more accurate, it will be called anytime there will be errors in date params 
 * 
 * @author Nico Nize
 *
 */
public class DateException extends IllegalArgumentException {

    /**
     * 
     */
    private static final long serialVersionUID = 908412516388107764L;
    /**
     * 
     */
    
    private final static String ERROR = "Errore formato data!";
    private final static String WARNING = "Formato data non valido!";
    private final static String DATEMISMATCH = "Errore di corrispondenza nelle date"
                                                + ", verifica di non aver inserito una data di arrivo"
                                                + " superiore a quella di partenza";
   
    public DateException() {
        super(DateException.ERROR);
    }
    
    public void warning(JPanel panel){
        JOptionPane.showMessageDialog(panel,DateException.WARNING);
    }
    
    public void dateBefore(JPanel panel) {
        JOptionPane.showMessageDialog(panel, DateException.DATEMISMATCH);
    }
    
}
