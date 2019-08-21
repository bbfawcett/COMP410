package MinBinHeap_A3;

import javax.swing.border.EmptyBorder;

public class MinBinHeap implements Heap_Interface {
	private EntryPair[] array; //load this array
	private int size;
	private static final int arraySize = 10000; //Everything in the array will initially 
	//be null. This is ok! Just build out 
	//from array[1]

	public MinBinHeap() {
		this.array = new EntryPair[arraySize];
		array[0] = new EntryPair(null, -100000); //0th will be unused for simplicity 
		//of child/parent computations...
		//the book/animation page both do this.
		size = 0;
	}

	//Please do not remove or modify this method! Used to test your entire Heap.
	@Override
	public EntryPair[] getHeap() { 
		return this.array;
	}

	@Override
	public void insert(EntryPair entry) {
		size++;
		array[size] = entry;
		sortUp(size);
	}

	@Override
	public void delMin() {
		if (size == 0) {
			return;
		}
		array[1] = array[size];
		array[size] = null;
		size--;
		sortDown(1);;
	}

	@Override
	public EntryPair getMin() {
		return array[1];
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void build(EntryPair[] entries) {
		for (int i = 0; i < entries.length; i++) {
			array[i+1] = entries[i];
		}
		size += entries.length;
		for(int i = size/2; i > 0; i--) {
			sortDown(i);
		}
	}

	public void sortDown(int n) {
		int child;
		EntryPair tmp = array[n];

		while(n * 2 <= size) {
			child = n*2;
			if (child != size && array[child+1].getPriority() < array[child].getPriority()) {
				child++;
			}
			if(array[child].getPriority() < tmp.getPriority()) {
				array[n] = array[child];
			} else {
				break;
			}
			n = child;
		}
		array[n] = tmp;

	}

	public void sortUp(int n) {
		int parent = n/2;
		if (parent == 0) {

		} else if(array[n].getPriority() < array[parent].getPriority()) {
			EntryPair tmp = array[parent];
			array[parent] = array[n];
			array[n] = tmp;
			sortUp(parent);
		}
	}
}