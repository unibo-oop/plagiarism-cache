package hollowmen.model.dungeon;

import java.util.Optional;

import hollowmen.model.Information;


/**
 * This class represents a generic {@link Information}<br>
 * This class is used by any {@link InformationUser} and it's useful
 * for holds a name and a description of any object
 * @author pigio
 *
 */
public class InfoImpl implements Information{

	private String name;
	
	private Optional<String> description;
	
	public InfoImpl(final String name) {
		this.name = name;
		this.description = Optional.empty();
	}
	
	public InfoImpl(final String name, final String description) {
		this.name = name;
		this.description = Optional.ofNullable(description);
	}
	
	public InfoImpl(Information info) {
		this.name = info.getName();
		this.description = info.getDescription();
	}
	
	/**
	 * {@inheritDoc Information}
	 */
	@Override
	public String getName() {
		return this.name;
	}
	
	/**
	 * {@inheritDoc Information}
	 */
	@Override
	public Optional<String> getDescription() {
		return this.description;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/**
	 * Two {@code Information} are equals if their name field are
	 * equals
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InfoImpl other = (InfoImpl) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Info->" + name;
	}

}
