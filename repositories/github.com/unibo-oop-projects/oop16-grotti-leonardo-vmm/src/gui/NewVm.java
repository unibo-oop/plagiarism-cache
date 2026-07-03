package gui;

import basicgui.MyButton;
import basicgui.MyFrame;
import basicgui.MyPanel;
import handler.ActionListenerHandler;
import handler.CaseHandler;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import supportfiles.SupportMethods;

public class NewVm extends MyFrame implements BasicGuiInterface {

  private static final long serialVersionUID = 1254618532721024725L;

  private final Boolean newOrUpdate;
  private List<MyPanel> panelList;
  private List<MyPanel> subPanelList;
  private List<MyPanel> shelfPanelList;
  private List<MyButton> btnList;
  private List<JTextField> txtList;
  private String name;
  private String desc;
  private String pos;
  private MyButton addShelf;
  private MyButton addVm;
  private MyButton back;

  /**
   * Costruttore tipo 1 della classe.
   */
  public NewVm() {
    super("Create new Vending Machine");
    this.newOrUpdate = true;
    setUpData("", "", "");
  }

  /**
   * Costruttore tipo 2 della classe.
   * @param name nome.
   * @param desc descrizione.
   * @param pos posizione.
   */
  public NewVm(final String name, final String desc, final String pos) {
    super("Modify a Vending Machine");
    this.newOrUpdate = false;
    setUpData(name, desc, pos);
  }

  @Override
  public void run() {
    init();
    setGraphic();
    setUpViusalData();
  }

  @Override
  public void reload() {
    this.getMainPanel().removeAll();
    init();
    setGraphic();
    setUpViusalData();
    this.getMainPanel().updateUI();
  }

  private void init() {
    this.panelList = new ArrayList<>();
    this.subPanelList = new ArrayList<>();
    this.shelfPanelList = new ArrayList<>();
    this.btnList = new ArrayList<>();
    this.txtList = new ArrayList<>();
    this.addShelf = new MyButton("add a shelf");
    this.addVm = new MyButton("addVm");
    this.back = new MyButton("back");
    if (this.newOrUpdate) {
      this.addShelf.setEnabled(false);
    }
    otherButtonActions();
    addShelfAction();
  }

  private void setGraphic() {
    final MyPanel mainPanel = new MyPanel(null);
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    mainPanel.add(textData());
    mainPanel.add(shelfPanel());
    mainPanel.add(otherButton());
    btnFunctions();
    this.add(mainPanel);
  }

  private MyPanel textData() {
    final MyPanel panel = new MyPanel(null);
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    for (int i = 0; i < 3; i++) {
      final MyPanel subPanel = new MyPanel(new BorderLayout());
      final JLabel lbl = new JLabel(i == 0 ? "Name" : i == 1 ? "Description" : "Position");
      final JTextField txt = new JTextField();
      subPanel.add(lbl);
      subPanel.add(txt);
      txtList.add(txt);
      panel.add(subPanel);
      subPanelList.add(subPanel);
    }
    panel.add(shelfAdd());
    panelList.add(panel);
    return panel;
  }

  private JScrollPane shelfPanel() {
    final MyPanel panel = new MyPanel(null);
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    if (!this.newOrUpdate) {
      for (int s = 0; s < SupportMethods.getAllVmShelf(this.name).size(); s++) {
        final MyPanel containerPanel = new MyPanel(null);
        containerPanel.setLayout(new BoxLayout(containerPanel, BoxLayout.X_AXIS));
        for (int i = 0; i < 2; i++) {
          final MyPanel sPanelLbl = new MyPanel(new BorderLayout());
          final JLabel lbl = new JLabel(
              i == 0 ? SupportMethods.getAllVmShelf(this.name).get(s).getNumberOfSlots().toString()
                  : SupportMethods.getAllVmShelf(this.name).get(s).getProdPerSlot().toString());
          sPanelLbl.add(lbl);
          containerPanel.add(sPanelLbl);
        }
        for (int i = 0; i < 2; i++) {
          final MyPanel sPanelBtn = new MyPanel(new BorderLayout());
          final MyButton btn = new MyButton(i == 0 ? "Controll Shelf Products" : "Delete Shelf");
          sPanelBtn.add(btn);
          this.btnList.add(btn);
          containerPanel.add(sPanelBtn);
        }
        shelfPanelList.add(containerPanel);
        panel.add(containerPanel);
      }
    }
    return new JScrollPane(panel);
  }

  private void btnFunctions() {
    for (final MyButton myButton : btnList) {
      myButton.addActionListener(e -> {
        final ActionListenerHandler actionListenerHandler = new ActionListenerHandler();
        final List<Object> obj = new ArrayList<>();
        final Integer index = btnList.indexOf(myButton) / 2;
        obj.add(this.name);
        obj.add(index);
        if (btnList.indexOf(myButton) % 2 == 0) {
          actionListenerHandler.buttonHandler(CaseHandler.SHELFMANAGEMENT, obj);
        } else {
          actionListenerHandler.buttonHandler(CaseHandler.SHELFDESTROY, obj);
        }
        obj.clear();
        obj.add(this);
        if (btnList.indexOf(myButton) % 2 != 0) {
          actionListenerHandler.buttonHandler(CaseHandler.RELOAD, obj);
        } else {
          actionListenerHandler.buttonHandler(CaseHandler.CLOSETHIS, obj);
        }
      });
    }
  }

  private void addShelfAction() {
    this.addShelf.addActionListener(e -> {
      final ActionListenerHandler actionListenerHandler = new ActionListenerHandler();
      final List<Object> obj = new ArrayList<>();
      obj.add(this);
      obj.add(this.name);
      actionListenerHandler.buttonHandler(CaseHandler.SHELFCREATE, (ArrayList<?>) obj);
      obj.clear();
      obj.add(this);
      actionListenerHandler.buttonHandler(CaseHandler.CLOSETHIS, obj);
      this.dispose();
    });
  }

  private void otherButtonActions() {
    this.addVm.addActionListener(e -> {
      final ActionListenerHandler actionListenerHandler = new ActionListenerHandler();
      final List<Object> obj = new ArrayList<>();
      obj.add(this.txtList.get(0).getText());
      obj.add(this.txtList.get(1).getText());
      obj.add(this.txtList.get(2).getText());
      if (this.newOrUpdate) {
        actionListenerHandler.buttonHandler(CaseHandler.CREATEVM, (ArrayList<?>) obj);
      } else {
        obj.add(SupportMethods.getVmIndex(this.name));
        actionListenerHandler.buttonHandler(CaseHandler.UPDATEVM, (ArrayList<?>) obj);
      }
      goBack();
    });
    this.back.addActionListener(e -> {
      goBack();
    });
  }

  private MyPanel otherButton() {
    final MyPanel mainPanel = new MyPanel(null);
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    final MyPanel bPanel = new MyPanel(new BorderLayout());
    final MyPanel sPanel = new MyPanel(new BorderLayout());
    bPanel.add(this.back);
    sPanel.add(this.addVm);
    mainPanel.add(sPanel);
    mainPanel.add(bPanel);
    return mainPanel;
  }

  private MyPanel shelfAdd() {
    final MyPanel panel = new MyPanel(new BorderLayout());
    panel.add(this.addShelf);
    return panel;
  }

  private void setUpViusalData() {
    this.txtList.get(0).setText(this.name);
    this.txtList.get(1).setText(this.desc);
    this.txtList.get(2).setText(this.pos);
  }

  private void setUpData(final String name, final String desc, final String pos) {
    this.name = name;
    this.desc = desc;
    this.pos = pos;
  }

  private void goBack() {
    final ActionListenerHandler actionListenerHandler = new ActionListenerHandler();
    final List<Object> obj = new ArrayList<>();
    obj.add(new MainMenu(SupportMethods.getUsername(), SupportMethods.getAuthorization()));
    actionListenerHandler.buttonHandler(CaseHandler.BACK, obj);
    obj.clear();
    obj.add(this);
    actionListenerHandler.buttonHandler(CaseHandler.CLOSETHIS, obj);    
  }
}
