package view;

import java.util.List;

import javax.swing.JButton;


public interface AllList {

	/**
	 * @return the yellow base list
	 */
	public List<Pair<JButton, Boolean>> getListBasePlayerYellow();
	
	/* set yellow base list
	 *  
	 * @param listBasePlayerYellow
	 */
	public void setListBasePlayerYellow(List<Pair<JButton, Boolean>> listBasePlayerYellow);

	/**
	 * @return the green base list
	 */
	public List<Pair<JButton, Boolean>> getListBasePlayerGreen();

	/* set green base list
	 *  
	 * @param listBasePlayerYellow
	 */
	public void setListBasePlayerGreen(List<Pair<JButton, Boolean>> listBasePlayerGreen);

	/**
	 * @return the green base list
	 */
	public List<Pair<JButton,Integer>> getRouteButton();

	/* set route list
	 *  
	 * @param listBasePlayerYellow
	 */
	public void setRouteButton(List<Pair<JButton,Integer>> routeButton);
	
	/**
	 * @return the blue base list
	 */
	public List<Pair<JButton, Boolean>> getListBasePlayerBlue();

	/* set blue base list
	 *  
	 * @param listBasePlayerYellow
	 */
	public void setListBasePlayerBlue(List<Pair<JButton, Boolean>> listBasePlayerBlue);

	/**
	 * @return the orange base list
	 */
	public List<Pair<JButton, Boolean>> getListBasePlayerOrange();

	/* set orange base list
	 *  
	 * @param listBasePlayerYellow
	 */
	public void setListBasePlayerOrange(List<Pair<JButton, Boolean>> listBasePlayerOrange);

	/**
	 * @return the purple base list
	 */
	public List<Pair<JButton, Boolean>> getListBasePlayerPurple();

	/* set purple base list
	 *  
	 * @param listBasePlayerYellow
	 */
	public void setListBasePlayerPurple(List<Pair<JButton, Boolean>> listBasePlayerPurple);
	
	/**
	 * @return the red base list
	 */
	public List<Pair<JButton, Boolean>> getListBasePlayerRed();

	/* set red base list
	 *  
	 * @param listBasePlayerYellow
	 */
	public void setListBasePlayerRed(List<Pair<JButton, Boolean>> listBasePlayerRed);

	/**
	 * @return the blue arrive list
	 */
	public List<Pair<JButton, Boolean>> getListArrivedPlayerBlue();

	/* set blue arrive list
	 *  
	 * @param listBasePlayerYellow
	 */
	public void setListArrivedPlayerBlue(List<Pair<JButton, Boolean>> listArrivedPlayerBlue);

	/**
	 * @return the orange arrive list
	 */
	public List<Pair<JButton, Boolean>> getListArrivedPlayerOrange();

	/* set orange arrive list
	 *  
	 * @param listBasePlayerYellow
	 */
	public void setListArrivedPlayerOrange(List<Pair<JButton, Boolean>> listArrivedPlayerOrange);

	/**
	 * @return the purple arrive list
	 */
	public List<Pair<JButton, Boolean>> getListArrivedPlayerPurple();

	/* set purple arrive list
	 *  
	 * @param listBasePlayerYellow
	 */
	public void setListArrivedPlayerPurple(List<Pair<JButton, Boolean>> listArrivedPlayerPurple);

	/**
	 * @return the red arrive list
	 */
	public List<Pair<JButton, Boolean>> getListArrivedPlayerRed();

	/* set red arrive list
	 *  
	 * @param listBasePlayerYellow
	 */
	public void setListArrivedPlayerRed(List<Pair<JButton, Boolean>> listArrivedPlayerRed);	
	
	/**
	 * @return the yellow arrive list
	 */
	public List<Pair<JButton, Boolean>> getListArrivedPlayerYellow();

	/* set yellow arrive list
	 *  
	 * @param listBasePlayerYellow
	 */
	public void setListArrivedPlayerYellow(List<Pair<JButton, Boolean>> listArrivedPlayerYellow);
	
	/**
	 * @return the green arrive list
	 */
	public List<Pair<JButton, Boolean>> getListArrivedPlayerGreen();

	/* set green arrive list
	 *  
	 * @param listBasePlayerYellow
	 */
	public void setListArrivedPlayerGreen(List<Pair<JButton, Boolean>> listArrivedPlayerGreen);
}
