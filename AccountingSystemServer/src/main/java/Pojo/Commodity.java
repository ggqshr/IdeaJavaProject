package Pojo;


public class Commodity {

  private String commodityBarCode;
  private String commodityName;
  private double commodityPrice;
  private long commodityStock;
  private String commodityType;


  public String getCommodityBarCode() {
    return commodityBarCode;
  }

  public void setCommodityBarCode(String commodityBarCode) {
    this.commodityBarCode = commodityBarCode;
  }


  public String getCommodityName() {
    return commodityName;
  }

  public void setCommodityName(String commodityName) {
    this.commodityName = commodityName;
  }


  public double getCommodityPrice() {
    return commodityPrice;
  }

  public void setCommodityPrice(double commodityPrice) {
    this.commodityPrice = commodityPrice;
  }


  public long getCommodityStock() {
    return commodityStock;
  }

  public void setCommodityStock(long commodityStock) {
    this.commodityStock = commodityStock;
  }


  public String getCommodityType() {
    return commodityType;
  }

  public void setCommodityType(String commodityType) {
    this.commodityType = commodityType;
  }

}
