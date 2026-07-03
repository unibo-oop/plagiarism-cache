package product;

public interface ProductAchetypeInterface {
  /**
   * Il metodo serve a settare il nome del prodotto.
   * 
   * @param name
   *          Il parametro è il nome che si vuole dare al prodotto
   */
  void setPaName(String name);

  /**
   * Il metodo restituisce il nome del prodotto selezionato.
   * 
   * @return Restituisce il nome del prodotto
   */
  String getPaName();

  /**
   * Il metodo setta la descrizione del prodotto.
   * 
   * @param desc
   *          Il parametro indica la descrizione del prodotto
   */
  void setDescription(String desc);

  /**
   * Il metodo restituisce la descrizione del prodotto.
   * 
   * @return restituisce la stringa di descrizione del prodotto
   */
  String getDescription();

  /**
   * Il metodo setta lo spazio che tale prodotto occupa nella macchinetta.
   * 
   * @param dim
   *          Il parametro è la dimensione dell'oggetto
   */
  void setDimension(int dim);

  /**
   * Il metodo restituisce la dimensione dell'oggetto.
   * 
   * @return restituisce quanti blocchi di dimensione occupa l'oggetto
   */
  int getDimension();
}
