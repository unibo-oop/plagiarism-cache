package elements;

/**
 * Represents an enumeration for the possible animation who can do Characters.
 * 
 * @author Luca
 */
public enum AnimationEnum {
	/**
	 * Wait. 
	 */
	WAIT("Wait"),
	/**
	 * Run. 
	 */
	RUN("Run"),
	/**
	 * Fire. 
	 */
	FIRE("Fire"),
	/**
	 * Fly. 
	 */
	FLY("Fly"),
	/**
	 * Dead. 
	 */
	DEAD("Dead");	
	
	private final String animation;
	
	AnimationEnum(final String animationName){
		this.animation = animationName;
	}	
    /**
     * Return the String value of specific animation. 
     * @return the String value.
     */
	public String getAnimation() {
		return this.animation;
	}	
}
