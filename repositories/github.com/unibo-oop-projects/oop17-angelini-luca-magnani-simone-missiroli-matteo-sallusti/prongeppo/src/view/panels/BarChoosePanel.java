package view.panels;

import java.awt.Color;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.GraphicManager;
import utility.PlayerType;
import view.PongElement;
import utility.BarRes;
import utility.GameValues;
import utility.GraphicType;

/**
 * @author Luca
 *
 */
public final class BarChoosePanel extends JPanel {
    /**
     * 
     */
    private static final long serialVersionUID = -5969623347088534753L;
    private final JComboBox<String> box;
    private PongElement bar;
    private int count;
    private final GraphicManager gmanager;
    /**
     * @param gmanager 
     * 
     */
    public BarChoosePanel(final GraphicManager gmanager) {
        super();
        this.box = new JComboBox<String>();
        this.gmanager = gmanager;
        final List<PlayerType> playerTypeList = new LinkedList<>(Arrays.asList(PlayerType.values()));
        playerTypeList.forEach(type -> this.box.addItem(type.toString()));
        final List<BarRes> resList = new LinkedList<>(Arrays.asList(BarRes.values()));
        final JButton next = utility.Utilities.initButton(">", Color.BLACK, Color.BLUE, GameValues.FONT_SMALLEST, null);
        final JButton prev = utility.Utilities.initButton("<", Color.BLACK, Color.BLUE, GameValues.FONT_SMALLEST, null);
        this.bar = this.getNewBar(resList);
        final JLabel label = new JLabel(new ImageIcon(this.bar.getImage()));
        next.addActionListener(e -> {
            this.removeBar();
            this.nextBar(resList, label);
            });
        prev.addActionListener(e -> {
            this.removeBar();
            this.previousBar(resList, label);
            });
        this.add(prev);
        this.add(label);
        this.add(next);
        this.add(this.box);
        this.setBackground(Color.BLACK);
        this.setVisible(true);
    }


    private void removeBar() {
        this.gmanager.removePongElement(Arrays.asList(this.bar));
    }


    private void nextBar(final List<BarRes> resList, final JLabel label) {
        this.count = utility.Utilities.updateItToList(this.count, resList, true);
        this.bar = this.getNewBar(resList);
        label.setIcon(new ImageIcon(this.bar.getImage()));
        label.revalidate();
    }
    private void previousBar(final List<BarRes> resList, final JLabel label) {
        this.count = utility.Utilities.updateItToList(this.count, resList, false);
        this.bar = this.getNewBar(resList);
        label.setIcon(new ImageIcon(this.bar.getImage()));
        label.revalidate();
    }


    /**
     * @return The PlayerType inside combo box
     */
    public PlayerType getBoxItem() {
        return PlayerType.valueOf(box.getSelectedItem().toString());
    }

    /**
     * @return the bar
     */
    public Optional<PongElement> getBarElem() {
        if (this.getBoxItem().equals(PlayerType.EMPTY)) { 
            this.removeBar(); 
            return Optional.empty(); 
        } else {
            return Optional.of(this.bar);
        }
    }

    private PongElement getNewBar(final List<BarRes> resList) {
        return gmanager.createPongElement(resList.get(this.count) + ".png", GraphicType.BAR);
    }
}
