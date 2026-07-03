package controller;


import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import demo.Demo;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import model.EmployeeImpl;
import model.ExerciseImpl;
import model.ModelImpl;
import model.PersonImpl;
import model.ProductImpl;
import model.ReadWriteData;
import model.SubscriptionImpl;
import model.UserImpl;
import model.enumerations.Status;
import model.interfaces.Data;
import model.interfaces.Employee;
import model.interfaces.Exercise;
import model.interfaces.Model;
import model.interfaces.Product;
import model.interfaces.User;
import view.View;
import view.ViewHandler;
import view.ViewObserver;

public class Controller implements ViewObserver{

    private final static Controller SINGLETON = new Controller();
    private View view = new ViewHandler();
    private Model model = new ModelImpl();

    private Controller() {

	    	File file = new File("dataProva.dat");
			if(!file.exists()) {
				new Demo();
			}
			else {
				Data dataToRead = ReadWriteData.read();
		        this.model.retreiveData().getUsersList().addAll(dataToRead.getUsersList());
		        this.model.retreiveData().getEmployees().addAll(dataToRead.getEmployees());
		        this.model.retreiveData().getProductInWharehouse().putAll(dataToRead.getProductInWharehouse());
		        this.model.retreiveData().getProductSold().putAll(dataToRead.getProductSold());
		        this.model.retreiveData().addToLoginData(dataToRead.getLoginData());
		        this.model.retreiveBalance().getFinancialOperation().putAll(dataToRead.getBalance().getFinancialOperation());
		        this.model.retreiveBalance().getMonthlyExpenses().putAll(dataToRead.getBalance().getMonthlyExpenses());
		        this.model.retreiveBalance().getMonthlyRevenue().putAll(dataToRead.getBalance().getMonthlyRevenue());
		        this.model.retreiveBalance().getMonthlyInscriptions().putAll(dataToRead.getBalance().getMonthlyInscriptions());
			}
		        this.view.setObserver(this);


    }

    public static Controller getInstance(){
        return SINGLETON;
    }

    @Override
    public Status login() {
            System.out.println(this.model.retreiveData().getLoginData());
            String user = view.getLogin().getUser();
            String pass = view.getLogin().getPassword();
            return model.getValidator().validate(user, pass);
    }

    @Override
    public void populateUsersTable() {
            this.view.getUsersTable().setData(this.model.retreiveData().getUsersList());
    }

    @Override
    public void populateProductTable() {
            this.view.getWarehouse().populateTable(this.model.retreiveData().getProductList());
    }

    @Override
    public void populateWorkoutTable(User user) {

        Map<Exercise, List<Exercise>> m = new HashMap<>();

        this.model.retreiveData().getUsersList()
                                                .get(this.model.retreiveData().getUsersList().indexOf(user))
                                                .getWorkout()
                                                .getExerciseMap()
                                                .entrySet()
                                                .stream()
                                                .forEach(entry ->
                                                                m.put(new ExerciseImpl(
                                                                                        entry.getKey()),
                                                                                        entry.getValue()));


        this.view.getWorkoutTable().setExerciseList(user, m);
    }

    @Override
    public void populateEmployeesTable() {
        this.view.getAdministration().setData(this.model.retreiveData().getEmployees());
    }

    @Override
    public Image getProfileImage(Scene scene) {
            return this.model.getImage(scene);
    }

    @Override
	public Image deleteProfileImage() {
            return this.model.getDefaultImage();
    }
/*
    @Override
    public Workout getUserWorkout(User user) {
            return this.model.getUserList()
                    .get(this.model.getUserList().indexOf(user))
                    .getWorkOutTable();
    }

    @Override
    public Status deleteUserWorkout(User user) {
            return null; this.model.getUserList()
                    .get(this.model.getUserList().indexOf(user))
                    .getWorkOutTable().;
    }
*/

    @Override
    public Status saveUserData() {

    		if (this.view.getuserProfile().getName() == null ||
    			this.view.getuserProfile().getSurname() == null ||
    			this.view.getuserProfile().getTaxCode() == null ||
    			this.view.getuserProfile().getBirthdate() == null ||
    			this.view.getuserProfile().getEmail() == null ||
    			this.view.getuserProfile().getPhoneNumber() == null ||
    			this.view.getuserProfile().getSubscriptionType() == null ||
    			this.view.getuserProfile().getValidFrom() == null ||
    			this.view.getuserProfile().getValidTo() == null ||
    			this.view.getuserProfile().getPaymentDate() == null
    			) {

    			return Status.NO_INPUT;

    		} else {

    			User user = new UserImpl(new PersonImpl.PersonBuilder()
                        .firstName(this.view.getuserProfile().getName())
                        .lastName(this.view.getuserProfile().getSurname())
                        .taxCode(this.view.getuserProfile().getTaxCode())
                        .birthday(this.view.getuserProfile().getBirthdate())
                        .email(this.view.getuserProfile().getEmail())
                        .telephoneNumber(this.view.getuserProfile().getPhoneNumber())
                        .imageProfile(this.view.getuserProfile().getProfileImage())
                        .build(),
                        new SubscriptionImpl.SubscriptionBuilder()
                        .type(this.view.getuserProfile().getSubscriptionType())
                        .signingDate(this.view.getuserProfile().getValidFrom())
                        .expirationDate(this.view.getuserProfile().getValidTo())
                        .paymentDate(this.view.getuserProfile().getPaymentDate())
                        .build());

    			return this.model.saveUser(user);
    		}
    }

    @Override
    public Status deleteUserData(User user) {
            return this.model.deleteUser(user);
    }

    @Override
    public void modifyUserData(User user) {
            this.view.getuserProfile().setFields(user);
    }

    @Override
    public List<User> getUsersList() {
            return this.model.retreiveData().getUsersList();
    }

    @Override
    public Status saveProductData() {

        try {
            this.view.getProductDetails().getPrice();
            this.view.getProductDetails().getQuantity();
        } catch (NumberFormatException e) {
            return Status.WRONG_VALUE;
        }

        if (this.view.getProductDetails().getName() == null ||
                this.view.getProductDetails().getCategory() == null ||
                this.view.getProductDetails().getId() == null ||
                this.view.getProductDetails().getDescription() == null){
            return Status.NO_INPUT;
        } else if(this.view.getProductDetails().getPrice() <= 0 ||
                    this.view.getProductDetails().getQuantity() < 0){
                return Status.WRONG_VALUE;
        } else {

            Product product = new ProductImpl(
                                                this.view.getProductDetails().getId(),
                                                this.view.getProductDetails().getName(),
                                                this.view.getProductDetails().getDescription(),
                                                this.view.getProductDetails().getCategory(),
                                                this.view.getProductDetails().getPrice(),
                                                this.view.getProductDetails().getQuantity()
                                                );

            return this.model.addProduct(product);
        }
    }

    @Override
    public Status deleteProductData(Product product) {
        return this.model.removeProduct(product);
    }

    @Override
    public void modifyProductData(Product product) {
        this.view.getProductDetails().setData(product);
    }

    @Override
    public List<Product> getProducts() {
            return this.model.retreiveData().getProductList();
    }


    @Override
    public Status saveEmployeeData() {

        if (this.view.getEmployeeProfile().getName() == null ||
                this.view.getEmployeeProfile().getSurname() == null ||
                this.view.getEmployeeProfile().getBirthdate() == null ||
                this.view.getEmployeeProfile().getPhoneNumber() == null ||
                this.view.getEmployeeProfile().getTaxCode() == null ||
                this.view.getEmployeeProfile().getEmail() == null ||
                this.view.getEmployeeProfile().getSalary() == null ||
                this.view.getEmployeeProfile().getContractType() == null ||
                this.view.getEmployeeProfile().getSigninDate() == null){

                return Status.NO_INPUT;

        } else {
                Employee employee = new EmployeeImpl(new PersonImpl.PersonBuilder()
                        .firstName(this.view.getEmployeeProfile().getName())
                        .lastName(this.view.getEmployeeProfile().getSurname())
                        .taxCode(this.view.getEmployeeProfile().getTaxCode())
                        .birthday(this.view.getEmployeeProfile().getBirthdate())
                        .email(this.view.getEmployeeProfile().getEmail())
                        .telephoneNumber(this.view.getEmployeeProfile().getPhoneNumber())
                        .build(),
                        this.view.getEmployeeProfile().getSalary(),
                        this.view.getEmployeeProfile().getSigninDate(),
                        this.view.getEmployeeProfile().getContractType());

                return this.model.addEmployee(employee);
        }
    }

    @Override
    public Status deleteEmployeeData(Employee employee) {
        return this.model.dismissEmployee(employee);
    }

    @Override
    public void modifyEmployeeData(Employee employee) {
        this.view.getEmployeeProfile().setFields(employee);
    }

    @Override
    public List<Employee> getEmployeeList() {
                return this.model.retreiveData().getEmployees();
    }

    @Override
    public Status addExercise(User user, Exercise ex, String day) {
        return this.model.getUserList().stream()
        		.filter( u -> u.getPerson().getTaxCode().equals(user.getPerson().getTaxCode()))
        		.findFirst().get().getWorkout().setExerciseInDay(day, ex);
    }

    @Override
    public Status removeExercise(User user, Exercise ex, String day) {
        return this.model.getUserList().stream()
        		.filter( u -> u.getPerson().getTaxCode().equals(user.getPerson().getTaxCode()))
        		.findFirst().get().getWorkout().deleteExercise(day, ex);
    }

}
