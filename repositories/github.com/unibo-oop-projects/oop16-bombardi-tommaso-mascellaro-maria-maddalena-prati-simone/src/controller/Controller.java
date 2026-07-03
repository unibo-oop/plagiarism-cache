package controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import controller.interfaces.ControllerIn;
import controller.interfaces.ControllerOut;
import model.admin.AdminImpl;
import model.admin.Pair;
import model.admin.interfaces.Admin;
import model.admin.products.Instructor;
import model.admin.products.Object1;
import model.admin.products.Object2;
import model.admin.products.Season;
import model.admin.products.Skipass;
import model.operations.Operation;
import model.user.UserImpl;
import model.user.interfaces.User;

/**
 * Class with controller implementation, designed using singleton pattern.
 */
public final class Controller extends Utilities implements ControllerIn, ControllerOut {

    private static final String DIR = System.getProperty("user.home") + System.getProperty("file.separator") + "SkiCenter DB";
    private static final File DIRECTORY = new File(DIR);
    private static final File FILE_USERS = new File(DIR + System.getProperty("file.separator") + "fileUsers.dat");
    private static final File FILE_OPERATIONS =  new File(DIR + System.getProperty("file.separator") + "fileOperations.dat");
    private static final Admin MA = AdminImpl.getModelAdmin();
    private static final User MU = new UserImpl();
    private static final Controller SINGLETON = new Controller();

    /**
     * Private constructor for controller implementation.
     */
    private Controller() {
        super();
        this.createFolderAndFiles();
        this.addFileOperations();
        this.addFileUsers();
    }

    private void createFolderAndFiles() {
        try {
            this.createDir(DIRECTORY);
        } catch (IllegalStateException exc) {
            System.err.println("SkiCenter DB Directory Non Creata Correttamente");
        }
        try {
            this.createFile(FILE_USERS);
        } catch (IllegalStateException exc) {
            System.err.println("Users File Non Creato Correttamente");
        }
        try {
            this.createFile(FILE_OPERATIONS);
        } catch (IllegalStateException exc) {
            System.err.println("Operations File Non Creato Correttamente");
        }
    }
    private void addFileOperations() {
        Map<Integer, Pair<String, Operation>> op;
        try {
            op = new HashMap<>(this.readOperations());
        } catch (UnsupportedOperationException exc) {
            op = new HashMap<>();
        }
        MA.addOperations(op);
    }
    private void addFileUsers() {
        Map<Pair<String, String>, Pair<String, String>> us;
        try {
            us = new HashMap<>(this.readUsers());
        } catch (UnsupportedOperationException exc) {
            us = new HashMap<>();
        }
        MU.addUsers(us);
    }

    /**
     * Get controller.
     * 
     * @return the only one instance of controller
     */
    public static synchronized Controller getController() {
        return SINGLETON;
    }


    @Override
    public void loginAdmin(final String username, final String password) {
        if (this.checkUsers(username).isPresent() && this.checkUsers(password).isPresent()) {
            MA.loginAdmin(username, password);
        } else {
            throw new IllegalArgumentException();
        }
    }
    @Override
    public void logoutAdmin() {
        MA.logoutAdmin();
    }
    @Override
    public Optional<String> getCurrentAdmin() {
        return MA.getCurrentAdmin();
    }
    @Override
    public void loginUser(final String username, final String password) {
        if (this.checkUsers(username).isPresent() && this.checkUsers(password).isPresent()) {
            MU.checkLogin(username, password);
        } else {
            throw new IllegalArgumentException();
        }
    }
    @Override
    public void logoutUser() {
        MU.logout();
    }
    @Override
    public Optional<String> getCurrentUser() {
        return MU.getLoggedUserName();
    }


    @Override
    public Set<Object1> getBuyObjects() {
        return MA.getBuyObjects();
    }
    @Override
    public Set<Object2> getRentAndStorageObjects() {
        return MA.getRentAndStorageObjects();
    }
    @Override
    public Set<Instructor> getInstructors() {
        return MA.getInstructors();
    }
    @Override
    public Set<Skipass> getSkipass() {
        return MA.getSkipass();
    }
    @Override
    public Set<Season> getSeasons() {
        return MA.getSeasons();
    }
    @Override
    public Set<String> getOperationTypes(final Set<Integer> op) {
        return MA.getOperationTypes(op);
    }


    @Override
    public Optional<Instructor> getInstructor(final String description) {
        return MA.getInstructor(description);
    }
    @Override
    public Optional<Skipass> getSkipass(final String description) {
        return MA.getSkipass(description);
    }
    @Override
    public Optional<Season> getSeason(final String detail) {
        return MA.getSeason(detail);
    }


    @Override
    public String getBuyPrice(final Object1 obj, final String numObj) {
        if (this.checkObjects(numObj).isPresent()) {
            return MA.getBuyPrice(obj, this.checkObjects(numObj).get());
        } else {
            throw new IllegalArgumentException();
        }
    }
    @Override
    public String getRentPrice(final Object2 obj, final String numObj, final String numDays, final Season season) {
        if (this.checkObjects(numObj).isPresent() && this.checkDays(numDays).isPresent()) {
            return MA.getRentPrice(obj, this.checkObjects(numObj).get(), this.checkDays(numDays).get(), season);
        } else {
            throw new IllegalArgumentException();
        }
    }
    @Override
    public String getInstructorPrice(final Instructor inst, final String numSkiers, final Season season) {
        if (this.checkStudents(numSkiers).isPresent()) {
            return MA.getInstructorPrice(inst, this.checkStudents(numSkiers).get(), season);
        } else {
            throw new IllegalArgumentException();
        }
    }
    @Override
    public String getSkipassPrice(final Skipass skip, final String numObj, final Season season) {
        if (this.checkObjects(numObj).isPresent()) {
            return MA.getSkipassPrice(skip, this.checkObjects(numObj).get(), season);
        } else {
            throw new IllegalArgumentException();
        }
    }
    @Override
    public String getStoragePrice(final Object2 obj, final String numObj, final String numDays) {
        if (this.checkObjects(numObj).isPresent() && this.checkDays(numDays).isPresent()) {
            return MA.getStoragePrice(obj, this.checkObjects(numObj).get(), this.checkDays(numDays).get());
        } else {
            throw new IllegalArgumentException();
        }
    }


    @Override
    public void addBuyObject(final Object1 obj, final String numObj) {
        if (this.checkObjects(numObj).isPresent()) {
            MU.buyProduct(this.checkObjects(numObj).get(), obj);
        } else {
            throw new IllegalArgumentException();
        }
    }
    @Override
    public void addRentObject(final Object2 obj, final String numObj, final String numDays, final Season season) {
        if (this.checkObjects(numObj).isPresent() && this.checkDays(numDays).isPresent()) {
            MU.rentProduct(this.checkObjects(numObj).get(), obj, this.checkDays(numDays).get(), season);
        } else {
            throw new IllegalArgumentException();
        }
    }
    @Override
    public void addInstructor(final Instructor type, final String numStudents, final Season season) {
        if (this.checkStudents(numStudents).isPresent()) {
            MU.bookLesson(this.checkStudents(numStudents).get(), season, type);
        } else {
            throw new IllegalArgumentException();
        }
    }
    @Override
    public void addSkipass(final Skipass type, final String numObj, final Season season) {
        if (this.checkObjects(numObj).isPresent()) {
            MU.buySkiPass(this.checkObjects(numObj).get(), type, season);
        } else {
            throw new IllegalArgumentException();
        }
    }
    @Override
    public void addStorage(final Object2 obj, final String numObj, final String numDays) {
        if (this.checkObjects(numObj).isPresent() && this.checkDays(numDays).isPresent()) {
            MU.depositProduct(this.checkObjects(numObj).get(), this.checkDays(numDays).get(), obj);
        } else {
            throw new IllegalArgumentException();
        }
    }


    @Override
    public Map<Integer, Pair<String, Pair<String, String>>> getCart() {
        return MU.getCartDetails();
    }
    @Override
    public String getCartPrice() {
        return MU.getCartTotalPrice();
    }
    @Override
    public void removeOperation(final String index) {
        if (this.checkIndex(index).isPresent()) {
            MU.removeFromCart(this.checkIndex(index).get());
        } else {
            throw new IllegalArgumentException();
        }
    }
    @Override
    public void removeAllOperations() {
        MU.emptyCart();
    }
    @Override
    public void pay(final Pair<String, String> ownerCode, final Pair<String, String> dateCvc) {
        if (this.checkName(ownerCode.getX()).isPresent() && this.checkCardNumber(ownerCode.getY()).isPresent() 
                && this.checkCardDate(dateCvc.getX()).isPresent() && this.checkCardCvc(dateCvc.getY()).isPresent()) {
            MU.checkPayment(ownerCode.getY(), dateCvc.getX());
        } else {
            throw new IllegalArgumentException();
        }
    }


    @SuppressWarnings("unchecked")
    private Map<Integer, Pair<String, Operation>>  readOperations() {
        if (this.readFile(FILE_OPERATIONS).isPresent()) {
            return (Map<Integer, Pair<String, Operation>>) this.readFile(FILE_OPERATIONS).get();
        } else {
            throw new UnsupportedOperationException();
        }
    }
    @Override
    public void completeOperations() {
        MA.addOperations(MU.checkout(this.getAllOperations().size()));
        if (!this.writeFile(MA.getOperations(), FILE_OPERATIONS).isPresent()) {
            throw new UnsupportedOperationException();
        }
    }
    @SuppressWarnings("unchecked")
    private Map<Pair<String, String>, Pair<String, String>> readUsers() {
        if (this.readFile(FILE_USERS).isPresent()) {
            return (Map<Pair<String, String>, Pair<String, String>>) this.readFile(FILE_USERS).get();
        } else {
            throw new UnsupportedOperationException();
        }
    }
    @Override
    public void registerUser(final Pair<String, String> userPass, final Pair<String, String> nameSur) {
        if (this.checkName(nameSur.getX()).isPresent() && this.checkName(nameSur.getY()).isPresent()
                && this.checkUsers(userPass.getX()).isPresent() && this.checkUsers(userPass.getY()).isPresent()) {
            MU.register(userPass, nameSur);
            if (!this.writeFile(MU.getUsers(), FILE_USERS).isPresent()) {
                throw new UnsupportedOperationException();
            }
        } else {
            throw new IllegalArgumentException();
        }
    }
    @Override
    public void resetApplication() {
        MA.resetOperations();
        if (!this.writeFile(MA.getOperations(), FILE_OPERATIONS).isPresent()) {
            throw new UnsupportedOperationException();
        }
        //MU.resetUsers();
        if (!this.writeFile(MU.getUsers(), FILE_USERS).isPresent()) {
            throw new UnsupportedOperationException();
        }
    }


    @Override
    public Set<Integer> getAllOperations() {
        return MA.getAllOperations();
    }
    @Override
    public Set<Integer> getUserOperations(final String user) {
        return MA.getUserOperations(user);
    }
    @Override
    public Set<Integer> getTypeOperations(final String type) {
        return MA.getTypeOperations(type);
    }
    @Override
    public Set<Integer> getUserAndTypeOperations(final String user, final String type) {
        return MA.getUserAndTypeOperations(user, type);
    }


    @Override
    public Map<Integer, Pair<String, Operation>> getOperations() {
        return MA.getOperations();
    }
    @Override
    public Pair<String, String> getOperationGain(final Integer operation) {
        return MA.getOperationGain(operation);
    }
    @Override
    public Pair<String, String> getTotalGain() {
        return MA.getTotalGain();
    }

}
