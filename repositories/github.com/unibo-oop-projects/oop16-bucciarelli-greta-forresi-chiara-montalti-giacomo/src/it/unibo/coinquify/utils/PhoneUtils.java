package it.unibo.coinquify.utils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFormattedTextField;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

/**
 * Phone util class.
 */
public final class PhoneUtils {
    private PhoneUtils() {
    }

    /**
     * * @return JTextField that accepts only numbers in ########## (where # is
     * a digit)
     * 
     * @throws ParseException
     *             if there's error in parsing
     */
    public static JTextField getPhoneNumberJTextField() throws ParseException {
        final MaskFormatter maskFormatter = new MaskFormatter("##########");
        maskFormatter.setPlaceholderCharacter('_');
        return new JFormattedTextField(maskFormatter);
    }

    /**
     * 
     * @return an empty JTextField that acepts only numbers.
     * @throws ParseException
     *             if occurs error in parsing
     */
    public static JTextField getContactNumberJTextField() throws ParseException {
        final MaskFormatter maskFormatter = new MaskFormatter("#########");
        maskFormatter.setPlaceholderCharacter(' ');
        return new JFormattedTextField(maskFormatter);
    }

    /**
     * 
     * @return the list of contact fields.
     */
    public static List<String> getArrayOfFields() {
        return new ArrayList<String>(Arrays.asList(Messages.getMessages().getString("NAME"),
                Messages.getMessages().getString("SURNAME"), Messages.getMessages().getString("FISCAL_CODE"),
                Messages.getMessages().getString("PHONE_NUMBER"), Messages.getMessages().getString("BIRTHDAY"),
                Messages.getMessages().getString("ADDRESS"), Messages.getMessages().getString("MAIL"),
                Messages.getMessages().getString("HOME_NUMBER"), Messages.getMessages().getString("WORK_NUMBER")));
    }

    /**
     * 
     * @param name
     *            of contact
     * @param surname
     *            of contact
     * @param fiscalCode
     *            of contact
     * @param phoneNumber
     *            of contact
     * @return if can exist a Contact with these fields
     */
    @SuppressWarnings("all")
    public static boolean isArgumentValid(final String name, final String surname, final String fiscalCode,
            final String phoneNumber) {
        return !(name.isEmpty() || surname.isEmpty() || phoneNumber.isEmpty()
                || (phoneNumber.length() > Constants.PHONENUMBER_MAX_LENGTH)
                || (fiscalCode.length() > 0 && fiscalCode.length() != Constants.FISCALCODE_LENGTH));
    }
}
