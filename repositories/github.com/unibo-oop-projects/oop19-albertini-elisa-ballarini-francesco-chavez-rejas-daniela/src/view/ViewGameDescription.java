package view;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import manager.ControllerManager;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import factory.EnumFactory;

import javax.swing.JButton;

/**
 * Class that implements {@link View} and that is the view of the descriptions
 * of the application.
 */
public class ViewGameDescription extends JPanel implements View {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private final ControllerManager controller;

    /**
     * @param controller : the controller of the application.
     */
    public ViewGameDescription(final ControllerManager controller) {
        this.controller = controller;
        this.controller.setView(this);
    }

    @Override
    public final JPanel getMainPanel() {
        return this;
    }

    @Override
    public final void initView(final Dimension frameDimension) {
        setLayout(new BorderLayout(0, 0));

        final JLabel lblDescriptionOfThe = new JLabel("DESCRIPTION OF THE GAME APPLICATION", JLabel.CENTER);
        add(lblDescriptionOfThe, BorderLayout.NORTH);

        final JTextArea descriptionArea = new JTextArea();

        final JScrollPane scroll = new JScrollPane(descriptionArea);
        add(scroll, BorderLayout.CENTER);

        final JButton btnBack = new JButton("BACK");
        btnBack.addActionListener(e -> this.controller.setController(EnumFactory.MENU));
        add(btnBack, BorderLayout.SOUTH);

        final BufferedReader buffer = new BufferedReader(
                new InputStreamReader(ClassLoader.getSystemResourceAsStream("tetrisDescription.txt")));

        try {
            descriptionArea.read(buffer, ClassLoader.getSystemResource("tetrisDescription.txt"));
            buffer.close();
            descriptionArea.requestFocus();
        } catch (IOException e) {
            e.printStackTrace();
        }

        descriptionArea.setEditable(false);
    }

}
