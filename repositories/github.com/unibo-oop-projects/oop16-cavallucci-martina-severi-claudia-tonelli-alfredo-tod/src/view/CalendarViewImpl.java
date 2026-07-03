package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import controller.CalendarController;

/**
 * A CalendarPanel that the day can be clicked.
 *
 */
public class CalendarViewImpl extends JPanel implements CalendarView, 
ActionListener, MouseListener {
    private static final long serialVersionUID = 3940127606374510139L;
    private final JPanel buttonnorth;
    private final JPanel buttonsouth;
    private final JPanel calendarCenter;
    private final JPanel tablePanel;
    private final JTextPane[] textPanes;
    private final Color background1;
    private final Color background2;
    private final Color background3;
    private final JButton btnRefresh;
    private final JComboBox<String> yearSelectCombo;
    private final JComboBox<String> monthSelectCombo;
    private final JButton btnShowCalendar;
    private final JPanel dayPanel;
    private CalendarController observer;
    private static final int DF1 = 13;
    private static final int DF2 = 25;
    private static final int NUMDAY = 42;
    private static final int NUMDAYW = 7;
    private static final int NUMYEARS = 2200;
    private static final int CURRENTYEAR = 2017;
    private static final int NUMMONTH = 12;
    private static final int DIF = NUMYEARS - CURRENTYEAR + 1;
    private static final int[] MONTH = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static final int BISESTILMONTH = 29;
    private static final int CONTROLYEAR = 400;

    /**
     * The costructor for the panel.
     * @param ctrl
     *        the CalendarController that controls the calendarPanel.
     */
    public CalendarViewImpl(final CalendarController ctrl) {

        this.observer = ctrl;
        this.background1 = new Color(ViewColor.light.getRed(),
                ViewColor.light.getGreen(), ViewColor.light.getBlue());
        this.background2 = new Color(ViewColor.lightblue.getRed(),
                ViewColor.lightblue.getGreen(), ViewColor.lightblue.getBlue());
        this.background3 = new Color(ViewColor.dark.getRed(),
                ViewColor.dark.getGreen(), ViewColor.dark.getBlue());

        this.setLayout(new BorderLayout());
        this.textPanes = new JTextPane[NUMDAY];

        this.buttonnorth = new JPanel();
        this.buttonnorth.setLayout(new GridLayout(1, 4));
        this.buttonnorth.setBackground(this.background1);
        this.add(this.buttonnorth, BorderLayout.NORTH);

        this.buttonsouth = new JPanel();
        this.buttonsouth.setLayout(new BorderLayout());
        this.buttonsouth.setBorder(new EmptyBorder(DF2, DF2, DF2, DF2));
        this.buttonsouth.setBackground(this.background1);
        this.add(this.buttonsouth, BorderLayout.SOUTH);

        this.calendarCenter = new JPanel();
        this.calendarCenter.setBackground(this.background1);
        this.calendarCenter.setLayout(new BorderLayout());
        this.add(this.calendarCenter, BorderLayout.CENTER);

        this.calendarCenter.setBackground(this.background1);

        this.tablePanel = new JPanel();
        this.tablePanel.setBackground(this.background1);
        this.tablePanel.setLayout(new BorderLayout());

        this.calendarCenter.add(this.tablePanel, BorderLayout.CENTER);

        this.btnRefresh = new JButton("Refresh");

        this.btnRefresh.setForeground(this.background2);
        this.btnRefresh.addActionListener(this);

        this.buttonsouth.add(this.btnRefresh, BorderLayout.WEST);

        this.yearSelectCombo = new JComboBox<>(this.getYears());
        this.yearSelectCombo.setBackground(this.background2);

        this.buttonnorth.add(this.yearSelectCombo, BorderLayout.NORTH);

        this.monthSelectCombo = new JComboBox<>(this.getMonth());
        this.monthSelectCombo.setBackground(this.background2);

        this.buttonnorth.add(this.monthSelectCombo, BorderLayout.NORTH);

        this.btnShowCalendar = new JButton("Show");
        this.btnShowCalendar.setBackground(this.background2);

        this.btnShowCalendar.addActionListener(this);

        this.buttonnorth.add(this.btnShowCalendar, BorderLayout.NORTH);
        this.buttonnorth.setBorder(new EmptyBorder(DF2, DF2, DF2, DF2));

        this.dayPanel = new JPanel();
        this.dayPanel.setBackground(this.background1);

        this.tablePanel.add(this.dayPanel);
        this.dayPanel.setLayout(new GridLayout(NUMDAYW, NUMDAY, 0, 0));

        final JLabel[] label = new JLabel[NUMDAYW];
        final String[] giorni = { "Sun", "Mon", "Tue", "Wes", "Thu", "Fri", "Sat" };
        for (int i = 0; i < label.length; i++) {
            label[i] = new JLabel(giorni[i]);
            label[i].setHorizontalAlignment(JLabel.CENTER);
            label[i].setBackground(this.background1);
            label[i].setFont(new Font("Euphemia", Font.BOLD, DF1));
            label[i].setForeground(this.background3);
            this.dayPanel.add(label[i], BorderLayout.NORTH);
        }
        for (int i = 0; i < this.textPanes.length; i++) {
            this.textPanes[i] = new JTextPane();
            this.textPanes[i].setEditable(true);
            this.textPanes[i].setBackground(this.background1);
            this.textPanes[i].setBorder(BorderFactory.createLineBorder(this.background2));
            this.textPanes[i].setEditable(false);
            this.dayPanel.add(this.textPanes[i]);
            this.textPanes[i].addMouseListener(this);

        }
    }

    private String[] getMonth() {

        final String[] stri = { "January", "February", "March",
                "April", "May", "June", "July", "Agust", "September",
                "October", "November", "December" };
        return stri;
    }

    private String[] getYears() {

        final String[] str = new String[DIF];
        for (int i = CURRENTYEAR, j = 0; i <= NUMYEARS; i++, j++) {
            str[j] = String.valueOf(i);
        }
        return str;
    }
    @Override
    public void init() {
      this.setEnabled(true);
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        final Object isPressed = e.getSource();
         if (isPressed == this.btnShowCalendar || isPressed == this.btnRefresh) {
            final int yearSelected = Integer.parseInt(this.yearSelectCombo.getSelectedItem().toString());
            final int monthSelected = this.monthSelectCombo.getSelectedIndex();
           this.showCalander(yearSelected, monthSelected);
        }
    }

    /**
     * .
     * @param yearSelected
     *          year
     * @param monthSelected
     *         month
     */
    protected void showCalander(final int yearSelected, final int monthSelected) {
        final Map<Integer, String> map = this.observer.loadEvent(yearSelected, monthSelected + 1);
          final int[] monthDay = MONTH;
          int year = CURRENTYEAR;
          int month = 0;
          int day = NUMDAYW;
          while (searchDate(yearSelected, monthSelected, year, month)) {
              if (month == 1 && leapYear(year)) {
                  day += BISESTILMONTH;
              } else {
                  day += monthDay[month]; 
              }
              month++; 
              if (month == NUMMONTH) {
                  month = 0; 
                  year++; 
              }
              day = day % NUMDAYW;
          }
          for (int i = 0; i < textPanes.length; i++) {
              textPanes[i].setText(""); 
          } 
          int last = monthDay[monthSelected];
          if (monthSelected == 1 && leapYear(yearSelected)) {
              last++; 
          } 
          for (int i = 1, j = day; i <= last; i++, j++) {
              textPanes[j].setText(String.valueOf(i)); 

              for (final Map.Entry<Integer, String> entry : map.entrySet()) {
                  final Integer key = entry.getKey();
                  final String value = entry.getValue().toString();
                  if (textPanes[j].getText().equals(key.toString())) {
                     System.out.println(value);
                     textPanes[j].setText(key.toString() + "\n" + value);

                  }

              }
          }
     }

      private boolean leapYear(final int year) { 
          boolean ans = false; 
          if (year % 4 == 0) {
             ans = true; 
          } 
          if (year % 100 == 0) {
             ans = false; 
          }
          if (year % CONTROLYEAR == 0) {
              return true; 
          }
          return ans; 
      }

      private boolean searchDate(final int yearSelect, final int monthSelect, 
              final int year, final int month) { 
        return !(yearSelect == year && monthSelect == month);
      } 

    @Override
    public void mouseClicked(final java.awt.event.MouseEvent e) {
        if (e.getClickCount() == 2) {
            final JTextPane t = (JTextPane) e.getSource();
            final int daySelected = Integer.parseInt(t.getText().substring(0, 2).trim());
            final int yearSelected = Integer.parseInt(this.yearSelectCombo.getSelectedItem()
                    .toString());

            final int monthSelected = this.monthSelectCombo.getSelectedIndex() + 1;


            this.observer.goToSummary(daySelected, monthSelected, yearSelected);
        }

    }

    @Override
    public void mousePressed(final java.awt.event.MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(final java.awt.event.MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(final java.awt.event.MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(final java.awt.event.MouseEvent e) {
        // TODO Auto-generated method stub

    }


}