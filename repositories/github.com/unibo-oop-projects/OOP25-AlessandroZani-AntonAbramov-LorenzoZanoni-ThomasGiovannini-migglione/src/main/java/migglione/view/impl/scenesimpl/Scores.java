package migglione.view.impl.scenesimpl;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import migglione.view.api.music.MusicPlayer;
import migglione.view.api.music.MusicProvider;
import migglione.view.api.music.MusicTracks;
import migglione.view.api.scenes.Scenes;
import migglione.view.impl.SwingViewImpl;
import migglione.view.impl.musicimpl.LoopingMusicPlayerImpl;

/**
 * The class Scores allows to see all scores of player game.
 * 
 * <p>
 * Read saved name and scores in external file whit JTextarea
 * and use JLable placeholder to change text to a custom one.
 * 
 * @see BorderedLine
 */
public final class Scores extends AbstractGamePanel implements MusicProvider {

    private static final long serialVersionUID = 976949968L;
    private static final String BACKGROUND_IMAGE_PATH = "/images/utilities/title.png";
    private static final String BACK = "Back";
    private static final String FILE_TXT_PATH = "/file/ScoreTable.txt";
    private static final int FONT_SIZE = 55;
    private static final Logger LOGGER = Logger.getLogger(Scores.class.getName());

    private final transient Image scorImage;
    private final JTextArea score;

    /**
     * Constructor of Score Table.
     * 
     * @param view is used to return back to the Menu
     */
    public Scores(final SwingViewImpl view) {

        this.setLayout(new BorderLayout());
        scorImage = new ImageIcon(getClass().getResource(BACKGROUND_IMAGE_PATH)).getImage();

        final JPanel pSouth = new JPanel(new FlowLayout(FlowLayout.CENTER));
        final JButton back = new GenericButton(BACK, b -> view.setScene(Scenes.MENU.getScene()));
        pSouth.setOpaque(false);
        pSouth.add(back);
        this.add(pSouth, BorderLayout.SOUTH);

        this.score = new BorderedLine();
        this.score.setEditable(false);
        this.score.setOpaque(false);
        this.score.setFont(new Font("Verdana", Font.PLAIN, FONT_SIZE));
        this.score.setBorder(null);

        final JScrollPane pane = new JScrollPane(score);
        this.add(pane, BorderLayout.CENTER); 
        pane.setOpaque(false);
        pane.getViewport().setOpaque(false);
        pane.setBorder(null);

        readFile(FILE_TXT_PATH);
    } 

    /**
     * Method for read content of a file.txt.
     * 
     * @param file is the path where is located the file to read.
     */
    public void readFile(final String file) {

        final Path path = Paths.get(System.getProperty("user.home"), ".migglione", "ScoreTable.txt");

        if (Files.exists(path)) {
            try (BufferedReader reader = Files.newBufferedReader(path)) {

                String line = reader.readLine();

                while (line != null) {
                    score.append(line + "\n");
                    line = reader.readLine();
                }
            } catch (final IOException error) {
                LOGGER.log(Level.SEVERE, "Error while reading file", error);
            }
        }
    }

    /**
     * Method for refresh scores table when you need.
     * 
     * @see SwingViewImpl
     */
    public void refresh() {
        score.setText("");
        readFile(FILE_TXT_PATH);
    }

    @Override
    public MusicPlayer getMusic() {
        return new LoopingMusicPlayerImpl(MusicTracks.ENA.getTrackPath());
    }

    @Override
    protected Image getImage() {
        return scorImage;
    }
}
