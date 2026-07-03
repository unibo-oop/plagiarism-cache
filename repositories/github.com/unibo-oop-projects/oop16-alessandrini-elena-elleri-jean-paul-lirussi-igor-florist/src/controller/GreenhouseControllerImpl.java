package controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import model.Greenhouse;
import model.Pair;
import model.Plant;
import model.PlantImpl;
import model.PlantingImpl;
import model.PlantingManager;
import model.PlantingManagerImpl;
import view.GreenhouseView;
import view.GreenhouseViewImpl;
import static java.util.stream.Collectors.toList;

/**
 * This class implements {@link GreenhouseController}.
 *
 */
public final class GreenhouseControllerImpl implements GreenhouseController {

    private static final int MAXNUMPLANT = 40;
    private static final String PATH = System.getProperty("user.home")
            + System.getProperty("file.separator")
            + ".thebloomingflorist"
            + System.getProperty("file.separator")
            + "planting.dat";

    private static final GreenhouseController SINGLETON = new GreenhouseControllerImpl();

    private int numPlantTot = 0;
    private GreenhouseViewImpl view;
    private final PlantingManagerImpl plantingM;
    private Greenhouse greenhouse;
    private Optional<PlantImpl> select;
    private LocalDate from;
    private LocalDate to;

    /**
     * Builder without parameters.
     * 
     */
    public GreenhouseControllerImpl() {
        PlantingManagerImpl model = null;
        try {
            model = load();
            System.out.println("planting.dat was loaded");
        } catch (final IOException | ClassNotFoundException e) {
            System.out.println("planting.dat can't be loaded");
        }
        this.view = new GreenhouseViewImpl(this);
        this.plantingM = Optional.ofNullable(model).orElse(new PlantingManagerImpl());
        this.select = Optional.empty();
    }

    /**
     * This method load planting.dat
     * 
     * @return {@link PlantingManager}
     * @throws IOException,
     *             ClassNotFoundException
     */
    private static PlantingManagerImpl load() throws IOException, ClassNotFoundException {
        try (ObjectInputStream input = new ObjectInputStream(
                new BufferedInputStream(new FileInputStream(PATH)));) {
            return (PlantingManagerImpl) input.readObject();
        }
    }

    /**
     * This method store the {@link PlantingManager} passed on planting.dat file
     * 
     * @param toWrite
     *            {@link PlantingManager} stored
     * @throws IOException
     */
    private static void store(final PlantingManagerImpl toWrite) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(PATH)))) {
            out.writeObject(toWrite);
        }
    }

    /**
     * 
     * 
     */
    public void openPlantingGenerator() {
        int posFree;
        try {
            posFree = this.greenhouse.minPosFree();
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            posFree = 0;
        }
        new PlantingGeneratorControllerImpl(posFree);
    }

    @Override
    public List<PlantImpl> getAllPlant() {
        if (!this.greenhouse.isEmpty()) {
            try {
                return this.plantingM.getPlantsInMap().stream().collect(toList());
            } catch (final NullPointerException e) {
                System.out.println("Error in getAllPlant");
            }
        }
        return null;
    }

    @Override
    public void selectDate(final Date date) {
        this.from = LocalDate.now();
        this.to = DateUtility.convert(date);
    }

    @Override
    public void selectPlant(final int pos) {
        this.select = Optional.of(this.plantingM.getPlantsInMap().get(pos));
    }

    @Override
    public void addAPlant(final PlantingImpl p) {
        if (this.isGreenhouseFull()) {
            this.view.showError(this.view, "The Greenhouse is full of plant");
        } else {
            try {
                //store(this.plantingM);
                this.view.updateView();
                //this.view.createPlantButton(p, numPlantTot);
                this.numPlantTot++;
            } /*catch (IOException e) {
                this.view.showError(this.view, "File planting.dat not found");
            } */ catch (IllegalStateException e) {
                this.view.showError(this.view, "Illegal state exception");
            }
        }
    }

    @Override
    public void removePlant(final PlantingImpl p) {
        try {
            //store(this.plantingM);
            this.numPlantTot--;
            this.view.updateView();
        } /* catch (IOException e) {
            this.view.showError(this.view, "File planting.dat not found");
        } */ catch (IllegalStateException e) {
            this.view.showError(this.view, "Illegal state exception");
        }
    }

    @SuppressWarnings("null")
    @Override
    public List<String> getAllPlantAsString() {
        final List<PlantingImpl> list = this.plantingM
                .getAll()
                .getOrDefault(this.select.get(), Collections.emptySet())
                .stream()
                .collect(toList());

        List<String> out = new ArrayList<>();
        Plant plant;
        Pair<LocalDate, LocalDate> dates = null;

        for (PlantingImpl e : list) {
            plant = e.getPlant();
            from = dates.getFirst();
            to = dates.getSecond();
            out.add("" + plant.getName() + ", planted: " + from 
                    + ", to pick: " + to);
        }
        for (int i = out.size(); i <= MAXNUMPLANT; i++) {
            out.add("Empty space ");
        }
        return out;
    }

    /**
     * This method return SINGLETON.
     * 
     * @return SINGLETON
     */
    public static GreenhouseController getInstance() {
        return SINGLETON;
    }

    @Override
    public boolean isGreenhouseFull() {
        return this.numPlantTot == MAXNUMPLANT ? true : false;
    }

    @Override
    public GreenhouseView getGreenhouseView() {
        return this.view;
    }

    @Override
    public void clearAll() {
            final List<PlantingImpl> list = 
                    this.plantingM.getSummaryToPickToday()
                    .getOrDefault(this.select.get(), Collections.emptySet())
                    .stream()
                    .collect(toList());

        for (PlantingImpl p : list) {
            this.plantingM.removePlanting((PlantImpl) p.getPlant(), p);
        }

        try {
            store(this.plantingM);
            this.view.setPlantable(false);
        } catch (IOException e) {
            this.view.showError(this.view, "File planting.dat not found");
        } catch (IllegalStateException e) {
            this.view.showError(this.view, "Illegal state exception");
        }
    }

    @Override
    public PlantingManager getPlantingManager() {
        return this.plantingM;
    }

    /**
     * This method get Plants To Pick in a selected day.
     * 
     * @param date
     *      to check
     * @return
     */
    public void getPlantsToPickInDate(final LocalDate date) {
        List<PlantingImpl> list = this.plantingM.getSummaryToPickDate(date);
        new PlantsToPickTableImpl(list);

    }
}
