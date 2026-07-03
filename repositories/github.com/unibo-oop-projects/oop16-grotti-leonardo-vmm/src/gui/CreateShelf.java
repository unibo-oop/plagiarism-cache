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
import javax.swing.JTextField;
import supportfiles.SupportMethods;

public class CreateShelf extends MyFrame implements BasicGuiInterface {

  private static final long serialVersionUID = 1L;

  private final String vm;
  private List<JLabel> lblList;
  private List<JTextField> txtList;
  private MyButton addBtn;

  public CreateShelf(final String vm) {
    super("Create Shelf");
    this.vm = vm;
  }

  @Override
  public void run() {
    init();
    setGraphics();
  }

  @Override
  public void reload() {
    this.getMainPanel().removeAll();
    init();
    setGraphics();
  }

  private void init() {
    this.lblList = new ArrayList<>();
    this.txtList = new ArrayList<>();
    this.addBtn = new MyButton("add shelf");
    btnAction();
  }

  private void setGraphics() {
    final MyPanel mainPanel = new MyPanel(null);
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    for (int i = 0; i < 2; i++) {
      lblList.add(new JLabel(i == 0 ? "Number of slots" : "Number of product per slot"));
      txtList.add(new JTextField());
      final MyPanel panel = new MyPanel(new BorderLayout());
      panel.add(lblList.get(i));
      panel.add(txtList.get(i));
      mainPanel.add(panel);
    }
    final MyPanel btnPanel = new MyPanel(new BorderLayout());
    btnPanel.add(addBtn);
    mainPanel.add(btnPanel);
    this.add(mainPanel);
  }

  private void btnAction() {
    this.addBtn.addActionListener(e -> {
      final ActionListenerHandler actionListenerHandler = new ActionListenerHandler();
      final List<Object> obj = new ArrayList<>();
      obj.add(this.vm);
      for (final JTextField txt : txtList) {
        obj.add(txt.getText());
      }
      actionListenerHandler.buttonHandler(CaseHandler.SHELFADD, (ArrayList<?>) obj);
      obj.clear();
      final NewVm newVm = new NewVm(this.vm, SupportMethods.vmDescription(this.vm),
          SupportMethods.vmPosition(this.vm));
      newVm.run();
      this.dispose();
    });

  }

}
