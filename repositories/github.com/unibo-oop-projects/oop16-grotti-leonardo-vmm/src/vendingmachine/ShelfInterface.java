package vendingmachine;

import java.util.List;

import product.ProductArchetype;

public interface ShelfInterface {

  /**
   * Il metodo setta il numero di slot presenti nello scaffale.
   * 
   * @param slots
   *          -> slots
   */
  void setNumberOfSlots(Integer slots);

  /**
   * Il metodo restituisce il numero di slot presenti nello scaffale.
   * 
   * @return -> slots
   */
  Integer getNumberOfSlots();

  /**
   * Il metodo setta il numero di prodotti che possono essere messi in uno slot.
   * 
   * @param num
   *          -> numero di prodotti in uno slot
   */
  void setProdPerSlot(Integer num);

  /**
   * restituisce il numero di prodotti che possono esere messi in uno slot.
   * 
   * @return restituisce il numero massimo di prodotti in uno slot
   */
  Integer getProdPerSlot();

  /**
   * Il metoo permette di aggiungere uno slot allo scaffale.
   * 
   * @param prod
   *          -> il prodotto nello scaffale
   * @param pos
   *          -> la posizione dello slot
   * @param qt
   *          -> la quantità di prodotto attualmente presente nello slot
   */
  void addSlot(ProductArchetype prod, Integer pos, Integer qt);

  /**
   * Il metodo rimuove lo slot nella posizione index all'interno della lista.
   * 
   * @param index
   *          -> posizione dello slot all'interno della lista
   */
  void removeSlot(Integer index);

  /**
   * Il metodo permette di ottenere lo slot presente nella posizione index all'interno della lista.
   * 
   * @param index
   *          -> posizione dello slot all'interno della lista
   * @return lo slot richiesto
   */
  Slot getSlot(Integer index);

  /**
   * Il metodo restituisce tutti gli slot dello scaffale.
   * 
   * @return tutti gli slot dello scaffale
   */
  List<Slot> getAllSlot();

}
