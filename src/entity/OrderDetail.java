package entity;

/**
 * @author : Sanu Vithanage
 * @since : 0.1.0
 **/

public class OrderDetail {
    private String code;
    private String orderId;
    private int orderQty;
    private double disscount;

    public OrderDetail() {
    }

    public OrderDetail(String code, String orderId, int orderQty, double disscount) {
        this.setCode(code);
        this.setOrderId(orderId);
        this.setOrderQty(orderQty);
        this.setDisscount(disscount);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(int orderQty) {
        this.orderQty = orderQty;
    }

    public double getDisscount() {
        return disscount;
    }

    public void setDisscount(double disscount) {
        this.disscount = disscount;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "code='" + code + '\'' +
                ", orderId='" + orderId + '\'' +
                ", orderQty=" + orderQty +
                ", disscount=" + disscount +
                '}';
    }
}
