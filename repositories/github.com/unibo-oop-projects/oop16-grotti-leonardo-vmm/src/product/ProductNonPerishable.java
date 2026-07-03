package product;

public class ProductNonPerishable implements ProductNonPerishableInterface {

  private Integer prodArc;
  private String batch;
  // private Float cost;

  public ProductNonPerishable(final ProductNonPerishableInterface prod) {
    setProductSupport(prod.getProduct());
    setBatchSupport(prod.getBatch());
  }

  public ProductNonPerishable(final ProductAchetypeInterface prod, final String batch) {
    setProductSupport(prod);
    setBatchSupport(batch);
  }

  @Override
  public void setProduct(final ProductAchetypeInterface prod) {
    setProductSupport(prod);
  }

  @Override
  public void setProduct(final String name, final String desc, final Integer dim) {
    final ProductArchetype prod = new ProductArchetype(name, desc, dim);
    ProdArcList.addProd(prod);
    this.prodArc = ProdArcList.getPos(prod);
  }

  @Override
  public ProductArchetype getProduct() {
    return (ProductArchetype) ProdArcList.getProd(prodArc);
  }

  @Override
  public void setBatch(final String batch) {
    setBatchSupport(batch);
  }

  @Override
  public String getBatch() {
    return this.batch;
  }

  private Integer getPos(final ProductArchetype prod) {
    ProdArcList.addProd(prod);
    return ProdArcList.getPos(prod);
  }

  private void setProductSupport(final ProductAchetypeInterface prod) {
    this.prodArc = ProdArcList.getPos(prod) != null ? ProdArcList.getPos(prod)
        : getPos((ProductArchetype) prod);
  }

  private void setBatchSupport(final String batch) {
    this.batch = batch;
  }

}
