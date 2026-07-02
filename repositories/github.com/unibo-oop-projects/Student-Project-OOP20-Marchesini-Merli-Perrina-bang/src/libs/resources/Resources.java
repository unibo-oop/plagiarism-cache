package libs.resources;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

/**
 * A utility class to read text files and image files.
 * 
 * @author Mattia Marchesini
 *
 */
public class Resources {

	/**
	 * Reads a text file.
	 * 
	 * @param path the path of the file to read
	 * @return the text of the file
	 */
	public static String readFile(String path) {
		InputStreamReader s = new InputStreamReader(ClassLoader.getSystemResourceAsStream(path));
		BufferedReader reader = new BufferedReader(s);

		StringBuffer sb = new StringBuffer();
		String str;
		try {
			while ((str = reader.readLine()) != null) {
				sb.append(str + "\n");
			}
			return sb.toString();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return "";
	}

	/**
	 * Loads an image file.
	 * 
	 * @param path the path of the file to load
	 * @return the image
	 */
	public static Image getSwingImage(String path) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(ClassLoader.getSystemResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return img;
	}
}
