/**
 * Unordered List
 */
public class List<T> {
	private LinkedList<T> elements;
	private int cursor;

	public List() {
		elements = new LinkedList<T>();
		cursor = -1;
	}

	// add an item to the unordered list
	public void add(T item) {
		elements.add(item);
	}

	public int size() {
		return elements.size();
	}

	public boolean isEmpty() {
		return elements.isEmpty();
	}

	public boolean contains(T item) {
		return (elements.indexOf(item) != -1);
	}

	public void remove(T item) {
		elements.remove(item);
	}

	// remove all occurrences of a given item
	public void removeAll(T item) {
		elements.removeAll(item);
	}

	public void clear() {
		elements.clear();
	}

	public void enumerate() {
		elements.enumerate();
	}

	// get the first item
	public T first() {
		if (elements.size() == 0) {
			return null;
		}
		cursor = 0;

		return elements.getAt(cursor);
	}

	// get the next item
	public T next() {
		if (cursor < 0 || cursor == (elements.size() - 1)) {
			return null;
		}
		cursor++;

		return elements.getAt(cursor);
	}
}
