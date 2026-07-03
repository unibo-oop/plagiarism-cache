package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import utilities.CreateCinema;

/**
 * It is used to save and read informations of the balance of the cinema.
 *
 */
public class SaveAndReadBalance implements SaveAndRead<Double> {

    /**
     * It is the string used to save the budget.
     */
    protected static final String BALANCE_STR = "BUDGET: ";
    /**
     * It is the string used to save the box office.
     */
    protected static final String BOX_OFFICE_STR = "BOX OFFICE: ";
    /**
     * It is the string used to save the expense.
     */
    protected static final String EXPENSE_STR = "EXPENSE: ";

    private static final String FILE = CreateCinema.FILE_B;

    private String check;

    @Override
    public void save() {
        try (BufferedWriter w = new BufferedWriter(new FileWriter(FILE))) {
            // It will save budget, box office and expense in different lines,
            // after a string used to recognize them.
            w.write(BALANCE_STR + CreateCinema.getCinema().getBudget());
            w.newLine();
            w.write(BOX_OFFICE_STR + CreateCinema.getCinema().getBoxOffice());
            w.newLine();
            w.write(EXPENSE_STR + CreateCinema.getCinema().getExpense());
            w.newLine();
        } catch (final IOException e) {
            System.err.println("ERRORE " + e.getMessage());
        }
    }

    @Override
    public Double read() {
        try (BufferedReader r = new BufferedReader(new FileReader(FILE))) {
            // Creation of a list with the lines of the file that contain the string contained also in check.
            final List<String> str = new ArrayList<>(
                    Arrays.asList(r.lines().filter(l -> l.contains(check)).toArray(String[]::new)));
            final Double d = Double.parseDouble(str.get(0).substring(check.length()));
            switch (check) {
            // If the string is equal to the value of BALANCE_STR it will set the budget.
            case BALANCE_STR:
                CreateCinema.getCinema().setBudget(d);
                break;
            // If the string is equal to the value of BOX_OFFICE_STR it will set the box office.
            case BOX_OFFICE_STR:
                CreateCinema.getCinema().setBoxOffice(d);
                break;
            // If the string is not equal to BALANCE_STR and BOX_OFFICE_STR, it will set the expense.
            default:
                CreateCinema.getCinema().setExpense(d);
            }
            return d;
        } catch (final Exception e) {
            return 0.0;
        }
    }

    @Override
    public void reset() {
        // It resets the balance and deleted informations from the file.
        CreateCinema.getCinema().setBudget(CinemaBalance.MAX_BUDGET);
        CreateCinema.getCinema().setBoxOffice(0.0);
        CreateCinema.getCinema().setExpense(0.0);
        try (BufferedWriter w = new BufferedWriter(new FileWriter(FILE))) {
            w.flush();
        } catch (final IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * It is used to set the string check.
     *
     * @param check
     *            the string used to chose which field of Balance we have to read.
     */
    protected void setCheck(final String check) {
        this.check = check;
    }

}
