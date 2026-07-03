package it.lttply.view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

import it.lttply.model.utils.ConsolePrinter;
import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;
import uk.co.caprica.vlcj.player.TrackDescription;
import uk.co.caprica.vlcj.player.embedded.DefaultAdaptiveRuntimeFullScreenStrategy;

/**
 * Player which embeds a VLC instance into it.
 */
public class PlayerMedia {

    private static final String UNABLE_TO_REPRODUCE = "Unable to reproduce ";

    private static final int WAIT_TIME_MULTIPLIER = 20;

    private static final int REFRESH_TIME = 100;

    /**
     * The required UID.
     */
    @SuppressWarnings("unused")
    private static final long serialVersionUID = -5929037521788909839L;

    private static final double SCALING_FACTOR = 0.25;
    private static final int SKIPPING_MILLISECS = 10000;

    private final EmbeddedMediaPlayerComponent mediaPlayerComponent;
    private final JLabel time;
    private final JPanel videoInfos;
    private JButton pauseButton;
    private final JComboBox<String> languages;
    private final List<String> lstLanguages;
    private Thread timeElapsedThread;
    private final String path;

    /**
     * Creates the player and reproduces the content.
     *
     * @param title
     *            the title of the media file to be reproduced
     * @param path
     *            the physical location of the media file to be reproduced
     * @throws InterruptedException
     */
    public PlayerMedia(final String title, final String path) {
        // Creating the frame and primary containers
        this.path = path;
        timeElapsedThread = null;
        final JFrame frame = new JFrame(title);
        frame.setAlwaysOnTop(true);
        frame.setBounds(100, 100, (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
                (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight());
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(final WindowEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void windowIconified(final WindowEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void windowDeiconified(final WindowEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void windowDeactivated(final WindowEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void windowClosing(final WindowEvent e) {
                JOptionPane.showMessageDialog(frame, "If you wanna close the player, use the proper \"Stop\" button.",
                        "Not allowed!", JOptionPane.WARNING_MESSAGE);

            }

            @Override
            public void windowClosed(final WindowEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void windowActivated(final WindowEvent e) {
                // TODO Auto-generated method stub

            }
        });

        final JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        frame.setContentPane(contentPane);

        // Creating the media player
        mediaPlayerComponent = new EmbeddedMediaPlayerComponent();
        mediaPlayerComponent.getMediaPlayer()
                .setFullScreenStrategy(new DefaultAdaptiveRuntimeFullScreenStrategy(frame));
        contentPane.add(mediaPlayerComponent, BorderLayout.CENTER);

        // Creating informations panel
        videoInfos = new JPanel();
        videoInfos.setLayout(new BoxLayout(videoInfos, BoxLayout.Y_AXIS));
        contentPane.add(videoInfos, BorderLayout.EAST);

        final JPanel controlsPane = new JPanel();

        // Creating player buttons
        try {
            pauseButton = createButton("view/pause.png");
        } catch (final IOException e1) {
            ConsolePrinter.getPrinter().printlnError(UNABLE_TO_REPRODUCE + title);
        }

        JButton rewindButton = new JButton();
        try {
            rewindButton = createButton("view/rew.png");
        } catch (final IOException e1) {
            ConsolePrinter.getPrinter().printlnError(UNABLE_TO_REPRODUCE + title);
        }

        JButton skipButton = new JButton();
        try {
            skipButton = createButton("view/ff.png");
        } catch (final IOException e1) {
            ConsolePrinter.getPrinter().printlnError(UNABLE_TO_REPRODUCE + title);
        }

        JButton stopButton = new JButton();
        try {
            stopButton = createButton("view/stop.png");
        } catch (final IOException e1) {
            ConsolePrinter.getPrinter().printlnError(UNABLE_TO_REPRODUCE + title);
        }

        JButton infoButton = new JButton();
        try {
            infoButton = createButton("view/info.png");
        } catch (final IOException e1) {
            ConsolePrinter.getPrinter().printlnError(UNABLE_TO_REPRODUCE + title);
        }

        lstLanguages = new LinkedList<>();
        languages = new JComboBox<>();

        // Adding listeners
        pauseButton.addActionListener(e -> {
            Image icon = null;
            if (!mediaPlayerComponent.getMediaPlayer().isPlaying()) {
                try {
                    icon = ImageIO.read(ClassLoader.getSystemResource("view/pause.png"));
                } catch (final IOException e1) {
                    ConsolePrinter.getPrinter().printlnError("An error occurred while playing " + title);
                }
                final Image newimg = icon.getScaledInstance((int) (icon.getWidth(null) * SCALING_FACTOR),
                        (int) (icon.getHeight(null) * SCALING_FACTOR), Image.SCALE_SMOOTH);
                pauseButton.setIcon(new ImageIcon(newimg));
            } else {
                try {
                    icon = ImageIO.read(ClassLoader.getSystemResource("view/play.png"));
                } catch (final IOException e1) {
                    ConsolePrinter.getPrinter().printlnError("An error occurred while playing " + title);
                }
                final Image newimg = icon.getScaledInstance((int) (icon.getWidth(null) * SCALING_FACTOR),
                        (int) (icon.getHeight(null) * SCALING_FACTOR), Image.SCALE_SMOOTH);
                pauseButton.setIcon(new ImageIcon(newimg));
            }
            mediaPlayerComponent.getMediaPlayer().pause();
        });

        rewindButton.addActionListener(e -> {
            mediaPlayerComponent.getMediaPlayer().skip(-SKIPPING_MILLISECS);
        });

        skipButton.addActionListener(e -> mediaPlayerComponent.getMediaPlayer().skip(SKIPPING_MILLISECS));

        stopButton.addActionListener(e -> {
            mediaPlayerComponent.getMediaPlayer().stop();

            try {
                Thread.sleep(REFRESH_TIME * (WAIT_TIME_MULTIPLIER / 2));
            } catch (InterruptedException ex) {
                ConsolePrinter.getPrinter().printlnError("Unable to close the player, try again!");
            }
            frame.dispose();
        });

        infoButton.addActionListener(e -> {
            videoInfos.setVisible(!videoInfos.isVisible());
        });

        // Adding buttons to the panels
        controlsPane.add(pauseButton);
        controlsPane.add(rewindButton);
        controlsPane.add(skipButton);
        controlsPane.add(stopButton);
        controlsPane.add(infoButton);
        controlsPane.add(new JLabel("Languages:"));
        controlsPane.add(languages);

        languages.addActionListener(e -> {
            @SuppressWarnings("unchecked")
            final JComboBox<String> combo = (JComboBox<String>) e.getSource();
            final String lang = (String) combo.getSelectedItem();
            mediaPlayerComponent.getMediaPlayer().setAudioTrack(lstLanguages.indexOf(lang));
        });

        final JPanel southSouthPanel = new JPanel(new BorderLayout());
        final JPanel southCenterPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        final JPanel southLeftPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        southSouthPanel.add(southCenterPanel, BorderLayout.EAST);
        southSouthPanel.add(controlsPane, BorderLayout.SOUTH);
        southSouthPanel.add(southLeftPanel, BorderLayout.WEST);
        contentPane.add(southSouthPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
        time = new JLabel();
        southLeftPanel.add(time);

    }

    /**
     * Starts the thread which manages the refreshing of the elapsed time and
     * informations.
     */
    public void startRefresher() {
        timeElapsedThread = new Thread(() -> {
            while (!mediaPlayerComponent.getMediaPlayer().isPlaying()) {
                try {
                    Thread.sleep(REFRESH_TIME);
                } catch (final InterruptedException e) {
                    ConsolePrinter.getPrinter().printlnError(
                            "An error occurred while playing the video, some functions may not work correctly");
                }
            }

            videoInfos.add(new JLabel("  ### VIDEO INFORMATIONS ###  "));
            videoInfos.add(new JLabel(
                    "Resolution: " + (int) mediaPlayerComponent.getMediaPlayer().getVideoDimension().getWidth() + "x"
                            + (int) mediaPlayerComponent.getMediaPlayer().getVideoDimension().getHeight()));

            videoInfos.add(new JLabel("FPS: " + mediaPlayerComponent.getMediaPlayer().getFps()));

            mediaPlayerComponent.getMediaPlayer().getAudioDescriptions().stream().map(TrackDescription::description)
                    .forEach(t -> {
                        languages.addItem(t);
                        lstLanguages.add(t);
                    });

            final String tracks = mediaPlayerComponent.getMediaPlayer().getAudioDescriptions().stream()
                    .map(TrackDescription::description).collect(Collectors.joining(", "));
            videoInfos.add(new JLabel("Tracks: " + tracks));

            while (mediaPlayerComponent.getMediaPlayer().isPlaying()) {
                time.setText(getTime(mediaPlayerComponent.getMediaPlayer().getTime()) + " - "
                        + getTime(mediaPlayerComponent.getMediaPlayer().getLength()));
                try {
                    Thread.sleep(REFRESH_TIME);
                } catch (final InterruptedException e1) {
                    ConsolePrinter.getPrinter().printlnError("Reproduction interrupted!");
                }
            }
        });

        timeElapsedThread.start();
    }

    /**
     * Reproduces the video.
     */
    public void play() {
        mediaPlayerComponent.getMediaPlayer().playMedia(path);
    }

    /**
     * @return <code>true</code> if the video is playing, <code>false</code>
     *         otherwise
     */
    public boolean isPlaying() {
        return mediaPlayerComponent.getMediaPlayer().isPlaying();
    }

    private static String getTime(final long value) {
        final PeriodFormatter fmt = new PeriodFormatterBuilder().printZeroAlways().minimumPrintedDigits(2).appendHours()
                .appendSeparator(":").printZeroAlways().minimumPrintedDigits(2).appendMinutes().appendSeparator(":")
                .printZeroAlways().minimumPrintedDigits(2).appendSeconds().toFormatter();
        return fmt.print(new Period(value));
    }

    private static JButton createButton(final String imageFromCP) throws IOException {
        final JButton button = new JButton();
        final Image icon = ImageIO.read(ClassLoader.getSystemResource(imageFromCP));
        final Image newimg = icon.getScaledInstance((int) (icon.getWidth(null) * SCALING_FACTOR),
                (int) (icon.getHeight(null) * SCALING_FACTOR), Image.SCALE_SMOOTH);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        button.setIcon(new ImageIcon(newimg));
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setOpaque(false);

        return button;
    }
}
