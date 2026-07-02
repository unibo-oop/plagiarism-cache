package org.hsm.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hsm.controller.simulator.Simulator;
import org.hsm.controller.simulator.SimulatorImpl;
import org.hsm.model.plant.Plant;
import org.hsm.model.plant.PlantImpl;
import org.hsm.model.plant.PlantModel;
import org.hsm.model.util.IDmanager;

/**
 * implementation of the Greenhouse interface.
 */
public class GreenhouseImpl implements Greenhouse, Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1132454764370517715L;

    private static final int CMQ_TO_MQ = 10000; // cm² to m²

    private final Map<Integer, Plant> plantMap = new HashMap<>();
    private final IDmanager productID = new IDmanager();
    private final String name;
    private final int size;
    private double costGreenhouse;
    private final GreenHouseType type;
    private int updateCount;

    private final Simulator simulator = new SimulatorImpl();

    /**
     * @param name
     *            name of the greenhouse
     * @param size
     *            size of the greenhouse in m³
     * @param cost
     *            cost of the greenhouse
     * @param t
     *            Type of greenhouse
     * @exception IllegalArgumentException
     *                throw if greenhouse already exist
     */
    public GreenhouseImpl(final String name, final int size, final int cost, final GreenHouseType t)
            throws IllegalArgumentException {
        super();
        if (name.equals("")) {
            throw new IllegalArgumentException();
        }
        this.name = name;
        this.size = size;
        this.setCost(cost);
        this.type = t;
        this.updateCount = 0;
    }

    /**
     * This method add some plants to the Greenhouse.
     *
     * @param n
     *            number of plants
     * @param plant
     *            an object of type Plant
     * @return return id plant
     *
     */
    @Override
    public int addPlant(final PlantModel model, final int cost) throws IllegalStateException {
        if ((model.getSize() / CMQ_TO_MQ) <= getFreeSize()) {
            final int tmp = productID.getID();
            this.plantMap.put(tmp, new PlantImpl(model, cost));
            return tmp;
        } else {
            throw new IllegalStateException();
        }
    }

    /**
     * Delete plant with the ID provided in input.
     *
     * @param id
     *            Identifier for the plant
     */
    @Override
    public void delPlant(final int id) {
        this.plantMap.remove(id);
    }

    /**
     * Delete all plants of the same type of plant provided in input.
     *
     * @param plant
     *            type of plant to be delete
     */
    @Override
    public void delPlants(final PlantModel plant) {
        final Set<Integer> set = new HashSet<>();
        for (final Map.Entry<Integer, Plant> elem : this.plantMap.entrySet()) {
            if (elem.getValue().getModel().equals(plant)) {
                set.add(elem.getKey());
            }
        }
        set.stream().forEach(i -> {
            this.plantMap.remove(i);
        });
    }

    @Override
    public Map<Integer, Plant> getPlants() {
        return plantMap;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public double getSize() {
        return size;
    }

    @Override
    public double getFreeSize() {
        double tmp = 0.0;
        if (!this.plantMap.isEmpty()) {
            for (final Map.Entry<Integer, Plant> elem : this.plantMap.entrySet()) {
                tmp += elem.getValue().getModel().getSize();
            }
            return this.size - (tmp / CMQ_TO_MQ);
        } else {
            return this.size;
        }
    }

    @Override
    public double getCost() {
        return (costGreenhouse / 100);
    }

    @Override
    public void setCost(final double cost) {
        this.costGreenhouse = cost;
    }

    @Override
    public GreenHouseType getType() {
        return this.type;
    }

    @Override
    public double getOccSize() {
        return size - getFreeSize();
    }

    @Override
    public int getNumberOfPlants() {
        return plantMap.size();
    }

    @Override
    public Map<String, Integer> getCompositionByNumber() {
        final Map<String, Integer> tmpMap = new HashMap<>();
        this.plantMap.values().stream().forEach(i -> {
            if (tmpMap.containsKey(i.getModel().getBotanicalName())) {
                tmpMap.put(i.getModel().getBotanicalName(), tmpMap.get(i.getModel().getBotanicalName()) + 1);
            } else {
                tmpMap.put(i.getModel().getBotanicalName(), 1);
            }

        });

        return tmpMap;
    }

    @Override
    public Map<String, Double> getCompositionByOccupiedSpace() {
        final Map<String, Double> tmpMap = new HashMap<>();
        this.plantMap.values().stream().forEach(i -> {
            if (tmpMap.containsKey(i.getModel().getBotanicalName())) {
                tmpMap.put(i.getModel().getBotanicalName(),
                        tmpMap.get(i.getModel().getBotanicalName()) + i.getModel().getSize());
            } else {
                tmpMap.put(i.getModel().getBotanicalName(), i.getModel().getSize());
            }

        });

        return tmpMap;
    }

    @Override
    public double totalCost() {
        return this.getCost() + this.plantMap.values().stream().mapToDouble(i -> i.getCost()).sum();
    }

    @Override
    public void incrementCounter() {
        this.updateCount++;
    }

    @Override
    public int getUpdateCounter() {
        return this.updateCount;
    }

    @Override
    public List<Double> getSimulatedWaterConsuption() {
        return this.simulator.getSimulatedWaterConsuption();
    }

    @Override
    public List<Double> getSimulatedPlantGrow() {
        return this.simulator.getSimulatedPlantGrow();
    }

    @Override
    public List<Double> getRealWaterConsuption() {
        return this.simulator.getRealWaterConsuption();
    }

    @Override
    public List<Double> getRealPlantGrow() {
        return this.simulator.getRealPlantGrow();
    }

}
