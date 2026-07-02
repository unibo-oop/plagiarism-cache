package it.tbt.model.entities.characters;

import com.google.gson.annotations.SerializedName;

/**
 * Status of a character.
 */
public enum Status {
    /**
     * Poisoned, loose health every turn.
     */
    @SerializedName("POISONED")
    POISONED,
    /**
     * Invincible, cannot take damage.
     */
    @SerializedName("INVINCIBLE")
    INVINCIBLE,
}
