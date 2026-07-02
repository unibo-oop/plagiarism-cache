package javagotchi.view.menu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import javagotchi.controller.menu.MenuController;
import javagotchi.model.Javagotchi;
/**
 *  This class is the implementation of the menu that shows
 *   the information of the avatar saved.
 * @author giulia
 */
public class InformationMenu implements Menu {
    private static final double MENU_PERC_WIDTH = 0.32;
    private static final double MENU_PERC_HEIGHT = 0.30;

    private final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final Optional<JFrame> frame;
    private final MenuView view;
    /**
     * this is the constructor for this class.
     * @param controller **this is the MenuController**
     * @param view **this is the MenuView**
     */
    public InformationMenu(final MenuController controller, final MenuView view) {
        this.view = view;
        final JPanel mainPanel = new JPanel();
        final JPanel controlPanel = new JPanel();

        final JButton backButton = new JButton("BACK");
        controlPanel.add(backButton);

        List<Javagotchi> infoList = new ArrayList<>();
        infoList = controller.getListJavagotchi();
        System.out.println("Creating InfoMenu...");
        this.frame = Optional.of(new JFrame("Tamagotchi - Information"));
        this.frame.get().setSize((int) (screenSize.getWidth() * MENU_PERC_WIDTH),
                (int) (screenSize.getHeight() * MENU_PERC_HEIGHT));
        this.frame.get().setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        final List<InformationTableLine> lines = new ArrayList<>();
        infoList.forEach(e -> {
            lines.add(new InformationTableLine(e.getInformation().getName(), e.getInformation().getAvatar().toString(),
                    e.getInformation().getGender().toString(), e.getInformation().getAge(), e.isAlive()));
        });
        final InformationTableModel tableModel = new InformationTableModel(lines, controller);
        final JTable valuesTable = new JTable(tableModel);

        final JScrollPane scrc1 = new JScrollPane(valuesTable);
        scrc1.setBorder(new TitledBorder("Tamagotchi - Information"));
        mainPanel.add(scrc1);

        backButton.addActionListener(e -> {
            InformationMenu.this.frame.get().setVisible(false);
            InformationMenu.this.view.getMenuManager().showMainMenu();
        });

        this.frame.get().getContentPane().add(mainPanel, BorderLayout.CENTER);

        this.frame.get().getContentPane().add(controlPanel, BorderLayout.SOUTH);

        this.frame.get().setResizable(true);
        this.frame.get().setLocationRelativeTo(null);
    }

    @Override
    public final void show() {
        if (this.frame.isPresent()) {
            this.frame.get().setVisible(true);
            System.out.println("Showing StatsMenu...");
        }
    }
}
