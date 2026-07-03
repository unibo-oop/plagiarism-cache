package handler;

import java.util.List;

public interface ActionListenerHandlerInterface {

  /**
   * Il metodo permette di eseguire le varie azioni richiamate dai bottoni delle GUI.
   * 
   * @param bt
   *          -> L'azione richiesta dal bottone premuto.
   * @param obj
   *          -> lista di oggetti che sono necessari per le azioni eseguite dagli oggetti.
   */
  void buttonHandler(CaseHandler bt, List<?> obj);
}
