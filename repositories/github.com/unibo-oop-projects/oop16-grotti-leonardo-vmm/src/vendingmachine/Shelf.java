package vendingmachine;

import java.util.ArrayList;
import java.util.List;
import product.ProductArchetype;

public class Shelf implements ShelfInterface {

  private Integer slots;
  private Integer prodPerSlot;
  private final List<Slot> slotList = new ArrayList<>();

  public Shelf(final Integer slots, final Integer prodPerSLot) {
    setNumberOfSlotsSupport(slots);
    setProdPerSlotSupport(prodPerSLot);
  }

  @Override
  public void setNumberOfSlots(final Integer slots) {
    setNumberOfSlotsSupport(slots);
  }

  @Override
  public Integer getNumberOfSlots() {
    return this.slots;
  }

  @Override
  public void setProdPerSlot(final Integer num) {
    setProdPerSlotSupport(num);
  }

  @Override
  public Integer getProdPerSlot() {
    return this.prodPerSlot;
  }

  private void setNumberOfSlotsSupport(final Integer slots) {
    this.slots = slots;
  }

  private void setProdPerSlotSupport(final Integer num) {
    this.prodPerSlot = num;
  }

  @Override
  public void addSlot(final ProductArchetype prod, final Integer pos, final Integer qt) {
    slotList.add(new Slot(prod, pos, qt));
  }

  @Override
  public void removeSlot(final Integer index) {
    slotList.remove(getSlotSupport(index));
  }

  @Override
  public Slot getSlot(final Integer index) {
    return getSlotSupport(index);
  }

  private Slot getSlotSupport(final Integer index) {
    return slotList.get(index);
  }

  @Override
  public List<Slot> getAllSlot() {
    return this.slotList;
  }

}
