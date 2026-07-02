package model;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ChartException extends IndexOutOfBoundsException {

    /**
     * 
     */
    private static final long serialVersionUID = 7736314487164863872L;

    private static final String ERROR = "Nessun dato disponibile per la cancellazione ";

    public ChartException() {
        super(ChartException.ERROR);
    }

    public void warning(JPanel panel) {
        JOptionPane.showMessageDialog(panel, "Nessun dato da cancellare!");
    }
}
