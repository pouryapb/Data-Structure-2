package pouryapb;

public class Stack<E> {

	/**
	 * List of elements in the Stack.
	 */
	private LinkedList<E> list = new LinkedList<>();

	/**
	 * Removes the element on the top of the stack and returns it.
	 * 
	 * @return Element on top of the stack
	 */
	public E pop() {
		if (list.isEmpty())
			return null;
		var value = list.getLast();
		list.deleteLast();
		return value;
	}

	/**
	 * Adds a new element on top of the stack.
	 * 
	 * @param value
	 */
	public void push(E value) {
		list.addLast(value);
	}

	/**
	 * Returns the element on top of the stack without removing it.
	 * 
	 * @return element on top of the stack
	 */
	public E peek() {
		if (list.isEmpty())
			return null;
		return list.getLast();
	}

	/**
	 * 
	 * @return size of the stack
	 */
	public int getSize() {
		return list.getSize();
	}

	/**
	 * Clears the stack
	 */
	public void clear() {
		list.clear();
	}

	/**
	 * 
	 * @return True if stack is empty
	 */
	public boolean isEmpty() {
		return list.isEmpty();
	}
}
