package testdata;

import product.ProdArcList;
import product.ProductArchetype;
import vendingmachine.VendingMachine;
import vendingmachine.VmList;

public class TestDataFiller implements TestDataFillerInterface {

  @Override
  public void run() {
    prodFiller();
    vmlistFiller();
    vmshelfFiller();
    vmslotFiller();
  }

  private void prodFiller() {
    ProdArcList.addProd(new ProductArchetype("Acqua", "Acqua Minerale", 1));
    ProdArcList.addProd(new ProductArchetype("Chinotto", "Bevanda gassata dissetante", 1));
  }

  private void vmlistFiller() {
    VmList.addItem(new VendingMachine("1", "b", "c"));
    VmList.addItem(new VendingMachine("2", "b", "c"));
    VmList.addItem(new VendingMachine("3", "b", "c"));
    VmList.addItem(new VendingMachine("4", "b", "c"));
    VmList.addItem(new VendingMachine("5", "b", "c"));
    VmList.addItem(new VendingMachine("6", "b", "c"));
    VmList.addItem(new VendingMachine("7", "b", "c"));
    VmList.addItem(new VendingMachine("8", "b", "c"));
    VmList.addItem(new VendingMachine("9", "b", "c"));
    VmList.addItem(new VendingMachine("10", "b", "c"));
    VmList.addItem(new VendingMachine("11", "b", "c"));
  }

  private void vmshelfFiller() {
    VmList.getMachine(0).addShelf(10, 10);
    VmList.getMachine(0).addShelf(10, 10);
  }

  private void vmslotFiller() {
    VmList.getMachine(0).getShelf(0).addSlot((ProductArchetype) ProdArcList.getProd(0), 0, 10);
    VmList.getMachine(0).getShelf(0).addSlot((ProductArchetype) ProdArcList.getProd(1), 1, 1);
  }
}
