package jsons;

/**
 * This class provides methods to get a valid Json object implementing the pattern adapter.
 * The method {@link #of(Object)} can be use to get the {@link JsonModel} of the object passed.
 *@see JsonModel
 *@see JsonUtils
 */
public class JsonModelImpl implements JsonModel{ //pattern adapter
	
	/**
	 * The String representing the Json Object
	 */
	private String model;

	protected JsonModelImpl() {
		
	}
	
	protected JsonModelImpl(String json) {
		this.model = json;
	}
	
	/**
	 * {@link JsonModel#getJson()}
	 */
	public String getJson() {
		return this.model;
	}
	
	public static JsonModel of(Object o) { //pattern adapter
		
		String json = JsonUtils.toJson(o);
				
		if(JsonUtils.isJsonValid(json)) {
			return new JsonModelImpl(json);
		}
		
		return new JsonModelImpl.Empty();
	}
	
	/**
	 * {@link JsonModel#getDeserializedValue(Class)}.
	 * @see JsonUtils
	 */
	public <T> T getDeserializedValue(Class<T> c) {
		
		try {
			T value = JsonUtils.fromJson(this.getJson(), c);
			return value;

		} catch(Exception e) {
			return null;
		}
	}
	
	protected static class Empty extends JsonModelImpl { //adaptee
		
		public Empty() {
			super();
		}
		
		public String getJson() {
			return null;
		}
		
		public <T> T toClass(Class<T> c) {	
			return null;
		}	
	}
}
