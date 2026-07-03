package product;

public class ProductPerishable extends ProductNonPerishable implements ProductPerishableInterface {

  private String expirationDate;

  public ProductPerishable(final ProductNonPerishableInterface prod, final String expDate) {
    super(prod);
    setExpirationDateSupport(expDate);
  }

  @Override
  public void setExpirationDate(final String date) {
    setExpirationDateSupport(date);
  }

  @Override
  public String getExpirationDate() {
    return this.expirationDate;
  }

  private void setExpirationDateSupport(final String date) {
    this.expirationDate = date;
  }

}
