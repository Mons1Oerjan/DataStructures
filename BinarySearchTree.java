//Binary Search Tree class
//uses the Binary Tree class
public class BinarySearchTree<T extends Comparable<T>>
// you are using the compareTo method on objects of type T; hence T should
// extend Comparable<T>
{
	private BinaryTree<T> tree;
	private int size;

	public BinarySearchTree()
	{
		tree = new BinaryTree<T>();
		size = 0;
	}

	public BinaryTree<T> getTree()
	{
		return tree;
	}

	public boolean isEmpty()
	{
		return tree.isEmpty();
	}

	public int size()
	{
		return size;
	}

	public BinaryTree<T> search(T key)
	{
		BinaryTree<T> t = tree;
		if (isEmpty())
			return null;
		while (t != null)
		{
			if (key.compareTo(t.getData()) < 0)
				t = t.getLeft();
			else if (key.compareTo(t.getData()) > 0)
				t = t.getRight();
			else // key is found
				return t;
		}
		return null;
	}

	public void insert(T item)
	{
		BinaryTree<T> newNode = new BinaryTree<T>(); // sets left, right, parent
														// and data to null
		newNode.setData(item);

		if (size == 0)
		{
			tree = newNode;
			size++;
			return;
		}

		BinaryTree<T> t = tree;
		boolean done = false;
		while (!done)
		{
			int c = item.compareTo(t.getData());
			if (c == 0)
			{
				System.out.println("Duplicate key. Can't insert");
				return;
			} 
			else if (c < 0) // need to go left
			{
				if (t.getLeft() == null) // place to insert found
				{
					t.setLeft(newNode);
					newNode.setParent(t);
					size++;
					done = true;
				} 
				else
					t = t.getLeft(); // otherwise go left one branch
			} 
			else // c>0; need to go right
			{
				if (t.getRight() == null) // place to insert found
				{
					t.setRight(newNode);
					newNode.setParent(t);
					size++;
					done = true;
				} 
				else
					t = t.getRight();
			}
		}
	}

	public BinaryTree<T> findPredecessor(BinaryTree<T> node)
	{
		if (node == null)
			return null;
		if (node.getLeft() == null)
			return null;
		BinaryTree<T> pred = node.getLeft();
		while (pred.getRight() != null)
			pred = pred.getRight();
		return pred;
	}

	public void deleteHere(BinaryTree<T> deleteNode, BinaryTree<T> attach)
	{
		if (deleteNode == null)
			return;
		BinaryTree<T> parent = deleteNode.getParent();

		if (parent == null)
			return;
		if (attach == null)
		{
			if (parent.getLeft() == deleteNode)
				parent.setLeft(null);
			else
				parent.setRight(null);
			return;
		}
		if (deleteNode == parent.getRight())
		{
			parent.detachRight();
			attach.setParent(parent);
			parent.attachRight(attach);
		} 
		else
		{
			parent.detachLeft();
			attach.setParent(parent);
			parent.attachLeft(attach);
		}
		deleteNode.clear();
	}

	public void delete(T key)
	{
		if (size == 0)
		{
			System.out.println("Can't delete. Empty tree");
			return;
		}
		BinaryTree<T> deleteNode = search(key);
		if (deleteNode == null)
		{
			System.out.println("Key not found. Can't delete");
			return;
		}

		BinaryTree<T> hold = null;
		if (deleteNode.getLeft() == null && deleteNode.getRight() == null)
		{
			deleteHere(deleteNode, null);
		} 
		else if (deleteNode.getLeft() == null)
		{
			hold = deleteNode.getRight();
			deleteHere(deleteNode, hold);
		} 
		else if (deleteNode.getRight() == null)
		{
			hold = deleteNode.getLeft();
			deleteHere(deleteNode, hold);
		} 
		else
		{
			hold = findPredecessor(deleteNode);
			deleteNode.setData(hold.getData());
			deleteNode = hold;
			deleteHere(deleteNode, deleteNode.getLeft());
		}
		size--;
	}

	// find rightmost node:
	public T findMax()
	{
		BinaryTree<T> t = tree;
		if (isEmpty())
			return null;
		while (t.getRight() != null)
			t = t.getRight();
		return t.getData();
	}

	// find leftmost node:
	public T findMin()
	{
		BinaryTree<T> t = tree;
		if (isEmpty())
			return null;
		while (t.getLeft() != null)
			t = t.getLeft();
		return t.getData();
	}

	//driver method
	public BinaryTree<T> recursiveSearch(T key)
	{
		if (tree.isEmpty())
			return null;
		else
			return recursiveSearch(tree, key);
	}

	// recursive search method
	public BinaryTree<T> recursiveSearch(BinaryTree<T> t, T key)
	{
		//base case:
		if (key.compareTo(t.getData()) == 0)
			return t;
		else
		{
			if (key.compareTo(t.getData()) < 0)
				return recursiveSearch(t.getLeft(), key);
			else // >0
				return recursiveSearch(t.getRight(), key);
		}
	}
	
	public boolean isBinarySearchTree(BinaryTree<T> t, T min, T max) //using min and max to set the limits of each child-node
	{
		//base case:
		if (t.getData() == null)
			return true;
		if (t.getData().compareTo(min) > 0 && t.getData().compareTo(max) < 0 && isBinarySearchTree(t.getLeft(), min, t.getData()) && isBinarySearchTree(t.getRight(), t.getData(), max))
			return true;
		else
			return false;
	}
	
	//helper method:
	public static <T> int inorder(BinaryTree<T> t, T[] a, int i)
	{
		if (t==null) //base case
		{
			return i;
		}
		i = inorder(t.getLeft(), a, i);
		a[i] = t.getData();
		i = inorder(t.getRight(), a, i +1);
		return i;
	}
	
	//can't figure out this one:
	public BinarySearchTree<T> merge(BinarySearchTree<T> t1, BinarySearchTree<T> t2)
	{
		BinarySearchTree<T> t3 = new BinarySearchTree<T>();
		int[] a = new int[t1.size];
		//inorder(t1, a, 0);
		
		
		return t3;
	}
	
	
}
