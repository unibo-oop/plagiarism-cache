package vendingmachine;

import java.util.ArrayList;
import java.util.List;

public class VendingMachine implements VendingMachineInterface {

  private String name;
  private String position;
  private String description;
  private List<Shelf> shelfList;
  private Money money;

  /**
   * Costurttore di tipo 1 della classe.
   */
  public VendingMachine() {
    setNameSupport("");
    setPositionSupport("");
    setDescriptionSupport("");
    setArrayList();
  }

  /**
   * Costruttore di tipo 2 della classe.
   * @param name nome distributore
   * @param pos posizione distributore
   * @param desc descrizione distributore
   */
  public VendingMachine(final String name, final String pos, final String desc) {
    setNameSupport(name);
    setPositionSupport(pos);
    setDescriptionSupport(desc);
    setArrayList();
  }

  /**
   * Costruttore di tipo 3 della classe.
   * @param vm vendingmachine già esistente
   */
  public VendingMachine(final VendingMachine vm) {
    setNameSupport(vm.getVmName());
    setPositionSupport(vm.getPosition());
    setDescriptionSupport(vm.getDescription());
    setArrayList();
  }

  private void setArrayList() {
    shelfList = new ArrayList<Shelf>();
    money = new Money();
  }

  @Override
  public void setVmName(final String name) {
    setNameSupport(name);
  }

  @Override
  public String getVmName() {
    return this.name;
  }

  @Override
  public void setPosition(final String position) {
    setPositionSupport(position);
  }

  @Override
  public String getPosition() {
    return this.position;
  }

  @Override
  public void setDescription(final String description) {
    setDescriptionSupport(description);

  }

  @Override
  public String getDescription() {
    return this.description;
  }

  @Override
  public void addShelf(final Shelf shelf) {
    this.shelfList.add((Shelf) shelf);
  }

  @Override
  public void addShelf(final Integer slots, final Integer prodPerSlot) {
    final Shelf shelf = new Shelf(slots, prodPerSlot);
    addShelf(shelf);
  }

  private void setNameSupport(final String name) {
    this.name = name;
  }

  private void setDescriptionSupport(final String desc) {
    this.description = desc;
  }

  private void setPositionSupport(final String position) {
    this.position = position;
  }

  @Override
  public List<Integer> getMonsy() {
    return money.howManyMoney();
  }

  @Override
  public void setMoney(final List<Integer> qt) {
    if (qt.size() != this.money.howManyMoney().size()) {
      for (int i = 0; i < qt.size() - this.money.howManyMoney().size(); i++) {
        qt.add(0);
      }
    }
    this.money.addOneCent(qt.get(0));
    this.money.addTwoCent(qt.get(1));
    this.money.addFiftyCent(qt.get(2));
    this.money.addTenCent(qt.get(3));
    this.money.addTwentyCent(qt.get(4));
    this.money.addFiftyCent(qt.get(5));
    this.money.addOne(qt.get(6));
    this.money.addTwo(qt.get(7));
    this.money.addFive(qt.get(8));
    this.money.addTen(qt.get(9));
    this.money.addTwo(qt.get(10));
    this.money.addFifty(qt.get(11));
    this.money.addOneHundred(qt.get(12));
    this.money.addTwoHundred(qt.get(13));
    this.money.addFiveHundred(qt.get(14));
  }

  @Override
  public void deleteShelf(final Shelf shelf) {
    this.shelfList.remove(shelf);
  }

  @Override
  public List<Shelf> getAllShelf() {
    return this.shelfList;
  }

  @Override
  public Shelf getShelf(final Integer index) {
    return shelfList.get(index);
  }
}
