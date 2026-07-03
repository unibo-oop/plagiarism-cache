package it.unibo.apice.oop.machikoro.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Class used for choose skin.
 **/
public class GUISettings extends JFrame {

    /**
     * Global Variables
     **/
    private static final long serialVersionUID = 1L;
    private final Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    private final JPanel panel = new JPanel(new BorderLayout());
    private final JButton button1 = new ButtonChanged();
    private final JButton button2 = new ButtonChanged();
    private GUIChoosePlayers guiForSkin;
    private static final int X = 100;
    private static final int Y = 100;
    private static final int W = 450;
    private static final int H = 300;
    private static final int WINDOWX = 464;
    private static final int WINDOWY = 364;

    /**
     * Lancia l'applicazione.
     * 
     * @param gcp
     *            Oggetto GUIChoosePlayers usato per memorizzare la skin che il
     *            player ha selezionato.
     */
    public void start(final GUIChoosePlayers gcp) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    guiForSkin = gcp;
                    setVisible(true);
                } catch (Exception e) {
                    System.out.println("Launch error");
                }
            }
        });
    }

    /**
     * Crea l'applicazione.
     * 
     * @throws IOException
     *             Eccezione che può essere causata.
     */
    public GUISettings() throws IOException {
        super();
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     * 
     * @throws IOException
     */
    private void initialize() throws IOException {
        Image imgtorre;
        Image imgtorre2;
        // Titolo.
        setTitle("Choose Skin");
        this.setBounds(X, Y, W, H);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        // La finestra non sarà ridimensionabile.
        this.setResizable(false);
        setSize(WINDOWX, WINDOWY);
        final int x = (int) ((dimension.getWidth() - getWidth()) / 2);
        final int y = (int) ((dimension.getHeight() - getHeight()) / 2);
        setLocation(x, y);
        this.setAlwaysOnTop(true);
        getContentPane().add(panel);
        // Lettura immagine.
        imgtorre = ImageIO.read(this.getClass().getResource("/RadioTower.png"));
        imgtorre2 = ImageIO.read(this.getClass().getResource("/RadioTowerCesena.png"));
        button1.setIcon(new ImageIcon(imgtorre));
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                guiForSkin.pickSkin(1);
                dispose();
            }
        });
        button2.setIcon(new ImageIcon(imgtorre2));
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent arg0) {
                guiForSkin.pickSkin(2);
                dispose();
            }
        });
        panel.add(button1, BorderLayout.WEST);
        panel.add(button2, BorderLayout.EAST);

    }

}
