package product;

import java.util.List;

public interface ProdArcListInterface {

  /**
   * Il metodo permette di aggiungere alla lista il prodotto.
   * 
   * @param prod
   *          -> prodotto
   */
  void addProd(ProductAchetypeInterface prod);

  /**
   * Il metodo permette di rimuovere il prodotto alla posizione indicata.
   * 
   * @param pos
   *          -> posizione
   */
  void removeProd(Integer pos);

  /**
   * Il metodo permette di rimuovere il prodotto.
   * 
   * @param prod
   *          -> prodotto
   */
  void removeProd(ProductAchetypeInterface prod);

  /**
   * Il metodo pulisce la lista.
   */
  void removeAll();

  /**
   * Il metodo permette di ricevere il prodotto nella posizione indicata.
   * 
   * @param pos
   *          -> posizione
   * @return -> prodotto
   */
  ProductAchetypeInterface getProd(Integer pos);

  /**
   * Il metodo permette di ricevere la posizione del prodotto.
   * 
   * @param prod
   *          -> prodotto
   * @return -> posizione
   */
  Integer getPos(ProductAchetypeInterface prod);

  /**
   * Il metodo permette di ottenere tutti i prodotti presenti nella lista.
   * 
   * @return -> lista dei prodotti
   */
  List<ProductArchetype> getAllProd();
}
