package view;

import java.util.List;

import javax.swing.JPanel;

import controller.view.ViewObserver;
import model.Entity;

/**
 * The Interface View.
 */
public interface View {

/**
 * Start.
 */
void start();


/**
 * Draw.
 *
 * @param list the list
 * @param score the score
 * @param level the level
 */
void draw(List<Entity>list, int score,int level);

/**
 * Gets the observer.
 *
 * @return the observer
 */
ViewObserver getObserver();

/**
 * Switch window.
 *
 * @param windows the windows
 * @param Title the title
 */
void switchWindow(JPanel windows, String Title);

/**
 * Reset to menu.
 */
void resetToMenu();

/**
 * Attach.
 *
 * @param observer the observer
 */
void attach(ViewObserver observer);
}
