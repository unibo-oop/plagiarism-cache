package hollowmen.model.roomentity;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import org.jbox2d.common.Vec2;

import hollowmen.enumerators.ActorState;
import hollowmen.enumerators.ParamName;
import hollowmen.model.Actor;
import hollowmen.model.Information;
import hollowmen.model.Parameter;
import hollowmen.model.dungeon.time.TimerSingleton;
import hollowmen.model.roomentity.typeaction.Attack;
import hollowmen.model.utils.Constants;
import hollowmen.utilities.ExceptionThrower;
import hollowmen.utilities.Pair;

/**
 * This class extends {@link RoomEntityAbs} and implements {@link Actor}<br>
 * Classes that extends this class know how to jump and attack
 * @author pigio
 *
 */
public abstract class ActorAbs extends RoomEntityAbs implements Actor{

	
	protected int jumpability = 0;
	private String state = ActorState.STANDING.toString();
	private boolean facingRight = true;
	protected ActionAllowed actionAllowed = new ActionAllowedImpl();
	private Map<String, Parameter> parameters;
	private Collection<Information> status = new LinkedList<>();
	
	
	public ActorAbs(Information info, Pair<Float, Float> size, Collection<Parameter> param) {
		super(info, size);
		new JumpSensor(this);
		this.parameters = new HashMap<>();
		param.stream().forEach(p -> this.parameters.put(p.getInfo().getName(), p));
		parameters.put(Parameter.ParamName.HP.toString(),
				new HPclass(this.parameters.get(Parameter.ParamName.HPMAX.toString()), this));
		actionAllowed.getActionAllowed().put(Actor.Action.JUMP.toString(), () -> this.jump());
		actionAllowed.getActionAllowed().put(Actor.Action.ATTACK.toString(), () -> this.attack());
	}

	/**
	 * {@inheritDoc Actor}
	 */
	@Override
	public void performAction(String action) throws NullPointerException {
		ExceptionThrower.checkNullPointer(action);
		ExceptionThrower.checkIllegalArgument(action, a -> !this.actionAllowed.getActionAllowed().containsKey(a));
		actionAllowed.getActionAllowed().get(action).run();
	}

	/**
	 * {@inheritDoc Actor}
	 */
	public void move(String d) {
		changeFacing(d);
		float speedX = (float) this.getParameters().get(ParamName.MOVSPEED.toString()).getValue() * Constants.MAXSPEED
				+ this.getBody().getLinearVelocity().x;
		float speedY = (float) this.getBody().getLinearVelocity().y;
		this.getBody().applyForceToCenter(new Vec2(this.isFacingRight() ? speedX : -speedX, speedY));
		if(this.jumpability > 0) {
			this.getBody().setGravityScale(1f);
		}
		if(this.state.equals(ActorState.STANDING.toString())) {
			this.setState(ActorState.MOVING.toString());
		}
	}

	private void changeFacing(String d) {
		if(d.equals(Direction.RIGHT.toString()) && !this.isFacingRight() 
				|| d.equals(Direction.LEFT.toString()) && this.isFacingRight()) {
			this.setFacingRight(!this.isFacingRight());
		}
	}
	
	/**
	 * {@inheritDoc Actor}
	 */
	@Override
	public Map<String, Parameter> getParameters() {
		return this.parameters;
	}

	/**
	 * This method set the facing
	 * @param b {@code true} facing right, {@code false} otherwise
	 */
	public void setFacingRight(boolean b) {
		this.facingRight = b;
	}
	
	/**
	 * {@inheritDoc Actor}
	 */
	@Override
	public boolean isFacingRight() {
		return facingRight;
	}

	/**
	 * {@inheritDoc Actor}
	 */
	@Override
	public String getState() {
		return this.state;
	}

	/**
	 * {@inheritDoc Actor}
	 */
	@Override
	public void setState(String state) {
		this.state = state;
	}

	public ActionAllowed getAction() {
		return this.actionAllowed;
	}
	
	/**
	 * {@inheritDoc Actor}
	 */
	@Override
	public Collection<Information> getStatus() {
		return this.status;
	}
		
	/**
	 * This method is used by {@link GameCollisionListener} for tell this {@code Actor} he's on the ground
	 */
	public void addGroundContact(){
		this.jumpability ++;
	}
	
	/**
	 * This method is used by {@link GameCollisionListener} for tell this {@code Actor} he isn't on the ground
	 */
	public void removeGroundContact() {
		this.jumpability --;
	}
	
	private void jump() {
		if((!this.getState().equals(ActorState.ATTACKING.toString()))
				&& this.jumpability > 0) {
			this.setState(ActorState.JUMPING.toString());
			this.getBody().setGravityScale(-1f);
			this.getBody().setLinearVelocity(new Vec2(this.getBody().getLinearVelocity().x, Constants.JUMPFORCE.y));
			TimerSingleton.getInstance().register(this, 2, x -> x.getBody().setGravityScale(1));
		}
	}
	
	private void attack() {
		new Attack().doAction(this);
	}

}
