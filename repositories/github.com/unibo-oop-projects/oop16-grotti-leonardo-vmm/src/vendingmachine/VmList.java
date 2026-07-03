package vendingmachine;

import java.util.ArrayList;
import java.util.List;

public final class VmList {

  private static List<VendingMachine> listVm = new ArrayList<>();
  private static VendingMachine tmpVM;

  private VmList() {
  }

  public static void addItem(final VendingMachine vm) {
    listVm.add(vm);
  }

  public static void addItem(final VendingMachine vm, final int index) {
    listVm.add(index, vm);
  }

  public static void removeItem(final int index) {
    listVm.remove(index);
  }

  public static VendingMachine getMachine(final int index) {
    return listVm.get(index);
  }

  /**
   * Metodo che permette di sapere l'indice del distributore.
   * @param name nome del distributore
   * @return indice del distributore
   */
  public static Integer getMachineIndex(final String name) {
    for (final VendingMachine vendingMachine : listVm) {
      if (vendingMachine.getVmName().equals(name)) {
        return getMachineIndexSupport(vendingMachine);
      }
    }
    return null;
  }

  public static Integer getMachineIndex(final VendingMachine vm) {
    return getMachineIndexSupport(vm);
  }

  /**
   * Metodo che restituisce la lista di distributori.
   * @return tutti i distributori
   */
  public static List<VendingMachine> list() {
    final List<VendingMachine> list = new ArrayList<>();
    if (!listVm.isEmpty()) {
      list.addAll(listVm);
    }
    return list;
  }

  public static void clear() {
    listVm.clear();
  }

  public static void setTmpVm(final VendingMachine vm) {
    tmpVM = vm;
  }

  public static VendingMachine getTmpVm() {
    return tmpVM;
  }

  private static Integer getMachineIndexSupport(final VendingMachine vm) {
    return listVm.indexOf(vm);
  }

}
