package controller;

import java.util.List;
import java.util.Optional;

import model.Appointments;
import model.Products;
import model.users.Clients;
import model.users.Staff;

public interface Company {

    void addStaff(Staff s);
    void removeStaff(Staff s);
    Optional<Staff> searchStaffbyCF(String CFStaff);
    Optional<Staff> searchStaffbyEmail(String emailStaff);
    List<Staff> getStaff();

    void addClient(Clients c);
    void removeClient(Clients c);
    Optional<Clients> searchClient(String CF_PIVA);
    List<Clients> getClients();

    void addProduct(Products p);
    void removeProduct(Products p);
    Optional<Products> searchProduct(String codeProduct);
    List<Products> getProducts();
    Optional<List<Products>> getProductsByStepType(String stepType);

    void addAppointment(Appointments a);
    void removeAppointment(Appointments a);
    Optional<Appointments> searchAppointment(String date, String hour);
    List<Appointments> getAppointment();
}