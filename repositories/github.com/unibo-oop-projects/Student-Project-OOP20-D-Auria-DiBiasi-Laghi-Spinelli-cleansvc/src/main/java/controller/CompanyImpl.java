package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import model.Appointments;
import model.Products;
import model.users.Clients;
import model.users.Staff;

public final class CompanyImpl implements Company {

    private static final CompanyImpl SINGLETON = new CompanyImpl();
    private final List<Staff> staff = new ArrayList<>();
    private final List<Clients> clients = new ArrayList<>();
    private final List<Products> products = new ArrayList<>();
    private final List<Appointments> appointments = new ArrayList<>();

    public CompanyImpl() { }
    public static CompanyImpl getInstance() {
        return SINGLETON;
    }

    @Override
    public void addStaff(final Staff s) {
        this.staff.add(s);
    }

    @Override
    public void removeStaff(final Staff s) {
        this.staff.remove(s);
    }

    @Override
    public Optional<Staff> searchStaffbyCF(final String CFStaff) {
        for (final Staff s : this.staff) {
            if (s.getCFPIVA().equals(CFStaff)) {
                return Optional.of(s);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Staff> searchStaffbyEmail(final String emailStaff) {
        for (final Staff s : this.staff) {
            if (s.getEmail().equals(emailStaff)) {
                return Optional.of(s);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Staff> getStaff() {
        return Collections.unmodifiableList(this.staff);
    }

    @Override
    public void addClient(final Clients c) {
        this.clients.add(c);
    }

    @Override
    public void removeClient(final Clients c) {
        this.clients.remove(c);
    }

    @Override
    public Optional<Clients> searchClient(final String CF_PIVA) {
        for (final Clients c : this.clients) {
            if (c.getCFPIVA().equals(CF_PIVA)) {
                return Optional.of(c);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Clients> getClients() {
        return Collections.unmodifiableList(this.clients);
    }

    @Override
    public void addProduct(final Products p) {
        this.products.add(p);
    }

    @Override
    public void removeProduct(final Products p) {
        this.products.remove(p);
    }

    @Override
    public Optional<Products> searchProduct(final String codeProduct) {
        for (final Products p : this.products) {
            if (p.getCode().equals(codeProduct)) {
                return Optional.of(p);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Products> getProducts() {
        return Collections.unmodifiableList(this.products);
    }

    @Override
    public Optional<List<Products>> getProductsByStepType(final String stepType) {
        List<Products> productByStep = new ArrayList<>();
        for (final Products product : this.products) {
            if (product.getStepType().getType().equals(stepType)) {
                productByStep.add(product);
            }
        }
        return (productByStep.isEmpty()) ? Optional.empty() : Optional.of(productByStep);
    }

    @Override
    public void addAppointment(final Appointments a) {
        this.appointments.add(a);
    }

    @Override
    public void removeAppointment(final Appointments a) {
        this.appointments.remove(a);
    }

    @Override
    public Optional<Appointments> searchAppointment(final String date, final String hour) {
        for (final Appointments a : this.appointments) {
            if (a.getDate().equals(date) && a.getHour().equals(hour)) {
                return Optional.of(a);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Appointments> getAppointment() {
        return Collections.unmodifiableList(appointments);
    }
}
