/**
 * COMP 410
 *See inline comment descriptions for methods not described in interface.
 *
 */
package LinkedList_A1;

public class LinkedListImpl implements LIST_Interface {
	public int size;
	Node sentinel; //this will be the entry point to your linked list (the head)

	public LinkedListImpl() {//this constructor is needed for testing purposes. Please don't modify!
		sentinel = new Node(0); //Note that the root's data is not a true part of your data set!
		size = 0;
		sentinel.setPrev(sentinel);
		sentinel.setNext(sentinel);
	}

	//implement all methods in interface, and include the getRoot method we made for testing purposes. Feel free to implement private helper methods!

	public Node getRoot(){ //leave this method as is, used by the grader to grab your linkedList easily.
		return sentinel;
	}

	@Override
	public boolean insert(double elt, int index) {
		// TODO Auto-generated method stub
		Node previous = getNode(index - 1);
		Node next = getNode(index);
		Node newNode = new Node(elt);
		
		if (index < 0 || index > size) {
			return false;
		} else {
			previous.setNext(newNode);
			newNode.setPrev(previous);
			newNode.setNext(next);
			next.setPrev(newNode);
			size++;
			return true;
		}
	}

	@Override
	public boolean remove(int index) {
		// TODO Auto-generated method stub
		Node previous = getNode(index - 1);
		Node next = getNode(index + 1);
		if (index < 0 || index >= size) {
			return false;
		} else {
			previous.setNext(next);
			next.setPrev(previous);
			size--;
			return true;
		}
	}

	@Override
	public double get(int index) {
		// TODO Auto-generated method stub
		if(index >= size || index < 0) {
			return Double.NaN;
		} else {
			Node tmp = sentinel;
			for(int i = 0; i <= index; i++) {
				tmp = tmp.getNext();
			}
			return tmp.getData();
		}
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return (size == 0);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		size = 0;
		sentinel.setPrev(sentinel);
		sentinel.setNext(sentinel);
	}
	
	public Node getNode(int index) {
		if(isEmpty() || index == size || index < 0) {
			return sentinel;
		} else {
			Node tmp = sentinel;
			for(int i = 0; i <= index; i++) {
				tmp = tmp.getNext();
			}
			return tmp;
		}
	}
}