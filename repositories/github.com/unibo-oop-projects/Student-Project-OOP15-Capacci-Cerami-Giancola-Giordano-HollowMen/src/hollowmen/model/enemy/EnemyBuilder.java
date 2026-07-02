package hollowmen.model.enemy;

import java.util.Collection;

import hollowmen.model.Enemy;
import hollowmen.model.Parameter;

/**
 * This interface is used for building an {@link Enemy}
 * @author pigio
 *
 */
public interface EnemyBuilder {

	/**
	 * 
	 * @param description
	 * @return this
	 */
	public EnemyBuilder description(String description);
	
	/**
	 * 
	 * @param level {@code int} from 0 to 20
	 * @return this
	 */
	public EnemyBuilder level(int level);
	
	/**
	 * If the title isn't one of an enemyTitle {@code build()} will throw exception
	 * @param title at the moment "ordinary", "commander", "boss"
	 * @return this
	 */
	public EnemyBuilder title(String title);
	
	/**
	 * If the following {@code Parameter}s aren't in the <b>parameters</b> build will throw exception:<br>
	 * -> "hpmax" any positive non zero base value,<br>
	 * -> "defense" any value,<br>
	 * -> "attack" any value,<br>
	 * -> "attackrange" any value, <br>
	 * -> "attackspeed" any positive non zero base value, <br>
	 * -> "movspeed" any positive non zero base value. <br>
	 * @param parameters
	 * @return
	 */
	public EnemyBuilder param(Collection<Parameter> parameters);
	
	/**
	 * 
	 * @return {@link Enemy}
	 * @throws IllegalStateException If any of the previous method wasn't called or called with wrong input
	 */
	public Enemy build() throws IllegalStateException;
	
}
