package model.admin;

import java.util.HashMap;
import java.util.Map;

import model.admin.products.Instructor;
import model.admin.products.Object1;
import model.admin.products.Object2;
import model.admin.products.Season;
import model.admin.products.Skipass;
import model.operations.OperationFactory;
import model.operations.Operation;
import model.operations.OperationFactoryImpl;

/**
 * Class with the implementation of a demo for the application.
 */
public class Demo {

    private static final String USER1 = "anto63";
    private static final String USER2 = "laura90";
    private static final String USER3 = "mario78";
    private static final String USER4 = "robi85";
    private static final OperationFactory OP = new OperationFactoryImpl();

    /**
     * Get demo administrators.
     * 
     * @return a map of administrators to initialize the application
     */
    public Map<Pair<String, String>, Pair<String, String>> demoAdmins() {
        final Map<Pair<String, String>, Pair<String, String>> admins = new HashMap<>();
        admins.put(new Pair<String, String>("tommi96", "96tommi"),  new Pair<String, String>("Tommaso", "Bombardi"));
        admins.put(new Pair<String, String>("meri96", "96meri"),  new Pair<String, String>("Maria Maddalena", "Mascellaro"));
        admins.put(new Pair<String, String>("simo96", "96simo"),  new Pair<String, String>("Simone", "Prati"));
        return admins;
    }

    /**
     * Get demo users.
     * 
     * @return a map of users to initialize the application
     */
    public Map<Pair<String, String>, Pair<String, String>> demoUsers() {
        final Map<Pair<String, String>, Pair<String, String>> users = new HashMap<>();
        users.put(new Pair<String, String>(USER1, "63anto"), new Pair<String, String>("Antonio", "Bianchi"));
        users.put(new Pair<String, String>(USER2, "90laura"), new Pair<String, String>("Laura", "Fabbri"));
        users.put(new Pair<String, String>(USER3, "78mario"), new Pair<String, String>("Mario", "Neri"));
        users.put(new Pair<String, String>(USER4, "85robi"), new Pair<String, String>("Roberto", "Rossi"));
        return users;
    }

    /**
     * Get demo operations.
     * 
     * @return a map of operations to initialize the application
     */
    public Map<Integer, Pair<String, Operation>> demoOperations() {
        int numOp = 1;
        final Map<Integer, Pair<String, Operation>> operations = new HashMap<>();
        operations.put(numOp++, new Pair<>(USER1, OP.createBuyOperation(Object1.SKI_BOOTS, 2)));
        operations.put(numOp++, new Pair<>(USER2, OP.createRentOperation(Object2.SKIS, 1, 3, Season.MID_SEASON)));
        operations.put(numOp++, new Pair<>(USER3, OP.createSkipassOperation(Skipass.TWO_DAYS, 2, Season.HIGH_SEASON)));
        operations.put(numOp++, new Pair<>(USER4, OP.createStorageOperation(Object2.SNOWBOARD, 3, 4)));
        operations.put(numOp++, new Pair<>(USER1, OP.createInstructorOperation(Instructor.SKI_2HOURS, 1, Season.OFF_SEASON)));
        operations.put(numOp++, new Pair<>(USER2, OP.createRentOperation(Object2.SKI_POLES, 2, 10, Season.HIGH_SEASON)));
        operations.put(numOp++, new Pair<>(USER3, OP.createBuyOperation(Object1.HELMET, 4)));
        operations.put(numOp++, new Pair<>(USER4, OP.createSkipassOperation(Skipass.HALF_DAY, 3, Season.OFF_SEASON)));
        operations.put(numOp++, new Pair<>(USER1, OP.createInstructorOperation(Instructor.SNOWBOARD_1HOUR, 2, Season.MID_SEASON)));
        operations.put(numOp++, new Pair<>(USER4, OP.createStorageOperation(Object2.SKIS, 1, 3)));
        return operations;
    }

}
