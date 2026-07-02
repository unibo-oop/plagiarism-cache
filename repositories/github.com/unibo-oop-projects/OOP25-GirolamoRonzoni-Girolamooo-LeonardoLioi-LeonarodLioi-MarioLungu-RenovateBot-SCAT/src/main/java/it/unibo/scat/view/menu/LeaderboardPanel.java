package it.unibo.scat.view.menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.scat.common.GameRecord;
import it.unibo.scat.view.UIConstants;
import it.unibo.scat.view.api.ViewActionsInterface;
import it.unibo.scat.view.menu.api.MenuPanelInterface;
import it.unibo.scat.view.util.AudioManager;
import it.unibo.scat.view.util.AudioTrack;

/**
 * Panel that displays the global leaderboard with ranking data.
 */
@SuppressFBWarnings(value = { "SE_TRANSIENT_FIELD_NOT_RESTORED",
        "EI_EXPOSE_REP2" }, justification = "Component not intended for serialization;Reference intentionally shared")
public final class LeaderboardPanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private static final int COLUMN_COUNT = 5;
    private static final int TABLE_ROW_HEIGHT = 45;
    private final transient MenuPanelInterface menuInterface;
    private final transient ViewActionsInterface menuActionsInterface;
    private final transient List<GameRecord> records;
    private final transient AudioManager audiomanager;

    /**
     * Creates the leaderboard panel and initializes its components.
     *
     * @param mInterface       interface used to switch menu screens
     * @param mActionInterface interface used to retrieve leaderboard data
     */
    public LeaderboardPanel(final MenuPanelInterface mInterface, final ViewActionsInterface mActionInterface) {
        this.menuInterface = mInterface;
        this.menuActionsInterface = mActionInterface;
        records = menuActionsInterface.fetchLeaderboard();
        audiomanager = new AudioManager();

        setLayout(new BorderLayout());
        this.setBackground(UIConstants.ARCADE_BLACK);
        this.setBorder(UIConstants.PANELS_BORDER);

        final JLabel titleLabel = new JLabel("GLOBAL RANKING", JLabel.CENTER);
        titleLabel.setFont(UIConstants.FONT_XXL);
        titleLabel.setForeground(UIConstants.ARCADE_GREEN);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        add(titleLabel, BorderLayout.NORTH);
        initContentTable();
        initBackButton();
    }

    /**
     * Initializes the back button and its mouse interactions.
     * When clicked, returns to the settings screen.
     */
    private void initBackButton() {
        final JLabel backButton = new JLabel("< BACK");
        backButton.setFont(UIConstants.FONT_M);
        backButton.setForeground(Color.RED);
        backButton.setHorizontalAlignment(SwingConstants.CENTER);
        backButton.setVerticalAlignment(SwingConstants.CENTER);
        backButton.setBorder(new EmptyBorder(TABLE_ROW_HEIGHT, 0, TABLE_ROW_HEIGHT, 0));

        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                menuInterface.showSettingsPanel();
                audiomanager.play(AudioTrack.OPTION_SELECTED, false);
            }

            @Override
            public void mouseEntered(final MouseEvent e) {
                backButton.setForeground(Color.WHITE);
                backButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                audiomanager.play(AudioTrack.MOUSE_OVER, false);
            }

            @Override
            public void mouseExited(final MouseEvent e) {
                backButton.setForeground(Color.RED);
                backButton.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        });
        add(backButton, BorderLayout.SOUTH);
    }

    /**
     * Builds and configures the leaderboard table,
     * populating it with fetched game records.
     */
    private void initContentTable() {
        final String[] columnNames = {"RANK", "NAME", "SCORE", "LEVEL", "DATE" };
        final Object[][] data = new Object[records.size()][COLUMN_COUNT];

        for (final GameRecord record : records) {

            final int index = records.indexOf(record);
            data[index][0] = index + 1;
            data[index][1] = record.getName();
            data[index][2] = record.getScore();
            data[index][3] = record.getLevel();
            data[index][4] = record.getDate().toString();
        }

        final DefaultTableModel model = new DefaultTableModel(data, columnNames);
        final JTable table = new JTable(model);
        UIManager.put("Table.gridColor", Color.BLACK);
        UIManager.put("Table.focusCellHighlightBorder", BorderFactory.createLineBorder(Color.BLACK));
        UIManager.put("TableHeader.cellBorder", BorderFactory.createLineBorder(Color.BLACK));

        table.setGridColor(Color.BLACK);
        table.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        table.getTableHeader().setBorder(BorderFactory.createLineBorder(Color.BLACK));

        final DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        table.setBackground(UIConstants.ARCADE_BLACK);
        table.setForeground(Color.WHITE);
        table.setFont(UIConstants.FONT_S);
        table.setGridColor(UIConstants.ARCADE_BLACK);
        table.setRowHeight(TABLE_ROW_HEIGHT);
        table.setEnabled(false);

        final JTableHeader header = table.getTableHeader();
        header.setBackground(UIConstants.ARCADE_BLACK);
        header.setForeground(UIConstants.ARCADE_GREEN);
        header.setFont(UIConstants.FONT_M);
        header.setReorderingAllowed(false);

        final JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.getViewport().setBackground(UIConstants.ARCADE_BLACK);
        scrollPane.setBorder(new EmptyBorder(0, 0, 0, 0));

        this.add(scrollPane, BorderLayout.CENTER);
    }

}
