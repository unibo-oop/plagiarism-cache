package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

import controller.ControlCore;

public class ModelCreateTableImpl implements ModelCreateTable {

    List<String> type = new ArrayList<>();

    public ModelCreateTableImpl(final ControlCore cc) {
        // TODO Auto-generated constructor stub
        this.type.add("int");
        this.type.add("char");
        this.type.add("date");
    }

    @Override
    public List<String> getListType() {
        // TODO Auto-generated method stub
        return this.type;
    }

    @Override
    public boolean needNumber(final int selectedIndex) {
        // TODO Auto-generated method stub
        if (this.type.get(selectedIndex).equals("char")) {
            return true;
        }
        return false;
    }

    @Override
    public String getTrText(final String string) {
        // TODO Auto-generated method stub
        return null;
    }

}
