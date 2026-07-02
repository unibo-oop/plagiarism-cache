package it.unibo.oop.smac.database;

import it.unibo.oop.smac.datatypes.IStolenCar;
import it.unibo.oop.smac.datatypes.LicensePlate;
import it.unibo.oop.smac.datatypes.StolenCar;

import java.util.Date;

import javax.management.InvalidAttributeValueException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Classe che implementa un'{@link IStolenCar} da salvare nel database.
 * 
 * @author Francesco Capponi
 */
@DatabaseTable(tableName = "StolenCar")
public class StolenCarRow implements IStolenCar {

  /**
   * Logger della classe
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(StolenCarRow.class);

  /**
   * Nome del campo contenente la targa.
   */
  public static final String LICENSEPLATE_FIELD_NAME = "licensePlate";

  /**
   * Nome del campo contenente la data di inserimento.
   */
  public static final String DATAINSERIMENTO_FIELD_NAME = "insertionDate";

  @DatabaseField(id = true, columnName = LICENSEPLATE_FIELD_NAME, dataType = DataType.STRING)
  private String licensePlate;

  @DatabaseField(columnName = DATAINSERIMENTO_FIELD_NAME, canBeNull = false)
  private final Date dataInserimento;

  StolenCarRow() {
    this(new LicensePlate());
  }

  /**
   * Costruttore pubblico che prende come parametro la targa dell'auto rubata.
   * 
   * @param plate
   *          La targa dell'auto rubata da inserire.
   */
  public StolenCarRow(final LicensePlate plate) {
    setLicensePlate(plate);
    this.dataInserimento = new Date();
  }

  /**
   * Costruttore pubblico che prende come parametro l'auto rubata.
   * 
   * @param stolenCar
   *          L'auto rubata da inserire.
   */
  public StolenCarRow(final StolenCar stolenCar) {
    setLicensePlate(stolenCar.getLicensePlate());
    this.dataInserimento = stolenCar.getInsertionDate();
  }

  /**
   * Restituisce una copia della targa.
   * 
   * @return Una {@link LicensePlate} contenente una copia della targa dell'auto rubata.
   */
  @Override
  public LicensePlate getLicensePlate() {
    LicensePlate response = null;
    try {
      response = new LicensePlate(licensePlate);
    } catch (InvalidAttributeValueException e) {
      // non può succede visto che se viene creata una copia difensiva di
      // un oggetto già esistente, e quindi di cui è stato convalidato il
      // valore
      LOGGER.error("How can a valid license plate generate a invalid license plate?", e);
    }
    return response;
  }

  /**
   * Restituisce la data di inserimento.
   * 
   * @return Una {@link Date} contenente la data di inserimento.
   */
  @Override
  public Date getInsertionDate() {
    return new Date(dataInserimento.getTime());
  }

  /**
   * Metodo utilizzato per settare la targa.
   * 
   * @param plate
   *          La {@link LicensePlate} da settare.
   */
  public void setLicensePlate(final LicensePlate plate) {
    this.licensePlate = plate.toString();
  }

}