
public class MyLinkedList {
	
	int size;
	Node first;
	Node last;
		
	public MyLinkedList() {		
		size = 0;
		first = null;
		last = null;
	}
	
	public void add(String s) {
		Node node = new Node();
		node.val = s;
		if (last == null) { 
			first = node;
			last = node;
		}
		else {
			last.next = node;
			last = node;
		}
	}
	
	public void delete(String s) {
		Node curr = first;
		Node prev = first;
		while (curr != null) {
			if (curr.val.equals(s)) {
				prev.next = curr.next;
				curr = null;
				return;
			}
			prev = curr;
			curr = curr.next;
		}		
	}
	
	public Node find(String s) {
		Node node = first;
		while (node != null) {
			if (node.val.equals(s)) {
				return node;
			}
			node = node.next;
		}
		return null;
	}
	
	public void print() {
		Node node = first;
		while (node != null) {
			System.out.print(node.val+", ");
			node = node.next;
		}
		System.out.println("\n");
	}
	
	public void checkCycle() {
		Node p1 = first;
		Node p2 = first;
		int p1no = 1;
		int p2no = 0;
		
		while (true) {
			if (p1.next == null || p1.next.next == null) {
				System.out.println("NO CYCLE");
				return;
			}
			p1 = p1.next.next;
			p2 = p2.next;
			if (p1 == p2) {
				System.out.println("CYCLE FOUND");
				return;
			}
			
		}
	}
	
	public void findItemFromLast(int n) {
		
		Node p1 = first;
		Node p2 = first;
		int p1pos = 0;
		int p2pos = 0;
		
		while (p1 != null) {
			p1 = p1.next;
			p1pos++;
			if (p1pos%2 == 0) {
				p2 = p2.next;
				p2pos++;
			}
		}
		System.out.println("Halfway P2="+p2.val);
		
		if (p1pos-p2pos > n) {
			while (p1pos-p2pos != n) {
				p2 = p2.next;
				p2pos++;
			}
		}
		
		System.out.println("P2="+p2.val);
	}

	public void reverseR() {
		reverseR(first);
	}

	public void reverseR(Node node) {
		if (node == null) {
			return;
		}
		Node rest = node.next;
		if (rest == null) {
			return;
		}
		reverseR(rest);
		node.next.next = node;
		node.next = null;
		node = rest;
	}

	public void reverse() {
		if (first == null) return;
		Node prev = null;
		Node curr = first;	
		
		while (curr != null) {
			Node next = curr.next;
			curr.next = prev;			
			prev = curr;
			curr = next;
		}		
		first = prev;
	}

	class Node {
		String val;
		Node next;	
		
		public void print() {
			System.out.println("Val="+val);
		}
	}
	
	public static void main(String[] args) {
		MyLinkedList list = new MyLinkedList();
		list.add("abc");
		list.add("xyz");
		list.add("A");
		list.add("B");
		list.add("C");
		list.add("D");
		list.print();
		Node n1 = list.find("D");
		n1.print();
		Node n2 = list.find("abc");
		//n1.next = n1; 
		list.checkCycle();
		list.findItemFromLast(2);
		list.reverse();
		list.print();
		
	}
}
