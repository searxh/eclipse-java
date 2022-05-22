import java.util.ArrayList;
import java.util.Arrays;

public class BSTRecursive {

	BSTNode root;
	int size;

	public BSTRecursive(BSTNode root, int size) {
		this.root = root;
		this.size = size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void makeEmpty() {
		root = null;
		size = 0;
	}

	public Iterator findMin() {
		return findMin(root);
	}

	public Iterator findMin(BSTNode n) {
		if (n == null)
			return null;
		if (n.left == null) {
			Iterator itr = new TreeIterator(n);
			return itr;
		}
		return findMin(n.left);
	}

	public int findMax() {
		return findMax(root);
	}

	public int findMax(BSTNode n) {
		if (n==null) {
			return -1;
		}
		if (n.right == null && n.left == null) {
			return n.data;
		}
		int left = findMax(n.left);
		int right = findMax(n.right);
		int max = (left>right)?left:right;
		return (n.data>max)?n.data:max;
	}

	public Iterator find(int v) {
		return find(v, root);
	}

	public Iterator find(int v, BSTNode n) {
		if (n == null)
			return null;
		if (v == n.data)
			return new TreeIterator(n);
		if (v < n.data)
			return find(v, n.left);
		else
			return find(v, n.right);
	}

	public BSTNode insert(int v) {
		return insert(v, root, null);
	}

	// return the node n after v was added into the tree
	public BSTNode insert(int v, BSTNode n, BSTNode parent) {
		if (n == null) {
			n = new BSTNode(v, null, null, parent);
			size++;
		} else if (v < n.data) {
			n.left = insert(v, n.left, n);
		} else if (v > n.data) {
			n.right = insert(v, n.right, n);
		}
		return n;
	}

	public BSTNode remove(int v) {
		return remove(v, root, null);
	}

	// return the node n after v was removed from the tree
	public BSTNode remove(int v, BSTNode n, BSTNode parent) {
		if (n == null)
			; // do nothing, there is nothing to be removed
		else if (v < n.data) {
			n.left = remove(v, n.left, n);
		} else if (v > n.data) {
			n.right = remove(v, n.right, n);
		} else {
			if (n.left == null && n.right == null) {
				n.parent = null; // disconnect from above
				n = null; // disconnect from below
				size--;
			} else if (n.left != null && n.right == null) {
				BSTNode n2 = n.left;
				n2.parent = parent;
				n.parent = null; // disconnect from above
				n.left = null; // disconnect from below
				n = n2; // change to the node below
						// to prepare for connection from parent
				size--;
			} else if (n.right != null && n.left == null) {
				BSTNode n2 = n.right;
				n2.parent = parent;
				n.parent = null; // disconnect from above
				n.right = null; // disconnect from below
				n = n2; // change to the node below
						// to prepare for connection from parent
				size--;
			} else {
				TreeIterator i = (TreeIterator) findMin(n.right);
				int minInRightSubtree = i.currentNode.data;
				n.data = minInRightSubtree;
				n.right = remove(minInRightSubtree, n.right, n);
			}
		}
		return n;
	}

	private int height(BSTNode n) {
		if (n == null)
			return -1;
		return 1 + Math.max(height(n.left), height(n.right));
	}

	/**************************************************************************************************/
	// Edit only the following methods.
	/**************************************************************************************************/

	/**
	 * Internal method to find the number of nodes in a given subtree using a
	 * recursive method.
	 * 
	 * @param n
	 *            the root of a given subtree
	 * @param v
	 *            the threshold 
	 * @return the number of nodes in which the data is less than v in the subtree 
	 */
	public int numNodesLessThan(BSTNode n,int v) {
		if (n == null) {
			return 0;
		}
		int counter = 0;
		if (n.data < v) {
			counter++;
		}
		int left = numNodesLessThan(n.left,v);
		int right = numNodesLessThan(n.right,v);
		return counter+left+right;
	}

	/**
	 * Internal method to count the number of leaves in a given subtree
	 * (recursively).
	 * 
	 * @param n
	 *            root node of a given subtree
	 * @param v
	 *            the threshold
	 * @return number of leaves in which the data is less than v in the subtree, 
	 */
	public int numLeavesLessThan(BSTNode n, int v) {
		if (n==null) {
			return 0;
		}
		int counter = 0;
		if (n.left == null && n.right == null && n.data < v) {
			counter++;
		}
		int left = numLeavesLessThan(n.left,v);
		int right = numLeavesLessThan(n.right,v);
		return counter+left+right;
	}

	/**
	 * In order insert all nodes from a BST that has n as the root node. 
	 * 
	 * @param n
	 *            the node that roots the subtree.
	 * 
	 */
	public void insertInOrder(BSTNode n) {
		if (n==null) {
			return;
		}
		insert(n.data);
		insertInOrder(n.left);
		insertInOrder(n.right);
	}
	
	/**
	 * Find the next data of v in the subtree which has n as the root node
	 * 
	 * @param n
	 *            root node of a given subtree
	 * @param v
	 *            the value v
	 * @return next data of v in the given subtree 
	 */
	public int sum(BSTNode n) {
		if (n==null) {
			return 0;
		}
		return sum(n.left)+sum(n.right)+n.data;
	}
	public void invert(BSTNode n) {
		if (n==null) {
			return;
		}
		invert(n.left);
		invert(n.right);
		BSTNode save = n.left;
		n.left = n.right;
		n.right = save;
	}
	public ArrayList<int[]> findSumOfPath(int sum) {
		int paths = findTotalPath(root);
		int [] arr = new int[height(root)+1];
		ArrayList<int[]> list = new ArrayList<int[]>();
		sumOfPath(root,sum,arr,0,list);
		for (int i = 0; i < list.size(); i++) {
			int [] array = list.get(i);
			for (int j = 0; j < array.length; j++) {
				System.out.print(array[j]+",");
			}
			System.out.println("");
		}
		return list;
		
	}
	public void sumOfPath(BSTNode n, int sum, int [] arr, int i, ArrayList<int[]> list) {
		if (n==null) {
			return;
		}
		arr[i] = n.data;
		sum -= n.data;
		i++;
		if (n.right == null && n.left == null) {
			if (sum == 0) {
				list.add(getArray(arr, i));
			}
		}
		sumOfPath(n.left,sum,arr,i,list);
		sumOfPath(n.right,sum,arr,i,list);
	}
	public int [] getArray(int [] a, int length) {
		int[] list = new int[length];
		for (int i=0; i < length; i++) {
			list[i] = a[i];
		}
		return list;
	}
	public int findTotalPath(BSTNode n) {
		if (n==null) {
			return 0;
		}
		int counter = 0;
		if (n.right == null && n.left == null) {
			counter++;
		}
		return findTotalPath(n.left)+findTotalPath(n.right)+counter;
	}
	public int nextOf(BSTNode n,int v) {
		if (n==null) {
			return -1;
		}
		if (v > findMax()) {
			return v;
		} else if (v == findMax() && n.data == findMax()) {
			return n.data;
		}
		if (n.data > v) {
			int left = nextOf(n.left, v); //have to save because it might not find
			if (left==-1) {
				if (n.right == null && n.data == findMax()) {
					return n.data;
				}
				return n.data;
			}
			return left;
		}
		return nextOf(n.right,v);
	}

}
