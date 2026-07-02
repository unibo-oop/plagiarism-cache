package controller.backupFile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import controller.Company;
import controller.CompanyImpl;
import model.Appointments;
import model.AppointmentsImpl;
import model.users.Clients;

public class SaveAndLoadAppointments implements SaveAndLoad {
    private Company company = CompanyImpl.getInstance();
    private static final String SEP = File.separator;
    private static final String FILE_APPOINTMENTS = "Appointments.txt";
    private static final String DATE_STR = "DATE: ";
    private static final String HOUR_STR = "HOUR: ";
    private static final String CLIENT_STR = "CLIENT: ";
    private static final String TIME_STR = "TIME: ";
    private static final String EARN_STR = "EARN: ";
    /**
     * A method that saves an appointment.
     */
    @Override
    public void save() {
        try (BufferedWriter w = new BufferedWriter(new FileWriter(FILE_APPOINTMENTS))) {
            for (final Appointments a : this.company.getAppointment()) {
                w.write(DATE_STR + a.getDate());
                w.newLine();
                w.write(HOUR_STR + a.getHour());
                w.newLine();
                w.write(CLIENT_STR + a.getClient().getCFPIVA());
                w.newLine();
                w.write(TIME_STR + a.getTime());
                w.newLine();
                w.write(EARN_STR + a.getEarn());
                w.newLine();
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * A method that loads an appointment.
     */
    @Override
    public void load() {
        final List<String> dateList = new ArrayList<>();
        final List<String> hourList = new ArrayList<>();
        final List<String> clientsList = new ArrayList<>();
        final List<Double> timeList = new ArrayList<>();
        final List<Double> earnList = new ArrayList<>();
        try (BufferedReader r = new BufferedReader(new FileReader(FILE_APPOINTMENTS))) {
            r.lines().forEach(l -> {
                if (l.contains(DATE_STR)) {
                    dateList.add(l.substring(DATE_STR.length()));
                }
                if (l.contains(HOUR_STR)) {
                    hourList.add(l.substring(HOUR_STR.length()));
                }
                if (l.contains(CLIENT_STR)) {
                    clientsList.add(l.substring(CLIENT_STR.length()));
                }
                if (l.contains(TIME_STR)) {
                    timeList.add(Double.valueOf(l.substring(TIME_STR.length())));
                }
                if (l.contains(EARN_STR)) {
                    earnList.add(Double.valueOf(l.substring(EARN_STR.length())));
                }
            });
            for (int i = 0; i < dateList.size(); i++) {
                Optional<Clients> c = this.company.searchClient(clientsList.get(i));
                if (!c.equals(Optional.empty())) {
                    this.company.addAppointment(new AppointmentsImpl(dateList.get(i), hourList.get(i), c.get(), timeList.get(i), earnList.get(i)));
                }
            }
        } catch (final IOException e) {
            System.err.println(e.getMessage());
        }
    }

}
