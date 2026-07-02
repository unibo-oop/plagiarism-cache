package it.unibo.the100dayswar.view.joystick;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import it.unibo.the100dayswar.application.The100DaysWar;
import it.unibo.the100dayswar.view.map.MapView;
import it.unibo.the100dayswar.view.statistics.StatisticsView;

/**
 * Class that represents the shop panel in the joystick view.
 * This panel allows the player to purchase or upgrade units.
 */
public class ShopView extends JPanel {
    private static final long serialVersionUID = 1L;
    private static final Dimension SIZE = new Dimension(200, 150);
    private static final Dimension BUTTON_SIZE = new Dimension(200, 55);
    private static final String ICON_BUTTON = "startmenu/genericbutton.jpg";
    private static final float FONT_SIZE = 10f;

    private final JButton buySoldier;
    private final JButton buyBasicTower;
    private final JButton buyAdvancedTower;
    private final JButton upgradeUnit;

    /**
     * Constructor for the ShopView class.
     * 
     * @param mapView the map view to repaint
     * @param statisticsView the statistics view to update
     */
    public ShopView(final MapView mapView, final StatisticsView statisticsView) {
        super.setLayout(new GridBagLayout());

        this.buySoldier = ButtonFactory.createCustomButton(
            "Buy Soldier", 
            ICON_BUTTON, 
            BUTTON_SIZE, 
            FONT_SIZE, 
            Color.WHITE
        );
        this.buyBasicTower = ButtonFactory.createCustomButton(
            "Buy Basic Tower", 
            ICON_BUTTON, 
            BUTTON_SIZE, 
            FONT_SIZE, 
            Color.WHITE
        );
        this.buyAdvancedTower = ButtonFactory.createCustomButton(
            "Buy Advanced Tower", 
            ICON_BUTTON, 
            BUTTON_SIZE, 
            FONT_SIZE, 
            Color.WHITE
        );
        this.upgradeUnit = ButtonFactory.createCustomButton(
            "Upgrade Unit", 
            ICON_BUTTON, 
            BUTTON_SIZE, 
            FONT_SIZE, 
            Color.WHITE
        );

        setButtonActions(mapView, statisticsView);
        setupLayout();
        super.setPreferredSize(SIZE);
    }

    /**
     * Disables all shop buttons.
     */
    public void disableButtons() {
        this.buyBasicTower.setEnabled(false);
        this.buySoldier.setEnabled(false);
        this.buyAdvancedTower.setEnabled(false);
        this.upgradeUnit.setEnabled(false);
    }

    /**
     * Enables all shop buttons.
     */
    public void enableButtons() {
        this.buyBasicTower.setEnabled(true);
        this.buySoldier.setEnabled(true);
        this.buyAdvancedTower.setEnabled(true);
        this.upgradeUnit.setEnabled(true);
    }

    /**
     * Sets the actions for the buttons.
     * 
     * @param mapView the map view to repaint
     * @param statisticsView the statistics view to update
     */
    private void setButtonActions(final MapView mapView, final StatisticsView statisticsView) {
        buySoldier.addActionListener(e -> {
            The100DaysWar.CONTROLLER.getShopController().buySoldier();
            mapView.repaint();
            statisticsView.updateStatisticView();
        });
        buyBasicTower.addActionListener(e -> {
            The100DaysWar.CONTROLLER.getShopController().buyBasicTower();
            mapView.repaint();
            statisticsView.updateStatisticView();
        });
        buyAdvancedTower.addActionListener(e -> {
            The100DaysWar.CONTROLLER.getShopController().buyAdvancedTower();
            mapView.repaint();
            statisticsView.updateStatisticView();
        });
        upgradeUnit.addActionListener(e -> {
            The100DaysWar.CONTROLLER.getShopController().upgradeUnit();
            mapView.repaint();
            statisticsView.updateStatisticView();
        });
    }

    /**
     * Arranges the buttons in the layout.
     */
    private void setupLayout() {
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        super.add(buySoldier, gbc);
        gbc.gridy = 1;
        super.add(buyBasicTower, gbc);
        gbc.gridy = 2;
        super.add(buyAdvancedTower, gbc);
        gbc.gridy = 3;
        super.add(upgradeUnit, gbc);
    }
}
