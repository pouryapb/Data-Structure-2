package pouryapb;

public class Queue<E> {

	/**
	 * list of elements in the queue.
	 */
	private LinkedList<E> list = new LinkedList<>();

	/**
	 * adds a new element to the queue.
	 * 
	 * @param value
	 */
	public void enqueue(E value) {
		list.addFirst(value);
	}

	/**
	 * removes first element and returns it.
	 * 
	 * @return value of first element
	 */
	public E dequeue() {
		if (list.isEmpty())
			return null;
		var value = list.getFirst();
		list.deleteFirst();
		return value;
	}

	/**
	 * returns first element without removing it.
	 * 
	 * @return value of first element
	 */
	public E getFirst() {
		if (list.isEmpty())
			return null;
		return list.getFirst();
	}

	/**
	 * 
	 * @return size of queue
	 */
	public int getSize() {
		return list.getSize();
	}

	/**
	 * clears the queue
	 */
	public void clear() {
		list.clear();
	}

	/**
	 * 
	 * @return True if queue is empty
	 */
	public boolean isEmpty() {
		return list.isEmpty();
	}
}
