package it.unibo.memory.view;

import javax.swing.*;
import java.awt.*;
import it.unibo.memory.model.Difficulty;

public class MenuPanel extends JPanel {

    private Difficulty selectedDifficulty = Difficulty.EASY;

    public MenuPanel(final MainFrame mainFrame) {
        setLayout(new BorderLayout());

        // Titolo
        JLabel titleLabel = new JLabel("Memory Game", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 40));
        add(titleLabel, BorderLayout.NORTH);

        // Pannello centrale con tutto
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));

        //imposta Difficoltà
        JPanel diffPanel = new JPanel();
        diffPanel.setBorder(BorderFactory.createTitledBorder("Difficolta"));

        ButtonGroup group = new ButtonGroup();

        JRadioButton easyBtn = new JRadioButton("Facile (4x4)");
        easyBtn.setSelected(true);
        easyBtn.addActionListener(e -> selectedDifficulty = Difficulty.EASY);

        JRadioButton mediumBtn = new JRadioButton("Media (6x6)");
        mediumBtn.addActionListener(e -> selectedDifficulty = Difficulty.MEDIUM);

        JRadioButton hardBtn = new JRadioButton("Difficile (8x8)");
        hardBtn.addActionListener(e -> selectedDifficulty = Difficulty.HARD);

        group.add(easyBtn);
        group.add(mediumBtn);
        group.add(hardBtn);

        diffPanel.add(easyBtn);
        diffPanel.add(mediumBtn);
        diffPanel.add(hardBtn);

        centerPanel.add(diffPanel);

        //scelta tema chiaro o scuro
        JPanel themePanel = new JPanel();
        themePanel.setBorder(BorderFactory.createTitledBorder("Tema"));

        ButtonGroup themeGroup = new ButtonGroup();

        JRadioButton lightBtn = new JRadioButton("Chiaro");
        lightBtn.setSelected(true);
        lightBtn.addActionListener(e -> mainFrame.applicaTema(false));

        JRadioButton darkBtn = new JRadioButton("Scuro");
        darkBtn.addActionListener(e -> mainFrame.applicaTema(true));

        themeGroup.add(lightBtn);
        themeGroup.add(darkBtn);
        themePanel.add(lightBtn);
        themePanel.add(darkBtn);

        centerPanel.add(themePanel);

        //istruzioni utenti 
        JPanel rulesPanel = new JPanel();
        rulesPanel.setBorder(BorderFactory.createTitledBorder("Regole del Gioco"));

        JTextArea rulesText = new JTextArea(
            "• Clicca su una carta per girarla.\n" +
            "• Trova la coppia con lo stesso simbolo.\n" +
            "• Se le due carte sono uguali, rimangono scoperte.\n" +
            "• Se sono diverse, vengono rigirate dopo 1 secondo.\n" +
            "• Vinci quando hai trovato tutte le coppie!"
        );
        rulesText.setEditable(false);
        rulesText.setOpaque(false);
        rulesPanel.add(rulesText);

        centerPanel.add(rulesPanel);

        // Crediti
        JPanel creditsPanel = new JPanel();
        creditsPanel.setBorder(BorderFactory.createTitledBorder("Crediti"));

        JLabel creditsLabel = new JLabel(
            "<html><center>Sviluppato da Manuel Tomasello e Marco Toma<br/>" +
            "Progetto PSS - A.A. 2025/26</center></html>",
            SwingConstants.CENTER
        );
        creditsPanel.add(creditsLabel);

        centerPanel.add(creditsPanel);

        add(centerPanel, BorderLayout.CENTER);

        //Bottone Inizio 
        JButton startButton = new JButton("INIZIA PARTITA");
        startButton.setFont(new Font("Arial", Font.BOLD, 20));
        startButton.addActionListener(e -> mainFrame.avviaPartita(selectedDifficulty));

        JPanel btnPanel = new JPanel();
        btnPanel.add(startButton);
        add(btnPanel, BorderLayout.SOUTH);
    }
}
