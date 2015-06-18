package com.amazon;

/**
 * Interface for binary heap. </br></br> Implementor should maintain desired
 * correct order because none is forced by the interface.
 * 
 * @param <T>
 *            Type of the elements in the heap.
 * 
 * @author Matej Risek
 *
 */
public interface BinaryHeap<T extends Comparable<T>> {
	
	/**
	 * Adds new element to heap.</br></br>
	 * Element have to take place in heap according to rules:</br>
	 * <pre>
	 * 1. Add the element to the bottom level of the heap.
	 * 2. Compare the added element with its parent; if they are in the correct order, stop.
	 * 3. If not, swap the element with its parent and return to the previous step.
	 * </pre>
	 * @param element Element which would be added to the heap.
	 */
	void add(T element);
	
	/**
	 * Returns first element of the heap.</br></br>
	 * Example:
	 * <pre>
	 *  For minHeap implementation the element returned will be
	 *  the smallest element in the heap.
	 * </pre>
	 * @return Top element of the heap.
	 */
	T peek();
	
	/**
	 * Returns first element of the heap and removes it from the heap.
	 * 
	 * @return Top element of the heap.
	 */
	T pop();
	
	/**
	 * Removes first match of the element.
	 * Method performs rebuild of the heap on every delete.
	 * 
	 * @param element
	 * @return true when element is successfully deleted.
	 * @return false when element is not found
	 */
	boolean delete(T element);
	
	/**
	 * Add elements from provided array to existing BinaryHeap.
	 * 
	 * @param array Array with elements of same type as instantiated BinaryHeap.
	 */
	void heapify(T[] array);
	
}
