package modelo;

public class DetalleFactura {
    private int InvoiceLineID;
    private int InvoiceID;
    private int TrackID;
    private int Quantity;
    private float UnitPrice;
    private float LineTotal;

    public int getInvoiceLineID() {
        return InvoiceLineID;
    }

    public void setInvoiceLineID(int InvoiceLineID) {
        this.InvoiceLineID = InvoiceLineID;
    }

    public int getInvoiceID() {
        return InvoiceID;
    }

    public void setInvoiceID(int InvoiceID) {
        this.InvoiceID = InvoiceID;
    }

    public int getTrackID() {
        return TrackID;
    }

    public void setTrackID(int TrackID) {
        this.TrackID = TrackID;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public float getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(float UnitPrice) {
        this.UnitPrice = UnitPrice;
    }

    public float getLineTotal() {
        return LineTotal;
    }

    public void setLineTotal(float LineTotal) {
        this.LineTotal = LineTotal;
    }
}
