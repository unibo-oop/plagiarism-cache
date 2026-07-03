package jsons;

/**
 * This interface must be implemented if you want to use Json. It provides a method to get a Json as information contained in the JsonModel and
 * once to convert the Json object in its specific class.
 */
public interface JsonModel {

	/**
	 * This is a static method to be used to get a JsonModel of an Object o.
	 * @param o The Object you want to convert in a Json Object.
	 * @return a {@link JsonModel} representing the object. Can be empty.
	 */
	public String getJson();
	
	/**
	 * This method convert the JsonModel String value into an Object of a class T.
	 * @param t the class of the object represent by the JsonModel
	 * @return the object deserialized of T class or null if the deserialization fails.
	 */
	public <T> T getDeserializedValue(Class<T> t);
}
