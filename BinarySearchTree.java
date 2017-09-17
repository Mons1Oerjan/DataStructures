/**
 * Generic Binary Search Tree data structure
 */
public class BinarySearchTree<T extends Comparable<T>> {
	private BinaryTree<T> tree;
	private int size;

	public BinarySearchTree() {
		tree = new BinaryTree<T>();
		size = 0;
	}

	public BinaryTree<T> getTree() {
		return tree;
	}

	public boolean isEmpty() {
		return tree.isEmpty();
	}

	public int size() {
		return size;
	}

	public BinaryTree<T> search(T key) {
		if (isEmpty()) {
			return null;
		}
		BinaryTree<T> t = tree;

		while (t != null) {
			if (key.compareTo(t.getData()) < 0)
				t = t.getLeft();
			else if (key.compareTo(t.getData()) > 0)
				t = t.getRight();
			else {
				// key is found:
				return t;
			}
		}

		return null;
	}

	public void insert(T item) {
		BinaryTree<T> newNode = new BinaryTree<T>();
		newNode.setData(item);

		if (size == 0) {
			tree = newNode;
			size++;
			return;
		}

		BinaryTree<T> t = tree;
		boolean done = false;

		while (!done) {
			int c = item.compareTo(t.getData());

			if (c == 0) {
				System.out.println("Duplicate key. Can't insert");
				return;
			}
			else if (c < 0) {
				// need to go left
				if (t.getLeft() == null) {
					// place to insert has been found:
					t.setLeft(newNode);
					newNode.setParent(t);
					size++;
					done = true;
				} else {
					// go left one branch
					t = t.getLeft();
				}
			} else {
				// need to go right
				if (t.getRight() == null) {
					// place to insert has been found:
					t.setRight(newNode);
					newNode.setParent(t);
					size++;
					done = true;
				} else {
					// go right one branch
					t = t.getRight();
				}
			}
		}
	}

	public BinaryTree<T> findPredecessor(BinaryTree<T> node) {
		if (node == null || node.getLeft() == null) {
			return null;
		}
		BinaryTree<T> pred = node.getLeft();

		while (pred.getRight() != null) {
			pred = pred.getRight();
		}

		return pred;
	}

	public void deleteHere(BinaryTree<T> deleteNode, BinaryTree<T> attach) {
		if (deleteNode == null) {
			return;
		}
		BinaryTree<T> parent = deleteNode.getParent();

		if (parent == null) {
			return;
		}
		if (attach == null) {
			if (parent.getLeft() == deleteNode)
				parent.setLeft(null);
			else
				parent.setRight(null);
			return;
		}
		if (deleteNode == parent.getRight()) {
			parent.detachRight();
			attach.setParent(parent);
			parent.attachRight(attach);
		} else {
			parent.detachLeft();
			attach.setParent(parent);
			parent.attachLeft(attach);
		}
		deleteNode.clear();
	}

	public void delete(T key) {
		if (size == 0) {
			System.out.println("Can't delete. Empty tree");
			return;
		}
		BinaryTree<T> deleteNode = search(key);

		if (deleteNode == null) {
			System.out.println("Key not found. Can't delete");
			return;
		}
		BinaryTree<T> hold = null;

		if (deleteNode.getLeft() == null && deleteNode.getRight() == null) {
			deleteHere(deleteNode, null);
		} else if (deleteNode.getLeft() == null) {
			hold = deleteNode.getRight();
			deleteHere(deleteNode, hold);
		} else if (deleteNode.getRight() == null) {
			hold = deleteNode.getLeft();
			deleteHere(deleteNode, hold);
		} else {
			hold = findPredecessor(deleteNode);
			deleteNode.setData(hold.getData());
			deleteNode = hold;
			deleteHere(deleteNode, deleteNode.getLeft());
		}
		size--;
	}

	// finds the rightmost node
	public T findMax() {
		if (isEmpty()) {
			return null;
		}
		BinaryTree<T> t = tree;

		while (t.getRight() != null) {
			t = t.getRight();
		}

		return t.getData();
	}

	// finds the leftmost node
	public T findMin() {
		if (isEmpty()) {
			return null;
		}
		BinaryTree<T> t = tree;

		while (t.getLeft() != null) {
			t = t.getLeft();
		}

		return t.getData();
	}

	// driver function
	public BinaryTree<T> recursiveSearch(T key) {
		if (tree.isEmpty()) {
			return null;
		} else {
			return recursiveSearch(tree, key);
		}
	}

	// recursive search method
	public BinaryTree<T> recursiveSearch(BinaryTree<T> t, T key) {
		if (key.compareTo(t.getData()) == 0) {
			// base case
			return t;
		} else if (key.compareTo(t.getData()) < 0) {
			return recursiveSearch(t.getLeft(), key);
		} else {
			return recursiveSearch(t.getRight(), key);
		}
	}

	// recursive function to determine if the given tree is a binary search tree
	public boolean isBinarySearchTree(BinaryTree<T> t, T min, T max) {
		if (t.getData() == null) {
			//base case
			return true;
		} else if (
			t.getData().compareTo(min) > 0 &&
			t.getData().compareTo(max) < 0 &&
			isBinarySearchTree(t.getLeft(), min, t.getData()) &&
			isBinarySearchTree(t.getRight(), t.getData(), max)
		) {
			// using min and max to set the limits of each child-node
			return true;
		} else {
			return false;
		}
	}

	// helper function
	public static <T> int inorder(BinaryTree<T> t, T[] a, int i) {
		if (t == null) {
			// base case
			return i;
		}
		i = inorder(t.getLeft(), a, i);
		a[i] = t.getData();
		i = inorder(t.getRight(), a, i +1);

		return i;
	}

	public BinarySearchTree<T> merge(BinarySearchTree<T> t1, BinarySearchTree<T> t2) {
		// TODO: Implement this function.
	}
}
