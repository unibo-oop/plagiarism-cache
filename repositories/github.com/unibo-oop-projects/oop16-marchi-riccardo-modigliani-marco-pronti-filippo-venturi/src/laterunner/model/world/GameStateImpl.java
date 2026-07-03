package laterunner.model.world;

import laterunner.model.collisions.BorderBoundingBox;
import laterunner.model.collisions.WorldEventListener;
import laterunner.model.saving.FileManager;
import laterunner.model.user.User;
import laterunner.model.vehicle.Obstacle;

/**
 *The class in which is implemented the game's state.
 *
 */
public class GameStateImpl implements GameState {

    private World world;
    private int score;
    private int level;
    private boolean endSurvival;

    /**
     * 
     * @param l
     *          the listener to be set in World
     * @param actLevel
     *          the level to play
     * @param initScore
     *          the initial score of the level
     */
    public GameStateImpl(final WorldEventListener l, final int actLevel, final int initScore) {

            this.level = actLevel;
            this.endSurvival = false;
            world = new WorldImpl(BorderBoundingBox.getBorderBoundingBox());
            world.generateLevel(level);
            world.setEventListener(l);
            this.score = initScore;
        }

        @Override
        public World getWorld() {
            return this.world;
        }

        @Override
        public void decScore(final Obstacle o) {
            this.score = this.score - o.getMalus();
            if (this.score < 0) {
                this.score = 0;
            }
            User.getUser().setUserLives(User.getUser().getUserLives() - o.getLifeDamage());
            User.Statistic.getStatistic().setLostLives(User.Statistic.getStatistic().getLostLives() + o.getLifeDamage());
            switch(o.getType()) {
            case OBSTACLE_CAR:
                User.Statistic.getStatistic().setObstacleCarHits(User.Statistic.getStatistic().getObstacleCarHits() + 1);
                break;
            case MOTORBIKE:
                User.Statistic.getStatistic().setMotorbikeHits(User.Statistic.getStatistic().getMotorbikeHits() + 1);
                break;
            case BUS:
                User.Statistic.getStatistic().setTruckHits(User.Statistic.getStatistic().getTruckHits() + 1);
                break;
            default:
                throw new IllegalStateException();
            }
          }

        @Override
        public void decScorebyBorder() {
            this.score = this.score - world.getBorderDamage();
            if (this.score < 0) {
                this.score = 0;
            }
        }

        @Override
        public void incScore(final int i) {
            this.score = this.score + i;
        }

        @Override
        public int getScore() {
            return this.score;
        }

        @Override
        public void update(final int elapsed) {
            this.world.updateState(elapsed);
        }

        @Override
        public boolean isLevelFinished() {
            return this.getWorld().getSceneEntities().size() == 1 || User.getUser().getUserLives() <= 0;
        }

        @Override
        public boolean isEndSurvival() {
            return endSurvival;
        }

        @Override
        public void setEndSurvival(final boolean sEndSurvival) {
            this.endSurvival = sEndSurvival;
        }

        @Override
        public void updateStats() {
            if (this.endSurvival) {
                if (User.Statistic.getStatistic().getSurvivalHighscore() < this.score) {
                    User.Statistic.getStatistic().setSurvivalHighscore(this.score);
                      }

            } else { 
                if (User.getUser().getUserLives() <= 0) {
                    User.getUser().reset();
                } else {
                    User.getUser().setMoney(User.getUser().getMoney() + this.score * this.level);
                    if (this.level == User.getUser().getLevelReached()) {
                            User.getUser().setLevelReached(this.level + 1);
                    }
                }
        }
            User.Statistic.getStatistic().setGamesPlayed(User.Statistic.getStatistic().getGamesPlayed() + 1);
            FileManager.saveToFile();
            FileManager.loadFromFile();
    }
}
