package gui;

import basicgui.MyButton;
import basicgui.MyPanel;
import handler.ActionListenerHandler;
import handler.CaseHandler;
import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import supportfiles.SupportMethods;

public class BasicFromVm extends MyPanel {

  private List<MyPanel> panelList;
  private List<JLabel> txtList;
  private List<MyButton> buttonList;
  private final Boolean manageOrBuy;
  private MyButton create;

  private static final long serialVersionUID = 1L;

  /**
   * Costruttore della classe.
   * @param lm layout del pannello.
   * @param flag indica se è il pannello di management o di acquisto.
   */
  public BasicFromVm(final LayoutManager lm, final Boolean flag) {
    super(lm);
    this.manageOrBuy = flag;
    init();
    setGraphic();
  }

  protected void reload() {
    this.panelList.clear();
    this.txtList.clear();
    this.buttonList.clear();
    this.removeAll();
    this.init();
    this.setGraphic();
  }

  private void init() {
    this.panelList = new ArrayList<>();
    this.txtList = new ArrayList<>();
    this.buttonList = new ArrayList<>();
    this.create = new MyButton("Add new Vending Machine");

  }

  private void setGraphic() {
    final JScrollPane sPane;
    if (this.manageOrBuy) {
      final MyPanel btPanel = new MyPanel(new BorderLayout());
      btPanel.add(this.create);
      this.add(btPanel);
      btnEvent();
      sPane = new JScrollPane(SupportMethods.dataPanel(true, true, this.manageOrBuy));
    } else {
      sPane = new JScrollPane(SupportMethods.dataPanel(false, true, this.manageOrBuy));
    }
    this.add(sPane);
  }

  private void btnEvent() {
    this.create.addActionListener(e -> {
      final ActionListenerHandler actionListenreHandler = new ActionListenerHandler();
      final List<Object> obj = new ArrayList<>();
      obj.add(SwingUtilities.getWindowAncestor(this));
      actionListenreHandler.buttonHandler(CaseHandler.CREATE, (ArrayList<?>) obj);
    });
  }
}
