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
	
	public void emptyList(){
		start = null;
		end = null;
		size = 0;
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
	
	public void insertSortByCustomer(Invoice val){
		InvoiceListNode aNode = new InvoiceListNode(val, null);
		size++;
		if(start == null){
			start = aNode;
			end = start;
			return;
		}
		InvoiceListNode temp1 = start;
		InvoiceListNode temp2 = start.getNext();
		if(size == 2){
			if(val.compareStrStr(temp1.getinvoice())==1){
				insertAtStart(val);
				return;
			}else{
				start.setNext(aNode);
				end = aNode;
				return;
			}
		}
		if(val.compareStrStr(temp1.getinvoice())==1){
			insertAtStart(val);
			return;
		}
		for(int i = 0; i<size-1; i++){
			if((val.compareStrStr(temp1.getinvoice())==1 && val.compareStrStr(temp2.getinvoice())==-1) || val.compareStrStr(temp1.getinvoice())==0){
				aNode.setNext(temp2);
				temp1.setNext(aNode);
				return;
			}else{
				temp1 = temp2;
				temp2 = temp2.getNext();
				if (temp2 == null){
					end = aNode;
					temp1.setNext(aNode);
					return;
				}
			}
		}
	}
	
	public void insertSortBySub(Invoice val){
		InvoiceListNode aNode = new InvoiceListNode(val, null);
		size++;
		if(start == null){
			start = aNode;
			end = start;
			return;
		}
		InvoiceListNode temp1 = start;
		InvoiceListNode temp2 = start.getNext();
		if(size == 2){
			if(val.compareDoubDoub(temp1.getinvoice())==1){
				insertAtStart(val);
				return;
			}else{
				start.setNext(aNode);
				end = aNode;
				return;
			}
		}
		if(val.compareDoubDoub(temp1.getinvoice())==1){
			insertAtStart(val);
			return;
		}
		for(int i = 0; i<size-1; i++){
			if((val.compareDoubDoub(temp1.getinvoice())==-1 && val.compareDoubDoub(temp2.getinvoice())==1) || val.compareDoubDoub(temp1.getinvoice())==0){
				aNode.setNext(temp2);
				temp1.setNext(aNode);
				return;
			}else{
				temp1 = temp2;
				temp2 = temp2.getNext();
				if (temp2 == null){
					end = aNode;
					temp1.setNext(aNode);
					return;
				}
			}
		}
	}
	
	public void insertSortByStuff(Invoice val){
		InvoiceListNode aNode = new InvoiceListNode(val, null);
		size++;
		if(start == null){
			start = aNode;
			end = start;
			return;
		}
		InvoiceListNode temp1 = start;
		InvoiceListNode temp2 = start.getNext();
		if(size == 2){
			if(val.compareStrStr2(temp1.getinvoice())==1){
				insertAtStart(val);
				return;
			}else{
				start.setNext(aNode);
				end = aNode;
				return;
			}
		}
		if(val.compareStrStr2(temp1.getinvoice())==1){
			insertAtStart(val);
			return;
		}
		for(int i = 0; i<size-1; i++){
			if(val.compareStrStr2(temp1.getinvoice())==1 && val.compareStrStr2(temp2.getinvoice())==-1){
				aNode.setNext(temp2);
				temp1.setNext(aNode);
				return;
			}else if(val.compareStrStr2(temp1.getinvoice())==0){
				
			}
			else{
				temp1 = temp2;
				temp2 = temp2.getNext();
				if (temp2 == null){
					end = aNode;
					temp1.setNext(aNode);
					return;
				}
			}
		}
	}
}
