package it.unibo.coinquify.shoppinglist.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.itextpdf.text.DocumentException;


import it.unibo.coinquify.file.ItemFile;
import it.unibo.coinquify.shoppinglist.controller.ShoppingListController;
import it.unibo.coinquify.shoppinglist.controller.ShoppingListControllerImpl;
import it.unibo.coinquify.shoppinglist.model.Item;
import it.unibo.coinquify.shoppinglist.model.ItemImpl;
import it.unibo.coinquify.shoppinglist.model.ShoppingList;
import it.unibo.coinquify.shoppinglist.model.ShoppingListImpl;
import it.unibo.coinquify.utils.Messages;
import it.unibo.coinquify.utils.PaneGUI;
import it.unibo.coinquify.utils.UtilsGUI;

/**
 * GUI of ShoppingList.
 */

public class ShoppingListGUI implements PaneGUI {

    private final JPanel panel;
    private final JPanel mainPanel;
    private final JPanel panelSouth;
    private boolean flag;
    private Map<JCheckBox, Integer> mapShoppingList;
    private ShoppingList listShoppingItem;
    private final ShoppingListController controller;
    private final Strategy strategy;

    /**
     * . New ShoppingListGUI
     * 
     * @throws ClassNotFoundException 
     * @throws IOException 
     * 
     */
    public ShoppingListGUI() throws ClassNotFoundException, IOException {

        this.panel = new JPanel();
        this.panelSouth = new JPanel();
        this.mainPanel = new JPanel();
        this.mapShoppingList = new HashMap<>();
        this.strategy = new StrategyImpl();
        this.controller = new ShoppingListControllerImpl();
        this.flag = false;
        this.panel.setLayout(new GridLayout(this.mapShoppingList.size(), 4));
        this.panelSouth.setLayout(new FlowLayout());
        this.mainPanel.setLayout(new BorderLayout());
        this.mainPanel.add(BorderLayout.CENTER, panel);
        this.mainPanel.add(BorderLayout.SOUTH, panelSouth);
        this.mainPanel.setVisible(true);
        this.panelSouth.setVisible(true);

        if (ItemFile.read().isEmpty()) {
            this.listShoppingItem = new ShoppingListImpl();

        } else {
            this.listShoppingItem = new ShoppingListImpl(ItemFile.read());

            this.mapShoppingList = new HashMap<>();
            for (final Item i : this.listShoppingItem.getItems()) {
                this.mapShoppingList.put(new JCheckBox(i.getItem()), Integer.parseInt(i.getQuantity()));
            }
        }

        this.mainDisplay();

    }

    /*
     * Main Display
     */
    private void mainDisplay() throws ClassNotFoundException, IOException {

        final JButton btRefresh = new JButton(Messages.getMessages().getString("REFRESH_SHOPPINGLIST"));
        final JButton btAdd = new JButton(Messages.getMessages().getString("ADD_NEW_ITEM"));
        final JButton btStampa = new JButton(Messages.getMessages().getString("SAVE_SHOPPINGLIST"));
        final JButton btReset = new JButton(Messages.getMessages().getString("DELETE_SHOPPINGLIST"));
        this.panelSouth.add(btAdd);
        this.panelSouth.add(btRefresh);
        this.panelSouth.add(btStampa);
        this.panelSouth.add(btReset);
        this.refreshbt();

        btAdd.addActionListener(e -> {
            this.displayAddItem();
            this.refreshbt();

        });

        btReset.addActionListener(e -> {
            this.mapShoppingList.clear();
            this.listShoppingItem.getItems().clear();
            this.refreshbt();

        });

        btRefresh.addActionListener(e -> {
            this.refreshbt();

        });

        btStampa.addActionListener(e -> {
            try {
                this.strategy.createPDF();
                JOptionPane.showMessageDialog(this.panel, Messages.getMessages().getString("PDF_CREATE"));
            } catch (DocumentException e1) {
                JOptionPane.showMessageDialog(this.panel, Messages.getMessages().getString("PDF_CREATE_ERROR"));
                e1.printStackTrace();
            } catch (IOException e2) {
                JOptionPane.showMessageDialog(this.panel, Messages.getMessages().getString("PDF_CREATE_ERROR"));
                e2.printStackTrace();
            }
            this.refreshbt();

        });

    }

    /*
     * Display for Add an Element in the List
     */
    private void displayAddItem() {

        final JPanel panelAdd = new JPanel();
        final JPanel mainPanel = new JPanel();
        final JPanel panelSouth = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(BorderLayout.CENTER, panelAdd);
        mainPanel.add(BorderLayout.SOUTH, panelSouth);
        final JButton btOk = new JButton(Messages.getMessages().getString("OK"));
        final JFrame frame = new JFrame(Messages.getMessages().getString("ADD_NEW_ITEM"));
        panelAdd.setLayout(new GridLayout(2, 2));

        final JTextField textItem = new JTextField(15);
        panelAdd.add(new JLabel(Messages.getMessages().getString("WHAT_DO_YOU_NEED"), Label.RIGHT));
        panelAdd.add(BorderLayout.CENTER, textItem);

        final JTextField textQuantity = new JTextField(3);
        panelAdd.add(new JLabel(Messages.getMessages().getString("HOW_MUCH"), Label.RIGHT));
        panelAdd.add(BorderLayout.CENTER, textQuantity);
        panelSouth.add(btOk);

        btOk.addActionListener(e -> {

            try {
                int i = 0;
                final JCheckBox c = new JCheckBox(textItem.getText(), false);
                final Set<JCheckBox> tmp = new HashSet<>(this.mapShoppingList.keySet());
                for (final JCheckBox o : tmp) {
                    if (o.getText().equals(c.getText())) {
                        flag = true;
                    }
                }

                if (Integer.parseInt(textQuantity.getText()) <= 0) {
                    JOptionPane.showMessageDialog(this.panel, Messages.getMessages().getString("ERROR_ITEM_MIN_ZERO"));
                    frame.dispose();
                } else if (flag) {
                    JOptionPane.showMessageDialog(this.panel, Messages.getMessages().getString("ERROR_ITEM_EQUALS"));
                    flag = false;
                    frame.dispose();
                } else {

                    i = Integer.parseInt(textQuantity.getText());
                    this.mapShoppingList.put(c, i);
                    this.controller.addItem(new ItemImpl(textItem.getText(), textQuantity.getText()), listShoppingItem);

                }

            } catch (Exception e6) {
                JOptionPane.showMessageDialog(this.panel, Messages.getMessages().getString("ERROR_ITEM_NOT_CORRECT"));
            }

            this.refreshbt();
            frame.dispose();
        });
        frame.add(mainPanel);
        UtilsGUI.finishFrame(frame);

    }

    @Override
    public JPanel getPanel() {
        return this.mainPanel;
    }

    /**
     * Refresh the panel with the Updates.
     */
    private void refreshbt() {

        this.panel.removeAll();
        this.strategy.refresh(this.mapShoppingList, this.listShoppingItem.getItems());

        if (this.mapShoppingList.isEmpty()) {
            this.panel.add(new Label(Messages.getMessages().getString("SHOPPING_LIST_EMPTY")));

        } else {
            for (final JCheckBox c : this.mapShoppingList.keySet()) {
                this.panel.add(c);
                this.panel.add(new Label(String.valueOf(this.mapShoppingList.get(c)), Label.LEFT));
                this.panel.add(new Label(" "));
                this.panel.add(new Label(" "));
            }
        }

        this.panel.repaint();
        this.panel.validate();

        ItemFile.write(listShoppingItem.getItems());
    }

    @Override
    public String getName() {
        return Messages.getMessages().getString("SHOPPING_LIST_NAME");
    }

}
