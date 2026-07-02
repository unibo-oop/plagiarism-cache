package hollowmen.model.utils;

import java.awt.Rectangle;

import org.jbox2d.common.Vec2;

import hollowmen.model.Modifier.Operation;
import hollowmen.utilities.Pair;


public class Constants {
	
	public static final int FLY_LINE_HEIGHT = 300;
	
	public static final double GHOST_TIME = 2;
	
	public static final int ROOM_TO_VISIT = 4;
	
	public static final double ATTACK_DURATION = 0.3;
	
	public static final float MAXSPEED = 50;
	
	public static final float FLATSPEED = 30;
	
	public static final Rectangle WORLD_SIZE = new Rectangle(800, 600);
	
	public static final Vec2 JUMPFORCE = new Vec2(0, -400);

	public static final int CHILDROOMQUANTITY = 3;
	
	public static final int SKILLPOINTS_ONLEVELUP = 1;
	
	public static final int SKILLPOINTS_FORUPGRADE = 3;
	
	public static final Pair<Float, Float> BULLET_SIZE = new Pair<>(50f, 50f);
	
	public static final Pair<Float, Float> DOOR_SIZE = new Pair<>(100f, 170f);

	public static final Pair<Float, Float> HERO_SIZE = new Pair<>(80f, 150f);
	
	public static final Pair<Float, Float> SLIME_SIZE = new Pair<>(110f, 70f);
	
	public static final Pair<Float, Float> BAT_SIZE = new Pair<>(80f, 70f);
	
	public static final Pair<Float, Float> PUPPET_SIZE = new Pair<>(100f, 100f);
	
	public static final Pair<Float, Float> TREASURE_SIZE = new Pair<>(50f, 50f);
	
	//STATPOINT STUFFS
	
	public static final int STATPOINTS_ONLEVELUP = 2;
	
	public static final double STAT_INCREASE = 5;
	
	public static final Operation DEFAULT_OP = Operation.ADD;
	
	
	//TRESURE CHEST
		
	public static final int TREASURE_FLATFLOOR = 50;
	
	public static final int TREASURE_FLATROOM = 10;
	
	public static final double TREASURE_ITEMCHANCE = 0.7;
	
}
