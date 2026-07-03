package adminpackage.rooms;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import adminpackage.mainview.Crea;
import adminpackage.roomextra.CreaSupplementoCamera;
import hotelmaster.exceptions.MissingEntityException;
import hotelmaster.pricing.RoomExtraPriceDescriber;
import hotelmaster.structure.Hotel;
import hotelmaster.structure.RoomTemplate;

public class AggiuntaInterni {

    private JFrame frame;
    private JPanel panel;
    private JPanel southPanel;
    private Dimension screenSize;
    private Map<JCheckBox, RoomExtraPriceDescriber> listBox;
    private Integer numCamere;
    private Integer piano;
    private JButton conferma;
    private JButton annulla;
    private JButton esci;
    private Image okIcon;
    private Image backIcon;
    private Image exitIcon;

    public AggiuntaInterni(final int camere, final int piani) {
        this.frame = new JFrame();
        this.frame.setSize(new Dimension(500, 500));
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.frame.setLocation((screenSize.width / 2) - (this.frame.getWidth() / 2),
                (this.screenSize.height / 2) - (this.frame.getHeight() / 2));
        this.panel = new JPanel();
        this.southPanel = new JPanel();
        this.numCamere = camere;
        this.piano = piani;
        this.listBox = new HashMap<>();
        this.okIcon = new ImageIcon(this.getClass().getResource("/icons/add.png")).getImage();
        this.backIcon = new ImageIcon(this.getClass().getResource("/icons/back.png")).getImage();
        this.exitIcon = new ImageIcon(this.getClass().getResource("/icons/exit.png")).getImage();
        for (final RoomExtraPriceDescriber priceDescriber : Hotel.instance()
                .getPriceView(RoomExtraPriceDescriber.class)) {
            JCheckBox tmp = new JCheckBox(priceDescriber.getDescription());
            listBox.put(tmp, priceDescriber);
            this.panel.add(tmp);
        }
        this.conferma = new JButton("");
        this.conferma.setIcon(new ImageIcon(this.okIcon));
        this.annulla = new JButton("");
        this.annulla.setIcon(new ImageIcon(this.backIcon));
        this.esci = new JButton("");
        this.esci.setIcon(new ImageIcon(this.exitIcon));
        this.conferma.addActionListener(a -> {
            int risp = JOptionPane.showConfirmDialog(this.frame, "Confermare?", "Conferma", JOptionPane.YES_OPTION);
            if (risp == JOptionPane.YES_OPTION) {
                RoomTemplate template = RoomTemplate.create();
                for (final JCheckBox priceCheckbox : listBox.keySet()) {
                    if (priceCheckbox.isSelected()) {
                        try {
                            template.addRoomExtra(listBox.get(priceCheckbox));
                        } catch (MissingEntityException | IllegalArgumentException e) {
                            e.printStackTrace();
                        }
                    }
                }
                this.frame.setVisible(false);
                this.frame.dispose();
                new TipoCamera(this.piano, this.numCamere, template);
            }
        });
        this.annulla.addActionListener(b -> {
            int risp = JOptionPane.showConfirmDialog(this.frame, "Sei sicuro di volere tornare indietro?", "Uscita",
                    JOptionPane.YES_OPTION);
            if (risp == JOptionPane.YES_OPTION) {
                this.frame.setVisible(false);
                this.frame.dispose();
                new CreaCamera();
            }
        });
        this.esci.addActionListener(c -> {
            int risp = JOptionPane.showConfirmDialog(this.frame, "Sei sicuro di volere uscire?", "Uscita",
                    JOptionPane.YES_OPTION);
            if (risp == JOptionPane.YES_OPTION) {
                this.frame.setVisible(false);
                this.frame.dispose();
                new Crea();
            }
        });
        this.southPanel.add(this.annulla);
        this.southPanel.add(this.esci);
        this.southPanel.add(this.conferma);
        this.frame.getContentPane().add(this.panel);
        this.frame.getContentPane().add(this.southPanel, BorderLayout.SOUTH);
        this.frame.setResizable(false);
        this.frame.setVisible(true);
    }

    public static void main(final String[] args) {
        new AggiuntaInterni(0, 0);
    }
}
