package BST_A2;

public class BST implements BST_Interface {
	public BST_Node root;
	public int size;

	public BST(){
		size = 0;
		root = null;
	}

	//used for testing, please leave as is
	public BST_Node getRoot(){
		return root;
	}


	// Insert

	public boolean insert(String s) {
		if(root == null) {
			root = new BST_Node(s);
			size++;
			return true;
		} else {
			size++;
			return root.insertNode(s);
		}
	}

	// End Insert

	// Remove
	public boolean remove(String s) {
		if (root == null) {
			return false;
		} else if (root.getData().compareTo(s) == 0) {
			if (root.getLeft() == null && root.getRight() == null) {
				root = null;
			} else if (root.getLeft() == null) {
				root = root.getRight();
			} else if (root.getRight() == null) {
				root = root.getLeft();
			} else {
				String min = root.getRight().findMin().getData();
				root.removeNode(min);
				root.data = min;
			}
			size--;
			return true;
		} else {
			size--;
			return root.removeNode(s);
		}
	}

	// End Remove

	// Find Min

	public String findMin() {
		if (root == null) {
			return null;
		} else {
			return root.findMin().getData();
		}
	}

	// End Find Min

	// Find Max

	public String findMax() {
		if (root == null) {
			return null;
		} else {
			return root.findMax().getData();
		}
	}

	// End Find Max

	// Empty

	public boolean empty() {
		return (size == 0);
	}

	// End Empty

	// Contains

	public boolean contains(String s) {
		if (root == null) {
			return false;
		} else if (root.getData().compareTo(s) == 0) {
			return true;
		} else {
			return root.containsNode(s);
		}
	}

	// End Contains

	// Size

	public int size() {
		return size;
	}

	// End Size

	// Height

	public int height() {
		if (root == null) {
			return -1;
		} else {
			return root.getHeight();
		}
	}
	// End Get Height
}