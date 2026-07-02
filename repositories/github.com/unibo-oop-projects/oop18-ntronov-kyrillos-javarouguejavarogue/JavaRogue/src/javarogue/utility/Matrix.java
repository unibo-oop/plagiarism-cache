package javarogue.utility;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * 
 * A Collection type data container that represents a 2D matrix. Rows and
 * columns are indexed form 0 to size - 1.
 *
 * @param <X> the type of the elements of the matrix.
 */
public class Matrix<X> implements Iterable<X> {

	private List<List<X>> matrix;
	private int rows;
	private int cols;

	/**
	 * Makes a rows * cols matrix and initializes elements to null
	 * 
	 * @param rows rows
	 * @param cols columns
	 */
	public Matrix(int rows, int cols) {
		if (rows <= 0 || cols <= 0) {
			throw new IllegalArgumentException("Invalid matrix size!");
		}
		this.matrix = new ArrayList<List<X>>();
		this.rows = rows;
		this.cols = cols;
		this.initialise(rows, cols);
	}

	/**
	 * Returns the element at the row i and column j. (Matrix[i][j])
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	public X get(int i, int j) {
		try {
			return this.matrix.get(i).get(j);
		} catch (IndexOutOfBoundsException e) {
			throw new IndexOutOfBoundsException(this.getIndexErrorMessage(i, j));
		}
	}

	/**
	 * Replaces the element at the row i and column j. (Matrix[i][j])
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	public void set(int i, int j, X element) {
		try {
			this.matrix.get(i).set(j, element);
		} catch (IndexOutOfBoundsException e) {
			throw new IndexOutOfBoundsException(this.getIndexErrorMessage(i, j));
		}
	}

	/**
	 * Fills the whole matrix with the filler.
	 * 
	 * @param filler The element to fill the matrix with.
	 */
	public void fill(X filler) {
		this.doubleFor((i, j) -> {
			this.set(i, j, filler);
		});
	}

	/**
	 * Returns an iterator that cycles each elements row per row.
	 */
	public Iterator<X> iterator() {
		return new Iterator<X>() {

			int currentRow = 0;
			Iterator<X> currentIt = matrix.get(0).iterator();

			@Override
			public boolean hasNext() {
				if (currentRow < rows - 1) {
					return true;
				} else {
					return currentIt.hasNext();
				}
			}

			@Override
			public X next() {
				if (currentIt.hasNext()) {
					return currentIt.next();
				} else if (currentRow < rows - 1) {
					currentRow++;
					currentIt = matrix.get(currentRow).iterator();
					return currentIt.next();
				} else {
					throw new NoSuchElementException();
				}
			}

		};
	}

	/**
	 * Less verbose way to call a double for cycle over matrix.
	 * 
	 * @param command a MatrixCommand command that provides current iteration's i
	 *                (rows index) and j (columns index) counters.
	 */
	public void doubleFor(MatrixCommand command) {
		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.cols; j++) {
				command.execute(i, j);
			}
		}
	}

	/**
	 * Less verbose way to call a double for cycle over matrix.
	 * 
	 * @param rowsStart Rows starting offset. (i = rowsStart)
	 * @param rowsEnd   Rows end condition. (i < rowsEnd)
	 * @param rowsStep  Rows counter step. (i = i + rowsStep)
	 * @param colsStart Columns starting offset. (j = rowsStart)
	 * @param colsEnd   Columns end condition. (j < rowsEnd)
	 * @param colsStep  Rows counter step. (j = j + colsStep)
	 * @param command
	 */
	public void doubleFor(int rowsStart, int rowsEnd, int rowsStep, int colsStart, int colsEnd, int colsStep,
			MatrixCommand command) {
		if (rowsStart < 0) {
			throw new IllegalArgumentException("Negative value for rowsStart");
		} else if (colsStart < 0) {
			throw new IllegalArgumentException("Negative value for colsStart");
		} else if (rowsStart >= this.rows) {
			throw new IllegalArgumentException("Invalid value for rowsStart");
		} else if (colsStart >= this.cols) {
			throw new IllegalArgumentException("Invalid value for colsStart");
		} else if (rowsEnd < 0) {
			throw new IllegalArgumentException("Negative value for rowsEnd");
		} else if (colsEnd < 0) {
			throw new IllegalArgumentException("Negative value for colsEnd");
		} else if (rowsEnd >= this.rows) {
			throw new IllegalArgumentException("Invalid value for rowsEnd");
		} else if (colsEnd >= this.cols) {
			throw new IllegalArgumentException("Invalid cols for colsEnd");
		}
		for (int i = rowsStart; i < rowsEnd; i = i + rowsStep) {
			for (int j = colsStart; j < colsEnd; j = j + colsStep) {
				command.execute(i, j);
			}
		}
	}

	/**
	 * 
	 * @return The number of rows of the matrix.
	 */
	public int getRows() {
		return this.rows;
	}

	/**
	 * 
	 * @return The number of cols of the matrix.
	 */
	public int getCols() {
		return this.cols;
	}

	/**
	 * Checks if an element is contained in the matrix.
	 * 
	 * @param element
	 * @return true if the element is contained, false otherwise.
	 */
	public boolean contains(X element) {
		boolean flag = false;
		for (X e : this) {
			if (e.equals(element)) {
				flag = true;
			}
		}
		return flag;
	}

	@Override
	public String toString() {
		String out = "";
		for (int i = 0; i < rows; i++) {
			if (i > 0) {
				out = out.concat("\n");
			}
			for (int j = 0; j < cols; j++) {
				out = out.concat(this.get(i, j).toString());
				if (j != cols - 1) {
					out = out.concat(" ");
				}
			}
		}
		return out;
	}

	// Initialize the matrix by allocating each row's lists and fills them with
	// null.
	private void initialise(int rows, int cols) {
		for (int i = 0; i < rows; i++) {
			this.matrix.add(i, new ArrayList<>(cols));
			for (int j = 0; j < cols; j++) {
				this.matrix.get(i).add(null);
			}
		}
	}

	private String getIndexErrorMessage(int i, int j) {
		return "Matrix index is out of bounds:  Index: (" + i + "," + j + ")  Rows: " + this.rows + "  Cols: "
				+ this.cols;
	}
}
