package controller;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.google.common.base.CaseFormat;

import model.Daisy;
import model.Employee;
import model.GenericPT;
import model.Lily;
import view.PlantingGeneratorView;
import model.PlantImpl;
import model.PlantingImpl;
import model.Rose;
import model.Sunflower;
import model.Tulip;

/**
 * This class implements {@link PlantingGeneratorController}.
 *
 */
public class PlantingGeneratorControllerImpl implements PlantingGeneratorController, Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 4903440897848166282L;
    private final PlantingGeneratorView view;
    private PlantImpl selectedPlant;

    private final List<Employee> empl;
    private Optional<Employee> selectedEmployee;

    private PlantingImpl planting;
    private LocalDate from;
    private LocalDate to;
    private final int posFree;
    private int pos;

    private Rose rose;
    private Sunflower sunflower;
    private Tulip tulip;
    private Lily lily;
    private GenericPT generic;

    /**
     * A simple builder for {@link PlantingGeneratorControllerImpl}.
     * 
     * @param posFree
     *      first free position
     */
    public PlantingGeneratorControllerImpl(final int posFree) {
        this.posFree = posFree;
        this.from = LocalDate.now();
        this.selectedEmployee = Optional.empty();
        this.empl = EmployeeControllerImpl.getInstance().getAllEmployee();
        // make all that i do effectives on {@link PlantingGeneratorView}
        this.view = new PlantingGeneratorView(this);
        this.view.init();
    }

    @Override
    public void updatePlant(final int pos) {
        this.pos = pos;
        this.updatePlanting();
    }

    @Override
    public void updatePlanting() {
        final Employee empl = this.selectedEmployee.orElse(null);
        switch(this.pos) {
            case 0: rose = new Rose(posFree);  this.planting = new PlantingImpl(rose, empl, this.from, this.to); break;
            case 1: sunflower = new Sunflower(posFree); this.planting = new PlantingImpl(sunflower, empl, this.from, this.to); break;
            case 2: tulip = new Tulip(posFree); this.planting = new PlantingImpl(tulip, empl, this.from, this.to); break;
            case 3: lily = new Lily(posFree); this.planting = new PlantingImpl(lily, empl, this.from, this.to); break;
            case 4: generic = new GenericPT(posFree); this.planting = new PlantingImpl(generic, empl, this.from, this.to); break;
            default: break;
        }
    }

    @Override
    public void updateDate(final LocalDate to) {
        this.to = to;
        this.updatePlanting();
    }

    @Override
    public void updateEmployee(final int pos) {
        this.selectedEmployee = Optional.of(this.empl.get(pos));
        this.updatePlanting();
    }

    @Override
    public void confirm() {
        switch(this.pos) {
            case 0: GreenhouseControllerImpl.getInstance().getPlantingManager().addPlanting(rose, this.planting); 
                    GreenhouseControllerImpl.getInstance().addAPlant(planting);
                    break;
            case 1: GreenhouseControllerImpl.getInstance().getPlantingManager().addPlanting(sunflower, this.planting); 
                    GreenhouseControllerImpl.getInstance().addAPlant(planting);
                    break;
            case 2: GreenhouseControllerImpl.getInstance().getPlantingManager().addPlanting(tulip, this.planting); 
                    GreenhouseControllerImpl.getInstance().addAPlant(planting);
                    break;
            case 3: GreenhouseControllerImpl.getInstance().getPlantingManager().addPlanting(lily, this.planting); 
                    GreenhouseControllerImpl.getInstance().addAPlant(planting);
                    break;
            case 4: GreenhouseControllerImpl.getInstance().getPlantingManager().addPlanting(generic, this.planting); 
                    GreenhouseControllerImpl.getInstance().addAPlant(planting);
                    break;
            default: break;
        }
        this.view.close();
    }
}
