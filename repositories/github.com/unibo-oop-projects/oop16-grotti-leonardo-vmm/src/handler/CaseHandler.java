package handler;


public enum CaseHandler {

    EXIT(0, "exit"),
    LOGIN(1, "login"),
    VIEWVM(2, "view vending machine"),
    LOGOUT(3, "logout"),
    CREATE(4, "create a new vending machine"),
    DESTROY(5, "destroy a vending machine"),
    SHELFCREATE(6, "open panel for creating a shelf"),
    SHELFMANAGEMENT(7, "open panel for managing shelf"),
    SHELFDESTROY(8, "destroy the shelf"),
    SHELFADD(9, "add the shelf to the vm"),
    UPDATEVM(10, "update a vm already in list"), 
    BACK(11, "back to the previous Frame"), 
    CREATEVM(12, "save the new vending machine"),
    CLOSETHIS(13, "dispose the current frame"),
    SAVEPRODINSLOT(14, "save the product choosen in the slot choosen"),
    SELECTPROD(15, "select the prod to put in the slot choosen"), 
    BUY(16, "go to buy menu of the selected vm"), 
    CREATEAP(17, "create a new archetype product"),
    BUYFROMVM(18, "select the vm from which buy product"), 
    SLOTDESTROY(19, "delete the selected slot"),
    RELOAD(20, "reload the selected panel"), 
    SLOTCREATE(21, "add a slot to the shelf");

  private final int value;
  private final String str;

  CaseHandler(final int value, final String str) {
    this.value = value;
    this.str = str;
  }

  public int value() {
    return value;
  }

  public String str() {
    return str;
  }
}
