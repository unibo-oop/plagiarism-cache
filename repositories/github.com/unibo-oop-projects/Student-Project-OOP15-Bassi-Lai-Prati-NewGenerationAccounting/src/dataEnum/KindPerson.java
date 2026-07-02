package dataEnum;

/**
 * enumerazione che identifica la persona in base al ruolo nei confronti
 * dell'azienda
 * 
 * @author niky
 *
 */
public enum KindPerson implements IDataEnum {
	CLIENTE, FORNITORE, NESSUNO;

	@Override
	public Enum<?>[] getEnumValues() {
		return values();
	}

}
