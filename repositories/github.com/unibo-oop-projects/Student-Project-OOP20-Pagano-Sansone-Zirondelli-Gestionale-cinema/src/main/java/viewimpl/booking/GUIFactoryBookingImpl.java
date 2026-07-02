package viewimpl.booking;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import utilities.factory.ProgrammedFilm;
import utilitiesimpl.GeneralSettings;
import utilitiesimpl.Row;
import utilitiesimpl.SeatState;
import view.booking.GUIFactoryBooking;

public class GUIFactoryBookingImpl implements GUIFactoryBooking {
    private static final double WIDTH_PERC_FRAME = 0.5;
    private static final double HEIGHT_PERC_FRAME = 0.5;
    private static final double WIDTH_IMAGE_COVER = WIDTH_PERC_FRAME / 5;
    private static final double HEIGHT_IMAGE_COVER = HEIGHT_PERC_FRAME / 2;
    private static final Color COLOR_BORDER_INFOPANEL = Color.black;
    private static final double WIDTH_IMAGE_SEAT = WIDTH_PERC_FRAME / 15;
    private static final double HEIGHT_IMAGE_SEAT = HEIGHT_PERC_FRAME / 15;
    private static final String STRING_BUTTON_BACK = "back";
    /**
     * {@inheritDoc}
     */
    @Override
    public JFrame getBaseFrame(final String title) {
        final JFrame frame = new JFrame();
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize((int) (screenSize.getWidth() * WIDTH_PERC_FRAME), (int) (screenSize.getHeight() * HEIGHT_PERC_FRAME));
        frame.setTitle(title);
        frame.setLocationByPlatform(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        return frame;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public JPanel getInfoPanel(final String info, final ActionListener action) {
        final JPanel infoPanel = new JPanel(new BorderLayout());
        final JButton button = new JButton(STRING_BUTTON_BACK);
        final JLabel label = new JLabel(info);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        infoPanel.add(label, BorderLayout.CENTER);
        infoPanel.setBorder(BorderFactory.createLineBorder(COLOR_BORDER_INFOPANEL));
        infoPanel.add(button, BorderLayout.WEST);
        button.addActionListener(action);
        return infoPanel;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public JLabel getLabelImage(final ImageIcon icon, final int width, final int height) {
        final JLabel labelIcon = new JLabel();
        ImageIcon imageIcon = icon;
        final Image image = imageIcon.getImage(); // transform it 
        final Image newimg = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);  // transform it back
        labelIcon.setIcon(imageIcon);
        return labelIcon;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public JButton getButtonImage(final ImageIcon icon) {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        ImageIcon imageIcon = icon;
        final Image image = imageIcon.getImage(); // transform it 
        final Image newimg = image.getScaledInstance((int) (screenSize.getWidth() * WIDTH_IMAGE_COVER), (int) (screenSize.getHeight() * HEIGHT_IMAGE_COVER),  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);  // transform it back
        final JButton button = new JButton(imageIcon);
        button.setIcon(imageIcon);
        button.setMargin(new Insets(0, 0, 0, 0));
        return button;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public JTable getTable(final Set<ProgrammedFilm> programmedFilm) {
        final int row = programmedFilm.size();
        final String[] columnNames = { "Date", "Time", "Hall" };
        Object[][] data = new Object[row][columnNames.length];
        int i = 0;
        for (final var elem : programmedFilm) {
            data[i][0] = elem.getDate();
            data[i][1] = elem.getStartTime();
            data[i][2] = elem.getHall();
            i++;
        }
        final DefaultTableModel model = new DefaultTableModel(data, columnNames);
        final JTable table = new JTable(model) {
            private static final long serialVersionUID = 1L;
            public boolean isCellEditable(final int row, final int column) {
                return false;
            }
        };
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        return table;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public DefaultTableModel getModel(final Collection<ProgrammedFilm> programmedFilm) {
        final int row = programmedFilm.size();
        final String[] columnNames = {"Date", "Time", "Hall" };
        Object[][] data = new Object[row][columnNames.length];
        int i = 0;
        for (final var elem : programmedFilm) {
            data[i][0] = elem.getDate();
            data[i][1] = elem.getStartTime();
            data[i][2] = elem.getHall();
            i++;
        }
        return new DefaultTableModel(data, columnNames);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public JButton getButtonSeat(final SeatState state, final int i, final int j) {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        ImageIcon imageIcon = new ImageIcon();
        if (state.equals(SeatState.FREE)) {
             imageIcon = new ImageIcon(ClassLoader.getSystemResource(GeneralSettings.IMAGE_SEAT_FREE)); // load the image to a imageIcon
        } 
        if (state.equals(SeatState.SELECTED)) {
             imageIcon = new ImageIcon(ClassLoader.getSystemResource(GeneralSettings.IMAGE_SEAT_SELECTED)); // load the image to a imageIcon
        } 
        if (state.equals(SeatState.TAKEN)) {
             imageIcon = new ImageIcon(ClassLoader.getSystemResource(GeneralSettings.IMAGE_SEAT_TAKEN)); // load the image to a imageIcon
        }
        final Image image = imageIcon.getImage(); 
        final Image newimg = image.getScaledInstance((int) (screenSize.getWidth() * WIDTH_IMAGE_SEAT), (int) (screenSize.getHeight() * HEIGHT_IMAGE_SEAT),  java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg); 
        final JButton button = new JButton(Row.values()[i] + " " + j, imageIcon);
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setVerticalTextPosition(JButton.TOP);
        button.setMargin(new Insets(0, 0, 0, 0));
        return button;
    }
}



