package product;

import java.util.ArrayList;
import java.util.List;

public final class ProdArcList {

  private static List<ProductArchetype> prodList = new ArrayList<>();

  private ProdArcList() {

  }

  public static void addProd(final ProductArchetype prod) {
    ProdArcList.prodList.add(prod);
  }

  public static void removeProd(final Integer pos) {
    ProdArcList.prodList.remove(ProdArcList.prodList.get(pos));
  }

  public static void removeProd(final ProductAchetypeInterface prod) {
    ProdArcList.prodList.remove(prod);
  }

  public static void removeAll() {
    ProdArcList.prodList.clear();
  }

  public static ProductAchetypeInterface getProd(final Integer pos) {
    return ProdArcList.prodList.get(pos);
  }

  public static Integer getPos(final ProductAchetypeInterface prod) {
    return ProdArcList.prodList.indexOf(prod);
  }

  public static List<ProductArchetype> getAllProd() {
    return ProdArcList.prodList;
  }
}
