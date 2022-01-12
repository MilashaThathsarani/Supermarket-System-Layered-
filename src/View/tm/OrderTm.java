package View.tm;

public class OrderTm {
    private String customerId;
    private String customerName;
    private String customerAddress;
    private String customerCity;
    private String customerProvince;
    private String customerPostalCode;
    private String orderId;
    private String orderDate;

    public OrderTm() {
    }

    public OrderTm(String customerId, String customerName, String customerAddress, String customerCity, String customerProvince, String customerPostalCode, String orderId, String orderDate) {
        this.setCustomerId(customerId);
        this.setCustomerName(customerName);
        this.setCustomerAddress(customerAddress);
        this.setCustomerCity(customerCity);
        this.setCustomerProvince(customerProvince);
        this.setCustomerPostalCode(customerPostalCode);
        this.setOrderId(orderId);
        this.setOrderDate(orderDate);
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerCity() {
        return customerCity;
    }

    public void setCustomerCity(String customerCity) {
        this.customerCity = customerCity;
    }

    public String getCustomerProvince() {
        return customerProvince;
    }

    public void setCustomerProvince(String customerProvince) {
        this.customerProvince = customerProvince;
    }

    public String getCustomerPostalCode() {
        return customerPostalCode;
    }

    public void setCustomerPostalCode(String customerPostalCode) {
        this.customerPostalCode = customerPostalCode;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }


    @Override
    public String toString() {
        return "OrderTm{" +
                "customerId='" + customerId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", customerCity='" + customerCity + '\'' +
                ", customerProvince='" + customerProvince + '\'' +
                ", customerPostalCode='" + customerPostalCode + '\'' +
                ", orderId='" + orderId + '\'' +
                ", orderDate='" + orderDate + '\'' +
                '}';
    }
}
