package view.booking;

import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import utilities.factory.ProgrammedFilm;
import utilitiesimpl.SeatState;

public interface GUIFactoryBooking {
    /**
     * Get base frame.
     * @param title of frame
     * @return base frame 
     */
    JFrame getBaseFrame(String title);
    /**
     * Get info panel with a button and title.
     * @param info string
     * @param action is an ActionListener of button
     * @return panel 
     */
    JPanel getInfoPanel(String info, ActionListener action);
    /**
     * Return a button with an image.
     * @param icon used in button
     * @return a button
     */
    JButton getButtonImage(ImageIcon icon);
    /**
     * Return a table filled with a set of ProgrammedFilm.
     * @param programmedFilm used
     * @return table
     */
    JTable getTable(Set<ProgrammedFilm> programmedFilm);
    /**
     * Return a button that show state of seat.
     * @param state of seat
     * @param i row
     * @param j column
     * @return a button
     */
    JButton getButtonSeat(SeatState state, int i, int j);
    /**
     * Return a model for table filled with a set of ProgrammedFilm.
     * @param programmedFilm used to fill model
     * @return model of table
     */
    DefaultTableModel getModel(Collection<ProgrammedFilm> programmedFilm);
    /**
     * Return a label with a specific icon with specific dimension.
     * @param icon used in label
     * @param width image
     * @param height image
     * @return label 
     */
    JLabel getLabelImage(ImageIcon icon, int width, int height);

}
