package modelo;

public class InvoiceLine {
    private int invoiceLineID;
    private int invoiceID;
    private int trackID;
    private int quantity;
    private double lineTotal;
    private String currencyType;
    private double discount;

    public int getInvoiceLineID() {
        return invoiceLineID;
    }

    public void setInvoiceLineID(int invoiceLineID) {
        this.invoiceLineID = invoiceLineID;
    }

    public int getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }

    public int getTrackID() {
        return trackID;
    }

    public void setTrackID(int trackID) {
        this.trackID = trackID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getLineTotal() {
        return lineTotal;
    }

    public void setLineTotal(double lineTotal) {
        this.lineTotal = lineTotal;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(String currencyType) {
        this.currencyType = currencyType;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
