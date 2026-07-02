package view;
import java.awt.Dimension;
import java.awt.Toolkit;
import model.ChartException;
import model.DateException;
import model.DatiDaVisualizzareEnum;
import javax.swing.*;
import com.github.lgooddatepicker.components.DatePicker;
import controller.AdministratorChartsControllerImpl;
import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler.ChartTheme;
import org.knowm.xchart.style.Styler.LegendPosition;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.border.TitledBorder;
import java.awt.Cursor;

/**
 * 
 * @author Nico Nize
 *
 */

public class AdministratorChartsView extends JFrame {
    
    /**
     * 
     */
    private static final long serialVersionUID = -800518568134501851L;
    
    private static final double PERCENT = 0.6;
    private static final String TITLE = "GRAFICI AMMINISTRATORE";
    final AdministratorChartsControllerImpl ctrl;
    final private JPanel panelTitle;
    final private JLabel lblNewLabel;
    final private JLabel lblTitle;
    final private JButton btnNewButton;
    final private JPanel mainPanel;
    final private JPanel datePanel;
    final private JPanel chartPanel;
    final private JLabel labelDatePickerFirst;
    final private DatePicker datepickerFirst;
    final private JLabel labelDatePickerLast;
    final private DatePicker datepickerLast;
    final private JComboBox<String> comboBoxDatiGrafico;
    final private JButton btnVisualizzaDati;
    final private JButton btnReset;
    final private JButton btnDeleteLine;
    
    public AdministratorChartsView() {   
       
        this.ctrl = new AdministratorChartsControllerImpl();
        setTitle(AdministratorChartsView.TITLE);
        setMinimumSize(new Dimension(500, 500));
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        panelTitle = new JPanel();
        panelTitle.setBackground(SystemColor.activeCaption);
        getContentPane().add(panelTitle, BorderLayout.NORTH);
        panelTitle.setLayout(new BorderLayout(0, 0));
        
        lblNewLabel = new JLabel("Seleziona la data e il tipo di dato d'interesse per visualizzare l'andamento nell'intervallo di tempo richiesto.");
        lblNewLabel.setForeground(SystemColor.textText);
        lblNewLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
        panelTitle.add(lblNewLabel, BorderLayout.SOUTH);
        
        btnNewButton = new JButton("Back Home");
        btnNewButton.addActionListener(e->{
            new HomeView().display();
            dispose();
        });
        btnNewButton.setBackground(SystemColor.activeCaption);
        panelTitle.add(btnNewButton, BorderLayout.NORTH);
        
        lblTitle = new JLabel("Finestra Grafici");
        panelTitle.add(lblTitle, BorderLayout.CENTER);
        lblTitle.setPreferredSize(new Dimension(100, 30));
        lblTitle.setMinimumSize(new Dimension(100, 30));
        lblTitle.setMaximumSize(new Dimension(100, 30));
        lblTitle.setHorizontalAlignment(SwingConstants.LEFT);
        lblTitle.setForeground(SystemColor.textText);
        lblTitle.setFont(new Font("Trebuchet MS", Font.BOLD, 20));
        
        mainPanel = new JPanel();
        getContentPane().add(mainPanel, BorderLayout.CENTER);
        mainPanel.setLayout(new BorderLayout(0, 0));
        
        datePanel = new JPanel();
        datePanel.setBorder(new TitledBorder(null, "Data e tipo dato", TitledBorder.LEADING, TitledBorder.TOP, null, SystemColor.activeCaption));
        datePanel.setBackground(SystemColor.window);
        datePanel.setPreferredSize(new Dimension(10, 100));
        datePanel.setMinimumSize(new Dimension(10, 100));
        mainPanel.add(datePanel, BorderLayout.NORTH);
        
        labelDatePickerFirst = new JLabel("Seleziona una data di partenza:");
        labelDatePickerFirst.setFont(new Font("Tahoma", Font.PLAIN, 14));
        datePanel.add(labelDatePickerFirst);
        
        datepickerFirst = new DatePicker();
        datepickerFirst.getComponentToggleCalendarButton().setForeground(SystemColor.textText);
        datepickerFirst.getComponentDateTextField().setToolTipText("Seleziona data di partenza per dati");
        datepickerFirst.getComponentToggleCalendarButton().setBackground(SystemColor.activeCaption);
        datepickerFirst.getComponentDateTextField().setFont(new Font("Tahoma", Font.PLAIN, 13));
        datePanel.add(datepickerFirst);
        
        labelDatePickerLast = new JLabel("Seleziona una data di arrivo:");
        labelDatePickerLast.setFont(new Font("Tahoma", Font.PLAIN, 14));
        datePanel.add(labelDatePickerLast);
        
        datepickerLast = new DatePicker();
        datepickerLast.getComponentToggleCalendarButton().setForeground(UIManager.getColor("text"));
        datepickerLast.getComponentDateTextField().setToolTipText("Seleziona data d'arrivo per i dati");
        datepickerLast.getComponentToggleCalendarButton().setBackground(SystemColor.activeCaption);
        datepickerLast.getComponentDateTextField().setFont(new Font("Tahoma", Font.PLAIN, 13));
        datePanel.add(datepickerLast);
        
        comboBoxDatiGrafico = new JComboBox<>();
        comboBoxDatiGrafico.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        comboBoxDatiGrafico.setToolTipText("Dato da visualizzare");
        comboBoxDatiGrafico.setBackground(SystemColor.inactiveCaption);
        comboBoxDatiGrafico.setForeground(SystemColor.textText);
        comboBoxDatiGrafico.setFont(new Font("Tahoma", Font.PLAIN, 13));
        datePanel.add(comboBoxDatiGrafico);
        comboBoxDatiGrafico.addItem(DatiDaVisualizzareEnum.TEMPOLAVORO.getIndex()+ " " + DatiDaVisualizzareEnum.TEMPOLAVORO.getItemName());
        comboBoxDatiGrafico.addItem(DatiDaVisualizzareEnum.ENTRATE.getIndex()+ " " + DatiDaVisualizzareEnum.ENTRATE.getItemName());

        btnVisualizzaDati = new JButton("Visualizza dati");
        btnVisualizzaDati.setForeground(SystemColor.textText);
        btnVisualizzaDati.setBackground(SystemColor.activeCaption);
        btnVisualizzaDati.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
       
        datePanel.add(btnVisualizzaDati);
        
        btnReset = new JButton("Reset grafico");
        btnReset.setForeground(SystemColor.textText);
        btnReset.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
        btnReset.setBackground(SystemColor.activeCaption);
        datePanel.add(btnReset);
        
        btnDeleteLine = new JButton("Elimina ultima linea");
        btnDeleteLine.setForeground(SystemColor.textText);
        btnDeleteLine.setFont(new Font("Trebuchet MS", Font.PLAIN, 13));
        btnDeleteLine.setBackground(SystemColor.activeCaption);
        datePanel.add(btnDeleteLine);
        
        
        XYChart chart = new XYChartBuilder().title("DATI VISUALIZZATI").theme(ChartTheme.GGPlot2).build();
        this.setChartStyle(chart);
        chartPanel = new XChartPanel<XYChart>(chart);
        mainPanel.add(chartPanel, BorderLayout.CENTER);
        
        btnVisualizzaDati.addActionListener(e->{
               
            try {
                ctrl.addLine(datepickerFirst, datepickerLast, (comboBoxDatiGrafico.getSelectedIndex())+1, mainPanel,chart);
            } catch (DateException dateExc) {
                // TODO Auto-generated catch block
                   System.out.println(dateExc.getMessage());
                   }
       
        });
        
        btnReset.addActionListener(e->{
            try {
                   Integer option = JOptionPane.showConfirmDialog(chartPanel, "Sei Sicuro di effettuare il reset del grafico?", "ATTENZIONE!", 2);
                   if(option.equals(JOptionPane.YES_NO_OPTION))
                       ctrl.resetChart(chart, mainPanel);
                    }catch(ChartException chExc) {
                       System.out.println(chExc.getMessage() + "in Reset.");
                }
            });
            
        btnDeleteLine.addActionListener(e->{
            try {
                ctrl.deleteLast(chart, chartPanel);
            }
            catch(ChartException chExc) {
                System.out.println(chExc.getMessage() + "in deleteLine.");
            }
        });
    }
    
    public void display() {
       final Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
       setSize((int) (dimension.getWidth()*(AdministratorChartsView.PERCENT)) , (int)(dimension.getHeight() * AdministratorChartsView.PERCENT));
       setVisible(true);
       setResizable(true);
    }
    
    /**
    * Set the chart's style.
    *
    * @param chart
    */
    
    public  void setChartStyle(XYChart chart) {
        chart.setXAxisTitle("Data");
        chart.getStyler().setChartBackgroundColor(SystemColor.window)
                            .setChartTitleBoxBackgroundColor(SystemColor.activeCaption)
                            .setChartTitlePadding(10)
                            .setLegendBackgroundColor(SystemColor.activeCaption)
                            .setLegendPosition(LegendPosition.OutsideE);
        chart.getStyler().setAxisTickLabelsColor(Color.lightGray)
                            .setAxisTickPadding(10); 
        chart.getStyler().setPlotBackgroundColor(SystemColor.window);  
        chart.getStyler().setPlotGridLinesColor(Color.lightGray);
        chart.getStyler().setAxisTicksMarksVisible(false);
        chart.getStyler().setXAxisTicksVisible(false);
        chart.getStyler().setYAxisTicksVisible(false);
        chart.getStyler().setDecimalPattern("€/m #,###");
        chart.getStyler().setDatePattern("dd/MMM/yyyy");
 }
}
