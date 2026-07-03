package handler;

import gui.AddProdToSlot;
import gui.BasicGuiInterface;
import gui.Buy;
import gui.CreateShelf;
import gui.LoginMenu;
import gui.MainMenu;
import gui.ManageSlot;
import gui.NewVm;
import java.util.List;
import product.ProdArcList;
import product.ProductArchetype;
import supportfiles.SupportMethods;
import vendingmachine.Shelf;
import vendingmachine.Slot;
import vendingmachine.VendingMachine;
import vendingmachine.VmList;

public class ActionListenerHandler implements ActionListenerHandlerInterface {

  @Override
  public void buttonHandler(final CaseHandler bt, final List<?> obj) {
    switch (bt) {
      case EXIT:
        exit0();
        break;
      case LOGIN:
        login(obj);
        break;
      case VIEWVM:
        viewVm(obj);
        break;
      case LOGOUT:
        logout();
        break;
      case CREATE:
        create(obj);
        break;
      case DESTROY:
        destroy(obj);
        break;
      case SHELFCREATE:
        shelfCreate(obj);
        break;
      case SHELFMANAGEMENT:
        shelfManagement(obj);
        break;
      case SHELFDESTROY:
        shelfDestroy(obj);
        break;
      case SHELFADD:
        shelfAdd(obj);
        break;
      case UPDATEVM:
        updateVm(obj);
        break;
      case BACK:
        back(obj);
        break;
      case CREATEVM:
        createVm(obj);
        break;
      case CLOSETHIS:
        closeThis(obj);
        break;
      case SAVEPRODINSLOT:
        saveProdInSlot(obj);
        break;
      case SELECTPROD:
        selectProd(obj);
        break;
      case BUY:
        buy(obj);
        break;
      case CREATEAP:
        createAp(obj);
        break;
      case BUYFROMVM:
        buyFromVm(obj);
        break;
      case SLOTDESTROY:
        slotDestroy(obj);
        break;
      case RELOAD:
        reload(obj);
        break;
      case SLOTCREATE:
        slotCreate(obj);
        break;
      default:
        defaultError();
        break;
    }
  }

  private void exit0() {
    System.out.println("EXIT");
    System.exit(0);
  }

  private void login(final List<?> obj) {
    int lv = 0;
    if (obj.get(0).equals("admin") && obj.get(1).equals("admin")) {
      lv = 1;
    } else {
      lv = 0;
    }
    final MainMenu menu = new MainMenu((String) obj.get(0), lv);
    menu.run();
  }

  private void viewVm(final List<?> obj) {
    final NewVm vm = new NewVm((String) obj.get(0), (String) obj.get(1), (String) obj.get(2));
    vm.run();
  }

  private void logout() {
    VmList.clear();
    final LoginMenu log = new LoginMenu();
    log.run();
  }

  private void create(final List<?> obj) {
    ((MainMenu) obj.get(0)).setVisible(false);
    final NewVm nvm = new NewVm();
    nvm.run();
    VmList.setTmpVm(new VendingMachine());
  }

  private void destroy(final List<?> obj) {
    VmList.removeItem((int) obj.get(0));
    System.out.println("DELETED");
    ((MainMenu) obj.get(1)).reload();
  }

  private void shelfCreate(final List<?> obj) {
    final CreateShelf createShelf = new CreateShelf((String) obj.get(1));
    createShelf.run();
  }

  private void shelfManagement(final List<?> obj) {
    System.out.println(obj.size());
    final ManageSlot ms = new ManageSlot((String) obj.get(0), (Integer) obj.get(1));
    ms.run();
  }

  private void shelfDestroy(final List<?> obj) {
    if (getVmIndex((String) obj.get(0)) != null) {
      final VendingMachine vendingMachine = VmList
          .getMachine(VmList.getMachineIndex((String) obj.get(0)));
      vendingMachine.deleteShelf(vendingMachine.getShelf((Integer) obj.get(1)));
      final Integer index = VmList.getMachineIndex(vendingMachine);
      VmList.removeItem(VmList.getMachineIndex(vendingMachine));
      VmList.addItem(vendingMachine, index);
    }
  }

  private void shelfAdd(final List<?> obj) {
    try {
      final Shelf shelf = new Shelf(Integer.parseInt((String) obj.get(1)),
          Integer.parseInt((String) obj.get(2)));
      VmList.getMachine(VmList.getMachineIndex((String) obj.get(0))).addShelf(shelf);
    } catch (Exception e) {
      System.out.println("Dati inseriti errati o assenti");
    }
  }

  private void updateVm(final List<?> obj) {
    System.out.println(VmList.getMachineIndex((String) obj.get(0)));
    if (VmList.getMachineIndex((String) obj.get(0)) == null) {
      final Integer index = (Integer) obj.get(3);
      final VendingMachine vendingMachine = VmList.getMachine(index);
      VmList.removeItem(index);
      VmList.addItem(vmFill(vendingMachine, obj), index);
    } else {
      System.out.println(
          "La macchinetta non può essere modificata in quanto"
          + " esiste già una macchinetta con tale nome");
    }
  }

  private void back(final List<?> obj) {
    final BasicGuiInterface frame = ((BasicGuiInterface) obj.get(0));
    frame.run();
  }

  private void createVm(final List<?> obj) {
    if (getVmIndex((String) obj.get(0)) == null) {
      final VendingMachine vendingMachine = new VendingMachine();
      VmList.addItem(vmFill(vendingMachine, obj));
    }
  }
  
  private VendingMachine vmFill(final VendingMachine vm, final List<?> obj) {
    vm.setVmName((String) obj.get(0));
    vm.setDescription((String) obj.get(1));
    vm.setPosition((String) obj.get(2));
    return vm;
  }

  private Integer getVmIndex(final String str) {
    return VmList.getMachineIndex(str);
  }
  
  private void closeThis(final List<?> obj) {
    ((BasicGuiInterface) obj.get(0)).dispose();
  }

  private void saveProdInSlot(final List<?> obj) {
    final Slot slot = VmList.getMachine(VmList.getMachineIndex((String) obj.get(0)))
        .getShelf((Integer) obj.get(1)).getSlot((Integer) obj.get(2));
    slot.resetProd((ProductArchetype) obj.get(3));
    slot.resetQt((Integer) obj.get(4));
    slot.resetCost((Float) obj.get(5));
  }

  private void selectProd(final List<?> obj) {
    final AddProdToSlot addProdToSlot = new AddProdToSlot((String) obj.get(0), (Integer) obj.get(1),
        (Integer) obj.get(2), (Integer) obj.get(3));
    addProdToSlot.run();
  }

  private void buy(final List<?> obj) {
    final VendingMachine vendingMachine = VmList.getMachine((Integer) obj.get(0));
    final List<Shelf> shelfList = vendingMachine.getAllShelf();
    final List<Slot> slotList = SupportMethods.getSlotsFromVmWithProd((Integer) obj.get(0));
    final Slot slot = slotList.get((Integer) obj.get(1));
    Integer indexOfShelf = -1;
    for (final Shelf shelf0 : shelfList) {
      if (shelf0.getAllSlot().contains(slot)) {
        indexOfShelf = shelfList.indexOf(shelf0);
      }
    }
    if (indexOfShelf != -1) {
      final Integer qt = VmList.getMachine((Integer) obj.get(0)).getAllShelf().get(indexOfShelf)
          .getAllSlot().get((Integer) obj.get(1)).getQt() - 1;
      if (qt >= 0) {
        VmList.getMachine((Integer) obj.get(0)).getAllShelf().get(indexOfShelf).getAllSlot()
            .get((Integer) obj.get(1)).resetQt(qt);
      } else {
        System.out.println("Impossibile eseguire l'acquisto");
      }
    }
    ((Buy) obj.get(2)).reload();
  }

  private void createAp(final List<?> obj) {
    final ProductArchetype pa = new ProductArchetype((String) obj.get(0), (String) obj.get(1),
        (Integer) obj.get(2));
    ProdArcList.addProd(pa);
    ((MainMenu) obj.get(3)).reload();
  }

  private void buyFromVm(final List<?> obj) {
    final Integer indexOfVm = VmList.getMachineIndex((String) obj.get(0));
    final Buy buy = new Buy(indexOfVm);
    buy.run();
  }

  private void slotDestroy(final List<?> obj) {
    System.out.println(obj.get(1).toString() + obj.get(2).toString());
    VmList.getMachine(VmList.getMachineIndex((String) obj.get(0))).getShelf((Integer) obj.get(1))
        .removeSlot((Integer) obj.get(2));
  }

  private void reload(final List<?> obj) {
    ((BasicGuiInterface) obj.get(0)).reload();
  }

  private void slotCreate(final List<?> obj) {
    VmList.getMachine(VmList.getMachineIndex((String) obj.get(0))).getShelf((Integer) obj.get(1))
        .addSlot(null, (Integer) obj.get(2), -1);
  }

  private void defaultError() {
    System.exit(1);
  }

}
