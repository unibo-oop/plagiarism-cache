package hollowmen.model.roomentity;

import java.util.Collection;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.Filter;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.FixtureDef;

import hollowmen.model.Actor;
import hollowmen.model.Attack;
import hollowmen.model.Information;
import hollowmen.model.Parameter;
import hollowmen.model.RoomEntity;
import hollowmen.model.dungeon.FilterType;
import hollowmen.model.utils.Box2DUtils;
import hollowmen.utilities.ExceptionThrower;
import hollowmen.utilities.Pair;

/**
 * This class extends {@link ActorAbs} and implements {@link Attack}<br>
 * This class apply the right filter and make the attack on the right position
 * @author pigio
 *
 */
public abstract class AttackAbs extends ActorAbs implements Attack{

	private Actor owner;
	private final String direction;

	public AttackAbs(Information info, Pair<Float, Float> size, Collection<Parameter> param, Actor owner, String direction) {
		super(info, size, param);
		this.owner = owner;
		this.direction = direction;
		this.positioning();
		this.filtering();
	}


	private void positioning() {
		Body temp = this.owner.getBody();
		float xPos = this.direction.equals(Actor.Direction.RIGHT.toString())
				? temp.getWorldCenter().x + this.owner.getLength() / 2 
				: temp.getWorldCenter().x - this.owner.getLength() / 2;	
		float yPos = temp.getWorldCenter().y;
		this.getBody().setTransform(new Vec2(xPos, yPos), 0f);
	}
	
	private void filtering() {
		Fixture fix = this.getBody().getFixtureList();
		while(fix != null) {
			fix.getFilterData().categoryBits = this.owner.getInfo().getName().equals(RoomEntity.RoomEntityName.HERO.toString()) ? 
					FilterType.HEROATTACK.getValue() : FilterType.ENEMYATTACK.getValue();
			fix.getFilterData().maskBits += this.owner.getInfo().getName().equals(RoomEntity.RoomEntityName.HERO.toString()) ? 
					FilterType.ENEMY.getValue() : FilterType.HERO.getValue();
			fix = fix.getNext();
		}
	}
	
	/**
	 * This will move just in the give direction without caring at your input
	 */
	@Override
	public void move(String d) throws NullPointerException {
		super.move(this.direction);
	}

	/**
	 * @throws IllegalArgumentException Can't perform anything (at the moment)
	 */
	@Override
	public void performAction(String action) {
		ExceptionThrower.checkIllegalArgument(this, x -> true);
	}
	
	/**
	 * {@inheritDoc Attack}
	 */
	@Override
	public Actor getOwner() {
		return this.owner;
	}
	
	
	@Override
	public BodyDef defBody() {
		return Box2DUtils.bodyDefBuilder()
				.type(BodyType.DYNAMIC)
				.gravityOff()
				.build();
	}

	@Override
	public Collection<FixtureDef> defFixture() {
		Filter filter = Box2DUtils.filterBuilder()
					.addMask(FilterType.GROUND.getValue())
					.addMask(FilterType.WALL.getValue())
					.build();
		return this.generateRectangleFix(filter, false);
	}
	
	
}
