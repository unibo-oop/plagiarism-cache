package hollowmen.model.roomentity;

import java.util.Collection;

import hollowmen.model.Actor;
import hollowmen.model.Parameter;
import hollowmen.model.RoomEntity;
import hollowmen.model.dungeon.InfoImpl;
import hollowmen.model.dungeon.time.TimerSingleton;
import hollowmen.model.utils.Constants;
import hollowmen.utilities.Pair;

/**
 * This class extends {@link AttackAbs} and represent a static attack that can't move but his
 * length is determinate by the owner {@code Parameter} ATTACKRANGE
 * @author pigio
 *
 */
public class Melee extends AttackAbs{

	private static final float HEIGHT = 50f;

	public Melee(Actor owner, Collection<Parameter> param, float length,
			String direction) {
		super(new InfoImpl(RoomEntity.RoomEntityName.BULLET.toString()), new Pair<>(length, HEIGHT), param, owner, direction);
		TimerSingleton.getInstance().register(this, Constants.ATTACK_DURATION, x -> x.dispose());
	}

	@Override
	public void move(String s) {};
	
}
