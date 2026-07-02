package viewimpl.managefilms.factory;


import java.awt.Dimension;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import utilities.factory.Film;
import view.managefilms.factory.PanelFilmFactory;


public final  class PanelFilmFactoryImpl implements PanelFilmFactory {
    private static final double WIDTH_PERC_FRAME = 0.5;
    private static final double HEIGHT_PERC_FRAME = 0.5;

    private static final double WIDTH_IMAGE = WIDTH_PERC_FRAME / 5;
    private static final double HEIGHT_IMAGE = HEIGHT_PERC_FRAME / 2;
    /** 
     * {@inheritDoc}
     * */
    @Override
    public JPanel getFilmPanel(final Map<JButton, Film> mapFilm, final Set<Film> setFilm) {
        final JPanel centralPanel = new JPanel(new WrapLayout());
        for (final var film : setFilm) { 
           //if film has set a coverPath loads this, otherwise loads defaultImage
            if (film.getCoverPath().isPresent()) {
                final ImageIcon img = new ImageIcon(film.getCoverPath().get());
                mapFilm.put(this.getButtonImage(film.getName(), img), film); 
            }
            else {
                final URL imgURL = ClassLoader.getSystemResource("images/filmStandardIco.png");
                final ImageIcon img = new ImageIcon(imgURL);
                mapFilm.put(this.getButtonImage(film.getName(), img), film);
            }
        }
        for (final var bt : mapFilm.keySet()) {
            centralPanel.add(bt);
        }
        return centralPanel;
    }
    /**
     * It's used to get a button with specific image.
     *  @param title
     *  @param icon
     *  @return button
     */
    private JButton getButtonImage(final String title, final ImageIcon icon) {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        // load the image to a imageIcon
        final Image image = icon.getImage(); // transform it 
        final Image newimg = image.getScaledInstance((int) (screenSize.getWidth() * WIDTH_IMAGE), (int) (screenSize.getHeight() * HEIGHT_IMAGE),  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        final ImageIcon newImgIcon = new ImageIcon(newimg);  // transform it back
        final JButton button = new JButton("Title:" + title, newImgIcon);
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setVerticalTextPosition(JButton.TOP);
        button.setMargin(new Insets(0, 0, 0, 0));
        return button;
    }




}
