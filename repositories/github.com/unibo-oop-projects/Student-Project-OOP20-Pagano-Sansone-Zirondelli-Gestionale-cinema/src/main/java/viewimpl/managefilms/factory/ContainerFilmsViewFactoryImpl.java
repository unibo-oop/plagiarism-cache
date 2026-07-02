package viewimpl.managefilms.factory;

import java.awt.LayoutManager;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JPanel;

import view.managefilms.factory.ContainerFilmsViewFactory;
/**
 * Basic implementation to create basic components of container films. 
 * */
public final class ContainerFilmsViewFactoryImpl implements ContainerFilmsViewFactory {
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

}
