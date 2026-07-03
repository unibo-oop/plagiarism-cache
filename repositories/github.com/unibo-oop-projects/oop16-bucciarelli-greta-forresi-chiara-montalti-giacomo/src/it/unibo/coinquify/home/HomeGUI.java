package it.unibo.coinquify.home;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import it.unibo.coinquify.balance.view.BalanceGUI;
import it.unibo.coinquify.calendar.view.CalendarGUI;
import it.unibo.coinquify.file.MainFile;
import it.unibo.coinquify.file.RoomMatesFile;
import it.unibo.coinquify.mansionsmng.view.MansionManagerGUI;
import it.unibo.coinquify.noticeboard.view.NoticeBoardGUI;
import it.unibo.coinquify.roommates.view.RoomMatesGUI;
import it.unibo.coinquify.shoppinglist.view.ShoppingListGUI;
import it.unibo.coinquify.telephonebook.view.TelephoneBookGUI;
import it.unibo.coinquify.utils.Messages;
import it.unibo.coinquify.utils.PaneGUI;
import it.unibo.coinquify.utils.PhoneNumberPresentException;
import it.unibo.coinquify.utils.UtilsGUI;

import java.awt.BorderLayout;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/**
 * The main GUI.
 */
public class HomeGUI extends JFrame {
    /**
     * 
     */
    private static final long serialVersionUID = 7223242357308163399L;

    private final Home home;
    private final List<PaneGUI> panels;

    /**
     * public constructor.
     * 
     * @throws IOException
     *             ex
     * @throws ClassNotFoundException
     *             ex
     * @throws FileNotFoundException
     *             ex
     */
    public HomeGUI() throws FileNotFoundException, ClassNotFoundException, IOException {
        this.home = new HomeImpl();
        this.panels = new ArrayList<>();
        this.build();
    }

    /**
     * @throws ClassNotFoundException
     * @throws IOException
     */
    private void build() throws ClassNotFoundException, IOException {
        final TelephoneBookGUI telephoneBook = new TelephoneBookGUI();
        final CalendarGUI calendar = new CalendarGUI();
        final NoticeBoardGUI noticeboard = new NoticeBoardGUI();
        final MansionManagerGUI mansionMngGUI = new MansionManagerGUI(this.home.getRoomMates(), this);
        this.home.setMansionManagerGUI(mansionMngGUI);

        final JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

        this.panels
                .addAll(Arrays.asList(new RoomMatesGUI(this.home, telephoneBook.getController().getAllContacts(), this),
                        calendar, mansionMngGUI, telephoneBook, new ShoppingListGUI(),
                        new BalanceGUI(this.home.getRoomMates()), noticeboard, new UtilsGUI()));

        // now add every panels a tab to home guis
        this.panels.forEach(p -> tabbedPane.addTab(p.getName(), p.getPanel()));

        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(final java.awt.event.WindowEvent windowEvent) {
                try {
                    // save all room mates
                    RoomMatesFile.writeRoomMates(home.getRoomMates());
                    telephoneBook.saveAll();
                    noticeboard.saveToFile();
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(calendar.getPanel(),
                            Messages.getMessages().getString("ERROR_IN__SAVING"));
                }

                try {
                    // save all events
                    calendar.saveAll();
                } catch (final Exception e) {
                    JOptionPane.showMessageDialog(null, Messages.getMessages().getString("ERROR_IN_SAVING"));
                }
                System.exit(0);
            }
        });

        this.setTitle("Coinquify");
        this.getContentPane().setLayout(new BorderLayout());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(tabbedPane, BorderLayout.CENTER);

    }

    private static void selectLanguage() {
        final String[] languages = { "English", "Italian" };
        Locale.setDefault(Locale.US);
        final JFrame frame = new JFrame("Language");
        final String locale = (String) JOptionPane.showInputDialog(frame, "Select language", "Language",
                JOptionPane.QUESTION_MESSAGE, null, languages, languages[0]);

        if (locale == null || "".equals(locale)) {
            System.exit(0);
        }

        switch (locale) {
        case "English":
            Messages.getMessages().setCurrentLocale(Locale.US);
            break;
        case "Italian":
            Messages.getMessages().setCurrentLocale(Locale.ITALY);
            break;
        default:
            System.exit(0);
        }
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * main.
     * 
     * @param args
     *            of main
     * @throws PhoneNumberPresentException
     *             if number is already present
     * @throws IllegalArgumentException if some field are invalid
     */
    public static void main(final String[] args) throws IllegalArgumentException, PhoneNumberPresentException {
        MainFile.checkResources();
        try {
            selectLanguage();
            final HomeGUI homeGUI = new HomeGUI();
            UtilsGUI.finishFrame(homeGUI);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
