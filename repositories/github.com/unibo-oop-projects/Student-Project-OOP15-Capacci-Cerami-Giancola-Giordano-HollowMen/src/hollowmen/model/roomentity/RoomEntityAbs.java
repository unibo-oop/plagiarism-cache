package hollowmen.model.roomentity;

import java.util.Collection;
import java.util.LinkedList;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.Filter;
import org.jbox2d.dynamics.FixtureDef;

import hollowmen.model.Information;
import hollowmen.model.RoomEntity;
import hollowmen.model.dungeon.DungeonSingleton;
import hollowmen.model.dungeon.InfoImpl;
import hollowmen.model.utils.Box2DUtils;
import hollowmen.utilities.Pair;

/**
 * This class implements {@link RoomEntity}<br>
 * It define the general behavior of any {@code RoomEntity}
 * @author pigio
 *
 */
public abstract class RoomEntityAbs implements RoomEntity{

	private Information info;
	private Body body;
	private float length;
	private float height;
	
	public RoomEntityAbs(Information info2, Pair<Float, Float> size) {
		this.info = new InfoImpl(info2);
		this.length = size.getX();
		this.height = size.getY();
		this.body = DungeonSingleton.getInstance().getWorld().createBody(this.defBody());
		this.defFixture().stream().forEach(x -> this.body.createFixture(x));
		DungeonSingleton.getInstance().getCurrentRoom().addEntity(this);
		this.getBody().setUserData(this);
	}
	
	/**
	 * This method defines the {@link BodyDef} of this {@code RoomEntity}
	 * @return {@link BodyDef}
	 */
	public abstract BodyDef defBody();
	
	/**
	 * This method defines the {@link FixtureDef} of this {@code RoomEntity}
	 * @return {@link FixtureDef}
	 */
	public abstract Collection<FixtureDef> defFixture();
	
	/**
	 * {@inheritDoc RoomEntity}
	 */
	@Override
	public Information getInfo() {
		return info;
	}

	/**
	 * {@inheritDoc RoomEntity}
	 */
	@Override
	public Body getBody() {
		return body;
	}

	/**
	 * {@inheritDoc RoomEntity}
	 */
	@Override
	public void dispose() {
		DungeonSingleton.getInstance().addToDisposeList(this);
	}
	
	/**
	 * {@inheritDoc RoomEntity}
	 */
	@Override
	public float getLength() {
		return this.length;
	}

	/**
	 * {@inheritDoc RoomEntity}
	 */
	@Override
	public float getHeight() {
		return this.height;
	}
	
	/**
	 * This method is a simple way to create a rectangular {@code FixtureDef}
	 * based on {@code getLength()} and {@code getHeight()}
	 * @param filter {@link Filter} to apply on {@code FixtureDef}
	 * @param sensor {@code true} if need to be a sensor, {@code false} otherwise
	 * @return a rectangular {@link FixtureDef}
	 */
	public Collection<FixtureDef> generateRectangleFix(Filter filter, boolean sensor) {
		PolygonShape shape = new PolygonShape();
		float halfLength = (float) (this.getLength() / 2);
		float halfHeight = (float) (this.getHeight() / 2);
		shape.setAsBox(halfLength, halfHeight, this.getBody().getLocalCenter(), 0);
		FixtureDef def =new Box2DUtils.FixtureDefBuilder()
				.shape(shape)
				.friction(0)
				.filter(filter)
				.sensor(sensor)
				.build();
		Collection<FixtureDef> retValue = new LinkedList<>();
		retValue.add(def);
		return retValue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((info == null) ? 0 : info.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;			
		if (obj == null)
			return false;			
		if (!(obj instanceof RoomEntity))
			return false;
		RoomEntity other = (RoomEntity) obj;
		if (info == null) {
			if (other.getInfo() != null)
				return false;
		} else if (!info.equals(other.getInfo()))
			return false;
		if (!this.getBody().getWorldCenter().equals(other.getBody().getWorldCenter()))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RoomEntityAbs [info=" + info + ", length=" + length + ", height=" + height + "]";
	}

	
	

}
