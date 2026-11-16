package HashMapLab;


public class Node {

	private Entry data;
	private Node next;
	
	public Node(Entry data) {
		this.data = data;
		this.next = null;
	}
	
	//Getters & Setters

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}

	public Entry getData() {
		return data;
	}
	
	public void setData(Entry e) {
		this.data = e;
	}
	
	
	
	
	
}
