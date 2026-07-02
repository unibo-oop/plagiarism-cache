package menu.factories;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import util.Constants;
/**
 * A simple title factory to avoid repetitions.
 */
public class TitleFactory {

	/**
	 * Create a new label with the input infos and the traits of a title.
	 * 
	 * @param stringTitle
	 * @param titleSize
	 * @param titleColor
	 * @return A Label with the standard trait of a title.
	 */
	public JLabel createTitle(String stringTitle, int titleSize, Color titleColor) {
		JLabel title = new JLabel(stringTitle);
		title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setMaximumSize(Constants.ObjectDimension.maxLabelDimension);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("sans", Font.BOLD, titleSize));
        title.setForeground(titleColor);
		title.setVisible(true);
		return title;
	}
}
