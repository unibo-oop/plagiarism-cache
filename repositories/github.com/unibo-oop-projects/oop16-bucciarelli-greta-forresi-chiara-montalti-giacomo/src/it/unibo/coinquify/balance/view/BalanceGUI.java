package it.unibo.coinquify.balance.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import it.unibo.coinquify.balance.controller.BalanceController;
import it.unibo.coinquify.balance.controller.BalanceControllerImpl;
import it.unibo.coinquify.balance.model.Common;
import it.unibo.coinquify.balance.model.Debt;
import it.unibo.coinquify.balance.model.DebtImpl;
import it.unibo.coinquify.balance.model.Lending;
import it.unibo.coinquify.balance.model.LendingImpl;
import it.unibo.coinquify.roommates.model.RoomMate;

import it.unibo.coinquify.utils.Messages;
import it.unibo.coinquify.utils.PaneGUI;
import it.unibo.coinquify.utils.UtilsGUI;

/**
 *
 * Balance of the house.
 */
public class BalanceGUI implements PaneGUI {

    private static final int TEXT_LENGHT_DEFAULT = 15;
    private static final int ADD_DEFAULT_ROW = 5;
    private static final String CREDITOR = Messages.getMessages().getString("CREDITOR");
    private static final String WARNING = Messages.getMessages().getString("WARNING");
    private static final String SURE_TO_DELETE = Messages.getMessages().getString("SURE_TO_DELETE");
    private static final String DELETE = Messages.getMessages().getString("DELETE");
    private static final String DEBITOR = Messages.getMessages().getString("DEBITOR");
    private final JPanel mainPanel;
    private final JPanel centerPanel;
    private final JPanel centerPanelShowLending;
    private final JPanel centerPanelShow;
    private final BalanceController controller;
    private final Set<RoomMate> setRoomMate;

    /**
     * . new Balance GUI
     * 
     * @param roomMates
     *            of the house
     */
    public BalanceGUI(final Set<RoomMate> roomMates) {

        this.controller = new BalanceControllerImpl();
        this.setRoomMate = roomMates;
        this.centerPanelShow = new JPanel();
        this.centerPanelShowLending = new JPanel();
        this.mainPanel = new JPanel();
        this.centerPanel = new JPanel();
        this.centerPanel.setLayout(new FlowLayout());
        this.mainPanel.setLayout(new BorderLayout());
        this.mainPanel.add(BorderLayout.CENTER, centerPanel);

        this.mainDisplay();
    }

    private void mainDisplay() {

        final JButton btShowDebts = new JButton(Messages.getMessages().getString("SHOW_OUTSTANDING_DEBTS"));
        final JButton btAddDebts = new JButton(Messages.getMessages().getString("ADD_NEW_DEBT"));
        final JButton btShowLending = new JButton(Messages.getMessages().getString("SHOW_OUTSTANDING_LENDING"));
        final JButton btAddLending = new JButton(Messages.getMessages().getString("ADD_NEW_LENDING"));


        this.centerPanel.add(btAddDebts);
        this.centerPanel.add(btShowDebts);
        this.centerPanel.add(btAddLending);
        this.centerPanel.add(btShowLending);


        btAddDebts.addActionListener(e -> {
            this.addDebtDisplay();
        });

        btShowDebts.addActionListener(e -> {
           this.changeRoomMateDisplay(true);
        });

        btShowLending.addActionListener(e -> {
            this.changeRoomMateDisplay(false);
        });

        btAddLending.addActionListener(e -> {
            this.addLendingDisplay();
        });

    }

    /**
     * Display for Add a new Debts.
     */
    private void addDebtDisplay() {
        final JFrame frame = new JFrame(Messages.getMessages().getString("ADD_NEW_DEBT"));
        final JPanel panelAdd = new JPanel();
        final JPanel centerPanelAdd = new JPanel();
        final JPanel southPanelAdd = new JPanel();
        final JCheckBox checkCommonDebt = new JCheckBox(Messages.getMessages().getString("COMMON_DEBT"));
        final JTextField textDebt = new JTextField(TEXT_LENGHT_DEFAULT);
        final JTextField textDescription = new JTextField(TEXT_LENGHT_DEFAULT);
        final JButton btOk = new JButton(Messages.getMessages().getString("OK"));
        final JComboBox<Object> roomMateBoxDebitor = new JComboBox<>(setRoomMate.toArray());
        final JComboBox<Object> roomMateBoxCreditor = new JComboBox<>(setRoomMate.toArray());

        panelAdd.setLayout(new BorderLayout());
        centerPanelAdd.setLayout(new GridLayout(ADD_DEFAULT_ROW, 2));
        southPanelAdd.setLayout(new FlowLayout());
        panelAdd.add(BorderLayout.CENTER, centerPanelAdd);
        panelAdd.add(BorderLayout.SOUTH, southPanelAdd);
        southPanelAdd.add(btOk);
        centerPanelAdd.add(checkCommonDebt);
        centerPanelAdd.add(BorderLayout.CENTER, new JLabel(""));
        centerPanelAdd.add(new JLabel(CREDITOR, Label.RIGHT));
        centerPanelAdd.add(BorderLayout.CENTER, roomMateBoxCreditor);
        centerPanelAdd.add(new JLabel(DEBITOR, Label.RIGHT));
        centerPanelAdd.add(BorderLayout.CENTER, roomMateBoxDebitor);
        centerPanelAdd.add(new JLabel(Messages.getMessages().getString("HOW_MUCH"), Label.RIGHT));
        centerPanelAdd.add(BorderLayout.CENTER, textDebt);
        centerPanelAdd.add(new JLabel(Messages.getMessages().getString("DESCRIPTION"), Label.RIGHT));
        centerPanelAdd.add(BorderLayout.CENTER, textDescription);

        btOk.addActionListener(e -> {

            if (checkCommonDebt.isSelected()) {
                for (final RoomMate r : this.setRoomMate) {
                    this.controller.addDebt(
                            new DebtImpl(textDescription.getText(), Common.HOUSE_DEBT,
                                    (Double.valueOf(textDebt.getText()) / setRoomMate.size())), r);
                }

            } else {
                try {
                    if (Double.valueOf(textDebt.getText()) <= 0) {
                        JOptionPane.showMessageDialog(this.centerPanel,
                                Messages.getMessages().getString("CONTROL_FIELD_FAIL_MIN_ZERO"));
                    } else if (roomMateBoxDebitor.getSelectedItem().equals(roomMateBoxCreditor.getSelectedItem())) {
                        JOptionPane.showMessageDialog(this.centerPanel,
                                Messages.getMessages().getString("CONTROL_FIELD_FAIL_EQUALS"));

                    } else {
                        this.controller.addDebt(new DebtImpl(textDescription.getText(),
                                (RoomMate) roomMateBoxDebitor.getSelectedItem(), Double.valueOf(textDebt.getText())),
                                (RoomMate) roomMateBoxCreditor.getSelectedItem());
                    }
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(this.centerPanel,
                            Messages.getMessages().getString("CONTROL_FIELD_FAIL"));
                }
            }

            frame.dispose();
        });
        frame.add(panelAdd);
        UtilsGUI.finishFrame(frame);
    }

    /**
     * Show the current Debts.
     */
    private void showDebtDisplay(final Object creditor) {
        final JFrame frame = new JFrame(Messages.getMessages().getString("DEBTS"));
        final JPanel panelAdd = new JPanel();
        final JPanel northPanelShow = new JPanel();
        final JPanel southPanelShow = new JPanel();
        final JButton btReset = new JButton(Messages.getMessages().getString("DELETE_ALL"));
        final JButton btOk = new JButton(Messages.getMessages().getString("OK"));
        this.refreshPanel(creditor);
        panelAdd.setLayout(new BorderLayout());
        northPanelShow.setLayout(new GridLayout(1, 4));
        centerPanelShow.setLayout(new GridLayout(0, 4));
        southPanelShow.setLayout(new FlowLayout());
        panelAdd.add(BorderLayout.NORTH, northPanelShow);
        panelAdd.add(BorderLayout.CENTER, centerPanelShow);
        panelAdd.add(BorderLayout.SOUTH, southPanelShow);
        northPanelShow.add(new JLabel(Messages.getMessages().getString("DEBITOR")));
        northPanelShow.add(new JLabel(Messages.getMessages().getString("DEBT")));
        northPanelShow.add(new JLabel(Messages.getMessages().getString("DESCRIPTION")));
        northPanelShow.add(new JLabel(DELETE));
        southPanelShow.add(btReset);
        southPanelShow.add(btOk);

        btReset.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(null, SURE_TO_DELETE,
                    WARNING, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

              ((RoomMate) creditor).getListDebt().clear();
                this.refreshPanel(creditor);
            }
        });

        btOk.addActionListener(e -> {
            frame.dispose();
        });
        frame.add(panelAdd);
        UtilsGUI.finishFrame(frame);
    }

    /**
     * Dispay for the change of RoomMate Creditor.
     */
    private void changeRoomMateDisplay(final boolean flag) {
        final JFrame frame = new JFrame(Messages.getMessages().getString("ROOMMATE_CHANGE_BALANCE"));
        final JPanel panelChange = new JPanel();
        final JButton btOk = new JButton(Messages.getMessages().getString("OK"));
        final JComboBox<Object> roomMateBoxCreditor = new JComboBox<>(setRoomMate.toArray());

        panelChange.setLayout(new GridLayout(2, 2));
        panelChange.add(new JLabel(CREDITOR, Label.RIGHT));
        roomMateBoxCreditor.removeAllItems();

        for (final RoomMate r : setRoomMate) {
            roomMateBoxCreditor.addItem(r);
        }

        panelChange.add(BorderLayout.CENTER, roomMateBoxCreditor);
        panelChange.add(btOk);

        btOk.addActionListener(e -> {

            if (flag) {
                this.showDebtDisplay(roomMateBoxCreditor.getSelectedItem());
            } else {
                this.showLendingDisplay(roomMateBoxCreditor.getSelectedItem());
            }
            frame.dispose();
        });
        frame.add(panelChange);
        UtilsGUI.finishFrame(frame);
    }

    /**
     * Display for Add a new Lending
     */
    private void addLendingDisplay() {
        final JFrame frame = new JFrame(Messages.getMessages().getString("ADD_NEW_LENDING"));
        final JPanel panelAdd = new JPanel();
        final JPanel centerPanelAdd = new JPanel();
        final JPanel southPanelAdd = new JPanel();
        final JCheckBox checkCommonLending = new JCheckBox(Messages.getMessages().getString("COMMON_LENDING"));
        final JTextField textDescription = new JTextField(TEXT_LENGHT_DEFAULT);
        final JButton btOk = new JButton(Messages.getMessages().getString("OK"));
        final JComboBox<Object> roomMateBoxDebitor = new JComboBox<>(setRoomMate.toArray());
        final JComboBox<Object> roomMateBoxCreditor = new JComboBox<>(setRoomMate.toArray());

        panelAdd.setLayout(new BorderLayout());
        centerPanelAdd.setLayout(new GridLayout(4, 2));
        southPanelAdd.setLayout(new FlowLayout());
        panelAdd.add(BorderLayout.CENTER, centerPanelAdd);
        panelAdd.add(BorderLayout.SOUTH, southPanelAdd);
        southPanelAdd.add(btOk);
        centerPanelAdd.add(checkCommonLending);
        centerPanelAdd.add(BorderLayout.CENTER, new JLabel(""));
        centerPanelAdd.add(new JLabel(CREDITOR, Label.RIGHT));
        centerPanelAdd.add(BorderLayout.CENTER, roomMateBoxCreditor);
        centerPanelAdd.add(new JLabel(DEBITOR, Label.RIGHT));
        centerPanelAdd.add(BorderLayout.CENTER, roomMateBoxDebitor);
        centerPanelAdd.add(new JLabel(Messages.getMessages().getString("OBJECT"), Label.RIGHT));
        centerPanelAdd.add(BorderLayout.CENTER, textDescription);

        btOk.addActionListener(e -> {
            if (checkCommonLending.isSelected()) {
                this.controller.addLending(
                        new LendingImpl(Common.HOUSE_LENDING, textDescription.getText()),
                        (RoomMate) roomMateBoxCreditor.getSelectedItem());
            } else {
                try {
                    if (roomMateBoxDebitor.getSelectedItem().equals(roomMateBoxCreditor.getSelectedItem())) {
                        JOptionPane.showMessageDialog(panelAdd,
                                Messages.getMessages().getString("CONTROL_FIELD_FAIL_EQUALS"));
                    } else {
                        this.controller.addLending(new LendingImpl((RoomMate) roomMateBoxDebitor.getSelectedItem(),
                                textDescription.getText()), (RoomMate) roomMateBoxCreditor.getSelectedItem());
                    }
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(panelAdd, Messages.getMessages().getString("CONTROL_FIELD_FAIL"));
                }
            }

            frame.dispose();
        });
        frame.add(panelAdd);
        UtilsGUI.finishFrame(frame);

    }

    /**
     * Show the current Lending of this creditor.
     */
    private void showLendingDisplay(final Object creditor) {
        final JFrame frame = new JFrame(Messages.getMessages().getString("DEBTS"));
        final JPanel panelAdd = new JPanel();
        final JPanel northPanelAdd = new JPanel();
        final JPanel southPanelAdd = new JPanel();
        final JButton btReset = new JButton(Messages.getMessages().getString("DELETE_ALL"));
        final JButton btOk = new JButton(Messages.getMessages().getString("OK"));
        this.refreshPanelLending(creditor);
        panelAdd.setLayout(new BorderLayout());
        northPanelAdd.setLayout(new GridLayout(1, 3));
        centerPanelShowLending.setLayout(new GridLayout(0, 3));
        southPanelAdd.setLayout(new FlowLayout());
        panelAdd.add(BorderLayout.NORTH, northPanelAdd);
        panelAdd.add(BorderLayout.CENTER, centerPanelShowLending);
        panelAdd.add(BorderLayout.SOUTH, southPanelAdd);
        northPanelAdd.add(new JLabel(DEBITOR));
        northPanelAdd.add(new JLabel(Messages.getMessages().getString("OBJECT")));
        northPanelAdd.add(new JLabel(DELETE));
        southPanelAdd.add(btReset);
        southPanelAdd.add(btOk);

        btReset.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(null, SURE_TO_DELETE, WARNING,
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                ((RoomMate) creditor).getListLending().clear();
                this.refreshPanelLending(creditor);
            }
        });

        btOk.addActionListener(e -> {
            frame.dispose();
        });
        frame.add(panelAdd);
        UtilsGUI.finishFrame(frame);
    }

    /**
     * Refresh the Panel for the show of the Lending.
     */
    private void refreshPanelLending(final Object creditor) {

        centerPanelShowLending.removeAll();
        if (((RoomMate) creditor).getListLending().isEmpty()) {
            this.centerPanelShowLending.add(new Label(Messages.getMessages().getString("THERE_ARE_NO_LENDINGS")));
        } else {
            for (final Lending l : ((RoomMate) creditor).getListLending()) {
                final JButton btDeleteLending = new JButton(Messages.getMessages().getString("DELETE"));
                this.centerPanelShowLending.add(new Label(l.getDebitor().toString()));
                this.centerPanelShowLending.add(new Label(l.getDescription()));
                this.centerPanelShowLending.add(btDeleteLending);

                btDeleteLending.addActionListener(e -> {
                    if (JOptionPane.showConfirmDialog(null, SURE_TO_DELETE, WARNING,
                            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        this.controller.removeLending(l, ((RoomMate) creditor));
                        this.refreshPanelLending(creditor);
                    }
                });
            }
        }
        this.centerPanelShowLending.repaint();
        this.centerPanelShowLending.validate();
    }

    /**
     * Refresh Panel for the Show of the debts.
     */
    private void refreshPanel(final Object creditor) {
        centerPanelShow.removeAll();
        if (((RoomMate) creditor).getListDebt().isEmpty()) {
            this.centerPanelShow.add(new Label(Messages.getMessages().getString("THERE_ARE_NO_DEBTS")));
        } else {
            for (final Debt d : ((RoomMate) creditor).getListDebt()) {

                final JButton btDeleteDebts = new JButton(Messages.getMessages().getString("DELETE"));
                this.centerPanelShow.add(new Label(d.getDebitor().toString()));
                this.centerPanelShow.add(new Label(String.format("%.2f", d.getQuantity()) + "€"));
                this.centerPanelShow.add(new Label(d.getDescription()));
                this.centerPanelShow.add(btDeleteDebts);

                btDeleteDebts.addActionListener(e -> {
                    if (JOptionPane.showConfirmDialog(null, SURE_TO_DELETE, WARNING,
                            JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        this.controller.removeDebt(d, ((RoomMate) creditor));
                        this.refreshPanel(creditor);
                    }
                });
            }
        }
        this.centerPanelShow.repaint();
        this.centerPanelShow.validate();
    }

    @Override
    public JPanel getPanel() {
        return this.mainPanel;
    }

    @Override
    public String getName() {
        return Messages.getMessages().getString("BALANCE_NAME");
    }

}