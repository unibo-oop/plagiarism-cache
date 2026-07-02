package tetris.controllers;

import tetris.models.ShapeImpl;
import tetris.models.ShapeImpl.Tetrominoes;
import tetris.views.TetrisBoardImpl;
import tetris.views.TetrisFrameImpl;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import tetris.controllers.TetrisSoundImpl;

/**
 * This class is more important class of tetris game. Manage start and pause of
 * game, removes completed lines, sound, the new piece, paints new tetromino
 * movetetromino to left and right and check if tetromino can rotate.
 * 
 * @author Carlo
 *
 */
public class BoardControllerImpl implements BoardController {
	private static TetrisBoardImpl tetrisBoard;
	private static TetrisFrameImpl tetrisFrame = new TetrisFrameImpl();
	private static int boardWidth;
	private static int boardHeight;
	private static boolean isFallingFinished = false;
	static boolean isStarted = false;
	static boolean isPaused = false;
	private static TetrisSoundImpl sound;
	public static int numLinesRemoved = 0;
	private int currentX = 0;
	private int currentY = 0;
	static Timer timer;
	public static int level = 1;
	public final static int ROWSDELETEDBYLEVEL = 5;
	private static ShapeImpl currentPiece;
	private static ShapeImpl nextPiece;
	private static ShapeImpl.Tetrominoes[] board;

	/**
	 * class constructor.
	 * 
	 * @param boardWidth
	 *            width of grid game
	 * @param boardHeight
	 *            height of grid game
	 * @param tetrisBoard
	 *            grid of game
	 */
	@SuppressWarnings("static-access")
	public BoardControllerImpl(int boardWidth, int boardHeight, TetrisBoardImpl tetrisBoard) {
		this.boardWidth = boardWidth;
		this.boardHeight = boardHeight;
		this.tetrisBoard = tetrisBoard;
		currentPiece = new ShapeImpl();
		nextPiece = new ShapeImpl();
		nextPiece.setPieceShape(Tetrominoes.NoShape);
		setTimer(new Timer(400, tetrisBoard));
		getTimer().start();
		board = new ShapeImpl.Tetrominoes[boardWidth * boardHeight];
		clearBoard();
	}

	public void gameAction() {
		if (isFallingFinished) {
			isFallingFinished = false;
			newPiece();
		} else {
			oneLineDown();
		  }
	}

	public boolean isStarted() {
		return isStarted;
	}

	public boolean isPaused() {
		return isPaused;
	}

	public boolean isCurrentPieceNoShaped() {
		return currentPiece.getPieceShape() == ShapeImpl.Tetrominoes.NoShape;
	}

	public boolean isNextPieceNoShaped() {
		return nextPiece.getPieceShape() == ShapeImpl.Tetrominoes.NoShape;
	}

	public void start() {
		sound = new TetrisSoundImpl();
		sound.playTheme();
		if (isPaused)
			return;
		isStarted = true;
		isFallingFinished = false;
		numLinesRemoved = 0;
		clearBoard();
		newPiece();
		getTimer().start();
	}

	public void pause() {
		if (!isStarted)
			return;
		isPaused = !isPaused;
		if (isPaused) {
			getTimer().stop();
			sound.stopTheme();
			tetrisBoard.setStatusText(TetrisFrameImpl.name);
		} else {
			getTimer().start();
			tetrisBoard.setStatusText(String.valueOf(numLinesRemoved));
			sound.playTheme();
		}
		tetrisBoard.repaint();
	}

	public void oneLineDown() {
		if (!tryMove(currentPiece, currentX, currentY - 1))
			pieceDropped();
	}

	public void clearBoard() {
		for (int i = 0; i < boardHeight * boardWidth; i++)
			board[i] = ShapeImpl.Tetrominoes.NoShape;
	}

	public void dropDown() {
		int newY = currentY;
		while (newY > 0) {
			if (!tryMove(currentPiece, currentX, newY - 1))
				break;
			newY--;
		}
		pieceDropped();
	}

	/**
	 * This methode locates the tetramino
	 * 
	 * @param x
	 *            X represents the row
	 * @param y
	 *            Y represents the column
	 * @return
	 */
	private static ShapeImpl.Tetrominoes shapeAt(int x, int y) {
		return board[y * boardWidth + x];
	}

	public void paint(Graphics g, double width, double height) {
		int squareWidth = (int) width / boardWidth;
		int squareHeight = (int) height / boardHeight;
		int boardTop = (int) height - boardHeight * squareHeight;

		for (int i = 0; i < boardHeight; i++) {
			for (int j = 0; j < boardWidth; j++) {
				tetris.models.ShapeImpl.Tetrominoes shape = shapeAt(j, boardHeight - i - 1);
				if (shape != tetris.models.ShapeImpl.Tetrominoes.NoShape)
					tetrisBoard.drawSquare(g, j * squareWidth, boardTop + i * squareHeight, shape);
			}
		}

		if (currentPiece.getPieceShape() != tetris.models.ShapeImpl.Tetrominoes.NoShape) {
			for (int i = 0; i < 4; i++) {
				int x = currentX + currentPiece.x(i);
				int y = currentY - currentPiece.y(i);
				tetrisBoard.drawSquare(g, x * squareWidth, boardTop + (boardHeight - y - 1) * squareHeight,
						currentPiece.getPieceShape());
			}
		}
	}

	public void newPiece() {
		if (isNextPieceNoShaped()) {
			currentPiece.setRandomShape();
		} else {
			currentPiece.setPieceShape(nextPiece.getPieceShape());
		}
		nextPiece.setRandomShape();
		System.out.println("current: " + currentPiece.getPieceShape().toString());
		System.out.println("next: " + nextPiece.getPieceShape().toString());

		tetrisFrame.updateNextImage(nextPiece.getPieceShape());

		currentX = boardWidth / 2 + 1;
		currentY = boardHeight - 1 + currentPiece.minY();
		if (!tryMove(currentPiece, currentX, currentY)) {
			currentPiece.setPieceShape(ShapeImpl.Tetrominoes.NoShape);
			getTimer().stop();
			isStarted = false;
			String name = TetrisFrameImpl.name;
			sound.playGameOver();
			sound.stopTheme();
			JOptionPane.showMessageDialog(null, name + " your score is " + numLinesRemoved + " deleted rows",
					"Game Over!", JOptionPane.INFORMATION_MESSAGE);
			try {
				@SuppressWarnings("unused")
				SaveRecord sv = new SaveRecord(numLinesRemoved, name);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * This method generates the next tetromino
	 * 
	 * @return next tetromino
	 */
	public static ShapeImpl getNextPiece() {
		return nextPiece;
	}

	public boolean tryMove(ShapeImpl newPiece, int newX, int newY) {
		for (int i = 0; i < 4; i++) {
			int x = newX + newPiece.x(i);
			int y = newY - newPiece.y(i);
			if (x < 0 || x >= boardWidth || y < 0 || y >= boardHeight)
				return false;
			if (shapeAt(x, y) != ShapeImpl.Tetrominoes.NoShape)
				return false;
		}
		currentPiece = newPiece;
		currentX = newX;
		currentY = newY;
		tetrisBoard.repaint();
		return true;
	}

	public void pieceDropped() {
		for (int i = 0; i < 4; i++) {
			int x = currentX + currentPiece.x(i);
			int y = currentY - currentPiece.y(i);
			board[(y * boardWidth) + x] = currentPiece.getPieceShape();
		}

		removeFullLines();

		if (!isFallingFinished)
			newPiece();
	}

	/**
	 * This method check if there are five rows completed. If it's true delete them
	 * and increases the level.
	 * 
	 * @return level of game
	 */
	public static int removeFullLines() {
		int numFullLines = 0;

		for (int i = boardHeight - 1; i >= 0; i--) {
			boolean lineIsFull = true;

			for (int j = 0; j < boardWidth; j++) {
				if (shapeAt(j, i) == ShapeImpl.Tetrominoes.NoShape) {
					lineIsFull = false;
					break;
				}
			}
			if (lineIsFull) {
				sound.playLine();
				++numFullLines;
				for (int k = i; k < boardHeight - 1; k++) {
					for (int j = 0; j < boardWidth; j++)
						board[(k * boardWidth) + j] = shapeAt(j, k + 1);

				}
			}
		}

		if (numFullLines > 0) {
			numLinesRemoved += numFullLines;
			tetrisBoard.setStatusText(String.valueOf(numLinesRemoved));
			isFallingFinished = true;
			currentPiece.setPieceShape(ShapeImpl.Tetrominoes.NoShape);
			tetrisBoard.repaint();

			if (numLinesRemoved % 5 == 0) {
				level++;
			}
		}
		TetrisFrameImpl.lbLevel.setText("Level: " + level);
		return level;
	}

	public void moveLeft() {
		tryMove(currentPiece, currentX - 1, currentY);
	}

	public void moveRight() {
		tryMove(currentPiece, currentX + 1, currentY);
	}

	public void rotateLeft() {
		tryMove(currentPiece.rotateLeft(), currentX, currentY);
	}

	public void rotateRight() {
		tryMove(currentPiece.rotateRight(), currentX, currentY);
	}

	public Timer getTimer() {
		return timer;
	}

	/**
	 * This method assign the timer to the game
	 * 
	 * @param timer
	 */
	public void setTimer(Timer timer) {
		BoardControllerImpl.timer = timer;
	}

}