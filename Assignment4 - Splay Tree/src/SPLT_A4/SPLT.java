package SPLT_A4;

public class SPLT implements SPLT_Interface{
	private BST_Node root;
	private int size;
	private BST_Node nodeOfInterest;

	public SPLT() {
		this.size = 0;
		nodeOfInterest = null;
	} 

	public BST_Node getRoot() { //please keep this in here! I need your root node to test your tree!
		return root;
	}

	@Override
	public void insert(String s) {
		boolean tmp = containsNoSplay(s);
		if (tmp) {
			splay(nodeOfInterest);
		} else {
			if (empty()) {
				root = new BST_Node(s, null, null, null);
			} else {
				BST_Node splayNode = nodeAdder(nodeOfInterest, s);
				splay(splayNode);
			}
			size++;
		}
	}

	public void remove(String s) {
		if (contains(s)) {
			BST_Node lNode = root.getLeft();
			BST_Node rNode = root.getRight();

			root.left = null;
			root.right = null;
			if (lNode != null) {
				findMax(lNode);
				root.right = rNode;
				if (rNode != null) {
					rNode.par = root;
				}
			} else {
				if (rNode != null) {
					root = rNode;
					rNode.par = null;
				}
			}
			size--;
		}
	}

	@Override
	public String findMin() {
		BST_Node tmp = root;
		if (tmp == null) {
			return null;
		} else {
			while (tmp.getLeft() != null) {
				tmp = tmp.getLeft();
			}
			splay(tmp);
			return tmp.getData();
		}
	}

	public String findMax() {
		// TODO Auto-generated method stub
		BST_Node tmp = root;
		if (tmp == null) {
			return null;
		} else {
			while (tmp.getRight() != null) {
				tmp = tmp.getRight();
			}
			splay(tmp);
			return tmp.getData();
		}
	}

	public void findMax(BST_Node n) {
		while (n.getRight() != null) {
			n = n.getRight();
		}

		splay(n);
	}

	@Override
	public boolean empty() {
		// TODO Auto-generated method stub
		return (size == 0);
	}


	public boolean contains(String s) {
		if (empty()) {
			return false;
		} else {
			BST_Node newNode = root;
			while(nodePlacer(newNode, s) != null && newNode.getData().compareTo(s) != 0) {
				newNode = nodePlacer(newNode, s);
			}
			if (newNode.getData().equals(s)) {
				splay(newNode);
				return true;
			} else {
				splay(newNode);
				return false;
			}
		}

	}

	public boolean containsNoSplay(String s) {
		if (empty()) {
			return false;
		} else {
			if (root.getData().compareTo(s) == 0) {
				nodeOfInterest = root;
				return true;
			}
			if (size == 1) {
				nodeOfInterest = root;
				return false;
			}
			BST_Node newNode = root;
			while(nodePlacer(newNode, s) != null && newNode.getData().compareTo(s) != 0) {
				newNode = nodePlacer(newNode, s);
			}
			if (newNode.getData().equals(s)) {
				nodeOfInterest = newNode;
				return true;
			}

			if (nodePlacer(newNode, s) == null) {
				nodeOfInterest = newNode;
				return false;
			}

			return false;

		}

	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public int height() {
		if (root == null) {
			return -1;
		} else {
			return root.getHeight();
		}
	}  

	public BST_Node nodePlacer(BST_Node n, String s) {
		if (s.compareTo(n.getData()) < 0) {
			return n.getLeft();
		} else {
			return n.getRight();
		}
	}

	public BST_Node nodeAdder(BST_Node n, String s) {
		if (s.compareTo(n.getData()) < 0) {
			n.left = new BST_Node(s, null, null, n);
			return n.left;
		} else {
			n.right = new BST_Node(s, null, null, n);
			return n.right;
		}
	}

	private void rotateR(BST_Node n) {
		BST_Node par = n.par;
		BST_Node gpar = par.par;
		n.par = gpar;
		par.left = n.getRight();
		if (n.getRight() != null) {
			n.getRight().par = par;
		}
		n.right = par;
		par.par = n;
		if (gpar != null) {
			if (n.getData().compareTo(gpar.getData()) < 0) {
				gpar.left = n;
			} else {
				gpar.right = n;
			}
		}
		if (gpar == null) {
			root = n;
		}
	}

	private void rotateL(BST_Node n) {
		BST_Node par = n.par;
		BST_Node gpar = par.par;
		n.par = gpar;
		par.right = n.getLeft();
		if (n.getLeft() != null) {
			n.getLeft().par = par;
		}
		n.left = par;
		par.par = n;
		if (gpar != null) {
			if (n.getData().compareTo(gpar.getData()) < 0) {
				gpar.left = n;
			} else {
				gpar.right = n;
			}
		}
		if (gpar == null) {
			root = n;
		}
	}

	private void splay(BST_Node n) {
		if (n.par == null) {
			root = n;
		} else {
			BST_Node par = n.par;
			if (n.getData().compareTo(par.getData()) < 0) {
				rotateR(n);
			} else {
				rotateL(n);
			}
			if (n.par != null) {
				splay(n);
			}
		}
	}
}