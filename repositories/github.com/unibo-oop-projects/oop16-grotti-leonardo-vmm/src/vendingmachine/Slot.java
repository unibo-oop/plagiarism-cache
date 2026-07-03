package vendingmachine;

import product.ProductArchetype;

public class Slot implements SlotInterface {

  private ProductArchetype prod;
  private Integer pos;
  private Integer qt;
  private Float cost;

  /**
   * Costruttore del metodo.
   * @param prod prodtto contenuto
   * @param pos posizione nello scaffale
   * @param qt quantità del prodotto contenuto
   */
  public Slot(final ProductArchetype prod, final Integer pos, final Integer qt) {
    this.prod = prod;
    this.pos = pos;
    this.qt = qt;
  }

  @Override
  public Integer getPos() {
    return this.pos;
  }

  @Override
  public Integer getQt() {
    return this.qt;
  }

  @Override
  public void resetPos(final Integer pos) {
    this.pos = pos;
  }

  @Override
  public void resetQt(final Integer qt) {
    this.qt = qt;
  }

  @Override
  public void resetProd(final ProductArchetype prod) {
    this.prod = prod;
  }

  @Override
  public ProductArchetype getProd() {
    return this.prod;
  }

  @Override
  public void resetCost(final Float cost) {
    this.cost = cost;
  }

  @Override
  public Float getCost() {
    return this.cost;
  }

}
