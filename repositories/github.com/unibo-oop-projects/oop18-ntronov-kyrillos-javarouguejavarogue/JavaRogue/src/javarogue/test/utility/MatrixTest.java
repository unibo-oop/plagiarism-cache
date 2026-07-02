package javarogue.test.utility;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Test;

import javarogue.utility.Matrix;

public class MatrixTest {

	@Test
	public void testMatrixRowsAndCols() {
		// Test square matrix
		Matrix<Integer> matrixInt = new Matrix<>(4, 4);
		assertEquals(4, matrixInt.getRows());
		assertEquals(4, matrixInt.getCols());
		// Test generic matrix
		Matrix<String> matrixStr = new Matrix<>(2, 10);
		assertEquals(2, matrixStr.getRows());
		assertEquals(10, matrixStr.getCols());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMatrixNegativeSize() {
		Matrix<Integer> fail = new Matrix<>(-1, 2);
		fail.fill(0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testMatrixZeroSize() {
		Matrix<Boolean> fail = new Matrix<>(10, 0);
		fail.fill(true);
	}

	@Test
	public void testMatrixGettersAndSetters() {
		// Test matrix with incremental valued from k to 9
		Matrix<Integer> incremental = new Matrix<>(3, 3);
		int k = 1;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				incremental.set(i, j, k);
				k++;
			}
		}
		assertEquals(1, incremental.get(0, 0).intValue());
		assertEquals(9, incremental.get(2, 2).intValue());
		assertEquals(4, incremental.get(1, 0).intValue());
	}

	@Test
	public void testEmptyMatrixGet() {
		Matrix<Integer> empty = new Matrix<>(10, 10);
		assertEquals(null, empty.get(5, 5));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testMatrixGetOutOfBound() {
		Matrix<Integer> test = new Matrix<>(1, 1);
		test.get(1, 1);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testMatrixSetOutOfBounds() {
		Matrix<Integer> test = new Matrix<>(1, 1);
		test.set(1, 1, 0);
	}

	@Test
	public void testMatrixFill() {
		Matrix<Integer> fill = new Matrix<>(3, 3);
		fill.fill(0);
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				assertEquals(0, fill.get(i, j).intValue());
			}
		}
	}

	@Test
	public void testMatrixIterator() {
		// Test matrix with incremental valued from k to 9
		Matrix<Integer> incremental = new Matrix<>(3, 3);
		int k = 1;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				incremental.set(i, j, k);
				k++;
			}
		}
		int current = 1;
		Iterator<Integer> iterator = incremental.iterator();
		while(iterator.hasNext()) {
			assertEquals(current, iterator.next().intValue());
			current++;
		}
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testMatrixIteratorNoSuchElement() {
		Matrix<Integer> test = new Matrix<>(4, 4);
		Iterator<Integer> iterator = test.iterator();
		for(int i = 0; i <= test.getCols() * test.getRows(); i++) {
			iterator.next();
		}
	}

	@Test
	public void testMatrixDoubleFor() {
		Matrix<Integer> test = new Matrix<>(4, 4);
		test.fill(8);
		test.doubleFor((i, j) -> {
			assertEquals(8, test.get(i, j).intValue());
		});
	}
	
	@Test
	public void testMatrixDoubleForWithParameters() {
		Matrix<Integer> test = new Matrix<>(4, 4);
		test.fill(8);
		test.doubleFor(0, 2, 1, 0, 3, 2, (i, j) -> {
			assertEquals(8, test.get(i, j).intValue());
		});
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testMatrixDoubleForWithParametersNegativeStartValue() {
		Matrix<Integer> test = new Matrix<>(4, 4);
		test.fill(8);
		test.doubleFor(-1, 2, 1, 0, 3, 2, (i, j) -> {
			
		});
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testMatrixDoubleForWithParametersNegativeEndValue() {
		Matrix<Integer> test = new Matrix<>(4, 4);
		test.fill(8);
		test.doubleFor(1, 2, 1, 0, -3, 2, (i, j) -> {
			
		});
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testMatrixDoubleForWithParametersInvalidStartValue() {
		Matrix<Integer> test = new Matrix<>(4, 4);
		test.fill(8);
		test.doubleFor(10, 2, 1, 0, 3, 2, (i, j) -> {
			
		});
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testMatrixDoubleForWithParametersInvalidEndValue() {
		Matrix<Integer> test = new Matrix<>(4, 4);
		test.fill(8);
		test.doubleFor(0, 2, 1, 0, 10, 2, (i, j) -> {
			
		});
	}
	
	@Test
	public void testMatrixContains() {
		Matrix<Integer> incremental = new Matrix<>(3, 3);
		int k = 1;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				incremental.set(i, j, k);
				k++;
			}
		}
		assertTrue(incremental.contains(2));
		assertFalse(incremental.contains(10));
	}
	
	@Test
	public void testMatrixToString() {
		Matrix<Integer> incremental = new Matrix<>(3, 3);
		int k = 1;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				incremental.set(i, j, k);
				k++;
			}
		}
		String expected = "1 2 3\n"
				+		  "4 5 6\n"
				+ 		  "7 8 9";
		assertEquals(expected, incremental.toString());
	}
	
}
