public class LinkedList<T> {
	private Node<T> front;
	private int count;

	public LinkedList() {
		front = null;
		count = 0;
	}

	public Node<T> getFront() {
		return front;
	}

	// adds an item to the front of the linked list
	public void add(T item) {
		Node<T> newNode = new Node<T>(item, front);
		front = newNode;
		count++;
	}

	public int size() {
		return count;
	}

	public void clear() {
		front = null;
		count = 0;
	}

	public boolean isEmpty() {
		return (count == 0);
	}

	public void enumerate() {
		Node<T> curr = front;

		while (curr != null) {
			System.out.print(curr.getData() + "--> ");
			curr = curr.getNext();
		}
	}

	// returns the data at a given index
	public T getAt(int index) {
		Node<T> curr = front;

		if (index < 0 || index >= count) {
			System.out.println("Error. Index out of bounds");
			return null;
		}

		for (int i = 0; i < index; i++) {
			curr = curr.getNext();
		}

		return curr.getData();
	}

	// inserts an item at a given index
	public void insertAt(T item, int index) {
		if (index < 0 || index > count) {
			System.out.println("Can't insert. Index out of bounds.");
			return;
		}
		if (index == 0) {
			add(item);
			return;
		}
		Node<T> prev = front;

		for (int i = 0; i < index - 1; i++) {
			prev = prev.getNext();
		}

		Node<T> itemnode = new Node<T>(item, prev.getNext());
		prev.setNext(itemnode);
		count++;
	}

	// sets the given data at a given index
	public void setAt(T item, int index) {
		if (index < 0 || index >= count) {
			System.out.println("Can't set. Index out of bounds");
			return;
		}
		Node<T> curr = front;

		for (int i = 0; i < index; i++) {
			curr = curr.getNext();
		}

		curr.setData(item);
	}

	// returns the index of the first occurrence of a given item, or -1 if not found
	public int indexOf(T item) {
		Node<T> curr = front;

		for (int i = 0; i < count; i++) {
			if (item.equals(curr.getData())) {
				return i;
			}
			curr = curr.getNext();
		}

		return -1;
	}

	// removes the node at a given index
	public T removeAt(int index) {
		T result = null;

		if (index < 0 || index >= count) {
			System.out.println("Can't remove. Index out of bounds");
			return null;
		} else {
			if (index == 0) {
				result = front.getData();
				front = front.getNext();
			} else {
				Node<T> prev = front;

				for (int i = 0; i < index - 1; i++) {
					prev = prev.getNext();
				}

				result = prev.getNext().getData();
				prev.setNext(prev.getNext().getNext());
			}

			count--;
		}

		return result;
	}

	// removes the node containing the first occurrence of a given item
	public void remove(T item) {
		int i = indexOf(item);

		if (i == -1) {
			System.out.println("No such item");
			return;
		} else {
			removeAt(i);
		}
	}

	// Removes all nodes containing a given item in one scan using O(n) time
	public void removeAll(T item) {
		Node<T> curr = front, prev = null;

		while (curr != null) {
			if (front.getData().equals(item)) {
				front = front.getNext();
				curr = curr.getNext();
				count--;
			} else if (curr == front) {
				prev = curr;
				curr = curr.getNext();
			} else {
				if (curr != null) {
					if (curr.getData().equals(item)) {
						prev.setNext(curr.getNext());
						curr = prev.getNext();
						count--;
					} else {
						prev = curr;
						curr = curr.getNext();
					}
				}
			}
		}
	}

	// Adds an item to the end of the linked list
	public void addToEnd(T item) {
		Node<T> newN;

		if (front == null) {
			newN = new Node<T>(item, null);
			front = newN;
		} else {
			newN = new Node<T>(item, null);
			Node<T>	curr = front;

			while (curr.getNext() != null) {
				curr = curr.getNext();
			}

			curr.setNext(newN);
		}

		count++;
	}
}
