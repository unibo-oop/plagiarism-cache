package characters;

public interface Removable {
	
	void setRemovable(final boolean removable);

	boolean isRemovable();

	void remove();

	boolean collidesWith(final CharacterImpl character);
	
}
