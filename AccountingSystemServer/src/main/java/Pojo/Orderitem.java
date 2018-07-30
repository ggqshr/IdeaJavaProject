package Pojo;


public class Orderitem {

  private String itemId;
  private String orderId;
  private String commodityBarCode;
  private long itemAmount;
  private double itemPrice;
  private long itemDiscount;
  private double itemTotalPrice;
  private String commodityType;


  public String getItemId() {
    return itemId;
  }

  public void setItemId(String itemId) {
    this.itemId = itemId;
  }


  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }


  public String getCommodityBarCode() {
    return commodityBarCode;
  }

  public void setCommodityBarCode(String commodityBarCode) {
    this.commodityBarCode = commodityBarCode;
  }


  public long getItemAmount() {
    return itemAmount;
  }

  public void setItemAmount(long itemAmount) {
    this.itemAmount = itemAmount;
  }


  public double getItemPrice() {
    return itemPrice;
  }

  public void setItemPrice(double itemPrice) {
    this.itemPrice = itemPrice;
  }


  public long getItemDiscount() {
    return itemDiscount;
  }

  public void setItemDiscount(long itemDiscount) {
    this.itemDiscount = itemDiscount;
  }


  public double getItemTotalPrice() {
    return itemTotalPrice;
  }

  public void setItemTotalPrice(double itemTotalPrice) {
    this.itemTotalPrice = itemTotalPrice;
  }


  public String getCommodityType() {
    return commodityType;
  }

  public void setCommodityType(String commodityType) {
    this.commodityType = commodityType;
  }

}
