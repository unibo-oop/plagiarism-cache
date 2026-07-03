package adminpackage.season;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import adminpackage.template.DeleteOperation;
/**
 * 
 *  emanu.
 *
 */
public class CancellaStagione extends DeleteOperation {

    public CancellaStagione(String testo, String titolo) {
        super(testo, titolo);
    }
/**
 * 
 * @param args
 */
    public static void main(final String[] args) {
        new CancellaStagione("Scegli tipo di stagione da cancellare", "Cancella stagione");

    }

    @Override
    public String[] getElements() {
        String[] ciao = { "Estate" };
        return ciao;
    }

    @Override
    public void sendData() {
        System.out.println(this.getElementoScelto());
    }
}