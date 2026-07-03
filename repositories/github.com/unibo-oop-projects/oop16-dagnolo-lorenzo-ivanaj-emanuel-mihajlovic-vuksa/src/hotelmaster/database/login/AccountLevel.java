package hotelmaster.database.login;

import java.util.HashMap;
import java.util.Map;

/**
 * The possible levels of privilege for accounts.
 */
public enum AccountLevel {
    /**
     * Administrator account.
     */
    ADMIN(0),
    /**
     * Secretary account.
     */
    SECRETARY(1);

    private int value;

    /*
     * int-to-enum solution source: http://stackoverflow.com/a/5292821
     */
    private static final Map<Integer, AccountLevel> INT_TYPE = new HashMap<Integer, AccountLevel>();
    static {
        for (final AccountLevel type : AccountLevel.values()) {
            INT_TYPE.put(type.value, type);
        }
    }

    AccountLevel(final int value) {
        this.value = value;
    }

    /**
     * Get the int value of the AccountLevel.
     * 
     * @return the int value
     */
    public int getIntValue() {
        return value;
    }

    /**
     * Get the AccountLevel of the relevant integer value.
     * 
     * @param value
     *            the integer value of the account level.
     * @return the enum value of the account level
     */
    public static AccountLevel of(final int value) {
        return INT_TYPE.get(value);
    }
}
