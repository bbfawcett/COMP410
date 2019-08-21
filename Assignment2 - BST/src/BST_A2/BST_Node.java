package BST_A2;

public class BST_Node {
	String data;
	BST_Node left;
	BST_Node right;

	BST_Node(String data){ this.data=data; }

	// --- used for testing  ----------------------------------------------
	//
	// leave these 3 methods in, as is

	public String getData(){ 
		return data; 
	}

	public BST_Node getLeft(){ 
		return left; 
	}

	public BST_Node getRight(){ 
		return right; 
	}

	// --- end used for testing -------------------------------------------

	public boolean containsNode(String s){
		if (nodePlacer(s) == null) {
			return false;
		} else if (s.compareTo(nodePlacer(s).getData()) == 0) {
			return true;
		} else {
			return nodePlacer(s).containsNode(s);
		}
	}

	public boolean insertNode(String s){ 
		if (this.getData().compareTo(s) == 0) {
			return false;
		} else if (this.nodePlacer(s) == null) {
			if (s.compareTo(this.getData()) < 0) {
				left = new BST_Node(s);
			} else {
				right = new BST_Node(s);
			}
			return true;
		} else {
			return nodePlacer(s).insertNode(s);
		}
	}

	public boolean removeNode(String s){
		if (nodePlacer(s) == null) {
			return false;
		} else if (nodePlacer(s).getData().compareTo(s) == 0) {
			if (nodePlacer(s).getLeft() == null && nodePlacer(s).getRight() == null) {
				if (s.compareTo(this.getData()) < 0) {
					left = null;
				} else {
					right = null;
				}
			} else if (nodePlacer(s).getLeft() == null) {
				if (s.compareTo(this.getData()) < 0) {
					left = nodePlacer(s).getRight();
				} else {
					right = nodePlacer(s).getRight();
				}
			} else if (nodePlacer(s).getRight() == null) {
				if (s.compareTo(this.getData()) < 0) {
					left = nodePlacer(s).getLeft();
				} else {
					right = nodePlacer(s).getLeft();
				}
			} else {
				String minR = nodePlacer(s).getRight().findMin().getData();
				removeNode(minR);
				nodePlacer(s).data = minR;
			}
			return true;
		} else {
			return nodePlacer(s).removeNode(s);
		}
	}

	public BST_Node findMin(){ 
		if(getLeft() == null) {
			return this;
		} else {
			return getLeft().findMin();
		}
	}

	public BST_Node findMax(){ 
		if(getRight() == null) {
			return this;
		} else {
			return getRight().findMax();
		}
	}
	
	public int getHeight(){
		int leftHeight = 0;
		int rightHeight = 0;
		if (getLeft() == null) {
			leftHeight--;
		} else {
			leftHeight = getLeft().getHeight();
		}
		
		if (getRight() == null) {
			rightHeight--;
		} else {
			leftHeight = getRight().getHeight();
		}


		if (leftHeight > rightHeight) {
			return leftHeight + 1;
		} else {
			return rightHeight + 1;
		}
	}


	public BST_Node nodePlacer(String s) {
		if (s.compareTo(getData()) < 0) {
			return left;
		} else {
			return right;
		}
	}
	
	public String toString(){
		return "Data: "+this.data+", Left: "+((this.left!=null)?left.data:"null")
				+",Right: "+((this.right!=null)?right.data:"null");
	}
}