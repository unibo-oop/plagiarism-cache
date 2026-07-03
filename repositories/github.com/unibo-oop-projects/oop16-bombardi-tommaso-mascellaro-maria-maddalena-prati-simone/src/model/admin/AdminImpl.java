package model.admin;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Predicate;

import model.admin.interfaces.Admin;
import model.admin.products.Instructor;
import model.admin.products.Object1;
import model.admin.products.Object2;
import model.admin.products.Season;
import model.admin.products.Skipass;
import model.operations.OperationFactory;
import model.operations.Operation;
import model.operations.OperationFactoryImpl;

/**
 * Class with administrator model implementation, designed using singleton pattern.
 */
public final class AdminImpl extends Demo implements Admin {

    private static final OperationFactory OP = new OperationFactoryImpl();
    private static final Admin SINGLETON = new AdminImpl();
    private final Map<Pair<String, String>, Pair<String, String>> admins = new HashMap<>(this.demoAdmins());
    private final Map<Integer, Pair<String, Operation>> operations = new HashMap<>(this.demoOperations());
    private Optional<Pair<String, String>> currentAdmin = Optional.empty();

    /**
     * Private constructor for administrator model implementation.
     */
    private AdminImpl() {
        super();
    }

    /**
     * Get administrator model.
     * 
     * @return the only one instance of administrator model
     */
    public static synchronized Admin getModelAdmin() {
        return SINGLETON;
    }

    @Override
    public void loginAdmin(final String username, final String password) {
        if (this.admins.keySet().contains(new Pair<String, String>(username, password))) {
            this.currentAdmin = Optional.of(new Pair<String, String>(username, password));
        } else {
            throw new UnsupportedOperationException();
        }
    }
    @Override
    public void logoutAdmin() {
        this.currentAdmin = Optional.empty();
    }
    @Override
    public Optional<String> getCurrentAdmin() {
        if (this.currentAdmin.isPresent()) {
            return Optional.of(this.admins.get(this.currentAdmin.get()).getX() 
                    + " " + this.admins.get(this.currentAdmin.get()).getY());
        } else {
            return Optional.empty();
        }
    }


    @Override
    public Set<Object1> getBuyObjects() {
        final Set<Object1> set = new TreeSet<>((final Object1 o1, final Object1 o2) ->
                o1.getDescription().compareTo(o2.getDescription()));
        set.addAll(Arrays.asList(Object1.values()));
        return set;
    }
    @Override
    public Set<Object2> getRentAndStorageObjects() {
        final Set<Object2> set = new TreeSet<>((final Object2 o1, final Object2 o2) ->
                o1.getDescription().compareTo(o2.getDescription()));
        set.addAll(Arrays.asList(Object2.values()));
        return set;
    }
    @Override
    public Set<Instructor> getInstructors() {
        final Set<Instructor> set = new TreeSet<>((final Instructor i1, final Instructor i2) ->
            i1.getDescription().compareTo(i2.getDescription()));
        set.addAll(Arrays.asList(Instructor.values()));
        return set;
    }
    @Override
    public Set<Skipass> getSkipass() {
        final Set<Skipass> set = new TreeSet<>((final Skipass sk1, final Skipass sk2) ->
            Double.compare(sk1.getDuration(), sk2.getDuration()));
        set.addAll(Arrays.asList(Skipass.values()));
        return set;
    }
    @Override
    public Set<Season> getSeasons() {
        final Set<Season> set = new TreeSet<>((final Season s1, final Season s2) ->
            Double.compare(s2.getRate(), s1.getRate()));
        set.addAll(Arrays.asList(Season.values()));
        return set;
    }
    @Override
    public Set<String> getOperationTypes(final Set<Integer> op) {
        final Set<String> set = new TreeSet<>((final String s1, final String s2) -> s1.compareTo(s2));
        op.stream().filter(elem -> this.getAllOperations().contains(elem)
                && !set.contains(this.getOperations().get(elem).getY().getDescription())).
                    forEach(elem -> set.add(this.getOperations().get(elem).getY().getDescription()));
        return set;
    }


    @Override
    public Optional<Instructor> getInstructor(final String description) {
        return this.getInstructors().stream().filter(elem -> elem.getDescription().equals(description)).findFirst();
    }
    @Override
    public Optional<Skipass> getSkipass(final String description) {
        return this.getSkipass().stream().filter(elem -> elem.getDescription().equals(description)).findFirst();
    }
    @Override
    public Optional<Season> getSeason(final String period) {
        return this.getSeasons().stream().filter(elem -> elem.getPeriod().equals(period)).findFirst();
    }


    @Override
    public String getBuyPrice(final Object1 obj, final int numObj) {
        return this.round(OP.createBuyOperation(obj, numObj).getPrice());
    }
    @Override
    public String getRentPrice(final Object2 obj, final int numObj, final int numDays, final Season season) {
        return this.round(OP.createRentOperation(obj, numObj, numDays, season).getPrice());
    }
    @Override
    public String getInstructorPrice(final Instructor inst, final int numSkiers, final Season season) {
        return this.round(OP.createInstructorOperation(inst, numSkiers, season).getPrice());
    }
    @Override
    public String getSkipassPrice(final Skipass skip, final int numObj, final Season season) {
        return this.round(OP.createSkipassOperation(skip, numObj, season).getPrice());
    }
    @Override
    public String getStoragePrice(final Object2 obj, final int numObj, final int numDays) {
        return this.round(OP.createStorageOperation(obj, numObj, numDays).getPrice());
    }


    @Override
    public Set<Integer> getAllOperations() {
        return this.getSelectOperations(elem -> this.getOperations().containsKey(elem));
    }
    @Override
    public Set<Integer> getUserOperations(final String user) {
        return this.getSelectOperations(elem -> this.getOperations().get(elem).getX().equals(user));
    }
    @Override
    public Set<Integer> getTypeOperations(final String type) {
        return this.getSelectOperations(elem -> this.getOperations().get(elem).getY().getDescription().equals(type));
    }
    @Override
    public Set<Integer> getUserAndTypeOperations(final String user, final String type) {
        return this.getSelectOperations(elem -> this.getOperations().get(elem).getX().equals(user)
                && this.getOperations().get(elem).getY().getDescription().equals(type));
    }
    private Set<Integer> getSelectOperations(final Predicate<Integer> pred) {
        final Set<Integer> set = new TreeSet<>((final Integer i1, final Integer i2) -> Integer.compare(i1, i2));
        this.getOperations().keySet().stream().filter(pred).forEach(elem -> set.add(elem));
        return set;
    }


    @Override
    public void addOperations(final Map<Integer, Pair<String, Operation>> oper) {
        oper.keySet().stream().filter(elem -> !this.getAllOperations().contains(elem)).
            forEach(elem -> this.operations.put(elem, oper.get(elem)));
    }
    @Override
    public void resetOperations() {
        this.operations.clear();
        this.operations.putAll(this.demoOperations());
    }
    @Override
    public Map<Integer, Pair<String, Operation>> getOperations() {
        return this.operations;
    }


    @Override
    public Pair<String, String> getOperationGain(final Integer operation) {
        if (this.getOperations().containsKey(operation)) {
            return this.round(this.getOperations().get(operation).getY().getGain());
        } else {
            throw new IllegalArgumentException();
        }
    }
    @Override
    public Pair<String, String> getTotalGain() {
        double gain1 = 0.00, gain2 = 0.00;
        for (final Integer oper : this.getAllOperations()) {
            gain1 = gain1 + this.getOperations().get(oper).getY().getGain().getX();
            gain2 = gain2 + this.getOperations().get(oper).getY().getGain().getY();
        }
        return this.round(new Pair<Double, Double>(gain1, gain2));
    }
    private String round(final double value) {
        final NumberFormat numForm = NumberFormat.getInstance();
        numForm.setMaximumFractionDigits(2);
        numForm.setMinimumFractionDigits(2);
        return numForm.format(value);
    }
    private Pair<String, String> round(final Pair<Double, Double> value) {
        return new Pair<String, String>(this.round(value.getX()), this.round(value.getY()));
    }

}
