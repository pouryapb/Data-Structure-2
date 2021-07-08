package pouryapb;

public class LinkedList<E> {

	static class Node<E> {

		/**
		 * next Element in the list.
		 */
		Node<E> next;
		/**
		 * previous Element in the list .
		 */
		Node<E> prev;
		/**
		 * value of current Element.
		 */
		E value;

		/**
		 * stores the value.
		 * 
		 * @param value
		 */
		public Node(E value) {
			this.value = value;
		}
	}

	/**
	 * First Element of List.
	 */
	private Node<E> head;
	/**
	 * Last Element of List.
	 */
	private Node<E> tail;
	/**
	 * Size of the List.
	 */
	private int size = 0;

	/**
	 * Adds an Element to the beginning of the List.
	 * 
	 * @param value
	 */
	public void addFirst(E value) {
		Node<E> newNode = new Node<>(value);
		if (head == null || tail == null) {
			head = tail = newNode;
		} else {
			newNode.next = head;
			head.prev = newNode;
			head = newNode;
		}
		size++;
	}

	/**
	 * Adds an Element to the end of the List.
	 * 
	 * @param value
	 */
	public void addLast(E value) {
		Node<E> newNode = new Node<>(value);
		if (head == null || tail == null) {
			head = tail = newNode;
		} else {
			tail.next = newNode;
			newNode.prev = tail;
			tail = newNode;
		}
		size++;
	}

	/**
	 * removes first element of the list.
	 */
	public void deleteFirst() {
		if (head != null) {
			head = head.next;
			try {
				head.prev = null;
			} catch (Exception e) {
			}
			size--;
		}
	}

	/**
	 * removes last element of the list.
	 */
	public void deleteLast() {
		if (tail != null) {
			tail = tail.prev;
			try {
				tail.next = null;
			} catch (Exception e) {
			}
			size--;
		}
	}

	/**
	 * 
	 * @return value of the first element
	 */
	public E getFirst() {
		if (head == null)
			return null;
		return head.value;
	}

	/**
	 * 
	 * @return value of the last element
	 */
	public E getLast() {
		if (tail == null)
			return null;
		return tail.value;
	}

	/**
	 * 
	 * @return size of the list
	 */
	public int getSize() {
		return size;
	}

	/**
	 * clears the list.
	 */
	public void clear() {
		head = tail = null;
		size = 0;
	}

	/**
	 * checks if the list is empty or not.
	 * 
	 * @return True if list is empty
	 */
	public boolean isEmpty() {
		return size == 0;
	}
}
