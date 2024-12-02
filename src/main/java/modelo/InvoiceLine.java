/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

public class InvoiceLine {
    private int invoiceLineId;
    private int invoiceId;
    private int trackId;
    private int quantity;
    private double unitPrice;
    private double lineTotal;

    public int getInvoiceLineId() { return invoiceLineId; }
    public void setInvoiceLineId(int invoiceLineId) { this.invoiceLineId = invoiceLineId; }

    public int getInvoiceId() { return invoiceId; }
    public void setInvoiceId(int invoiceId) { this.invoiceId = invoiceId; }

    public int getTrackId() { return trackId; }
    public void setTrackId(int trackId) { this.trackId = trackId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getUnitPrice() { return unitPrice; }
    public void setUnitPrice(double unitPrice) { this.unitPrice = unitPrice; }

    public double getLineTotal() { return lineTotal; }
    public void setLineTotal(double lineTotal) { this.lineTotal = lineTotal; }
}
