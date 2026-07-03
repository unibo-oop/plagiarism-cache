package controller;

import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;

import model.Implementations.CultivationImplementation;
import model.Implementations.InvoiceImplementation;
import model.Implementations.Pair;
import model.Implementations.ShiftImplementation;
import model.Implementations.State;
import model.Implementations.SupplyImplementation;
import model.Interfaces.CultivationModel;
import model.Interfaces.InvoiceModel;
import model.Interfaces.ShiftModel;
import model.Interfaces.SupplyModel;
import model.core.*;

public class Controller implements ControllerModel {
	private static Controller controller;
	private Model model = new ModelImplementation();
	private CultivationsModel cultivation = new CultivationsImplementation();
	private InvoicesModel invoices = new InvoicesImplementation();
	private ShiftsModel shifts = new ShiftsImplementation();
	private SuppliesModel supplies = new SuppliesImplementation();
	private StreamModel<Model> sModel;
	private StreamModel<CultivationsModel> sCultivation;
	private StreamModel<InvoicesModel> sInvoices;
	private StreamModel<ShiftsModel> sShifts;
	private StreamModel<SuppliesModel> sSupply;

	private Controller() {
		this.sModel = new StreamImplementation<>();
		this.sCultivation = new StreamImplementation<>();
		this.sInvoices = new StreamImplementation<>();
		this.sShifts = new StreamImplementation<>();
		this.sSupply = new StreamImplementation<>();
		this.loadData();
	}

	public static Controller getController() {
		if (controller == null) {
			controller = new Controller();
		}
		return controller;
	}

	@Override
	public void loadData() {
		if (this.sModel.readFile("model.txt") instanceof Model)
			this.model = this.sModel.readFile("model.txt");
		if (this.sCultivation.readFile("cultivations.txt") instanceof CultivationsModel)
			this.cultivation = this.sCultivation.readFile("cultivations.txt");
		if (this.sInvoices.readFile("invoices.txt") instanceof InvoicesModel)
			this.invoices = this.sInvoices.readFile("invoices.txt");
		if (this.sShifts.readFile("shifts.txt") instanceof ShiftsModel)
			this.shifts = this.sShifts.readFile("shifts.txt");
		if (this.sSupply.readFile("supplies.txt") instanceof SuppliesModel)
			this.supplies = this.sSupply.readFile("supplies.txt");
	}
	
	@Override
	public void saveData() {
		this.sModel.saveFile("model.txt", this.model);
		this.sCultivation.saveFile("cultivations.txt", this.cultivation);
		this.sInvoices.saveFile("invoices.txt", this.invoices);
		this.sShifts.saveFile("shifts.txt", this.shifts);
		this.sSupply.saveFile("supplies.txt", this.supplies);
	}

	@Override
	public List<CultivationModel> getCultivations() {
		return this.cultivation.getCultivations();
	}
	
	public CultivationModel getCultivation(String fieldID, String supply){
		return this.cultivation.getCultivation(fieldID, supply);
	}

	@Override
	public List<Pair<Integer, InvoiceModel>> getInvoices(Calendar data) {
		return this.getInvoices(data);
	}

	@Override
	public List<ShiftModel> getShifts(String CF) {
		return this.shifts.getShifts(this.model.getEmployee(CF));
	}

	@Override
	public List<SupplyModel> getSupplies() {
		return this.supplies.getSupplies();
	}
	
	@Override
	public Double getSalary(String cf, Calendar month, Double pricePerH) {
		if(pricePerH<0){
			throw new InputMismatchException();
		}
		return SalaryUtil.getSalary(this.shifts.getShifts(), this.model.getEmployee(cf), month, pricePerH);
	}
	
	@Override
	public Integer getSalaryMinutes(String cf, Calendar month){
		return SalaryUtil.getMinutes(this.shifts.getShifts(), this.model.getEmployee(cf), month);
	}

	@Override
	public Model getModel() {
		return this.model;
	}

	@Override
	public void addCultivation(Calendar startDate, int nOfPlants, String supply, String fieldID, String annotation,
			State state) {
		this.cultivation.addCultivation(new CultivationImplementation(startDate, nOfPlants,
				this.supplies.getSupply(supply), this.model.getField(fieldID), annotation, state));
	}

	@Override
	public void addInvoice(String customer, Calendar data, Map<CultivationModel, Pair<Integer, Double>> products) {
		this.invoices.addInvoice(new InvoiceImplementation(this.model.getCustomer(customer), data, products));
	}

	@Override
	public void addSupply(String supplier, String plant, String fornitureID, String description, int nPlants) {
		this.supplies.addSupply(new SupplyImplementation(this.model.getSupplier(supplier), this.model.getPlant(plant),
				fornitureID, description, nPlants));
	}

	@Override
	public void addShift(Calendar data, Calendar start, Calendar end, String description, String employeeCF) {
		if(start.getTime().compareTo(end.getTime())>0){
			throw new IllegalArgumentException("end date before start date");
		}
		this.shifts.addShift(new ShiftImplementation(data, start, end, description, this.model.getEmployee(employeeCF)));
	}

	@Override
	public void addTreatment(String supply, String fieldID, String phytosanitary, Calendar date, String description) throws Exception {
		this.cultivation.getCultivation(fieldID, supply).addTreatment(this.model.getPhytosanitary(phytosanitary), date, description);
	}

	@Override
	public void addToModel(ModelParam param, Object... objects) {
		switch (param) {
		case CUSTOMER:
			this.model.addCustomer((String)objects[0], (String)objects[1], (String)objects[2]);
			break;
		case EMPLOYEES:
			this.model.addEmployee((String)objects[0], (String)objects[1], (String)objects[2],(String)objects[3]);
			break;
		case FIELD:
			this.model.addField((String)objects[0], (Integer)objects[1], (Integer)objects[2]);
			break;
		case PHYTOSANITARY:
			this.model.addPhytosanitary((String)objects[0], (String)objects[1], (String)objects[2], (Integer)objects[3]);
			break;
		case PLANT:
			this.model.addPlant((String)objects[0], (String)objects[1],(Integer)objects[2]);
			break;
		case SUPPLIER:
			this.model.addSupplier((String)objects[0], (String)objects[1], (String)objects[2]);
			break;
		default:
			break;
		}
	}

	@Override
	public InvoiceModel getInvoice(String customer, Calendar year, Integer invoiceNumber) {
		return this.invoices.getInvoice(this.model.getCustomer(customer), year, invoiceNumber);
	}

	@Override
	public List<Pair<Integer, InvoiceModel>> getInvoice(String customer) {
		return this.invoices.getInvoiceFromCustomer(this.getModel().getCustomer(customer));
	}


}
