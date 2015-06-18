package com.amazon;

import java.util.Arrays;

/**
 * MinHeap is an implementation of the BinaryHeap interface. It stores elements
 * in array of type T. </br></br> Correct order of the elements in the MinHeap
 * is such that the parent should be lesser or equal to its children.
 * 
 * @param <T>
 *            Type of the elements which MinHeap would hold.
 *
 * @author Matej Risek
 */
public class MinHeap<T extends Comparable<T>> implements BinaryHeap<T> {

	private static final int INIT_CAPACITY = 16;
	private T[] array;
	private int size;

	@SuppressWarnings("unchecked")
	public MinHeap() {
		array = (T[]) new Comparable[INIT_CAPACITY];
		size = 0;
	}

	public MinHeap(T[] array) {
		this();
		this.heapify(array);
	}
	
	/**
	 * Add method runs in <b>O(log n)</b> for the worst case and <b>O(1)</b> on average.
	 */
	@Override
	public void add(T element) {
		/*
		 * Check if array is too small for additional element. If it is, array
		 * is doubled in size through call to instance method resize.
		 */
		if (size >= array.length) {
			array = this.resize();
		}

		int lastIndex = size++;
		array[lastIndex] = element;
		this.upHeap();
	}

	/**
	 * Peek in this implementation runs in <b>O(1)</b>.</br>
	 * It calls convenience method peek_min and retrieves smallest element from the
	 * heap.   
	 */
	@Override
	public T peek() {
		return peek_min();
	}

	public T peek_min() {
		if (size < 1)
			return null;
		return array[0];
	}
	
	/**
	 * Pop in this implementation runs in <b>O(log n)</b>.</br>
	 * Run time heavily depends on the necessary swaps in downHeap method.
	 * This method calls convenience method pop_min and retrieves smallest element
	 * from the heap.</br>The element is removed from the heap afterwards.
	 */
	@Override
	public T pop() {
		return pop_min();
	}

	public T pop_min() {
		if (size < 1)
			return null;
		T element = array[0];
		array[0] = array[size - 1];
		array[size - 1] = null;
		size--;
		downHeap(0);

		return element;
	}

	/**
	 * The delete method takes <b>O(log n)</b> for element to find. </br>
	 * It removes first element that matches provided element parameter. 
	 * After removal heap is rebuilt which takes <b>O(n log n)</b>.
	 * 
	 */
	@Override
	public boolean delete(T element) {
		int elementIndex = findElementIndex(element);
		if (elementIndex == -1)
			return false;

		for (int index = findElementIndex(element); index < size; index++) {
			array[index] = array[index + 1];
		}
		array[size] = null;
		size--;
		this.reheapify();
		return true;
	}

	@Override
	public void heapify(T[] array) {
		for (T t : array) {
			if (t == null)
				continue;
			add(t);
		}
	}

	private void reheapify() {
		int size = this.size;
		this.size = 0;
		for (int i = 0; i < size; i++) {
			add(array[i]);
		}
	}

	private int getParentIndex(int c) {
		return (c - 1) / 2;
	}

	private boolean hasParent(int c) {
		return c > 0;
	}

	private int getLeftChildIndex(int p) {
		return p * 2 + 1;
	}

	private boolean hasLeftChild(int p) {
		return getLeftChildIndex(p) < size && array[getLeftChildIndex(p)] != null;
	}

	private int getRightChildIndex(int p) {
		return p * 2 + 2;
	}

	private boolean hasRightChild(int p) {
		return getRightChildIndex(p) < size && array[getRightChildIndex(p)] != null;
	}

	private int findElementIndex(T element) {
		for (int i = 0; i < size; i++) {
			if (array[i].equals(element)) {
				return i;
			}
		}
		return -1;
	}

	private T[] resize() {
		return Arrays.copyOf(array, array.length * 2);
	}

	private void swap(int firstIndex, int secondIndex) {
		T temp = array[firstIndex];
		array[firstIndex] = array[secondIndex];
		array[secondIndex] = temp;
	}

	/**
	 * Up heap method. Method is used after the insertion of the element.</br>
	 * It goes from last element in the heap and puts element in correct place
	 * by comparing it with its parents, and doing the swap if it's necessary.
	 */
	private void upHeap() {
		int elementIndex = size - 1;
		while (hasParent(elementIndex) && array[elementIndex].compareTo(array[getParentIndex(elementIndex)]) < 0) {
			this.swap(elementIndex, getParentIndex(elementIndex));
			elementIndex = getParentIndex(elementIndex);

		}
	}

	/**
	 * Down heap method. Method is used after pop method.</br> Pop removes top
	 * element of the heap and put the last one to the top.</br> After that
	 * downHeap should compare top element with its children and swap them if
	 * it's necessary to remain correct order.
	 * 
	 * @param index
	 *            Index of the element.
	 */
	private void downHeap(int index) {

		int smallest = index;

		// Check if left child is smaller than parent.
		if (hasLeftChild(index) && array[getLeftChildIndex(index)].compareTo(array[smallest]) < 0) {
			smallest = getLeftChildIndex(index);
		}

		// Check if right child is larger than parent.
		if (hasRightChild(index) && array[getRightChildIndex(index)].compareTo(array[smallest]) < 0) {
			smallest = getRightChildIndex(index);
		}

		/*
		 * Swaps smallest child with parent and proceeds recursively with
		 * downHeap using index of the smallest item.
		 */
		if (smallest != index) {
			this.swap(index, smallest);
			downHeap(smallest);
		}
	}

}
