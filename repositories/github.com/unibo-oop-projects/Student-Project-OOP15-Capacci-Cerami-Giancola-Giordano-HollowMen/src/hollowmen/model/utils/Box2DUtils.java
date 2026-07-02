package hollowmen.model.utils;

import java.util.Collection;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.Shape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.Filter;
import org.jbox2d.dynamics.FixtureDef;

import hollowmen.model.Enemy;
import hollowmen.model.RoomEntity;
import hollowmen.utilities.RandomSelector;

public class Box2DUtils {

	public static void lowerCorner(RoomEntity re, boolean fly) {
		float halfHeight = re.getHeight() / 2;
		float halfLength = re.getLength() / 2;
		re.getBody().setTransform(new Vec2((float) (RandomSelector.getIntFromRange(0, 1) == 0 ? halfLength + RandomSelector.getIntFromRange(0, 300)
				: Constants.WORLD_SIZE.getWidth() - halfLength - RandomSelector.getIntFromRange(0, 300)),
				fly ? Constants.WORLD_SIZE.height - halfHeight - Constants.FLY_LINE_HEIGHT : Constants.WORLD_SIZE.height - halfHeight), 0);
	}
	
	
	public static void enemyPositioning(Enemy e) {
		if(e.getInfo().getName().equals(RoomEntity.RoomEntityName.BAT.toString())) {
			Box2DUtils.lowerCorner(e, true);
		} else {
			Box2DUtils.lowerCorner(e, false);
		}
	}
	
	public static void linearSpacing(Collection<? extends RoomEntity> entity) {
		int i = 1;
		for(RoomEntity re : entity) {
			float halfHeight = re.getHeight() / 2;
			float halfLength = re.getLength() / 2;	
			int spacing = (int) ((Constants.WORLD_SIZE.getWidth() - re.getLength())  / (entity.size() == 0 ? 1 : entity.size()));
			re.getBody().setTransform(new Vec2((float) (spacing * i)-halfLength, Constants.WORLD_SIZE.height - halfHeight), 0);
			i++;
		}
	}
	
	public static void centerPosition(RoomEntity r) {
		float distance = r.getHeight() / 2;
		r.getBody().setTransform(new Vec2((float) (Constants.WORLD_SIZE.getWidth() / 2), 
				(float) (Constants.WORLD_SIZE.getHeight() - distance)), 0);
	}
	
	public static String suffix(String s) {
		String[] first = s.split("_");
		return first[0];
	}
	
	public static BodyDef bodyDefCloner(BodyDef subj) {
		BodyDef clone = new BodyDef();
		clone.type = subj.type;
		clone.position = subj.position;
		clone.fixedRotation = subj.fixedRotation;
		return clone;
	}
	
	public static BodyDefBuilder bodyDefBuilder() {
		return new BodyDefBuilder();
	}
	
	public static FilterBuilder filterBuilder() {
		return new FilterBuilder();
	}
	
	public static FixtureDefBuilder fixDefBuilder() {
		return new FixtureDefBuilder();
	}
	
	
	//[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[
	public static class BodyDefBuilder {
		private BodyDef def = new BodyDef();
		
		public BodyDefBuilder() {
			def.fixedRotation = true;
			def.setGravityScale(1);
			def.setLinearDamping(0f);
		}
		
		public BodyDefBuilder type(BodyType bodyType) {
			def.type = bodyType;
			return this;
		}
		
		public BodyDefBuilder gravityOff() {
			this.def.setGravityScale(0);
			return this;
		}
		
		public BodyDefBuilder position(float x, float y) {
			def.position = new Vec2(x, y);
			return this;
		}
		
		public BodyDefBuilder rotationOn() {
			def.fixedRotation = false;
			return this;
		}
		
		public BodyDefBuilder position(Vec2 position) {
			def.setPosition(position);
			return this;
		}
		
		public BodyDef build(){
			return this.def;
		}
	}

	public static FixtureDef fixtureDefCloner(FixtureDef subj) {
		return Box2DUtils.fixDefBuilder()
				.density(subj.getDensity())
				.filter(Box2DUtils.filterBuilder()
						.addCategory(subj.getFilter().categoryBits)
						.addMask(subj.getFilter().maskBits).build())
				.friction(subj.getFriction())
				.restitution(subj.getRestitution())
				.sensor(subj.isSensor)
				.shape(subj.shape)
				.build();
	}
	
	public static class FixtureDefBuilder {
		public static final String CATEGORY = "cat";
		public static final String MASK = "mask";
		
		private FixtureDef def = new FixtureDef();
		
		public FixtureDefBuilder() {
			def.filter.categoryBits = 0x0000;
			def.filter.maskBits = 0x0000;
			CircleShape shape = new CircleShape();
			shape.setRadius(1f);
			def.shape = shape;
			def.density = 1f;
		}
		
		public FixtureDefBuilder density(float density) {
			def.setDensity(density);
			return this;
		}
		
		public FixtureDefBuilder filter(Filter filter) {
			def.filter = filter;
			return this;
		}
		
		public FixtureDefBuilder restitution(float fromZeroToOne) {
			def.restitution = fromZeroToOne;
			return this;
		}
		
		public FixtureDefBuilder friction(float fromZeroToOne) {
			def.setFriction(fromZeroToOne);
			return this;
		}
		
		public FixtureDefBuilder sensor(boolean flag) {
			def.isSensor = flag;
			return this;
		}
		
		public FixtureDefBuilder shape(Shape shape) {
			def.setShape(shape);
			return this;
		}
		
		public FixtureDef build() {
			return def;
		}
	}
	
	public static Filter filterCloner(Filter subj) {
		Filter clone = new Filter();
		clone.categoryBits = subj.categoryBits;
		clone.maskBits = subj.maskBits;
		clone.groupIndex = subj.groupIndex;
		return clone;
	}
	
	public static class FilterBuilder {
		private Filter fil = new Filter();
		
		public FilterBuilder() {
			fil.categoryBits = 0x0000;
			fil.maskBits = 0x0000;
		}
		
		public FilterBuilder addCategory(int i) {
			fil.categoryBits += i;
			return this;
		}
		
		public FilterBuilder addMask(int i) {
			fil.maskBits += i;
			return this;
		}
		
		public FilterBuilder index(int i) {
			fil.groupIndex = i;
			return this;
		}
		
		public Filter build() {
			return fil;
		}
	}
	
}
