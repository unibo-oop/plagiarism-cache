package vendingmachine;

import java.util.ArrayList;
import java.util.List;

public class Money implements MoneyInterface {

  private Integer oneCent;
  private Integer twoCent;
  private Integer fiveCent;
  private Integer tenCent;
  private Integer twentyCent;
  private Integer fiftyCent;
  private Integer one;
  private Integer two;
  private Integer five;
  private Integer ten;
  private Integer twenty;
  private Integer fifty;
  private Integer oneHundred;
  private Integer twoHundred;
  private Integer fiveHundred;

  /**
   * Costruttore della classe.
   */
  public Money() {
    this.oneCent = 0;
    this.twoCent = 0;
    this.fiveCent = 0;
    this.tenCent = 0;
    this.twentyCent = 0;
    this.fiftyCent = 0;
    this.one = 0;
    this.two = 0;
    this.five = 0;
    this.ten = 0;
    this.twenty = 0;
    this.fifty = 0;
    this.oneHundred = 0;
    this.twoHundred = 0;
    this.fiveHundred = 0;
  }

  @Override
  public ArrayList<Integer> howManyMoney() {
    final List<Integer> moneyList = new ArrayList<>();
    moneyList.add(this.oneCent);
    moneyList.add(this.twoCent);
    moneyList.add(this.fiveCent);
    moneyList.add(this.tenCent);
    moneyList.add(this.twentyCent);
    moneyList.add(this.fiftyCent);
    moneyList.add(this.one);
    moneyList.add(this.two);
    moneyList.add(this.five);
    moneyList.add(this.ten);
    moneyList.add(this.twenty);
    moneyList.add(this.fifty);
    moneyList.add(this.oneHundred);
    moneyList.add(this.twoHundred);
    moneyList.add(this.fiveHundred);
    return (ArrayList<Integer>) moneyList;
  }

  @Override
  public void addOneCent(final int num) {
    this.oneCent += num;
  }

  @Override
  public void addTwoCent(final int num) {
    this.twoCent += num;
  }

  @Override
  public void addFiveCent(final int num) {
    this.fiveCent += num;
  }

  @Override
  public void addTenCent(final int num) {
    this.tenCent += num;
  }

  @Override
  public void addTwentyCent(final int num) {
    this.twentyCent += num;
  }

  @Override
  public void addFiftyCent(final int num) {
    this.fiftyCent += num;
  }

  @Override
  public void addOne(final int num) {
    this.one += num;
  }

  @Override
  public void addTwo(final int num) {
    this.two += num;
  }

  @Override
  public void addFive(final int num) {
    this.five += num;
  }

  @Override
  public void addTen(final int num) {
    this.ten += num;
  }

  @Override
  public void addTwenty(final int num) {
    this.twenty += 0;
  }

  @Override
  public void addFifty(final int num) {
    this.fifty += num;
  }

  @Override
  public void addOneHundred(final int num) {
    this.oneHundred += num;
  }

  @Override
  public void addTwoHundred(final int num) {
    this.twoHundred += num;
  }

  @Override
  public void addFiveHundred(final int num) {
    this.fiveHundred += num;
  }

}
