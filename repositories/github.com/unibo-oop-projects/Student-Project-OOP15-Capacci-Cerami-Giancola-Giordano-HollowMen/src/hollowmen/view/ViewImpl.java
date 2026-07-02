package hollowmen.view;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.swing.ImageIcon;
import hollowmen.controller.ViewObserver;
import hollowmen.enumerators.InputMenu;
import hollowmen.model.facade.DrawableRoomEntity;
import hollowmen.model.facade.InformationDealer;
import hollowmen.view.ale.Game;
import hollowmen.view.ale.Lobby;
import hollowmen.view.juls.BasicMenuImpl;
import hollowmen.view.juls.ComplexMenuImpl;

/**
 * The ViewImpl class, that implements {@link View}, is used to draw the application on screen.
 * 
 * @author Alessia
 * 
 */

public class ViewImpl implements View {
	
	private Game game;
	private Lobby lobby;
	private Map<String,ImageIcon> storage;
	private ViewObserver observer;
	private BasicMenuImpl basic = new BasicMenuImpl();
	private ComplexMenuImpl complex = new ComplexMenuImpl();
	static final ClassLoader loader = ViewImpl.class.getClassLoader();
	
	public ViewImpl(int x, int y, ViewObserver observer){
		this.observer = observer;
		//try-catch to register the font into a GraphicsEnvironment
		try{
		    
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, loader.getResourceAsStream("CHILLER.ttf")));
		}catch(IOException e){
			e.printStackTrace();
		}catch(FontFormatException e){
			e.printStackTrace();
		}
		
		UtilitySingleton.getInstance().setObserver(observer);
		SingletonFrame.setWidth(x);
		SingletonFrame.setHeight(y);
		this.game=new Game(x,y,observer);
	}
	
	/**
	 * The {@code drawMenu} method draws the menu on screen when needed.
	 * @param type - distinguishes the two kinds of menu (Basic or Complex)
	 * @param name - represents the menu to draw
	 * @param collection - (Optional) represents the pool of InformationDealer (Items/Mobs/Skill Nodes/Achievements)
	 * 						to add to the menu
	 * 
	 * @author Juls
	 */
	public void drawMenu(InputMenu name, Optional<Collection<InformationDealer>> collection) {
		if (name.getType().equals("basic")) {
			basic.drawBasicMenu(name);
		} else {
			complex.drawComplexMenu(name, collection.orElse(Collections.emptyList()));
		}
	}

	/**
	 * The method {@code getFile} is used to take from the controller a list
	 * of all the files that the view needs.
	 */
	public void takeFile(Map<String, byte[]> fileMap){
		this.storage=new HashMap<String,ImageIcon>();
		for(Map.Entry<String,byte[]> elem: fileMap.entrySet()){
			this.storage.put(elem.getKey(),new ImageIcon(elem.getValue()));
		}
		this.game.setStorage(this.storage);
		UtilitySingleton.getInstance().setStorage(storage);
	}
	
	/**
	 * The method {@code drawGame} is used to draw all the components on screen.
	 * It's linked to {@link Game} class.
	 */ 
	public void drawGame(List<DrawableRoomEntity> componentList) {
		SingletonFrame frame=SingletonFrame.getInstance();
		frame.getContentPane().removeAll();
		this.game.draw(componentList);
		frame.add(this.game);
		frame.repaint();
	}
	
	/**
	 * The method {@code drawLobby} is used to draw all the lobby components on screen.
	 */
	public void drawLobby(){
	    SingletonFrame frame=SingletonFrame.getInstance();
	    frame.getContentPane().removeAll();
	    this.lobby=new Lobby(this.observer,this.storage);
	    frame.add(this.lobby);
	    frame.update(frame.getGraphics());
	}
	/**
	 * The {@code getStorage()} method allows to get the images' storage.
	 * 
	 * @return - storage
	 */
	public Map<String,ImageIcon> getStorage() {
		return storage;
	}
	
	/**
	 * The {@code getObserver} method return the observer. 
	 * 
	 * @return
	 */
	public ViewObserver getObserver(){
		return this.observer;
	}
}
