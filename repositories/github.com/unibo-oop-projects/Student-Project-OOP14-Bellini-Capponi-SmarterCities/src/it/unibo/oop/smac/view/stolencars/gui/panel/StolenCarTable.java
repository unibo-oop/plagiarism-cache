package it.unibo.oop.smac.view.stolencars.gui.panel;

import it.unibo.oop.smac.datatypes.IStolenCar;

import java.util.LinkedList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

/**
 * Classe che implementa un {@link AbstractTableModel} che visualizza i dati delle auto dichiarate
 * come rubate.
 * 
 * @author Francesco Capponi
 */
public class StolenCarTable extends AbstractTableModel {

  private static final long serialVersionUID = 6581624902840366368L;

  /**
   * Lista delle auto dichiarate come rubate.
   */
  private List<IStolenCar> stolenCarList = new LinkedList<>();
  /**
   * Nomi delle colonne della tabella.
   */
  private static final String[] COLUMN_NAMES = { "License Plate", "Insertion Date" };

  /**
   * Metodo che permette l'aggiornamento della tabella con nuovi dati.
   * 
   * @param stolenCars
   *          La lista di {@link IStolenCar} da visualizzare.
   */
  public void updateList(final List<IStolenCar> stolenCars) {
    this.stolenCarList = stolenCars;
    final int rowCount = getRowCount();
    fireTableRowsInserted(rowCount, rowCount);
  }

  /**
   * Metodo che restituisce il numero di righe della tabella basandosi sulla lista di dati di cui si
   * compone la tabella stessa.
   * 
   * @return Il numero di righe della tabella.
   */
  @Override
  public int getRowCount() {
    return stolenCarList.size();
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
        return this.stolenCarList.get(row).getLicensePlate();
      case 1:
        // colonna relativa alla data di avvistamento
        return this.stolenCarList.get(row).getInsertionDate();
      default:
        return null;
    }
  }

}
