package migglione.view.impl.scenesimpl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import migglione.view.api.music.MusicPlayer;
import migglione.view.api.music.MusicProvider;
import migglione.view.api.music.MusicTracks;
import migglione.view.api.scenes.Scenes;
import migglione.view.impl.SwingViewImpl;
import migglione.view.impl.musicimpl.LoopingMusicPlayerImpl;

/**
 * Class designed to construct the Menu scene of the application,
 * it offers the option to start the game, see the tutorial, the gallery,
 * the credits and to quit the application.
 */
public final class Menu extends AbstractGamePanel implements MusicProvider {

    private static final long serialVersionUID = 9879879872L;
    private static final String TITLE = "IL MIGGLIONE";
    private static final String START_GAME = "Start Game";
    private static final String SCORES = "Scores";
    private static final String TUTORIAL = "Tutorial";
    private static final String GALLERY = "Gallery";
    private static final String CREDITS = "Credits";
    private static final String QUIT = "Quit";
    private static final String FONT_NAME = "Times New Roman";
    private static final int FONT_SIZE = 70;
    private static final String BACKGROUND_IMAGE_PATH = "/images/utilities/title.png";
    private final transient Image titleImage;

    /**
     * Constructor of the class.
     * The Menu is designed to change its size while changing the window's,
     * so are the distances of the buttons and the image set as a background.
     * It is also designed to play music.
     * 
     * @param view is used in order to associate the changing scenes to the buttons
     */
    public Menu(final SwingViewImpl view) {
        this.setLayout(new BorderLayout());

        final JPanel cPanel = new JPanel();
        final JPanel titleBox = new JPanel();
        final JLabel title = new JLabel(TITLE);
        final JButton startButton = new GenericButton(START_GAME, b -> view.setScene(Scenes.START_GAME.getScene()));
        final JButton tutorial = new GenericButton(TUTORIAL, b -> view.setScene(Scenes.TUTORIAL.getScene()));
        final JButton scores = new GenericButton(SCORES, b -> view.setScene(Scenes.SCORES.getScene()));
        final JButton gallery = new GenericButton(GALLERY, b -> view.setScene(Scenes.GALLERY.getScene()));
        final JButton credits = new GenericButton(CREDITS, b -> view.setScene(Scenes.CREDITS.getScene()));
        final JButton quit = new GenericButton(QUIT, b -> view.quit());

        cPanel.setLayout(new BoxLayout(cPanel, BoxLayout.Y_AXIS));
        cPanel.setAlignmentX(CENTER_ALIGNMENT);
        cPanel.setOpaque(false);

        titleImage = new ImageIcon(getClass().getResource(BACKGROUND_IMAGE_PATH)).getImage();

        titleBox.setLayout(new BorderLayout());
        titleBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        titleBox.setBackground(Color.BLACK);
        titleBox.add(title, BorderLayout.CENTER);

        title.setFont(new Font(FONT_NAME, Font.BOLD, FONT_SIZE));
        title.setForeground(Color.YELLOW);
        title.setHorizontalAlignment(JLabel.CENTER);

        cPanel.add(Box.createVerticalGlue());
        cPanel.add(titleBox);
        cPanel.add(Box.createVerticalGlue());

        cPanel.add(startButton);
        cPanel.add(Box.createVerticalGlue());
        cPanel.add(tutorial);
        cPanel.add(Box.createVerticalGlue());
        cPanel.add(scores);
        cPanel.add(Box.createVerticalGlue());
        cPanel.add(gallery);
        cPanel.add(Box.createVerticalGlue());
        cPanel.add(credits);
        cPanel.add(Box.createVerticalGlue());
        cPanel.add(quit);
        cPanel.add(Box.createVerticalGlue());

        this.add(cPanel, BorderLayout.CENTER);
    }

    @Override
    public MusicPlayer getMusic() {
        return new LoopingMusicPlayerImpl(MusicTracks.ENA.getTrackPath());
    }

    @Override
    protected Image getImage() {
        return titleImage;
    }
}
