package model;

import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertNotNull;


import org.junit.Test;

import model.Employee.Duty;

import java.io.File;
import java.time.LocalDate;
import java.time.Period;
import java.util.Iterator;
import java.util.List;

/**
 * class for model test v15.
 */
public class ModelTest {

    private static final int INTERO = 5; //meglio non superi i 27 (aumenterebbe il mese e non i giorni)
    private static final int INTERO2 = 9; //4 in più di intero

    private static final int ANNO = 1990; //meglio non superi i 27 (aumenterebbe il mese e non i giorni)
    private static final int ANNO2 = 1985;
    private static final int ANNO3 = 2017;
    private static final int ANNO4 = 2018;
    /**
     * Test 1.
     * test periodi trascorsi e colori
     */
    @Test
    public void test01() {
        System.out.println("\n>>Test1");
        //test funzionamento date
        final LocalDate primasemina = LocalDate.of(2017, 05, 20);
        System.out.println("Data semina: " + primasemina);

        final LocalDate now = LocalDate.now();
        System.out.println("Data oggi: " + now);

        //test periodo
        Period periodo = Period.between(primasemina, now);
        System.out.println("tempo trascorso: " + periodo.getMonths() + " mesi e " + periodo.getDays() + " giorni");
        assertEquals(periodo, primasemina.until(now));
        //stampa colori
        for (final Color c: Color.values()) {
            System.out.print(c + " ");
        }
        periodo = periodo.normalized();
        //periodo = periodo.negated();
        if (periodo.isNegative()) {
            fail("negative period");
        }
        System.out.println("\nmese attuale : " + now.getMonthValue());
        System.out.println("totale mesi trascorsi : " + periodo.toTotalMonths());
    }



    /**
     * Test 2.
     * test aggiunta e sottrazione giorni a periodi.
     */
    @Test
    public void test02() {
        System.out.println("\n>>Test2");
        //somma
        Period periodo2 = Period.ofDays(2);
        periodo2 = periodo2.plusDays(2);
        assertEquals(periodo2.plusDays(INTERO).getDays(), INTERO2);
        //sottrazione
        Period periodo3 = Period.ofDays(8);
        periodo3 = periodo3.minusDays(4);
        assertEquals(periodo3.getDays(), INTERO2 - INTERO);
        //stampe
        System.out.println("periodo2 di giorni: " + periodo2.getDays());
        System.out.println("periodo3 di giorni: " + periodo3.getDays());
    }

    /**
     * Test 3.
     * controllo proprietà tipi di fiori.
     */
    @Test
    public void test03() {
        System.out.println("\n>>Test3");
        final Rose r1 = new Rose(0);
        final Narcissus n1 = new Narcissus();
        final Carnation g1 = new Carnation();
        //controllo colori
        assertEquals(r1.getColor(), Color.RED); //rosa rossa
        assertEquals(n1.getColor(), Color.YELLOW); //narciso giallo
        //controllo condizione
        n1.setCondition(Condition.BUD);
        assertEquals(n1.getCondition(), Condition.BUD);
        //controllo acqua 
        n1.setWatered(true);
        n1.setDate(LocalDate.now().minusDays(INTERO)); //semina 5 giorni fa
        assertTrue(n1.isWatered());
        n1.setWatered(false);
        assertFalse(n1.isWatered());
        //controllo data semina e età
        assertEquals(n1.getDate(), LocalDate.now().minusDays(INTERO));
        assertEquals(n1.getAge().getDays(), INTERO);
        //stampe narciso
        System.out.println("\nNarciso: cond-water-color : " + n1.getCondition() + n1.isWatered() + n1.getColor());
        System.out.println("Narciso->semina 5 giorni fa: " + n1.getDate() + " giorni dalla semina: " + n1.getAge().getDays());
        System.out.println(("file separator: " + File.separator + " file-esempio.txt"));
        System.out.println(g1.getDescription());
    }

    /**
     * Test 4.
     * controllo persone dipendenti e guest.
     */
    @Test
    public void test04() {
        System.out.println("\n>>Test4");
        //final Narciso n1 = new Narciso();
        //stampe narciso
        //assertNotNull(n1.getDescription());
        final Employee emp1 = new Employee("gigi", "chino", "GGFCC45", "Padova", "via mont", "33187676", "gigi@gmial.ccc");
        emp1.setEmpCode(ANNO);
        emp1.setDuty(Duty.SELLER);
        final Employee emp2 = emp1;
        assertEquals(emp1, emp2);
        emp2.setEmpCode(ANNO2);
        assertEquals(emp1.getFc(), emp2.getFc());

        //stampe 
        System.out.println("\n Codice primo imp: " + emp1.getEmpCode());
        System.out.println(" Impiego primo imp: " + emp1.getDuty());
        System.out.println(" Codice secondo imp: " + emp2.getEmpCode());
    }

    /**
     * Test 5.
     * controllo builder per le guest e impiegati.
     */
    @Test
    public void test05() {
        System.out.println("\n>>Test5");
        //con builder
        final Guest guest1 = new GuestBuilder().name("marco")
                                        .surname("rossi")
                                        .cf("MP993")
                                        .date(LocalDate.of(ANNO2, INTERO, INTERO2))
                                        .build();

        final Employee employee1 = new EmployeeBuilder().name("maria")
                                                            .surname("rossi")
                                                            .cf("MR234")
                                                            .build();
        assertEquals(guest1.getSurname(), employee1.getSurname());
        //Stamp
        System.out.println("\n nome primo guest: " + guest1.getName());
        System.out.println(" Fisc code primo guest: " + guest1.getFc());
        System.out.println(" Età primo guest: " + guest1.getAge());
        System.out.println(" Nome primo imp: " + employee1.getName());
        System.out.println(" Età primo imp: " + employee1.getAge());

        employee1.setDuty(Duty.MANAGER);
        employee1.setEmpCode(0);

        final Employee employee2 = new EmployeeBuilder().name("franca")
                .surname("coni")
                .cf("FRC34")
                .build();

        employee2.setEmpCode(INTERO2);
        employee2.setDuty(Duty.MANAGER);

        assertEquals(employee1.getDuty(), employee2.getDuty());
        assertEquals(employee1.getSurname(), guest1.getSurname());

        //Stamp
        System.out.println("\n mansione primo imp: " + employee1.getDuty());
        System.out.println(" mansione secondo imp: " + employee2.getDuty());
        System.out.println(" codice primo imp: " + employee1.getEmpCode());
        System.out.println(" codice secondo imp: " + employee2.getEmpCode());
    }

    /**
     * Test 6.
     * controllo builder per clienti.
     */
    @Test
    public void test06() {
        System.out.println("\n>>Test6");
        final Client c1 = new Client("ciro", "belli", "rle34lkda", LocalDate.of(ANNO3, INTERO, INTERO2), ANNO4);
        final Client c2 = new Client("ciro", "calli", "adskda", LocalDate.of(ANNO3, INTERO2, INTERO), ANNO4);

        assertEquals(c1.getClientCode(), c2.getClientCode());
        assertEquals(c2.getName(), c1.getName());

        final Client c3 = new ClientBuilder().name("giorgio")
                                                .surname("masini")
                                                .cf("giorserc2304")
                                                .date(LocalDate.of(ANNO3, INTERO2, INTERO))
                                                .build();
        final Client c4 = new ClientBuilder().name("Elvio")
                                                .surname("Angeli")
                                                .cf("elng454")
                                                .date(LocalDate.of(ANNO3, INTERO, INTERO))
                                                .clientcode(ANNO4)
                                                .build();

        assertEquals(c1.getClientCode(), c4.getClientCode());
        assertEquals(c3.getAge(), c2.getAge());

        //stampe
        System.out.println("\ncodice c1: " + c1.getClientCode());
        System.out.println("cognome c2: " + c3.getSurname());
        System.out.println("codice c4: " + c4.getClientCode());
        System.out.println("età c3: " + c3.getAge());
    }

    /**
     * Test 7.
     * GreenHouse.
     * test aggiunta e sottrazione fiori a Greenhouse.
     * metodi getter e setter
     */
    @Test
    public void test07() {
        System.out.println("\n>>Test7");
        final GreenhouseImpl greenH = new GreenhouseImpl();
        final GreenhouseImpl greenH2 = new GreenhouseImpl();
        greenH.setSunflowerNum(INTERO);
        greenH.setRoseNum(INTERO2);
        greenH.setTulipNum(INTERO);

        assertEquals(0, greenH2.getNumFlowers());
        assertEquals(greenH.getTulipNum(), greenH.getSunflowerNum());
        assertEquals(greenH.getSunflowerNum() - greenH.getRoseNum(), INTERO - INTERO2);

        System.out.println("\ngreen house flowers : " + greenH.getNumFlowers());
        System.out.println("green house free spaces : " + greenH.getNumFreeSpaces());

        System.out.println("\n green house sunflower flowers : " + greenH.getSunflowerNum());
        System.out.println(" green house rose flowers : " + greenH.getRoseNum());
        System.out.println(" green house Tulip flowers : " + greenH.getTulipNum());
        System.out.println(" green house generic flowers : " + greenH.getGenericPTNum());


        greenH.setAllFlowerNum(INTERO, INTERO, INTERO2, INTERO2, INTERO2);

        System.out.println("\nGreen house flowers : " + greenH.getRoseNum() + " " + greenH.getTulipNum() + " " + greenH.getSunflowerNum() + " "
                                                        + greenH.getLilyNum() + " " + greenH.getGenericPTNum());

        System.out.println(" green house number flowers : " + greenH.getNumFlowers());
        System.out.println(" green house free spaces : " + greenH.getNumFreeSpaces());

        assertEquals(greenH.getSunflowerNum(), greenH.getGenericPTNum());
        assertEquals(greenH.getRoseNum(), greenH.getTulipNum());
        assertEquals(greenH.getLilyNum(), INTERO2);

        greenH2.setSunflowerNum(INTERO);
        greenH2.setRoseNum(INTERO);
        final List<PlantImpl> serra = greenH2.getGreenHouse();
        if (greenH2.isEmpty()) {
            System.out.println("Serra vuota: " + greenH2.getNumFlowers());
        }
        for (final Iterator<PlantImpl> iteratore = serra.iterator(); iteratore.hasNext();) {
            final PlantImpl plant = iteratore.next();
            System.out.println("Pianta: " + plant.getName());
        }
    }

    /**
     * Test 8.
     * test EmployeeManager
     */
    @Test
    public void test08() {
        System.out.println("\n>>Test8");
        //creo emp
        final Employee emp1 = new Employee("Francesco", "chino", "GGFCC45", "Padova", "via mare", "33187676", "rfranchin@gmal.ccc");
        emp1.setEmpCode(ANNO);
        emp1.setDuty(Duty.SELLER);
        final Employee emp2 = new Employee("Vighi", "Cuntadin", "fssjll45", "Tarvisio", "vie taj greps", "37672346", "vighi.talcurtil@gmiao.ccc");
        emp2.setEmpCode(ANNO2);
        emp2.setDuty(Duty.FLORIST);
        final Employee emp3 = new Employee("Giacomo", "Rossi", "grois45", "Moza", "via mela", "37322346", "giac.cma@gmail.ccc");
        emp2.setEmpCode(ANNO2);
        emp2.setDuty(Duty.FLORIST);

        final EmployeeManagerImpl empman = new EmployeeManagerImpl();

        //aggiunta
        empman.add(emp2);
        empman.add(emp1);

        assertEquals(2, empman.numEmp());

        //rimozione
        empman.remove(emp2);
        assertEquals(1, empman.numEmp());

        //riaggiunta con duplicato
        empman.add(emp2);
        empman.add(emp2);
        empman.add(emp1);
        empman.add(emp3);

        assertEquals(3, empman.numEmp());

        //stampe
        System.out.println("num dipendenti: " + empman.numEmp());
        System.out.println(empman.toString());
    }

    /**
     * Test 9.
     * Planting.
     * creazione piantata: Planting
     * ottenimento pianta, chi l'ha piantata e date dalla planting
     */
    @Test
    public void test09() {
        System.out.println("\n>>Test9");
        //nuova piante
        final Rose r1 = new Rose(1);
        final Narcissus n1 = new Narcissus();
        n1.setCondition(Condition.BUD);
        n1.setWatered(true);
        n1.setDate(LocalDate.now().minusDays(INTERO)); //semina 5 giorni fa
        r1.setCondition(Condition.FLOWER);
        r1.setWatered(false);
        r1.setDate(LocalDate.now().minusDays(INTERO2)); //semina 9 giorni fa
        //nuovi piantatori
        final Employee emp1 = new EmployeeBuilder().name("fabrizio")
                .surname("verdi")
                .cf("FBVR234")
                .address("via gennaio")
                .city("come")
                .phonenumber("987987")
                .mail("fverdi@yahoo.it")
                .build();
        emp1.setDuty(Duty.FLORIST);
        final Employee emp2 = new EmployeeBuilder().name("fabia")
                .surname("bianchi")
                .cf("FBBNCH4")
                .address("via febbbraio")
                .city("come")
                .phonenumber("798798798")
                .mail("fabianchi@yahoo.it")
                .build();
        emp2.setPhonenumber("9987987");
        emp2.setDuty(Duty.FLORIST);
        //nuove planting.
        final PlantingImpl pl1 = new PlantingImpl(n1, emp1, LocalDate.of(ANNO, INTERO, INTERO), LocalDate.of(ANNO4, INTERO, INTERO2));
        final PlantingImpl pl2 = new PlantingImpl(n1, emp2, LocalDate.of(ANNO2, INTERO, INTERO), LocalDate.of(ANNO4, INTERO2, INTERO2));
        final PlantingImpl pl3 = new PlantingImpl(r1, emp2, LocalDate.of(ANNO3, INTERO, INTERO), LocalDate.of(ANNO4, INTERO2, INTERO));


        assertEquals(pl1.getPlant().getName(), pl2.getPlant().getName());
        assertEquals(pl2.getEmployee(), pl3.getEmployee());
        assertEquals(pl1.getPlant().getColor(), pl2.getPlant().getColor());
        assertEquals(pl1.getPlant().getName(), pl2.getPlant().getName());
        assertEquals(pl3.getEmployee().getDuty(), pl2.getEmployee().getDuty());

        //stampe
        System.out.println("\nPiantata 1: pianta: " + pl1.getPlant().getName());
        System.out.println("nome del fioraio: " + pl1.getEmployee().getName());
        System.out.println("mesi durata pianta: " + pl1.getPeriod().toTotalMonths());
        System.out.println("mesi pianta a ora: " + pl1.getTime().toTotalMonths());
        System.out.println("\nPiantata 2: pianta: " + pl2.getPlant().getName());
        System.out.println("nome del fioraio: " + pl2.getEmployee().getName());
        System.out.println("mesi durata pianta: " + pl2.getPeriod().toTotalMonths());
        System.out.println("mesi pianta a ora: " + pl2.getTime().toTotalMonths());
        System.out.println("\nPiantata 3: pianta: " + pl3.getPlant().getName());
        System.out.println("nome del fioraio: " + pl3.getEmployee().getName());
        System.out.println("mesi durata pianta: " + pl3.getPeriod().toTotalMonths());
        System.out.println("mesi pianta a ora: " + pl3.getTime().toTotalMonths());
    }

    /**
     * Test 10.
     * test Warehouse.
     */
    @Test
    public void test10() {
        System.out.println("\n>>Test10");
        //creazione
        final WarehouseImpl wa = new WarehouseImpl();

        assertEquals(0, wa.getFertilizerUsed());
        assertEquals(0, wa.getGenericPicked());
        assertEquals(0, wa.getTotalPlantsPicked());

        //aggiunta
        wa.addFertilizerUsed(INTERO);
        wa.addLilyPicked(INTERO2);
        wa.addRosePicked(INTERO);
        wa.addWaterUsed(INTERO2);

        assertEquals(INTERO, wa.getFertilizerUsed());
        assertEquals(INTERO2, wa.getWaterUsed());
        assertEquals(INTERO + INTERO2, wa.getTotalPlantsPicked());

        //reset
        wa.resetFertilizerUsed();
        wa.resetWaterUsed();

        assertEquals(0, wa.getWaterUsed());
        assertEquals(0, wa.getFertilizerUsed());
        assertEquals(INTERO + INTERO2, wa.getTotalPlantsPicked());

        //reinserimento
        wa.addWaterUsed(INTERO);
        assertEquals(INTERO, wa.getWaterUsed());

        //stampe
        System.out.println("magazzino: lily - " + wa.getLilyPicked());
        System.out.println("magazzino: rose - " + wa.getRosePicked());
        System.out.println("magazzino: sunflower - " + wa.getSunflowerPicked());
        System.out.println(wa.toString());
    }

    /**
     * Test 11. Finale. 
     * test Planting Manager.
     */
    @Test
    public void testFinale() {
        System.out.println("\n>>TestFinale");
        //nuova piante
        final Rose r1 = new Rose(1);
        final Narcissus n1 = new Narcissus();
        final Narcissus n2 = new Narcissus();
        n1.setCondition(Condition.BUD);
        n1.setWatered(true);
        n1.setDate(LocalDate.now().minusDays(INTERO)); //semina 5 giorni fa
        r1.setCondition(Condition.FLOWER);
        r1.setWatered(false);
        r1.setDate(LocalDate.now().minusDays(INTERO2)); //semina 9 giorni fa
        //nuovi piantatori
        final Employee emp1 = new EmployeeBuilder().name("fabrizio")
                .surname("verdi")
                .cf("FBVR234")
                .address("via gennaio")
                .city("rome")
                .phonenumber("987987")
                .mail("fverdi@yahoo.it")
                .build();
        emp1.setDuty(Duty.FLORIST);
        final Employee emp2 = new EmployeeBuilder().name("fabia")
                .surname("bianchi")
                .cf("FBBNCH4")
                .address("via febbbraio")
                .city("rome")
                .phonenumber("798798798")
                .mail("fabianchi@yahoo.it")
                .build();
        emp2.setPhonenumber("9987987");
        emp2.setDuty(Duty.FLORIST);
        //nuove planting.
        final PlantingImpl pl1 = new PlantingImpl(n1, emp1, LocalDate.of(ANNO, INTERO, INTERO), LocalDate.of(ANNO4, INTERO, INTERO2));
        final PlantingImpl pl2 = new PlantingImpl(n1, emp2, LocalDate.of(ANNO2, INTERO, INTERO), LocalDate.of(ANNO4, INTERO2, INTERO2));
        final PlantingImpl pl3 = new PlantingImpl(r1, emp2, LocalDate.of(ANNO3, INTERO2, INTERO), LocalDate.of(ANNO4, INTERO2, INTERO));
        final PlantingImpl pl4 = new PlantingImpl(n2, emp2, LocalDate.of(ANNO3, INTERO, INTERO), LocalDate.now()); //pl4 termina sempre oggi

        //PLANTING MANAGER
        final PlantingManagerImpl plantingmanager = new PlantingManagerImpl();

        //controllo vuoto
        assertTrue(plantingmanager.isMapEmpty());

        plantingmanager.addPlanting(r1, pl3);

        //controllo pieno
        assertFalse(plantingmanager.isMapEmpty());

        plantingmanager.addPlanting((PlantImpl) pl1.getPlant(), pl1);

        try {
        plantingmanager.addPlanting((PlantImpl) pl2.getPlant(), pl2);
        } catch (IllegalArgumentException e) {
            System.out.println("\npiantata sovrasctitta rifiutata! ok");
        }
        System.out.println("fiori nella mappa: " + plantingmanager.getNumFlowersinthemap());
        //controllo numero piante
        assertEquals(2, plantingmanager.getNumFlowersinthemap());
        //controllo Planting Available
        assertTrue(plantingmanager.isPlantingAvaiable(r1, LocalDate.of(ANNO2, INTERO, INTERO), LocalDate.of(ANNO, INTERO, INTERO))); //data del pl1
        assertFalse(plantingmanager.isPlantingAvaiable(r1, LocalDate.of(ANNO, INTERO, INTERO), LocalDate.of(ANNO3, INTERO2, INTERO2))); //data del pl1
        //controllo Planting Available scambio date
        try {
            plantingmanager.isPlantingAvaiable(r1, LocalDate.of(ANNO, INTERO, INTERO), LocalDate.of(ANNO2, INTERO, INTERO));
        } catch (IllegalArgumentException e) {
            System.out.println("\npiantata con date invertite rifiutata! ok");
        }
        //controllo remove
        assertTrue(plantingmanager.removePlanting((PlantImpl) pl1.getPlant(), pl1));
        //dopo aver rimosso la piantata da false
        assertFalse(plantingmanager.removePlanting((PlantImpl) pl1.getPlant(), pl1));
        assertFalse(plantingmanager.removePlanting((PlantImpl) pl1.getPlant(), pl1));
        //sono ancora 2
        assertEquals(2, plantingmanager.getNumFlowersinthemap());

        plantingmanager.removePlant((PlantImpl) pl1.getPlant());
        //sono 1
        assertEquals(1, plantingmanager.getNumFlowersinthemap());

        plantingmanager.addPlanting((PlantImpl) pl4.getPlant(), pl4);

        assertEquals(2, plantingmanager.getNumFlowersinthemap());
        assertTrue(plantingmanager.getAll().get(pl4.getPlant()).contains(pl4));
        assertEquals(1, plantingmanager.getAll().get(pl4.getPlant()).size()); //c'è solo una piantata


        //stampa tutta la mappa
        System.out.println("ALL THE MAP before");
        plantingmanager.printExtendedMap();


        //aggiungo piantata nel narciso
        plantingmanager.addPlanting(n2, pl3);
        System.out.println();

        //ristampo
        System.out.println("ALL THE MAP after");
        plantingmanager.printExtendedMap();

        assertEquals(plantingmanager.getNumFlowersinthemap(), 2);

        assertFalse(plantingmanager.getSummaryToPickDate(LocalDate.now()).isEmpty());

        System.out.println("\n piante da raccogliere oggi: " + plantingmanager.getSummaryToPickDate(LocalDate.now()).size());
    }
}
