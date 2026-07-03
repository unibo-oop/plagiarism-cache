package supportfiles;

import basicgui.MyButton;
import basicgui.MyFrame;
import basicgui.MyPanel;
import handler.ActionListenerHandler;
import handler.CaseHandler;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import product.ProdArcList;
import product.ProductArchetype;
import vendingmachine.Shelf;
import vendingmachine.Slot;
import vendingmachine.VendingMachine;
import vendingmachine.VmList;


public final class SupportMethods {

  private static String username;
  private static Integer authorization;

  private SupportMethods() {

  }

  public static String vmDescription(final String vm) {
    return VmList.getMachine(VmList.getMachineIndex(vm)).getDescription();
  }

  public static String vmPosition(final String vm) {
    return VmList.getMachine(VmList.getMachineIndex(vm)).getPosition();
  }

  public static VendingMachine getVm(final String vm) {
    return VmList.getMachine(VmList.getMachineIndex(vm));
  }

  /**
   * Il metodo restituisce i pannelli per la costruzione di una vm.
   * @param vm vm desiderata
   * @param data dati richiesti
   * @return pannello con  i dati
   */
  public static MyPanel vmData(final VendingMachine vm, final String data) {
    final MyPanel panel = new MyPanel(new BorderLayout());
    final JLabel lbl = new JLabel("", 0);
    switch (data) {
      case "Name":
        lbl.setText(vm.getVmName());
        break;
      case "Desc":
        lbl.setText(vm.getDescription());
        break;
      case "Pos":
        lbl.setText(vm.getPosition());
        break;
      default:
        lbl.setText("ERRORE");
        break;
    }
    panel.add(lbl);
    panel.setBorder(new SoftBevelBorder(BevelBorder.RAISED));
    return panel;
  }

  /**
   * Il metodo permette di ricvere pannelli con i dati richiesti.
   * @param destroy se può essere distrutto
   * @param manage se può essere gestito
   * @param manageOrBuy se è un pannello di acquisti o di gestione
   * @return pannello richiesto
   */
  public static MyPanel dataPanel(final Boolean destroy, final Boolean manage,
      final Boolean manageOrBuy) {
    final MyPanel vmlPanel = new MyPanel(null);
    vmlPanel.setLayout(new BoxLayout(vmlPanel, BoxLayout.Y_AXIS));
    for (final VendingMachine vm : VmList.list()) {
      final MyPanel vmPanel = new MyPanel(null);
      vmPanel.setLayout(new BoxLayout(vmPanel, BoxLayout.X_AXIS));
      vmPanel.add(SupportMethods.vmData(vm, "Name"));
      vmPanel.add(SupportMethods.vmData(vm, "Desc"));
      vmPanel.add(SupportMethods.vmData(vm, "Pos"));
      if (destroy) {
        final MyPanel btPanel = new MyPanel(new BorderLayout());
        final MyButton delete = new MyButton("Delete");
        deleteAction(delete, VmList.list().indexOf(vm), vmlPanel);
        btPanel.add(delete);
        vmPanel.add(btPanel);
      }
      if (manage) {
        final MyPanel btPanel = new MyPanel(new BorderLayout());
        final MyButton info = new MyButton(manageOrBuy ? "More inforamtion" : "Buy from VM");
        infoAction(info, VmList.list().indexOf(vm), vmlPanel, manageOrBuy);
        btPanel.add(info);
        vmPanel.add(btPanel);
      }
      vmlPanel.add(vmPanel);
    }
    return vmlPanel;
  }

  private static void deleteAction(final MyButton bt, final int index, final MyPanel panel) {
    bt.addActionListener(e -> {
      final ActionListenerHandler alhandler = new ActionListenerHandler();
      final List<Object> obj = new ArrayList<>();
      obj.add((Integer) index);
      final JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(panel);
      obj.add(topFrame);
      alhandler.buttonHandler(CaseHandler.DESTROY, (ArrayList<?>) obj);
    });
  }

  private static void infoAction(final MyButton bt, final int index, final MyPanel panel,
      final Boolean manageOrBuy) {
    bt.addActionListener(e -> {
      final ActionListenerHandler actionHandlerListener = new ActionListenerHandler();
      final List<Object> obj = new ArrayList<>();
      final VendingMachine vm = VmList
          .getMachine(VmList.getMachineIndex(VmList.list().get(index).getVmName()));
      obj.add(vm.getVmName());
      obj.add(vm.getDescription());
      obj.add(vm.getPosition());
      actionHandlerListener.buttonHandler(manageOrBuy ? CaseHandler.VIEWVM : CaseHandler.BUYFROMVM,
          (ArrayList<?>) obj);
      obj.clear();
      obj.add((MyFrame) SwingUtilities.getWindowAncestor(panel));
      actionHandlerListener.buttonHandler(CaseHandler.CLOSETHIS, obj);
    });
  }

  public static String getUsername() {
    return SupportMethods.username;
  }

  public static void setUsername(final String username) {
    SupportMethods.username = username;
  }

  public static Integer getAuthorization() {
    return SupportMethods.authorization;
  }

  public static void setAuthorization(final Integer authorization) {
    SupportMethods.authorization = authorization;
  }

  /**
   * Il metodo permette di eseguire il settaggio della grafica di un pannello.
   * @param index indice del pannello
   * @param buttonTxt indice del pulsante
   * @return ewsttuisce la lista di oggetti per una grafica
   */
  public static List<Object> setGraphicPanleList(final int index, final String buttonTxt) {
    final List<Object> objList = new ArrayList<>();
    final MyPanel panel0 = new MyPanel(null);
    final MyPanel panel1 = new MyPanel(new BorderLayout());
    final MyPanel panel2 = new MyPanel(new BorderLayout());
    final JLabel lbl = new JLabel(VmList.getMachine(index).getVmName());
    panel0.setLayout(new BoxLayout(panel0, BoxLayout.X_AXIS));
    objList.add(lbl);
    panel1.add(lbl);
    final MyButton button = new MyButton(buttonTxt);
    objList.add(button);
    panel2.add(button);
    panel0.add(panel1);
    panel0.add(panel2);
    panel0.setBorder(new SoftBevelBorder(BevelBorder.RAISED));
    objList.add(panel0);
    return objList;
  }

  public static ProductArchetype getProd(final String vmName, final Integer shelfIndex,
      final Integer slotIndex) {
    return VmList.getMachine(VmList.getMachineIndex(vmName)).getShelf(shelfIndex).getSlot(slotIndex)
        .getProd();
  }

  public static List<ProductArchetype> getAllProdList() {
    return (List<ProductArchetype>) ProdArcList.getAllProd();
  }

  /**
   * Il metodo restituisce i prodotti presenti in un determinato scaffale.
   * @param index indice dello scaffale
   * @return lista dei prodotti di uno scaffale
   */
  public static List<ProductArchetype> getProdList(final Integer index) {
    final List<Slot> slotList = getSlotsFromVmWithProdSupport(index);
    final List<ProductArchetype> prodList = new ArrayList<>();
    for (final Slot slot : slotList) {
      prodList.add(slot.getProd());
    }
    return prodList;
  }

  /**
   * Il metodo restituisce le quantità dei prodotti presenti in uno scaffale.
   * @param index indice dello scaffale
   * @return restituiscela lista delle quantità dei prodotti.
   */
  public static List<Integer> getProdQtList(final Integer index) {
    final List<Slot> slotList = getSlotsFromVmWithProdSupport(index);
    final List<Integer> prodQtList = new ArrayList<>();
    for (final Slot slot : slotList) {
      prodQtList.add(slot.getQt());
    }
    return prodQtList;
  }

  public static List<Slot> getSlotsFromVmWithProd(final Integer index) {
    return getSlotsFromVmWithProdSupport(index);
  }

  private static List<Slot> getSlotsFromVmWithProdSupport(final Integer index) {
    final List<Slot> slotList = getSlotFromVm(index);
    final List<Slot> slotWithProd = new ArrayList<>();
    for (final Slot slot : slotList) {
      if (slot.getQt() != null) {
        slotWithProd.add(slot);
      }
    }
    return slotWithProd;
  }

  public static Integer getVmIndex(final String vm) {
    return VmList.getMachineIndex(vm);
  }

  public static List<Shelf> getAllVmShelf(final String vm) {
    return VmList.getMachine(VmList.getMachineIndex(vm)).getAllShelf();
  }

  private static List<Slot> getSlotFromVm(final Integer index) {
    final VendingMachine vm = VmList.getMachine(index);
    final List<Shelf> shelfList = vm.getAllShelf();
    final List<Slot> slotList = new ArrayList<>();
    for (final Shelf shelf : shelfList) {
      slotList.addAll(shelf.getAllSlot());
    }
    return slotList;
  }

  public static List<Slot> getSlotFromShelf(final String vmIndex, final Integer shelfIndex) {
    return VmList.getMachine(VmList.getMachineIndex(vmIndex)).getShelf(shelfIndex).getAllSlot();
  }

  /**
   * Il metodo permetted i sapere la quantità di un prodotto presente in uno scaffale di una 
   * macchinetta.
   * @param vmName distributore automatico nome
   * @param shelfIndex indice dello scaffale
   * @param slotIndex indice dello slot
   * @return restituisce la quanttità del prodotto indicato tramite i dati elencati in precedenza
   */
  public static Integer getQt(final String vmName, final Integer shelfIndex,
      final Integer slotIndex) {
    final Integer qt = VmList.getMachine(VmList.getMachineIndex(vmName))
        .getShelf(shelfIndex).getSlot(slotIndex)
        .getQt();
    if (qt != null) {
      return qt;
    } else {
      return 0;
    }

  }

  /**
   * Il metodo permette di sapere il costo di un prodotto.
   * @param vmName nome del distributore automatico
   * @param shelfIndex indice dello scaffale
   * @param slotIndex indice dello scaffale
   * @return restituisce il costo del prodotto indicato tramite i dati precedenti
   */
  public static Float getCost(final String vmName, final Integer shelfIndex,
      final Integer slotIndex) {
    final Float cost = VmList.getMachine(VmList.getMachineIndex(vmName))
        .getShelf(shelfIndex).getSlot(slotIndex)
        .getCost();
    if (cost != null) {
      return cost;
    } else {
      return (float) 0.0;
    }
  }

}
