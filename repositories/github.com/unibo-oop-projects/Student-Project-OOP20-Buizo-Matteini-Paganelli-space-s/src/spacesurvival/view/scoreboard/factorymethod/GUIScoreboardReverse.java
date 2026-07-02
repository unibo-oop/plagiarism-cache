package spacesurvival.view.scoreboard.factorymethod;

import spacesurvival.view.utilities.GraphicsLayoutUtils;
import spacesurvival.view.scoreboard.FactoryGUIScoreboard;
import spacesurvival.view.scoreboard.GUIScoreboard;
import spacesurvival.view.scoreboard.concrete.ConcreteScoreboardGUI;
import spacesurvival.view.utilities.FactoryGUIs;

import java.awt.BorderLayout;

import javax.swing.JPanel;

/**
 * Implements the creation of the standard scoreboard menu GUI.
 */
public class GUIScoreboardReverse implements FactoryGUIScoreboard {

    /**
     * {@inheritDoc}
     */
    @Override
    public GUIScoreboard create() {
        final ConcreteScoreboardGUI scoreboardConcrete = new ConcreteScoreboardGUI();
        scoreboardConcrete.setFontTitleGUI(GraphicsLayoutUtils.getFontForTitle(GraphicsLayoutUtils.SIZE_FONT_H2));
        scoreboardConcrete.getTxtSearchName().setColumns(GraphicsLayoutUtils.SIZE_COLUMNS_TEXT);
        scoreboardConcrete.setFontGUI(GraphicsLayoutUtils.FONT_STANDARD_H5);
        scoreboardConcrete.setForegroundGUI(GraphicsLayoutUtils.COLOR_4);

        scoreboardConcrete.setLayout(new BorderLayout());

        scoreboardConcrete.add(FactoryGUIs.encapsulatesInPanelFlow(scoreboardConcrete.getLbTitle()),
                BorderLayout.NORTH);
        scoreboardConcrete.add(scoreboardConcrete.getBtnBack(), BorderLayout.SOUTH);

        final JPanel panelScore = FactoryGUIs.createPanelTransparent(new BorderLayout());

        panelScore.add(FactoryGUIs.createPanelFlowUnionComponents(java.util.List.of(scoreboardConcrete.getTxtSearchName(),
                scoreboardConcrete.getBtnSearch())), BorderLayout.SOUTH);

        panelScore.add(FactoryGUIs.encapsulatesInPanelFlow(
                scoreboardConcrete.getScoreboard()), BorderLayout.CENTER);

        scoreboardConcrete.add(panelScore, BorderLayout.CENTER);
        return scoreboardConcrete;
    }

}
