package view.menus;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.ControllerUtils;
import view.BoardColorPalette;

/**
 * This frame manage the rules menu.
 */

public class RulesMenu {
    private final JFrame frame = new JFrame();
    private final JPanel cardRulesPanel = new JPanel();
    private final JPanel buttonsPanel = new JPanel();
    private final JButton next = new JButton("Next");
    private final JButton prev = new JButton("Prev");
    private final JButton main = new JButton("To Main");
    private final CardLayout cards = new CardLayout();
    private final JPanel titlePanel = new JPanel();
    private final JLabel title = new JLabel();
    private static final String TITLE = " RULES ";
    private final Font fontForRules = new Font(Font.SANS_SERIF, Font.BOLD, 22);
    private final Font fontForTitle = new Font(Font.SANS_SERIF, Font.BOLD, 28);
    /**
     * Class constructor.
     * 
     * @throws URISyntaxException
     * @throws IOException
     */
    public RulesMenu() {

        frame.setLayout(new BorderLayout());
        buttonsPanel.add(prev);
        buttonsPanel.add(next);
        buttonsPanel.add(main);
        cardRulesPanel.setLayout(cards);
        title.setText(TITLE);
        title.setFont(fontForTitle);
        titlePanel.add(title);
        titlePanel.setBackground(Color.decode(BoardColorPalette.SEA_BLUE.getHexRGB()));
        title.setForeground(Color.decode(BoardColorPalette.PALE_SPRING_BUD.getHexRGB()));
        buttonsPanel.setBackground(Color.decode(BoardColorPalette.SEA_BLUE.getHexRGB()));
        frame.add(titlePanel, BorderLayout.PAGE_START);
        frame.add(cardRulesPanel, BorderLayout.CENTER);
        frame.add(buttonsPanel, BorderLayout.PAGE_END);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        cardRulesPanel.setBackground(Color.decode(BoardColorPalette.PERSIAN_GREEN.getHexRGB()));
        final Scanner sc = new Scanner(getClass().getResourceAsStream(ControllerUtils.getRulesName()));
        sc.useDelimiter("\\*\\*\\*");
        final Integer i = 0;
        while (sc.hasNextLine()) {
            final JPanel tmpPanel = new JPanel();
            final String text = sc.next().replaceAll("\n", "<br>").replaceAll("##", "<h1>").replaceAll("#", "</h1>");
            final  JLabel tmpLabel = new JLabel("<Html><div style='text-align: center;'>" + text + "</Html>");
            tmpLabel.setFont(fontForRules);
            tmpPanel.setBackground(Color.decode(BoardColorPalette.METALLIC_SEAWEED.getHexRGB()));
            tmpLabel.setForeground(Color.decode(BoardColorPalette.PALE_SPRING_BUD.getHexRGB()));
            final JPanel emptyPanel = new JPanel();
            emptyPanel.setBackground(Color.BLACK);
            tmpPanel.add(tmpLabel);
            cardRulesPanel.add(i.toString(), tmpPanel);
        }
        sc.close();

        next.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                cards.next(cardRulesPanel);
            }

        });
        prev.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                cards.previous(cardRulesPanel);
            }

        });
        main.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                new MainMenu();
                frame.setVisible(false);
            }

        });
    }

}
