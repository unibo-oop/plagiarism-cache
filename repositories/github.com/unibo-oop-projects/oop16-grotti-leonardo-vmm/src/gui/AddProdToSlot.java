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
import javax.swing.JTextField;
import supportfiles.SupportMethods;

public class AddProdToSlot extends MyFrame implements BasicGuiInterface {

  private static final long serialVersionUID = 1L;

  private List<Label> lblList;
  private List<MyButton> btnList;
  private final String vmName;
  private final Integer shelfIndex;
  private final Integer slot;
  private final Integer slotIndex;
  private JTextField qtTxt;
  private JTextField costTxt;
  private MyButton back;

  /**
   * Costruttore della classe.
   * @param vmName indica il nome del distributore.
   * @param shelfIndex indice dello scaffale.
   * @param slot posizione dello slot
   * @param slotIndex indice dello slot
   */
  public AddProdToSlot(final String vmName, final Integer shelfIndex, final Integer slot,
      final Integer slotIndex) {
    super("Prodotto nello slot");
    this.vmName = vmName;
    this.shelfIndex = shelfIndex;
    this.slot = slot;
    this.slotIndex = slotIndex;
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
    this.update(this.getMainPanel().getGraphics());
  }

  private void init() {
    this.btnList = new ArrayList<>();
    this.lblList = new ArrayList<>();
    this.qtTxt = new JTextField(SupportMethods.getQt(vmName, shelfIndex, slotIndex).toString());
    this.costTxt = new JTextField(SupportMethods.getCost(vmName, shelfIndex, slotIndex).toString());
    this.back = new MyButton("Back");
  }

  private void setGraphics() {
    final MyPanel panel0 = new MyPanel(null);
    panel0.setLayout(new BoxLayout(panel0, BoxLayout.Y_AXIS));
    final MyPanel panel1 = new MyPanel(null);
    panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
    final MyPanel panel2 = new MyPanel(null);
    panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
    final MyPanel panel3 = new MyPanel(null);
    panel3.setLayout(new BoxLayout(panel3, BoxLayout.Y_AXIS));
    final List<MyPanel> panelList = principalDataPanel();
    for (int i = 0; i < panelList.size(); i++) {
      panel1.add(panelList.get(i));
    }
    for (int i = 0; i < SupportMethods.getAllProdList().size(); i++) {
      panel2.add(prodPanel(i));
    }
    panel3.add(btnPanel());
    panel0.add(panel1);
    panel0.add(panel2);
    panel0.add(panel3);
    this.add(panel0);
    btnActions();
  }

  private List<MyPanel> principalDataPanel() {
    final List<MyPanel> panelList = new ArrayList<>();
    final MyPanel sPanel0 = new MyPanel(new BorderLayout());
    final MyPanel sPanel1 = new MyPanel(new BorderLayout());
    final MyPanel sPanel2 = new MyPanel(new BorderLayout());
    final MyPanel sPanel3 = new MyPanel(new BorderLayout());
    final MyPanel sPanel4 = new MyPanel(null);
    sPanel4.setLayout(new BoxLayout(sPanel4, BoxLayout.X_AXIS));
    final MyPanel sPanel5 = new MyPanel(null);
    sPanel5.setLayout(new BoxLayout(sPanel5, BoxLayout.X_AXIS));
    final MyPanel s4Panel0 = new MyPanel(new BorderLayout());
    final MyPanel s4Panel1 = new MyPanel(new BorderLayout());
    final MyPanel s5Panel0 = new MyPanel(new BorderLayout());
    final MyPanel s5Panel1 = new MyPanel(new BorderLayout());
    final Label lblName = new Label("Name: " + this.vmName);
    final Label lblShelf = new Label("Shelf: " + this.shelfIndex);
    final Label lblSlot = new Label("Slot: " + this.slot);
    final Label lblProd = new Label("Product: "
        + SupportMethods.getProd(this.vmName, this.shelfIndex, this.slot).getDescription());
    final Label lblQt = new Label("Quantity: ");
    final Label lblCost = new Label("Cost: ");
    sPanel0.add(lblName);
    sPanel1.add(lblShelf);
    sPanel2.add(lblSlot);
    sPanel3.add(lblProd);
    s4Panel0.add(lblQt);
    s4Panel1.add(this.qtTxt);
    s5Panel0.add(lblCost);
    s5Panel1.add(this.costTxt);
    sPanel4.add(s4Panel0);
    sPanel4.add(s4Panel1);
    sPanel5.add(s5Panel0);
    sPanel5.add(s5Panel1);
    panelList.add(sPanel0);
    panelList.add(sPanel1);
    panelList.add(sPanel2);
    panelList.add(sPanel3);
    panelList.add(sPanel4);
    panelList.add(sPanel5);
    return panelList;
  }

  private MyPanel prodPanel(final Integer i) {
    final MyPanel sPanel0 = new MyPanel(null);
    final MyPanel sPanel1 = new MyPanel(new BorderLayout());
    final MyPanel sPanel2 = new MyPanel(new BorderLayout());
    final Label lbl = new Label(SupportMethods.getAllProdList().get(i).getPaName());
    final MyButton btn = new MyButton("choose Product");
    sPanel0.setLayout(new BoxLayout(sPanel0, BoxLayout.X_AXIS));
    this.lblList.add(lbl);
    this.btnList.add(btn);
    sPanel1.add(lbl);
    sPanel2.add(btn);
    sPanel0.add(sPanel1);
    sPanel0.add(sPanel2);
    return sPanel0;
  }

  private MyPanel btnPanel() {
    final MyPanel btnPanel = new MyPanel(new BorderLayout());
    btnPanel.add(this.back);
    return btnPanel;
  }

  private void btnActions() {
    this.back.addActionListener(e -> {
      final ActionListenerHandler actionListenerHandler = new ActionListenerHandler();
      final List<Object> obj = new ArrayList<>();
      obj.add(new ManageSlot(this.vmName, this.shelfIndex));
      actionListenerHandler.buttonHandler(CaseHandler.BACK, obj);
      obj.clear();
      obj.add(this);
      actionListenerHandler.buttonHandler(CaseHandler.CLOSETHIS, obj);
    });
    for (int i = 0; i < this.btnList.size(); i++) {
      this.btnList.get(i).addActionListener(e -> {
        final ActionListenerHandler actionListenerHandler = new ActionListenerHandler();
        final List<Object> obj = new ArrayList<>();
        obj.add(this.vmName);
        obj.add(this.shelfIndex);
        obj.add(this.slotIndex);
        obj.add(SupportMethods.getAllProdList().get(this.btnList.indexOf(e.getSource())));
        obj.add(Integer.parseInt(this.qtTxt.getText()));
        obj.add(Float.parseFloat(this.costTxt.getText()));
        actionListenerHandler.buttonHandler(CaseHandler.SAVEPRODINSLOT, obj);
        obj.clear();
        obj.add(this);
        actionListenerHandler.buttonHandler(CaseHandler.RELOAD, obj);
      });
    }
  }

}
