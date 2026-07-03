package gui;

import basicgui.MyButton;
import basicgui.MyPanel;
import handler.ActionListenerHandler;
import handler.CaseHandler;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Label;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import supportfiles.SupportMethods;

public class ProductArchetypeManager extends MyPanel {

  private static final long serialVersionUID = 1L;

  private JTextField nameTxt;
  private JTextField descTxt;
  private MyButton create;
  private List<Object> princPanelList;
  private List<Object> panelList;

  /**
   * Costruttore della classe.
   */
  public ProductArchetypeManager() {
    super(null);
    init();
    setGraphics();
  }

  protected void reload() {
    this.removeAll();
    init();
    setGraphics();
  }

  private void init() {
    final Label nameLbl = new Label();
    this.nameTxt = new JTextField();
    final Label descLbl = new Label();
    this.descTxt = new JTextField();
    this.create = new MyButton("Create new AP");
    this.panelList = new ArrayList<>();
    this.princPanelList = new ArrayList<>();
    nameLbl.setText("Name: ");
    descLbl.setText("Desc: ");
    this.princPanelList.add(nameLbl);
    this.princPanelList.add(nameTxt);
    this.princPanelList.add(descLbl);
    this.princPanelList.add(descTxt);
    this.princPanelList.add(create);
    btnCreateAction();
  }

  private void setGraphics() {
    int count = 0;
    final MyPanel panel0 = new MyPanel(null);
    panel0.setLayout(new BoxLayout(panel0, BoxLayout.Y_AXIS));
    for (int i = 0; i < 2; i++) {
      final MyPanel sPanel0 = new MyPanel(null);
      sPanel0.setLayout(new BoxLayout(sPanel0, BoxLayout.X_AXIS));
      for (int j = 0; j < 2; j++) {
        final MyPanel sPanel1 = new MyPanel(new BorderLayout());
        sPanel1.add((Component) this.princPanelList.get(count));
        sPanel0.add(sPanel1);
        count++;
      }
      panel0.add(sPanel0);
    }
    final MyPanel sPanel2 = new MyPanel(new BorderLayout());
    sPanel2.add(create);
    panel0.add(sPanel2);
    final MyPanel panel1 = new MyPanel(null);
    panel1.setLayout(new BoxLayout(panel1, BoxLayout.Y_AXIS));
    for (int i = 0; i < SupportMethods.getAllProdList().size(); i++) {
      final MyPanel sPanel0 = new MyPanel(null);
      sPanel0.setLayout(new BoxLayout(sPanel0, BoxLayout.X_AXIS));
      final MyPanel subSPanel0 = new MyPanel(new BorderLayout());
      final MyPanel subSPanle1 = new MyPanel(new BorderLayout());
      final Label namelbl = new Label();
      final Label descLbl = new Label();
      namelbl.setText(SupportMethods.getAllProdList().get(i).getPaName());
      descLbl.setText(SupportMethods.getAllProdList().get(i).getDescription());
      subSPanel0.add(namelbl);
      subSPanle1.add(descLbl);
      sPanel0.add(subSPanel0);
      sPanel0.add(subSPanle1);
      panel1.add(sPanel0);
      this.panelList.add(sPanel0);
    }
    final JScrollPane sPanel = new JScrollPane(panel1);
    this.add(panel0);
    this.add(sPanel);
  }

  private void btnCreateAction() {
    this.create.addActionListener(e -> {
      final List<Object> obj = new ArrayList<>();
      final ActionListenerHandler actionListenerHandler = new ActionListenerHandler();
      obj.add(nameTxt.getText());
      obj.add(descTxt.getName());
      obj.add(1);
      obj.add(SwingUtilities.getWindowAncestor(this));
      actionListenerHandler.buttonHandler(CaseHandler.CREATEAP, obj);
    });
  }

}
