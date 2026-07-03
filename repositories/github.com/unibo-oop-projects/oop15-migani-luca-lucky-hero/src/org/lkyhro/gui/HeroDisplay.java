package org.lkyhro.gui;

import org.lkyhro.Hero;
//import org.lkyhro.item.Item;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.util.Map;

/**
 * Created by Migani Luca on 24/02/2016.
 */
public class HeroDisplay implements InventoryPanelObserver {
    private JFrame inventoryFrame;
    private JPanel mainPanel;
    private JTextField nameF;
    private JTextField healthPointsF;
    private JTextField attackF;
    private JTextField defenseF;
    private JTextField luckF;
    private JTextField expF;
    private JTextField levelF;
    private JTextField nextLvlF;
    private JLabel nameL;
    private JLabel healthPointsL;
    private JLabel attackL;
    private JLabel defenseL;
    private JLabel luckL;
    private JLabel levelL;
    private JLabel expL;
    private JLabel nextLvlL;
    private JButton inventoryB;
    private JButton backB;
    private JTextField helmF;
    private JTextField armorF;
    private JLabel helmL;
    private JLabel armorL;
    private JPanel buttonPanel;
    private JLabel glovesL;
    private JLabel bootsL;
    private JLabel weaponL;
    private JLabel shieldL;
    private JTextField glovesF;
    private JTextField weaponF;
    private JTextField shieldF;
    private JTextField bootsF;
    private JPanel valuePanel;
    private Hero hero;

    /**
     * Constructor method for HeroDisplay
     * @param initialGUI InitialGui InitialGui is the first and principle gui of the program
     * @param hero Hero the hero used by the player
     */
    public HeroDisplay(InitialGUI initialGUI, Hero hero) {
        this.hero = hero;
        this.nameL.setText("Name:");
        this.healthPointsL.setText("Hp:");
        this.attackL.setText("Attack:");
        this.defenseL.setText("Defense:");
        this.luckL.setText("Luck:");
        this.levelL.setText("Level:");
        this.expL.setText("Exp:");
        this.nextLvlL.setText("Exp. to next lvl:");
        this.helmL.setText("Helm:");
        this.armorL.setText("Armor:");
        this.glovesL.setText("Gloves:");
        this.bootsL.setText("Boots:");
        this.weaponL.setText("Weapon:");
        this.shieldL.setText("Shield:");


        this.nameF.setText(hero.getName());
        this.nameF.setEditable(false);
        this.healthPointsF.setText("" + hero.getHealthPoint());
        this.healthPointsF.setEditable(false);
        this.attackF.setText("" + hero.getAttack());
        this.attackF.setEditable(false);
        this.defenseF.setText("" + hero.getDefense());
        this.defenseF.setEditable(false);
        this.luckF.setText("" + hero.getLuck());
        this.luckF.setEditable(false);
        this.levelF.setText("" + hero.getLevel());
        this.levelF.setEditable(false);
        this.expF.setText("" + hero.getExperience());
        this.expF.setEditable(false);
        this.nextLvlF.setText("" + hero.getExpToNextLvl());
        this.nextLvlF.setEditable(false);
        if (hero.getHelm() != null) {
            this.helmF.setText(hero.getHelm().getName());
        } else {
            this.helmF.setText("");
        }
        this.helmF.setEditable(false);
        if (hero.getArmor() != null) {
            this.armorF.setText(hero.getArmor().getName());
        } else {
            this.armorF.setText("");
        }
        this.armorF.setEditable(false);
        if (hero.getGloves() != null) {
            this.glovesF.setText(hero.getGloves().getName());
        } else {
            this.glovesF.setText("");
        }
        this.glovesF.setEditable(false);
        if (hero.getBoots() != null) {
            this.bootsF.setText(hero.getBoots().getName());
        } else {
            this.bootsF.setText("");
        }
        this.bootsF.setEditable(false);
        if (hero.getWeapon() != null) {
            this.weaponF.setText(hero.getWeapon().getName());
        } else {
            this.weaponF.setText("");
        }
        this.weaponF.setEditable(false);
        if (hero.getShield() != null) {
            this.shieldF.setText(hero.getShield().getName());
        } else {
            this.shieldF.setText("");
        }
        this.shieldF.setEditable(false);


        this.inventoryB.setText("Inventory");
        this.backB.setText("Back");

        inventoryB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (hero.getInventory().isEmpty()) {
                    JOptionPane.showMessageDialog(mainPanel, "Inventory is empty");
                } else {
                    backB.setEnabled(false);
                    inventoryFrame = new JFrame();
                    inventoryFrame.setSize(300, 200);
                    InventoryPanel inventoryPanel = new InventoryPanel(hero.getInventory(), null, hero, false);
                    inventoryFrame.add(inventoryPanel.$$$getRootComponent$$$());
                    inventoryPanel.setObserver(HeroDisplay.this);
                    inventoryFrame.setVisible(true);
                }
            }
        });


        backB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initialGUI.switchPanel(new BaseGameMenu(initialGUI, hero).$$$getRootComponent$$$());
            }
        });
    }

    /**
     * Method used to update the data displayed in the panel, used mainly after equipping or deleting an equipment
     * @param hero Hero the hero used by the player
     */
    private void guiUpdate(Hero hero) {
        this.nameF.setText(hero.getName());
        this.nameF.setEditable(false);
        this.healthPointsF.setText("" + hero.getHealthPoint());
        this.healthPointsF.setEditable(false);
        this.attackF.setText("" + hero.getAttack());
        this.attackF.setEditable(false);
        this.defenseF.setText("" + hero.getDefense());
        this.defenseF.setEditable(false);
        this.luckF.setText("" + hero.getLuck());
        this.luckF.setEditable(false);
        this.levelF.setText("" + hero.getLevel());
        this.levelF.setEditable(false);
        this.expF.setText("" + hero.getExperience());
        this.expF.setEditable(false);
        this.nextLvlF.setText("" + hero.getExpToNextLvl());
        this.nextLvlF.setEditable(false);
        if (hero.getHelm() != null) {
            this.helmF.setText(hero.getHelm().getName());
        } else {
            this.helmF.setText("");
        }
        this.helmF.setEditable(false);
        if (hero.getArmor() != null) {
            this.armorF.setText(hero.getArmor().getName());
        } else {
            this.armorF.setText("");
        }
        this.armorF.setEditable(false);
        if (hero.getGloves() != null) {
            this.glovesF.setText(hero.getGloves().getName());
        } else {
            this.glovesF.setText("");
        }
        this.glovesF.setEditable(false);
        if (hero.getBoots() != null) {
            this.bootsF.setText(hero.getBoots().getName());
        } else {
            this.bootsF.setText("");
        }
        this.bootsF.setEditable(false);
        if (hero.getWeapon() != null) {
            this.weaponF.setText(hero.getWeapon().getName());
        } else {
            this.weaponF.setText("");
        }
        this.weaponF.setEditable(false);
        if (hero.getShield() != null) {
            this.shieldF.setText(hero.getShield().getName());
        } else {
            this.shieldF.setText("");
        }
        this.shieldF.setEditable(false);
        valuePanel.revalidate();
        valuePanel.repaint();
    }

    /**
     * This method is used when the use of inventoryPanel has ended, it closes inventoryFrame and re-enable buttons
     * on the HeroDisplay panel, then updates the gui by using guiUpdate();
     */
    @Override
    public void inventoryActionDone() {
        inventoryFrame.setVisible(false);
        inventoryB.setEnabled(true);
        backB.setEnabled(true);
        guiUpdate(hero);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createTitledBorder("Hero Display"));
        valuePanel = new JPanel();
        valuePanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(valuePanel, gbc);
        nameL = new JLabel();
        nameL.setText("Label");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        valuePanel.add(nameL, gbc);
        final JPanel spacer1 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        valuePanel.add(spacer1, gbc);
        final JPanel spacer2 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 9;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        valuePanel.add(spacer2, gbc);
        final JPanel spacer3 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 10;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        valuePanel.add(spacer3, gbc);
        healthPointsL = new JLabel();
        healthPointsL.setText("Label");
        gbc = new GridBagConstraints();
        gbc.gridx = 11;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        valuePanel.add(healthPointsL, gbc);
        attackL = new JLabel();
        attackL.setText("Label");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        valuePanel.add(attackL, gbc);
        final JPanel spacer4 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        valuePanel.add(spacer4, gbc);
        luckL = new JLabel();
        luckL.setText("Label");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        valuePanel.add(luckL, gbc);
        nameF = new JTextField();
        nameF.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        valuePanel.add(nameF, gbc);
        final JPanel spacer5 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.VERTICAL;
        valuePanel.add(spacer5, gbc);
        attackF = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        valuePanel.add(attackF, gbc);
        final JPanel spacer6 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.fill = GridBagConstraints.VERTICAL;
        valuePanel.add(spacer6, gbc);
        expL = new JLabel();
        expL.setText("Label");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        valuePanel.add(expL, gbc);
        luckF = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.gridwidth = 5;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        valuePanel.add(luckF, gbc);
        expF = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 6;
        gbc.gridwidth = 5;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        valuePanel.add(expF, gbc);
        defenseL = new JLabel();
        defenseL.setText("Label");
        gbc = new GridBagConstraints();
        gbc.gridx = 11;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        valuePanel.add(defenseL, gbc);
        levelL = new JLabel();
        levelL.setText("Label");
        gbc = new GridBagConstraints();
        gbc.gridx = 11;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        valuePanel.add(levelL, gbc);
        nextLvlL = new JLabel();
        nextLvlL.setText("Label");
        gbc = new GridBagConstraints();
        gbc.gridx = 11;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.WEST;
        valuePanel.add(nextLvlL, gbc);
        levelF = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 13;
        gbc.gridy = 4;
        gbc.gridwidth = 8;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        valuePanel.add(levelF, gbc);
        nextLvlF = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 13;
        gbc.gridy = 6;
        gbc.gridwidth = 8;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        valuePanel.add(nextLvlF, gbc);
        final JPanel spacer7 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 12;
        gbc.gridy = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        valuePanel.add(spacer7, gbc);
        defenseF = new JTextField();
        defenseF.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 13;
        gbc.gridy = 2;
        gbc.gridwidth = 7;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        valuePanel.add(defenseF, gbc);
        healthPointsF = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 13;
        gbc.gridy = 0;
        gbc.gridwidth = 7;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        valuePanel.add(healthPointsF, gbc);
        final JPanel spacer8 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.fill = GridBagConstraints.VERTICAL;
        valuePanel.add(spacer8, gbc);
        helmL = new JLabel();
        helmL.setText("Label");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.WEST;
        valuePanel.add(helmL, gbc);
        armorL = new JLabel();
        armorL.setText("Label");
        gbc = new GridBagConstraints();
        gbc.gridx = 11;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.WEST;
        valuePanel.add(armorL, gbc);
        helmF = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 8;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        valuePanel.add(helmF, gbc);
        armorF = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 13;
        gbc.gridy = 8;
        gbc.gridwidth = 5;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        valuePanel.add(armorF, gbc);
        final JPanel spacer9 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.fill = GridBagConstraints.VERTICAL;
        valuePanel.add(spacer9, gbc);
        glovesL = new JLabel();
        glovesL.setText("Label");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 10;
        gbc.anchor = GridBagConstraints.WEST;
        valuePanel.add(glovesL, gbc);
        final JPanel spacer10 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 11;
        gbc.fill = GridBagConstraints.VERTICAL;
        valuePanel.add(spacer10, gbc);
        shieldL = new JLabel();
        shieldL.setText("Label");
        gbc = new GridBagConstraints();
        gbc.gridx = 11;
        gbc.gridy = 12;
        gbc.anchor = GridBagConstraints.WEST;
        valuePanel.add(shieldL, gbc);
        glovesF = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 10;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        valuePanel.add(glovesF, gbc);
        weaponF = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 12;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        valuePanel.add(weaponF, gbc);
        shieldF = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 13;
        gbc.gridy = 12;
        gbc.gridwidth = 4;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        valuePanel.add(shieldF, gbc);
        bootsF = new JTextField();
        gbc = new GridBagConstraints();
        gbc.gridx = 13;
        gbc.gridy = 10;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        valuePanel.add(bootsF, gbc);
        bootsL = new JLabel();
        bootsL.setText("Label");
        gbc = new GridBagConstraints();
        gbc.gridx = 11;
        gbc.gridy = 10;
        gbc.anchor = GridBagConstraints.WEST;
        valuePanel.add(bootsL, gbc);
        weaponL = new JLabel();
        weaponL.setText("Label");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 12;
        gbc.anchor = GridBagConstraints.WEST;
        valuePanel.add(weaponL, gbc);
        final JPanel spacer11 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        mainPanel.add(spacer11, gbc);
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(buttonPanel, gbc);
        final JPanel spacer12 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        buttonPanel.add(spacer12, gbc);
        inventoryB = new JButton();
        inventoryB.setText("Button");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        buttonPanel.add(inventoryB, gbc);
        backB = new JButton();
        backB.setText("Button");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        buttonPanel.add(backB, gbc);
    }

    /**
     * @return JComponent the major panel of this class
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }
}
