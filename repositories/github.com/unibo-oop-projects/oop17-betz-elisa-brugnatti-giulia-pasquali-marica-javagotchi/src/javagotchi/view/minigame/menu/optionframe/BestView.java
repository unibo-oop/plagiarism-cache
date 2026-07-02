package javagotchi.view.minigame.menu.optionframe;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import javagotchi.controller.minigame.main.MiniGame;
import javagotchi.utility.Utility;
import javagotchi.view.minigame.AbstractFrameDefault;

/**
 * 
 * @author marica
 *
 */
public class BestView extends AbstractFrameDefault {

    private static final long serialVersionUID = 4129537195224209315L;
    private static final int WIDTH = 650;
    private static final int HEIGHT = 300;

    private final JButton ok = new JButton("Ok");

    /**
     * Constructor for BestView.
     */
    public BestView() {
        super(WIDTH, HEIGHT);
        this.setTitle("Best Score");
        this.setLayout(new BorderLayout());

        this.add(tablePanel(), BorderLayout.CENTER);
        this.add(southPanel(), BorderLayout.SOUTH);
        this.setEvent();
    }

    private JScrollPane tablePanel() {

        final JTable table = new JTable(new TableBestScore());
        final DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        final JScrollPane scrollPane = new JScrollPane(table);

        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        scrollPane.setBorder(new TitledBorder("Avatar - Best Score"));

        return scrollPane;

    }

    private JPanel southPanel() {
        final JPanel south = new JPanel(new FlowLayout());
        south.add(ok, BorderLayout.CENTER);
        return south;
    }

    @Override
    protected final void setEvent() {
        ok.addActionListener(e -> {
            Utility.log("Click Ok ...");
            this.dispose();
        });
    }

    private static class TableBestScore extends AbstractTableModel {

        private static final long serialVersionUID = -2638359166260334698L;

        private final Map<String, Integer> classification = MiniGame.getFactoryController().getControllerMiniGame()
                .getSavedData().readBestScore();

        private static final int COLUMNS_NUM = 2;

        @Override
        public final int getRowCount() {
            return classification.keySet().size();
        }

        @Override
        public final int getColumnCount() {
            return COLUMNS_NUM;
        }

        @Override
        public final Object getValueAt(final int rowIndex, final int columnIndex) {

            final String name = new ArrayList<>(classification.keySet()).get(rowIndex);
            final int bestscore = new ArrayList<>(classification.values()).get(rowIndex);
            switch (columnIndex) {
            case 0:
                return name;
            case 1:
                return bestscore;
            default:
                break;
            }
            return 0;
        }

        @Override
        public final String getColumnName(final int column) {

            switch (column) {
            case 0:
                return "Name";
            case 1:
                return "Best Score";
            default:
            }
            return "";
        }

        @Override
        public final Class<?> getColumnClass(final int column) {

            switch (column) {
            case 0:
                return String.class;
            case 1:
                return Integer.class;
            default:
            }
            return Object.class;
        }
    }
}
