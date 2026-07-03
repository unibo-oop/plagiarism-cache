package vendingmachine;

import product.ProductArchetype;

public interface SlotInterface {

  void resetProd(ProductArchetype prod);

  /**
   * Il metodo permette di riposizionare lo slot nello scaffale.
   * 
   * @param pos
   *          -> nuova posizione dello slot nello scaffale
   */
  void resetPos(Integer pos);

  /**
   * Il metodo permette di modificare la quantità di prodotto nello scaffale.
   * 
   * @param qt
   *          -> nuova quantià del prodotto
   */
  void resetQt(Integer qt);

  /**
   * Il metodo permette di impostare il costo del prodotto presente nello slot.
   * 
   * @param cost
   *          -> costo del prodotto nello slot
   */
  void resetCost(Float cost);

  /**
   * Il metodo permette di sapere qual'è il prodotto presente in questo slot.
   * 
   * @return -> restituisce il prodotto presente nello slot
   */
  ProductArchetype getProd();

  /**
   * Il metodo permette di conoscere la posizione dello slot nello scaffale.
   * 
   * @return -> restituisce la posizione dello slot
   */
  Integer getPos();

  /**
   * Il metodo permette di conoscere la quantità di prodotto presente nello slot.
   * 
   * @return -> restituisce la quantità di merce presente nello slot
   */
  Integer getQt();

  /**
   * Il metodo permette di conoscere il prezzo del prodotto presente nello slot.
   * 
   * @return -> restituisce il prezzo del prodotto presente nello slot
   */
  Float getCost();

}
