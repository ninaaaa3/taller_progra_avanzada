package modelo;

public class Factura {
    private int invoiceID;
    private int customerID;
    private String invoiceDate;
    private String PaymentCurrency;
    private Float total_CLP;
    private String Status;

    public int getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getPaymentCurrency() {
        return PaymentCurrency;
    }

    public void setPaymentCurrency(String PaymentCurrency) {
        this.PaymentCurrency = PaymentCurrency;
    }

    public Float getTotal_CLP() {
        return total_CLP;
    }

    public void setTotal_CLP(float total_CLP) {
        this.total_CLP = total_CLP;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }
}
