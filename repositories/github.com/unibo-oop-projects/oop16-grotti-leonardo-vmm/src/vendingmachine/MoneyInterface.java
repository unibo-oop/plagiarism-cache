package vendingmachine;

import java.util.List;

public interface MoneyInterface {

  /**
   * Il metodo permette di sapere tutti i soldi contenuti nella macchinetta.
   * @return restituisce la lista dei soldi
   */
  List<Integer> howManyMoney();
  
  /**
   * I metodi con il termine add permettono di aggiungere il numero indicato di soldi.
   */
  
  /**
   * 1 centesimo.
   * @param num numero di soldi aggiunti
   */
  void addOneCent(int num);

  /**
   * 2 centesimi.
   * @param num numero di soldi aggiunti
   */
  void addTwoCent(int num);

  /**
   * 5 centesimi.
   * @param num numero di soldi aggiunti
   */
  void addFiveCent(int num);

  /**
   * 10 centesimi.
   * @param num numero di soldi aggiunti
   */
  void addTenCent(int num);

  /**
   * 20 centesimi.
   * @param num numero di soldi aggiunti
   */
  void addTwentyCent(int num);

  /**
   * 50 centesimi.
   * @param num numero di soldi aggiunti
   */
  void addFiftyCent(int num);

  /**
   * 1 euro.
   * @param num numero di soldi aggiunti
   */
  void addOne(int num);

  /**
   * 2 euro.
   * @param num numero di soldi aggiunti
   */
  void addTwo(int num);

  /**
   * 5 euro.
   * @param num numero di soldi aggiunti
   */
  void addFive(int num);

  /**
   * 10 euro.
   * @param num numero di soldi aggiunti
   */
  void addTen(int num);

  /**
   * 20 euro.
   * @param num numero di soldi aggiunti
   */
  void addTwenty(int num);

  /**
   * 50 euro.
   * @param num numero di soldi aggiunti
   */
  void addFifty(int num);

  /**
   * 100 euro.
   * @param num numero di soldi aggiunti
   */
  void addOneHundred(int num);

  /**
   * 200 euro.
   * @param num numero di soldi aggiunti
   */
  void addTwoHundred(int num);

  /**
   * 500 euro.
   * @param num numero di soldi aggiunti
   */
  void addFiveHundred(int num);
}
