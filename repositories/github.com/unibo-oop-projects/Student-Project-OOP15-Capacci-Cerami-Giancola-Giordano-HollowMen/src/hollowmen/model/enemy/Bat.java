package hollowmen.model.enemy;

import java.util.Collection;

import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.Filter;
import org.jbox2d.dynamics.FixtureDef;

import hollowmen.model.Enemy;
import hollowmen.model.Information;
import hollowmen.model.Parameter;
import hollowmen.model.dungeon.FilterType;
import hollowmen.model.dungeon.InfoImpl;
import hollowmen.model.roomentity.EnemyAbs;
import hollowmen.model.utils.Box2DUtils;
import hollowmen.model.utils.Constants;

/**
 * This class represents an {@link Enemy} called bat<br>
 * This is a flying enemy<br>
 * This class provides a Builder instead of Constructors
 * @author pigio
 *
 */
public class Bat extends EnemyAbs{

	
	private Bat(Information info, Collection<Parameter> param, int power, String title) {
		super(new InfoImpl(RoomEntityName.BAT.toString(), info.getDescription().get()), Constants.BAT_SIZE, param, power, title);
	}

	
	
	@Override
	public BodyDef defBody() {
		return Box2DUtils.bodyDefBuilder()
				.type(BodyType.DYNAMIC).build();
	}

	@Override
	public Collection<FixtureDef> defFixture() {
		Filter filt = this.standardEnemyFilter();
		filt.categoryBits += FilterType.FLY.getValue();
		filt.maskBits += FilterType.FLYLINE.getValue();
		return this.generateRectangleFix(filt, false);
	}

	public static class Builder extends EnemyBuilderImpl {

		@Override
		public Enemy build() throws IllegalStateException {
			Enemy en = new Bat(new InfoImpl(RoomEntityName.BAT.toString(), super.descritpion),
					super.parameters, super.level, super.title);
			this.standardException(en);
			return en;
		}
	}
	
	
}
