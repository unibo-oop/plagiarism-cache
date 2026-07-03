package product;

public interface ProductPerishableInterface {

  /**
   * Il metodo permette di impostare la data dis cadenza del prodotto.
   * 
   * @param date
   *          -> data di scadenza
   */
  void setExpirationDate(String date);

  /**
   * Il metodo restituisce la data di scadenza.
   * 
   * @return -> data di scadenza
   * 
   */
  String getExpirationDate();
}
