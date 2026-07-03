package controller;

import static controller.UserInfo.PASSWORD;
import static controller.UserInfo.USERNAME;

import java.awt.Component;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import javax.swing.JPanel;

import model.User;
import model.UserImpl;
import model.UserManager;
import model.UserManagerImpl;
import view.CalendarView;
import view.ContactView;
import view.LoginView;
import view.LoginViewImpl;
import view.MainViewTabbedImpl;
import view.ToDoView;
import view.View;
/**
 *MainController used by TestApp.
 */
public final class MainControllerImpl implements MainController {
    private static final MainController SINGLETON = new MainControllerImpl();
    private final MainViewTabbedImpl frame;
    private final LoginView lv;
    /**
     * manager.
     * manager
     * 
     */
    public static UserManager manager;
    /**
     * 
     */
    //public static CalendarThread thisThread;

    @SuppressWarnings("static-access")
    private MainControllerImpl() {
        FileInputStream in = null;
        try {
            in = new FileInputStream("user_storage.dat");
            ObjectInputStream s = null;
            try {
                s = new ObjectInputStream(in);
            } catch (IOException e) {
                System.out.println("IOException");
            }
            try {
                this.manager = (UserManager) s.readObject();
            } catch (ClassNotFoundException e) {
                System.out.println("ClassNotFoundException");
            } catch (IOException e) {
                System.out.println("IOException");
            }
        } catch (FileNotFoundException e) {
            manager = UserManagerImpl.getManager();
        }
        this.frame = new MainViewTabbedImpl();
        this.lv = new LoginViewImpl(this, this.frame);
        this.frame.addTab((JPanel) lv, "Login");
    }
    /**
     * logout.
     * logout
     * 
     */
    @SuppressWarnings("static-access")
    public void logout() {
        this.manager.logout();
        this.writeValues();
    }
    /**
     * writeValues.
     * write on file
     */
    @SuppressWarnings("static-access")
    public void writeValues() {
        try {
            final FileOutputStream out = new FileOutputStream("user_storage.dat");
            try {
                @SuppressWarnings("resource")
                final ObjectOutputStream s = new ObjectOutputStream(out);
                s.writeObject(this.manager);
                s.flush();
            } catch (IOException e) {
                System.out.println("Error IOException writeFile");
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error FileNotFound writeFile");
            e.printStackTrace();
        }
    }
    private void userLogged() {
        final CalendarView cv = CalendarControllerImpl.getInstance().getCalendarView();
        final ContactView cov = ContactControllerImpl.getInstance().getContactView();
        final ToDoView tdv = ToDoControllerImpl.getInstance().getToDoView();
        this.frame.addTab((JPanel) cv, "Calendar");
        this.frame.addTab((JPanel) cov, "Contacts");
        this.frame.addTab((JPanel) tdv, "To Do");
    }
    @Override
    public void startApp() {
        this.frame.init();
    }

    @Override
    public void show(final View view) {
        this.frame.goToTab(view);
    }

    @Override
    public void updateView(final View view, final View old) {
        this.frame.replaceTab(view, old);
    }
    /**
     * 
     * @return SINGLETON
     */
    public static MainController getSingleton() {
        return SINGLETON;
    }

    @Override
    public void confirmLogin(final Map<UserInfo, String> map) {
        User user;
        try {
            user = new UserImpl(map.get(USERNAME), map.get(PASSWORD));
            try {
            if (MainControllerImpl.manager.login(user)) {
            this.lv.showMessage("Allowed Access", (Component) this.lv);
            this.userLogged();
            } else {
                this.lv.showMessage("Access error, please controll that Username and Password are corrected", (Component) this.lv);
            }
            } catch (Exception e) {
                this.lv.showMessage("Access error, please controll that Username and Password are corrected", (Component) this.lv);
            }

        } catch (Exception e) {
            this.lv.showMessage("Access error, please controll that Username and Password are corrected", (Component) this.lv);
        }
    }
    @Override
    public void changePassword() {
        final ChangePasswordControllerImpl changePasswordController = new ChangePasswordControllerImpl();
        changePasswordController.update();
    }
    @Override
    public void registerUserCliecked() {
        final AddUserControllerImpl addUserController = new AddUserControllerImpl();
        addUserController.update();
    }
    @Override
    public void cancelUser() {
            MainControllerImpl.manager.remove(MainControllerImpl.manager.currentUser());
    }
}
