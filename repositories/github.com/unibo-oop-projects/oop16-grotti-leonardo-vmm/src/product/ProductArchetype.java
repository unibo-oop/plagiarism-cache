package product;

public class ProductArchetype implements ProductAchetypeInterface {

  private String name;
  private String desc;
  private Integer dim;

  /**
   * Costruttore della classe.
   * @param name nome prod
   * @param desc descrizione prod
   * @param dim dimensione prod
   */
  public ProductArchetype(final String name, final String desc, final Integer dim) {
    setNameSupport(name);
    setDescriptionSupport(desc);
    setDimensionSupport(dim);
  }

  @Override
  public void setPaName(final String name) {
    setNameSupport(name);
  }

  @Override
  public String getPaName() {
    return this.name;
  }

  @Override
  public void setDescription(final String desc) {
    setDescriptionSupport(desc);
  }

  @Override
  public String getDescription() {
    return desc;
  }

  @Override
  public void setDimension(final int dim) {
    setDimensionSupport(dim);
  }

  @Override
  public int getDimension() {
    return this.dim;
  }

  private void setNameSupport(final String name) {
    this.name = name;
  }

  private void setDescriptionSupport(final String desc) {
    this.desc = desc;
  }

  private void setDimensionSupport(final Integer dim) {
    this.dim = dim;
  }
}
