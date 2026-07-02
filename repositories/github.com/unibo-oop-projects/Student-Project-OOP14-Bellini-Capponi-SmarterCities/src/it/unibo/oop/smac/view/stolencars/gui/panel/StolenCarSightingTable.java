package it.unibo.oop.smac.view.stolencars.gui.panel;

import it.unibo.oop.smac.database.model.StreetObserverNotValidException;
import it.unibo.oop.smac.datatypes.ISighting;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Classe che implementa un {@link AbstractTableModel} che visualizza i dati degli avvistamenti
 * delle auto rubate.
 * 
 * @author Francesco Capponi
 */
public class StolenCarSightingTable extends AbstractTableModel {

  private static final long serialVersionUID = 6581624902840366368L;

  /**
   * Logger della classe
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(StolenCarSightingTable.class);
  /**
   * Nomi delle colonne della tabella.
   */
  private static final String[] COLUMN_NAMES = { "License Plate", "Seen at", "Location" };

  /**
   * Lista di ISighting che devono essere visualizzati.
   */
  private final List<ISighting> sightings;

  /**
   * Costruttore che inizializza una tabella vuota.
   */
  public StolenCarSightingTable() {
    super();
    sightings = new ArrayList<ISighting>();
  }

  /**
   * Metodo che permette l'aggiunta di un nuovo oggetto {@link ISighting} alla lista.
   * 
   * @param sighting
   *          L'{@link ISighting} da aggiungere.
   */
  public void insertSighting(final ISighting sighting) {
    this.sightings.add(sighting);
    this.fireTableDataChanged();
  }

  /**
   * Metodo che restituisce il numero di righe della tabella basandosi sulla lista di dati di cui si
   * compone la tabella stessa.
   * 
   * @return Il numero di righe della tabella.
   */
  @Override
  public int getRowCount() {
    return sightings.size();
  }

  /**
   * Metodo che restituisce il numero di colonne della tabella.
   * 
   * @return Il numero di colonne della tabella.
   */
  @Override
  public int getColumnCount() {
    return COLUMN_NAMES.length;
  }

  /**
   * Restituisce il nome della colonna desiderata.
   * 
   * @param col
   *          Numero di colonna di cui si richiede il nome.
   * @return Una stringa che contiene il nome di default della <code>colonna</code>
   */
  @Override
  public String getColumnName(final int col) {
    return COLUMN_NAMES[col];
  }

  /**
   * Metodo che impedisce la modifica da parte dell'utente di qualsiasi cella della tabella.
   */
  @Override
  public boolean isCellEditable(final int row, final int col) {
    return false;
  }

  /**
   * Restituisc il valore della casella all'index <code>col</code> e <code>row</code>.
   *
   * @param row
   *          Riga del valore richiesto.
   * @param col
   *          Colonna del valore richiesto.
   * @return L'oggetto corrispondente ad una determinata riga-colonna.
   */
  @Override
  public Object getValueAt(final int row, final int col) {
    switch (col) {
      case 0:
        // colonna relativa alla license plate
        return this.sightings.get(row).getLicensePlate();
      case 1:
        // colonna relativa alla data di avvistamento
        return this.sightings.get(row).getDate();
      case 2:
        // colonna relativa al luogo dell'avvistamento
        try {
          return this.sightings.get(row).getStreetObserver().getCoordinates();
        } catch (StreetObserverNotValidException e) {
          LOGGER.error("The Street Observer is corrupted ", e);
          return null;
        }
      default:
        return null;
    }
  }

}
