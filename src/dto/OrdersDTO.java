package dto;

import java.util.List;

public class OrdersDTO {
     private String ordersId;
     private String cId;
     private String orderDate;
     private List<OrderDetailDTO> items;

     public OrdersDTO() {
     }

     public OrdersDTO(String ordersId, String cId, String orderDate, List<OrderDetailDTO> items) {
          this.setOrderId(ordersId);
          this.setcId(cId);
          this.setOrderDate(orderDate);
          this.setItems(items);
     }

     public String getOrderId() {
          return ordersId;
     }

     public void setOrderId(String orderId) {
          this.ordersId = orderId;
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

     public List<OrderDetailDTO> getItems() {
          return items;
     }

     public void setItems(List<OrderDetailDTO> items) {
          this.items = items;
     }

     @Override
     public String toString() {
          return "Orders{" +
                  "orderId='" + ordersId + '\'' +
                  ", cId='" + cId + '\'' +
                  ", orderDate='" + orderDate + '\'' +
                  ", items=" + items +
                  '}';
     }
}