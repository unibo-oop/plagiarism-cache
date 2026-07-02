package viewimpl.manageprogrammingfilms.factory;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import controller.managefilms.FilmsController;
import view.manageprogrammingfilms.factory.ScheduleFilmsFactory;


public final class ScheduleFilmsFactoryImpl implements ScheduleFilmsFactory {

    private static final int TOP_EMPTY_BORDER_PANEL = 20;
    private static final int LEFT_EMPTY_BORDER_PANEL = 10;
    private static final int BOTTOM_EMPTY_BORDER_PANEL = 20;
    private static final int RIGHT_EMPTY_BORDER_PANEL = 20;

    private static final int TOP_EMPTY_BORDER_BUTTON_PANEL = 10;
    private static final int LEFT_EMPTY_BORDER_BUTTON_PANEL = 40;
    private static final int BOTTOM_EMPTY_BORDER_BUTTON_PANEL = 10;
    private static final int RIGHT_EMPTY_BORDER_BUTTON_PANEL = 40;
    /** 
     * {@inheritDoc}
     * */
    @Override
    public JPanel getBottomPanel(final ActionListener al) {
        final JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(TOP_EMPTY_BORDER_PANEL, LEFT_EMPTY_BORDER_PANEL, BOTTOM_EMPTY_BORDER_PANEL, RIGHT_EMPTY_BORDER_PANEL));
        final JPanel buttonPanel = new JPanel();
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(TOP_EMPTY_BORDER_BUTTON_PANEL, LEFT_EMPTY_BORDER_BUTTON_PANEL, BOTTOM_EMPTY_BORDER_BUTTON_PANEL, RIGHT_EMPTY_BORDER_BUTTON_PANEL));
        final JButton scheduleButton = new JButton("Schedule");
        scheduleButton.addActionListener(al);
        buttonPanel.add(scheduleButton);
        panel.add(buttonPanel, scheduleButton);
        return panel;
    }
    /** 
     * {@inheritDoc}
     * */
    @Override
    public TimePanel getTimePanel() {
        return new TimePanel();
    }
    /** 
     * {@inheritDoc}
     * */
    @Override
    public DatePanel getDatePanel() {
        return new DatePanel();
    }
    /** 
     * {@inheritDoc}
     * */
    @Override
    public InfoProgrammationPanel getInfoProgrammationPanel(final FilmsController filmsController) {
        return new InfoProgrammationPanel(filmsController);
    }


}
