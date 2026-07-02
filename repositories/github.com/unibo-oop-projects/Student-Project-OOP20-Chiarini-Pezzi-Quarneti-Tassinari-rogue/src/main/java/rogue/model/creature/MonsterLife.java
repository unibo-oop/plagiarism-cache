package rogue.model.creature;

/**
 * An interface modeling a specific {@link Life} for the {@link Monster}.
 *
 */
public class MonsterLife extends AbstractLife {

    protected MonsterLife(final int healthPoints, final int experience) {
        super(healthPoints, experience);
    }
}
