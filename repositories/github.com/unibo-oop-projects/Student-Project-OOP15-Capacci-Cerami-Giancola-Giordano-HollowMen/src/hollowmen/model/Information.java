package hollowmen.model;

import java.util.Optional;

/**
 * This interface represents {@code Information} of any object <br>
 * it holds information about the name and an {@code Optional} description<br>
 * <br>
 * Plus define a default method equals for recognize two Information using their name
 * @author pigio
 *
 */
public interface Information {
	
	/**
	 * This method give a {@code String} represents the name associated to this object
	 * @return {@link String} represents the name associated to this object
	 */
	public String getName();
	
	/**
	 * This method give a {@code String} represents description about this object
	 * @return {@link Optional}<{@link String}> represents description about this object, it can be empty
	 */
	public Optional<String> getDescription();
	
	/**
	 * This method can be used to see if an {@code Information} <b>obj</b> is equals to this
	 * @param obj {@link Information}
	 * @return {@code true} if their name are the same, {@code false} otherwise
	 */
	public default boolean equals(Information obj) {
		return this.getName().equals(obj.getName());
	}
	
	public default boolean equals(String s) {
		return this.getName().equals(s);
	}
}
