package it.unibo.oop.bounce.manager;

public enum State {
	
	/**
	 * Identifica lo stato della pallina quando è ferma.
	 */
	STANDING,
	/**
	 * Identifica lo stato della pallina quando è in caduta.
	 */
	FALLING,
	/**
	 * Identifica lo stato della pallina quando è in movimento.
	 */
	ROLLING,
	/**
	 * Identifica lo stato della pallina quando salta.
	 */
	JUMPING,
	/**
	 * Identifica lo stato della pallina quando è muore.
	 */
	DYING,
	/**
	 * Identifica lo stato della pallina quando è grande.
	 */
	BIG,
	/**
	 * Identifica lo stato della pallina quando è piccola.
	 */
	SMALL,
	/**
	 * Identifica lo stato della pallina quando è in fase di crescita.
	 */
	GROWING;
	
}
