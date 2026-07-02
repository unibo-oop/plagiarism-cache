package controller;

import utils.ItemGenre;
import utils.Language;
import utils.TypeColor;

/**
 * Class containing methods that converts a string into a particular enum.
 *
 * @author
 *
 */
public final class CastManager {

	private CastManager() {
	}

	/**
	 * Method which casts the given string to an ItemGenre type.
	 *
	 * @param string
	 *            string to be casted
	 * @return the string casted to ItemGenre
	 */
	public static ItemGenre castToItemGenre(final String string) {

		for (final ItemGenre ig : ItemGenre.values()) {
			if (ig.toString().equals(string)) {
				return ig;
			}
		}
		return null;
	}

	/**
	 * Method which casts the given string to a Language type.
	 *
	 * @param string
	 *            string to be casted
	 * @return the string casted to Language
	 */
	public static Language castToLanguage(final String string) {

		for (final Language l : Language.values()) {
			if (l.toString().equals(string)) {
				return l;
			}
		}
		return null;
	}

	/**
	 * Method which casts the given string to a TypeColor type.
	 *
	 * @param string
	 *            string to be casted
	 * @return the string casted to TypeColor
	 */
	public static TypeColor castToTypeColor(final String string) {
		for (final TypeColor tc : TypeColor.values()) {
			if (tc.toString().equals(string)) {
				return tc;
			}
		}
		return null;
	}
}
