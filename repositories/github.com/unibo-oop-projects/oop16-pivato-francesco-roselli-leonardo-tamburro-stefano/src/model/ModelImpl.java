package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import model.enumerations.*;
import model.interfaces.*;

import javax.imageio.ImageIO;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class ModelImpl implements Model {

	private static final String SEP = System.getProperty("file.separator") + "a.txt";
	private static final String DEFAULT_IMAGE = SEP+"view"+SEP+"resources"+SEP+"images"+SEP+"blank-profile-picture.png";
	private static final int UNIT = 1;

	@Override
	public ValidatorImpl getValidator() {
		return new ValidatorImpl();
	}

	@Override
	public Image getImage(Scene scene) {
		Stage stage = (Stage) scene.getWindow();
		final FileChooser chooser = new FileChooser();
		chooser.setTitle("Chose image to load");
		chooser.getExtensionFilters().addAll(
				new ExtensionFilter("JPG", "*.jpg"),
				new ExtensionFilter("PNG", "*.png"));
		chooser.setInitialDirectory(new File(System.getProperty("user.home")));
		File file = chooser.showOpenDialog(stage);
		if(file == null){
			return this.getDefaultImage();
		}
		BufferedImage bufferedImage = null;
		try {
			bufferedImage = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Image image = SwingFXUtils.toFXImage(bufferedImage, null);
		return image;
	}

	@Override
	public Image getDefaultImage() {
		return new Image(DEFAULT_IMAGE);
	}

	@Override
	public Data retreiveData() {
		return DataImpl.getInstance();
	}

	@Override
	public BalanceImpl retreiveBalance() {
		return BalanceImpl.getInstance();
	}

	@Override
	public List<User> getUserList() {
		return DataImpl.getInstance().getUsersList();
	}

	private List<User> controlUser(User user) {
		return DataImpl.getInstance()
				.getUsersList()
				.stream()
				.filter(x->x.getPerson().getTaxCode().equals(user.getPerson().getTaxCode()) ? true : false)
				.collect(Collectors.toList());

	}

	private void controlSigningDate(User oldUser, User newUser) {
		if(!oldUser.getSubscription().getSigningDate().equals(newUser.getSubscription().getSigningDate())) {
			BalanceImpl.getInstance().subscriptionFirm(newUser.getSubscription());
		}
	}

	private Status modifyUser(User newUser) {
		if (!controlUser(newUser).isEmpty()) {
			User oldUser = controlUser(newUser).get(controlUser(newUser).size() - UNIT);
			controlSigningDate(oldUser, newUser);
			deleteUser(oldUser);
			DataImpl.getInstance().addToUserList(newUser);
			return Status.ALL_RIGHT;
		}
		return Status.USER_NOT_FOUND;
	}

	@Override
	public Status saveUser(User user) {
		if (modifyUser(user).equals(Status.USER_NOT_FOUND)) {
			DataImpl.getInstance().addToUserList(user);
			DataImpl.getInstance().getBalance().subscriptionFirm(user.getSubscription());
		}
		return Status.ALL_RIGHT;
	}

	@Override
	public Status deleteUser(User user) {
		if (DataImpl.getInstance().getUsersList().contains(user)) {
			DataImpl.getInstance().getUsersList().remove(user);
			return Status.ALL_RIGHT;
		}
		return Status.USER_NOT_FOUND;
	}

	private List<Product> controlProduct(Product product) {
		List<Product> l = DataImpl.getInstance().getProductInWharehouse()
								.entrySet()
								.stream()
								.map(x->x.getKey())
								.filter(x-> x.getProductCode().equals(product.getProductCode()) ? true : false)
								.collect(Collectors.toList());
		return l;

	}

	private Status modifyProduct(Product newProduct) {
		if (!controlProduct(newProduct).isEmpty()) {
			Product oldProduct = controlProduct(newProduct)
								.get(controlProduct(newProduct).size() - UNIT);
			DataImpl.getInstance().getProductInWharehouse().remove(oldProduct, DataImpl.getInstance()
													.getProductInWharehouse().get(oldProduct));

			DataImpl.getInstance().getProductInWharehouse()
									.merge(newProduct, newProduct.getQuantity(), (x, y)-> x + y);
			return Status.ALL_RIGHT;
		}
		return Status.PRODUCT_NOT_FOUND;
	}

	@Override
	public Status addProduct(Product product) {
		if(modifyProduct(product).equals(Status.PRODUCT_NOT_FOUND)) {
			DataImpl.getInstance().getProductInWharehouse()
									.merge(product, product.getQuantity(), (x, y)-> x + y);
		}
		return Status.ALL_RIGHT;
	}

	@Override
	public Status removeProduct(Product product) {
		if(DataImpl.getInstance().getProductInWharehouse().containsKey(product)) {
			DataImpl.getInstance().getProductInWharehouse().remove(product, DataImpl.getInstance()
															.getProductInWharehouse().get(product));
			return Status.ALL_RIGHT;
		}
		return Status.PRODUCT_NOT_FOUND;
	}

	private List<Employee> controlEmployee(Employee employee) {

		return DataImpl.getInstance()
							.getEmployees()
							.stream()
							.filter(x-> x.getPerson().getTaxCode().equals(employee.getPerson().getTaxCode()) ? true : false)
							.collect(Collectors.toList());
	}

	private Status modifyEmployee(Employee newEmployee) {
		if(!controlEmployee(newEmployee).isEmpty()) {
			Employee oldEmployee = controlEmployee(newEmployee).get(controlEmployee(newEmployee).size() - UNIT);
			DataImpl.getInstance().getEmployees().remove(oldEmployee);
			DataImpl.getInstance().getEmployees().add(newEmployee);
			return Status.ALL_RIGHT;
		}
		return Status.EMPLOYEE_NOT_FOUND;
	}

	@Override
	public Status addEmployee(Employee employee) {

		if(modifyEmployee(employee).equals(Status.EMPLOYEE_NOT_FOUND)) {
			DataImpl.getInstance().getEmployees().add(employee);
		}
		return Status.ALL_RIGHT;
	}

	@Override
	public Status dismissEmployee(Employee employee) {
		if(DataImpl.getInstance().getEmployees().contains(employee)) {
			DataImpl.getInstance().getEmployees().remove(employee);
			return Status.ALL_RIGHT;
		}
		return Status.EMPLOYEE_NOT_FOUND;
	}
}
