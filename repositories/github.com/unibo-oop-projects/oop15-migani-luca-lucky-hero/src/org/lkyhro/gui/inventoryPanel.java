package org.lkyhro.gui;

import org.lkyhro.Encounter;
import org.lkyhro.Hero;
import org.lkyhro.item.Equipment;
import org.lkyhro.item.ItemType;
import org.lkyhro.item.Item;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Map;

/**
 * Created by Migani Luca on 28/02/2016.
 */
public class InventoryPanel {
    private JPanel mainPanel;
    private JComboBox<String> itemComboBox;
    private JButton useB;
    private JButton equipB;
    private JButton deleteB;
    private JButton cancelB;
    private JPanel comboPanel;
    private JPanel buttonPanel;
    private JTextArea description;
    private JLabel quantity;
    private boolean isBattle;

    private InventoryPanelObserver listener;

    /**
     * Constructor method for InventoryPanel
     * @param inventoryCopy Map copy of hero's inventory
     * @param encounter Encounter battle between hero and monster
     * @param hero Hero hero used by the player
     * @param isBattle Boolean to identify if the inventoryPanel is opened during a battle or not
     */
    public InventoryPanel(Map<Item, Integer> inventoryCopy, Encounter encounter, Hero hero, boolean isBattle) {
        this.isBattle = isBattle;
        if (isBattle) {
            equipB.setEnabled(false);
            deleteB.setEnabled(false);
        } else {
            useB.setEnabled(false);
        }
        String[] inventoryNames = new String[inventoryCopy.keySet().size()];
        int k = 0;
        for (Item i : inventoryCopy.keySet()) {
            inventoryNames[k] = i.getName();
            k++;
        }
        itemComboBox.setModel(new DefaultComboBoxModel<>(inventoryNames));
        panelUpdate(inventoryCopy, (String) itemComboBox.getSelectedItem());
        itemComboBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    panelUpdate(inventoryCopy, (String) e.getItem());
                }
            }
        });

        useB.setText("Use");
        useB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                encounter.useItem((String) itemComboBox.getSelectedItem());
                listener.inventoryActionDone();
            }
        });

        equipB.setText("Equip");
        equipB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hero.equipItem((Equipment) getItemFromInventory(inventoryCopy));
                listener.inventoryActionDone();
            }
        });

        deleteB.setText("Delete");
        deleteB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hero.deleteItem(getItemFromInventory(inventoryCopy));
                listener.inventoryActionDone();
            }
        });

        cancelB.setText("Cancel");
        cancelB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listener.inventoryActionDone();
            }
        });


    }

    /**
     * This method returns the item selected in the itemComboBox by picking it up from the hero inventory
     * @param inventoryCopy copy of hero's inventory
     * @return Item returned from the inventory
     */
    private Item getItemFromInventory(Map<Item, Integer> inventoryCopy) {
        for (Map.Entry<Item, Integer> entry : inventoryCopy.entrySet()) {
            if (entry.getKey().getName().equals(itemComboBox.getSelectedItem())) {
                return entry.getKey();
            }
        }
        return null;
    }

    /**
     * method to update panel's data when the item selected in itemComboBox changes
     * @param inventoryCopy copy of hero's inventory
     * @param itemName name of the item selected in the combo box
     */
    private void panelUpdate(Map<Item, Integer> inventoryCopy, String itemName) {
        String itemDescription = "";
        int itemQuantity = 0;
        for (Map.Entry<Item, Integer> entry : inventoryCopy.entrySet()) {
            if (entry.getKey().getName().equals(itemName)) {
                itemDescription = entry.getKey().getDescription();
                itemQuantity = entry.getValue();
                if (entry.getKey().getType() != ItemType.THROWABLE && entry.getKey().getType() != ItemType.HEALING) {
                    useB.setEnabled(false);
                    if (!isBattle) {
                        equipB.setEnabled(true);
                    }
                } else {
                    equipB.setEnabled(false);
                    if (isBattle) {
                        useB.setEnabled(true);
                    }
                }
            }
        }
        description.setText(itemDescription);
        quantity.setText("Quantity " + itemQuantity);
    }

    /**
     *
     * @param observer sets the observer used by MonsterBattle and HeroDisplay
     */
    public void setObserver(InventoryPanelObserver observer) {
        this.listener = observer;
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
        comboPanel = new JPanel();
        comboPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(comboPanel, gbc);
        itemComboBox = new JComboBox<String>();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        comboPanel.add(itemComboBox, gbc);
        description = new JTextArea();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.BOTH;
        comboPanel.add(description, gbc);
        quantity = new JLabel();
        quantity.setText("Label");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        comboPanel.add(quantity, gbc);
        final JPanel spacer1 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        mainPanel.add(spacer1, gbc);
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(buttonPanel, gbc);
        useB = new JButton();
        useB.setText("Button");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        buttonPanel.add(useB, gbc);
        equipB = new JButton();
        equipB.setText("Button");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        buttonPanel.add(equipB, gbc);
        deleteB = new JButton();
        deleteB.setText("Button");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        buttonPanel.add(deleteB, gbc);
        cancelB = new JButton();
        cancelB.setText("Button");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        buttonPanel.add(cancelB, gbc);
        final JPanel spacer2 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        buttonPanel.add(spacer2, gbc);
        final JPanel spacer3 = new JPanel();
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.VERTICAL;
        buttonPanel.add(spacer3, gbc);
    }

    /**
     * @return JComponent the major panel of this class
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }
}