package product;

public interface ProductNonPerishableInterface {

  /**
   * Il metodo permette di impostare l'archetipo del prodotto.
   * 
   * @param prod
   *          -> prodotto archetipo
   */
  void setProduct(ProductAchetypeInterface prod);

  /**
   * Il metodo permette di associare il prodotto ad un archetipo.
   * 
   * @param name
   *          -> nome
   * @param desc
   *          -> descrizione
   * @param dim
   *          -> dimensione
   */
  void setProduct(String name, String desc, Integer dim);

  /**
   * Il metodo permette di ricevere l'archetipo del prodotto.
   * 
   * @return -> prodotto archetipo
   */
  ProductArchetype getProduct();

  /**
   * Il metodo permette di impostare il otto di produzione.
   * 
   * @param batch
   *          -> lotto di produzione
   */
  void setBatch(String batch);

  /**
   * Il metodo permette di ricevere il lotto di produzione.
   * 
   * @return -> lotto di produzione
   */
  String getBatch();
}
