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
import supportfiles.SupportMethods;

public class Buy extends MyFrame implements BasicGuiInterface {

  private static final long serialVersionUID = 1L;

  private final Integer index;
  private List<MyPanel> panelList;
  private List<MyButton> btnList;
  private MyButton back;

  public Buy(final Integer index) {
    super("Buy");
    this.index = index;
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
    panelList = new ArrayList<>();
    btnList = new ArrayList<>();
    back = new MyButton("Back");
    btnBackAction();
  }

  private void setGraphics() {
    final MyPanel panel = new MyPanel(null);
    final MyPanel panel0 = new MyPanel(null);
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel0.setLayout(new BoxLayout(panel0, BoxLayout.Y_AXIS));
    for (int s = 0; s < SupportMethods.getSlotsFromVmWithProd(this.index).size(); s++) {
      final MyPanel sPanel0 = new MyPanel(null);
      final MyPanel sPanel1 = new MyPanel(new BorderLayout());
      final MyPanel sPanel2 = new MyPanel(new BorderLayout());
      final MyPanel sPanel3 = new MyPanel(new BorderLayout());
      final MyPanel sPanel4 = new MyPanel(new BorderLayout());
      final MyPanel sPanel5 = new MyPanel(new BorderLayout());
      final Label nameLbl = new Label(
          SupportMethods.getSlotsFromVmWithProd(this.index).get(s).getProd().getPaName());
      final Label descLbl = new Label(
          SupportMethods.getSlotsFromVmWithProd(this.index).get(s).getProd().getDescription());
      final Label qtLbl = new Label(
          SupportMethods.getSlotsFromVmWithProd(this.index).get(s).getQt().toString());
      final Label costLbl = new Label();
      if (SupportMethods.getSlotsFromVmWithProd(this.index).get(s).getCost() != null) {
        costLbl
            .setText(SupportMethods.getSlotsFromVmWithProd(this.index).get(s).getCost().toString());
      } else {
        costLbl.setText("0");
      }
      final MyButton buyBtn = new MyButton("Buy");
      sPanel0.setLayout(new BoxLayout(sPanel0, BoxLayout.X_AXIS));
      sPanel1.add(nameLbl);
      sPanel2.add(descLbl);
      sPanel3.add(qtLbl);
      sPanel4.add(costLbl);
      sPanel5.add(buyBtn);
      sPanel0.add(sPanel1);
      sPanel0.add(sPanel2);
      sPanel0.add(sPanel3);
      sPanel0.add(sPanel4);
      sPanel0.add(sPanel5);
      panelList.add(sPanel0);
      btnList.add(buyBtn);
      panel0.add(sPanel0);
      btnAction(buyBtn);
    }
    final JScrollPane scrollPane = new JScrollPane(panel0);
    panel.add(scrollPane);
    final MyPanel panel1 = new MyPanel(new BorderLayout());
    panel1.add(this.back);
    panel.add(panel1);
    this.add(panel);
  }

  private void btnAction(final MyButton btn) {
    btn.addActionListener(e -> {
      final List<Object> obj = new ArrayList<>();
      final Integer btnIndex = this.btnList.indexOf(btn);
      final ActionListenerHandler actionListenerHandler = new ActionListenerHandler();
      obj.add(this.index);
      obj.add(btnIndex);
      obj.add(this);
      actionListenerHandler.buttonHandler(CaseHandler.BUY, obj);
    });
  }

  private void btnBackAction() {
    this.back.addActionListener(e -> {
      final List<Object> obj = new ArrayList<>();
      final ActionListenerHandler actionListenerHandler = new ActionListenerHandler();
      obj.add(new MainMenu(SupportMethods.getUsername(), SupportMethods.getAuthorization()));
      actionListenerHandler.buttonHandler(CaseHandler.BACK, obj);
      obj.clear();
      obj.add(this);
      actionListenerHandler.buttonHandler(CaseHandler.CLOSETHIS, obj);
    });
  }
}
