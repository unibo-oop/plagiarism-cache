package hollowmen.model.enemy;

import java.util.Collection;

import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

import hollowmen.model.Enemy;
import hollowmen.model.Information;
import hollowmen.model.Parameter;
import hollowmen.model.dungeon.InfoImpl;
import hollowmen.model.roomentity.EnemyAbs;
import hollowmen.model.utils.Box2DUtils;
import hollowmen.model.utils.Constants;

/**
 * This class represents an {@link Enemy} called slime<br>
 * This class provides a Builder instead of Constructors
 * @author pigio
 *
 */
public class Slime extends EnemyAbs{

	
	private Slime(Information info, Collection<Parameter> param, int power, String title) {
		super(new InfoImpl(RoomEntityName.SLIME.toString(), info.getDescription().get()), Constants.SLIME_SIZE, param, power, title);
	}

	@Override
	public BodyDef defBody() {
		return Box2DUtils.bodyDefBuilder()
				.type(BodyType.DYNAMIC).build();
	}

	@Override
	public Collection<FixtureDef> defFixture() {
		return this.generateRectangleFix(this.standardEnemyFilter(), false);
	}

	public static class Builder extends EnemyBuilderImpl {

		@Override
		public Enemy build() throws IllegalStateException {
			Enemy en = new Slime(new InfoImpl(RoomEntityName.BAT.toString(), super.descritpion),
					super.parameters, super.level, super.title);
			this.standardException(en);
			return en;
		}
	}
	
}
