package hollowmen.model.roomentity;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.FixtureDef;

import hollowmen.model.Actor;
import hollowmen.model.dungeon.FilterType;
import hollowmen.model.utils.Box2DUtils;

/**
 * This class is a special Sensor that let an {@code Actor} jump
 * @author pigio
 *
 */
public class JumpSensor extends FixtureDef{

	public JumpSensor(Actor act) {
		PolygonShape box = new PolygonShape();
		box.setAsBox(1f, 1f, new Vec2(0,  act.getHeight()/ 2), 0);
		act.getBody().createFixture(Box2DUtils.fixDefBuilder()
				.sensor(true)
				.shape(box)
				.density(0)
				.filter(Box2DUtils.filterBuilder()
						.addCategory(FilterType.JUMP.getValue())
						.addMask(FilterType.GROUND.getValue())
						.build())
				.build());
	}
}
