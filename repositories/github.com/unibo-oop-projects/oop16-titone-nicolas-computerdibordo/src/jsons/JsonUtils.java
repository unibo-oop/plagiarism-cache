package jsons;

import com.google.gson.Gson;

/**
 * This class is took by stackoverflow.com.
 * This class provides static methods to convert objects in valid JSON Strings and vice versa.
 * @see Gson
 */
public final class JsonUtils { //from StackOverflow

	/**
	 * The instance of Gson used to compute JSON Object
	 */
	private static final Gson gson = new Gson();

	private JsonUtils() {
	}
	
	/**
	 * This method is used to validate a {@link String} as a valid JSON Object.
	 * Valid JSON String means: the String do not represent a JSON Array (ex. [ ] )
	 * It accept a JSON Object like { } .
	 * @param jsonString the String you want to validate.
	 * @return a Boolean represent the result of the validation.
	 */
	public static boolean isJsonValid(String jsonString) {
		try {
			gson.fromJson(jsonString, Object.class);
			return true;
		} catch (com.google.gson.JsonSyntaxException ex) {
			return false;
		}
	}
	
	/**
	 * This method serialized the Object o into a valid Json Object.
	 * @param o The object you want to convert
	 * @return the String representing the Object
	 */
	public static String toJson(Object o) {
		
		return gson.toJson(o);
	}
	
	/**
	 * This method convert a String s into an instance of the specified class c. If the String s is not a valid JSON Object it will return null.
	 * @param s The String to convert.
	 * @param c The class of the object you want to convert.
	 * @return An instance of the converted json Object or null if the String was not a valid Json Object.
	 */
	public static <T> T fromJson(String s, Class<T> c) {
		try {
			return gson.fromJson(s, c);
		} catch(Exception e) {
			return null;
		}
		
	}
}
