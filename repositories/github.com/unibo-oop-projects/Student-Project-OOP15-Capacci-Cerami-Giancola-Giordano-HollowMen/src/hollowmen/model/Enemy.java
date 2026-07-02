package hollowmen.model;

/**
 * This interface represents all the {@code Enemy} intended as {@link Actor} that will fight the {@link Hero}
 * @author pigio
 *
 */
public interface Enemy extends Actor{
		
	public enum EnemyTitle {

		BOSS,
		COMMANDER,
		ORDINARY;

		@Override
		public String toString(){
			return this.name().toLowerCase();
		}
	}
	
	/**
	 * This method gives an integer value represent his level
	 * @return {@code int} for the level of this {@code Enemy}
	 */
	public int getLevel();

	/**
	 * This method gives the title of this {@code Enemy}<br>
	 * ex. "boss", "commander", "ordinary"
	 * @return {@code String}
	 */
	public String getTitle();
	
	/**
	 * This method gives the {@code Lootable} object for this {@code Enemy}
	 * @return {@link Lootable}
	 */
	public Lootable getLoot();
}
