package bzzbomber.view.menu;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import bzzbomber.controller.Controller;
import bzzbomber.controller.GameState;
import bzzbomber.model.Score;
import bzzbomber.model.TopScoreImpl;
import bzzbomber.model.TopScore;
import bzzbomber.view.GenericView;
import bzzbomber.view.StatisticsLine;
import bzzbomber.view.TableStatistics;

/**
 * create a table with the top ten score on the line.
 */
public class StatisticMenuView implements GenericView {

    private static final double PROPORTION = 0.6;
    private final JFrame frame;
    private final JButton returnMenu;

    private final Controller controller;

    /**
     * constructor builds the frame and two buttons.
     * 
     * @param controller
     *            the controller of the game.
     */
    public StatisticMenuView(final Controller controller) {

        this.controller = controller;

        final GUIFactory menuFactory;
        menuFactory = new GUIFactoryImpl();
        this.frame = menuFactory.createBasicFrame();
        this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.frame.setSize((int) (this.frame.getWidth() * StatisticMenuView.PROPORTION),
                (int) (this.frame.getHeight() * StatisticMenuView.PROPORTION));

        final TopScore topScore = new TopScoreImpl();
        topScore.readFromFile(TopScoreImpl.PATH);

        final JPanel panelTTS;
        final List<StatisticsLine> list = new ArrayList<>();
        final TableStatistics tableStatis = new TableStatistics(list);
        final JTable table = new JTable(tableStatis);

        panelTTS = new JPanel(new GridLayout(1, tableStatis.getColumnCount()));
        panelTTS.setBorder(new TitledBorder("Top Ten Player"));

        int i = 1;
        for (final Score s : topScore.getScore()) {
            list.add(new StatisticsLine(i, new Score(s)));
            i++;
        }
        final JScrollPane scroll = new JScrollPane(table);
        panelTTS.add(scroll);

        final JPanel panelButtons = new JPanel();
        this.returnMenu = menuFactory.createBackButton();
        final JButton exit = menuFactory.createButton("Exit", "/exit.png");
        panelButtons.add(returnMenu, BorderLayout.CENTER);
        panelButtons.add(exit, BorderLayout.CENTER);

        this.returnMenu.addActionListener(e -> {
            this.frame.setVisible(false);
        });
        exit.addActionListener(e -> {
            System.out.println("Bye bye!");
            System.exit(0);
        });

        this.frame.add(panelButtons, BorderLayout.SOUTH);
        this.frame.add(panelTTS, BorderLayout.CENTER);
    }

    @Override
    public final void show() {
        this.frame.setLocationRelativeTo(null);
        this.frame.setVisible(true);
        System.out.println("Show Statistic Menu...");
        if (controller.getGameState().equals(GameState.WON)) {
            this.returnMenu.setEnabled(false);
        }
    }

}
