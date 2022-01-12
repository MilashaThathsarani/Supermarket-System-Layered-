package View.tm;

public class OrderDetailTm {
    private String code;
    private String orderId;
    private int orderQty;
    private double unitPrice;

    public OrderDetailTm() {
    }

    public OrderDetailTm(String code, String orderId, int orderQty, double unitPrice) {
        this.setCode(code);
        this.setOrderId(orderId);
        this.setOrderQty(orderQty);
        this.setUnitPrice(unitPrice);
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

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "OrderDetailTm{" +
                "code='" + code + '\'' +
                ", orderId='" + orderId + '\'' +
                ", orderQty=" + orderQty +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
