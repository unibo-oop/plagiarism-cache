package tests;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import javafx.geometry.Point2D;

public class LevelGeneratorTest {

	/**
	 * test lettura da file, controllo che gli inidici passati da file di testo
	 * abbiano la giusta posizione
	 *
	 */
	@Test
	public final void testReadLevel() {

		final int width = 5;
		final int height = 5;
		final Map<Point2D, Integer> comparer = new HashMap<>();
		final Map<Point2D, Integer> comparator = new HashMap<>();

		comparator.put(new Point2D(0, 0), 2);
		comparator.put(new Point2D(0, 1), 2);
		comparator.put(new Point2D(0, 2), 2);
		comparator.put(new Point2D(0, 3), 2);
		comparator.put(new Point2D(0, 4), 2);
		comparator.put(new Point2D(1, 0), 2);
		comparator.put(new Point2D(1, 1), 1);
		comparator.put(new Point2D(1, 2), 1);
		comparator.put(new Point2D(1, 3), 1);
		comparator.put(new Point2D(1, 4), 2);
		comparator.put(new Point2D(2, 0), 2);
		comparator.put(new Point2D(2, 1), 3);
		comparator.put(new Point2D(2, 2), 1);
		comparator.put(new Point2D(2, 3), 1);
		comparator.put(new Point2D(2, 4), 2);
		comparator.put(new Point2D(3, 0), 2);
		comparator.put(new Point2D(3, 1), 1);
		comparator.put(new Point2D(3, 2), 5);
		comparator.put(new Point2D(3, 3), 1);
		comparator.put(new Point2D(3, 4), 2);
		comparator.put(new Point2D(4, 0), 2);
		comparator.put(new Point2D(4, 1), 2);
		comparator.put(new Point2D(4, 2), 2);
		comparator.put(new Point2D(4, 3), 2);
		comparator.put(new Point2D(4, 4), 2);

		BufferedReader br = null;
		try {
			final InputStream is = getClass().getResourceAsStream("/test.txt");
			br = new BufferedReader(new InputStreamReader(is));
			int x = 0;
			int y = 0;
			while ((x < width) && (y < height)) {
				final String line = br.readLine();
				while (x < width) {
					final String numbers[] = line.split(" ");
					final int num = Integer.parseInt(numbers[x]);
					comparer.put(new Point2D(y, x), num);
					x++;
				}
				if (x == width) {
					x = 0;
					y++;
				}
			}
			br.close();
		} catch (final Exception e) {
		}

		comparator.forEach((k, v) -> {
			Assert.assertEquals(comparer.get(k), v);
		});

	}
}
