package utility;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import model.entities.Ball;
import model.entities.IEntity;

/**
 * Classe di utilità per la gestione delle collisioni.
 * @author Gnoli Mirco
 */
public final class CollisionUtility {

    //creato per silenziare il checkstyle
    private CollisionUtility() { }

    /**
     * Metodo per controllare se due {@link IEntity} collidono.
     * Inscrive ogni entità in un rettangolo, poi sfrutta la classe {@link Rectangle} per controllare se collidono.
     *
     * @param b - prima entità
     * @param e - seconda entità
     *
     * @return true se i rettangoli creati collidono, falso altrimenti.
     */
    public static boolean intersects(final IEntity b, final IEntity e) {
        Shape s1, s2;
        if (b instanceof Ball) {
            s1 = new Circle(b.getPosition().getKey(), b.getPosition().getValue(), ((Ball) b).getRadius());
            s2 = new Rectangle(e.getMinX(), e.getMinY(), e.getMaxX() - e.getMinX(), e.getMaxY() - e.getMinY());
        } else if (e instanceof Ball) { // in teoria qui non entra mai, dato che passo sempre prima la Ball.
            s1 = new Rectangle(b.getMinX(), b.getMinY(), b.getMaxX() - b.getMinX(), b.getMaxY() - b.getMinY());
            s2 = new Circle(e.getPosition().getKey(), e.getPosition().getValue(), ((Ball) e).getRadius());
        } else {
            s1 = new Rectangle(b.getMinX(), b.getMinY(), b.getMaxX() - b.getMinX(), b.getMaxY() - b.getMinY());
            s2 = new Rectangle(e.getMinX(), e.getMinY(), e.getMaxX() - e.getMinX(), e.getMaxY() - e.getMinY());
        }

        return s1.intersects(s2.getBoundsInLocal());
    }

    /**
     * Verifica se la prima {@link IEntity} collide con la parte orizzontale-inferiore della seconda entità.
     * @param b - prima entità
     * @param e - seconda entità
     * @return true se collidono nella maniera sopra descritta, false altrimenti
     */
    public static boolean firstCollidedWithLowerHorizontalBound(final IEntity b, final IEntity e) {
        return b.getMaxX() >= e.getMinX() && b.getMinX() <= e.getMaxX() && b.getMaxY() >= e.getMaxY() ? true : false;
    }

    /**
     * Verifica se la prima {@link IEntity} collide con la parte orizzontale-superiore della seconda entità.
     * @param b - prima entità
     * @param e - seconda entità
     * @return true se collidono nella maniera sopra descritta, false altrimenti
     */
    public static boolean firstCollidedWithTopHorizontalBound(final IEntity b, final IEntity e) {
        return b.getMaxX() >= e.getMinX() && b.getMinX() <= e.getMaxX() && b.getMinY() <= e.getMinY() ? true : false;
    }

    /**
     * Verifica se la prima {@link IEntity} collide con la parte verticale-più a destra della seconda entità.
     * @param b - prima entità
     * @param e - seconda entità
     * @return true se collidono nella maniera sopra descritta, false altrimenti
     */
    public static boolean firstCollidedWithRightestVerticalBound(final IEntity b, final IEntity e) {
        return b.getMaxY() >= e.getMinY() && b.getMinY() <= e.getMaxY() && b.getMaxX() >= e.getMaxX() ? true : false;
    }

    /**
     * Verifica se la prima {@link IEntity} collide con la parte verticale-più a sinistra della seconda entità.
     * @param b - prima entità
     * @param e - seconda entità
     * @return true se collidono nella maniera sopra descritta, false altrimenti
     */
    public static boolean firstCollidedWithLeftestVerticalBound(final IEntity b, final IEntity e) {
        return b.getMaxY() >= e.getMinY() && b.getMinY() <= e.getMaxY() && b.getMinX() <= e.getMinX() ? true : false;
    }

}
