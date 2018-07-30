package Pojo;


public class Ordertabel {

  private String orderId;
  private String customerPhoneNumber;
  private String orderTime;
  private String userId;
  private double orderTotalPrice;
  private String orderType;


  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }


  public String getCustomerPhoneNumber() {
    return customerPhoneNumber;
  }

  public void setCustomerPhoneNumber(String customerPhoneNumber) {
    this.customerPhoneNumber = customerPhoneNumber;
  }


  public String getOrderTime() {
    return orderTime;
  }

  public void setOrderTime(String orderTime) {
    this.orderTime = orderTime;
  }


  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }


  public double getOrderTotalPrice() {
    return orderTotalPrice;
  }

  public void setOrderTotalPrice(double orderTotalPrice) {
    this.orderTotalPrice = orderTotalPrice;
  }


  public String getOrderType() {
    return orderType;
  }

  public void setOrderType(String orderType) {
    this.orderType = orderType;
  }

}
