package test;

import static org.junit.Assert.assertTrue;
import it.unibo.oop.smac.database.model.StolenCarModelDatabase;
import it.unibo.oop.smac.datatypes.LicensePlate;
import it.unibo.oop.smac.datatypes.StolenCar;
import it.unibo.oop.smac.model.IStolenCarModel;

import javax.management.InvalidAttributeValueException;

import org.junit.Test;

/**
 * Classe che controlla l'interfaccia esposta da {@link ModelStolenCarsTest}.
 * 
 * @author Francesco Capponi
 */
public class ModelStolenCarsTest {

  private static final String VALID_LICENSEPLATE = "TE355TT";
  private static final String INVALID_LICENSEPLATE = "NONCONFORME";

  /**
   * Controlla che la creazione di un oggetto {@link LicensePlate} con una targa conforme non
   * restituisca eccezioni.
   * 
   * @exception Exception
   *              quando viene restituita una exception, fallisce il test
   */
  @Test
  public void testNewStolenCarWithValidLicensePlate() throws Exception {
    new StolenCar.Builder().licensePlate(VALID_LICENSEPLATE);

  }

  /**
   * Controlla che la creazione di un oggetto {@link StolenCar} con una targa NON conforme
   * restituisca eccezioni.
   * 
   * @exception Exception
   *              necessaria l'exception per il successo del test
   */
  @Test(expected = InvalidAttributeValueException.class)
  public void testNewStolenCarWithInvalidLicensePlate() throws Exception {
    new StolenCar.Builder().licensePlate(INVALID_LICENSEPLATE);
  }

  /**
   * Controlla che la creazione di un oggetto {@link StolenCar} con una targa senza tutti i
   * parametri settati dia errore.
   * 
   * @exception Exception
   *              necessaria l'exception per il successo del test
   */
  @Test(expected = IllegalArgumentException.class)
  public void testStolenCarBuilderInvalidParameter() throws Exception {
    new StolenCar.Builder().licensePlate(VALID_LICENSEPLATE).build();
    new StolenCar.Builder().insertionDateNow().build();
  }

  /**
   * Controlla che la creazione di un oggetto {@link StolenCar} con una targa con tutti i parametri
   * settati NON dia errore.
   * 
   * @exception Exception
   *              quando viene restituita una exception, fallisce il test
   */
  @Test
  public void testStolenCarBuildervalidParameter() throws Exception {
    new StolenCar.Builder().licensePlate(VALID_LICENSEPLATE).insertionDateNow().build();
  }

  /**
   * Controlla che una valida {@link StolenCar} venga aggiunta con successo.
   * 
   * @exception Exception
   *              quando viene restituita una exception, fallisce il test
   */
  @Test
  public void testNewStolenCar() throws Exception {
    final IStolenCarModel modelStolenCars = StolenCarModelDatabase.getInstance();

    final StolenCar stolenCar = new StolenCar.Builder().licensePlate(VALID_LICENSEPLATE)
        .insertionDateNow().build();

    modelStolenCars.addNewStolenCar(stolenCar);
    assertTrue(modelStolenCars.checkStolenPlate(new LicensePlate(VALID_LICENSEPLATE)));

  }

  /**
   * Controlla che venga restituita la lista delle car aggiunte.
   * 
   * @exception Exception
   *              quando viene restituita una exception, fallisce il test
   */
  @Test
  public void testGetStolenCarsInfoList() throws Exception {
    final IStolenCarModel modelStolenCars = StolenCarModelDatabase.getInstance();

    final StolenCar stolenCar = new StolenCar.Builder().licensePlate(VALID_LICENSEPLATE)
        .insertionDateNow().build();

    modelStolenCars.addNewStolenCar(stolenCar);
    assertTrue(modelStolenCars.getStolenCarsInfoList().size() > 0);

  }
}
