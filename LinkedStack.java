package towerofhanoi;

// Virginia Tech Honor Code Pledge:
//f
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I accept the actions of those who do.
// Sicheng Fan
import java.util.EmptyStackException;
import stack.StackInterface;

/**
 * class represents a stack data structure that is implemented using a linked
 * list.
 * 
 * @author
 */
public class LinkedStack<T> implements StackInterface<T> {

	private int size;
	private Node topNode;

	/**
	 * 
	 * Constructs a new LinkedStack object with a null top node, indicating an empty
	 * stack.
	 */
	public LinkedStack() {
		this.topNode = null;
	}

	/**
	 * 
	 * Returns the number of elements in this stack.
	 * 
	 * @return the number of elements in this stack
	 */
	public int size() {
		return size;
	}

	/**
	 * 
	 * Returns true if this stack contains no elements.
	 * 
	 * @return true if this stack contains no elements, false otherwise
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * 
	 * Removes all of the elements from this stack.
	 */
	public void clear() {
		this.size = 0;
		this.topNode = null;
	}

	/**
	 * Returns a string representation of this stack. The string representation
	 * consists of a list of the stack's elements in the order they are popped from
	 * the stack, enclosed in square brackets, elements are separated by the
	 * characters ", ".
	 * 
	 * @return a string representation of this stack
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Node current = topNode;
		sb.append("[");
		while (current != null) {
			sb.append(current.data);
			current = current.next;
			if (current != null) {
				sb.append(", ");
			}
		}
		sb.append("]");
		return sb.toString();
	}

	/**
	 * Adds a new item to the stack
	 * 
	 * @param t the element to be pushed onto this stack
	 * 
	 */
	public void push(T t) {
		if (isEmpty()) {
			topNode = new Node(t);
		} else {
			Node newNode = new Node(t, topNode);
			topNode = newNode;
		}
		size++;
	}

	/**
	 * 
	 * Removes and returns the top element of the stack.
	 * 
	 * @return the top element of the stack.
	 * @throws EmptyStackException if the stack is empty.
	 */
	public T pop() {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		T data = topNode.data;
		topNode = topNode.next;
		size--;
		return data;
	}

	/**
	 * 
	 * Returns the element at the top of the stack without removing it.
	 * 
	 * @return the element at the top of the stack.
	 * @throws EmptyStackException if the stack is empty.
	 */
	public T peek() {
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		return topNode.getData();
	}

	/**
	 * The private inner class Node represents a node in a singly linked list used
	 * to implement the stack. Each node contains a data element of type T and a
	 * reference to the next node in the list.
	 */
	private class Node {

		private T data;

		public Node next;

		/**
		 * Constructs a new node with the given data element.
		 * 
		 * @param data the data element of type T for the node
		 */
		public Node(T data) {
			this.data = data;
		}

		/**
		 * Constructs a new node with the given data element and reference to the next
		 * node.
		 * 
		 * @param entry the data element of type T for the node
		 * @param node  the reference to the next node in the list
		 */
		public Node(T entry, Node node) {
			this(entry);
			this.setNextNode(node);
		}

		/**
		 * Sets the reference to the next node in the list.
		 * 
		 * @param node the reference to the next node in the list
		 */
		public void setNextNode(Node node) {
			this.next = node;
		}

		/**
		 * Gets the reference to the next node in the list.
		 * 
		 * @return the reference to the next node in the list
		 */
		public Node getNextNode() {
			return next;
		}

		/**
		 * Gets the data element of the node.
		 * 
		 * @return the data element of type T for the node
		 */
		public T getData() {
			return data;
		}
	}
}
