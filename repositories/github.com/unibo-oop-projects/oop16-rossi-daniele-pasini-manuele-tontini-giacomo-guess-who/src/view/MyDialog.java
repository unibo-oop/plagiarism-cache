package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.util.Optional;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.sun.awt.AWTUtilities;

import utilities.ResourceLoader;

/**
 * This class allows you to create 3 different specializations (Confirm,
 * Message, Win) of a custom dialog.
 *
 */
@SuppressWarnings("serial")
public final class MyDialog extends JDialog {
    private static final String IMAGE_PATH = "/images/";
    private static final int UNKNOWN_VALUE = -1;
    private static final int CONFIRM_VALUE = 0;
    private static final int REFUSE_VALUE = 1;
    private static final double BTNPAN_HEIGHTPROP = 0.08;
    private static final double MINWIDTH_PROP = 0.30;
    private static final double DIALOG_WIDTHPROP = 1.3;
    private static final double DIALOG_HEIGHTPROP = 0.5;
    private static final double IMGWIDTH_PROP = 0.10;
    private static final double IMGHEIGHT_PROP = 0.50;
    private static final double WINDIALOG_WITDTHPROP = 0.45;
    private static final double WINDIALOG_HEIGHTPROP = 0.45;

    private final JPanel messagePane;
    private final JPanel buttonsPane;
    private static int returnValue = UNKNOWN_VALUE;
    private static GUIFactory factory = GUIFactoryImpl.getFactory();

    private MyDialog(final Frame parent, final Dimension d) {
        super(parent, true);
        final FlowLayout btnPaneLayout = new FlowLayout();
        final GridBagLayout messagePaneLayout = new GridBagLayout();
        buttonsPane = factory.createPanel(Optional.of(btnPaneLayout));
        messagePane = factory.createPanel(Optional.of(messagePaneLayout));
        buttonsPane.setPreferredSize(
                new Dimension((int) parent.getWidth(), (int) (parent.getHeight() * BTNPAN_HEIGHTPROP)));
        setUndecorated(true);
        AWTUtilities.setWindowOpaque(this, false);
        final JPanel backgroundPane = new JPanel() {
            private Image image = new ImageIcon(ResourceLoader.loadImage(IMAGE_PATH + "DialogBackground.png"))
                    .getImage();

            @Override
            public void paintComponent(final Graphics g) {
                g.drawImage(image, 0, 0, (int) d.getWidth(), (int) d.getHeight(), this);
            }
        };
        backgroundPane.setLayout(new BorderLayout());
        backgroundPane.add(messagePane, BorderLayout.CENTER);
        backgroundPane.add(buttonsPane, BorderLayout.SOUTH);
        this.setPreferredSize(d);
        this.setContentPane(backgroundPane);
        this.pack();
        this.setLocationRelativeTo(parent);
    }

    @Override
    public Component add(final Component component) {
        if (component instanceof JButton) {
            this.buttonsPane.add(component);
        } else {
            this.messagePane.add(component, new GridBagConstraints());
        }
        return component;
    }

    private static Dimension calculateDimesion(final Frame parent, final JLabel lbl, final String message) {
        final java.awt.FontMetrics fm = lbl.getFontMetrics(lbl.getFont());
        int width = (int) (fm.stringWidth(message) * DIALOG_WIDTHPROP);
        int height = (int) (fm.stringWidth(message) * DIALOG_HEIGHTPROP);
        if (width < parent.getWidth() * MINWIDTH_PROP) {
            width = (int) (parent.getWidth() * MINWIDTH_PROP);
            height = (int) (width * DIALOG_HEIGHTPROP);
        }
        return new Dimension(width, height);
    }

    /**
     * @param parent
     *            The frame where the dialog should be displayed
     * @param message
     *            The dialog's message
     * @return 0 if the user pressed "yes" button 1 otherwise
     */
    public static int showConfirmDialog(final Frame parent, final String message) {
        final JButton jbtnYes = factory.createButton("Si");
        final JButton jbtnNo = factory.createButton("No");
        final JLabel jlblMessage = factory.createLabel(message);
        final MyDialog confDialog = new MyDialog(parent, calculateDimesion(parent, jlblMessage, message));
        jbtnYes.addActionListener(l -> {
            returnValue = CONFIRM_VALUE;
            confDialog.dispose();
        });
        jbtnNo.addActionListener(l -> {
            returnValue = REFUSE_VALUE;
            confDialog.dispose();
        });
        confDialog.add(jlblMessage);
        confDialog.add(jbtnYes);
        confDialog.add(jbtnNo);
        confDialog.setVisible(true);
        return returnValue;
    }

    /**
     * @param parent
     *            The frame where the dialog should be displayed
     * @param message
     *            The dialog's message
     * @return 0 if the user pressed "ok" button
     */
    public static int showMessageDialog(final Frame parent, final String message) {
        final JButton jbtnOk = factory.createButton("Ok");
        final JLabel jlblMessage = factory.createLabel(message);
        final MyDialog messageDialog = new MyDialog(parent, calculateDimesion(parent, jlblMessage, message));
        jbtnOk.addActionListener(l -> {
            returnValue = CONFIRM_VALUE;
            messageDialog.dispose();
        });
        messageDialog.add(jlblMessage);
        messageDialog.add(jbtnOk);
        messageDialog.setVisible(true);
        return returnValue;
    }

    /**
     * @param parent
     *            The frame where the dialog should be displayed
     * @param message
     *            The dialog's message (win/lose)
     * @param aiCharacter
     *            The Ai's character
     * @return 0 if the user pressed "yes" button 1 otherwise
     */
    public static int showWinDialog(final Frame parent, final String message, final ImageIcon aiCharacter) {
        final JPanel contentPane = factory.createPanel(Optional.empty());
        final BoxLayout contentPaneLayout = new BoxLayout(contentPane, BoxLayout.Y_AXIS);
        contentPane.setLayout(contentPaneLayout);
        final JLabel lblMessage = factory.createLabel(message);
        final MyDialog winDialog = new MyDialog(parent, new Dimension((int) (parent.getWidth() * WINDIALOG_WITDTHPROP),
                (int) (parent.getHeight() * WINDIALOG_HEIGHTPROP)));
        final MyLabel lblImage = new MyLabel(aiCharacter);
        lblImage.setPreferredSize(new Dimension((int) (winDialog.getPreferredSize().getWidth() * IMGWIDTH_PROP),
                (int) (winDialog.getPreferredSize().getHeight() * IMGHEIGHT_PROP)));
        lblImage.setAlignmentX(CENTER_ALIGNMENT);
        final JLabel lblRestartMessage = factory.createLabel("Vuoi iniziare un'altra partita?");
        final JButton jbClose = factory.createButton("No");
        final JButton jbPlay = factory.createButton("Si");
        jbClose.addActionListener(l -> {
            returnValue = REFUSE_VALUE;
            winDialog.dispose();
        });
        jbPlay.addActionListener(l -> {
            returnValue = CONFIRM_VALUE;
            winDialog.dispose();
        });
        contentPane.add(lblMessage);
        contentPane.add(lblImage);
        contentPane.add(lblRestartMessage);
        winDialog.add(contentPane);
        winDialog.add(jbPlay);
        winDialog.add(jbClose);
        winDialog.pack();
        winDialog.setLocationRelativeTo(parent);
        winDialog.setVisible(true);
        return returnValue;
    }
}
