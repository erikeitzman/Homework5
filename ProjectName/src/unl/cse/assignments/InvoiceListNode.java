package unl.cse.assignments;

import java.util.Arrays;

import com.airamerica.Invoice;

public class InvoiceListNode {
	
    private InvoiceListNode next;
    private Invoice item;

    public static Invoice [] insertionSort(Invoice list[]) {
		Invoice result[] = Arrays.copyOf(list, list.length);
		int j;                    
		Invoice key;                
		int i;  

		for (j = 1; j < result.length; j++){
			key = result[j];
			for(i = j - 1; (i >= 0) && (result[ i ].compareStrStr(key)>= 0); i--)
			{
				result[ i+1 ] = result[ i ];
			}
			result[ i+1 ] = key;
		}
		return result;
	}
    
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