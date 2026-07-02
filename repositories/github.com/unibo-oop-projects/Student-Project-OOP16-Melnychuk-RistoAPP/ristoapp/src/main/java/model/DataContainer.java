package model;

import java.util.HashMap;

/**
 * This class serves as a struct for holding the application session data.
 *
 */
public class DataContainer {
	// KEYS
	public static final String NUMBER_OF_RESERVATIONS = "NUMBER_OF_RESERVATIONS";
	public static final String RESERVATION_ID = "RESERVATION_ID";
	public static final String SELECTED_RESERVATION_ID = "SELECTED_RESERVATION_ID";
	// ERROR KEYS
	public static final String ERROR = "ERROR";
	public static final String VALIDATION_ERROR = "VALIDATION_ERROR";
	public static final String VALIDATION_ERROR_NAME = "VALIDATION_ERROR_NAME";
	public static final String VALIDATION_ERROR_EMAIL = "VALIDATION_ERROR_EMAIL";
	public static final String VALIDATION_ERROR_PHONE = "VALIDATION_ERROR_PHONE";
	public static final String VALIDATION_ERROR_NO = "VALIDATION_ERROR_NO";
	public static final String VALIDATION_ERROR_TABLE = "VALIDATION_ERROR_TABLE";
	public static final String VALIDATION_ERROR_DATE = "VALIDATION_ERROR_DATE";
	public static final String VALIDATION_ERROR_TIME = "VALIDATION_ERROR_TIME";
	public static final String VALIDATION_ERROR_MENU = "VALIDATION_ERROR_MENU";
	// ERROR ROOT CAUSE
	public static final String ERROR_MODIFY_NO_SELECTED_ID = "ERROR_MODIFY_NO_SELECTED_ID";
	public static final String ERROR_CANCEL_NO_SELECTED_ID = "ERROR_CANCEL_NO_SELECTED_ID";
	public static final String ERROR_VIEW_NO_SELECTED_ID = "ERROR_VIEW_NO_SELECTED_ID";

	protected static HashMap<String, Object> data;

	/**
	 * Initializes the available reservation ID with 0.
	 */
	static {
		data = new HashMap<String, Object>();
		DataContainer.setData(RESERVATION_ID, 0);
	}

	public static Object getData(String key) {
		return data.get(key);
	}

	public static void setData(String s, Object o) {
		data.put(s, o);
	}

	public static void remove(String key) {
		data.remove(key);
	}

}
