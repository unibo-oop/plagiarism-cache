package viewimpl.managefilms.factory;

import java.awt.Image;
import java.awt.LayoutManager;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import view.managefilms.factory.InfoFilmsViewfactory;


public final class InfoFilmsViewFactoryImpl implements InfoFilmsViewfactory {

    /** 
     * {@inheritDoc}
     * */
    @Override
    public JPanel createPanel(final LayoutManager layout) {
        return new JPanel(layout);
    }
    /** 
     * {@inheritDoc}
     * */
    @Override
    public JButton createButtonWithText(final String text) {
        return new JButton(text);
    }
    /** 
     * {@inheritDoc}
     * */
    @Override
    public JButton createButtonWithIcon(final Icon icon) {
        return new JButton(icon);
    }
    /** 
     * {@inheritDoc}
     * */
    @Override
    public JTextField createTextField(final String text) {
        return new JTextField(text);
    }
    /** 
     * {@inheritDoc}
     * */
    @Override
    public JTextArea createTextArea(final String text) {
        return new JTextArea(text);
    }
    /** 
     * {@inheritDoc}
     * */
    @Override
    public ImageIcon getScaledIcon(final ImageIcon icon, final int width, final int height) {
        final Image image = icon.getImage(); // transform it
        final Image newimg = image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        return new ImageIcon(newimg); // transform it back
    }
}
