package model;

/**
 * Classe astratta che contiene i metodi astratti per i comportamenti "lineari", cioè strettamente verticali e/o orizzontali.
 * Si utilizza il pattern Template Method, le implementazioni vengono rimandate alle specializzazioni di questa classe.
 * 
 * @author Nicola Santolini
 */
public abstract class AbstractLinearBehaviour extends AbstractBehaviourController {

	/**
	 * Metodo astratto che gestisce il comportamento da applicare quando devono essere eliminate caramelle
	 * in verticale.
	 * @param mat matrice degli elementi
	 */
	protected abstract void makeVertical(final ICandy[][] mat);
	
	/**
	 * Metodo astratto che gestisce il comportamento da applicare quando devono essere eliminate caramelle
	 * in orizzontale.
	 * @param mat matrice degli elementi
	 */
	protected abstract void makeHorizontal(final ICandy[][] mat);
	
}
