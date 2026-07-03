package gui;

import basicgui.MyButton;
import basicgui.MyFrame;
import basicgui.MyPanel;
import handler.ActionListenerHandler;
import handler.CaseHandler;
import java.awt.BorderLayout;
import java.awt.Label;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import supportfiles.SupportMethods;

public class ManageSlot extends MyFrame implements BasicGuiInterface {

  private static final long serialVersionUID = 1L;

  private final Integer shelf;
  private final String vm;
  private Label vmLbl;
  private Label shelfLbl;
  private JTextField posSlotTxt;
  private MyButton addSlotBtn;
  private MyButton backBtn;
  private List<Label> slotList;
  private List<MyButton> deleteList;
  private List<MyButton> selectProdList;

  /**
   * Costruttore della classe.
   * @param vm nome del distributore automatico
   * @param shelf indice dello scaffale 
   */
  public ManageSlot(final String vm, final Integer shelf) {
    super("ManageSlot");
    this.shelf = shelf;
    this.vm = vm;
  }

  @Override
  public void run() {
    init();
    setGraphics();
  }

  @Override
  public void reload() {
    this.remove(this.getMainPanel());
    init();
    setGraphics();
    this.validate();
    this.repaint();
  }

  private void init() {
    this.posSlotTxt = new JTextField();
    this.addSlotBtn = new MyButton("Add new Slot");
    this.backBtn = new MyButton("Back");
    this.slotList = new ArrayList<>();
    this.deleteList = new ArrayList<>();
    this.selectProdList = new ArrayList<>();
    this.vmLbl = new Label(this.vm);
    this.shelfLbl = new Label(this.shelf.toString());
  }

  private void setGraphics() {
    final MyPanel mainPanel = new MyPanel(null);
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    mainPanel.add(firstPanel());
    mainPanel.add(secondPanel());
    mainPanel.add(thirdPanel());
    this.setPanel(mainPanel);
    btnDeleteAction();
    btnSelectProdAction();
    btnBackAction();
    btnAddSlotAction();
  }

  private MyPanel firstPanel() {
    final MyPanel panel = new MyPanel(null);
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    final MyPanel panel0 = new MyPanel(null);
    panel0.setLayout(new BoxLayout(panel0, BoxLayout.X_AXIS));
    final MyPanel panel1 = new MyPanel(null);
    panel1.setLayout(new BoxLayout(panel1, BoxLayout.X_AXIS));
    final MyPanel panel2 = new MyPanel(null);
    panel2.setLayout(new BoxLayout(panel2, BoxLayout.X_AXIS));
    final MyPanel panel3 = new MyPanel(new BorderLayout());
    final MyPanel s0Panel0 = new MyPanel(new BorderLayout());
    final MyPanel s0Panel1 = new MyPanel(new BorderLayout());
    final MyPanel s1Panel0 = new MyPanel(new BorderLayout());
    final MyPanel s1Panel1 = new MyPanel(new BorderLayout());
    final MyPanel s2Panel0 = new MyPanel(new BorderLayout());
    final MyPanel s2Panel1 = new MyPanel(new BorderLayout());
    final Label vmLbl = new Label("Vending Machine: ");
    final Label shelfLbl = new Label("Shelf: ");
    final Label posSlotLbl = new Label("Slot number: ");
    s0Panel0.add(vmLbl);
    s0Panel1.add(this.vmLbl);
    s1Panel0.add(shelfLbl);
    s1Panel1.add(this.shelfLbl);
    s2Panel0.add(posSlotLbl);
    s2Panel1.add(this.posSlotTxt);
    panel0.add(s0Panel0);
    panel0.add(s0Panel1);
    panel1.add(s1Panel0);
    panel1.add(s1Panel1);
    panel2.add(s2Panel0);
    panel2.add(s2Panel1);
    panel3.add(addSlotBtn);
    panel.add(panel0);
    panel.add(panel1);
    panel.add(panel2);
    panel.add(panel3);
    return panel;
  }

  private JScrollPane secondPanel() {
    final MyPanel panel = new MyPanel(null);
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    for (int s = 0; s < SupportMethods.getSlotFromShelf(this.vm, this.shelf).size(); s++) {
      final MyPanel sPanel = new MyPanel(null);
      sPanel.setLayout(new BoxLayout(sPanel, BoxLayout.X_AXIS));
      final MyPanel sPanel0 = new MyPanel(new BorderLayout());
      final MyPanel sPanel1 = new MyPanel(new BorderLayout());
      final MyPanel sPanel2 = new MyPanel(new BorderLayout());
      final Label slotLbl = new Label(
          SupportMethods.getSlotFromShelf(this.vm, this.shelf).get(s).getPos().toString());
      final MyButton deleteBtn = new MyButton("Delete");
      final MyButton selectProdBtn = new MyButton("Prod select");
      sPanel0.add(slotLbl);
      this.slotList.add(slotLbl);
      sPanel1.add(deleteBtn);
      this.deleteList.add(deleteBtn);
      sPanel2.add(selectProdBtn);
      this.selectProdList.add(selectProdBtn);
      sPanel.add(sPanel0);
      sPanel.add(sPanel1);
      sPanel.add(sPanel2);
      panel.add(sPanel);
    }
    return new JScrollPane(panel);
  }

  private MyPanel thirdPanel() {
    final MyPanel panel = new MyPanel(new BorderLayout());
    panel.add(backBtn);
    return panel;
  }

  private void btnDeleteAction() {
    for (final MyButton btn : this.deleteList) {
      btn.addActionListener(e -> {
        final ActionListenerHandler actionListenerHandler = new ActionListenerHandler();
        final List<Object> obj = btnProdActionSupport(true, btn);
        actionListenerHandler.buttonHandler(CaseHandler.SLOTDESTROY, obj);
        obj.clear();
        obj.add(this);
        actionListenerHandler.buttonHandler(CaseHandler.RELOAD, obj);
      });
    }
  }

  private void btnSelectProdAction() {
    for (final MyButton btn : this.selectProdList) {
      btn.addActionListener(e -> {
        final ActionListenerHandler actionListenerHandler = new ActionListenerHandler();
        final List<Object> obj = btnProdActionSupport(false, btn);
        obj.add(this.selectProdList.indexOf(btn));
        actionListenerHandler.buttonHandler(CaseHandler.SELECTPROD, obj);
        obj.clear();
        obj.add(this);
        actionListenerHandler.buttonHandler(CaseHandler.CLOSETHIS, obj);
      });
    }
  }
  
  private List<Object> btnProdActionSupport(final Boolean flag, final MyButton btn) {
    final List<Object> obj = new ArrayList<>();
    obj.add(this.vm);
    obj.add(this.shelf);
    if (flag) {
      obj.add(this.deleteList.indexOf(btn));
    } else {
      obj.add(Integer.parseInt(this.slotList.get(this.selectProdList.indexOf(btn)).getText()));
    }
    return obj;
  }

  private void btnBackAction() {
    this.backBtn.addActionListener(e -> {
      final ActionListenerHandler actionListenerHandler = new ActionListenerHandler();
      final List<Object> obj = new ArrayList<>();
      obj.add(new NewVm(SupportMethods.getVm(this.vm).getVmName(),
          SupportMethods.getVm(this.vm).getDescription(),
          SupportMethods.getVm(this.vm).getPosition()));
      actionListenerHandler.buttonHandler(CaseHandler.BACK, obj);
      obj.clear();
      obj.add(this);
      actionListenerHandler.buttonHandler(CaseHandler.CLOSETHIS, obj);
    });
  }

  private void btnAddSlotAction() {
    this.addSlotBtn.addActionListener(e -> {
      try {
        final ActionListenerHandler actionListenerHandler = new ActionListenerHandler();
        final List<Object> obj = new ArrayList<>();
        obj.add(this.vm);
        obj.add(this.shelf);
        obj.add(Integer.parseInt(this.posSlotTxt.getText()));
        actionListenerHandler.buttonHandler(CaseHandler.SLOTCREATE, obj);
        obj.clear();
        obj.add(this);
        actionListenerHandler.buttonHandler(CaseHandler.RELOAD, obj);
      } catch (Exception exception) {
        System.out.println(exception);
      }
    });
  }
}
