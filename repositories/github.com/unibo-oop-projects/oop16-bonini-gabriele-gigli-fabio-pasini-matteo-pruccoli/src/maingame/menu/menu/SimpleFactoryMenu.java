package maingame.menu.menu;

/**
 * Interfaccia che specifica i diversi menu.
 */
public interface SimpleFactoryMenu {

    /**
     * Crea il menu da visualizzare all'avvio dell'applicazione.
     * 
     * @return Menu
     */
    MenuImpl startMenu();

    /**
     * Crea il menu da visualizzare durante l'esecuzione dell'applicazione.
     * 
     * @return Menu
     */
    MenuImpl settingsMenu();

}
