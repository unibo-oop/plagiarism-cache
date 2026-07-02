package migglione.view.impl.scenesimpl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import javax.swing.JButton;
import javax.swing.JLabel;

import migglione.controller.api.Controller;
import migglione.view.api.music.MusicPlayer;
import migglione.view.api.music.MusicProvider;
import migglione.view.api.music.MusicTracks;
import migglione.view.api.scenes.Scenes;
import migglione.view.impl.SwingViewImpl;
import migglione.view.impl.musicimpl.LoopingMusicPlayerImpl;

/**
 * Class designed to show after the player chooses Start Game in the menu.
 * 
 * <p>
 * Its design is such that it is merely a passage point
 * between the real game field and the menu, so that the
 * player can insert their name or go back.
 */
public final class StartGame extends AbstractGamePanel implements MusicProvider {

    private static final long serialVersionUID = 9879879800L;
    private static final String BACKGROUND_IMAGE_PATH = "/images/utilities/title.png";
    private static final String FONT_NAME = "Times New Roman";
    private static final String BACK = "Back";
    private static final int REQUEST_SIZE = 120;
    private static final int REQUEST_TEXT_SIZE = 30;
    private static final int TEXT_SIZE = 24;
    private static final int TEXT_AREA_WIDTH = 300;
    private static final int TEXT_AREA_HEIGHT = 80;
    private static final int MAX_CHARACTERS = 15;
    private final transient Image startImage;

    private final JTextField textField;
    private final JButton start;

    /**
     * Constructor of StartGame.
     * 
     * @param view is used to return back to the Menu
     * @param controller is used to notify it of the start of
     *                   the game and give it the player's name
     */
    public StartGame(final SwingViewImpl view, final Controller controller) {
        this.setLayout(new BorderLayout());

        startImage = new ImageIcon(getClass().getResource(BACKGROUND_IMAGE_PATH)).getImage();

        final JPanel pCenter = new JPanel();
        pCenter.setLayout(new BoxLayout(pCenter, BoxLayout.Y_AXIS));
        pCenter.setOpaque(false);

        final JLabel request = new JLabel("Enter your name (max 15 chars):");
        request.setFont(new Font(FONT_NAME, Font.PLAIN, REQUEST_TEXT_SIZE));
        request.setForeground(Color.YELLOW);
        request.setHorizontalAlignment(JLabel.CENTER);

        final JPanel requestBox = new JPanel(new BorderLayout());
        requestBox.setMaximumSize(new Dimension(Integer.MAX_VALUE, REQUEST_SIZE));
        requestBox.setBackground(Color.BLACK);
        requestBox.add(request);

        this.textField = new JTextField();
        textField.setMaximumSize(new Dimension(TEXT_AREA_WIDTH, TEXT_AREA_HEIGHT));
        textField.setFont(new Font(FONT_NAME, Font.PLAIN, TEXT_SIZE));
        textField.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
        textField.setBackground(Color.BLACK);
        textField.setForeground(Color.YELLOW);
        textField.setAlignmentX(CENTER_ALIGNMENT);
        textField.setDocument(new PlainDocument() {
            @Override
            public void insertString(final int offs, final String s, final AttributeSet attr)
            throws BadLocationException {
                if (s == null) {
                    return;
                }
                if ((getLength() + s.length()) <= MAX_CHARACTERS) {
                    super.insertString(offs, s, attr);
                }
            }
        });

        this.start = new GenericButton("Start!", a -> {
            controller.startSession(textField.getText());
            view.setScene(Scenes.FIELD.getScene());
        });
        start.setEnabled(false);

        textField.getDocument().addDocumentListener(new DocumentListener() {

            public void update() {
                start.setEnabled(!textField.getText().isBlank());
            }

            @Override
            public void insertUpdate(final DocumentEvent e) {
                update();
            }

            @Override
            public void removeUpdate(final DocumentEvent e) {
                update();
            }

            @Override
            public void changedUpdate(final DocumentEvent e) {
                update();
            }
        });

        pCenter.add(Box.createVerticalGlue());
        pCenter.add(requestBox);
        pCenter.add(Box.createVerticalGlue());
        pCenter.add(textField);
        pCenter.add(Box.createVerticalGlue());
        pCenter.add(start);

        final JPanel pSouth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        final JButton back = new GenericButton(BACK, b -> view.setScene(Scenes.MENU.getScene()));
        pSouth.setOpaque(false);
        pSouth.add(back);

        this.add(pSouth, BorderLayout.SOUTH);
        this.add(pCenter, BorderLayout.CENTER);
    }

    /**
     * Method used by SwingViewImpl.
     * 
     * <p>
     * It allows the area of the text and the button
     * start to be reset after the player return to the
     * home and then re-enter StartGame
     */
    public void reset() {
        this.textField.setText("");
        this.start.setEnabled(false);
    }

    @Override
    public MusicPlayer getMusic() {
        return new LoopingMusicPlayerImpl(MusicTracks.ENA.getTrackPath());
    }

    @Override
    protected Image getImage() {
        return startImage;
    }
}
