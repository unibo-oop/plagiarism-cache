package aoc.model;

import aoc.model.entity.EntityInterface;
import aoc.model.entity.mother.Mother;
import aoc.model.entity.mother.MotherInterface;
import aoc.model.entity.slipper.Projectile;
import aoc.model.level.Level;
import aoc.model.level.StoryLevel;
import aoc.utilities.Direction;
import aoc.utilities.Vector;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ModelImpl implements Model {
    
    private Level level;
    private Optional<Long> lastRapidShoot = Optional.empty();
    private final MotherInterface mother;
    
    /**
     * This method creates a Model containing the level represented by the index and
     * a new mother object that will be controller by the player.
     * @param index
     *            index of the level that will be played.
     */
    public ModelImpl(final Optional<Integer> index) {
    	if (index.isPresent()) {
    		this.level = new StoryLevel(index.get());    		
    	}
    	//if not present should launch Arcade Mode
	this.mother = new Mother(new Vector(0, WorldConstants.WORLD_HEIGHT * WorldConstants.CELL_WIDTH / 2), Projectile.BASIC_SLIPPER);
	mother.attach(level);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public GameStatus getGameStatus() {
	return level.getGameStatus();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void update() {
	level.update();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<EntityInterface> getEntityList() {
	return Stream.concat(level.getEntityList().stream(), Arrays.asList(this.mother).stream()).collect(Collectors.toList());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getCurrentLevel() {
	return level.getCurrentLevel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void moveMother(final Direction dir) {
	final int motherRow = this.getMotherRow();
	if (dir.equals(Direction.DOWN) && motherRow < WorldConstants.WORLD_HEIGHT - 1 ||
		dir.equals(Direction.UP) && motherRow > 0) {
	    this.mother.move(dir);
	}
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void shoot(final ShootingStyle style) {
	if (style.equals(ShootingStyle.SINGLE)) {
	   this.singleShot();
	} else if (style.equals(ShootingStyle.RAPID)) {
	   this.rapidShot();
	}
    }
    
    /**
     * This method shoots a single projectile.
     */
    private void singleShot() {
	this.mother.attack();
    }
    
    /**
     * This method shoots a single projectile in rapid style
     * only if enough time has passed since the last
     * projectile shooted with this method.
     */
    private void rapidShot() {
	if ((lastRapidShoot.isPresent() && ((System.nanoTime() - lastRapidShoot.get()) >= WorldConstants.TIME_BETWEEN_SHOOT))
	        || !lastRapidShoot.isPresent()) {
	    lastRapidShoot = Optional.of(System.nanoTime());
	    this.singleShot();
	}
    }
    
    /**
     * Returns the row where the mother is currently in.
     * @return an int representing a row
     */
    private int getMotherRow() {
	return WorldConstants.ROW_CENTERS.indexOf(mother.getPosition().getY());
    }
}