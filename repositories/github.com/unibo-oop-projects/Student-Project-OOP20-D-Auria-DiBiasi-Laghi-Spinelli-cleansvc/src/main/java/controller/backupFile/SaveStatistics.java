package controller.backupFile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class SaveStatistics {
    private static final String SEP = File.separator;
    private static final String FILE_STATISTICS = "Statistics.txt";

    private FileWriter fw;

    /**
     * A method that saves statistics.
     * @param datepicker
     * @param minutes
     * @param entries
     */
    public void save(final LocalDate datepicker, final double minutes, final double entries) {
        try {
            fw = new FileWriter(FILE_STATISTICS, true);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        try (BufferedWriter w = new BufferedWriter(fw)) {
            w.write("Data: " + datepicker + " " + "Minuti: " + minutes + " " + "Entrate: " + entries);
            w.newLine();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

}
