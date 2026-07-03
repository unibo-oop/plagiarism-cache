package model.user;

import java.text.NumberFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import model.admin.Demo;
import model.admin.Pair;
import model.operations.BuyOperation;
import model.operations.InstructorOperation;
import model.operations.Operation;
import model.operations.RentOperation;
import model.operations.SkipassOperation;
import model.operations.StorageOperation;
import model.admin.products.Instructor;
import model.admin.products.Object1;
import model.admin.products.Object2;
import model.admin.products.Season;
import model.admin.products.Skipass;
import model.user.interfaces.LoginUser;
import model.user.interfaces.ShoppingCart;
import model.user.interfaces.Account;
/**
 * 
 */
public class UserImpl extends Demo implements Account, ShoppingCart, LoginUser {

    //CARRELLO
    private final Map<Integer, Operation> cart = new HashMap<>();
    private int count = 1; 
    //UTENTE
    private final Map<Pair<String, String>, Pair<String, String>> users;
    private Optional<Pair<String, String>> currentUser = Optional.empty();
    //MAGIC NUMBERS AND REPS
    /**
     * months in a year.
     */
    public static final int MONTHS = 12;
    /**
     * String that repeats. 
     */
    public static final String QUANTITY = "Quantita' : ";

    /**
     * initialize users map with demo users.
     */
    public UserImpl() {
        super();
        this.users = super.demoUsers();
    }

    //LOGIN UTENTE
    @Override
    public void register(final Pair<String, String> userPass, final Pair<String, String> nameSurname) {
        for (final Pair<String, String> p : users.keySet()) {
            if (userPass.getX().equals(p.getX())) {
                //USERNAME ALREADY EXIST
                throw new IllegalStateException();
            }
        }
        //CREATE NEW USER
        users.put(userPass, nameSurname);
        this.checkLogin(userPass.getX(), userPass.getY());
    }
    @Override
    public void checkLogin(final String username, final String password) {
        boolean login = false;
        for (final Pair<String, String> p : users.keySet()) {
            if (username.equals(p.getX()) && password.equals(p.getY())) {
                //LOGIN OK
                this.currentUser = Optional.of(new Pair<>(username, password));
                login = true;
            }
        }
        if (!login) {
            throw new UnsupportedOperationException();
        }
    }
    @Override
    public Optional<String> getLoggedUserName() {
        if (this.currentUser.isPresent()) {
            return Optional.of(this.users.get(this.currentUser.get()).getX() + " " + this.users.get(this.currentUser.get()).getY());
        }
        return Optional.empty();
    }
    @Override
    public void logout() {
        this.currentUser = Optional.empty();
    }

    //MEMORIZZAZIONE UTENTI
    @Override
    public void addUsers(final Map<Pair<String, String>, Pair<String, String>> usersMap) {
        for (final Pair<String, String> p : usersMap.keySet()) {
            if (!this.getUsers().containsKey(p)) {
                this.users.put(p, usersMap.get(p));
            }
        }
    }
    @Override
    public Map<Pair<String, String>, Pair<String, String>> getUsers() {
        return users;
    }

    //UTENTE
    @Override
    public Map<Integer, Pair<String, Operation>> checkout(final int savedOps) {
        final Map<Integer, Pair<String, Operation>> completedOps = new HashMap<>();
        for (final int i : this.cart.keySet()) {
            completedOps.put(i + savedOps, new Pair<>(this.currentUser.get().getX(), this.cart.get(i)));
        }
        this.currentUser = Optional.empty();
        this.emptyCart();
        return completedOps;
    }

    //CARRELLO
    @Override
    public void buyProduct(final int quantity, final Object1 obj) {
        final Operation buy = new BuyOperation(obj, quantity);
        cart.put(this.count, buy);
        this.count++;
    }
    @Override
    public void rentProduct(final int quantity, final Object2 obj, final int duration, final Season season) {
        final Operation rent = new RentOperation(obj, quantity, duration, season);
        cart.put(this.count, rent);
        this.count++;
    }
    @Override
    public void depositProduct(final int numObj, final int duration, final Object2 obj) {
        final Operation storage = new StorageOperation(obj, numObj, duration);
        cart.put(this.count, storage);
        this.count++;
    }
    @Override
    public void bookLesson(final int numStudents, final Season season, final Instructor instructor) {
        final Operation lesson = new InstructorOperation(instructor, numStudents, season);
        cart.put(this.count, lesson);
        this.count++;
    }
    @Override
    public void buySkiPass(final int quantity, final Skipass skipass, final Season season) {
        final Operation pass = new SkipassOperation(skipass, quantity, season);
        cart.put(this.count, pass);
        this.count++;
    }
    @Override
    public void removeFromCart(final int index) {
        if (index <= cart.size()) {
            for (int i = index; i < cart.keySet().size(); i++) {
                cart.replace(i, cart.get(i), cart.get(i + 1));
            }
            cart.remove(cart.keySet().size());
            this.count--;
        } else {
            throw new IllegalArgumentException();
        }
    }
    @Override
    public Map<Integer, Operation> getCart() {
        return this.cart;
    }
    @Override
    public void emptyCart() {
        this.count = 1;
        this.cart.clear();
    }
    @Override
    public String getCartTotalPrice() {
        double totalPrice = 0;
        for (final int i : this.cart.keySet()) {
            totalPrice = totalPrice + this.cart.get(i).getPrice();
        }
        return this.round(totalPrice);
    }

    //PAGAMENTO
    @Override
    public void checkPayment(final String code, final String date)  {
        if (!this.checkDate(date) || !this.checkCardCode(code)) {
            throw new IllegalArgumentException();
        }
    }
    //CHECK PER IL PAGAMENTO
    private boolean checkCardCode(final String code) {
        if (!code.isEmpty() && code.length() == 16) {
            try {
                Long.parseLong(code);
            } catch (NumberFormatException e) { 
                return false;
            }
            return true;
        }
        return false;
    }
    private boolean checkDate(final String date) {
        final String expireMonth = date.substring(0, 2);
        final String expireYear = date.substring(3, 7);
        int month = 0;
        int year = 0;
        final Calendar today = Calendar.getInstance();

        if (date.substring(2, 3).equals("/")) {
            try {
                month = Integer.parseInt(expireMonth);
                year = Integer.parseInt(expireYear);
            } catch (NumberFormatException e) { 
                return false;
            }
            if ((month <= MONTHS) && (month > 0)) {
                if (year > today.get(Calendar.YEAR)) {
                    return true;
                } else if (year == today.get(Calendar.YEAR) && month >= today.get(Calendar.MONTH)) {
                    return true;
                }
            }
        }
        return false;
    }

    //ARROTONDAMENTO PREZZI
    private String round(final double num) {
        final NumberFormat numForm = NumberFormat.getInstance();
        numForm.setMaximumFractionDigits(2);
        numForm.setMinimumFractionDigits(2);
        return numForm.format(num);
    }
}