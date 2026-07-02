package hollowmen.model.roomentity.typeaction;

import java.util.LinkedList;
import java.util.List;

import hollowmen.enumerators.ActorState;
import hollowmen.enumerators.ParamName;
import hollowmen.enumerators.StatusName;
import hollowmen.model.Actor;
import hollowmen.model.Parameter;
import hollowmen.model.TypeAction;
import hollowmen.model.dungeon.InfoImpl;
import hollowmen.model.dungeon.ParamImpl;
import hollowmen.model.dungeon.time.TimerSingleton;
import hollowmen.model.roomentity.Bullet;
import hollowmen.model.roomentity.Melee;
import hollowmen.model.utils.Constants;

/**
 * This class implements {@link TypeAction}<br>
 * It holds a generic "attack routine"
 * @author pigio
 *
 */
public class Attack implements TypeAction{

	
	public Attack() {};
	
	@Override
	public void doAction(Actor subject) throws NullPointerException {
		if(subject.getStatus().contains(new InfoImpl(StatusName.RECHARGE.toString()))
				|| !subject.getState().equals(ActorState.ATTACKING.toString())) {
			if(subject.getParameters().get(ParamName.ATTACKRANGE.toString()).getValue() <= 0) {
				createProjectile(subject);
			} else {
				createMelee(subject);
			}
			
			subject.setState(ActorState.ATTACKING.toString());
			subject.getParameters().get(ParamName.MOVSPEED.toString());
			TimerSingleton.getInstance().register(subject, Constants.ATTACK_DURATION,
					x -> {
						x.setState(ActorState.STANDING.toString());
						x.getStatus().add(new InfoImpl(StatusName.RECHARGE.toString()));
					});
			TimerSingleton.getInstance().register(subject, Constants.ATTACK_DURATION + 
					subject.getParameters().get(ParamName.ATTACKSPEED.toString()).getValue(),
					x -> x.getStatus().remove(new InfoImpl(StatusName.RECHARGE.toString())));
		}
	}

	
	private void createProjectile(Actor subject) {
		List<Parameter> temp = new LinkedList<>();
		temp.add(new ParamImpl(subject.getParameters().get(ParamName.ATTACK.toString()).getInfo(),
					subject.getParameters().get(ParamName.ATTACK.toString()).getValue()));
		temp.add(new ParamImpl(subject.getParameters().get(ParamName.HPMAX.toString()).getInfo(),
					subject.getParameters().get(ParamName.HPMAX.toString()).getValue()));
		temp.add(new ParamImpl(subject.getParameters().get(ParamName.MOVSPEED.toString()).getInfo(),
					subject.getParameters().get(ParamName.MOVSPEED.toString()).getValue() * Constants.MAXSPEED));
		new Bullet(subject, temp, subject.isFacingRight() ? Actor.Direction.RIGHT.toString() : Actor.Direction.LEFT.toString());
	}
	
	private void createMelee(Actor subject) {
		List<Parameter> temp = new LinkedList<>();
		temp.add(new ParamImpl(subject.getParameters().get(ParamName.ATTACK.toString()).getInfo(),
					subject.getParameters().get(ParamName.ATTACK.toString()).getValue()));
		temp.add(new ParamImpl(subject.getParameters().get(ParamName.HPMAX.toString()).getInfo(),
				subject.getParameters().get(ParamName.HPMAX.toString()).getValue()));
		new Melee(subject, temp, (float) subject.getParameters().get(Parameter.ParamName.ATTACKRANGE.toString()).getValue(),
				subject.isFacingRight() ? Actor.Direction.RIGHT.toString() : Actor.Direction.LEFT.toString());
	}
	
	
	
}
