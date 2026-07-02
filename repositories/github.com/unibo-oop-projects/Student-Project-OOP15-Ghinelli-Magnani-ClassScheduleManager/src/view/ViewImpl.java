package view;


import java.awt.BorderLayout;      
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;

import controller.Controller;
import model.interfaces.ILesson;
import view.menu.AddMenu;
import view.menu.BaseMenu;
import view.menu.DeleteMenu;
import view.menu.SemesterMenu;
import view.utility.ColorUtility;
import view.utility.ObjectManager;

/**
 * 
 * Class which represents the main frame of the program.
 *
 */

public class ViewImpl extends JFrame implements IView {
    
    /**
     * 
     */
    private static final long serialVersionUID = -7339167500714323687L;
    private final JMenuBar menuBar = new JMenuBar();
    private final BaseMenu menu = new BaseMenu(this);
    private final JMenu addMenu = new AddMenu(this);
    private final JMenu deleteMenu = new DeleteMenu(this);
    private final JMenu semesterMenu = new SemesterMenu();
    private final MyTableModel tableModel = new MyTableModel();
    private final JTable table = new JTable(tableModel);
    private final JScrollPane fullTable = new JScrollPane(this.table);
    private final JPanel combo = new JPanel(new BorderLayout());
    private final JPanel legenda = new JPanel(new GridBagLayout());
    private final EditPanel editing = new EditPanel(table, this);
    private int dataType;

    /**
     * Constructor of the main frame.
     */
    
    public ViewImpl() {
        super();
        this.addData(0, null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setTitle("Orario Lezioni");
        
        this.table.setDefaultRenderer(Object.class, new MyRenderer());
        this.table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        this.table.setTableHeader(null);
        this.table.setFillsViewportHeight(true); // da ricordare a che serve
        this.table.setFocusable(false);
        this.table.setRowSelectionAllowed(false);
        
        this.menuBar.add(menu);
        this.menuBar.add(addMenu);
        this.menuBar.add(deleteMenu);
        this.menuBar.add(semesterMenu);
        
        this.legenda.setBorder(new TitledBorder("Legenda"));
        GridBagConstraints cnst = new GridBagConstraints();
        cnst.gridy = 0;
        for (int i = 0; i < ColorUtility.getColorsByYear().size(); i++) {
            final JLabel color = new JLabel("  ");
            color.setOpaque(true);
            color.setBackground(ColorUtility.getColorsByYear().get(i).getY());
            this.legenda.add(color, cnst);
            this.legenda.add(new JLabel(ColorUtility.getColorsByYear().get(i).getX()), cnst);
            cnst.gridy++;
        }
        this.combo.add(legenda, BorderLayout.NORTH);
        this.combo.add(editing, BorderLayout.CENTER);
        
        this.getContentPane().add(menuBar, BorderLayout.NORTH);
        this.getContentPane().add(fullTable, BorderLayout.CENTER);
        this.getContentPane().add(combo, BorderLayout.EAST);
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize().width / 2, Toolkit.getDefaultToolkit().getScreenSize().height / 2);
        final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((dim.width - this.getSize().width) / 2, (dim.height - this.getSize().height) / 2);
        this.setVisible(true);
    }

    @Override
    public void addData(final int type, final List<ILesson> list) {
        this.dataType = type;
        this.tableModel.setModel(ObjectManager.getStruct(type, list));
    }

    @Override
    public void editMode(final boolean set) {
        this.menu.setEnabled(!set);
        this.addMenu.setEnabled(!set);
        this.deleteMenu.setEnabled(!set);
        this.semesterMenu.setEnabled(!set);
        this.editing.editMode(set, this.dataType);
    }

    @Override
    public void refreshSearchList() {
        this.menu.refreshSearchList();
    }

    @Override
    public void errorDialog(final String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    @Override
    public void exportData() {
        final HSSFWorkbook workbook = new HSSFWorkbook();
        final HSSFSheet sheet = workbook.createSheet("List of lessons");
        final CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setWrapText(true);
        for (int i = 0; i < table.getRowCount(); i++) {
            final Row rows = sheet.createRow(i);
            for (int y = 0; y < table.getColumnCount(); y++) {
                if (table.getValueAt(i, y) instanceof ILesson) {
                    rows.createCell(y).setCellValue(((ILesson) table.getValueAt(i, y)).getSubject().getName() + System.lineSeparator() + ((ILesson) table.getValueAt(i, y)).getProfessor().getName());
                } else {
                    rows.createCell(y).setCellValue(table.getValueAt(i, y).toString());
                }
                rows.getCell(y).setCellStyle(cellStyle);
                sheet.autoSizeColumn(y);
            }
        }
        Controller.getController().excelExport(workbook);
    }

}
