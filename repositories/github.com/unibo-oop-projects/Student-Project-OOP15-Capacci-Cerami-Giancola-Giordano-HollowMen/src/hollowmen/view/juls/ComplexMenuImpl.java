package hollowmen.view.juls;

import java.util.Collection;

import hollowmen.enumerators.InputMenu;
import hollowmen.model.facade.InformationDealer;
import hollowmen.view.SingletonFrame;
import hollowmen.view.juls.dialog.Achievements;
import hollowmen.view.juls.dialog.Bestiary;
import hollowmen.view.juls.dialog.Inventory;
import hollowmen.view.juls.dialog.Shop;

/**
 * The {@code ComplexMenuImpl} class represents the concrete
 * implementation of {@link ComplexMenu}. It allows to draw "complex" menus on screen.
 * @author Juls
 */
public class ComplexMenuImpl implements ComplexMenu{

	public ComplexMenuImpl() {}
	
	@Override
	public void drawComplexMenu(InputMenu name, Collection<InformationDealer> collection) {
		switch (name) {
		
		case INVENTORY:
			new Inventory(SingletonFrame.getInstance(), collection);
			break;
			
		case SKILL_TREE:
			// for now, this does nothing
			break;
			
		case SHOP:
			new Shop(SingletonFrame.getInstance(), collection);
			break;
			
		case POKEDEX:
			new Bestiary(SingletonFrame.getInstance(), collection);
			break;
			
		case ACHIEVEMENTS:
			new Achievements(SingletonFrame.getInstance(), collection);
			break;
			
		default:
			break;
		
		}	
	}
}
