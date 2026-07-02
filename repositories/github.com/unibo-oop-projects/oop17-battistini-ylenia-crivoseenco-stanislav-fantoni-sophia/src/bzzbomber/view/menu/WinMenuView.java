package bzzbomber.view.menu;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import bzzbomber.controller.Controller;
import bzzbomber.model.Score;
import bzzbomber.model.TopScoreImpl;
import bzzbomber.view.GenericView;

/**
 * 
 * Win Menu.
 */
public class WinMenuView implements GenericView {

    private static final double PROPORTION = 0.3;
    private final Controller controller;
    private final JFrame frame;
    private final TopScoreImpl topTenScore;

    /**
     * constructor, it build the message of compliments and the possibility to save
     * the score.
     * 
     * @param controller
     *            the controller of the game.
     */
    public WinMenuView(final Controller controller) {
        this.controller = controller;

        final GUIFactory menuFactory;
        menuFactory = new GUIFactoryImpl();
        this.frame = menuFactory.createBasicFrame();
        this.frame.setSize((int) (this.frame.getWidth() * WinMenuView.PROPORTION),
                (int) (this.frame.getHeight() * WinMenuView.PROPORTION));

        this.frame.setTitle("WIN");

        final JLabel textField = new JLabel(
                "COMPLIMENTI" + "\n\r" + " Hai VINTO! \n\r Inserisci il nome e salva il punteggo ",
                SwingConstants.CENTER);

        final JPanel panelNickName = new JPanel();
        final JPanel panelButton = new JPanel();
        final JPanel panelText = new JPanel(new BorderLayout(15, 60));

        panelText.add(textField);

        final JTextArea textArea = new JTextArea("NickName");
        panelNickName.add(textArea, BorderLayout.CENTER);

        final JButton save = menuFactory.createImageButton("/save.png",
                (int) (this.frame.getWidth() * WinMenuView.PROPORTION),
                (int) (this.frame.getHeight() * WinMenuView.PROPORTION));

        this.topTenScore = new TopScoreImpl();
        this.topTenScore.readFromFile(TopScoreImpl.PATH);

        save.addActionListener(e -> {
            this.topTenScore.addScore(new Score(textArea.getText(), this.controller.getScore()));
            try {
                this.topTenScore.saveOnFile(TopScoreImpl.PATH);
            } catch (IllegalStateException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            save.setEnabled(false);
        });

        final JButton back = menuFactory.createImageButton("/return.png",
                (int) (this.frame.getWidth() * WinMenuView.PROPORTION),
                (int) (this.frame.getHeight() * WinMenuView.PROPORTION));

        back.addActionListener(e -> {
            this.frame.setVisible(false);
            new StatisticMenuView(this.controller).show();
        });

        panelButton.add(back, BorderLayout.EAST);
        panelButton.add(save, BorderLayout.WEST);

        this.frame.add(panelText, BorderLayout.NORTH);
        this.frame.add(panelNickName, BorderLayout.CENTER);
        this.frame.add(panelButton, BorderLayout.SOUTH);

    }

    @Override
    public final void show() {
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
        System.out.println("Show Win Menu...");
    }
}
