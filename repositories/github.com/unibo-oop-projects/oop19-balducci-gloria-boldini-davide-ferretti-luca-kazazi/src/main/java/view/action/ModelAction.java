package view.action;

import controllers.levelController.LevelController;

public class ModelAction implements ModelActionInterface {

    private static LevelController liv = new LevelController();

    @Override
    public void initLevel(final int level) {
        liv.initLevel(level);
    }

    @Override
    public LevelController getLevelController() {
        return liv;
    }

    @Override
    public void resumeLevel() {
        liv.resumeLevel();
    }

    @Override
    public void restartLevel() {
        liv.interruptLevel();
        liv.initLevel(liv.getLevel());
    }

    @Override
    public void interruptLevel() {
        liv.interruptLevel();
    }

    @Override
    public void nextLevel() {
        this.interruptLevel();
        if (liv.getLevel() == 5) {
            liv.initLevel(1);
        } else {
            liv.initLevel(liv.getLevel() + 1);
        }
    }

}
