package it.unibo.coinquify.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import it.unibo.coinquify.telephonebook.model.Contact;

/**
 * FileWriter of contacts to a .csv file.
 *
 */
public final class CsvFileWriter {

    private static final CsvFileWriter SINGLETON = new CsvFileWriter();

    // Delimiter used in CSV file
    private static final String COMMA_DELIMITER = ",";
    private static final String NEW_LINE_SEPARATOR = System.lineSeparator();

    private CsvFileWriter() {
    }

    /**
     * 
     * @return the Singleton object to save contacts
     */
    public CsvFileWriter getCsvFileWriter() {
        return SINGLETON;
    }

    /**
     * 
     * @param fileName
     *            the path where save the csv file
     * @param header
     *            the list of arguments of Contacts to save
     * @param contactsToWrite
     *            list of contact to write
     * @throws IOException
     *             if occurs problems with files
     */
    public static void writeCsvFile(final String fileName, final List<String> header,
            final List<Contact> contactsToWrite) throws IOException {

        try (FileWriter fileWriter = new FileWriter(fileName)) {

            // Write the CSV file header
            for (int i = 0; i < header.size() - 1; i++) {
                fileWriter.append(header.get(i));
                fileWriter.append(COMMA_DELIMITER);
            }

            // Save the last header
            fileWriter.append(header.get(header.size() - 1));
            fileWriter.append(NEW_LINE_SEPARATOR);

            // Write all contacts
            for (final Contact cntct : contactsToWrite) {
                fileWriter.append(cntct.getName() + COMMA_DELIMITER);
                fileWriter.append(cntct.getSurname() + COMMA_DELIMITER);
                fileWriter.append(cntct.getFiscalCode() + COMMA_DELIMITER);
                fileWriter.append(cntct.getPhoneNumber() + COMMA_DELIMITER);
                fileWriter.append((cntct.getBirthday().isPresent() ? cntct.getBirthday().get() : "") + COMMA_DELIMITER);
                fileWriter.append(cntct.getAddress() + COMMA_DELIMITER);
                fileWriter.append(cntct.getEmail() + COMMA_DELIMITER);
                fileWriter.append(cntct.getHomePhoneNumber() + COMMA_DELIMITER);
                fileWriter.append(cntct.getWorkPhoneNumber() + NEW_LINE_SEPARATOR);
            }

        }
    }
}