package view.level;

/**
 * Classe astratta che implementa ILevel che contiene un unico metodo astratto che verrà implementato come
 * serve nelle classi che estenderanno questa stessa classe.
 * 
 * @author Beatrice Ricci
 *
 */
public abstract class AbstractLevel implements ILevel {

	@Override
	public abstract void setLevel();
}
