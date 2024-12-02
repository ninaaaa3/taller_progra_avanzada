package modelo;

public class DetalleFactura {
    private int invoiceLineID;
    private int invoiceID;
    private int trackID;
    private String trackName;
    private int quantity;
    private float unitPrice;
    private float lineTotal;

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

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public float getLineTotal() {
        return lineTotal;
    }

    public void setLineTotal(float lineTotal) {
        this.lineTotal = lineTotal;
    }
}

