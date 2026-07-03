package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 *  the class which manages the plantings for every plant.
 */
public class PlantingManagerImpl implements PlantingManager, Serializable {

    /**
     * UID generated.
     */
    private static final long serialVersionUID = -7483205960031563751L;
    private final Map<PlantImpl, Set<PlantingImpl>> map;

    /**
     * initializing map.
     */
    public PlantingManagerImpl() {
        this.map = new HashMap<>();
    }

    @Override
    public Map<PlantImpl, Set<PlantingImpl>> getAll() {
        return map;
    }

    @Override
    public boolean addPlanting(final PlantImpl plant, final PlantingImpl planting) {
        if (map.containsKey(plant)) { //se la mappa contiene la pianta
            if (isPlantingAvaiable(plant, planting.getDates().getFirst(), planting.getDates().getSecond())) {
                //se si può piantare
                this.map.get(plant).add(planting);
            } else {
                throw new IllegalArgumentException("no planting available.");
            }
        } else {    //se la mappa non contiene la pianta
            final Set<PlantingImpl> setperpianta = new HashSet<>(); //nuovo set
            setperpianta.add(planting); //ci aggiungo la piantata
            this.map.put(plant, setperpianta);  //metto la nuova pianta con il set nella mappa
        }
        return true;
    }

    @Override
    public boolean removePlanting(final PlantImpl plant, final PlantingImpl planting) {
        if (map.containsKey(plant)) {
            return this.map.get(plant).remove(planting);
        }
        throw new IllegalArgumentException("no plant find where remove the planting");
    }


    @Override
    public boolean isPlantingAvaiable(final PlantImpl plant, final LocalDate from, final LocalDate to) {
        if (to.isBefore(from)) {
            throw new IllegalArgumentException("date can't end before start");
        }
        final Iterator<PlantingImpl> iterator = map.get(plant).iterator();
        while (iterator.hasNext()) {
            final PlantingImpl planting = iterator.next();
            //qui cè la planting che itera della pianta data
            //se si interseca
            if (!(planting.getDates().getFirst().isAfter(to) || planting.getDates().getSecond().isBefore(from))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isPlantingAvaiablePeriod(final PlantImpl plant, final LocalDate from, final Period period) {
        return this.isPlantingAvaiable(plant, from, from.plus(period));
    }

    @Override
    public Map<PlantImpl, Set<PlantingImpl>> getSummaryToPickToday() {
        // XXX pick today review, lambda?
        //new map, search every plant if in the set a planting ends today
        final Map<PlantImpl, Set<PlantingImpl>> picktoday = new HashMap<>();
        //ciclo le chiavi
        for (final PlantImpl key : map.keySet()) {
            final Set<PlantingImpl> setpianta = map.get(key);
            //devo iterare il set alla ricerca di una piantata che termina oggi
            //potevo utilizzare anche un while creando l'iteratore prima
            for (final Iterator<PlantingImpl> iteratore = setpianta.iterator(); iteratore.hasNext();) {
                final PlantingImpl planting = iteratore.next();
                if (planting.getDates().getSecond().equals(LocalDate.now())) {
                    picktoday.put(key, setpianta); //passa tutto il set delle piantate
                }
            }
        }
        return picktoday;
    }

    @Override
    public List<PlantingImpl> getSummaryToPickDate(final LocalDate date) {
        //new list, search for every plant if in the set a planting ends the date passed
        final List<PlantingImpl> pickdate = new LinkedList<>();
        //ciclo le chiavi
        for (final PlantImpl key : map.keySet()) {
            //ciclo nel set di ogni chiave
            for (final Iterator<PlantingImpl> iteratore = map.get(key).iterator(); iteratore.hasNext();) {
                final PlantingImpl planting = iteratore.next();
                //System.out.println("piantata: " + planting.getDates().getFirst() + " - " + planting.getDates().getSecond());
                //System.out.println("data passata: " + date);
                //System.out.println(planting.getDates().getSecond().equals(date));
                if (planting.getDates().getSecond().equals(date)) {
                    pickdate.add(planting);
                }
            }
        }
        /*if (pickdate.isEmpty()) {
            throw new IllegalArgumentException("no plantings to pick in this date!");
        }*/
        return pickdate;
    }

    @Override
    public int getNumFlowersinthemap() {
        return map.size();
    }

    @Override
    public boolean isMapEmpty() {
        return map.isEmpty();
    }

    @Override
    public List<PlantImpl> getPlantsInMap() {
        final List<PlantImpl> listapiante = new LinkedList<>();
        for (final PlantImpl key : map.keySet()) {
            listapiante.add(key);
        }
        return listapiante;
    }

    @Override
    public boolean removePlant(final PlantImpl pos) {
        this.map.remove(pos);
        return true;
    }


    @Override
    public String toString() {
        String result = "";
        for (final PlantImpl key : this.map.keySet()) {
            result = result.concat("\n>PIANTA:" + key.getName());
             final Set<PlantingImpl> setpiantate = this.map.get(key);
             for (final Iterator<PlantingImpl> iteratore = setpiantate.iterator(); iteratore.hasNext();) {
                 final PlantingImpl planting = iteratore.next();
                 result = result.concat("piantata: " + planting.getDates().getFirst() + " - " + planting.getDates().getSecond());
             }
        }
        return result;
    }

    /**
     * prints all the map in extended format.
     * very useful.
     */
    public void printExtendedMap() {
        for (final PlantImpl key : this.map.keySet()) {
            System.out.println("\nஇڰۣ—ڿڰۣ—— PIANTA:" + key.getName());
             final Set<PlantingImpl> setpiantate = this.map.get(key);
             for (final Iterator<PlantingImpl> iteratore = setpiantate.iterator(); iteratore.hasNext();) {
                 final PlantingImpl planting = iteratore.next();
                 System.out.println("piantata: " + planting.getDates().getFirst() + " - " + planting.getDates().getSecond());
             }
        }
    }

}
