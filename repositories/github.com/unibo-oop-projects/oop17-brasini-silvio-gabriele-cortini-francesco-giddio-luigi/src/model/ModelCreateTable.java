package model;

import java.util.List;

import javax.swing.JPanel;

public interface ModelCreateTable {

    String getTrText(String string);

    /**
     * richiede una lista di type, ex int, char/varchar[255], date
     * 
     * @return lista di tipi
     */
    List<String> getListType();

    /**
     * se il selectIndex è varchar(o char) ha bisogno di una lungezza
     * 
     * @param selectedIndex
     * @return
     */
    boolean needNumber(int selectedIndex);

}
