package unl.cse.assignments;

import com.airamerica.Invoice;

public class InvoiceListNode {
	
    private InvoiceListNode next;
    private Invoice item;

    public InvoiceListNode(Invoice item, InvoiceListNode next) {
        this.item = item;
        this.next = null;
    }

    public Invoice getinvoice() {
        return item;
    }

    public InvoiceListNode getNext() {
        return next;
    }

    public void setNext(InvoiceListNode next) {
        this.next = next;
    }
}