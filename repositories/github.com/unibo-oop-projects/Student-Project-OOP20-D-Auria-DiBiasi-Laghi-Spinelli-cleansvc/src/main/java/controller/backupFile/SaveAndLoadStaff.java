package controller.backupFile;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import controller.Company;
import controller.CompanyImpl;
import model.users.Staff;
import model.users.StaffImpl;

public class SaveAndLoadStaff implements SaveAndLoad {
    private Company company = CompanyImpl.getInstance();
    private static final String SEP = File.separator;
    private static final String FILE_STAFF = "Staff.txt";
    private static final String CFPIVA_STR = "CFPIVA: ";
    private static final String NAME_STR = "NAME: ";
    private static final String ADDRESS_STR = "ADDRESS: ";
    private static final String CITY_STR = "CITY: ";
    private static final String CAP_STR = "CAP: ";
    private static final String TEL_STR = "TEL: ";
    private static final String EMAIL_STR = "EMAIL: ";
    private static final String ADMIN_STR = "ADMIN: ";
    /**
     * A method that saves a staff.
     */
    @Override
    public void save() {
        try (BufferedWriter w = new BufferedWriter(new FileWriter(FILE_STAFF))) {
            for (final Staff s : this.company.getStaff()) {
                w.write(CFPIVA_STR + s.getCFPIVA());
                w.newLine();
                w.write(NAME_STR + s.getName());
                w.newLine();
                w.write(ADDRESS_STR + s.getAddress());
                w.newLine();
                w.write(CITY_STR + s.getCity());
                w.newLine();
                w.write(CAP_STR + s.getCAP());
                w.newLine();
                w.write(TEL_STR + s.getTel());
                w.newLine();
                w.write(EMAIL_STR + s.getEmail());
                w.newLine();
                String admin = s.isAdmin() ? "si" : "no";
                w.write(ADMIN_STR + admin);
                w.newLine();
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * A method that loads a staff.
     */
    @Override
    public void load() {
        final List<String> cfPIvaList = new ArrayList<>();
        final List<String> nameList = new ArrayList<>();
        final List<String> addressList = new ArrayList<>();
        final List<String> cityList = new ArrayList<>();
        final List<String> capList = new ArrayList<>();
        final List<String> telList = new ArrayList<>();
        final List<String> emailList = new ArrayList<>();
        final List<String> adminList = new ArrayList<>();
        try (BufferedReader r = new BufferedReader(new FileReader(FILE_STAFF))) {
            r.lines().forEach(l -> {
                if (l.contains(CFPIVA_STR)) {
                    cfPIvaList.add(l.substring(CFPIVA_STR.length()));
                }
                if (l.contains(NAME_STR)) {
                    nameList.add(l.substring(NAME_STR.length()));
                }
                if (l.contains(ADDRESS_STR)) {
                    addressList.add(l.substring(ADDRESS_STR.length()));
                }
                if (l.contains(CITY_STR)) {
                    cityList.add(l.substring(CITY_STR.length()));
                }
                if (l.contains(CAP_STR)) {
                    capList.add(l.substring(CAP_STR.length()));
                }
                if (l.contains(TEL_STR)) {
                    telList.add(l.substring(TEL_STR.length()));
                }
                if (l.contains(EMAIL_STR)) {
                    emailList.add(l.substring(EMAIL_STR.length()));
                }
                if (l.contains(ADMIN_STR)) {
                    adminList.add(l.substring(ADMIN_STR.length()));
                }
            });
            for (int i = 0; i < cfPIvaList.size(); i++) {
                Boolean admin = (adminList.get(i).equals("si"));
                this.company.addStaff(new StaffImpl(cfPIvaList.get(i), nameList.get(i), addressList.get(i), cityList.get(i), Integer.parseInt(capList.get(i)), telList.get(i), emailList.get(i), admin));
            }
        } catch (final IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
