package util;

import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import controller.BaseModelView;
import model.DataContainer;
import model.ReservationBean;
import view.BaseForm;

/**
 * Class responsibility is to validate the input fields in the forms.
 *
 */
public class FormValidator {
	protected BaseForm form;

	public FormValidator(BaseForm form) {
		this.form = form;
	}

	public FormValidator() {
		this.form = null;
	}

	/**
	 * Returns true if an error has been detected.
	 * 
	 * @return
	 */
	public boolean validate() {
		String errorMsg = null;
		errorMsg = (String) DataContainer.getData(DataContainer.VALIDATION_ERROR_NAME);
		if (errorMsg != null)
			return true;
		errorMsg = (String) DataContainer.getData(DataContainer.VALIDATION_ERROR_EMAIL);
		if (errorMsg != null)
			return true;
		errorMsg = (String) DataContainer.getData(DataContainer.VALIDATION_ERROR_PHONE);
		if (errorMsg != null)
			return true;
		errorMsg = (String) DataContainer.getData(DataContainer.VALIDATION_ERROR_TABLE);
		if (errorMsg != null)
			return true;
		errorMsg = (String) DataContainer.getData(DataContainer.VALIDATION_ERROR_NO);
		if (errorMsg != null)
			return true;
		errorMsg = (String) DataContainer.getData(DataContainer.VALIDATION_ERROR_DATE);
		if (errorMsg != null)
			return true;
		errorMsg = (String) DataContainer.getData(DataContainer.VALIDATION_ERROR_MENU);
		if (errorMsg != null)
			return true;
		DataContainer.remove(DataContainer.VALIDATION_ERROR);
		return false;
	}

	/**
	 * Validates if the field value is numeric
	 * 
	 * @param s
	 * @return true if field value is numeric
	 */
	protected boolean isNumeric(String s) {
		try {
			Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	/**
	 * Validates the value of the name field
	 * 
	 * @param s
	 * @return true if validation error occurs
	 */
	public boolean validateName(String s) {
		if (s == null || s.isEmpty()) {
			DataContainer.setData(DataContainer.VALIDATION_ERROR, "Il campo nome non deve essere vuoto.");
			return true;
		} else {
			// Check length
			if (s.length() > 20) {
				DataContainer.setData(DataContainer.VALIDATION_ERROR, "Il campo nome deve avere più di 20 caratteri.");
				DataContainer.setData(DataContainer.VALIDATION_ERROR_NAME, "TRUE");
				return true;
			}
			Pattern p = Pattern.compile("[^a-zA-Z]");
			boolean hasSpecialChar = p.matcher(s).find();
			if (hasSpecialChar) {
				DataContainer.setData(DataContainer.VALIDATION_ERROR, "IL nome deve contenere solo le lettere.");
				DataContainer.setData(DataContainer.VALIDATION_ERROR_NAME, "TRUE");
				return true;
			}
		}
		return false;
	}

	/**
	 * Validates the value of the name field
	 * 
	 * @param s
	 * @return true if validation error occurs
	 */
	public boolean validateEmail(String s) {
		if (s == null || s.isEmpty()) {
			DataContainer.setData(DataContainer.VALIDATION_ERROR, "Il campo emali non deve essere vuoto.");
			DataContainer.setData(DataContainer.VALIDATION_ERROR_EMAIL, "TRUE");
			return true;
		} else {
			// Check email format
			String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
			java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
			java.util.regex.Matcher m = p.matcher(s);
			boolean ok = m.matches();
			if (!ok) {
				DataContainer.setData(DataContainer.VALIDATION_ERROR, "Email non valido.");
				DataContainer.setData(DataContainer.VALIDATION_ERROR_EMAIL, "TRUE");
				return true;
			}
		}
		return false;
	}

	/**
	 * Validates the value of the phone field.
	 * 
	 * @param s
	 * @return true if validation error occurs
	 */
	public boolean validatePhone(String s) {
		if (s == null || s.isEmpty()) {
			DataContainer.setData(DataContainer.VALIDATION_ERROR, "Il campo telefono non deve essere vuoto.");
			DataContainer.setData(DataContainer.VALIDATION_ERROR_PHONE, "TRUE");
			return true;
		} else {
			Pattern p = Pattern.compile("[^0-9]");
			boolean hasSpecialChar = p.matcher(s).find();
			if (hasSpecialChar) {
				DataContainer.setData(DataContainer.VALIDATION_ERROR, "Il telefono deve contenere solo i numeri.");
				DataContainer.setData(DataContainer.VALIDATION_ERROR_PHONE, "TRUE");
				return true;
			}
		}
		return false;
	}

	/**
	 * Validates the value of the table field.
	 * 
	 * @param s
	 * @return true if validation error occurs
	 */
	public boolean validateTable(String s) {
		if (s == null || s.isEmpty()) {
			DataContainer.setData(DataContainer.VALIDATION_ERROR, "Il campo tavolo non deve essere vuoto.");
			DataContainer.setData(DataContainer.VALIDATION_ERROR_TABLE, "TRUE");
			return true;
		} else {
			int tableValue = 0;
			try {
				tableValue = Integer.parseInt(s);

			} catch (NumberFormatException e) {
				DataContainer.setData(DataContainer.VALIDATION_ERROR, "Il campo tavolo deve contenere solo i numeri.");
				DataContainer.setData(DataContainer.VALIDATION_ERROR_TABLE, "TRUE");
				return true;
			}
			if (tableValue < 1 || tableValue > ApplicationConfiguration.NUMBER_OF_TABLES) {
				DataContainer.setData(DataContainer.VALIDATION_ERROR, "Il ristorante ha solo tavoli da 1 a 24.");
				DataContainer.setData(DataContainer.VALIDATION_ERROR_TABLE, "TRUE");
				return true;
			}
		}
		return false;
	}

	/**
	 * Validates the value of the number of persons field.
	 * 
	 * @param s
	 * @return true if validation error occurs
	 */
	public boolean validateNoOfPersons(String s) {
		if (s == null || s.isEmpty()) {
			DataContainer.setData(DataContainer.VALIDATION_ERROR,
					"Il campo del numero delle persone non deve essere vuoto.");
			DataContainer.setData(DataContainer.VALIDATION_ERROR_NO, "TRUE");
			return true;
		} else {
			int numberOfPersonsValue = 0;
			try {
				numberOfPersonsValue = Integer.parseInt(s);
			} catch (NumberFormatException e) {
				DataContainer.setData(DataContainer.VALIDATION_ERROR,
						"Il numero di persone deve contenere solo numeri.");
				DataContainer.setData(DataContainer.VALIDATION_ERROR_NO, "TRUE");
				return true;
			}
			if (numberOfPersonsValue < 1 || numberOfPersonsValue > 10) {
				DataContainer.setData(DataContainer.VALIDATION_ERROR, "Il numero massimo di persone è 10.");
				DataContainer.setData(DataContainer.VALIDATION_ERROR_NO, "TRUE");
				return true;
			}
		}
		return false;
	}

	/**
	 * Validates the value of the date field.
	 * 
	 * @param s
	 * @return true if validation error occurs
	 */
	public boolean validateDate(String s) {
		if (s == null || s.isEmpty()) {
			DataContainer.setData(DataContainer.VALIDATION_ERROR, "Il campo data non deve essere vuota.");
			DataContainer.setData(DataContainer.VALIDATION_ERROR_DATE, "TRUE");
			return true;
		} else {
			Date date = Utilities.parseDate(s);
			if (date == null) {
				DataContainer.setData(DataContainer.VALIDATION_ERROR,
						"Il campo data deve avere la seguente forma: 30.01.2017.");
				DataContainer.setData(DataContainer.VALIDATION_ERROR_DATE, "TRUE");
				return true;
			}
		}
		return false;
	}

	/**
	 * Validates the value of the date field.
	 * 
	 * @param s
	 * @return true if validation error occurs
	 */
	public boolean validateTime(String date, String time) {
		if (time == null || time.isEmpty()) {
			DataContainer.setData(DataContainer.VALIDATION_ERROR, "Il campo ora non deve essere vuoto.");
			DataContainer.setData(DataContainer.VALIDATION_ERROR_DATE, "TRUE");
			return true;
		} else if (date == null || date.isEmpty()) {
			if (!Utilities.validateTime(time)) {
				DataContainer.setData(DataContainer.VALIDATION_ERROR, "Tempo non valido controlla le regole");
				DataContainer.setData(DataContainer.VALIDATION_ERROR_TIME, "TRUE");
				return true;
			}
		} else {
			Date data = Utilities.parseDateTime(date, time);
			if (data == null) {
				DataContainer.setData(DataContainer.VALIDATION_ERROR,
						"Il campo ora deve avere la seguente forma: 20:30.");
				DataContainer.setData(DataContainer.VALIDATION_ERROR_DATE, "TRUE");
				return true;
			}
		}
		return false;
	}

	/**
	 * Validates reservation time. Allowed is a time slot of 2 hours per
	 * reservation
	 * 
	 * Example: Wished reservation date: 24.05.2017, 21:00. Already maintained
	 * reservations: 24.05.2017, 19:00 -> ok 24.05.2017, 22:30 -> not ok
	 */
	public boolean validateIsReservationAvailable(String date, String time, String table) {
		if (date != null && time != null && table != null) {
			int tavolo = Integer.parseInt(table);
			Date localDateTime = Utilities.parseDateTime(date, time);
			if (localDateTime == null)
				return false;
			BaseModelView modelView = new BaseModelView();
			List<ReservationBean> reservations = modelView.getReservations();
			for (int i = 0; i < reservations.size(); i++) {
				ReservationBean rb = reservations.get(i);
				long diff = Math.abs(localDateTime.getTime() - rb.getDate().getTime());
				if (diff < 7200000)
					if (tavolo == rb.getTable()) {
						DataContainer.setData(DataContainer.VALIDATION_ERROR, "La prenotazione non è possibile.");
						DataContainer.setData(DataContainer.VALIDATION_ERROR_DATE, "TRUE");
						return true;
					}
			}
		}
		return false;

	}

	/**
	 * Validates the value of the menu field.
	 * 
	 * @param s
	 * @return true if validation error occurs
	 */
	public boolean validateMenu(String s) {
		if (s == null || s.isEmpty()) {
			DataContainer.setData(DataContainer.VALIDATION_ERROR, "Il menu non puo essere vuoto.");
			DataContainer.setData(DataContainer.VALIDATION_ERROR_MENU, "TRUE");
			return true;
		} else {
			Pattern p = Pattern.compile("[^a-zA-Z0-9]");
			boolean hasSpecialChar = p.matcher(s).find();
			if (hasSpecialChar) {
				DataContainer.setData(DataContainer.VALIDATION_ERROR,
						"Il menu deve contenere solo caratteri alfanumerici.");
				DataContainer.setData(DataContainer.VALIDATION_ERROR_MENU, "TRUE");
				return true;
			}
		}
		return false;
	}
}
