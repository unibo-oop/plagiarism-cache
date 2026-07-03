package controller;

import com.sun.javafx.application.LauncherImpl;

import view.SplashScreenLoader;
import view.Menu.MainMenu;

/**
 * launch the Application
 * @author Alex
 *
 */
public class MainApplication {

    public static void main(final String[] args){
        LauncherImpl.launchApplication(MainMenu.class,SplashScreenLoader.class,args);
    }
}
