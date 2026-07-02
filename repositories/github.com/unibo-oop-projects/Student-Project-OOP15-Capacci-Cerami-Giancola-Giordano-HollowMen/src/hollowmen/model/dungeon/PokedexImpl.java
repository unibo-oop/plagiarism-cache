package hollowmen.model.dungeon;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import hollowmen.model.Enemy;
import hollowmen.model.Pokedex;
import hollowmen.model.Room;
import hollowmen.model.enemy.EnemyPool;
import hollowmen.utilities.Tris;

/**
 * This class implements {@link Pokedex}
 * @author pigio
 *
 */
public class PokedexImpl implements Pokedex{

	private Map<Tris<String, Integer, String>, Enemy> enemyMet;
	
	/**
	 * This constructor creates a new empty {@code Pokedex}
	 */
	public PokedexImpl() {
		enemyMet = new HashMap<>();
	}

	/**
	 * This constructor creates a {@code Pokedex} using the <b>enemyName</b>
	 * to determine the enemies already met
	 * @param enemyName
	 */
	public PokedexImpl(Collection<Tris<String, Integer, String>> enemyName) {
		super();
		enemyName.stream()
			.forEach(e -> enemyMet.put(e, EnemyPool.getInstance().getSpecificEnemy(e.getX(), e.getY(), e.getZ())));
	}
	
	/**
	 * {@inheritDoc Pokedex}
	 */
	@Override
	public void checkNewEnemy(Room r) {
		r.getEnemies().stream().forEach(e -> {
			if(!this.enemyMet.containsKey(genKey(e))) {
				this.enemyMet.put(genKey(e), e);
			}
		});
	}

	/**
	 * {@inheritDoc Pokedex}
	 */
	@Override
	public Collection<Enemy> getEnemyMet() {
		return this.enemyMet.entrySet().stream().map(p -> p.getValue()).collect(Collectors.toList());
	}
	

	
	private Tris<String, Integer, String> genKey(Enemy e) {
		return new Tris<>(e.getInfo().getName(), e.getLevel(), e.getTitle());
	}
	
}
