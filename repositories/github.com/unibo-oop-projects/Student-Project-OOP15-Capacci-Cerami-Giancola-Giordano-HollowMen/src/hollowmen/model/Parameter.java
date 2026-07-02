package hollowmen.model;

import java.util.Collection;

/**
 * This interface represent a {@code Parameter} intended as a {@code double} value
 * with a name associated inherited by {@link Information}<br>
 * <br>
 * A {@code Parameter} can collect and remove {@link Modifier}
 * @author pigio
 *
 */
public interface Parameter extends InformationUser{
	
	/**
	 * All Parameter name in game
	 * @author pigio
	 *
	 */
	public enum ParamName {

		ATTACK,
		DEFENSE,
		HPMAX,
		HP,
		ATTACKSPEED,
		ATTACKRANGE,
		MOVSPEED,
		DURATION,
		COOLDOWN;
		
		@Override
		public String toString(){
			return this.name().toLowerCase();
		}
		
	}
	
	/**
	 * This method give the final value for this {@code Parameter}
	 * @return {@code double} value
	 */
	public double getValue();
	
	/**
	 * This method <u>add</u> <b>mod</b> {@code Modifier} to this {@code Parameter}
	 * @param mod {@link Modifier}
	 * @throws IllegalArgumentException If <b>mod</b> doesn't target this {@code Parameter}
	 * @throws NullPointerException
	 */
	public void addModifier(Modifier mod) throws NullPointerException;
	
	/**
	 * This method <u>remove</u> <b>mod</b> {@code Modifier} to this {@code Parameter}
	 * @param mod {@link Modifier}
	 * @throws IllegalArgumentException If <b>mod</b> doesn't target this {@code Parameter}<br>
	 * OR If this {@code Paramete} hasn't <b>mod</b>
	 * @throws NullPointerException
	 */
	public void removeModifier(Modifier mod) throws IllegalArgumentException, NullPointerException;
	
	/**
	 * This method give all the Modifier in this Parameter
	 * @return {@code Collection} {@link Modifier}
	 */
	public Collection<Modifier> getModifiers();
	
}
