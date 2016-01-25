
public class Queue<T>
{
	private LinkedList<T> list;
	int cursor; //mainly used for the first and next methods
	
	public Queue()
	{
		list = new LinkedList<T>();
		cursor = -1;
	}
	
	public void enqueue(T item)
	{
		if (list.size()==0)
			list.add(item);
		else
			list.addToEnd(item);
	}
	
	public T dequeue()
	{
		if (list.size() == 0)
			return null;
		else
		{
			T item = list.getAt(1); //might be 1
			list.removeAt(1); //might be 1
			return item;
		}
	}
	
	public int size()
	{
		return list.size(); 
	}
	
	public boolean isEmpty()
	{
		return (list.size() == 0);
	}
	
	public void clear()
	{
		list.clear();
	}
	
	public T peek() //need trivial cases
	{
		if (list.size() == 0)
			return null;
		else
			return list.getAt(0);
	}
	
	public int positionOf(T item) //need trivial cases
	{
		return list.indexOf(item);
	}
	
	public void remove(T item) //need trivial cases
	{
		list.remove(item);
	}
	
	public T first()
	{
		if (list.size() == 0)
			return null;
		cursor = 0;
		return list.getAt(cursor);
	}
	
	public T next()
	{
		if(cursor < 0 || cursor == list.size()-1)
		{
			return null;
		}
		cursor++;
		return list.getAt(cursor);
	}
}
