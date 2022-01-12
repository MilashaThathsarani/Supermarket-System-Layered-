package entity;

public class Orders {
     private String ordersId;
     private String cId;
     private String orderDate;

     public Orders() {
     }

     public Orders(String ordersId, String cId, String orderDate) {
          this.setOrdersId(ordersId);
          this.setcId(cId);
          this.setOrderDate(orderDate);
     }

     public String getOrdersId() {
          return ordersId;
     }

     public void setOrdersId(String ordersId) {
          this.ordersId = ordersId;
     }

     public String getcId() {
          return cId;
     }

     public void setcId(String cId) {
          this.cId = cId;
     }

     public String getOrderDate() {
          return orderDate;
     }

     public void setOrderDate(String orderDate) {
          this.orderDate = orderDate;
     }

     @Override
     public String toString() {
          return "Orders{" +
                  "ordersId='" + ordersId + '\'' +
                  ", cId='" + cId + '\'' +
                  ", orderDate='" + orderDate + '\'' +
                  '}';
     }
}