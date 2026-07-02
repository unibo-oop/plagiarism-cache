package main.java.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Consumer;
import com.google.common.collect.ImmutableMap;

import main.java.model.Tetromino.Direction;
import main.java.model.Tetromino.RotationSense;
import main.java.model.queue.AutofillableQueue;

/**
 * Implementation of the model interface.
 * It uses the Strategy pattern in order to control the behavior of the score,
 * level and gravity. This version of the tetris uses the following rules:
 * - score per line(s) cleared = <40|100|300|1200> * (level + 1)
 *									1	2	3	4	lines cleared
 *   the more lines are cleared at once the more will be the reward;
 * - level = lines cleared / 10 + 1
 * - gravity = TODO
 */
public class TetrisImpl implements Tetris, Controllable {
	
	private final ScoreManager scoreManager;
	private final LevelManager levelManager;
	private final GravityManager gravityManager;
	private final InputManager<Controllable> inputManager;
	
	private Tetromino current;
	private final List<Square> board;
	private int lines;
	private int currentScore;
	
	private final List<Input> inputs;
	private double accumulator;
	private boolean softDrop;
	/*Attributi usati in modelImpl */
	private List<Square> matrix = new ArrayList<>(); //Inserisci solo i pezzi gia arrivati,quindi inizialmente e vuoto
	private Tetromino t;
	private Integer punteggio;
	private String path;
	private List<Square> tetramino;
	private boolean gameOver;
	private List<Square> listHold = new ArrayList<>();
	private double currentSpeed = 1.0;
	private int level = 0;
	private int height;
	private int width;
	private AutofillableQueue<Shape> queue;
	
	/**
	 * Class constructor.
	 */
		
		
		/*queue= new TetrisQueue(() -> Shape.I.get(), size);*/
	
	public TetrisImpl(int height, int width) {
		
		tetramino= t.getAllSquares();
		gameOver=false;
		if(checkTouch(tetramino)) {
			gameOver=true;
		}
		this.height = height;
		this.width = width;
		
		this.scoreManager = new ScoreManager() {

			private final Map<Integer, Integer> points =
					new ImmutableMap.Builder<Integer, Integer>()
					.put(1,40)
					.put(2, 100)
					.put(3, 300)
					.put(4, 1200)
					.build();
			
			@Override
			public int get(int n, int level) {
				return points.get(n) * (level + 1);
			}
		};
		this.levelManager = lines -> lines / 10 + 1;
		this.gravityManager = level -> { 
			return gravity(level);
		};
		this.inputManager = new InputManager<Controllable>() {

			private final Map<Input, Consumer<Controllable>> inputs =
					new ImmutableMap.Builder<Input, Consumer<Controllable>>()
					.put(Input.MOVE_LEFT, t -> t.move(Direction.LEFT))
					.put(Input.MOVE_RIGHT, t -> t.move(Direction.RIGHT))
					.put(Input.ROTATE_CW, t -> t.rotate(RotationSense.CLOCKWISE))
					.put(Input.ROTATE_CCW, t -> t.rotate(RotationSense.COUNTERCLOCKWISE))
					.put(Input.SOFT_DROP_ON, t -> t.setSoftDrop(true))
					.put(Input.SOFT_DROP_OFF, t -> t.setSoftDrop(false))
					.build();
			
			@Override
			public Consumer<Controllable> resolve(Input i) {
				return inputs.get(i);
			}
			
		};
		this.inputs = new ArrayList<>();
		this.board = new ArrayList<>();
		this.accumulator = 0;
		this.softDrop = false;
	}

	/**
	 * Getter for the current score.
	 */
	@Override
	public int getScore() {
		return this.currentScore;
	}

	/**
	 * Getter for the cleared lines.
	 */
	@Override
	public int getLines() {
		return this.lines;
	}

	/**
	 * Getter for the current level.
	 */
	@Override
	public int getCurrentLevel() {
		return levelManager.get(this.lines);
	}

	/**
	 * Getter for the current tetromino.
	 */
	@Override
	public Tetromino getCurrentTetromino() {
		return current;
	}

	/**
	 * Getter for the board.
	 */
	@Override
	public List<Square> getBoard() {
		return board;
	}
	
	/**
	 * Sends the input to the game.
	 */
	public void sendInput(List<Input> inputs) {
		this.inputs.addAll(inputs);
	}

	/**
	 * Function that updates the state of the game.
	 * @param time
	 */
	@Override
	public void update(double time) {
		this.accumulator += time;
		this.resolveInput();
		double p;
		
		while(accumulator > (p = (getPeriod() * getMultiplier()))) {
			this.accumulator -= p;
			this.move(Direction.DOWN);
			
		}
	}
	
	/**
	 * Rotates the current tetromino in the given rotation sense.
	 */
	@Override
	public void rotate(RotationSense r) {
		List<Square> app = new ArrayList<>();
		Tetromino temp = t.clone();
		temp.rotate(r);
		app = temp.getAllSquares();
		if (!(checkTouch(app) || isOutOfBounds(app))) {
			t.rotate(r);
		}
		lineElimination(checkEliminationLine());
		
	}
	
	/**
	 * Moves the current tetromino in the given direction.
	 */
	@Override
	public void move(Direction d) {
		Tetromino temp = t.clone();
		List<Square> app= new ArrayList<>();
		temp.move(d);
		app=temp.getAllSquares();
		
		if(checkTouch(app) && d == Direction.DOWN) {
			matrix.addAll(t.getAllSquares());/*Ancoro il pezzo*/
			tetramino.clear();
		}
		else if(!isOutOfBounds(app)) {
			t.move(d);
		}
		
		lineElimination(checkEliminationLine());
			
			
		
	}
	
	private boolean checkTouch(List<Square> app) {
		return app.stream().filter(y -> matrix.contains(y)).count() > 0 ;
	}
	
	
	private boolean isOutOfBounds(List<Square> app) {
		return app.stream()
				.filter(y -> y.getCoords().getX() > height && y.getCoords().getY() > width)
				.count() > 0;
	}

	/**
	 * Setter for the soft-drop mode.
	 */
	@Override
	public void setSoftDrop(boolean activated) {
		this.softDrop = activated;
	}
	
	/**
	 * Adds points to the score according to the score manager.
	 * @param n	amount of lines cleared
	 */
	private void addPoints(int n) {
		this.currentScore += scoreManager.get(n, getCurrentLevel());
	}

	/**
	 * Getter for the gravity value.
	 */
	private double getPeriod() {
		return 1 / gravityManager.get(getCurrentLevel());
	}
	
	/**
	 * Getter for the time multiplier. The time multiplier is a modifier for
	 * the period of time needed to update the game. If softDrop value is true
	 * then the time multiplier will be 1/2.
	 */
	private double getMultiplier() {
		return softDrop ? 0.5 : 1;
	}
	
	/**
	 * Applies to the game all the input given.
	 */
	private void resolveInput() {
		this.inputManager.resolveAll(inputs, this);
	}

	@Override
	public boolean isGameOver() {
		return this.gameOver;
	}
	
	@Override
	public void reset() {
		this.matrix.clear();
		this.tetramino.clear();
	}

	@Override
	public void lineElimination(List<Integer> list) {
		if(!list.isEmpty()) {
			for(Square s: matrix) {
				if(list.contains(s.getCoords().getY())) {
					matrix.remove(s);
				}
			}
		}
	}
	
	/*ritorna  le linee da eliminare*/
	private List<Integer> checkEliminationLine() {
		List<Integer> list = new ArrayList<>();
		Map<Integer , Integer> map = fillingMap();/*creo una map che ha come key il numero della riga e come values il numero di celle occupate in quella riga*/
		for(Entry<Integer, Integer> e : map.entrySet()) {
			if(e.getValue() == width) {
				list.add(e.getKey());
			}
		}
		return list;
		
	}
	
	
	private Map<Integer , Integer> fillingMap(){
		Map<Integer , Integer> map = new HashMap<>();
		Integer numberOfSquare = 1;/*per eliminare una riga devo avere "width" celle occupate in quella riga, numberSquare è quel numero*/

		for(int i = 0 ; i < height * width ; i++) {
			if( map.containsKey(matrix.get(i).getCoords().getX())) {
				numberOfSquare = map.get(matrix.get(i).getCoords().getX())+1;
			}
			map.put(matrix.get(i).getCoords().getX(), numberOfSquare);
		}
		return map;
	}
	
	@Override
	public void hold() {
		/* Non puo essere richiamata due volte per lo stesso tetramino*/
		List<Square> list = new ArrayList<>();
		if(listHold.isEmpty()) {
			/*listHold.addAll(tetramino);
			reset();*/
			/*TODO*/
		 
			reset();
		}
		else {
			list.addAll(listHold);
			listHold = tetramino;
			tetramino = list;
		}
		
	}
	
	private double gravity(int level) {
		double speedChange;
		if(this.level == 0 || this.level != level) {
			this.level = level;
			speedChange = currentSpeed/3;
			return currentSpeed -= speedChange;
		}
		return currentSpeed;
	}
	
	
}
