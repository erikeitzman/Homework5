package unl.cse.assignments;

import com.airamerica.Invoice;

public class LinkedList {
	private InvoiceListNode start;
	private InvoiceListNode end;
	private int size;
	
	public InvoiceListNode getStart() {
		return start;
	}

	public void setStart(InvoiceListNode start) {
		this.start = start;
	}

	public InvoiceListNode getEnd() {
		return end;
	}

	public void setEnd(InvoiceListNode end) {
		this.end = end;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public LinkedList(){
		start = null;
		end = null;
		size = 0;
	}
	
	public boolean isEmpty(){
		return start == null;
	}
	
	public int getSize(){
		return size;
	}
	
	public void insertAtStart(Invoice val){
		InvoiceListNode aNode = new InvoiceListNode(val, null);
		size++;
		if(start == null){
			start = aNode;
			end = start;
		}else{
			aNode.setNext(start);
			start = aNode;
		}
	}
}
