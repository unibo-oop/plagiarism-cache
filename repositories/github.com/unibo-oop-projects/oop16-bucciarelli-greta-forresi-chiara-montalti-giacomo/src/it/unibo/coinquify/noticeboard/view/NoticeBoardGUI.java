package it.unibo.coinquify.noticeboard.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.itextpdf.text.Font;

import it.unibo.coinquify.noticeboard.controller.NoticeBoardController;
import it.unibo.coinquify.noticeboard.controller.NoticeBoardControllerImpl;
import it.unibo.coinquify.noticeboard.model.PostIt;
import it.unibo.coinquify.utils.Messages;
import it.unibo.coinquify.utils.PaneGUI;
import it.unibo.coinquify.utils.UtilsGUI;

/**
 * GUI NoticeBoard manager.
 *
 */
public class NoticeBoardGUI implements PaneGUI {

    private final JPanel panel;
    private NoticeBoardController controller;
    private final JPanel listOfRules;
    private final SimpleDateFormat formatter;
    private static final String PATTERN = "HH:mm:ss, dd MMMM y";
    private final JPanel postItPanel;
    private static final double GBCWEIGHTX = 0.3;
    private static final double GBCWEIGHTY = 1.0;
    private static final double SECONDGBCWEIGHTX = 0.7;
    private static final int PREFEREDWIDTH = 400;
    private static final int PREFEREDHEIGHT = 50;

    /**
     * NoticeBoard GUI manager.
     */
    public NoticeBoardGUI() {

        this.formatter = new SimpleDateFormat(PATTERN, Messages.getMessages().getCurrentLocale());

        this.panel = new JPanel();
        try {
            this.controller = new NoticeBoardControllerImpl();
        } catch (ClassNotFoundException | IOException e1) {
            JOptionPane.showMessageDialog(panel, Messages.getMessages().getString("FILE_ERROR_NOTICEBOARD"));
        }

        this.panel.setLayout(new GridBagLayout());
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = GBCWEIGHTX;
        gbc.weighty = GBCWEIGHTY;
        gbc.gridx = 0;
        gbc.gridy = 0;

        final JPanel mainJpRules = new JPanel(new BorderLayout());
        mainJpRules.setBorder(new TitledBorder(Messages.getMessages().getString("RULES")));
        this.listOfRules = new JPanel(new GridLayout(0, 1));
        mainJpRules.add(this.listOfRules, BorderLayout.CENTER);

        final JPanel mainJpPostIt = new JPanel(new BorderLayout());
        this.postItPanel = new JPanel(new FlowLayout());
        this.postItPanel.setBorder(new TitledBorder(Messages.getMessages().getString("POSTIT")));
        mainJpPostIt.add(this.postItPanel, BorderLayout.CENTER);

        this.panel.add(mainJpRules, gbc);
        gbc.weightx = SECONDGBCWEIGHTX;
        gbc.gridx = 1;
        this.panel.add(mainJpPostIt, gbc);

        // Setting Rules on Frame
        for (final String string : this.controller.getRules()) {
            this.listOfRules.add(new JLabel(string));
        }

        final JButton modifyBtn = new JButton(Messages.getMessages().getString("UPDATE"));
        modifyBtn.addActionListener(e -> {
            updateRulesWindow();
        });
        mainJpRules.add(modifyBtn, BorderLayout.SOUTH);

        // Setting PostItFrame
        for (final PostIt postIts : this.controller.getPostIt()) {
            this.postItPanel.add(makeButtonFromPostIt(postIts));
        }

        final JButton newPostIt = new JButton(Messages.getMessages().getString("NEW_POSTIT"));
        mainJpPostIt.add(newPostIt, BorderLayout.SOUTH);
        newPostIt.addActionListener(e -> {
            this.controller.addNewPost();
            updateViewPostIt();
        });

        this.panel.setVisible(true);
    }

    private JButton makeButtonFromPostIt(final PostIt postIt) {
        final JButton temp = new JButton(postIt.getTitle());
        temp.addActionListener(e -> {
            modifyPostIt(postIt);
        });
        return temp;
    }

    private void modifyPostIt(final PostIt postIt) {
        final JFrame jf = new JFrame(Messages.getMessages().getString("POSTIT"));
        final JPanel jp = new JPanel(new BorderLayout());
        jf.add(jp);

        final JPanel titlePanel = new JPanel(new GridLayout(1, 2));
        jp.add(titlePanel, BorderLayout.NORTH);

        final JTextField title = new JTextField(postIt.getTitle());
        title.setBackground(Color.YELLOW);
        title.setHorizontalAlignment(JTextField.CENTER);
        title.setFont(title.getFont().deriveFont(Font.BOLD));
        titlePanel.add(title);

        final JLabel dateLabel = new JLabel(
                Messages.getMessages().getString("CREATION_DAY") + formatter.format(postIt.getDateOfCreation()));
        dateLabel.setBackground(Color.YELLOW);
        dateLabel.setOpaque(true);
        titlePanel.add(dateLabel);

        final JTextArea body = new JTextArea(10, 20);
        body.setBackground(Color.YELLOW);
        body.setText(postIt.getBody());
        jp.add(body, BorderLayout.CENTER);

        final JPanel buttonsPanel = new JPanel(new FlowLayout());
        buttonsPanel.setBackground(Color.YELLOW);
        jp.add(buttonsPanel, BorderLayout.SOUTH);

        // Save Button
        final JButton save = new JButton(Messages.getMessages().getString("SAVE"));
        buttonsPanel.add(save);
        save.addActionListener(e -> {
            try {
                this.controller.modifyPostIt(postIt, title.getText(), body.getText());
                updateViewPostIt();
                jf.dispose();
            } catch (IllegalArgumentException e2) {
                JOptionPane.showMessageDialog(jf, Messages.getMessages().getString("ERROR_TITLE_EMPTY"));
            }

        });

        // Delete Button
        final JButton delete = new JButton(Messages.getMessages().getString("DELETE"));
        buttonsPanel.add(delete);
        delete.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(jf, Messages.getMessages().getString("SURE_DELETE_POSTIT"),
                    Messages.getMessages().getString("DELETE_POSTIT"),
                    JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
                this.controller.deletePostIt(postIt);
            }
            updateViewPostIt();
            jf.dispose();
        });

        jf.setVisible(true);
        UtilsGUI.finishFrame(jf);
    }

    private void updateViewPostIt() {
        this.postItPanel.removeAll();
        for (final PostIt postIts : this.controller.getPostIt()) {
            this.postItPanel.add(makeButtonFromPostIt(postIts));
        }
        this.postItPanel.updateUI();
        this.postItPanel.repaint();
    }

    private void updateRulesWindow() {
        final JFrame updateRulesFrame = new JFrame(Messages.getMessages().getString("EDIT_RULES"));
        updateRulesFrame
                .setPreferredSize(new Dimension(PREFEREDWIDTH, this.controller.getRules().size() * PREFEREDHEIGHT));
        final JPanel mainUpdatePanel = new JPanel(new BorderLayout());
        updateRulesFrame.add(mainUpdatePanel);
        final JPanel rulesPanel = new JPanel(new GridLayout(0, 1, 1, 1));
        mainUpdatePanel.add(rulesPanel, BorderLayout.CENTER);
        final List<JTextField> rulesTxtFld = new ArrayList<>();

        for (final String string : this.controller.getRules()) {
            final JTextField txtFld = new JTextField(10);
            rulesPanel.add(txtFld);
            txtFld.setText(string);
            rulesTxtFld.add(txtFld);
        }

        final JButton saveBtn = new JButton(Messages.getMessages().getString("SAVE"));
        saveBtn.addActionListener(e -> {
            this.listOfRules.removeAll();
            final List<String> newListRules = new ArrayList<>();
            for (final JTextField jTextField : rulesTxtFld) {
                if (!jTextField.getText().isEmpty()) {
                    this.listOfRules.add(new JLabel(jTextField.getText()));
                    newListRules.add(jTextField.getText());
                }
            }
            this.controller.updateRules(newListRules);
            this.listOfRules.updateUI();
            this.listOfRules.repaint();
            updateRulesFrame.dispose();
        });
        mainUpdatePanel.add(saveBtn, BorderLayout.SOUTH);

        final JButton addTxtfld = new JButton(Messages.getMessages().getString("ADD"));
        addTxtfld.addActionListener(e -> {
            final JTextField txtFld = new JTextField(10);
            rulesPanel.add(txtFld);
            rulesTxtFld.add(txtFld);
            UtilsGUI.finishFrame(updateRulesFrame);
            updateRulesFrame.setPreferredSize(new Dimension(PREFEREDWIDTH, rulesTxtFld.size() * PREFEREDHEIGHT));
        });
        mainUpdatePanel.add(addTxtfld, BorderLayout.NORTH);
        UtilsGUI.finishFrame(updateRulesFrame);
        updateRulesFrame.setVisible(true);
    }

    @Override
    public JPanel getPanel() {
        return this.panel;
    }

    @Override
    public String getName() {
        return Messages.getMessages().getString("NOTICEBOARD");
    }

    /**
     * save all object to file.
     */
    public void saveToFile() {
        try {
            this.controller.saveRules();
            this.controller.savePostIt();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
