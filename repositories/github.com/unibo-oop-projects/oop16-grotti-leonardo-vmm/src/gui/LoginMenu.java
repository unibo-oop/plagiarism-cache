package gui;

import basicgui.MyButton;
import basicgui.MyFrame;
import basicgui.MyPanel;
import basicgui.Size;
import basicgui.SizeInterface;
import handler.ActionListenerHandler;
import handler.CaseHandler;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class LoginMenu implements BasicGuiInterface {

  private MyFrame me;
  private List<JLabel> labelList;
  private List<JTextField> textFieldList;
  private List<MyButton> buttonList;
  private SizeInterface size;
  private int pageSizeW;
  private int pageSizeH;

  @Override
  public void run() {
    init();
    labels();
    textAreas();
    buttons();
    setGraphics();
  }

  private void init() {
    this.size = new Size();
    this.pageSizeW = this.size.getSizeWidth(30);
    this.pageSizeH = this.size.getSizeHeight(30);
    this.labelList = new ArrayList<>();
    this.textFieldList = new ArrayList<>();
    this.buttonList = new ArrayList<>();
  }

  private void labels() {
    labelList.add(new JLabel("Name"));
    labelList.add(new JLabel("Password"));
  }

  private void textAreas() {
    textFieldList.add(new JTextField());
    textFieldList.add(new JTextField());
    for (final JTextField txtField : textFieldList) {
      txtField.setSize(this.size.getComponentDimension(0, 80, this.size.getDimension(30)));
    }
    textFieldList.get(0).setSize(10, 2);

  }

  private void buttons() {
    buttonList.add(new MyButton("Login"));
    buttonList.add(new MyButton("Exit"));
  }

  private void setComponet(final MyPanel myPanel) {
    int i = 0;
    for (final JLabel label : labelList) {
      final MyPanel panelL = new MyPanel(new BorderLayout());
      panelL.add(label);
      myPanel.add(panelL);
      final MyPanel panelT = new MyPanel(new BorderLayout());
      panelT.add(textFieldList.get(i));
      myPanel.add(panelT);
      i++;
    }
    for (final MyButton button : buttonList) {
      final MyPanel panel = new MyPanel(new BorderLayout());
      button.addActionListener(e -> {
        final ActionListenerHandler alHandler = new ActionListenerHandler();
        if (button.getText().equals("Exit")) {
          final List<Object> obj = new ArrayList<>();
          alHandler.buttonHandler(CaseHandler.EXIT, (ArrayList<?>) obj);
        } else {
          final List<String> datas = new ArrayList<>();
          datas.add(textFieldList.get(0).getText());
          datas.add(textFieldList.get(0).getText());
          alHandler.buttonHandler(CaseHandler.LOGIN, (ArrayList<?>) datas);
        }
        this.me.dispose();
      });
      panel.add(button);
      myPanel.add(panel);
    }
  }

  private void setGraphics() {
    final MyPanel mainPanel = new MyPanel(new FlowLayout());
    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
    setComponet(mainPanel);
    final MyFrame frame = new MyFrame("Login", mainPanel);
    frame.setSize(pageSizeW, pageSizeH);
    this.me = frame;
  }

  @Override
  public void reload() {
    init();
    labels();
    textAreas();
    buttons();
    setGraphics();
  }

  @Override
  public void dispose() {
    this.me.dispose();
  }

}
