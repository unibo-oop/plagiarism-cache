package maingame.menu.listener.controller;

import editor.GUIEditorImpl;
import maingame.game.Game;
import maingame.level.Difficulty;
import maingame.level.LevelImpl;
import maingame.menu.menu.Menu;
import maingame.menu.model.ModelMenu;

/**
 * Implementazione dell'interfaccia ControllerMenu.
 */
public class ControllerMenuImpl implements ControllerMenu {

    private final ModelMenu model;
    private final Menu menu;
    /**
     * Costruttore per Controller.
     * @param myModel model a quale fa riferimento il menu
     * @param myMenu menu a quale fa riferimento il controller
     */
    public ControllerMenuImpl(final ModelMenu myModel, final Menu myMenu) {
        this.model = myModel;
        this.menu = myMenu;
    }

    @Override
    public void escape() {
        if (model.isShowCommand()) {
            model.setShowCommand(false);
            menu.showCommand();
        }
        if (model.isShowStats()) {
            model.setShowStats(false);
            menu.showStats();
        }
    }

    @Override
    public void setDiff() {
        switch (model.getDifficulty()) {
        case EASY:
            model.setDifficulty(Difficulty.HARD);
            LevelImpl.setDifficulty(Difficulty.HARD);
            break;
        case HARD:
            model.setDifficulty(Difficulty.EASY);
            LevelImpl.setDifficulty(Difficulty.EASY);
            break;
        default:
            model.setDifficulty(Difficulty.HARD);
            LevelImpl.setDifficulty(Difficulty.HARD);
            break;
        }
        menu.setDiff();
    }

    @Override
    public void myChangeSelected(final boolean up) {
        if (!model.isShowCommand() && !model.isShowStats()
                && (!(up && model.getIndex() <= 0) && !(!up && model.getIndex() >= model.getNumOption() - 1))) {
            menu.changeSelected(up);
            model.setIndex(model.getIndex() + (up ? -1 : 1));
        }
    }

    @Override
    public void enter() {
        if (!model.isShowCommand() && !model.isShowStats()) {
            switch (model.getOption(model.getIndex())) {
            case DIFF:
                this.setDiff();
                break;
            case START:
                this.myStart();
                break;
            case COMMAND:
                model.setShowCommand(true);
                menu.showCommand();
                break;
            case STATS:
                model.setShowStats(true);
                menu.showStats();
                break;
            case EDITOR:
                this.myStart();
                Game.getGame().setEditor(true);
                GUIEditorImpl.getGUIEditor().open();
                break;
            case RESTART:
                Game.getGame().setEnd(true);
                this.myStart();
            default:
                break;
            }
        }
    }


    private void myStart() {
        synchronized (Game.getGame()) {
            try {
                Game.getGame().wakeUp();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
