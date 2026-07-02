package application;

import java.awt.Toolkit;

import model.file.MyFile;
import model.file.MyFileImpl;
import view.HomeHotelManage.HomeHotelManageView;

public final class HotelManage {
    private static final String SEP = System.getProperty("file.separator");
    private static final String DIRUSER = System.getProperty("user.home");
    private static final String NAMEFILECLIENT = ("Clients.txt");
    private static final String NAMEFILERESERVATION = ("Reservation.txt");
    private static final String PATHCLIENT = (DIRUSER + SEP + NAMEFILECLIENT);
    private static final String PATHRESERVATION = (DIRUSER + SEP + NAMEFILERESERVATION);

public static void main(final String[] args) {
    new HotelManage().start();
    }

    private void start() {
        this.initializeDemo();
        final HomeHotelManageView mainFrame = new HomeHotelManageView();

        mainFrame.setSize(Toolkit.getDefaultToolkit().getScreenSize().width,
                Toolkit.getDefaultToolkit().getScreenSize().height);
        mainFrame.pack();
        mainFrame.setVisible(true);
    }

    private void initializeDemo() {
        MyFile fileClient = new MyFileImpl(PATHCLIENT);
        MyFile fileReservation = new MyFileImpl(PATHRESERVATION);

        if (fileClient.getFile().length() == 0) {
            fileClient.fileWriter("mario.neri.marner65g80i548k");
            fileClient.fileWriter("giovanni.giorgio.giorgiov33e66w456t");
            fileClient.fileWriter("chiara.tozzi.tzzchr00a50r458l");
            fileClient.fileWriter("simone.albonetti.lbnsmn99e07d458k");
        }
        if (fileReservation.getFile().length() == 0) {
            fileReservation.fileWriter("lbnsmn99e07d458k.01/05/2021.31/05/2021.1");
            fileReservation.fileWriter("marner65g80i548k.5/05/2021.22/05/2021.18");
            fileReservation.fileWriter("giorgiov33e66w456t.12/05/2021.21/05/2021.12");
            fileReservation.fileWriter("tzzchr00a50r458l.27/05/2021.28/05/2021.11");
        }
    }
}
