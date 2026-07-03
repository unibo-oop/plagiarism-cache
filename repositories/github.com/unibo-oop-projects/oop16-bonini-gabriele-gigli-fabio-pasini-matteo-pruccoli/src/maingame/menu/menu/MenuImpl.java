package maingame.menu.menu;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;

import editor.guieditor.GUIFactoryEditor;
import editor.guieditor.GUIFactoryEditorImpl;
import maingame.level.Difficulty;
import maingame.menu.guifactory.GUIFactoryMenu;
import maingame.menu.guifactory.GUIFactoryMenuImpl;
import maingame.menu.listener.InputListener;
import maingame.menu.model.ModelMenu;
import maingame.menu.model.ModelMenuImpl;
import maingame.statistics.StatisticsImpl;

/**
 * Implementazione dell'interfaccia Menu, crea un oggetto menu con i comandi
 * speificati per parametro.
 */
public class MenuImpl extends JPanel implements Menu {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private final List<JLabel> optionLabel = new ArrayList<>();
    private final JPanel menuPanel = new JPanel();
    private final JPanel commandPanel;
    private final InputListener listener;
    private JPanel statsPanel;
    private boolean canSetDiff;
    private final GUIFactoryMenu menuFactory = new GUIFactoryMenuImpl();
    private final ModelMenu model = new ModelMenuImpl();
    private final JLabel lblbDiff = menuFactory.createLabel(Difficulty.EASY.name());
    private static final String SELECTED = ">";
    private boolean isEnableList;

    /**
     * @param arg
     *            comandi da assegnare allo specifico menu
     */
    public MenuImpl(final Option... arg) {
        for (final Option x : arg) {
            if (x.equals(Option.DIFF)) {
                this.canSetDiff = true;
            }
            optionLabel.add(menuFactory.createLabel(x.toString()));
            model.addOption(x);
        }
        final int sizeLayout = optionLabel.size() + (canSetDiff ? 1 : 0);
        menuPanel.setLayout(new GridLayout(sizeLayout, 1));
        for (final JLabel x : optionLabel) {
            menuPanel.add(x);
            if (x.getText().equals(Option.DIFF.toString())) {
                menuPanel.add(lblbDiff);
            }
        }
        optionLabel.get(0).setText(SELECTED + optionLabel.get(0).getText());
        this.listener = new InputListener(this, model);
        this.add(menuPanel);
        commandPanel = this.setMenuCommands();
        this.add(commandPanel);
        commandPanel.setVisible(false);
    }

    @Override
    public void showCommand() {
        if (model.isShowCommand()) {
            commandPanel.setVisible(true);
            menuPanel.setVisible(false);
        } else {
           commandPanel.setVisible(false);
           menuPanel.setVisible(true);
        }
    }

    @Override
    public void showStats() {
        if (model.isShowStats()) {
            statsPanel = setMenuStats();
            this.add(statsPanel);
            statsPanel.setVisible(true);
            menuPanel.setVisible(false);
        } else {
            statsPanel.setVisible(false);
            menuPanel.setVisible(true);
        }
    }

    @Override
    public void setDiff() {
        if (canSetDiff) {
            this.lblbDiff.setText(model.getDifficulty().toString());
        }
    }

    @Override
    public void changeSelected(final boolean up) {
        final int currentIndex = model.getIndex();
        final int go = up ? -1 : +1;
        String newString;
        final String oldString = optionLabel.get(currentIndex).getText().substring(SELECTED.length());
        if (!(up && currentIndex <= 0) && !(!up && currentIndex >= model.getNumOption() - 1)) {
            newString = SELECTED + optionLabel.get(currentIndex + go).getText();
            optionLabel.get(currentIndex).setText(oldString);
            optionLabel.get(currentIndex + go).setText(newString);
        }

    }

    @Override
    public void enableCommand(final boolean enable) {
        if (enable && !isEnableList) {
            this.setFocusable(true);
            this.requestFocusInWindow();
            this.addKeyListener(listener);
            isEnableList = true;
        } else {
            this.removeKeyListener(listener);
            isEnableList = false;
        }
    }

    private JPanel setMenuCommands() {
        final int nRows = 4;
        final int nColumns = 1;
        final GUIFactoryEditor factory = new GUIFactoryEditorImpl();
        return factory.createPanel(new GridLayout(nRows, nColumns), menuFactory.createLabel("WASD to move"),
                menuFactory.createLabel("MOUSE SX projectile"), menuFactory.createLabel("MOUSE DX super projectile"),
                menuFactory.createLabel("SPACE to attack"));
    }

    private JPanel setMenuStats() {
        final int nRows = 5;
        final int nColumns = 1;
        StatisticsImpl.getStatistics().save();
        final GUIFactoryEditor factory = new GUIFactoryEditorImpl();
        return factory.createPanel(new GridLayout(nRows, nColumns),
                menuFactory.createLabel("N. of kill: " + StatisticsImpl.getStatistics().getKill()),
                menuFactory.createLabel("Time played: " + StatisticsImpl.getStatistics().getTime()),
                menuFactory.createLabel("Max score: " + StatisticsImpl.getStatistics().getMaxScore()),
                menuFactory.createLabel("Projectile shoots: " + StatisticsImpl.getStatistics().getProjectile()),
                menuFactory.createLabel("Steps: " + StatisticsImpl.getStatistics().getSteps()));
    }
}
