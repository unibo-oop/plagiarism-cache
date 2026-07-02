package model.objects;

import java.util.Optional;

import model.player.Player;

/**
 * Describes an object on the game map. create class ownable game object, since
 * terrain doesn't have owner.
 */
public interface GameObject {

    /**
     * 
     * @return the owner of the object.
     */
    Optional<Player> getOwner();

    /**
     * sets the owner of the object.
     * 
     * @param player indicate if the object has an owner or not.
     */
    void set(Optional<Player> player);

    /**
     * sets the owner of the object as player.
     * 
     * @param player the new owner.
     */
    void setOwner(Player player);

    /**
     * removes the owner of the object.
     */
    void removeOwner();

    /**
     * AMERI.
     * 
     * @return ritorna la descrizione dell'oggeto utile per il sideButtomMenu.
     */
    String getDescription();

    /**
     * 
     * @return il nome del possessore. se non esiste ritorna che il possessore è
     *         neutrale.
     */
    String getOwnerName();
}
