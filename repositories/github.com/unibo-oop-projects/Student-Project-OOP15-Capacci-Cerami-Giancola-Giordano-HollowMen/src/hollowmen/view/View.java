package hollowmen.view;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import hollowmen.enumerators.InputMenu;
import hollowmen.model.facade.DrawableRoomEntity;
import hollowmen.model.facade.InformationDealer;

/**
 * The View interface allows to draw application on screen.
 * 
 * @author Alessia
 *
 */
public interface View {
	
	/**
	 * The method {@code getFile} is used to take from the controller a list
	 * of all the files that the view needs.
	 *
	 * @param fileList
	 */
	public void takeFile(Map<String, byte[]> fileMap);
	
	/**
	 * The {@code drawMenu} method draws the menu on screen when needed.
	 * @param type - distinguishes the two kinds of menu (Basic or Complex)
	 * @param name - represents the menu to draw
	 * @param collection - (Optional) represents the pool of Items/Mobs/Skill Nodes/Achievements
	 * 						to add to the menu
	 * 
	 * @author Juls
	 */
	public void drawMenu(InputMenu name, Optional<Collection<InformationDealer>> collection);
	
	/**
	 * The method {@code drawGame} is used to take from the controller a list
	 * of all the components that the view needs to draw.
	 * 
	 * @param componentList 
	 */
	public void drawGame(List<DrawableRoomEntity> componentList);
	
	
	/**
	 * The method {@code drawLobby} is used to draw on screen the component I need
	 */
	public void drawLobby();
}
