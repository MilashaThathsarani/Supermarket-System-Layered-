package dto;

import javafx.scene.control.TextField;

public class OrderDetailDTO {
    private String oid;
    private String itemCode;
    private int qty;
    private double unitPrice;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(String oid, String itemCode, int qty, double unitPrice) {
        this.setOid(oid);
        this.setItemCode(itemCode);
        this.setQty(qty);
        this.setUnitPrice(unitPrice);
    }

    public OrderDetailDTO(Object id, String itemCode, int qty, double unitPrice) {
    }

    public OrderDetailDTO(String itemCode, TextField txtOrderId, int qty, double unitPrice) {
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "OrderDetailDTO{" +
                "oid='" + oid + '\'' +
                ", itemCode='" + itemCode + '\'' +
                ", qty=" + qty +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
