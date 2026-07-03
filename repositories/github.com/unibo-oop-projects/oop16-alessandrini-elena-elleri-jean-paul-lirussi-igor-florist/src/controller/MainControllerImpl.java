 package controller;

import java.awt.Image;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import org.apache.commons.io.IOUtils;

import view.GreenhouseView;
import view.MainTabbedPaneImpl;
import view.View;

/**
 * 
 * This class implements {@link MainController}.
 *
 */
public final class MainControllerImpl implements MainController {


    private static final String MAIN_DIRECTORY = System.getProperty("user.home") 
                                                + System.getProperty("file.separator")
                                                + ".thebloomingflorist";
    private static final List<String> CONF_FILES = Arrays.asList("planting.dat", 
                                                                 "employee.dat");

    private static final  MainController SINGLETON = new MainControllerImpl();
    private static final int DIMENSION = 50;
    private MainTabbedPaneImpl frame;

    /**
     * A builder that loads all the {@link Icon}
     */
    private MainControllerImpl() {
        try {
            this.install();
        } catch (IOException e) {
            System.out.println("install failed");
        }

        this.frame = new MainTabbedPaneImpl();
        final GreenhouseView greenhouseView = GreenhouseControllerImpl.getInstance().getGreenhouseView();
        final URL greenURL = getClass().getResource("/generic.png");
        ImageIcon greenhouseIcon = new ImageIcon(new ImageIcon(greenURL).getImage().getScaledInstance(DIMENSION, DIMENSION, Image.SCALE_DEFAULT));
        this.frame.addTab((JPanel) greenhouseView, "Greenhouse", greenhouseIcon);

        final URL employeeURL = getClass().getResource("/employees.png");
        ImageIcon employeeIcon = new ImageIcon(new ImageIcon(employeeURL).getImage().getScaledInstance(DIMENSION, DIMENSION, Image.SCALE_DEFAULT));
        this.frame.addTab(EmployeeControllerImpl.getInstance().getEmployeeView(), "Employees", employeeIcon);

        final URL infoURL = getClass().getResource("/info.png");
        ImageIcon infoIcon = new ImageIcon(new ImageIcon(infoURL).getImage().getScaledInstance(DIMENSION, DIMENSION, Image.SCALE_DEFAULT));
        this.frame.addTab(InfoController.getInstance().getInfoView(), "Info", infoIcon);

    }

    /**
     * This method create the main application folder if absent.
     * 
     */
    private static void createMainFolder() {
        File directory = new File(MAIN_DIRECTORY);
        if (directory.exists()) {
            System.out.println("Directory already exists");
        } else {
            System.out.println("Directory not exists, creating");
            boolean success = directory.mkdir();
            if (success) {
                System.out.println("Successfully created new directory");
            } else {
                System.out.println("Error creating new directory");
            }
        }
    }

    /**
     * This method installs the application load all the configuration file in
     * the main folder.
     * 
     * @throws IOException
     */
    private void install() throws IOException {
        createMainFolder();
        for (String s : CONF_FILES) {
            final String path = MAIN_DIRECTORY + System.getProperty("file.separator") + s;
            if (!Files.exists(Paths.get(path))) {
                try {
                    InputStream input = this.getClass().getResourceAsStream(System.getProperty("file.separator") + s);
                    IOUtils.copy(input, new FileOutputStream(path));
                    System.out.println(s + " created");
                } catch (final NullPointerException exc) {
                    System.out.println(s + " not found");
                }
            }
        }
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
    public void updateView(final View newView, final View oldView) {
        this.frame.replaceTab(newView, oldView);
    }

    /**
     * This method return the SINGLETON.
     * 
     * @return SINGLETON
     */
    public static MainController getSingleton() {
        return SINGLETON;
    }

}
