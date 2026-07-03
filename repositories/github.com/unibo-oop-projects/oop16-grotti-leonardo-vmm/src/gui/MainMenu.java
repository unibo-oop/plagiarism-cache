package gui;

import basicgui.MyButton;
import basicgui.MyFrame;
import basicgui.MyPanel;
import handler.ActionListenerHandler;
import handler.CaseHandler;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import supportfiles.SupportMethods;


public class MainMenu extends MyFrame implements BasicGuiInterface {

  private static final long serialVersionUID = 2279297160329106769L;

  private List<JScrollPane> scrolPanelList;
  private List<MyPanel> panelList;
  private JTabbedPane tab;
  private MyButton logOut;
  private JLabel nik;

  /**
   * Costruttore della classe.
   * @param name nome dell'utente.
   * @param authorization autorizzazione dell'utente.
   */
  public MainMenu(final String name, final int authorization) {
    super("Vending Machines");
    SupportMethods.setUsername(name);
    SupportMethods.setAuthorization(authorization);
  }

  @Override
  public void reload() {
    ((BasicFromVm) panelList.get(0)).reload();
    ((BasicFromVm) panelList.get(1)).reload();
    ((ProductArchetypeManager) panelList.get(2)).reload();
  }

  @Override
  public void run() {
    init();
    setUpData();
    setGraphics();
  }

  private void init() {
    this.panelList = new ArrayList<>();
    this.scrolPanelList = new ArrayList<>();
  }

  private void setUpData() {
    for (int i = 0; i < 3; i++) {
      this.panelList.add(panel(i));
      final JScrollPane sPane = new JScrollPane(panelList.get(i));
      this.scrolPanelList.add(sPane);
    }
    tab = new JTabbedPane();
    tab.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    tab.addTab("Buy form Vending Machine", scrolPanelList.get(0));
    tab.addTab("Manage Vending Machine", scrolPanelList.get(1));
    tab.addTab("Product Archetype Manager", panelList.get(2));
    // tab.addTab("Eliminate Vending Machine", scrolPanelList.get(3));
    this.logOut = new MyButton("LogOut");
    this.nik = new JLabel("Welcome: " + SupportMethods.getUsername());
    setComponents();
    setLogOut();
  }

  private void setComponents() {
    if (SupportMethods.getAuthorization() < 1) {
      tab.setEnabledAt(1, false);
      tab.setEnabledAt(2, false);
    }
  }

  private MyPanel panel(final int i) {
    MyPanel panel;
    switch (i) {
      case 0:
        panel = new BasicFromVm(null, false);
        break;
      case 1:
        panel = new BasicFromVm(null, true);
        break;
      case 2:
        panel = new ProductArchetypeManager();
        break;
      default:
        panel = new MyPanel(new FlowLayout());
        break;
    }
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    return panel;
  }

  private void setGraphics() {
    final MyPanel mainPanel = new MyPanel(null);
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    mainPanel.add(this.nik);
    mainPanel.add(this.tab);
    mainPanel.add(this.logOut);
    mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
    this.setPanel(mainPanel);
  }

  private void setLogOut() {
    this.logOut.addActionListener(e -> {
      final ActionListenerHandler actionListenerHandler = new ActionListenerHandler();
      final List<Object> obj = new ArrayList<>();
      actionListenerHandler.buttonHandler(CaseHandler.LOGOUT, (ArrayList<?>) obj);
      obj.add(this);
      actionListenerHandler.buttonHandler(CaseHandler.CLOSETHIS, obj);
    });
  }
}
