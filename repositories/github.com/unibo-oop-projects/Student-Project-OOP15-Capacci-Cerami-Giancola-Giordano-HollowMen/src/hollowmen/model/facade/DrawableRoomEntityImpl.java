package hollowmen.model.facade;

import hollowmen.enumerators.ActorState;

/**
 * 
 * @author Giordo
 *
 */
public class DrawableRoomEntityImpl implements DrawableRoomEntity{
	
	private String name;
	private Point2D position;
	private boolean isFacingRight;
	private ActorState state;
	
	public DrawableRoomEntityImpl(String name, Point2D position, boolean isFacingRight, ActorState state){
		this.name=name;
		this.position=position;
		this.isFacingRight=isFacingRight;
		this.state=state;
	}
	
	public String getName() {
		return this.name;
	}

	public Point2D getPosition() {
		return this.position;
	}

	public boolean isFacingRight() {
		return this.isFacingRight;
	}

	public ActorState getState() {
		return this.state;
	}

}
