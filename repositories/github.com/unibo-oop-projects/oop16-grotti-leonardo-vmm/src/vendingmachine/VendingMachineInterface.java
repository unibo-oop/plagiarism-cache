package vendingmachine;

import java.util.List;

public interface VendingMachineInterface {

  /**
   * Il metodo ï¿½ necessario per settare la prima volta il nome della macchinetta.
   * 
   * @param name
   *          -> nome
   */
  void setVmName(String name);

  /**
   * Il metodo serve per conoscere il nome della macchinetta desiderata.
   * 
   * @return -> nome
   */
  String getVmName();

  /**
   * Il metodo serve per dare una posizione descrittiva alla macchinetta.
   * 
   * @param position
   *          -> posizione della macchinetta
   */
  void setPosition(String position);

  /**
   * Il metodo serve per sapere dove si trova una macchinetta.
   * 
   * @return -> posizione della macchinetta
   */
  String getPosition();

  /**
   * Il metodo serve per settare la descrizione di una macchinetta.
   * 
   * @param description
   *          -> descrizione della macchinetta
   */
  void setDescription(String description);

  /**
   * Il metodo restituisce la descrizione della macchinetta.
   * 
   * @return -> descrizione della macchientta
   */
  String getDescription();

  /**
   * Il metodo permette di aggiungere una scansia alla macchinetta.
   * 
   * @param shelf
   *          -> scansia
   */
  void addShelf(Shelf shelf);

  /**
   * Il metodo permette di aggiungere una scansia alla macchientta.
   * 
   * @param slots
   *          -> numero di slots
   * @param prodPerSlot
   *          -> numero di prodotti mettibili in uno slot
   */
  void addShelf(Integer slots, Integer prodPerSlot);

  /**
   * Il metodo elimina lo scaffale indicato.
   * 
   * @param shelf
   *          -> scaffale da eliminare
   */
  void deleteShelf(Shelf shelf);

  /**
   * Il metodo restituisce tutti gli scaffali presenti nella vendingmachine.
   * 
   * @return -> tutti gli scaffali
   */
  List<Shelf> getAllShelf();

  /**
   * Il metodo restituisce lo scaffale all'indice indicato.
   * 
   * @param index
   *          -> indice dello scaffale da cercare
   * @return -> scaffale cercato
   */
  Shelf getShelf(Integer index);

  /**
   * Il metodo restiduidsce tutti i soldi presenti nella macchinetta, con ogni formato.
   * 
   * @return -> restituisce tutti i soldi con ogni formato
   */
  List<Integer> getMonsy();

  /**
   * Il metodo permette di impostare il numero di monete presenti nellamacchinetta per ogni formato.
   * 
   * @param qt
   *          -> lista delle quantità, i tagli presenti vanno da 1 centesimo fino ai 500 euro,
   *          aggiungere ogni taglio anche se contiente 0 in alternativa le quantità verranno
   *          trasposte e negli ultimi impostati 0
   */
  void setMoney(List<Integer> qt);

}
