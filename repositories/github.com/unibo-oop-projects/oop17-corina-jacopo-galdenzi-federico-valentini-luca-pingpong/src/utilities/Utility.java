package utilities;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Utility {
	private Utility() {
		
	}
	public static final int MSEC_CONST = 1000;
	public static Font fontLoader(String fontPath) {
		Font font = null;
		try {
		     	font = Font.createFont(Font.TRUETYPE_FONT, new File(fontPath));
		     	font = font.deriveFont(Font.PLAIN,20);
		     	
		} catch (IOException|FontFormatException e) {
			//JOptionPane.showMessageDialog(null,e.getMessage());
		}
		return font;
	}
	public static Dimension getFrameDimension(Component c) {
		JFrame topFrame = (JFrame)SwingUtilities.getWindowAncestor(c);
		if(topFrame != null) {
			return topFrame.getSize();
		}
		else {
			return null;
		}
	}
}
