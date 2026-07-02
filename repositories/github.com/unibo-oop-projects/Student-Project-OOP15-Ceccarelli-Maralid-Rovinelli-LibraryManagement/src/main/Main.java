package main;
import model.Model;
import model.ModelImpl;
import view.MainView;
import view.MainViewImpl;

import java.awt.Window;

import controller.Controller;
public class Main {

    public static void main(String[] args) {
        Model model = new ModelImpl();
        Controller c = new Controller(model);
        MainView v = new MainViewImpl();
        c.setView(v);
        ((Window) v).setVisible(true);
    }

}
