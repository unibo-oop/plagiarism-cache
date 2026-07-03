package view;

import controller.SelectToolObserverView;

public interface MainPanelView {

    /**
       * Imposta l'observer per il MainPanel
       * @param observer
       */
    void setObserver(SelectToolObserverView observer);

}