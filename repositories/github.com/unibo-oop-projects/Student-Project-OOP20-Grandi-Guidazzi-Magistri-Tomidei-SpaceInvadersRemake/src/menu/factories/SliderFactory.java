package menu.factories;

import java.awt.Color;

import javax.swing.JSlider;

import menu.Board;
import util.Constants;

/**
 *A simple slider factory to avoid repetitions.
 */
public class SliderFactory {

	private JSlider slider;
	
	/**
	 * A method that create the slider.
	 * @param board
	 * @return the slider with the listener.
	 */
	public JSlider create(Board board){
		
		this.slider = new JSlider(JSlider.HORIZONTAL, Constants.AudioConstants.minSliderValue, Constants.AudioConstants.maxSliderValue,
				(int) (board.getMenuController().getAudio().getVolume()*10));
		
		this.slider.setOpaque(false);
		
		this.slider.setMajorTickSpacing(Constants.AudioConstants.SPACING);
		this.slider.setForeground(Color.white);
		this.slider.setPaintLabels(true);
		this.slider.setPaintTicks(true);
		this.slider.addChangeListener(e->{
				board.getMenuController().getAudio().setVolume((float)slider.getValue() / Constants.AudioConstants.maxSliderValue);
		});
		
		return this.slider;
	}
	
}
