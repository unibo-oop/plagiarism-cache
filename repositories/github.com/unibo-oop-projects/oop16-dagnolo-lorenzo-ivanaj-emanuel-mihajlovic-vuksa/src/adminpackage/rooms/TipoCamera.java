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
import hotelmaster.pricing.RoomTypePriceDescriber;
import hotelmaster.structure.Hotel;
import hotelmaster.structure.HotelManager;
import hotelmaster.structure.RoomTemplate;

public class TipoCamera {
    private JFrame frame;
    private JPanel panel;
    private JPanel southPanel;
    private Dimension screenSize;
    private Map<JCheckBox, RoomTypePriceDescriber> listBox;
    private Integer numCamere;
    private Integer piano;
    private JButton conferma;
    private JButton annulla;
    private JButton esci;
    private Image okIcon;
    private Image backIcon;
    private Image exitIcon;
    private Integer piani;
    private Integer camere;
    private RoomTemplate room;

    /**/
    public TipoCamera(final int piano, final int camera, final RoomTemplate rooms) {
        this.frame = new JFrame();
        this.frame.setSize(new Dimension(500, 500));
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.frame.setLocation((screenSize.width / 2) - (this.frame.getWidth() / 2),
                (this.screenSize.height / 2) - (this.frame.getHeight() / 2));
        this.piani = piano;
        this.camere = camera;
        this.room = rooms;
        this.panel = new JPanel();
        this.southPanel = new JPanel();
        this.numCamere = camere;
        this.piano = piani;
        this.listBox = new HashMap<>();
        this.okIcon = new ImageIcon(this.getClass().getResource("/icons/add.png")).getImage();
        this.backIcon = new ImageIcon(this.getClass().getResource("/icons/back.png")).getImage();
        this.exitIcon = new ImageIcon(this.getClass().getResource("/icons/exit.png")).getImage();
        for (final RoomTypePriceDescriber priceDescriber : Hotel.instance()
                .getPriceView(RoomTypePriceDescriber.class)) {
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
                if (listBox.keySet().stream().filter(checkbox -> checkbox.isSelected()).count() == 1) {
                    try {
                        this.room.setRoomType(listBox.get(
                                listBox.keySet().stream().filter(checkbox -> checkbox.isSelected()).findAny().get()));
                    } catch (MissingEntityException e) {

                        e.printStackTrace();
                    }
                } else {

                    JOptionPane.showMessageDialog(frame, "Puoi selezionare solo un tipo", "Errore selezione",
                            JOptionPane.ERROR_MESSAGE);
                }
                try {
                    HotelManager.create().addRooms(this.piano, this.numCamere, this.room);
                } catch (MissingEntityException e) {
                    e.printStackTrace();
                } catch (IllegalArgumentException e) {
                    JOptionPane.showMessageDialog(frame, "Hai inserito un numero di camera o piano sbagliato",
                            "Errore creazione supplemento camera", JOptionPane.ERROR_MESSAGE);
                    this.frame.setVisible(false);
                    this.frame.dispose();
                    new CreaCamera();
                }
            }
        });
        this.annulla.addActionListener(b -> {
            int risp = JOptionPane.showConfirmDialog(this.frame, "Sei sicuro di volere tornare indietro?", "Uscita",
                    JOptionPane.YES_OPTION);
            if (risp == JOptionPane.YES_OPTION) {
                this.frame.setVisible(false);
                this.frame.dispose();
                new AggiuntaInterni(this.numCamere, this.piano);
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
    }public static void main(String[] args){
        new TipoCamera(1, 1, RoomTemplate.create());
    }
}
