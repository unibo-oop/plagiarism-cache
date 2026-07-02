package model;


/**
 * Classe che implementa l'interfaccia di "facciata" e interagisce con il Board e i gestori dei comportamenti
 * e dei controlli.
 * 
 * @author Nicola Santolini
 */

public class Game implements IGame {

	private final IChecks checker;
	
	private final TrisBehaviour trisController;
	private final PokerBehaviour pokerController;
	private final WrappedBehaviour wrappedController;
	private final FiveBehaviour fiveController;	
	
	/**
	 * Costruttore.
	 */
	public Game() {

		this.checker = new Checks();
		
		this.trisController = new TrisBehaviour();
		this.pokerController = new PokerBehaviour();
		this.wrappedController = new WrappedBehaviour();
		this.fiveController = new FiveBehaviour();
	}
	
	@Override
	public int gameLoop(final ICandy[][] mat) {

		if (checker.checkFiveVertical(mat)) {
			fiveController.makeVertical(mat);
			return ModelUtilities.SPECIAL_POINTS;
		} else if (checker.checkFiveHorizontal(mat)) {
			fiveController.makeHorizontal(mat);
			return ModelUtilities.SPECIAL_POINTS;
		} else if (checker.checkWrapped(mat)) {
			wrappedController.controlWrapped(mat);
			return ModelUtilities.WRAPPED_POINTS;
		} else if (checker.checkPokerVertical(mat)) {
			pokerController.makeVertical(mat);
			return ModelUtilities.STRIPED_POINTS;
		} else if (checker.checkPokerHorizontal(mat)) {
			pokerController.makeHorizontal(mat);
			return ModelUtilities.STRIPED_POINTS;
		} else if (checker.checkTrisVertical(mat)) {
			trisController.makeVertical(mat);
			return ModelUtilities.TRIS_POINTS;
		} else if (checker.checkTrisHorizontal(mat)) {
			trisController.makeHorizontal(mat);
			return ModelUtilities.TRIS_POINTS;
		}
		return 0;
	}
	
	@Override
	public boolean checkTris(final ICandy[][] mat) {
		return checker.checkTris(mat);
	}
	
	@Override
	public boolean checkNextMove(final ICandy[][] mat) {
		return checker.checkNextMove(mat);
	}
	
	@Override
	public int doFive(final ICandy[][] mat, final int c) {
		return fiveController.doFive(mat, c);
	}
}
